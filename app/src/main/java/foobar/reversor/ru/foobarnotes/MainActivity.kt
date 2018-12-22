package foobar.reversor.ru.foobarnotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val editable: Editable = findViewById<TextView>(R.id.text_result).editableText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        findViewById<Button>(R.id.button_0).setOnClickListener()
    }

    fun percent(view: View) {

    }

    fun symbolHandler(view: View) {
        editable.append((view as TextView).text)
    }
}
