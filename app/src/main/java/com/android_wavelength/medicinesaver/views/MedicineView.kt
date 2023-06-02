package com.android_wavelength.medicinesaver.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.android_wavelength.medicinesaver.R
import com.android_wavelength.medicinesaver.databinding.MedicineViewBinding
import com.android_wavelength.medicinesaver.models.Medicine

class MedicineView(
    context: Context,
    attributeSet: AttributeSet?
) :LinearLayout(context, attributeSet) {

    private var binding : MedicineViewBinding

    var medicine : Medicine? = null

        set(value) {
            field = value
            bindMedicineToView()
        }
    constructor(context: Context) : this(context, null)

    init {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.medicine_view, null)
        binding = MedicineViewBinding.bind(view)

        this.addView(view)
    }

    private fun bindMedicineToView() {
        if(medicine != null) {
            binding.imgMedicine.setImageResource(medicine!!.imageId)
            binding.txtMedicineName.text = medicine!!.name
            binding.txtMedicineUses.text = medicine!!.uses
            binding.txtMedicineSideEffects.text = medicine!!.sideEffects
            binding.txtMedicineStorageCondition.text = medicine!!.storage

        }
    }
}