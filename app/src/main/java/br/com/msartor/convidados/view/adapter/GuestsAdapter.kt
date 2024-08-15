package br.com.msartor.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import br.com.msartor.convidados.databinding.RowGuestBinding
import br.com.msartor.convidados.model.GuestModel
import br.com.msartor.convidados.view.viewholder.GuestViewHolder

class GuestsAdapter: RecyclerView.Adapter<GuestViewHolder>() {
    private var guestList: List<GuestModel> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(it: List<GuestModel>) {
        guestList = it
        notifyDataSetChanged()
    }
}
