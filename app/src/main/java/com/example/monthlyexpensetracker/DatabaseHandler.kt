package com.example.monthlyexpensetracker

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const  val DATABASE_NAME = "Expenses"
        private const  val TABLE_INCOME = "IncomeTable"
        private const  val TABLE_EXPENSES = "ExpensesTable"
        private const  val KEY_ID = "id"
        private const  val KEY_MONTH= "month"
        private const  val KEY_YEAR= "year"
        private const  val KEY_TYPE = "type"
        private const  val KEY_ARCHIVED = "archived"
        private const  val KEY_VALUE = "value"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createExpensesTable = ("CREATE TABLE " + TABLE_EXPENSES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MONTH + " TEXT," + KEY_YEAR + " TEXT," + KEY_TYPE + " TEXT,"
                + KEY_ARCHIVED + " TEXT" + KEY_VALUE + " FLOAT" + ")")

        val createIncomeTable = ("CREATE TABLE " + TABLE_INCOME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MONTH + " TEXT," + KEY_YEAR + " TEXT," + KEY_VALUE + " FLOAT" + ")")
        db?.execSQL(createExpensesTable)
        db?.execSQL(createIncomeTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_EXPENSES")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_INCOME")
        onCreate(db)
    }

    // method to add income
    fun addIncome(income: IncomeModelClass):Long{
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
        contentValues.put(KEY_VALUE, expense.value)

        // Inserting Row
        val success = db.insert(TABLE_EXPENSES, null, contentValues)

        db.close() // Closing database connection
        return success
    }
//    //method to read data
//    fun viewEmployee():List<EmpModelClass>{
//        val empList:ArrayList<EmpModelClass> = ArrayList<EmpModelClass>()
//        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
//        val db = this.readableDatabase
//        var cursor: Cursor? = null
//        try{
//            cursor = db.rawQuery(selectQuery, null)
//        }catch (e: SQLiteException) {
//            db.execSQL(selectQuery)
//            return ArrayList()
//        }
//        var userId: Int
//        var userName: String
//        var userEmail: String
//        if (cursor.moveToFirst()) {
//            do {
//                userId = cursor.getInt(cursor.getColumnIndex("id"))
//                userName = cursor.getString(cursor.getColumnIndex("name"))
//                userEmail = cursor.getString(cursor.getColumnIndex("email"))
//                val emp= EmpModelClass(userId = userId, userName = userName, userEmail = userEmail)
//                empList.add(emp)
//            } while (cursor.moveToNext())
//        }
//        return empList
//    }
//    //method to update data
//    fun updateEmployee(emp: EmpModelClass):Int{
//        val db = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(KEY_ID, emp.userId)
//        contentValues.put(KEY_NAME, emp.userName) // EmpModelClass Name
//        contentValues.put(KEY_EMAIL,emp.userEmail ) // EmpModelClass Email
//
//        // Updating Row
//        val success = db.update(TABLE_CONTACTS, contentValues,"id="+emp.userId,null)
//        //2nd argument is String containing nullColumnHack
//        db.close() // Closing database connection
//        return success
//    }
//    //method to delete data
//    fun deleteEmployee(emp: EmpModelClass):Int{
//        val db = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(KEY_ID, emp.userId) // EmpModelClass UserId
//        // Deleting Row
//        val success = db.delete(TABLE_CONTACTS,"id="+emp.userId,null)
//        //2nd argument is String containing nullColumnHack
//        db.close() // Closing database connection
//        return success
//    }
}