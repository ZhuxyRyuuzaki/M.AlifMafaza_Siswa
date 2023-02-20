package com.example.malifmafaza_siswa

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class SiswaAdapter(private val dataset: MutableList<DataSiswa>)
    : RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder>() {
    class SiswaViewHolder(view: View ): RecyclerView.ViewHolder(view) {
        val nis = view.findViewById<TextView>(R.id.txtDataNis)
        val nama = view.findViewById<TextView>(R.id.txtDataNama)
        val jekel = view.findViewById<TextView>(R.id.txtDataJK)
        val edit = view.findViewById<TextView>(R.id.btnEdit)
        val hapus = view.findViewById<TextView>(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_siswa_adapter,parent,false)
        return SiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiswaViewHolder, position: Int) {
        holder.nis.text = dataset[position].Nis
        holder.nama.text = dataset[position].Nama
        holder.jekel.text = dataset[position].Jekel
        holder.hapus.setOnClickListener {
            dataset.removeAt(position)
            notifyItemRangeRemoved(position, dataset.size)
            notifyDataSetChanged()
        }
        holder.edit.setOnClickListener {
            val context = holder.itemView.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_siswa, null)
            val eNis = view.findViewById<TextView>(R.id.editNIS_Update)
            val eNama = view.findViewById<TextView>(R.id.editNama_Update)
            val ejLakiLaki = view.findViewById<RadioButton>(R.id.rbLakiLaki_Update)
            val ejPerempuan = view.findViewById<RadioButton>(R.id.rbPerempuan_Update)
            eNis.text = dataset[position].Nis
            eNama.text = dataset[position].Nama
            var jk = "Laki-Laki"
            if ( dataset[position].Jekel == jk ) {
                ejLakiLaki.isSelected
            } else {
                ejPerempuan.isSelected
            }

            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Edit Data")
                .setView(view)
                .setPositiveButton("Update", DialogInterface.OnClickListener{ dialogInterface, i ->
                    dataset[position].Nis = eNis.text.toString()
                    dataset[position].Nama = eNama.text.toString()
                    notifyDataSetChanged()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener{ dialogInterface, i -> })
            alertDialog.create().show()
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}