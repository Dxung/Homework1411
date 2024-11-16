package com.example.homework1411

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var addNewButton : Button
    private var students: MutableList<StudentModel> = mutableListOf()
    private lateinit var studentAdapter : StudentAdapter
    private lateinit var tempUndoData : StudentModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initProp()

        //Khởi tạo các nút
        setupAddNewButton()

    }

    private fun initProp(){
        addNewButton = findViewById(R.id.btn_add_new)
        FirstInitAdaptor()
        InitTempUndoData(students)

    }

    //Setup First TempData
    private fun InitTempUndoData(list: MutableList<StudentModel>){
        if(list.isNotEmpty()) {
            tempUndoData = students[0]
        }
    }

    //Setup Adaptor
    private fun FirstInitAdaptor(){
        //Thêm danh sách dữ liệu cho adaptor
        students.addAll(listOf(
            StudentModel("Nguyễn Văn An", "SV001"),
            StudentModel("Trần Thị Bảo", "SV002"),
            StudentModel("Lê Hoàng Cường", "SV003"),
            StudentModel("Phạm Thị Dung", "SV004"),
            StudentModel("Đỗ Minh Đức", "SV005"),
            StudentModel("Vũ Thị Hoa", "SV006"),
            StudentModel("Hoàng Văn Hải", "SV007"),
            StudentModel("Bùi Thị Hạnh", "SV008"),
            StudentModel("Đinh Văn Hùng", "SV009"),
            StudentModel("Nguyễn Thị Linh", "SV010"),
            StudentModel("Phạm Văn Long", "SV011"),
            StudentModel("Trần Thị Mai", "SV012"),
            StudentModel("Lê Thị Ngọc", "SV013"),
            StudentModel("Vũ Văn Nam", "SV014"),
            StudentModel("Hoàng Thị Phương", "SV015"),
            StudentModel("Đỗ Văn Quân", "SV016"),
            StudentModel("Nguyễn Thị Thu", "SV017"),
            StudentModel("Trần Văn Tài", "SV018"),
            StudentModel("Phạm Thị Tuyết", "SV019"),
            StudentModel("Lê Văn Vũ", "SV020")
        ))

        //Khởi tạo adaptor
        studentAdapter = StudentAdapter(students, ::setupEditButton, ::setupDeleteButton)

        //khởi tạo thuộc tính cho RecyclerView
        findViewById<RecyclerView>(R.id.recycler_view_students).run {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }

    //Add New Student Func
    private fun setupAddNewButton (){
        //Xử lý sự kiện khi ấn nút "New Button"
        addNewButton.setOnClickListener {
            val addNewStudentDialog = AddStudentDialog(::updateAddRecyclerListData)
            addNewStudentDialog.show(supportFragmentManager, "AddNewStudentDialog")
        }
    }

    private fun setupEditButton(student: StudentModel, pos: Int){
        val editStudentDialog = EditStudentDialog (student,::updateEditRecyclerListData, pos)
        editStudentDialog.show(supportFragmentManager, "EditNewStudentDialog")
    }

    private fun setupDeleteButton(pos: Int, view: View){
        val deleteStudentDialog = DeleteStudentDialog (::updateDeleteRecyclerListData,::undoDeleteRecyclerListData ,pos, view)
        deleteStudentDialog.show(supportFragmentManager, "DeleteStudentDialog")
    }

    private fun updateAddRecyclerListData(student: StudentModel){
        students.add(student)
        studentAdapter.notifyItemInserted(students.size-1)
    }

    private fun updateEditRecyclerListData(student: StudentModel, pos: Int){
        students[pos] = student
        studentAdapter.notifyItemChanged(pos)
    }

    private fun updateDeleteRecyclerListData(position: Int){
        tempUndoData = students.removeAt(position)
        studentAdapter.notifyItemRemoved(position)
    }

    private fun undoDeleteRecyclerListData(position: Int){
        if(position == students.size - 1){
            students.add(tempUndoData)

        }else{
            students.add(position, tempUndoData)
        }

        studentAdapter.notifyItemInserted(position)

    }







}