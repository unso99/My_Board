import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.myboard.util.DateUtil
import java.util.Date

@BindingAdapter("date")
fun TextView.setDate(date: Date?){
    text = DateUtil.convertPrintDateString(date)
}
