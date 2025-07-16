package ru.korndev.netologyapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.korndev.netologyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            viewText.text = "000"

            fabNewpost.setOnClickListener {
                if (viewText.text != "Хуй") {
                    viewText.text = "Хуй"
                } else {
                    viewText.text = "Вот, всё норм!"
                }
            }

            likeButton.setOnClickListener {
                shareText.text = "0"
                var i = 0
                while (i < 1_000) {
                    i++
                    shareText.text = i.toString()
                }
            }


        }
    }
}