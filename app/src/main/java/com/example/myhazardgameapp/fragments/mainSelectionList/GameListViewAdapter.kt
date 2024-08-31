package com.example.myhazardgameapp.fragments.mainSelectionList

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.fragments.GameSelectedFragment
import com.example.myhazardgameapp.fragments.GameSelectedListFragment
import com.example.myhazardgameapp.other.AppToast
import com.example.myhazardgameapp.fragments.gameSelectedList.SelectedGameChangeSupport
import com.example.myhazardgameapp.other.FragmentStack
import com.example.myhazardgameapp.other.Game

class GameListViewAdapter(
    private val context: FragmentActivity,
    private val games: Array<Game>
) : ArrayAdapter<Game>(context, R.layout.fragment_main_activity_game_list, games) {

    private var filteredGames: Array<Game> = games
    private var filteredByTitle: Array<Game> = games
    private var filteredByPlayers: Array<Game> = games
    private var filteredByType: Array<Game> = games

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(
            R.layout.fragment_main_activity_game_list,
            null, true
        )

        val titleView = rowView.findViewById<TextView>(R.id.game_list_view_title)
        val imageView = rowView.findViewById<ImageView>(R.id.game_list_view_image)
        val playersView = rowView.findViewById<TextView>(R.id.game_list_view_players)

        val game = filteredGames[position]

        rowView.setOnClickListener {
            val buttonClick = ScaleAnimation(
                1f, 0.9f,
                1f, 0.9f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            buttonClick.duration = 200
            rowView.startAnimation(buttonClick)

            SelectedGameChangeSupport.SelectedGame = filteredGames[position]

            FragmentStack.mainStack.push(GameSelectedFragment())

            val fragmentTransaction = context.supportFragmentManager.beginTransaction()
            fragmentTransaction
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_right)
                .replace(R.id.main_fragment_container , GameSelectedFragment())
                .commit()
        }

        titleView.text = game.title
        imageView.setImageResource(game.image)
        playersView.text = game.playerCount

        return rowView
    }

    private fun applyFilters() {
        filteredGames = filteredByTitle.intersect(filteredByPlayers.asIterable())
            .intersect(filteredByType.asIterable()).toTypedArray()

        if (filteredGames.isEmpty()) {
            AppToast.showToast(context, "No results found")
        }

        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return filteredGames.size
    }

    override fun getItem(position: Int): Game? {
        return filteredGames[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getFilterByTitle(before: Int): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint.isNullOrEmpty()) {
                    filteredByTitle = games
                } else {
                    filteredByTitle = games.filter {
                        it.title.contains(constraint, ignoreCase = true)
                    }.toTypedArray()
                }
                filterResults.values = filteredByTitle
                filterResults.count = filteredByTitle.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredGames = filteredByTitle.intersect(filteredByPlayers.asIterable())
                    .intersect(filteredByType.asIterable()).toTypedArray()

                if (filteredGames.isEmpty() && before == 0) {
                    AppToast.showToast(context, "No results found")
                }

                notifyDataSetChanged()
            }
        }
    }

    fun getFilterByPlayers(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()

                if (constraint.isNullOrEmpty() || constraint.length < 2) {
                    filteredByPlayers = games
                } else {
                    val min = constraint[0].toString().toIntOrNull()
                    val max = constraint[1].toString().toIntOrNull()

                    if (min != null && max != null) {
                        filteredByPlayers = games.filter {
                            val playerCount = it.playerCount.filter { char ->
                                char.isDigit()
                            }.toIntOrNull() ?: 0
                            playerCount in min..max
                        }.toTypedArray()
                    } else {
                        filteredByPlayers = games
                    }
                }
                filterResults.values = filteredByPlayers
                filterResults.count = filteredByPlayers.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                applyFilters()
            }
        }
    }

    fun getFilterByType(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint.isNullOrEmpty() || constraint == "all") {
                    filteredByType = games
                } else {
                    filteredByType = games.filter {
                        it.type.contains(constraint, ignoreCase = true)
                    }.toTypedArray()
                }
                filterResults.values = filteredByType
                filterResults.count = filteredByType.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                applyFilters()
            }
        }
    }
}
