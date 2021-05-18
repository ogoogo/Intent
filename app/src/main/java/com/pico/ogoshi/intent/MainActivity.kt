package com.pico.ogoshi.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val readRepuestCode : Int = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentButton.setOnClickListener {
            val toSecondActivity :Intent = Intent(this, SecondActivity::class.java)
            startActivity(toSecondActivity)
        }

        playStoreButton.setOnClickListener {
            val playStoreIntent = Intent(Intent.ACTION_VIEW)
            playStoreIntent.data= Uri.parse("https://play.google.com/store/apps")
            playStoreIntent.setPackage("com.android.vending")
            startActivity(playStoreIntent)
        }

        mapButton.setOnClickListener {
            val mapIntent = Intent(Intent.ACTION_VIEW)
            mapIntent.data=Uri.parse("geo:35.6473.139.7360")
            if(mapIntent.resolveActivity(packageManager)!=null){
                startActivity(mapIntent)
            }
        }

        browserButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("https://life-is-tech.com/")
            if(browserIntent.resolveActivity(packageManager)!=null){
                startActivity(browserIntent)
            }
        }

        gallerlyButton.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE)
            galleryIntent.type="image/*"
            startActivityForResult(galleryIntent,readRepuestCode)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if(requestCode == readRepuestCode && resultCode == Activity.RESULT_OK){
            resultData?.data.also{uri ->
                imageView.setImageURI(uri)
            }
        }
    }
}