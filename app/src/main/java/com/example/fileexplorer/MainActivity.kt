package com.example.fileexplorer

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.loader.content.CursorLoader
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import java.io.File


class MainActivity : AppCompatActivity() {



    private val musicFragment by lazy { Music() }
    private val filesFragment by lazy { Files() }
    private val downloadsFragment by lazy { Downloads() }
    private val moreFragment by lazy { More() }
    private val videosFragment by lazy { Videos() }
    private val imagesFragment by lazy { Images() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("In Activity ONCREATE")
        setContentView(R.layout.activity_main)

        val btnMusic = findViewById<ImageView>(R.id.btnMusic)
        val btnMore = findViewById<ImageView>(R.id.btnMore)
        val btnDownloads = findViewById<ImageView>(R.id.btnDownloads)
        val btnFiles = findViewById<ImageView>(R.id.btnFiles)
        val btnImages = findViewById<ImageView>(R.id.btnGallery)

        val btnVideos = findViewById<ImageView>(R.id.btnVideo)
        btnMusic.setOnClickListener{
            buttonAnimation(findViewById<ImageView>(R.id.btnMusic))
            showFragment(musicFragment)
        }
        btnMore.setOnClickListener{
            buttonAnimation(findViewById<ImageView>(R.id.btnMore))
            showFragment(moreFragment)
        }
        btnDownloads.setOnClickListener{
            buttonAnimation(findViewById<ImageView>(R.id.btnDownloads))
            showFragment(downloadsFragment)
        }
        btnFiles.setOnClickListener{
            buttonAnimation(findViewById<ImageView>(R.id.btnFiles))
            showFragment(filesFragment)
        }
        btnImages.setOnClickListener{
            buttonAnimation(findViewById<ImageView>(R.id.btnGallery))
            showFragment(imagesFragment)
        }
//        val btnMoreVideo = findViewById<ImageView>(R.id.btnVideo)
        btnVideos.setOnClickListener{
            buttonAnimation(findViewById<ImageView>(R.id.btnVideo))
            showFragment(videosFragment)
        }
    }


    private fun showFragment(fragment:Fragment){

        if(supportFragmentManager.findFragmentById(R.id.fragmentContainerView) !== fragment){
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
//                    R.anim.slide_in,
                    R.anim.fade_in,
                    R.anim.fade_out,
//                    R.anim.slide_out
                )
                .replace(R.id.fragmentContainerView,fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()


        }
    }

    override fun onStart() {
        super.onStart()
        val layout = findViewById<LinearLayout>(R.id.linearLayoutBottom)
        layout.translationY = -resources.displayMetrics.heightPixels.toFloat() + (resources.displayMetrics.heightPixels/1.5).toFloat()
        layout.animate().apply {
            layout.rotationX = 0f
            duration = 500
            translationY(0f)
//            rotationX(720f)
            start()
        }
        println("In Activity ONSTART")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("In Activity ONDESTROY")
    }

    override fun onStop() {
        super.onStop()
        println("In Activity ONSTOP")
    }

    override fun onPause() {
        super.onPause()
        val settings = getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
        settings.edit().also {
            it.putString("key","Shared preference string value")
            it.apply()
        }
        println("In Activity ONPAUSE")
    }

    override fun onResume() {
        super.onResume()
        val settings = getSharedPreferences("MainActivity",Context.MODE_PRIVATE)
        println(settings.getString("key","SHARED PREFERENCE DEFAULT STRING"))
        println("In Activity ONRESUME")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("activity saved","Activity State Saved Successfully")
        println("In Activity onSaveInstanceState")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        println(savedInstanceState.getString("activity saved"))
        println("In Activity onRestoreInstanceState")
    }

    override fun onRestart() {
        super.onRestart()
        println("In Activity Restart")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this@MainActivity,"Potrait Screen",Toast.LENGTH_SHORT).show()
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(this@MainActivity,"Landscape Screen",Toast.LENGTH_SHORT).show()
        }
    }


//    private fun buttonAnimation(imView:ImageView){
//        imView.animate().apply {
//            imView.rotationY = 0f
//            imView.rotationX = 0f
//            duration=100
//            interpolator = AccelerateDecelerateInterpolator()
//            rotationY(360f)
//            withEndAction {
//                imView.animate().apply {
//                    rotationX(360f)
//                    start()
//                }
//            }
//            start()
//        }

        private fun buttonAnimation(imView:ImageView){
            imView.animate().apply {
                duration=200
                interpolator = AccelerateDecelerateInterpolator()
                scaleX(1.5f)
                scaleY(1.5f)
                withEndAction {
                    imView.animate().apply {
                        duration = 200
                        imView.scaleX =1f
                        imView.scaleY = 1f
                        start()
                    }
                }
                start()
            }
//        imView.animate().cancel()
    }
}
