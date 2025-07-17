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
import ru.korndev.netologyapp.utils.Converter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            var likes = 10
            var share = 0
            val view = 666
            var likeEnable = false
            likeText.text = likes.toString()
            shareText.text = "Шары"
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
                    likes = likes - 1
                    likeText.text = likes.toString()
                    likeEnable = false
                    likeButton.imageTintList = ColorStateList.valueOf(Color.GRAY)
                } else {
                    likes = likes + 1
                    likeEnable = true
                    likeText.text = likes.toString()
                    likeButton.imageTintList = ColorStateList.valueOf(Color.RED)
                }

            }

            shareButton.setOnClickListener {
                share++
                shareText.text = share.toString()
            }


            savecalc.setOnClickListener {
                val inputText = editText.text.toString()
                textcalc.text = Converter.Companion.converter(inputText.toInt())
            }
        }
    }
}