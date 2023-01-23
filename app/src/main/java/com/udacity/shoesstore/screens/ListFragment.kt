package com.udacity.shoesstore.screens

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoesstore.R
import com.udacity.shoesstore.databinding.FragmentListBinding
import com.udacity.shoesstore.databinding.ItemShoeBinding
import timber.log.Timber


/**
 * ListFragment where we can show list
 */
class ListFragment : Fragment() {

    // here we use this a configuration to share data between fragment
    private val storeViewModel: StoreViewModel by activityViewModels()

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_list,
                container,
                false
        )

        binding.setLifecycleOwner(this)
        setHasOptionsMenu(true)

        val menuHost: MenuHost = requireActivity()

        // i used  addMenuProvider   instead of setHasOptionsMenu(true)
        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                // here i integrate menu with navigation ui
                return NavigationUI.onNavDestinationSelected(menuItem, requireView().findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        binding.floatingActionButton.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToDetailsFragment()

           findNavController(this).navigate(action)
        }




        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = TextView(requireActivity())
        textView.setLayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        storeViewModel.shoeList.observe(requireActivity(), Observer { shoeList ->
            if (shoeList.isNotEmpty()){

                shoeList.forEach { shoe->
                    Timber.d(shoe.name)
                    val v = View.inflate(binding.lineId.context, R.layout.item_shoe, null)
                    val binding1 = ItemShoeBinding.bind(v)
                    binding1.textId.text="name : ${shoe.name}  , company : ${shoe.company},  Size: ${shoe.size}"
                    binding.lineId.addView(binding1.root)

                }
                Timber.d(shoeList.size.toString())
            }

        })

    }
}
