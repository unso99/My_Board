package com.myboard.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myboard.databinding.ActivityInputBinding
import com.myboard.domain.model.Content
import com.myboard.presentation.viewmodel.InputViewModel
import com.myboard.util.PermissionUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInputBinding
    private val viewModel : InputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (intent.getSerializableExtra(ITEM) as? Content)?.let {
            viewModel.initItem(it)
        }
        observeViewModel()
        PermissionUtil.checkPermission(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    //done이벤트 감시
    private fun observeViewModel(){
        viewModel.doneEvent.observe(this){
            Toast.makeText(this,it.second,Toast.LENGTH_SHORT).show()
            if(it.first) finish()
        }
    }

    companion object{
        private  const val ITEM = "item"

        //item을 받아오는 함수
        fun start(context: Context, item : Content? = null){
            Intent(context,InputActivity::class.java).apply {
                putExtra(ITEM,item)
            }.run {
                context.startActivity(this)
            }
        }
    }
}