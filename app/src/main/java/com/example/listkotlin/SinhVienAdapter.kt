package com.example.listkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filter.FilterResults
import android.widget.Filterable
import android.widget.TextView

class SinhVienAdapter(var context: Context, var mangSinhVien: ArrayList<SinhVien>) :
    BaseAdapter(), Filterable {
    class ViewHolder(row: View) {

        var textviewTen: TextView
        var textViewNamSinh: TextView
        var textViewSoDienThoai: TextView
        var textViewChuyenNganh: TextView
        var textViewHe: TextView

        init {
            textviewTen = row.findViewById(R.id.tvTen)
            textViewNamSinh = row.findViewById(R.id.tvNamSinh)
            textViewSoDienThoai = row.findViewById(R.id.tvSoDienThoai)
            textViewChuyenNganh = row.findViewById(R.id.tvNganh)
            textViewHe = row.findViewById(R.id.tvHe)
        }
    }

    //filter search
    override fun getFilter(): Filter {
        return filterStudent
    }

    var filterStudent = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            filterResults.values = mangSinhVien
            return filterResults
        }
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        }
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        //gan du lieu vao lan chay 2
        if (convertView == null) {
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.dong_sinh_vien, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        var sinhVien: SinhVien = getItem(position) as SinhVien
        viewHolder.textviewTen.text = sinhVien.ten
        viewHolder.textViewNamSinh.text = sinhVien.namSinh
        viewHolder.textViewSoDienThoai.text = sinhVien.soDienThoai
        viewHolder.textViewChuyenNganh.text = sinhVien.chuyenNganh
        viewHolder.textViewHe.text = sinhVien.he

        return view as View
    }

    override fun getItem(position: Int): Any {
        return mangSinhVien.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mangSinhVien.size
    }
}