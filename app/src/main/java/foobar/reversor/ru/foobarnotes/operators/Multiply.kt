package foobar.reversor.ru.foobarnotes.operators

import com.udojava.evalex.AbstractOperator
import com.udojava.evalex.Expression

import java.math.BigDecimal

class Multiply : AbstractOperator("\u00d7", Expression.OPERATOR_PRECEDENCE_MULTIPLICATIVE, true) {

    override fun eval(v1: BigDecimal, v2: BigDecimal): BigDecimal {
        return v1.multiply(v2)
    }
}
