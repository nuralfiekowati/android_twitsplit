package com.tweeter.alfi.twitsplit

import android.R.layout.simple_list_item_1
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun errorToast() {
        val myToast = Toast.makeText(this, "Error! Your sentence contains a span \n" +
                "of non-whitespace characters longer than 50 characters!", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun String.splitMessage(size: Int): List<String> {

        val nSplitsInt = ceil(length.toDouble() / size.toDouble()).toInt()
        println("length: " + length)
        println("nSplitsInt: " + nSplitsInt)

        val listSplitString  = (0 until nSplitsInt).map { val partIndicator = """${(it + 1).toString()}/$nSplitsInt """

            var partIndicatorLength = partIndicator.length
            val startOfEachSplit = (it * size) - (it * partIndicatorLength)
            val endOfEachSplit = ((it + 1) * size) - ((it + 1) * partIndicatorLength)
            val substringToSplit = substring(startOfEachSplit, minOf(length, endOfEachSplit))
            val errorMessage = "ERROR!!Non-whitespace!"

            if (substringToSplit.contains("\\s".toRegex())) {
                println("hohoho")
                val eachOfString: String = """$partIndicator ${
                    substringToSplit
                }"""

                eachOfString
            }
            else {
                if (it + 1 != nSplitsInt) {
                    errorMessage
                } else {
                    val eachOfString: String = """$partIndicator ${
                    substringToSplit
                    }"""

                    eachOfString
                }
            }

        }

        println(listSplitString)
        return listSplitString
    }

    fun tweetSubmit(view: View) {
        val postString = plain_text_input.text.toString()
        val splitPost = postString.splitMessage(50)
        val splitPostLastString = splitPost.lastIndex
        val messages= splitPost.map{
            if (splitPost.indexOf(it) != splitPostLastString) {
                it.substringBeforeLast(" ")
            } else {
                it
            }
        }

        lv_messages.adapter = ArrayAdapter(this, simple_list_item_1, messages)
    }

}
