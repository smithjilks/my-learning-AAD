package com.smith.viewmodelintro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smith.viewmodelintro.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var viewModel: FirstFragmentViewModel
    private lateinit var binding: FragmentFirstBinding

    private var clicks = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)


        // set the value of the view model
        // This is the bad way. We keep getting new view models and they arent getting destroyed
        //viewModel = FirstFragmentViewModel()


        // This is the correct way using a view model provider if already created
        // Otherwise it will create a new one
        viewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)


        //Using data binding to bind dat directly to the views
        binding.firstFragmentViewModel = viewModel
        binding.lifecycleOwner = this



        // Not using data binding, we have to create observers

        /*
        viewModel.message.observe(viewLifecycleOwner, Observer { myMessage ->
            binding.firstFragemtTextviewMessage.text = myMessage
        })

        viewModel.clicks.observe(viewLifecycleOwner, Observer { myClicks ->
            binding.firstFragmentTextViewClicks.text = "$myClicks total clicks"
        })
         */


        //set click listeners
        binding.helloButton.setOnClickListener {
            viewModel.hello(binding.firstFragmentEditTextName.text.toString())
        }

        binding.goodbyeButton.setOnClickListener {
            viewModel.goodbye(binding.firstFragmentEditTextName.text.toString())
        }
        binding.endButton.setOnClickListener {  nextFragment() }


        // updateUI()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun nextFragment() {
        findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(viewModel.clicks.value!!))
    }

//    Old version no using viewmodel

    /*
    private fun hello() {

        val name = binding.firstFragmentEditTextName.text.toString()
        binding.firstFragemtTextviewMessage.text = "Hello $name"
        clicks ++
        binding.firstFragmentTextViewClicks.text = "$clicks total clicks"

    }

    private fun goodbye() {
        val name = binding.firstFragmentEditTextName.text.toString()
        binding.firstFragemtTextviewMessage.text = "Goodbye $name"
        clicks ++
        binding.firstFragmentTextViewClicks.text = "$clicks total clicks"

    }
   */


//    The old way without using LiveData
    /*
    private fun hello() {
        val name = binding.firstFragmentEditTextName.text.toString()
        viewModel.message = "Hello $name"
        viewModel.clicks++
        updateUI()
    }


    private fun goodbye() {
        val name = binding.firstFragmentEditTextName.text.toString()
        viewModel.message = "Goodbye $name"
        viewModel.clicks++
        updateUI()

    }

    private fun updateUI() {
        binding.firstFragemtTextviewMessage.text = viewModel.message
        binding.firstFragmentTextViewClicks.text = "${viewModel.clicks} total clicks"
    }
     */


}