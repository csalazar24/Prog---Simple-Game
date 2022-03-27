package com.zybooks.tictactoe
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zybooks.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    //private lateinit var game: TicTacToe

    var boardList = mutableListOf<Button>()
    var boardListString = mutableListOf<String>()
    private var firstTurn = 1
    private var currentTurn = 1


    private var XScore = 0
    private var OScore = 0

    var temp: Boolean = false



    private lateinit var binding : ActivityMainBinding
    private lateinit var startNewGameButton: Button
    private lateinit var player1Points: TextView
    private lateinit var player2Points: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //game = TicTacToe()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startNewGameButton = binding.startNewGameButton


        player1Points = binding.playerOneScore
        player2Points = binding.playerTwoScore


        if (savedInstanceState == null) {
            initBoard()
            startNewGameButton.visibility = View.GONE

        }
        else{



            binding.b00.text = savedInstanceState.getString("b00")
            binding.b01.text = savedInstanceState.getString("b01")
            binding.b02.text = savedInstanceState.getString("b02")
            binding.b10.text = savedInstanceState.getString("b10")
            binding.b11.text = savedInstanceState.getString("b11")
            binding.b12.text = savedInstanceState.getString("b12")
            binding.b20.text = savedInstanceState.getString("b20")
            binding.b21.text = savedInstanceState.getString("b21")
            binding.b22.text = savedInstanceState.getString("b22")
            initBoard()
            currentTurn = savedInstanceState.getInt("CurrentTurn")
            XScore = savedInstanceState.getInt("Player1Points")
            OScore = savedInstanceState.getInt("Player2Points")
            updateScores()
            //resetBoard()
            temp = savedInstanceState.getBoolean("temp")

            if(temp){
                resetBoard()
                startNewGameButton.visibility = View.GONE
                temp = false
            }

        }




    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("b00", binding.b00.text.toString())
        outState.putString("b01", binding.b01.text.toString())
        outState.putString("b02", binding.b02.text.toString())
        outState.putString("b10", binding.b10.text.toString())
        outState.putString("b11", binding.b11.text.toString())
        outState.putString("b12", binding.b12.text.toString())
        outState.putString("b20", binding.b20.text.toString())
        outState.putString("b21", binding.b21.text.toString())
        outState.putString("b22", binding.b22.text.toString())
        outState.putInt("CurrentTurn", currentTurn)
        outState.putInt("Player1Points", XScore)
        outState.putInt("Player2Points", OScore)

        outState.putBoolean("temp",temp)


    }

    private fun initBoard()
    {
        //game.resetBoard()
        boardList.add(binding.b00)
        boardList.add(binding.b01)
        boardList.add(binding.b02)
        boardList.add(binding.b10)
        boardList.add(binding.b11)
        boardList.add(binding.b12)
        boardList.add(binding.b20)
        boardList.add(binding.b21)
        boardList.add(binding.b22)
    }

    fun boardTapped(view: View)
    {
        val handler = Handler()


       // if(!temp) {
            if (view !is Button)
                return

            addToBoard(view)


            if (isGameOver("O")) {

                OScore++
                updateScores()
                Toast.makeText(applicationContext, "O wins", Toast.LENGTH_SHORT).show()

                handler.postDelayed(Runnable {
                    startNewGameButton.visibility = View.VISIBLE
                }, 3000)
                disableButtons()
                temp = true

            } else if (isGameOver("X")) {
                XScore++
                updateScores()
                Toast.makeText(this, "X wins", Toast.LENGTH_SHORT).show()

                handler.postDelayed(Runnable {
                    startNewGameButton.visibility = View.VISIBLE
                }, 3000)
                disableButtons()
                temp = true
            }

            if (isBoardFull()) {

                Toast.makeText(applicationContext, "Tie", Toast.LENGTH_SHORT).show()

                handler.postDelayed(Runnable {
                    startNewGameButton.visibility = View.VISIBLE
                }, 3000)
                disableButtons()
                temp = true

            }
       // }


        startNewGameButton.setOnClickListener {

            startNewGameButton.visibility = View.GONE
            enableButtons()
            resetBoard()
            temp = false
        }

    }

    private fun isGameOver(s: String): Boolean
    {
        //Horizontal Victory
        if(binding.b00.text == s && binding.b01.text == s && binding.b02.text == s)
            return true
        if(binding.b10.text == s && binding.b11.text == s && binding.b12.text == s)
            return true
        if(binding.b20.text == s && binding.b21.text == s && binding.b22.text == s)
            return true

        //Vertical Victory
        if(binding.b00.text == s && binding.b10.text == s && binding.b20.text == s)
            return true
        if(binding.b01.text == s && binding.b11.text == s && binding.b21.text == s)
            return true
        if(binding.b02.text == s && binding.b12.text == s && binding.b22.text == s)
            return true

        //Diagonal Victory
        if(binding.b00.text == s && binding.b11.text == s && binding.b22.text == s)
            return true
        if(binding.b02.text == s && binding.b11.text == s && binding.b20.text == s)
            return true

        return false
    }



    private fun updateScores() {
        player1Points.text = "Player X Points: $XScore"
        player2Points.text = "Player O Points: $OScore"

    }

    fun resetBoard()
    {

        for(button in boardList)
        {
            button.text = ""
        }

        if(firstTurn == 0)
            firstTurn = 1
        else if(firstTurn == 0)
            firstTurn = 1

        currentTurn = firstTurn

    }

    fun isBoardFull(): Boolean
    {
        for(button in boardList){

            if(button.text == "") {
                return false
            }
        }
        return true
    }

    fun addToBoard(button: Button)
    {
        if(button.text != "")
            return

        if(currentTurn == 0)
        {
            button.text = "O"
            currentTurn = 1
        }
        else if(currentTurn == 1)
        {
            button.text = "X"
            currentTurn = 0
        }

    }

    fun disableButtons(){
        binding.b00.isEnabled = false
        binding.b01.isEnabled = false
        binding.b02.isEnabled = false
        binding.b10.isEnabled = false
        binding.b11.isEnabled = false
        binding.b12.isEnabled = false
        binding.b20.isEnabled = false
        binding.b21.isEnabled = false
        binding.b22.isEnabled = false
    }

    fun enableButtons(){
        binding.b00.isEnabled = true
        binding.b01.isEnabled = true
        binding.b02.isEnabled = true
        binding.b10.isEnabled = true
        binding.b11.isEnabled = true
        binding.b12.isEnabled = true
        binding.b20.isEnabled = true
        binding.b21.isEnabled = true
        binding.b22.isEnabled = true
    }


}

