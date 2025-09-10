package ru.korndev.netologyapp.activity

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import ru.korndev.netologyapp.R
import ru.korndev.netologyapp.databinding.ActivityMainBinding
import ru.korndev.netologyapp.utils.Converter
import ru.korndev.netologyapp.viewmodel.PostViewModel

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ServiceCast")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val likesTest: EditText = findViewById(R.id.input_like)
        val shareTest: EditText = findViewById(R.id.input_share)
        val viewTest: EditText = findViewById(R.id.input_view)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->

            with(binding) {
                userName.text = post.author
                userDesc.text = post.published
                textfield.text = post.content
                likeText.text = post.likes.toString()
                shareText.text = post.shared.toString()
                viewText.text = post.view.toString()
                likeButton.imageTintList =
                    if (post.likedByMe) ColorStateList.valueOf(Color.RED) else ColorStateList.valueOf(
                        Color.GRAY
                    )
                viewButton.imageTintList = ColorStateList.valueOf(Color.GRAY)
                shareButton.imageTintList = ColorStateList.valueOf(Color.GRAY)
            }
        }

        binding.likeButton.setOnClickListener {
            viewModel.like()
        }

        binding.shareButton.setOnClickListener {
            viewModel.share()
        }

        binding.fabNewpost.setOnClickListener {
            viewModel.view()
        }

//        binding.saveCalc.setOnClickListener {
//            val inputLikeTest = likesTest.text.toString()
//            val inputShareTest = shareTest.text.toString()
//            val inputViewTest = viewTest.text.toString()
//            viewModel.test()
//        }
        var i = 0
        binding.userIcon.setOnClickListener {
            i++
            if(i > 2) {
                binding.testGroup.visibility = View.VISIBLE
            }
        }
    }
}
