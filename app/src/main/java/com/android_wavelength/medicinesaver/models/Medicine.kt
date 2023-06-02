package com.android_wavelength.medicinesaver.models

data class Medicine(
    val id : Int,
    var name : String,
    var uses : String,
    var storage : String,
    var sideEffects : String?,
    var imageId : Int
) : java.io.Serializable

