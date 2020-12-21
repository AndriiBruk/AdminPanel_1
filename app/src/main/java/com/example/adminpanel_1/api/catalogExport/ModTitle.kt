package com.example.adminpanel_1.api.catalogExport

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModTitle(
    val ru: String,
    val ua: String
): Parcelable