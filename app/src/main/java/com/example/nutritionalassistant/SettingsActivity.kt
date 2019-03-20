package com.example.nutritionalassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        signoutBtn.setOnClickListener {
            signOut()

            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }

}
