package com.myboard.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.myboard.databinding.ActivityMainBinding
import com.myboard.domain.model.Content
import com.myboard.presentation.ui.list.ListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { ListAdapter(Handler()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        binding.recyclerView.adapter = adapter
    }

    fun onClickAdd() {

    }

    inner class Handler {
        fun onClick(item: Content) {

        }

        fun onLongClick(item: Content): Boolean {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("정말 삭제하시겠습니까?")
                .setPositiveButton("네") { _, _ ->

                }
                .setNegativeButton("아니오") { _, _ ->

                }
                .show()
            return false
        }

    }
}