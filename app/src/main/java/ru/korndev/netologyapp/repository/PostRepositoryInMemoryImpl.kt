package ru.korndev.netologyapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.korndev.netologyapp.dto.Post
import kotlin.random.Random

class PostRepositoryInMemoryImpl : PostRepository {

    private var post = Post(
        id = 1,
        author = "Pavel Kornienkov",
        published = "32.01.2032 в 25:60",
        content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        likes = 136,
        shared = 269,
        view = 666,
        likedByMe = true,
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        Log.e("tap", "like")
        post = post.copy(
            likes = if (post.likedByMe) post.likes - 1 else post.likes + 1,
            likedByMe = !post.likedByMe
        )
        data.value = post
    }

    override fun share() {
       post = post.copy(
           shared = post.shared + 1
       )
        data.value = post
    }

    override fun viewed() {
        post = post.copy(
            view = post.view + Random.nextInt(0,100)
        )
        data.value = post
    }

//    override fun test() {
//
//    }
}