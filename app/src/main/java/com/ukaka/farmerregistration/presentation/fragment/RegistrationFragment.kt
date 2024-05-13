package com.ukaka.farmerregistration.presentation.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ukaka.farmerregistration.R
import com.ukaka.farmerregistration.databinding.FragmentRegistrationBinding
import com.ukaka.farmerregistration.presentation.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.registrationBtn.setOnClickListener {
            if (doValidation()) {
                viewModel.saveFarmerInfo(
                    name = binding.fullNameET.text.toString().trim(),
                    cropType = binding.cropTypeET.text.toString().trim()
                )
                Toast.makeText(requireContext(), "Registered successfully!", Toast.LENGTH_SHORT).show()
                resetInputFields()
                binding.fullNameET.requestFocus()
            }
        }

        binding.storage.setOnClickListener {
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToFarmersListFragment()
            findNavController().navigate(action)
        }

        binding.fullNameET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotBlank() == true) {
                    binding.fullNameLayout.error = null
                }
            }
        })

        binding.cropTypeET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotBlank() == true) {
                    binding.cropTypeLayout.error = null
                }
            }
        })
    }

    /**
     * This validation is done to ensure that the user (super admin) does not register a user with some missing information.
     */
    private fun doValidation(): Boolean {
        var passValidation = true
        binding.fullNameLayout.error = ""
        binding.cropTypeLayout.error = ""
        // verify that full name isn't null or empty
        if (binding.fullNameET.text.isNullOrEmpty()) {
            binding.fullNameLayout.error = "Please input Full Name"
            passValidation = false
        }

        // verify that crop type isn't null or empty
        if (binding.cropTypeET.text.isNullOrEmpty()) {
            binding.cropTypeLayout.error = "Please input Crop Type"
            passValidation = false
        }

        return passValidation
    }

    /**
     * Clear the text fields after the farmers have been registered
     */
    private fun resetInputFields() {
        binding.fullNameET.text?.clear()
        binding.cropTypeET.text?.clear()
    }

}