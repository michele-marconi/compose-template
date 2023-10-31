package com.spindox.composetemplate.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spindox.composetemplate.data.entity.Hop
import com.spindox.composetemplate.data.entity.Malt
import com.spindox.composetemplate.data.entity.MashTemp
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class RoomTypeConverters {

    @ToJson
    @TypeConverter
    fun listStringToJsonStr(list: List<String>): String {
        return Gson().toJson(list)
    }

    @FromJson
    @TypeConverter
    fun jsonStrToListString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @ToJson
    @TypeConverter
    fun listMashToJsonStr(list: List<MashTemp>): String {
        return Gson().toJson(list)
    }

    @FromJson
    @TypeConverter
    fun jsonMashToListString(value: String): List<MashTemp> {
        val listType = object : TypeToken<List<MashTemp>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @ToJson
    @TypeConverter
    fun listMaltToJsonStr(list: List<Malt>): String {
        return Gson().toJson(list)
    }

    @FromJson
    @TypeConverter
    fun jsonMaltToListString(value: String): List<Malt> {
        val listType = object : TypeToken<List<Malt>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @ToJson
    @TypeConverter
    fun listHopToJsonStr(list: List<Hop>): String {
        return Gson().toJson(list)
    }

    @FromJson
    @TypeConverter
    fun jsonHopToListString(value: String): List<Hop> {
        val listType = object : TypeToken<List<Hop>>() {}.type
        return Gson().fromJson(value, listType)
    }
}