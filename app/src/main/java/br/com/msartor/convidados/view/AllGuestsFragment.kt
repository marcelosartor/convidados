package br.com.msartor.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.msartor.convidados.databinding.FragmentAllGuestsBinding
import br.com.msartor.convidados.view.adapter.GuestsAdapter
import br.com.msartor.convidados.view.listener.OnGuestListener
import br.com.msartor.convidados.viewmodel.AllGuestsViewModel


class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = GuestsAdapter()
    private lateinit var viewModel: AllGuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AllGuestsViewModel::class.java]

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // layout recycler view
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        // adapter
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id:Int) {
                Toast.makeText(context,"Alow, Fui Clicado ${id}", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id:Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }

        }
        adapter.attachListener(listener)

        viewModel.getAll()

        observe()

        return root
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        viewModel.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun observe(){
        viewModel.guests.observe(viewLifecycleOwner){
            adapter.updateGuests(it)
            // Lista de convidados
        }
    }
}