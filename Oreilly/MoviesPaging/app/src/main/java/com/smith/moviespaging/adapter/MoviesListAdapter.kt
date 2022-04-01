package com.smith.moviespaging.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smith.moviespaging.data.db.Movie
import com.smith.moviespaging.databinding.MovieItemBinding

class MoviesListAdapter : ListAdapter<Movie, MoviesListAdapter.MovieViewHolder> (MovieComparator()){



    class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.titleTextView.text = movie.title
            binding.genreTextView.text = movie.majorGenre
            binding.directorTextView.text = movie.director
            Log.d("Movie Item From Flow Adapter", movie.title.toString())
        }

        companion object {
            fun create(parent: ViewGroup): MovieViewHolder {
                val binding = MovieItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
                return MovieViewHolder(binding)

            }
        }
    }


    class MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MoviesListAdapter.MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        Log.d("Movie Item", movie.toString())
        movie?.let {
            holder.bind(it)
        }
    }
}