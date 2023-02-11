package com.task.githubrepo.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.task.githubrepo.REPO_KEY
import com.task.githubrepo.SHARED_PREFERENCES
import com.task.githubrepo.data.dtos.Item
import com.task.githubrepo.data.dtos.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocalDataTest {
    private lateinit var sut : ILocalSource
    private lateinit var context : Context
    @Before
    fun setUp() {
        context = mockk<Context>()
        sut = LocalData(context)
    }

    @Test
    fun get_locally_repo_data() = runTest {
        val preferences = mockk<SharedPreferences>()
        val mockRepo = Gson().toJson(Repository( items = listOf( Item(), Item())))
        coEvery {context.getSharedPreferences(SHARED_PREFERENCES, 0)} returns preferences
        coEvery {preferences.getString(REPO_KEY, null)} returns mockRepo
        assertEquals(2 , sut.getLocallySaveRepo()?.items?.size)
        coVerify { preferences.getString(REPO_KEY, null) }
    }
}