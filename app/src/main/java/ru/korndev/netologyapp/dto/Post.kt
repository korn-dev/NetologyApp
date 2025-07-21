package ru.korndev.netologyapp.dto

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likes: Int,
    val shared: Int,
    val view: Int,
    var likedByMe: Boolean = false
)
