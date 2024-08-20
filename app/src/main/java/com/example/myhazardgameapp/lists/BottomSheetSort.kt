import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.lists.Game
import com.example.myhazardgameapp.lists.GameListViewAdapter
import com.example.myhazardgameapp.lists.SortStatus
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetDialog : BottomSheetDialogFragment() {
    lateinit var adapter: ArrayAdapter<Game>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        val sortButton = view.findViewById<TextView>(R.id.main_fragment_sort_button)
        val radioGroup = view.findViewById<RadioGroup>(R.id.main_fragment_sort_radiogroup)

        var radioButton = view.findViewById<RadioButton>(radioGroup!!.checkedRadioButtonId)
        if(SortStatus.id == 0){
            SortStatus.id = radioGroup.checkedRadioButtonId
        }
        radioGroup.check(SortStatus.id)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            radioButton = group.findViewById<RadioButton>(checkedId)

            SortStatus.status = radioButton.text
            SortStatus.id = radioButton.id
        }

        sortButton.setOnClickListener {
            if(SortStatus.status == "Nazwa: A-Z"){
                adapter.sort { game, game2 -> game.compereAlfabetical(game, game2) }
            }
            if(SortStatus.status == "Nazwa: Z-A"){
                //yee it works just changing places of compered games
                adapter.sort { game, game2 -> game.compereAlfabetical(game2, game) }
            }
            if(SortStatus.status == "Więcej graczy"){
                //yee it works just changing places of compered games
                adapter.sort { game, game2 -> game.comperePlayers(game2, game) }
            }
            if(SortStatus.status == "Mniej graczy"){
                //yee it works just changing places of compered games
                adapter.sort { game, game2 -> game.comperePlayers(game, game2) }
            }

            dismiss()
        }
    }
}