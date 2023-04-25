package com.example.data.remote.datasource

import com.example.data.remote.response.follower.FollowerResponse
import com.example.data.remote.response.repo.GitHubRepoResponse
import com.example.data.remote.response.user.UserResponse
import com.example.data.remote.service.GitHubApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GitHubDataSource @Inject constructor(
    private val service: GitHubApiService
) {

    suspend fun getFollowers(userName: String): Flow<List<FollowerResponse>> = flow {
        runCatching {
            service.getFollowers(userName)
        }.onSuccess { response ->
            emit(response)
        }.onFailure {
            emit(emptyList())
        }
    }

    suspend fun getGitHubRepoList(userName: String): Flow<List<GitHubRepoResponse>> = flow {
        runCatching {
            service.getGitHubRepo(userName)
        }.onSuccess { response ->
            emit(response)
        }.onFailure {
            emit(emptyList())
        }
    }
}
