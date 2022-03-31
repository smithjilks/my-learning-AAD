package com.smith.navdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.smith.navdemo.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toSecondFragment.setOnClickListener{
            // Navigate to the next destination
            //findNavController().navigate(R.id.action_firstFragment_to_secondFragment2)

            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment3("Message from the first fragment")
            this.findNavController().navigate(action)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}