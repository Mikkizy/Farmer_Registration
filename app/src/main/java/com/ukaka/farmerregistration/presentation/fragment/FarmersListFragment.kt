package com.ukaka.farmerregistration.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ukaka.farmerregistration.R
import com.ukaka.farmerregistration.databinding.FragmentFarmersListBinding
import com.ukaka.farmerregistration.databinding.FragmentRegistrationBinding
import com.ukaka.farmerregistration.presentation.adapter.RegisteredFarmersAdapter
import com.ukaka.farmerregistration.presentation.viewmodel.FarmersListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FarmersListFragment : Fragment() {

    private var _binding: FragmentFarmersListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FarmersListViewModel by viewModels()

    private lateinit var registeredFarmersAdapter: RegisteredFarmersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFarmersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        initUI()
    }

    private fun initUI() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.registeredFarmersModel.collect { data ->
                registeredFarmersAdapter.submitList(data)
                if (data.isEmpty()) {
                    updateLayout(true)
                } else {
                    updateLayout(false)
                }
            }
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.closeBtn -> {
                    findNavController().popBackStack()
                }
            }
            true
        }
    }

    /**
     * Update the layout based on the data returned by the repository, if it is empty or not.
     */
    private fun updateLayout(emptyResult: Boolean) {
        with(binding) {
            if (emptyResult) {
                registeredFarmersRV.visibility = INVISIBLE
                noRegisteredFarmerTV.visibility = VISIBLE
                copyrightTv.visibility = INVISIBLE
            } else {
                noRegisteredFarmerTV.visibility = INVISIBLE
                registeredFarmersRV.visibility = VISIBLE
                copyrightTv.visibility = VISIBLE
            }
        }
    }

    private fun initRV() {
        registeredFarmersAdapter = RegisteredFarmersAdapter()
        binding.registeredFarmersRV.adapter = registeredFarmersAdapter
    }

}