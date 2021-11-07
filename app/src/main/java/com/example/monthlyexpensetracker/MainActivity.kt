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

    }

//    fun saveRecord(view: View){
//        val id = u_id.text.toString()
//        val name = u_name.text.toString()
//        val email = u_email.text.toString()
//        val `databaseHandler`: `DatabaseHandler`= `DatabaseHandler`(this)
//        if(id.trim()!="" && name.trim()!="" && email.trim()!=""){
//            val status = `databaseHandler`.addEmployee(EmpModelClass(Integer.parseInt(id),name, email))
//            if(status > -1){
//                Toast.makeText(applicationContext,"record save",Toast.LENGTH_LONG).show()
//                u_id.text.clear()
//                u_name.text.clear()
//                u_email.text.clear()
//            }
//        }else{
//            Toast.makeText(applicationContext,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
//        }
//
//    }


    fun addExpense() {
         val intent = Intent(this, add_expenses::class.java)
// To pass any data to next activity
       //  intent.putExtra("keyIdentifier", value)
// start your next activity

         print(intent)
         startActivity(intent)
    }
}