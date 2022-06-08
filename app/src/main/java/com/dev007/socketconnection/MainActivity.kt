package com.dev007.socketconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        val tvTextView = findViewById<TextView>(R.id.tvTextView)


        //setting socket
        SocketHandler.setSocket() //singleton
        val soc = SocketHandler.getSocket() //getting socket client.
        soc.connect() //connecting with socket server.

        btn.setOnClickListener {
            soc.emit("counter") //"counter" - event name used in server.
        }

        soc.on("counter") { args ->
            if (args.isNotEmpty() && args[0] != null) {
                val counter = (args[0] as Int).toString()
                runOnUiThread { tvTextView.text = counter } //use runOnUiThread or Coroutines withCOntext as MAIN.
            }
        }
    }
}