package com.example.listkotlin

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList
import kotlin.Boolean as Boolean1


class MainActivity : AppCompatActivity() {
    val mangSinhVien: ArrayList<SinhVien> = ArrayList()
    val mangSinhVientwo: ArrayList<SinhVien> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvDanhSach.adapter = SinhVienAdapter(this, mangSinhVientwo)

        dialogAdd()
        DeleteItemList()
        EditItemList()
        searchView()
    }

    private fun searchView() {
    }

    private fun EditItemList() {

    }

    fun DeleteItemList() {
        lvDanhSach.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val builder =
                    android.app.AlertDialog.Builder(this@MainActivity)
                builder.setTitle("xác nhận xóa")
                builder.setMessage("bạn có chắc xóa học sinh!")
                builder.setPositiveButton(
                    "có"
                ) { dialog, which ->
                    mangSinhVien.removeAt(position)
                    mangSinhVientwo.clear()
                    mangSinhVientwo.addAll(mangSinhVien)
                    lvDanhSach.invalidateViews()
                }
                builder.setNegativeButton(
                    "không"
                ) { dialog, which -> lvDanhSach.invalidateViews() }
                builder.show()
                return@OnItemClickListener
            }
    }

    fun dialogAdd() {
        btnAdd.setOnClickListener {
            val buidle = AlertDialog.Builder(this)
            val inflater: LayoutInflater = layoutInflater
            val dialogLayuot = inflater.inflate(R.layout.them_sinh_vien, null)

            var etName: EditText = dialogLayuot.findViewById(R.id.etName)
            var etPhone: EditText = dialogLayuot.findViewById(R.id.etPhone)
            var etNamSinh: EditText = dialogLayuot.findViewById(R.id.etNamSinh)
            var etChuyenNganh: EditText = dialogLayuot.findViewById(R.id.etChuyenNganh)
            var etHe: EditText = dialogLayuot.findViewById(R.id.etHe)

            with(buidle) {
                setTitle("Thêm sinh Viên")
                setPositiveButton("OK") { dialog, which ->
                    if (etChuyenNganh.text.isNotEmpty() && etNamSinh.text.isNotEmpty()) {
                        mangSinhVien.add(
                            SinhVien(
                                etName.text.toString(),
                                "",
                                etPhone.text.toString(),
                                "",
                                etHe.text.toString()
                            )
                        )
                        mangSinhVientwo.clear()
                        mangSinhVientwo.addAll(mangSinhVien)
                        lvDanhSach.invalidateViews()
                    } else {
                        Toast.makeText(
                            context,
                            "Bạn nhập thiếu thông tin hoặc trùng SDT!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                setNegativeButton("Cance") { dialog, which ->
                    lvDanhSach.invalidate()
                }
                setView(dialogLayuot)
                show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean1 {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onOptionsItemSelected(item: MenuItem): Boolean1 {
        val id = item.itemId
        if (id == R.id.action1) {
            val sortedName = mangSinhVientwo.sortedBy { it.ten }
            mangSinhVientwo.clear()
            mangSinhVientwo.addAll(sortedName)
            lvDanhSach.invalidateViews()
        } else if (id == R.id.action2) {
            val sortedDate = mangSinhVien.sortedBy { it.namSinh }
            mangSinhVientwo.clear()
            mangSinhVientwo.addAll(sortedDate)
            lvDanhSach.invalidateViews()
        } else if (id == R.id.action3) {
            val sortedPhone = mangSinhVien.sortedBy { it.soDienThoai }
            mangSinhVientwo.clear()
            mangSinhVientwo.addAll(sortedPhone)
            lvDanhSach.invalidateViews()
        } else if (id == R.id.action4) {

        } else if (id == R.id.action5) {

        }

        return super.onOptionsItemSelected(item)
    }

}
