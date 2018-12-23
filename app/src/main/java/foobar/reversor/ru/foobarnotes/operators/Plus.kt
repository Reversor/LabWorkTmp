package foobar.reversor.ru.foobarnotes.operators

import com.udojava.evalex.AbstractOperator
import com.udojava.evalex.Expression

import java.math.BigDecimal

class Plus : AbstractOperator("\u002b", Expression.OPERATOR_PRECEDENCE_ADDITIVE, true) {

    override fun eval(v1: BigDecimal, v2: BigDecimal): BigDecimal {
        return v1.add(v2)
    }
}
