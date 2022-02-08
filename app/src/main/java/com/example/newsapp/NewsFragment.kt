package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.newsapp.Adapters.AdapterCategory
import com.example.newsapp.Adapters.AdapterNews
import com.example.newsapp.DataSource.Data
import com.example.newsapp.databinding.FragmentCategoriesBinding
import com.example.newsapp.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {

    private val args by navArgs<NewsFragmentArgs>()
    lateinit var _binding: FragmentNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseDirections()
        }

    private fun chooseDirections() {
        val directions = args.categoryOfNew
        when(directions){
            FAVORITES -> isFavorite()
            else -> {
                val data = MainActivity.ResponseData.myData?.data?.filter { it.category.equals(args.categoryOfNew) }
                if (haveData(data)) {
                    _binding.newsRecycle.adapter = AdapterNews(data!!,false)}
            }
        }
    }
    private fun isFavorite(){
        val data = MainActivity.ResponseData.dataFavorites
        if (data != null){
            _binding.newsRecycle.adapter = AdapterNews(data!!,true)
            _binding.txtStatus.visibility = View.GONE
        }
       else{
            noDataFavorites()
       }
    }
    private fun noDataFavorites(){
        _binding.txtStatus.text = "no data in favorites"
        _binding.txtStatus.visibility = View.VISIBLE
    }
    private fun haveData(data:List<Data>?):Boolean{
        if(data == null)
        {
            _binding.txtStatus.text = "no data in ${args.categoryOfNew} category"
            _binding.txtStatus.visibility = View.VISIBLE
            return false
        }
        else{
            if(data.size >= 1){
                _binding.txtStatus.visibility = View.GONE
            }
            else{
                _binding.txtStatus.text = "no data in ${args.categoryOfNew} category"
                _binding.txtStatus.visibility = View.VISIBLE
            }
            return true
        }

    }
}