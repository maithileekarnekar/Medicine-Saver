package com.android_wavelength.medicinesaver.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android_wavelength.medicinesaver.R
import com.android_wavelength.medicinesaver.databinding.AddMedicineFragmentBinding
import com.android_wavelength.medicinesaver.models.Medicine

class AddMedicineFragment : Fragment() {

    private lateinit var binding: AddMedicineFragmentBinding

    var medicineDetails : Medicine? = null

    private var imageId = R.drawable.tablet

    interface OnMedicineAddedListener {
        fun onMedicineAdded(medicine: Medicine)
    }

    var onMedicineAddedListener : OnMedicineAddedListener?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.add_medicine_fragment, null)

        binding = AddMedicineFragmentBinding.bind(view)

        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().build())
        bindData()
        setupListeners()

        return view
    }

    private fun setupListeners() {

        binding.btnSaveMedicine.setOnClickListener{
            val medicine = Medicine(
                1,
                binding.edtMedicineName.text.toString(),
                binding.edtMedicineUses.text.toString(),
                binding.edtStorageCondition.text.toString(),
                binding.edtSideEffects.text.toString(),
                imageId
            )
            onMedicineAddedListener?.onMedicineAdded(medicine)
            //removeCurrentFragment()
        }
    }

    private fun removeCurrentFragment() {
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

    private fun bindData(){
        if(medicineDetails == null) return
        binding.imgMedicine.setImageResource(R.drawable.tablet)
        binding.edtMedicineUses.setText(medicineDetails!!.uses)
        binding.edtMedicineName.setText(medicineDetails!!.name)
        binding.edtStorageCondition.setText(medicineDetails!!.storage)
        binding.edtSideEffects.setText(medicineDetails!!.sideEffects)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            if (imageUri!=null) {
                imageId = R.drawable.tablet
            }
        }
    }
}
