package com.udacity.shoesstore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoesstore.R
import com.udacity.shoesstore.databinding.FragmentWelcomeBinding

/**
 * WelcomeFragment where we can see welcome text and navigate
 * to instruction screen
 */
class WelcomeFragment : Fragment() {

    //private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_welcome,
                container,
                false
        )

        binding.setLifecycleOwner(this)

        // this a click listener to navigate to instruction screen
         binding.next.setOnClickListener {
             val action=WelcomeFragmentDirections.actionFragmentWelcomeToInstructionFragment()
             findNavController(this).navigate(action)
         }


        return binding.root

    }


}
