package com.myboard.util

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.myboard.R
import java.util.Date

@BindingAdapter("date")
fun TextView.setDate(date: Date?){
    text = DateUtil.convertPrintDateString(date)
}

@BindingAdapter("img")
fun ImageView.setImage(img : String?){
    img?.let {
        if(img!= ""){
            val encodedByte = Base64.decode(img, Base64.DEFAULT)
            setImageBitmap(BitmapFactory.decodeByteArray(encodedByte,0,encodedByte.size))
        }else{
            setImageResource(R.drawable.baseline_image_search_24)
        }
    }

}
