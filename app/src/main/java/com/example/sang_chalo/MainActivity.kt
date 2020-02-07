package com.example.sang_chalo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val timer = object : Thread() {
            override fun run() {
                try {
                    //Display for 3 seconds
                    sleep(3000)
                } catch (e: InterruptedException) {

                    e.printStackTrace()
                } finally {
                    println("here")
                  try {
                      startActivity(Intent(this@MainActivity, Choose_Login::class.java))
                  }catch (e:Exception)
                  {
                      println(e.toString())
                  }


                    finish()
                }


            }
        }

    timer.start()




    }
}
