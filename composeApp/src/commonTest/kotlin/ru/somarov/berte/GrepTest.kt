package ru.somarov.berte

import Greeting
import kotlin.test.Test
import kotlin.test.assertEquals

class GrepTest {
    companion object {
        val sampleData = listOf(
            "123 abc",
            "abc 123",
            "123 ABC",
            "ABC 123"
        )
    }

    @Test
    fun composeKk() {
        val results = mutableListOf<String>()
        Greeting().grep(sampleData, "[a-z]+") {
            results.add(it)
        }

        assertEquals(2, results.size)
    }
}
