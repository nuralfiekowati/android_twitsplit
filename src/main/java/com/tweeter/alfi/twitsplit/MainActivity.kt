package com.tweeter.alfi.twitsplit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.ceil
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun String.splitString(size: Int): List<String> {
        val nSplits = ceil(length.toDouble() / size.toDouble())
        val nSplitsInt = nSplits.toInt()
        println("length: " + length)
        println("nSplits: " + nSplitsInt)
//        return (0 until nSplitsInt).map { substring(it * size, (it + 1) * size)}
          return (0 until nSplitsInt).map {substring(it * size, minOf(length, (it + 1) * size))}
    }

    fun tweetSubmit(view: View) {
        val postString = plain_text_input.text.toString()
        val splitPost = postString.splitString(50)
        postTextView.text = splitPost.toString()
    }

}
