package com.smith.navdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.smith.navdemo.databinding.FragmentFirstBinding
import com.smith.navdemo.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val args: SecondFragmentArgs by navArgs()

    private lateinit var message: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val message = args.greetingsFromFirst
        binding.messageTextView.text = message
        binding.toThirdFragment.setOnClickListener{
            // Navigate to the next destination
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}