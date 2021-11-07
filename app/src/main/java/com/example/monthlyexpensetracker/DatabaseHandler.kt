package com.example.monthlyexpensetracker
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.util.Log
import android.widget.Toast
//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 6
        private const  val DATABASE_NAME = "Expenses"
        const  val TABLE_INCOME = "IncomeTable"
        const  val TABLE_EXPENSES = "ExpensesTable"
        const  val KEY_ID = "id"
        const  val KEY_MONTH= "month"
        const  val KEY_YEAR= "year"
        const  val KEY_TYPE = "type"
        const  val KEY_ARCHIVED = "archived"
        const  val KEY_VALUE = "expense_value"
        const  val KEY_NAME= "expense_name"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createExpensesTable = ("CREATE TABLE " + TABLE_EXPENSES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_MONTH + " TEXT," + KEY_YEAR + " TEXT," + KEY_TYPE + " TEXT,"
                + KEY_ARCHIVED + " TEXT," + KEY_VALUE + " FLOAT," + KEY_NAME + " TEXT" + ")")
        Log.d("db createExpensesTable", createExpensesTable)

        val createIncomeTable = ("CREATE TABLE " + TABLE_INCOME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_MONTH + " TEXT," + KEY_YEAR + " TEXT," + KEY_VALUE + " FLOAT" + ")")

        Log.d("db createIncomeTable", createIncomeTable)

        try {
            db.execSQL(createExpensesTable)
            db.execSQL(createIncomeTable)
        } catch (e: Error) {
            Log.d("db error", e.message.toString())
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_EXPENSES")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_INCOME")
        onCreate(db)
    }

    // method to add income
    fun addIncome(income: IncomeModelClass):Long{
        print(income)
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_MONTH, income.month)
        contentValues.put(KEY_YEAR, income.year)
        contentValues.put(KEY_VALUE, income.value)

        // Inserting Row
        val success = db.insert(TABLE_INCOME, null, contentValues)

        db.close() // Closing database connection
        return success
    }

    // method to add expense
    fun addExpense(expense: ExpensesModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_MONTH, expense.month)
        contentValues.put(KEY_YEAR, expense.year)
        contentValues.put(KEY_TYPE, expense.type)
        contentValues.put(KEY_ARCHIVED, expense.archived)
        contentValues.put(KEY_VALUE, expense.expense_value)
        contentValues.put(KEY_NAME, expense.expense_name)

        // Inserting Row
        val success = db.insert(TABLE_EXPENSES, null, contentValues)

        db.close() // Closing database connection
        return success
    }
    //method to read data
    fun viewExpenses():List<ExpensesModelClass>{
        val expensesList:ArrayList<ExpensesModelClass> = ArrayList<ExpensesModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_EXPENSES"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var expense_name: String
        var expense_value: Float
        var type: String
        var month: Int
        var year: Int
        var archived: Boolean
        if (cursor.moveToFirst()) {
            do {
                expense_name = cursor.getString(cursor.getColumnIndexOrThrow("expense_name"))
                expense_value = cursor.getFloat(cursor.getColumnIndexOrThrow("expense_value"))
                type = cursor.getString(cursor.getColumnIndexOrThrow("type"))
                month = cursor.getInt(cursor.getColumnIndexOrThrow("month"))
                year = cursor.getInt(cursor.getColumnIndexOrThrow("year"))
                archived = cursor.getInt(cursor.getColumnIndexOrThrow("archived")) > 0

                val expense= ExpensesModelClass(expense_name = expense_name, expense_value = expense_value, type = type, month = month, year=year, archived=archived)
                expensesList.add(expense)
            } while (cursor.moveToNext())
        }

        print("Sud")
        print(expensesList)
        return expensesList
    }
}