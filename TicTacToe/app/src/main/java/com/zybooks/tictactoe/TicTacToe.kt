package com.zybooks.tictactoe

import android.view.View
import android.widget.Button
import com.zybooks.tictactoe.databinding.ActivityMainBinding

class TicTacToe {

    var boardList = mutableListOf<Button>()
    var boardListString = mutableListOf<String>()
    private var firstTurn = 1
    private var currentTurn = 1

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

    fun saveStringState(){

        for(button in boardList)
        {
            boardListString.add(button.text.toString())

        }

    }

}