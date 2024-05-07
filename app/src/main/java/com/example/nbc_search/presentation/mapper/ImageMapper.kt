package com.example.nbc_search.presentation.mapper

import android.content.Context
import android.util.Log
import com.google.gson.Gson

object ImageMapper {
    private val gson = Gson()

    private fun <T> fromJson(json: String, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }

    private fun <T> toJson(obj: T): String {
        return gson.toJson(obj)
    }

    fun <T> saveData(context: Context, key: String, obj: T, name: String) {
        val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val json = toJson(obj)
        Log.d("SaveData", "저장된 값 $json")
        with(pref.edit()) {
            putString(key, json)
            apply()
        }
    }

    fun <T> loadData(context: Context, key: String, clazz: Class<T>, name: String): T? {
        val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val json = pref.getString(key, null)
        Log.d("LoadData", "저장된 값 $json")
        return json?.let { fromJson(it, clazz) }
    }

    fun removeData(context: Context, key: String, name: String) {
        val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        with(pref.edit()) {
            remove(key)
            apply()
        }
    }
}