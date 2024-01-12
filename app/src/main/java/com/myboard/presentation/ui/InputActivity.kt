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
import com.myboard.R
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
            if(it.img == "") {
                binding.imageView.setImageResource(R.drawable.baseline_image_search_24)
            } else {
                val encodedByte = Base64.decode(it.img,Base64.DEFAULT)
                binding.imageView.setImageBitmap(BitmapFactory.decodeByteArray(encodedByte,0,encodedByte.size))
            }
            viewModel.initItem(it)
        } ?: binding.imageView.setImageResource(R.drawable.baseline_image_search_24)
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

    private fun setImage(uri: Uri) {
        //이미지를 어플에서 넣었을때 작동
        binding.imageView.setImageURI(uri)
        //서버에 데이터를 보내기 위한 base64로 인코딩
        viewModel.img.value = encoding(uri)
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

    fun onImageClick() {
        Log.e("imageclick","클릭됨")
        PermissionUtil.checkPermission(this, imageLoadLauncher)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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