package com.udacity.shoesstore.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoesstore.R
import com.udacity.shoesstore.databinding.FragmentDetailsBinding
import com.udacity.shoesstore.models.Shoe

/**
 * DetailsFragment where we can add new shoe item by using storeViewModel
 */
class DetailsFragment : Fragment() {

    // here we use this a configuration to share data between fragment

    private val storeViewModel: StoreViewModel by activityViewModels()

    private lateinit var binding:FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_details,
                container,
                false
        )

        binding.setLifecycleOwner(this)
        // cancel button to popstack to List Fragment
        binding.cancelId.setOnClickListener {
            // Hide the keyboard.
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
            val action=DetailsFragmentDirections.actionDetailsFragmentToListFragment()
            findNavController().navigate(action)
        }

        binding.saveId.setOnClickListener {
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
            val name=binding.editTextTextName.text.toString().trim()
            val  company=binding.editTextTextCompany.text.toString().trim()
            val size=binding.editTextTextSize.text.toString().trim()?:"0"
            val description=binding.editTextTextDescription.text.toString().trim()

            storeViewModel.addShoe(Shoe(name=name, size = size.toDouble(),company=company,description=description))
            val action=DetailsFragmentDirections.actionDetailsFragmentToListFragment()
           findNavController().popBackStack()
        }


        return binding.root

    }


}
