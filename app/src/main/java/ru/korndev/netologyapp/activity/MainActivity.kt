package ru.korndev.netologyapp.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log.e
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.core.view.WindowCompat
import androidx.transition.Visibility
import kotlinx.coroutines.delay
import ru.korndev.netologyapp.R
import ru.korndev.netologyapp.databinding.ActivityMainBinding
import ru.korndev.netologyapp.dto.Post
import ru.korndev.netologyapp.utils.Converter


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var flash = false


    @SuppressLint("ServiceCast")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Pavel Kornienkov",
            content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            likes = 136,
            published = "32.01.2032 в 25:60",
            shared = 269,
            view = 666,
            likedByMe = true
        )

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
                if (post.likedByMe) {
                    post.likes = post.likes - 1
                    likeText.text = post.likes.toString()
                    post.likedByMe = false
                    likeButton.imageTintList = ColorStateList.valueOf(Color.GRAY)
                } else {
                    post.likes = post.likes + 1
                    post.likedByMe = true
                    likeText.text = post.likes.toString()
                    likeButton.imageTintList = ColorStateList.valueOf(Color.RED)
                }

            }

            shareButton.setOnClickListener {
                post.shared++
                post.shared.toString().also { shareText.text = it }
                shareButton.imageTintList = ColorStateList.valueOf(
                    when (shareText.text.toString().toInt()) {
                        in 0..10 -> Color.BLUE
                        in 11..20 -> Color.RED
                        in 21..30 -> Color.GREEN
                        else -> Color.GRAY
                    }
                )
            }

            val flashlight = getSystemService(Context.CAMERA_SERVICE) as CameraManager
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            var cameraId: String? = null
            try {
                val cameraIds = flashlight.cameraIdList
                for (id in cameraIds) {
                    val cameraCharacteristics = flashlight.getCameraCharacteristics(id)
                    val hasFlash =
                        cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
                    if (hasFlash == true) {
                        cameraId = id
                        break
                    }
                }
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }


            savecalc.setOnClickListener {
                try {
                    val inputText = editText.text.toString()
                    textcalc.text = Converter.Companion.converter(inputText.toInt())
                    editText.text.clear()
                    editText.clearFocus()

                    textcalc.backgroundTintList = ColorStateList.valueOf(
                        when (inputText.toInt()) {
                            in 0..10 -> Color.BLUE
                            in 11..20 -> Color.RED
                            in 21..30 -> Color.rgb(1, 111, 185)
                            else -> Color.TRANSPARENT
                        }
                    )
                } catch (e: Exception) {
                    textcalc.text = "Ты шо, охуел?!"
                    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
                    )
                    if (cameraId != null) {
                        flashlight.setTorchMode(cameraId, true)
                        flashoff.visibility = View.VISIBLE
                        savecalc.visibility = View.INVISIBLE

                    }
                } catch (e: CameraAccessException) {
                    e.printStackTrace()
                }
            }

            flashoff.setOnClickListener {
                if (cameraId != null) {
                    flashlight.setTorchMode(cameraId, false)
                    flashoff.visibility = View.INVISIBLE
                    savecalc.visibility = View.VISIBLE
                    VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
                    textcalc.text ="Ээээээ, Красаучик!!!"
                }
            }
        }
    }
}
