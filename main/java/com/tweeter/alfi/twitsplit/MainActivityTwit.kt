package com.tweeter.alfi.twitsplit

import android.R.layout.simple_list_item_1
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityTwit : MainActivityTwitI, AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val toSplit = SplitMessageTwit()

    /**
     * This function will return a list of string obtained from each substring (resulting from the splitting)
     * before last whitespace.
     */

    override fun readyToSumbit(postString: String): List<String> {
        val toSplitPost = toSplit.doSplit(postString, 50)
        val splitPostLastString = toSplitPost.lastIndex
        val messages= toSplitPost.map{
            if (toSplitPost.indexOf(it) != splitPostLastString) {
                val beforeLastSpace = it.substringBeforeLast(" ")

                beforeLastSpace
            } else {
                it
            }
        }

        return messages
    }

    /**
     * When the list of string contains error string, the toast below will appear.
     */

    fun errorToast() {
        val myToast = Toast.makeText(this, "Error! Your sentence contains a span \n" +
                "of non-whitespace characters longer than 50 characters!", Toast.LENGTH_SHORT)
        myToast.show()
    }

    /**
     * This function is used to show (posting) the splitted strings to a list view.
     */
    override fun tweetSubmit(view: View) {
        val postString = plain_text_input.text.toString()
        val goTweet = readyToSumbit(postString)
        val errorString = "ERROR!!Non-whitespace!"
        if (goTweet.contains(errorString)) {
            errorToast()
        } else {
            lv_messages.adapter = ArrayAdapter(this, simple_list_item_1, goTweet)
        }
    }

}

interface MainActivityTwitI{
    fun readyToSumbit(postString: String): List<String>
    fun tweetSubmit(view: View)
}
