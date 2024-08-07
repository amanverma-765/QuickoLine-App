package org.quickoline.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeesDto(
    @SerialName("all")
    val all: Int?,
    @SerialName("ews")
    val ews: Int?,
    @SerialName("fem")
    val fem: Int?,
    @SerialName("gen")
    val gen: Int?,
    @SerialName("obc")
    val obc: Int?,
    @SerialName("ph")
    val ph: Int?,
    @SerialName("sc")
    val sc: Int?,
    @SerialName("st")
    val st: Int?
)