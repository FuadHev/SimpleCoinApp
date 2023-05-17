package com.thenexprojects.coinapplication102.presentation.view.coin_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thenexprojects.coinapplication102.R
import com.thenexprojects.coinapplication102.common.Resource
import com.thenexprojects.coinapplication102.databinding.FragmentCoinListBinding
import com.thenexprojects.coinapplication102.presentation.adapter.CoinClickListenerInterface
import com.thenexprojects.coinapplication102.presentation.adapter.CoinsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment() {
    private val viewModel by viewModels<CoinListViewModel>()


    private lateinit var binding: FragmentCoinListBinding
    private val adapter by lazy {
        CoinsAdapter(object : CoinClickListenerInterface {
            override fun coinClickListener(id: String) {
                findNavController().navigate(CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(id))
            }

        }, emptyList())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCoinListBinding.inflate(inflater, container, false)
        addObservers()

        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter

        return binding.root
    }

    private fun addObservers() {
        viewModel.getCoins().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    //Show progress dialog
                }
                is Resource.Success -> {
                    //hide progress dialog
                    val data = it.data
                    Log.e("data", data.toString())
                    adapter.updatCoins(data)
                }
                is Resource.Error -> {
                    //hide progress dialog
                    val error = it.exception

                    Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}