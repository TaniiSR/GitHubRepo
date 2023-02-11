package com.task.githubrepo

import com.task.githubrepo.data.dtos.Item
import com.task.githubrepo.data.dtos.Repository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class TrendingRepoTest {
    lateinit var sut: ITrendingRepo
    lateinit var mockService: RepoService

    @Before
    fun setUp() {
        mockService = mockk()
        sut = TrendingRepo(mockService)
    }

    @Test
    fun test_fetch_repos_success() = runTest {
        coEvery { mockService.getTrendingRepos() } returns mockk {
            every { items } returns listOf(mockk(), mockk())
        }
        val actualResult = sut.fetchRepo()
        assertEquals(2, actualResult?.size)
        coVerify { mockService.getTrendingRepos() }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}