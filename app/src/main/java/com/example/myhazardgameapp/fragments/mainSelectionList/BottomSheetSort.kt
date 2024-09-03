import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.other.gamesFiles.Game
import com.example.myhazardgameapp.fragments.mainSelectionList.GameListViewAdapter
import com.example.myhazardgameapp.fragments.mainSelectionList.SortStatus
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetSort : BottomSheetDialogFragment() {
    lateinit var adapter: ArrayAdapter<Game>
    lateinit var chosenStatus: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val v: View = inflater.inflate(
            R.layout.bottom_sheet_sort,
            container, false
        )
        return v
    }

    fun setAdapter(gameListViewAdapter: GameListViewAdapter) {
        adapter = gameListViewAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sortButton = view.findViewById<TextView>(R.id.bottom_sheet_sort_button)
        val radioGroup = view.findViewById<RadioGroup>(R.id.bottom_sheet_sort_radiogroup)

        var radioButton = view.findViewById<RadioButton>(radioGroup!!.checkedRadioButtonId)
        if(SortStatus.id == 0){
            SortStatus.id = radioGroup.checkedRadioButtonId
        }
        radioGroup.check(SortStatus.id)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            radioButton = group.findViewById<RadioButton>(checkedId)
            chosenStatus = radioButton.text.toString()
        }

        sortButton.setOnClickListener {
            if(chosenStatus == "Nazwa: A-Z"){
                adapter.sort { game, game2 -> game.compereAlfabetical(game, game2) }
            }
            if(chosenStatus == "Nazwa: Z-A"){
                adapter.sort { game, game2 -> game.compereAlfabetical(game2, game) }
            }
            if(chosenStatus == "Więcej graczy"){
                adapter.sort { game, game2 -> game.comperePlayers(game2, game) }
            }
            if(chosenStatus == "Mniej graczy"){
                adapter.sort { game, game2 -> game.comperePlayers(game, game2) }
            }

            SortStatus.status = radioButton.text
            SortStatus.id = radioButton.id

            dismiss()
        }
    }
}