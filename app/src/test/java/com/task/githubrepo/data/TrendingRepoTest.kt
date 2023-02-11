package com.task.githubrepo.data

import com.task.githubrepo.data.dtos.Item
import com.task.githubrepo.data.dtos.Repository
import com.task.githubrepo.data.local.ILocalSource
import com.task.githubrepo.data.remote.IRemoteTrendingRepo
import com.task.githubrepo.data.remote.base.NetworkResult
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TrendingRepoTest {
    private lateinit var sut : ITrendingRepo
    private lateinit var mockRemoteSource : IRemoteTrendingRepo
    private lateinit var mockLocalSource : ILocalSource
    @Before
    fun setUp() {
        mockRemoteSource = mockk()
        mockLocalSource = mockk()
        sut = TrendingRepo(mockRemoteSource, mockLocalSource)
    }

    @Test
    fun test_fetch_trending_repos_success_remote() = runTest {
        val mockResponse = NetworkResult.Success(data = mockk<Repository>{
            every { items } returns listOf(mockk(), mockk())
        })
        coEvery { mockRemoteSource.fetchRepo() } returns mockResponse
        coEvery { mockLocalSource.saveRepoLocally(mockResponse.data) } returns true
        val actualResult = sut.fetchTrendingRepos(false) as NetworkResult.Success
        val expectedResult = listOf<Item>(mockk(), mockk())
        Assert.assertEquals(expectedResult.size, actualResult.data.items?.size)
        coVerify { mockRemoteSource.fetchRepo() }
    }
    @Test
    fun test_fetch_trending_repos_locally() = runTest {
        coEvery { mockLocalSource.getLocallySaveRepo()} returns mockk<Repository>{
            every { items } returns listOf(mockk(), mockk())
        }
        val actualResult = sut.fetchTrendingRepos(isFromCache = true) as NetworkResult.Success
        val expectedResult = listOf<Item>(mockk(), mockk())
        Assert.assertEquals(expectedResult.size, actualResult.data.items?.size)
        coVerify { mockLocalSource.getLocallySaveRepo() }
    }

    @Test
    fun test_repository_fetch_repos_failed() = runTest {
        val errorMsg = "Request failed please try again"
        coEvery { mockRemoteSource.fetchRepo()} returns mockk<NetworkResult.Error>{
            every { error } returns mockk{
                every { message } returns errorMsg
            }
        }
        val actual = sut.fetchTrendingRepos(isFromCache = false) as NetworkResult.Error
        Assert.assertEquals(errorMsg, actual.error.message)
        coVerify { mockRemoteSource.fetchRepo() }
    }
    @After
    fun tearDown() {
        clearAllMocks()
    }
}