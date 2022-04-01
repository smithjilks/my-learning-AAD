package com.smith.moviespaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smith.moviespaging.adapter.MoviesAdapter
import com.smith.moviespaging.adapter.MoviesListAdapter
import com.smith.moviespaging.app.App
import com.smith.moviespaging.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var recyclerView: RecyclerView

    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory(
            (application as App).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = binding.moviesRecyclerView
        val adapter = MoviesAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        /**
         * Running with paging source
         * Note: Uses the MoviesAdapter
         */
        lifecycleScope.launch {
            viewModel.getAllPaging.collectLatest { pagedList ->
                pagedList?.let {
                    adapter.submitData(pagedList)
                    Toast.makeText(applicationContext, adapter.snapshot().size.toString(), Toast.LENGTH_LONG).show()

                }

            }
        }

        /**
         * Running with flow
         * Note: Uses the MoviesListAdapter
         */

//        val adapter2 = MoviesListAdapter()
//        recyclerView.adapter = adapter2
//        viewModel.allMoviesFlow.observe(this) { movies ->
//            movies?.let {
//                Toast.makeText(this, movies[9].toString(), Toast.LENGTH_LONG).show()
//                adapter2.submitList(it)
//                recyclerView.adapter = adapter
//            }
//        }


        /**
         * Using distinctUtilChangedBy
         */
//        lifecycleScope.launch {
//            //Your adapter's loadStateFlow here
//            adapter.loadStateFlow.
//            distinctUntilChangedBy {
//                it.refresh
//            }.collect {
//                //you get all the data here
//                val list = adapter.snapshot()
//                //Toast.makeText(applicationContext, "${list[0]?: ""} ", Toast.LENGTH_LONG).show()
//
//            }
//        }




    }
}