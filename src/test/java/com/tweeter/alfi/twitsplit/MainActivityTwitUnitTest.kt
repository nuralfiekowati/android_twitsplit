package com.tweeter.alfi.twitsplit

import org.junit.Test

import org.junit.Assert.*
import org.junit.After
import org.junit.Before

class MainActivityTwitUnitTest {

    @Before
    fun beforeEachTestCall() {
        println("Before test method called")
    }

    /**
     * Here we check the split function doSplit (which is internally integrated with splitMessage())
     * by using the number of characters (size): maximum 50.
     * The result should still considering the last of whitespace exists in the substring resulting from each split.
     */

    @Test
    fun splitMsg() {
        var mainActTwit : MainActivityTwitI = MainActivityTwit()
        val postString = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val expectedSplitResult: List<String> = listOf(
                "1/2 I can't believe Tweeter now supports chunking ",
                "2/2 my messages, so I don't have to do it myself."
        )
        val actualSplitResult = mainActTwit.doSplit(postString, 50)
        assertEquals(expectedSplitResult, actualSplitResult)
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

    /**
     * Here we check the function readyToSumbit() with the input string contains so long sentence with non-whitespace.
     * The result should give a string indicating error.
     */

    @Test
    fun msgToTweetSubmit2() {
        var mainActTwit : MainActivityTwitI = MainActivityTwit()
        val postString = "Ican'tbelieveTweeternowsupportschunkingmymessages,soIdon'thaveto do it myself."
        val expectedAfterRemoveLastSpace: List<String> = listOf(
                "ERROR!!Non-whitespace!",
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
