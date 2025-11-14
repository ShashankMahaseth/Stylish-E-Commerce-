package com.example.stylishe_commerceapp.data.local.Conveter

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class StringListConverter {
    private val json =Json{
        ignoreUnknownKeys=true
        isLenient=true
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String{
        return json.encodeToString(value)

    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return try {
            json.decodeFromString(value)
        } catch (e: Exception){
            emptyList()
        }
    }
}