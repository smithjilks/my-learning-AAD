package com.smith.viewmodelintro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.smith.viewmodelintro.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: SecondViewModel
    private lateinit var viewModelFactory: SecondViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_second ,container, false)
        viewModelFactory = SecondViewModelFactory(SecondFragmentArgs.fromBundle(requireArguments()).clicks)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SecondViewModel::class.java)

//        val args = SecondFragmentArgs.fromBundle(requireArguments())
//        binding.textView.text = args.clicks.toString()
        viewModel.clicks.observe(viewLifecycleOwner, Observer { clickNumber ->
            binding.textView.text = clickNumber.toString()

        })
        binding.seoncdFragmentButtonBack.setOnClickListener {
            findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
        }

        return binding.root
    }


}