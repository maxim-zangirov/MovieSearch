package kg.maximz.moviesearch

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.drawToBitmap

class MainActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_1 = "COLOR_1"
        private const val COLOR_2 = "COLOR_2"
        private const val COLOR_3 = "COLOR_3"
        private const val RESULT_CODE_LIKED = 579
    }

    private val btnMovie1Details by lazy {
        findViewById<View>(R.id.btnMovie1Details)
    }

    private val btnMovie2Details by lazy {
        findViewById<View>(R.id.btnMovie2Details)
    }

    private val btnMovie3Details by lazy {
        findViewById<View>(R.id.btnMovie3Details)
    }

    private val textMovie1Title by lazy {
        findViewById<TextView>(R.id.textMovie1Title)
    }

    private val textMovie2Title by lazy {
        findViewById<TextView>(R.id.textMovie2Title)
    }

    private val textMovie3Title by lazy {
        findViewById<TextView>(R.id.textMovie3Title)
    }

    private val textMovie1Desc by lazy {
        findViewById<TextView>(R.id.textMovie1Desc)
    }

    private  val textMovie2Desc by lazy {
        findViewById<TextView>(R.id.textMovie2Desc)
    }

    private val textMovie3Desc by lazy {
        findViewById<TextView>(R.id.textMovie3Desc)
    }

    private val imgMovie1 by lazy {
        findViewById<ImageView>(R.id.imgMovie1)
    }

    private val imgMovie2 by lazy {
        findViewById<ImageView>(R.id.imgMovie2)
    }

    private val imgMovie3 by lazy {
        findViewById<ImageView>(R.id.imgMovie3)
    }

    private val btnShare by lazy {
        findViewById<Button>(R.id.btnShare)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            textMovie1Title.setTextColor(it.getInt(COLOR_1))
            textMovie2Title.setTextColor(it.getInt(COLOR_2))
            textMovie3Title.setTextColor(it.getInt(COLOR_3))
        }

        btnMovie1Details.setOnClickListener {
            showDetailsActivity(textMovie1Title, textMovie1Desc, imgMovie1)
        }

        btnMovie2Details.setOnClickListener {
            showDetailsActivity(textMovie2Title, textMovie2Desc, imgMovie2)
        }

        btnMovie3Details.setOnClickListener {
            showDetailsActivity(textMovie3Title, textMovie3Desc, imgMovie3)
        }

        btnShare.setOnClickListener {
            val shareMessage = resources.getString(R.string.txt_share_message)
            val shareTitle = resources.getString(R.string.txt_share)
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
                putExtra(Intent.EXTRA_TEXT, shareMessage)
                type = "text/plain"
            }

            startActivity(Intent.createChooser(shareIntent, shareTitle))
        }
    }

    private fun showDetailsActivity(title: TextView, desc: TextView, img: ImageView) {
        title.setTextColor(resources.getColor(R.color.purple_700))

        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra(MovieActivity.MOVIE_DATA, MovieData(title.text.toString(), desc.text.toString(), img.drawToBitmap()))
        startActivityForResult(intent, RESULT_CODE_LIKED)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COLOR_1, textMovie1Title.currentTextColor)
        outState.putInt(COLOR_2, textMovie2Title.currentTextColor)
        outState.putInt(COLOR_3, textMovie3Title.currentTextColor)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE_LIKED) {
            if (resultCode == Activity.RESULT_OK) {
               Toast.makeText(this, data?.getStringExtra(MovieActivity.RESULT_CONTENT)?: "Нет данных", Toast.LENGTH_SHORT).show()
            }
        }
    }
}