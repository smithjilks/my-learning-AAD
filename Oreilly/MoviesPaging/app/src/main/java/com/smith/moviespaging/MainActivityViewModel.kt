package com.smith.moviespaging

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.smith.moviespaging.data.Repository
import com.smith.moviespaging.data.db.Movie
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    // CachedIn makes sure even with config changes the data survives (or remains the same)
    // Tying it to view model scope to take advantage of view model lifecycle
     val getAllPaging = repository.getAllPaging.cachedIn(viewModelScope)

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allMoviesFlow: LiveData<List<Movie>> = repository.allMoviesFlow.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(movie: Movie) = viewModelScope.launch {
        repository.insert(movie)
    }


}

class MainActivityViewModelFactory(private  val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainActivityViewModel(repository) as T
    }

}