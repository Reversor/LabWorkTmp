package foobar.reversor.ru.foobarnotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.udojava.evalex.AbstractOperator
import com.udojava.evalex.Expression
import com.udojava.evalex.Operator
import foobar.reversor.ru.foobarnotes.operators.Divide
import foobar.reversor.ru.foobarnotes.operators.Minus
import foobar.reversor.ru.foobarnotes.operators.Multiply
import foobar.reversor.ru.foobarnotes.operators.Plus
import java.math.BigDecimal
import java.math.MathContext

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

    fun symbolHandler(view: View): Editable = input.append((view as TextView).text)

    fun backspace(view: View) {
        val length = input.length
        if (length > 0) input.delete(length - 1, length)
    }

    fun result(view: View) {
        result.clear()
        result.append(evaluate(input.toString()).toString())
        input.clear()
    }

    fun evaluate(expression: String): BigDecimal {
        val evaluationalExpression = Expression(expression, MathContext.DECIMAL64)
        evaluationalExpression.addOperator(Divide())
        evaluationalExpression.addOperator(Multiply())
        evaluationalExpression.addOperator(Minus())
        evaluationalExpression.addOperator(Plus())
        try {
            val bigDecimal = evaluationalExpression.eval()
            return bigDecimal
        } catch (e: Expression.ExpressionException) {
            return BigDecimal.ZERO
        }
    }

}
