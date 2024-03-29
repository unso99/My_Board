package com.myboard.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.myboard.databinding.ActivityMainBinding
import com.myboard.domain.model.Content
import com.myboard.presentation.ui.list.ListAdapter
import com.myboard.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy { ListAdapter(Handler()) }

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            view = this@MainActivity
            recyclerView.adapter = adapter
        }

        observeViewModel()
    }

    fun onClickAdd() {
        InputActivity.start(this)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.contentList
                .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                .collectLatest {
                    binding.apply {
                        progressBar.isVisible = false
                        emptyTextView.isVisible = it.isEmpty()
                        recyclerView.isVisible = it.isNotEmpty()
                    }
                    adapter.submitList(it)
                }
        }

        viewModel.doneEvent.observe(this) {
            if (it.first) {
                if(it.second == "하트 추가 완료") showLikeAnimation()

            }
        }
    }

    private fun showLikeAnimation() {
        binding.apply {
            heartAnimationView.playAnimation()
            heartAnimationView.animate()
                .scaleX(7f)
                .scaleY(7f)
                .alpha(0.3f)
                .withEndAction {
                    heartAnimationView.scaleX = 0f
                    heartAnimationView.scaleY = 0f
                    heartAnimationView.alpha = 1f
                }.start()
        }
    }

    inner class Handler {
        fun onClick(item: Content) {
            viewModel.plusViewCount(item)
            InputActivity.start(this@MainActivity, item)
        }

        fun onLongClick(item: Content): Boolean {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("정말 삭제하시겠습니까?")
                .setPositiveButton("네") { _, _ ->
                    viewModel.deleteItem(item)
                }
                .setNegativeButton("아니오") { _, _ ->

                }
                .show()
            return false
        }

        fun onLikeClick(item: Content) {
            viewModel.plusLikeCount(item)
        }

    }
}