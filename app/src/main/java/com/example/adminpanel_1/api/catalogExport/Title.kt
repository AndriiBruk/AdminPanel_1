package com.example.adminpanel_1.api.catalogExport

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Title(
    val ru: String,
    val ua: String
): Parcelable