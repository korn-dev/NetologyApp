package ru.korndev.netologyapp.viewmodel

import androidx.lifecycle.ViewModel
import ru.korndev.netologyapp.repository.PostRepository
import ru.korndev.netologyapp.repository.PostRepositoryInMemoryImpl

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
    fun view() = repository.viewed()
//    fun test() = repository.test()
}