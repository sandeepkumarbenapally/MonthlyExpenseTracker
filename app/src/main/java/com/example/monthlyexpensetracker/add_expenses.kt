package com.example.monthlyexpensetracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button


class add_expenses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expenses)

        regExpensesButton = findViewById<Button>(R.id.regExpenses)
        regExpensesButton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                // trigger the alert dialog
                showAlert()
            }
        })
        irregExpensesButton = findViewById<Button>(R.id.irregExpenses)
        irregExpensesButton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                // trigger the alert dialog
                showAlert1()
            }
        })
    }

    private var regExpensesButton: Button? = null
    private var irregExpensesButton: Button? = null
    private fun showAlert() {

        regularExpenses().show(supportFragmentManager, "regularexpenses")
    }

    private fun showAlert1() {

        IrregularExpenses().show(supportFragmentManager, "irregularexpenses.kt")
    }
}
