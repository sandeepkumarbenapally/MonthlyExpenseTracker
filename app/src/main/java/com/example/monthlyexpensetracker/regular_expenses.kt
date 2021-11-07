package com.example.monthlyexpensetracker

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.View
import java.lang.IllegalStateException
import android.widget.TextView

import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.Toast
import java.lang.Error


class regularExpenses: DialogFragment(){
    var mContext: Context? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

//        this.mContext = mContext;


        return activity?.let{

            val alertDialog  = AlertDialog.Builder(it)
            val inflater = LayoutInflater.from(activity)
            val v: View = inflater.inflate(R.layout.activity_regular_expenses, null, false)


            alertDialog.setView(v)
            val namefield: TextView = v.findViewById(R.id.editTextExpenseName)
            val expenseField: TextView = v.findViewById(R.id.editTextExpenseValue)
            val dateField: DatePicker = v.findViewById(R.id.textViewDate)

            alertDialog.setPositiveButton("Submit", DialogInterface.OnClickListener { dialog, id ->
                Log.d("namefield", namefield.getText().toString())
                Log.d("expenseField", expenseField.getText().toString())
                Log.d("dateField year", dateField.getYear().toString())
                Log.d("dateField month", dateField.getMonth().toString())

                try {
                    val databaseHandler: DatabaseHandler = DatabaseHandler(mContext as Context)

                    if (namefield.text.toString() != "" && expenseField.getText()
                            .toString() != "" && dateField.getYear().toString() != ""
                    ) {
                        val expense = expenseField.text.toString().toFloat()
                        val namefield = namefield.text.toString()
                        val year = Integer.parseInt(dateField.getYear().toString())
                        val month = Integer.parseInt(dateField.getMonth().toString()) + 1

                        val status = databaseHandler.addExpense(
                            ExpensesModelClass(
                                month,
                                year,
                                "regular",
                                false,
                                expense,
                                namefield
                            )
                        )
                        if (status > -1) {
                            Toast.makeText(mContext, "added expense", Toast.LENGTH_LONG)
                                .show()
                        }
                    } else {
                        Toast.makeText(
                            mContext,
                            "date or expense or expense name cannot be blank",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: Error) {
                    Toast.makeText(
                        mContext,
                        "cannot add expense ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
            alertDialog.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {

                }
            })

            alertDialog.create()
        }?:throw IllegalStateException("Activity is null !!")
    }
}