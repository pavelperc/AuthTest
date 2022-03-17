package com.example.authtest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLoginWithGlobal.isEnabled = BuildConfig.FLAVOR == "ru"

        textViewFlavor.text = "flavor: " + BuildConfig.FLAVOR

        if (intent.hasExtra("token")) {
            textViewFlavor.text = "onCreate token: " + intent.getStringExtra("token")
        }

        buttonLoginWithGlobal.setOnClickListener {
            val intent = Intent("auth_action")
                .putExtra("extra", "hello")
                .setPackage("com.example.authtest.glob")

            if (intent.resolveActivity(packageManager) == null) {
                Toast.makeText(this, "App is not installed", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            startActivityForResult(intent, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data?.hasExtra("token") == true) {
            textViewFlavor.text = "token: " + data.getStringExtra("token")
        }
    }
}