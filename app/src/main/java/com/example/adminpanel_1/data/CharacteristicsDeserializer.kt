package com.example.adminpanel_1.data

import com.example.adminpanel_1.api.catalogExport.*
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class CharacteristicsDeserializer : JsonDeserializer<CharacteristicsResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CharacteristicsResponse {

        val characteristics = mutableListOf<Characteristic>()

        val jsonObject = json?.asJsonObject

        jsonObject?.keySet()?.forEach { key ->
            val characteristicItem = jsonObject.get(key)

            try {
                if (characteristicItem.isJsonArray) {
                    val list = mutableListOf<CharacteristicSelectFromList>()
                    characteristicItem.asJsonArray.forEach { element ->
                        val listItem =
                            parseCharacteristicSelectFromList(element, key)
                        list.add(listItem)
                    }
                    characteristics.add(
                        CharacteristicSelectFromListMultiple(
                            key = key,
                            value = list
                        )
                    )

                }
                else if (characteristicItem.isJsonPrimitive){
                    val value = characteristicItem.asString
                    characteristics.add(CharacteristicNumber(key = key, number = value))
                } else if (characteristicItem.isJsonObject && characteristicItem.asJsonObject.get("id") ==null) {
                    val characteristicString = parseCharacteristicValueResponse(characteristicItem.asJsonObject, key)
                    characteristics.add(characteristicString)
                } else if (characteristicItem.isJsonObject && characteristicItem.asJsonObject.get("id") !=null) {
                    val valueLocalizationObject = characteristicItem.asJsonObject.get("value").asJsonObject
                    val characteristicString = parseCharacteristicValueResponse(valueLocalizationObject, key)
                    characteristics.add(characteristicString)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        return CharacteristicsResponse(characteristics = characteristics)
    }

    private fun parseCharacteristicSelectFromList(
        element: JsonElement,
        key: String
    ): CharacteristicSelectFromList {
        val id = element.asJsonObject.get("id").asInt
        val valueLocalizationObject = element.asJsonObject.get("value").asJsonObject
        val value = parseCharacteristicValueResponse(valueLocalizationObject, key)


        val listItem =
            CharacteristicSelectFromList(key = key, id = id, value = value)
        return listItem
    }

    private fun parseCharacteristicValueResponse(
        valueLocalizationObject: JsonObject,
        key: String
    ): CharacteristicValueResponse {
        val map = mutableMapOf<String, String>()
        valueLocalizationObject.keySet().forEach { keyItem ->
            map[keyItem] = valueLocalizationObject.get(keyItem).asString

        }
        val value = CharacteristicValueResponse(
            key = key,
            localizationValue = map as HashMap<String, String>
        )
        return value
    }

}

