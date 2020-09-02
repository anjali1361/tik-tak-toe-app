package com.anjali.tic_tac_toe

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    lateinit var image: ImageView
 lateinit var winner:String
    var gameActive = true



    //Player reprenstation
     //0- x
     //1 - 0

    var activePlayer = 0
    val gameState  = arrayListOf<Int>(2,2,2,2,2,2,2,2,2)

    //State representation
      //0 - x
     //1 - o
     //2 - null

    var winPosition = arrayOf(intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8), intArrayOf(0,3,6),
        intArrayOf(1,4,7), intArrayOf(2,5,8), intArrayOf(0,4,8), intArrayOf(2,4,6))

    @SuppressLint("ResourceAsColor")
    fun PlayerTap(view: View) {

        val img = view as ImageView
        val tappedImage = img.getTag().toString().toInt()
//
//        if(!gameActive){
//            gameReset(view)
//        }


            if (gameState[tappedImage] == 2 || gameState.contains(2)  ) {
                gameState[tappedImage] = activePlayer
                img.translationY = -1000f

                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.crosse)
                        activePlayer = 1
                        val turn: TextView = findViewById(R.id.turn)
                        turn.setText("O's Turn -> tap to play")


                    } else {
                        img.setImageResource(R.drawable.o)
                        activePlayer = 0
                    val turn: TextView = findViewById(R.id.turn)
                    turn.setText("X's Turn -> tap to play")
                    }
                    img.animate().translationYBy(1000f).setDuration(300)


            }else{
                turn.setText("||No Won Play Again||")
                image.setImageResource(R.drawable.ic_nowon)
            }



        //check if any player has won
        for(arr in winPosition){

            if(gameState[arr[0]] == gameState[arr[1]] &&  gameState[arr[1]] == gameState[arr[2]] &&  gameState[arr[0]] != 2) {
                //somebody has won - find out who
                gameActive = false
                if (gameState[arr[0]] == 0) {
                    winner = "X has won!!"
                    val turn: TextView = findViewById(R.id.turn)
                    turn.setText(winner)
                    image.setImageResource(R.drawable.ic_won)
                    break
                } else {
                    winner = "O has won!!"
                    val turn: TextView = findViewById(R.id.turn)
                    turn.setText(winner)
                    image.setImageResource(R.drawable.ic_won)
                    break
                }


            }
            //update the status bar with winner announcement
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.reset,menu)
        return super.onCreateOptionsMenu(menu)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when( item.itemId) {

            R.id.reset ->{
                //to restart an activity
                finish()
                startActivity(intent)

            }
            R.id.share ->{
                val intent= Intent(Intent.ACTION_SEND)
                intent.type="text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT,"SHARE DEMO")
                val message ="https://play.google.com/store/apps/details?="+BuildConfig.APPLICATION_ID+"\n\n"
                intent.putExtra(Intent.EXTRA_TEXT,"Hey ,there is my first app check it out and respond me with some feedback "+ message)
                startActivity(Intent.createChooser(intent,"share using"))
            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)


    }

}


