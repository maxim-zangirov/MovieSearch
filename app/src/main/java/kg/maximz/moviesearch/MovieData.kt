package kg.maximz.moviesearch

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieData(val title: String = "", val desc: String = "", val img: Bitmap): Parcelable