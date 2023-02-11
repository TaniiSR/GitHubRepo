package com.task.githubrepo.data

import com.task.githubrepo.ITrendingRepo
import com.task.githubrepo.TrendingRepo
import com.task.githubrepo.data.dtos.Item
import com.task.githubrepo.data.dtos.Repository
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
    @Before
    fun setUp() {
        mockRemoteSource = mockk()
        sut = TrendingRepo(mockRemoteSource)
    }

    @Test
    fun test_fetch_trending_repos_success_remote() = runTest {
        coEvery { mockRemoteSource.fetchRepo() } returns NetworkResult.Success(data = mockk<Repository>{
                every { items } returns listOf(mockk(), mockk())
            })
        val actualResult = sut.fetchTrendingRepos() as NetworkResult.Success
        val expectedResult = listOf<Item>(mockk(), mockk())
        Assert.assertEquals(expectedResult.size, actualResult.data.items?.size)
        coVerify { mockRemoteSource.fetchRepo() }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}