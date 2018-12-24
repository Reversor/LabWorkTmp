package foobar.reversor.ru.foobarnotes.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import foobar.reversor.ru.foobarnotes.R
import foobar.reversor.ru.foobarnotes.dto.ResultExpression
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class ResultExpressionAdapter(private val values: List<ResultExpression>) :
    RecyclerView.Adapter<ResultExpressionAdapter.ResultExpressionHolder>() {
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm d-MMM-Y")


    class ResultExpressionHolder(val linearLayout: LinearLayout) : RecyclerView.ViewHolder(linearLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultExpressionHolder = ResultExpressionHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.result_view, parent, false) as LinearLayout
    )

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ResultExpressionHolder, position: Int) {
        val resultExpression = values[position]
        val s = "${resultExpression.expression}=${resultExpression.result}"
        holder.linearLayout.findViewById<TextView>(R.id.text_result).text = s
        val dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(resultExpression.timestamp),
            ZoneOffset.systemDefault()
        )
        val t = timeFormatter.format(dateTime)
        holder.linearLayout.findViewById<TextView>(R.id.text_time).text = t
    }
}