package com.example.newsapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.DataSource.Data
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import kotlin.coroutines.coroutineContext

class AdapterNews(var dataSource: List<Data>,var comeFromFavorite:Boolean): RecyclerView.Adapter<AdapterNews.ViewHolderUser>()
{

    var statusButton = false

    class ViewHolderUser(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.new_image)
        val textTitle: TextView = view.findViewById(R.id.new_title)
        val textDescription: TextView = view.findViewById(R.id.new_description)
        val textCategory: TextView = view.findViewById(R.id.new_category)
        val btnFavorite: ImageButton = view.findViewById(R.id.save_to_favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.view_news,parent,false)
        return ViewHolderUser(layout)
    }

    override fun onBindViewHolder(holder: ViewHolderUser, position: Int) {
        if(dataSource!= null)
        {
            val new = dataSource.get(position)
            holder.textTitle.text = "${new.title}"
            holder.textDescription.text = new.description
            holder.textCategory.text = "${new.published_at}"

            if(new.image!= null){
            holder.image.load(new.image as String)
            }
            else{
                holder.image.setImageResource(R.drawable.newspaper)
            }
            statusButton = MainActivity.ResponseData.myDataFavoritesPosition[position]
            if(comeFromFavorite){
                holder.btnFavorite.setImageResource(R.drawable.ic_remove)
            }
            else{
            when(statusButton){
                true -> holder.btnFavorite.setImageResource(R.drawable.ic_star)
                else -> holder.btnFavorite.setImageResource(R.drawable.ic_star_outline)
            }}
            holder.btnFavorite.setOnClickListener{
                if(comeFromFavorite){
                    holder.btnFavorite.setImageResource(R.drawable.ic_star_outline)
                    MainActivity.ResponseData.myDataFavoritesPosition[position] = false
                    //saveOrNotFavorite(holder,position,holder.itemView.context)
                   //notifyDataSetChanged()
                }
                else
                {
                    saveOrNotFavorite(holder,position,holder.itemView.context)
                }
            }
        }


    }
    private fun saveOrNotFavorite(holder:AdapterNews.ViewHolderUser,position:Int,context:Context){
        statusButton = !statusButton
        if(statusButton){
            Toast.makeText(context,"saved to favorites",Toast.LENGTH_LONG).show()
            holder.btnFavorite.setImageResource(R.drawable.ic_star)
            MainActivity.ResponseData.dataFavorites?.add(dataSource[position])
            MainActivity.ResponseData.myDataFavoritesPosition[position] = true
        }
        else{
            Toast.makeText(context,"removed from favorites",Toast.LENGTH_LONG).show()
            holder.btnFavorite.setImageResource(R.drawable.ic_star_outline)
            MainActivity.ResponseData.myDataFavoritesPosition[position] = false
        }
    }
    override fun getItemCount():Int {
        if (dataSource != null) {
            return dataSource.size
        } else {
            return 0
        }
    }
}