package com.thenexprojects.coinapplication102.presentation.view.coin_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import com.thenexprojects.coinapplication102.R
import com.thenexprojects.coinapplication102.common.Resource
import com.thenexprojects.coinapplication102.data.model.CoinByIdResponse
import com.thenexprojects.coinapplication102.databinding.FragmentCoinDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.GlobalScope.coroutineContext
import okhttp3.*
import java.io.IOException
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {


    private lateinit var binding: FragmentCoinDetailBinding
    private val viewModel by viewModels<CoinDetailViewModel>()
    private val args by navArgs<CoinDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserve()
    }

    private fun addObserve() {
        viewModel.getCoinById(args.coidId).observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading->{

                }
                is Resource.Success->{
                    val body=it.data
                    binding.name.text=body.name
                    binding.symbol.text=body.symbol
                    binding.type.text=body.type
                    if(body.description.trim().isNotEmpty()) {
                        binding.description.text=     body.description
                    }else{
                        binding.description.visibility=GONE
                        binding.des.visibility=GONE
                    }

                    Picasso.get().load(body.logo).into(binding.logo)



                    getVideo(it.data,binding.youtubePlayerView)

                }
                is Resource.Error->{

                }


            }




        }
    }


    private fun getVideo(it:CoinByIdResponse, y:YouTubePlayerView) {

        val videoUrl = it.links.youtube[0]
        val videoId = videoUrl.substringAfter("v=")
            .substringBefore("&")

        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)

                youTubePlayer.cueVideo(videoId, 0f)
            }
        })
    }


}