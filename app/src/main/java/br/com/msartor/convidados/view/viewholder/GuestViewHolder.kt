package br.com.msartor.convidados.view.viewholder

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import br.com.msartor.convidados.databinding.RowGuestBinding
import br.com.msartor.convidados.model.GuestModel
import br.com.msartor.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {
    fun bind(guestModel: GuestModel) {
        bind.textName.text = guestModel.name

        bind.textName.setOnClickListener {
            listener.onClick(guestModel.id)
        }

        bind.textName.setOnLongClickListener(View.OnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim") { dialog, which -> listener.onDelete(guestModel.id) }
                .create()
                .show()

            true
        })

    }

}