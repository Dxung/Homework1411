package com.example.homework1411

import androidx.appcompat.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class AddStudentDialog(private val updateList: (StudentModel) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_edit_student, null)

        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Add New Student")
            .setView(dialogView)
            .setPositiveButton("Add", null)
            .setNegativeButton("Cancel", null)

        val dialog = builder.create()

        //Chỉ bắt đầu thêm logic cho nút POSITIVE khi dialog được show
        //Dùng để khởi chạy hàm update dữ liệu của
        dialog.setOnShowListener {
            val positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                val nameInput = dialogView.findViewById<EditText>(R.id.input_name_editText).text.toString()
                val idInput = dialogView.findViewById<EditText>(R.id.input_Id_editText).text.toString()

                if (nameInput.isNotBlank() && idInput.isNotBlank()) {
                    //gọi hàm update list với dữ liệu mới kiểu studentModel
                    updateList(StudentModel(nameInput,idInput))
                    Toast.makeText(context, "ADD NEW STUDENT SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                    dismiss()
                } else {
                    Toast.makeText(context, "PLEASE FILL ALL FIELDS", Toast.LENGTH_SHORT).show()
                    // Không đóng dialog
                }
            }
        }

        return dialog
    }
}

