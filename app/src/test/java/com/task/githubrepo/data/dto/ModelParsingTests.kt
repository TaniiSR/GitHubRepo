package com.task.githubrepo.data.dto

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.task.githubrepo.data.dtos.Repository
import org.junit.Assert
import org.junit.Test
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class ModelParsingTests {
    @Test
    fun `test json response maps to model`() {
        val trendingRepositories = readJsonFile("MockResponse.json")
        Assert.assertEquals(30, trendingRepositories.items?.count())
        Assert.assertEquals("golang/go", trendingRepositories.items?.first()?.fullName)
        Assert.assertEquals("ruby/ruby", trendingRepositories.items?.last()?.fullName)
    }

    private fun readJsonFile(fileName: String): Repository {
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<Repository>() {}.type
        return gson.fromJson(readFileFromTestResources(fileName), itemType)
    }

    @Throws(IOException::class)
    fun readFileFromTestResources(fileName: String): String {
        var inputStream: InputStream? = null
        try {
            inputStream = getInputStreamFromResource(fileName)
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))

            var str: String? = reader.readLine()
            while (str != null) {
                builder.append(str)
                str = reader.readLine()
            }
            return builder.toString()
        } finally {
            inputStream?.close()
        }
    }

    private fun getInputStreamFromResource(fileName: String) =
        javaClass.classLoader?.getResourceAsStream(fileName)
}