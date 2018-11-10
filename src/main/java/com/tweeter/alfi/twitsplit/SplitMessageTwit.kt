package com.tweeter.alfi.twitsplit

import kotlin.math.ceil

class SplitMessageTwit : SplitMessageTwitI {

    /**
     * A function to split message with the size of n characters.
     */

    fun String.splitMessage(size: Int): List<String> {

        val nSplitsInt = ceil(length.toDouble() / size.toDouble()).toInt()
        println("The length of the string: " + length)
        println("The size of the string to split: " + size)
        println("The number of split for the string: " + nSplitsInt)

        val listSplitString  = (0 until nSplitsInt).map { val partIndicator = """${(it + 1).toString()}/$nSplitsInt """

            var partIndicatorLength = partIndicator.length
            val startOfEachSplit = (it * size) - (it * partIndicatorLength)
            val endOfEachSplit = ((it + 1) * size) - ((it + 1) * partIndicatorLength)
            val substringToSplit = substring(startOfEachSplit, minOf(length, endOfEachSplit))
            val errorMessage = "ERROR!!Non-whitespace!"

            if (substringToSplit.contains("\\s".toRegex())) {
                val eachOfString: String = partIndicator + substringToSplit
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

        return listSplitString
    }

    /**
     * Calling the function to split above.
     * The function below requires two arguments as the input: the string to split and the size of n characters to split.
     */

    override fun doSplit(postStr: String, size: Int): List<String> {
        val splitPost = postStr.splitMessage(size)
        return splitPost
    }

}

interface SplitMessageTwitI{
    fun doSplit(postStr: String, size: Int): List<String>
}