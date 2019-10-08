package com.booleanull.corenetworkapi.data

import com.booleanull.core.data.News
import com.google.gson.annotations.Expose

data class NewsEntity(
    @Expose
    val source: SourceEntity,
    @Expose
    val author: String?,
    @Expose
    val title: String?,
    @Expose
    val description: String?,
    @Expose
    val url: String?,
    @Expose
    val urlToImage: String?,
    @Expose
    val publishedAt: String?
)

data class SourceEntity(
    @Expose
    val id: String?,
    @Expose
    val name: String?
)

data class ResponseEntity(
    @Expose
    val totalResults: Int,
    @Expose
    val articles: List<NewsEntity>
)

fun ResponseEntity.convertToNews() =
    this.articles.map {
        News(
            it.source.name,
            it.title,
            it.description,
            it.url,
            it.urlToImage,
            it.publishedAt
        )
    }