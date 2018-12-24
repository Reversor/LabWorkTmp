package foobar.reversor.ru.foobarnotes

import android.annotation.TargetApi
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import foobar.reversor.ru.foobarnotes.dto.ResultExpression

@TargetApi(Build.VERSION_CODES.P)
class DbHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {

    private val table_name = "foo"
    private val expression = "expression"
    private val result = "result"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""CREATE TABLE $table_name (_id INTEGER PRIMARY KEY, $expression TEXT, $result REAL)""")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun insert(resultExpression: ResultExpression) = this.writableDatabase.insert(
        table_name,
        null,
        ContentValues().run {
            this.put(expression, resultExpression.expression)
            this.put(result, resultExpression.result)
            this
        }
    )

    fun getResultExpressions(): MutableList<ResultExpression> {
        val list: MutableList<ResultExpression> = mutableListOf()
        val db = this.readableDatabase

        val cursor = db.query(table_name, arrayOf(expression, result), null, null, null, null, null)

        if (cursor == null) {
            return list
        }

        while (cursor.moveToNext()) {
            list.add(ResultExpression(cursor.getString(0), cursor.getDouble(1)))
        }

        cursor.close()
        return list;
    }

}
