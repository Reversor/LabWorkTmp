package foobar.reversor.ru.foobarnotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.udojava.evalex.Expression
import foobar.reversor.ru.foobarnotes.adapters.ResultExpressionAdapter
import foobar.reversor.ru.foobarnotes.dto.ResultExpression
import foobar.reversor.ru.foobarnotes.operators.Divide
import foobar.reversor.ru.foobarnotes.operators.Minus
import foobar.reversor.ru.foobarnotes.operators.Multiply
import foobar.reversor.ru.foobarnotes.operators.Plus
import java.math.BigDecimal
import java.math.MathContext
import java.time.Instant

class MainActivity : AppCompatActivity() {


    private lateinit var results: MutableList<ResultExpression>

    private lateinit var input: Editable
    private lateinit var result: StringBuilder
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: ResultExpressionAdapter
    private val dbHelper = DbHelper(this, "Foo", null, 2)


    private var value: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById<EditText>(R.id.text_input).editableText
        result = StringBuilder()
        results = dbHelper.getResultExpressions()

        viewManager = LinearLayoutManager(this)
        viewAdapter = ResultExpressionAdapter(results)
        findViewById<RecyclerView>(R.id.list_result).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        findViewById<Button>(R.id.button_с).setOnLongClickListener { v: View? ->
            results.clear()
            viewAdapter.notifyDataSetChanged()
            dbHelper.clearTable()
        }
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
        result.setLength(0)
        value = 0.0
    }

    fun symbolHandler(view: View): Editable = input.append((view as TextView).text)

    fun backspace(view: View) {
        val length = input.length
        if (length > 0) input.delete(length - 1, length)
    }

    fun result(view: View) {
        result.setLength(0)
        val expression = input.toString()
        val decimal = evaluate(expression)
        result.append(decimal.toString())
        val resultExpression = ResultExpression(expression, decimal.toDouble(), Instant.now().epochSecond)
        dbHelper.insert(resultExpression)
        results.add(resultExpression)
        viewAdapter.notifyItemInserted(result.capacity())
        viewAdapter.notifyDataSetChanged()
        input.clear()
    }

    private fun evaluate(expression: String): BigDecimal {
        val evaluationalExpression = Expression(expression, MathContext.DECIMAL64)
        evaluationalExpression.addOperator(Divide())
        evaluationalExpression.addOperator(Multiply())
        evaluationalExpression.addOperator(Minus())
        evaluationalExpression.addOperator(Plus())
        try {
            return evaluationalExpression.eval()
        } catch (e: Exception) {
            return BigDecimal.ZERO
        }
    }

}
