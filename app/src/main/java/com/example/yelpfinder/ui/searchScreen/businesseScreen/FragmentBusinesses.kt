package com.example.yelpfinder.ui.searchScreen.businesseScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.yelpfinder.databinding.FragmentBusinessesBinding
import com.example.yelpfinder.models.dataModels.BusinessesModel


class FragmentBusinesses() : Fragment() {

    var businessData : BusinessesModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            businessData = it.getParcelable(BUSINESSDATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentBusinessesBinding.inflate(inflater)
        businessData?.let {
            val adapter = BusinessesAdapter(requireContext(),it)
            binding.fragmentBusinessesRecyclerview.adapter = adapter
            binding.fragmentBusinessesRecyclerview.addItemDecoration(
                DividerItemDecoration(
                    binding.fragmentBusinessesRecyclerview.getContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        return binding.root
    }


    companion object{
        private const val BUSINESSDATA = "BusinessData"
        fun getInstance(context: Context, businessData : BusinessesModel): FragmentBusinesses{
            val fragment = FragmentBusinesses()
            val bundle = Bundle(1)
            bundle.putParcelable(BUSINESSDATA, businessData)
            fragment.arguments = bundle
            return fragment
        }
    }
}