package com.example.adminpanel_1.data

import com.example.adminpanel_1.api.catalogExport.Product
import com.example.adminpanel_1.api.catalogExport.Response
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ProductDeserializer : JsonDeserializer<Response>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Response {
        val products = mutableListOf<Product>()

        val jsonObject = json?.asJsonObject
        jsonObject?.asJsonArray?.forEach { element ->
            val article = element.asJsonObject.get("article").asString
            val description = element.asJsonObject.get("description").asJsonObject
            val mapDescr = mutableMapOf<String,String>()
            description.keySet().forEach { key ->
                mapDescr[key] = description.get(key).asString
            }
            val galleryCommon = element.asJsonObject.get("gallery_common").asJsonArray
            val images = element.asJsonObject.get("images").asJsonArray

            val modTitle = element.asJsonObject.get("mod_title").asJsonObject
            val mapModTitle = mutableMapOf<String,String>()
            modTitle.keySet().forEach { key ->
                mapModTitle[key] = modTitle.get(key).asString
            }

            val title = element.asJsonObject.get("title").asJsonObject
            val mapTitle = mutableMapOf<String,String>()
            title.keySet().forEach { key ->
                mapTitle[key] = title.get(key).asString
            }

            //val product = Product(article,description,galleryCommon,images,modTitle,title)

        }

        return Response(products = products)
    }


}