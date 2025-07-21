package ru.korndev.netologyapp.activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import ru.korndev.netologyapp.R
import ru.korndev.netologyapp.databinding.ActivityMainBinding
import ru.korndev.netologyapp.dto.Post
import ru.korndev.netologyapp.utils.Converter
import kotlin.math.exp

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Pavel Kornienkov",
            content = "Cat is pet",
            likes = 136,
            published = "32.01.2032 в 25:60",
            shared = 269,
            view = 666,
            likedByMe = true
        )

        with(binding) {
            var share = 0
            val view = 666
            var likeEnable = false
            likeText.text = post.likes.toString()
            userDesc.text = post.published
            textfield.text = post.content
            shareText.text = "0"
            viewText.text = view.toString()
            textcalc.text = "Абра-кадабра!"
            val editText: EditText = findViewById(R.id.inputfield)
            viewButton.imageTintList = ColorStateList.valueOf(Color.GRAY)
            shareButton.imageTintList = ColorStateList.valueOf(Color.GRAY)

            fabNewpost.setOnClickListener {
                if (viewText.text != "Хуй") {
                    viewText.text = "Хуй"
                } else {
                    viewText.text = "Вот, всё норм!"
                }
            }

            likeButton.setOnClickListener {
                if (likeEnable) {
                    post.likes = post.likes - 1
                    likeText.text = post.likes.toString()
                    likeEnable = false
                    likeButton.imageTintList = ColorStateList.valueOf(Color.GRAY)
                } else {
                    post.likes = post.likes + 1
                    likeEnable = true
                    likeText.text = post.likes.toString()
                    likeButton.imageTintList = ColorStateList.valueOf(Color.RED)
                }

            }

            shareButton.setOnClickListener {
                share++
                share.toString().also { shareText.text = it }
                shareButton.imageTintList = ColorStateList.valueOf(
                    when (shareText.text.toString().toInt()) {
                        in 0..10 -> Color.BLUE
                        in 11..20 -> Color.RED
                        in 21..30 -> Color.GREEN
                        else -> Color.GRAY
                    }
                )
            }


            savecalc.setOnClickListener {
                val inputText = editText.text.toString()
                textcalc.text = Converter.Companion.converter(inputText.toInt())
                editText.text.clear()
                editText.clearFocus()

                textcalc.backgroundTintList = ColorStateList.valueOf(
                    when (inputText.toInt()) {
                        in 0..10 -> Color.BLUE
                        in 11..20 -> Color.RED
                        in 21..30 -> Color.GREEN
                        else -> Color.GRAY
                    }
                )
            }
        }
    }
}