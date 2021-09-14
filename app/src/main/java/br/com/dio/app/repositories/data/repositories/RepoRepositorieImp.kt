package br.com.dio.app.repositories.data.repositories

import br.com.dio.app.repositories.core.RemoteException
import br.com.dio.app.repositories.data.services.GitHubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositorieImp(private val service: GitHubService): RepoRepositories {
    override suspend fun listRepositories(user: String) = flow {
        try {
            val repoList = service.listRepositories(user)
            emit(repoList)
        }
        catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Não foi possível fazer a busca no moneto!")
        }
    }
}