package br.com.dio.app.repositories.data.repositories

import br.com.dio.app.repositories.data.services.GitHubService
import kotlinx.coroutines.flow.flow

class RepoRepositorieImp(private val service: GitHubService): RepoRepositories {
    override suspend fun listRepositories(user: String) = flow {
        val repoList = service.listRepositories(user)
        emit(repoList)
    }
}