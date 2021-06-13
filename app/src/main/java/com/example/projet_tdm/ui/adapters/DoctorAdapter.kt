package com.example.projet_tdm.ui.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Doctor
import com.example.projet_tdm.ui.medcinDetails
import com.example.projet_tdm.url

class DoctorAdapter(val context: Context) :
    RecyclerView.Adapter<DoctorViewHolder>() {

    var data = listOf<Doctor>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        return DoctorViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.doctor_layout, parent, false)
        )

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {

        holder.name.text = data[position].name
        holder.lastName.text = data[position].lastName
        holder.phone.text = "Num : "+data[position].phone
        Glide.with(context).load(url +data[position].image)
            .apply(
                RequestOptions().placeholder(R.drawable.placeholder
            ))
            .into(holder.img)

        //holder.img.setImageResource(data[position].image)

        holder.phone.setOnClickListener(View.OnClickListener{
            val uri = Uri.parse("tel:021212121")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        })

        holder.itemView.setOnClickListener(View.OnClickListener{
            val intent = Intent(context, medcinDetails::class.java)
            intent.putExtra("Dr",data[position])
            context.startActivity(intent)
        })

    }

    fun setListDoctors(list: List<Doctor>) {
        data = list
        notifyDataSetChanged()

    }

}


class DoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val img = view.findViewById<ImageView>(R.id.doctorImage)
    val name = view.findViewById<TextView>(R.id.viewName)
    val lastName = view.findViewById<TextView>(R.id.viewLastName)
    val phone = view.findViewById<TextView>(R.id.viewPhone)



}
