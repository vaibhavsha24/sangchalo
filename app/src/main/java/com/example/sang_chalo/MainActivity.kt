package com.example.sang_chalo

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_ASK_PERMISSIONS = 1

    private val RUNTIME_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.ACCESS_NETWORK_STATE)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions()

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
                      if(FirebaseAuth.getInstance().currentUser!=null)
                      {
                          startActivity(Intent(this@MainActivity,SignIn::class.java))
                          finish()
                      }
                      else {
                          startActivity(Intent(this@MainActivity, Choose_Login::class.java))
                      }
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

    private fun requestPermissions() {
        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(this,
                RUNTIME_PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS)
        } else {

        }
    }
    private fun String.permissionGranted(ctx: Context) =
        ContextCompat.checkSelfPermission(ctx, this) == PackageManager.PERMISSION_GRANTED

    private fun hasPermissions(): Boolean {
        /**
         * Only when the app's target SDK is 23 or higher, it requests each dangerous permissions it
         * needs when the app is running.
         */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        return RUNTIME_PERMISSIONS.count { !it.permissionGranted(this) } == 0
    }


}
