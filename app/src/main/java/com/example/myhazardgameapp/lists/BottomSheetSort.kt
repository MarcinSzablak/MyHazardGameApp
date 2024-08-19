import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.myhazardgameapp.R
import com.example.myhazardgameapp.lists.Game
import com.example.myhazardgameapp.lists.GameListViewAdapter
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
        val sortujButton = view?.findViewById<TextView>(R.id.main_fragment_sort_button)
        val radioGroup = view?.findViewById<RadioGroup>(R.id.main_fragment_sort_radiogroup)

        var chosenOption: CharSequence = ""

        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            var radioButton = group.findViewById<RadioButton>(checkedId)

            chosenOption = radioButton.text
        }


        sortujButton?.setOnClickListener {
            if(chosenOption == "Nazwa: A-Z"){
                adapter.sort { game, game2 -> game.compereAlfabetical(game, game2) }
            }
            if(chosenOption == "Nazwa: Z-A"){
                //yee it works just changing places of compered games
                adapter.sort { game, game2 -> game.compereAlfabetical(game2, game) }
            }
            if(chosenOption == "Więcej graczy"){
                //yee it works just changing places of compered games
                adapter.sort { game, game2 -> game.comperePlayers(game2, game) }
            }
            if(chosenOption == "Mniej graczy"){
                //yee it works just changing places of compered games
                adapter.sort { game, game2 -> game.comperePlayers(game, game2) }
            }

            dismiss()
        }
    }
}