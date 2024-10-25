package com.dicoding.picodiploma.intermsubm1_2

import com.dicoding.picodiploma.intermsubm1_2.response.ListStoryItem
import com.google.gson.annotations.SerializedName

object DataDummy {

    fun generateDummyQuoteResponse(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (id in 0..100) {
            val quote = ListStoryItem(
                id.toString(),
                createdAt = "2022-02-22T22:22:22Z",
                description = "Description $id",
                lat = id.toDouble() * 10,
                lon = id.toDouble() * 10,
                name = "Name $id",
                photoUrl = "https://akcdn.detik.net.id/visual/2020/02/14/066810fd-b6a9-451d-a7ff-11876abf22e2_169.jpeg?w=650"
            )
            items.add(quote)
        }
        return items
    }
}
