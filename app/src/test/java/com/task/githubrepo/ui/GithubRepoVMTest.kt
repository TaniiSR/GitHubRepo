package com.task.githubrepo.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.githubrepo.data.ITrendingRepo
import com.task.githubrepo.data.dtos.Item
import com.task.githubrepo.data.dtos.Repository
import com.task.githubrepo.data.remote.base.NetworkResult
import com.task.githubrepo.getOrAwaitValue
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GithubRepoVMTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: GithubRepoVM
    private lateinit var mockRepo: ITrendingRepo

    @Before
    fun setUp() {
        mockRepo = mockk()
        sut = GithubRepoVM(mockRepo)
    }

    @Test
    fun test_fetch_trending_repos_success() = runTest {
        coEvery { mockRepo.fetchTrendingRepos(false) } returns
                NetworkResult.Success(data = mockk<Repository> {
            coEvery { items } returns listOf(Item())
        })
        sut.fetchTrendingRepos(true)
        assertEquals(listOf<Item>(Item()), sut.trendingRepos.getOrAwaitValue())
        coVerify { mockRepo.fetchTrendingRepos(false) }
    }

    @Test
    fun test_fetch_trending_repos_failure() = runTest {
        val errorMsg = "Request failed please try again"
        coEvery { mockRepo.fetchTrendingRepos(false) } returns
                mockk<NetworkResult.Error>{
                    every { error } returns mockk{
                        every { message } returns errorMsg
                    }
                }
        sut.fetchTrendingRepos(true)
        assertEquals(listOf<Item>(), sut.trendingRepos.getOrAwaitValue())
        coVerify { mockRepo.fetchTrendingRepos(false) }
    }

    @Test
    fun test_fetch_trend_repos_from_cache() = runTest{
        coEvery { mockRepo.fetchTrendingRepos(true) } returns
                NetworkResult.Success(data = mockk<Repository> {
                    coEvery { items } returns listOf(Item())
                })
        sut.fetchTrendingRepos()
        assertEquals(listOf<Item>(Item()), sut.trendingRepos.getOrAwaitValue())
        coVerify { mockRepo.fetchTrendingRepos(true) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}