package com.task.githubrepo

import com.task.githubrepo.data.dtos.Item
import com.task.githubrepo.data.dtos.Repository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import retrofit2.Response


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
        coEvery { mockService.getTrendingRepos() } returns Response.success(
            200,
            mockk<Repository> { every { items } returns listOf(mockk(), mockk()) }
        )

        val actualResult = sut.fetchRepo() as NetworkResult.Success

        val expectedResult = listOf<Item>(mockk(), mockk())

        assertEquals(expectedResult.size, actualResult.data.items?.size)

        coVerify { mockService.getTrendingRepos() }
    }

    @Test
    fun test_fetch_repos_failed() = runTest {
        val errorMsg = "Request failed please try again"
        coEvery { mockService.getTrendingRepos() } returns Response.error(400, errorMsg.toResponseBody())
        val actual = sut.fetchRepo() as NetworkResult.Error
        assertEquals(errorMsg, actual.error.message)
        coVerify { mockService.getTrendingRepos() }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}