package com.example.monthlyexpensetracker

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Toast
import android.util.Log
import java.lang.Error
import android.widget.ListView




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var _editText = findViewById<EditText>(R.id.editTextNumberDecimal)
        val textView: TextView = findViewById(R.id.textView_date)
        textView.text = "Select Month"
//        textView.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "MMM, yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                textView.text = sdf.format(cal.time)
                Log.d("sandy", "calling function")
                addIncome(view, year, monthOfYear + 1);
            }



        textView.setOnClickListener {
            DatePickerDialog(
                this@MainActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()



        }


       var  _button = findViewById<Button>(R.id.addExpensesButton)
        _button?.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(p0: View?) {
                addExpense();
            }
        })

        var  _button1 = findViewById<Button>(R.id.showExpensesButton)
        _button1?.setOnClickListener ( object : View.OnClickListener {
            override fun onClick(p0: View?) {
                showExpense();
            }
        })


    }

    fun addIncome(view: View, year: Int, monthOfYear: Int){
        var incomeValue = findViewById<EditText>(R.id.editTextNumberDecimal)
        val textView: TextView = findViewById(R.id.textView_date)

        try {
            val databaseHandler: DatabaseHandler= DatabaseHandler(this)

            if(incomeValue.text.toString() != "" && monthOfYear != 0 && year != 0){
                val income = incomeValue.text.toString().toFloat()
                Log.d("income", income.toString())
                Log.d("year", year.toString())
                Log.d("month", monthOfYear.toString())
                val status = databaseHandler.addIncome(IncomeModelClass(monthOfYear, year, income))
                if(status > -1){
                    Toast.makeText(applicationContext,"added income",Toast.LENGTH_LONG).show()
//                    incomeValue.text.clear()
//                    textView.text = "Select Month"
                }
            }else{
                Toast.makeText(applicationContext,"date or income cannot be blank",Toast.LENGTH_LONG).show()
            }
        } catch (e: Error) {
            Toast.makeText(applicationContext,"cannot add income ${e.message}",Toast.LENGTH_LONG).show()
        }
    }


    fun addExpense() {
         val intent = Intent(this, add_expenses::class.java)

         print(intent)
         startActivity(intent)
    }

    fun showExpense() {
        val intent = Intent(this, show_Expenses::class.java)

        startActivity(intent)
    }
}