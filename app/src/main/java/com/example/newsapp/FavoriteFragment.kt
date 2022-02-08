package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.newsapp.Adapters.AdapterNews
import com.example.newsapp.DataSource.Data
import com.example.newsapp.databinding.FragmentFavoriteBinding
import com.example.newsapp.databinding.FragmentNewsBinding

class FavoriteFragment : Fragment() {

    lateinit var _binding: FragmentFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = MainActivity.ResponseData.dataFavorites
        if (data != null)
            _binding.favoriteRecycle.adapter = AdapterNews(data!!,true)
        if(data == null)
            noDataFavorites()
    }

    private fun noDataFavorites(){
        _binding.txtStatus.text = "no data in favorites"
        _binding.txtStatus.visibility = View.VISIBLE
    }
}