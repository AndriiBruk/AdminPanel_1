package com.example.adminpanel_1.api.catalogExport

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class CatalogExport(
    val response: Response,
    val status: String
)