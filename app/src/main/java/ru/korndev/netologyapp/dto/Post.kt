package ru.korndev.netologyapp.dto

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likes: Int,
    var shared: Int,
    val view: Int,
    var likedByMe: Boolean = false
)
