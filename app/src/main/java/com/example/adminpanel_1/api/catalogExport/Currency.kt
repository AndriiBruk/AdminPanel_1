package com.example.adminpanel_1.api.catalogExport

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency(
    val id: Int,
    val value: String
) :Parcelable