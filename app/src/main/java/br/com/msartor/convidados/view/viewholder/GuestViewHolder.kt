package br.com.msartor.convidados.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.msartor.convidados.databinding.RowGuestBinding
import br.com.msartor.convidados.model.GuestModel

class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {
    fun bind(guestModel: GuestModel) {
        bind.textName.text = guestModel.name
    }

}