package ru.korndev.netologyapp.repository

import androidx.lifecycle.LiveData
import ru.korndev.netologyapp.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
    fun viewed()
//    fun test()
}