package com.example.homework1411

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class EditStudentDialog(private val student: StudentModel,
                        private val onSaveClick :(StudentModel, Int) -> Unit,
                        private val position:Int ) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_edit_student, null)
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Edit Student")
            .setView(dialogView)
            .setPositiveButton("Save", null)  // Chưa xử lý logic save ở đây
            .setNegativeButton("Cancel", null)

        val dialog = builder.create()

        //Hiển thị dữ liệu của sinh viên hiện tại
        dialogView.findViewById<EditText>(R.id.input_name_editText).setText(student.studentName)
        dialogView.findViewById<EditText>(R.id.input_Id_editText).setText(student.studentId)

        dialog.setOnShowListener {
            val positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {

                //Lấy lại dữ liệu đã được sửa
                val nameInput = dialogView.findViewById<EditText>(R.id.input_name_editText).text.toString()
                val idInput = dialogView.findViewById<EditText>(R.id.input_Id_editText).text.toString()

                if (nameInput.isNotBlank() && idInput.isNotBlank()) {
                    //Trong trường hợp có thay đổi
                    if (nameInput!=(student.studentName) || idInput!=(student.studentId)) {
                        onSaveClick(StudentModel(nameInput, idInput),position)
                        Toast.makeText(context, "UPDATE NEW STUDENT SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                        dismiss()
                    } else {// nếu không có thay đổi gì
                        Toast.makeText(context, "STUDENT DATA REMAINS", Toast.LENGTH_SHORT).show()
                        dismiss()
                    }
                } else {
                    Toast.makeText(context, "PLEASE FILL ALL FIELDS", Toast.LENGTH_SHORT).show()
                    // Không đóng dialog
                }
            }
        }
        return dialog
    }

}