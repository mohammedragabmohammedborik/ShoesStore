package com.udacity.shoesstore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoesstore.R
import com.udacity.shoesstore.databinding.FragmentLoginBinding
import timber.log.Timber

/**
 * LoginFragment  where  can navigate to welcome screen
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_login,
                container,
                false
        )

        binding.setLifecycleOwner(this)
        binding.loginButton.setOnClickListener {
            // log data for input edit text
            Timber.d( binding.emailEditText.text.toString())
            Timber.d( binding.passwordEditText.text.toString())

            // action where we can navigate to welcome screen

            val action=LoginFragmentDirections.actionFragmentLoginToFragmentWelcome()
            findNavController(this).navigate(action)
        }
        binding.eitherButtonId.setOnClickListener {
            val action=LoginFragmentDirections.actionFragmentLoginToFragmentWelcome()
            findNavController(this).navigate(action)
        }

        return binding.root

    }


}
