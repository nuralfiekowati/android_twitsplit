package com.tweeter.alfi.twitsplit

import org.junit.Test

import org.junit.Assert.*
import org.junit.After
import org.junit.Before

class TwitUnitTest {

    @Before
    fun beforeEachTestCall() {
        println("Before test method called")
    }

    /**
     * Here we check the split function doSplit (which is internally integrated with splitMessage()).
     * by using the number of characters (size): maximum 50.
     * The result should still considering the last of whitespace exists in the substring resulting from each split.
     */

    @Test
    fun splitMsg() {
        var splitMsgTwit : SplitMessageTwitI = SplitMessageTwit()
        val postString = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val expectedSplitResult: List<String> = listOf(
                "1/2 I can't believe Tweeter now supports chunking ",
                "2/2 my messages, so I don't have to do it myself."
        )
        val actualSplitResult = splitMsgTwit.doSplit(postString, 50)
        assertEquals(expectedSplitResult, actualSplitResult)
    }

    /**
     * Here we check the split function doSplit (which is internally integrated with splitMessage()).
     * by using the number of characters (size): 30.
     * It will split the string in the 30th of each substring.
     * The result should still considering the last of whitespace exists in the substring resulting from each split.
     */

    @Test
    fun splitMsg2() {
        var splitMsgTwit : SplitMessageTwitI = SplitMessageTwit()
        val postString = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val expectedSplitResult: List<String> = listOf(
                "1/4 I can't believe Tweeter no",
                "2/4 w supports chunking my mes",
                "3/4 sages, so I don't have to ",
                "4/4 do it myself."
        )
        val actualSplitResult = splitMsgTwit.doSplit(postString, 30)
        assertEquals(expectedSplitResult, actualSplitResult)
    }

    /**
     * Here we check the split function doSplit (which is internally integrated with splitMessage()).
     * by using the number of characters (size): 50.
     * It will return a list of string with at least one of them is an Error string.
     */

    @Test
    fun splitMsg3() {
        var splitMsgTwit : SplitMessageTwitI = SplitMessageTwit()
        val postString = "Ican'tbelieveTweeternowsupportschunkingmymessages,soIdon'thavetodoitmyself."
        val expectedResult: List<String> = listOf(
                "ERROR!!Non-whitespace!",
                "2/2  ges,soIdon'thavetodoitmyself."
        )
        val actualResult = splitMsgTwit.doSplit(postString, 50)
        assertEquals(expectedResult, actualResult)
    }

    /**
     * Here we check split function readyToSumbit().
     * This function will split the string by using the default of size to split (50).
     * The result should removing the last of whitespace that should not exist in the substring resulting from each split.
     */

    @Test
    fun msgToTweetSubmit() {
        var mainActTwit : MainActivityTwitI = MainActivityTwit()
        val postString = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val expectedAfterRemoveLastSpace: List<String> = listOf(
                "1/2 I can't believe Tweeter now supports chunking",
                "2/2 my messages, so I don't have to do it myself."
        )
        val actualAfterRemoveLastSpace = mainActTwit.readyToSumbit(postString)
        assertEquals(expectedAfterRemoveLastSpace, actualAfterRemoveLastSpace)
    }

    @After
    fun afterEachTestCall() {
        println("After test method called")
    }

}
