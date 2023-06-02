package com.android_wavelength.medicinesaver.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android_wavelength.medicinesaver.R
import com.android_wavelength.medicinesaver.fragments.MedicineDetailsFragment
import com.android_wavelength.medicinesaver.models.Medicine
import com.android_wavelength.medicinesaver.views.MedicineView

class MedicinesAdapter (
    val medicinesList : ArrayList<Medicine>
    ) : RecyclerView.Adapter<MedicinesAdapter.MedicineViewHolder>() {

    interface OnMedicineClickListener {
        fun onMedicineClick(medicine: Medicine, medicineView: MedicineView, position: Int, adapter: MedicinesAdapter)
    }

    var onMedicineClickListener : OnMedicineClickListener? = null

    inner class MedicineViewHolder(var medicineView: MedicineView) : RecyclerView.ViewHolder(medicineView) {
        init {
            medicineView.setOnClickListener{
                if (onMedicineClickListener == null) {
                    return@setOnClickListener
                }
                onMedicineClickListener!!.onMedicineClick(
                    medicinesList[adapterPosition],
                    medicineView,
                    adapterPosition,
                    this@MedicinesAdapter,
                )
            }
        }
    }

    override fun getItemCount() = medicinesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        return MedicineViewHolder(
            MedicineView(parent.context)
        )
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.medicineView.medicine = medicinesList[position]
    }
}