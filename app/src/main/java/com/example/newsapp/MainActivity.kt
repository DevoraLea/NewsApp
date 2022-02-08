package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.newsapp.Adapters.AdapterCategory
import com.example.newsapp.Adapters.AdapterNews
import com.example.newsapp.DataSource.Data
import com.example.newsapp.DataSource.ItemNews
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.Network.OverviewViewModel
import com.example.newsapp.Network.OverviewViewModelFactory
import com.example.newsapp.Repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
  // val viewModel:OverviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        getData()
        setupActionBarWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || onSupportNavigateUp()
    }
    fun getData(){
        val repository = Repository()
        val viewModelFactory = OverviewViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(OverviewViewModel::class.java)
        viewModel.news.observe(this, Observer { response ->
            ResponseData.myData = response

            for(i in 0..response.data.size)
                ResponseData.myDataFavoritesPosition.add(false)
        })
    }
    private fun message(msg:String) {
        //binding.txtStatus.text = msg
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_news,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.refresh_new -> message("Testing refresh button")
            R.id.share_new -> message("Testing share button")
        }
        return super.onOptionsItemSelected(item)
    }


    object ResponseData {
     var myData:ItemNews? = null
     var myDataFavoritesPosition:MutableList<Boolean> = mutableListOf()
     var dataFavorites:MutableList<Data>? = mutableListOf()
    }
/*
    private fun refresh() {
        var listRetrofit = viewModel.news.value?.data
            if(listRetrofit != null) {
                //binding.newsRecycle.adapter = AdapterNews(listRetrofit!!)
                binding.newsRecycle.adapter = AdapterCategory(listRetrofit!!)
            }
    }*/
}