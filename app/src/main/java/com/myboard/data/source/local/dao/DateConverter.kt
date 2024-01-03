package com.myboard.data.source.local.dao

import androidx.room.TypeConverter
import com.myboard.util.DateUtil
import java.util.Date

class DateConverter {
    //데이터받기
    @TypeConverter
    fun toDate(timeStamp: String?): Date? {
        return timeStamp?.let { DateUtil.dbDateFormat.parse(it) }
    }

    //데이터 저장
    @TypeConverter
    fun toTimeStamp(date: Date?): String? {
        return date?.let { DateUtil.dbDateFormat.format(date) }
    }
}