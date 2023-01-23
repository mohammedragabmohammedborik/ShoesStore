package com.udacity.shoesstore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoesstore.R
import com.udacity.shoesstore.databinding.FragmentInstructionsBinding

/**
 * InstructionFragment  where we can navigate list screen
 */
class InstructionFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_instructions,
                container,
                false
        )

        binding.setLifecycleOwner(this)
        // this a click listener to navigate to list screen
        binding.buttonGoList.setOnClickListener {
            val action=InstructionFragmentDirections.actionInstructionFragmentToListFragment()
            findNavController(this).navigate(action)
        }


        return binding.root

    }


}
