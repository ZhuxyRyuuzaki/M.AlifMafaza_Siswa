package com.example.malifmafaza_siswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var inputNis : EditText
    private lateinit var inputNama : EditText
    private lateinit var jkLakiLaki : RadioButton
    private lateinit var jkPerempuan : RadioButton
    private lateinit var tambahData : Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNis = findViewById(R.id.inputNIS)
        inputNama = findViewById(R.id.inputNama)
        jkLakiLaki = findViewById(R.id.Laki2)
        jkPerempuan = findViewById(R.id.Perempuan)
        tambahData = findViewById(R.id.btnTambah)
        recyclerView = findViewById(R.id.listItem)
        // membuat variabel kosong tipe array MutableList untuk simpan data array

        //data array disimpan di data class DataSiswa
        val data = mutableListOf<DataSiswa>()
        viewManager = LinearLayoutManager(this)
        recyclerAdapter = SiswaAdapter(data)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = viewManager

        // setOnClickListener untuk memfungsikan tombol Tambah Data
        tambahData.setOnClickListener {
            // 1. membuat variabel penyimpanan data
            val nis = inputNis.text.toString() //editText.text -> String
            val nama = inputNama.text.toString()
            var jk = ""
            if (jkLakiLaki.isChecked) {
                jk = "Laki-Laki"
            } else {
                jk = "Perempuan"
            }
            // Simpan data ke array mutableList
            val SiswaData = DataSiswa(nis, nama, jk)
            data.add(SiswaData)
            recyclerAdapter.notifyDataSetChanged()
        }
    }
}