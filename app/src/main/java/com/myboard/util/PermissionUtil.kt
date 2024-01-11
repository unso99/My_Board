package com.myboard.util

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

object PermissionUtil {

    fun checkPermission(activity: Activity) {
        when {
            activity.shouldShowRequestPermissionRationale(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                showPermissionInfoDialog(activity)
            }

            else -> {
                requestReadExternalStorage(activity)
            }
        }
    }

    private fun showPermissionInfoDialog(activity: Activity) {
        AlertDialog.Builder(activity).apply {
            setMessage("이미지를 가져오기 위해서, 외부 저장소 읽기 권한이 필요합니다.")
            setNegativeButton("취소",null)
            setPositiveButton("동의"){ _,_ ->
                requestReadExternalStorage(activity)
            }
        }.show()
    }

    private fun requestReadExternalStorage(activity: Activity){
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_READ_EXTERNAL_STORAGE
        )
    }



    private const val REQUEST_READ_EXTERNAL_STORAGE = 100
}