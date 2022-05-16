package com.meetstudio.pharmacy.data

class PharmacyInfo(
    val features: List<Feature>,
    val type: String
):java.io.Serializable

data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
):java.io.Serializable

data class Geometry(
    val coordinates: List<Double>,
    val type: String
):java.io.Serializable

data class Properties(
    val address: String,
    val available: String,
    val county: String,
    val cunli: String,
    val custom_note: String,
    val id: String,
    val mask_adult: Int,
    val mask_child: Int,
    val name: String,
    val note: String,
    val phone: String,
    val service_periods: String,
    val town: String,
    val updated: String,
    val website: String
):java.io.Serializable