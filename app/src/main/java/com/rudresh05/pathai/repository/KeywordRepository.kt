package com.rudresh05.pathai.repository

import android.content.Context
import org.json.JSONObject

class KeywordRepository(private val context: Context) {

    val csKeywords: List<String> by lazy { loadCSKeywords(context) }

    private fun loadCSKeywords(context: Context): List<String> {
        val jsonString = context.assets.open("keywords.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("cs_keywords")
        val list = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            list.add(jsonArray.getString(i))
        }
        return list
    }
}