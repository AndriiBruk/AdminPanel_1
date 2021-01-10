package com.example.adminpanel_1.api.catalogExport

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Product(
//    val accessories: List<Accessory>,
//    val adult: String,
//    val alt_parent: List<AltParent>,
    @SerializedName("article") val article: String,
    //val article_for_display: String,
    // val brand: Brand,
    // val characteristics: Characteristics,
//    val color: Color,
//    val condition: Condition,
//    val countdown_description: CountdownDescription,
//    val countdown_end_time: String,
//    val creation_time: String,
//    val currency: Currency,
//    val discount: Int,
//    val display_in_showcase: Int,
    //val export_to_marketplace: List<ExportToMarketplace>,
//    val gallery_360: List<String>,
    val gallery_common: List<String>,
    val images: List<String>,
    //val gifts: List<Gift>,
//    val gtin: String,
//    val guarantee_length: String,
//    val guarantee_shop: GuaranteeShop,
//    val h1_title: H1Title,
//    val icons: List<Icon>,

    //val installments_payment: InstallmentsPayment,
//    val isExportPriceLevelsAllowed: Boolean,
//    val link: String,
    //val marketplace_description: MarketplaceDescription,
//    val minimal_order: String,
    val mod_title: ModTitle,
    val title: Title,
    val description: Description,
//    val mpn: String,
//    val multiplicity: String,
    //val parent: Parent,
//    val parent_article: String,
//    val popularity: Int,
    val presence: Presence,
    val price: Double,
    val price_old: Double,
    //val price_levels: List<PriceLevel>,
//    val quantity: Int,
//    val razmer: Razmer,
//    val rozetka_title: RozetkaTitle,
//    val seo_description: SeoDescription,
//    val seo_keywords: SeoKeywords,
//    val seo_title: SeoTitle,
//    val short_description: ShortDescription,
//    val shtrixKod: ShtrixKod,
//    val slug: String,
//    val special_delivery_terms: SpecialDeliveryTerms,
//    val volume: Volume,
//    val wholesale_price_3: String,
//    val xarakteristikaVSistemnomShablone: XarakteristikaVSistemnomShablone,
    @SerializedName("characteristics") val characteristics: CharacteristicsResponse,

    ) : Parcelable

@Parcelize
class CharacteristicsResponse(
    val characteristics: List<Characteristic>
) : Parcelable


interface Characteristic : Parcelable {
    val key: String
}

@Parcelize
data class CharacteristicNumber(
    override val key: String,
    val number: String
) : Characteristic, Parcelable

@Parcelize
data class CharacteristicSelectFromList(
    override val key: String,
    val id : Int,
    val value: CharacteristicValueResponse
) : Characteristic, Parcelable

@Parcelize
data class CharacteristicSelectFromListMultiple(
    override val key: String,
    val value: List<CharacteristicSelectFromList>
) : Characteristic, Parcelable

@Parcelize
data class CharacteristicValueResponse(
    override val key: String,
    val localizationValue: HashMap<String, String>
) : Characteristic, Parcelable