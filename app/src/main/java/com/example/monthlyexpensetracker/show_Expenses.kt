package com.example.monthlyexpensetracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView


class show_Expenses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        Log.d("db", "loading expenses")
        val expenses: List<ExpensesModelClass> = databaseHandler.viewExpenses()

        Log.d("db", "loaded expenses")
        val expenseArrayName = Array<String>(expenses.size){"0"}
        val expenseArrayValue = Array<Float>(expenses.size){2.7f}
        val expenseArrayType = Array<String>(expenses.size){"0"}
        val expenseArrayMonth = Array<Int>(expenses.size){0}
        val expenseArrayYear = Array<Int>(expenses.size){0}

        Log.d("db", "initiated  vals")

        var index = 0
        for(e in expenses){

            expenseArrayName[index] = e.expense_name
            expenseArrayValue[index] = e.expense_value
            expenseArrayType[index] = e.type
            expenseArrayMonth[index] = e.month
            expenseArrayYear[index] = e.year

            index++
        }

        Log.d("db", "added  arrays")
        setContentView(R.layout.activity_show_expenses)
        //creating custom ArrayAdapter
        val myListAdapter = MyListAdapter(this,expenseArrayName,expenseArrayValue,expenseArrayType,expenseArrayMonth,expenseArrayYear)

        Log.d("db", "added  myListAdapter")

        Log.d("db", "added  listView")

        val listViewId = findViewById<ListView>(R.id.listViewId)

        listViewId.adapter = myListAdapter

        Log.d("db", "added  listView adapter")



    }
}