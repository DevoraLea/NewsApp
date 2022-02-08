package com.example.newsapp.Adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.CategoriesFragmentDirections
import com.example.newsapp.DataSource.Data
import com.example.newsapp.DataSource.ItemNews
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategoriesBinding

class AdapterCategory(var dataSource: List<String>): RecyclerView.Adapter<AdapterCategory.ViewHolderUser>()
{

    class ViewHolderUser(view: View): RecyclerView.ViewHolder(view){
        val btnCategory: Button = view.findViewById(R.id.btn_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.view_category,parent,false)
        return ViewHolderUser(layout)
    }

    override fun onBindViewHolder(holder: ViewHolderUser, position: Int) {
        val category = dataSource.get(position)
            holder.btnCategory.text = "${category}"
        holder.btnCategory.setOnClickListener {
            try{
           val action = CategoriesFragmentDirections.actionCategoriesFragmentToNewsFragment(category)
            holder.itemView.findNavController().navigate(action)
        }catch (ex:Exception){
            ex.printStackTrace()
                Log.e("MYAPP",ex.message.toString())
            holder.btnCategory.setBackgroundColor(Color.RED)
                holder.btnCategory.text = ex.message.toString()

        }
    }}

    override fun getItemCount():Int {
        if (dataSource != null) {
            return dataSource.size
        } else {
            return 0
        }
    }
}