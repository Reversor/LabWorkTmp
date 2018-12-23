package foobar.reversor.ru.foobarnotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var input: Editable
    lateinit var result: Editable

    var value: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById<EditText>(R.id.text_input).editableText
        result = findViewById<EditText>(R.id.text_result).editableText
    }

    fun invert(view: View) {
        if (input.isNotEmpty()) {
            if (input[0] == '-') {
                input.delete(0, 1)
            } else {
                input.insert(0, "-")
            }
        } else {
            input.append('-')
        }
    }

    fun clear(view: View) {
        input.clear()
        result.clear()
        value = 0.0
    }

    fun symbolHandler(view: View) {
        input.append((view as TextView).text)
    }

    fun point(view: View) {
        if (input.indexOf('.') < 0) {
            symbolHandler(view)
        }
    }

    fun plus(view: View) {
        doOperation { d1, d2 -> d1 + d2 }
    }

    fun minus(view: View) {
        doOperation { d1, d2 -> d1 - d2 }
    }

    fun backspace(view: View) {
        val length = input.length
        if (length > 0) input.delete(length - 1, length)
    }

    fun result(view: View) {
        input.clear()
        result.clear()
        result.append(value.toString())
    }

    fun doOperation(operation: (Double, Double) -> Double) {
        if (input.isNotEmpty()) {
            value = operation.invoke(value, input.toString().toDouble())
            result.clear()
            result.append(value.toString())
        }
    }
}
