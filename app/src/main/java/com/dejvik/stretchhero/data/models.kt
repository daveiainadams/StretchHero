package com.dejvik.stretchhero.data

data class StretchStep(
    val name: String,
    val duration: Int,
    val imageResIdName: String
)

data class Routine(
    val name: String,
    val steps: List<StretchStep>,
    val id: String
)
