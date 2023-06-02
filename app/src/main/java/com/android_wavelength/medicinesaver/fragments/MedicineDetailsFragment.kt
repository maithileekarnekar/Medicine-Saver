package com.android_wavelength.medicinesaver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android_wavelength.medicinesaver.R
import com.android_wavelength.medicinesaver.adapters.MedicinesAdapter
import com.android_wavelength.medicinesaver.databinding.MedicineDetailsFragmentBinding
import com.android_wavelength.medicinesaver.models.Medicine

class MedicineDetailsFragment : Fragment() {

    private lateinit var binding: MedicineDetailsFragmentBinding

    var medicineDetails : Medicine? = null

    interface OnMedicineDetailsListeners{
        fun onMedicineEdit(medicine: Medicine)
        fun onMedicineDelete(medicine: Medicine)
    }

    var onMedicineDetailsListeners: OnMedicineDetailsListeners? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.medicine_details_fragment, null)

        binding = MedicineDetailsFragmentBinding.bind(view)

        bindData()
        setupListeners(view)

        return view
    }

    private fun setupListeners(view: View) {

        view.setOnClickListener{

        }
        binding.imgEditMedicine.setOnClickListener{
            onMedicineDetailsListeners?.onMedicineEdit(medicineDetails!!)
        }
        binding.imgDeleteMedicine.setOnClickListener {
            onMedicineDetailsListeners?.onMedicineDelete(medicineDetails!!)

        }
    }

    private fun bindData(){

        if(medicineDetails == null)return
        binding.medicine = medicineDetails
        binding.imgMedicine.setImageResource(medicineDetails!!.imageId)
        }
    }