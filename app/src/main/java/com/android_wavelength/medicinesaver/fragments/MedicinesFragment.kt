package com.android_wavelength.medicinesaver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android_wavelength.medicinesaver.R
import com.android_wavelength.medicinesaver.adapters.MedicinesAdapter
import com.android_wavelength.medicinesaver.database.DBUtil
import com.android_wavelength.medicinesaver.databinding.MedicinesFragmentBinding
import com.android_wavelength.medicinesaver.models.Medicine
import com.android_wavelength.medicinesaver.views.MedicineView

class MedicinesFragment : Fragment() {

    private lateinit var binding : MedicinesFragmentBinding
    private var medicinesList: ArrayList<Medicine> = ArrayList()
    private lateinit var medicinesAdapter: MedicinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.medicines_fragment, null)

        binding = MedicinesFragmentBinding.bind(view)

        initData()
        initViews(view)
        setupListeners()

        return view
    }

    private fun setupListeners() {
        medicinesAdapter.onMedicineClickListener =
            object : MedicinesAdapter.OnMedicineClickListener {
                override fun onMedicineClick(
                    medicine: Medicine,
                    medicineView: MedicineView,
                    position: Int,
                    adapter: MedicinesAdapter
                ) {

                    val medicineDetailsFragment = MedicineDetailsFragment()
                    addFragment(medicineDetailsFragment)
                    medicineDetailsFragment.medicineDetails = medicine

                    medicineDetailsFragment.onMedicineDetailsListeners =
                        object : MedicineDetailsFragment.OnMedicineDetailsListeners {
                            override fun onMedicineEdit(medicine: Medicine) {
                                val addMedicineFragment = AddMedicineFragment()
                                addMedicineFragment.medicineDetails = medicineDetailsFragment.medicineDetails
                                addFragment(addMedicineFragment)
                                addMedicineFragment.onMedicineAddedListener =
                                    object : AddMedicineFragment.OnMedicineAddedListener {
                                        override fun onMedicineAdded(medicine: Medicine) {

                                            medicineDetailsFragment.medicineDetails = medicine
                                            medicinesList[position] = medicine
                                            medicinesAdapter.notifyItemChanged(position)
                                            DBUtil.updateMedicine(requireContext(), medicinesList[position])
                                            removeFragment(addMedicineFragment)
                                            removeFragment(medicineDetailsFragment)
                                        }
                                    }

                            }
                            override fun onMedicineDelete(medicine: Medicine) {
                                medicinesList.remove(medicine)
                                DBUtil.deleteMedicine(requireContext(), position)
                                adapter.notifyItemRemoved(position)
                                removeFragment(medicineDetailsFragment)
                            }
                        }
                }
            }
        binding.btnAddMedicine.setOnClickListener{

            val addMedicineDetailsFragment = AddMedicineFragment()
            addFragment(addMedicineDetailsFragment)

            addMedicineDetailsFragment.onMedicineAddedListener =
                object : AddMedicineFragment.OnMedicineAddedListener {
                    override fun onMedicineAdded(medicine: Medicine) {

                        medicinesList.add(medicine)
                        DBUtil.addMedicine(requireContext(), medicine, medicinesList.size-1)

                        medicinesAdapter.notifyItemInserted(medicinesList.size-1)
                        removeFragment(addMedicineDetailsFragment)
                    }
                }
        }
    }

    private fun addFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, fragment, Fragment::class.java.name)
            .addToBackStack(Fragment::class.java.name)
            .commit()
    }

    private fun removeFragment(fragment: Fragment){
        parentFragmentManager.beginTransaction()
            .remove(fragment)
            .commit()
        parentFragmentManager.popBackStack()
    }

    private fun mt(text : String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

    private fun initData() {
        medicinesList.addAll(DBUtil.getMedicines(requireContext()))
    }

    private fun initViews(view: View) {
       binding.recyclerMedicines.layoutManager = LinearLayoutManager(
           requireContext(),
           LinearLayoutManager.VERTICAL,
           false
       )

        medicinesAdapter = MedicinesAdapter(medicinesList)
        binding.recyclerMedicines.adapter = medicinesAdapter
    }
}