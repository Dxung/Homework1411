package com.example.homework1411

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar

class DeleteStudentDialog (private val deleteOnClick :(Int) -> Unit,
                           private val undoOnClick : (position: Int) -> Unit,
                           private val position: Int ,
                           private val view: View) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Delete Student")
            .setPositiveButton("Delete", null)
            .setNegativeButton("Cancel", null)

        val dialog = builder.create()

        dialog.setOnShowListener {
            val positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                deleteOnClick(position)
                Toast.makeText(context, "DELETE STUDENT SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                showUndoSnackBar(view, position)
                dismiss()

            }
        }
        return dialog
    }

    private fun showUndoSnackBar(view: View, position: Int){
        Snackbar.make(view , "Student deleted", Snackbar.LENGTH_INDEFINITE)
            .setDuration(20000)
            .setAction("Undo"){
                undoOnClick(position)
            }
            .show()
    }
}