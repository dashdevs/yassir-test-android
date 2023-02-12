package com.ddroznik.hilt_mvvm_compose_movie.data.datasource.local

import androidx.room.TypeConverter
import com.ddroznik.hilt_mvvm_compose_movie.domain.model.MovieItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypeConverterMovie {
    val gson: Gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): MovieItem? {
        if (data == null) return null
        val listType: Type = object : TypeToken<MovieItem?>() {}.type
        return gson.fromJson<MovieItem?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someobject: MovieItem?): String? {
        return gson.toJson(someobject)
    }
}