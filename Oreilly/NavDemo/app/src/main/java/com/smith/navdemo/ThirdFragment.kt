package com.smith.navdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.smith.navdemo.databinding.FragmentSecondBinding
import com.smith.navdemo.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private lateinit var message: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toFirstFragment.setOnClickListener {
            val action =
                ThirdFragmentDirections.actionThirdFragmentToFirstFragment3()
            this.findNavController().navigate(action)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}