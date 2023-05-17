package com.thenexprojects.coinapplication102.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenexprojects.coinapplication102.databinding.CoinItemBinding
import com.thenexprojects.coinapplication102.domain.model.Coin

class CoinsAdapter(private val coinClickListenerInterface: CoinClickListenerInterface,private var coinList:List<Coin>):RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {


    inner class ViewHolder(val view:CoinItemBinding):RecyclerView.ViewHolder(view.root){

        fun collapseExpandedView(){
            view.info.visibility=View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = CoinItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }
    fun updatCoins(newcoinList:List<Coin>){
        this.coinList=newcoinList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin=coinList[position]
        val b=holder.view

        val isExpandable:Boolean=coin.isExpandable
        b.info.visibility=if (isExpandable) View.VISIBLE  else View.GONE
        b.name.text=coin.name
        b.coinItem.setOnClickListener {
            coinClickListenerInterface.coinClickListener(coin.id)
        }

        b.isActive.text=coin.is_active.toString()
        b.symbol.text=coin.symbol
        b.type.text=coin.type

        b.imgInfo.setOnClickListener {

            isAnyItemExpanded(position)
            coin.isExpandable=!coin.isExpandable
            notifyItemChanged(position,Unit)
        }

    }
    private fun isAnyItemExpanded(position: Int){
        val temp=coinList.indexOfFirst {
            it.isExpandable
        }
        if(temp>=0&&temp!=position){
            coinList[temp].isExpandable=false
            notifyItemChanged(temp,0)
        }
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {

        super.onBindViewHolder(holder, position, payloads)

        if (payloads.isNotEmpty()&&payloads[0]==0){
            holder.collapseExpandedView()
        }else{
            super.onBindViewHolder(holder, position, payloads)
        }

    }


}
interface CoinClickListenerInterface{
    fun coinClickListener(id:String)
}