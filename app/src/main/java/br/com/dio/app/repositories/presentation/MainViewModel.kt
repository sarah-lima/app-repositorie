package br.com.dio.app.repositories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.app.repositories.data.model.Repo
import br.com.dio.app.repositories.domain.ListUseRepositoriesCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val listUseRepositoriesCase: ListUseRepositoriesCase) : ViewModel(){

    private val _repo = MutableLiveData<State>()
    val repos: LiveData<State> = _repo

    fun getRepoList(user:String){
        viewModelScope.launch {
            listUseRepositoriesCase(user)
                .onStart {
                    _repo.postValue(State.Loading)
                }
                .catch {
                    _repo.postValue(State.Error(it))
                }
                .collect {
                    _repo.postValue(State.Success(it))
                }
        }
    }
    sealed class State {
        object Loading : State()
        data class Success(val list: List<Repo>): State()
        data class Error(val error: Throwable): State()
    }
}