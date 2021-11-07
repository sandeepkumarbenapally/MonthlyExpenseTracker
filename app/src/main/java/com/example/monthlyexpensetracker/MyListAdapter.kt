package com.example.monthlyexpensetracker

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(private val context: Activity, private val expense_name: Array<String>, private val expense_value: Array<Float>, private val type: Array<String>, private val month: Array<Int>, private val year: Array<Int>)
    : ArrayAdapter<String>(context, R.layout.activity_show_expenses, expense_name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val nameText = rowView.findViewById(R.id.textViewName) as TextView
        val valueText = rowView.findViewById(R.id.textViewValue) as TextView
        val typeText = rowView.findViewById(R.id.textViewType) as TextView
        val monthText = rowView.findViewById(R.id.textViewMonth) as TextView
        val yearText = rowView.findViewById(R.id.textViewYear) as TextView

        nameText.text = "Expense Name ${expense_name[position]}"
        valueText.text = "Expense value ${expense_value[position]}"
        typeText.text = "Expense Type ${type[position]}"
        monthText.text = "Expense Month ${month[position]}"
        yearText.text = "Expense Year ${year[position]}"
        return rowView
    }
}