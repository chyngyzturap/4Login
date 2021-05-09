package com.pharos.forlogin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pharos.forlogin.R
import com.pharos.forlogin.data.responses.Rooms
import com.pharos.forlogin.data.responses.RoomsResponse
import com.pharos.forlogin.databinding.ListRoomsBinding

class HomeAdapter()
    : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var _binding: ListRoomsBinding? = null
    private var roomsList: MutableList<Rooms> = mutableListOf()

    public fun addData(data: MutableList<Rooms>){
        roomsList = data
        notifyDataSetChanged()
    }

    public fun clearData(){
        roomsList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        _binding = ListRoomsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return roomsList.size
    }

    inner class HomeViewHolder(private val binding: ListRoomsBinding): RecyclerView.ViewHolder(binding.root){

        fun onBind(position: Int) {
            binding.textViewTitle.text = roomsList[position].title
//            binding.textViewStatus.text = roomsList[position].status.toString()

            if (roomsList[position].status) {
                binding.textViewStatus.text = "Free"
                binding.textViewStatus.setBackgroundResource(R.drawable.bg_free_room_status)
            } else {
                binding.textViewStatus.text = "Booked"
                binding.textViewStatus.setBackgroundResource(R.drawable.bg_booked_room_status)
            }

        }
    }
}