package br.com.dio.app.repositories.domain

import br.com.dio.app.repositories.core.UseCase
import br.com.dio.app.repositories.data.model.Repo
import br.com.dio.app.repositories.data.repositories.RepoRepositories
import kotlinx.coroutines.flow.Flow

class ListUseRepositoriesCase(private val repository: RepoRepositories): UseCase<String, List<Repo>>() {
    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}