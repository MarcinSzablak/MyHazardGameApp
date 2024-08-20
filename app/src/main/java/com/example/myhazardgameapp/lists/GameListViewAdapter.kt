package com.example.myhazardgameapp.lists

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Interpolator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.SortedList
import com.example.myhazardgameapp.R
import java.util.Comparator

class GameListViewAdapter(
    private val context: Activity,
    private val games: Array<Game>
) : ArrayAdapter<Game>(context, R.layout.fragment_main_activity_list_view, games) {

    private var filteredGames: Array<Game> = games

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(
            R.layout.fragment_main_activity_list_view,
            null, true
        )

        val titleView = rowView.findViewById<TextView>(R.id.game_list_view_title)
        val imageView = rowView.findViewById<ImageView>(R.id.game_list_view_image)
        val playersView = rowView.findViewById<TextView>(R.id.game_list_view_players)

        val game = filteredGames[position]

        titleView.text = game.title
        imageView.setImageResource(game.image)
        playersView.text = game.playerCount

        rowView.setOnClickListener { event -> }

        rowView.setOnClickListener {
            val buttonClick = ScaleAnimation(
                1f, 0.95f,
                1f, 0.95f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
            buttonClick.duration = 300
            rowView.startAnimation(buttonClick)
        }

        return rowView
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint.isNullOrEmpty()) {
                    filterResults.values = games
                    filterResults.count = games.size
                } else {
                    val filteredList = games.filter {
                        it.title.contains(constraint, ignoreCase = true)
                    }
                    filterResults.values = filteredList.toTypedArray()
                    filterResults.count = filteredList.size
                }
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredGames = results?.values as Array<Game>

                if(filteredGames.isEmpty()){
                    val appToast = AppToast.showToast(context, "No results found")
                }

                notifyDataSetChanged()
            }
        }
    }
}
