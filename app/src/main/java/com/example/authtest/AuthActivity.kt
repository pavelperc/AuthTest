package com.example.authtest

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_main.textViewFlavor

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        textViewFlavor.text = "flavor: " + BuildConfig.FLAVOR + "\nextra " + intent.getStringExtra("extra")
        buttonBackToRu.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra("token", token()))
            finish()
        }
        buttonOpenRu.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
                .setComponent(
                    ComponentName(
                        "com.example.authtest.ru",
                        "com.example.authtest.MainActivity"
                    )
                )
                .putExtra("token", token())
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            startActivity(intent)
        }
    }

    private fun token() = ('a'..'z').shuffled().take(5).joinToString("")
}