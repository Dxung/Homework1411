package com.example.homework1411

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(val students: List<StudentModel>,
                     private val onEditClick: (StudentModel, Int) -> Unit, val OnDeleteClick: (Int, View) -> Unit): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
        val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
        val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
        val imageDelete: ImageView = itemView.findViewById(R.id.image_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
            parent, false)
        return StudentViewHolder(itemView)
    }

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.textStudentName.text = student.studentName
        holder.textStudentId.text = student.studentId

        //Setup for EditButtonClick
        addListenerToImageEdit(holder, student, position)

        //Setup for DeleteButtonClick
        addListenerToImageDelete(holder, position, holder.itemView)

    }

    //Truyền data student và vị trí (pos) cho hàm ở mainactivity
    private fun addListenerToImageEdit(holder: StudentViewHolder, student: StudentModel, pos: Int){
        holder.imageEdit.setOnClickListener{
            onEditClick(student, pos)
        }
    }

    private fun addListenerToImageDelete(holder: StudentViewHolder, pos: Int, view: View){
        holder.imageDelete.setOnClickListener{
            OnDeleteClick(pos, view)
        }
    }
}