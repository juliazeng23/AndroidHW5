package com.example.hw5startercode

import android.app.Activity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException

/**
 * Retrieves the image URL corresponding to picture number `picNumber`
 *
 * @param picNumber A number from 0 to 5, inclusive
 */
fun getPicture(picNumber: Int, activity: Activity, callback: (url: String) -> Unit) {
    val resources = activity.applicationContext.resources
    val queryList: Array<String> = resources.getStringArray(R.array.image_queries)
    val query = queryList[picNumber]
    val queryUrl = resources.getString(R.string.query_url, query)

    val client = OkHttpClient()
    val request = Request.Builder()
        .url(queryUrl)
        .build()
    CoroutineScope(Dispatchers.Main).launch {
        withContext(Dispatchers.IO) {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected response code: $response")

                val moshi = Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
                val queryAdapter = moshi.adapter(QueryResult::class.java)
                val url = queryAdapter.fromJson(response.body?.string()!!)!!.hits[0].largeImageURL
                activity.runOnUiThread {
                    callback(url)
                }
            }
        }
    }
}