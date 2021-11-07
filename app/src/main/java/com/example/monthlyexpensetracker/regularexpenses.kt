package com.example.monthlyexpensetracker

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import java.lang.IllegalStateException

class regularExpenses: DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{

            val alertDialog = AlertDialog.Builder(it)
            alertDialog.setView(requireActivity().layoutInflater.inflate(R.layout.activity_regular_expenses, null))
            alertDialog.setPositiveButton("Submit", DialogInterface.OnClickListener({dialog, id ->
            }))
            alertDialog.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {

                }
            })

            alertDialog.create()
        }?:throw IllegalStateException("Activity is null !!")
    }
}