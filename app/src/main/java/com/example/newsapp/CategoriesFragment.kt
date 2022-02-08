package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.newsapp.Adapters.AdapterCategory
import com.example.newsapp.Adapters.AdapterNews
import com.example.newsapp.DataSource.Data
import com.example.newsapp.Network.OverviewViewModel
import com.example.newsapp.Network.OverviewViewModelFactory
import com.example.newsapp.Repository.Repository
import com.example.newsapp.databinding.FragmentCategoriesBinding

const val FAVORITES = "favorites"
class CategoriesFragment : Fragment() {

    object CategoriesNames{
        val categories = listOf("general","business","entertainment",
            "health","science","sports","technology")

    }
    var _binding: FragmentCategoriesBinding? = null
    val binding get() = _binding!!
    private val viewModel: OverviewViewModel by viewModels()
    lateinit var data:List<Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.categoriesRecycle.adapter = AdapterCategory(CategoriesNames.categories)
            binding.favoriteButton.setOnClickListener{
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToNewsFragment(FAVORITES)
            view.findNavController().navigate(action)
        }

    }
}