package com.myboard.presentation.ui

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myboard.databinding.ActivityInputBinding
import com.myboard.domain.model.Content
import com.myboard.presentation.viewmodel.InputViewModel
import com.myboard.util.PermissionUtil
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.InputStream

@AndroidEntryPoint
class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private val viewModel: InputViewModel by viewModels()
    private val imageLoadLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                Log.e("imageUrl", uri.toString())
                Log.e("base64", encoding(uri))
                setImage(uri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.view = this
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (intent.getSerializableExtra(ITEM) as? Content)?.let {
            viewModel.initItem(it)
        }
        observeViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    //done이벤트 감시
    private fun observeViewModel() {
        viewModel.doneEvent.observe(this) {
            Toast.makeText(this, it.second, Toast.LENGTH_SHORT).show()
            if (it.first) finish()
        }
    }

    private fun encoding(uri: Uri?): String {
        // Base64 인코딩부분
        val ins: InputStream? = uri?.let {
            applicationContext.contentResolver.openInputStream(
                it
            )
        }
        val img: Bitmap = BitmapFactory.decodeStream(ins)
        ins?.close()
        val resized = Bitmap.createScaledBitmap(img, 256, 256, true)
        val byteArrayOutputStream = ByteArrayOutputStream()
        resized.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        val outStream = ByteArrayOutputStream()
        val res: Resources = resources
        return Base64.encodeToString(byteArray, NO_WRAP)
    }

    private fun setImage(uri: Uri) {
        binding.imageView.setImageURI(uri)
    }

    fun onImageClick() {
        PermissionUtil.checkPermission(this, imageLoadLauncher)
    }

    companion object {
        private const val ITEM = "item"

        //item을 받아오는 함수
        fun start(context: Context, item: Content? = null) {
            Intent(context, InputActivity::class.java).apply {
                putExtra(ITEM, item)
            }.run {
                context.startActivity(this)
            }
        }
    }
}