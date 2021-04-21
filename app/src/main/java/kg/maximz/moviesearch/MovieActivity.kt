package kg.maximz.moviesearch

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MovieActivity : AppCompatActivity() {
    companion object {
        const val MOVIE_DATA = "MOVIE_DATA"
        const val RESULT_CONTENT = "RESULT_CONTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie)

        intent.getParcelableExtra<MovieData>(MOVIE_DATA)?.let {
            findViewById<ImageView>(R.id.imgPoster).setImageBitmap(it.img)
            findViewById<TextView>(R.id.textTitle).setText(it.title)
            findViewById<TextView>(R.id.textDesc).setText(it.desc)
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            val resultString = "Чекбокс ${if (findViewById<CheckBox>(R.id.cbLike).isChecked) " проставлен" else " не проставлен"}, комментарий: ${findViewById<EditText>(R.id.editText).text.toString()}"
            setResult(RESULT_OK, Intent().putExtra(RESULT_CONTENT, resultString))
            finish()
        }
    }
}