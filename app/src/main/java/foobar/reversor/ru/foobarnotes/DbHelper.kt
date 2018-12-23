package foobar.reversor.ru.foobarnotes

import android.annotation.TargetApi
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build

@TargetApi(Build.VERSION_CODES.P)
class DbHelper(context: Context?, name: String?, version: Int, openParams: SQLiteDatabase.OpenParams) :
    SQLiteOpenHelper(context, name, version, openParams) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""CREATE TABLE $TABLE_NAME (_id INTEGER PRIMARY KEY, $EXPRESSION TEXT, $RESULT REAL)""")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {

        val TABLE_NAME = "foo"
        val EXPRESSION = "expression"
        val RESULT = "result"
    }
}
