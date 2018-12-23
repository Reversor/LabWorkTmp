package foobar.reversor.ru.foobarnotes.operators

import com.udojava.evalex.AbstractOperator
import com.udojava.evalex.Expression
import foobar.reversor.ru.foobarnotes.R

import java.math.BigDecimal
import java.math.MathContext

class Divide : AbstractOperator("\u00f7", Expression.OPERATOR_PRECEDENCE_MULTIPLICATIVE, true) {

    override fun eval(v1: BigDecimal, v2: BigDecimal): BigDecimal {
        return v1.divide(v2, MathContext.DECIMAL64)
    }
}
