package br.com.msartor.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.msartor.convidados.constants.DataBaseConstants
import br.com.msartor.convidados.databinding.FragmentAbsentBinding
import br.com.msartor.convidados.view.adapter.GuestsAdapter
import br.com.msartor.convidados.view.listener.OnGuestListener
import br.com.msartor.convidados.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!

    private val adapter = GuestsAdapter()
    private lateinit var viewModel: GuestsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentAbsentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // layout recycler view
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        // adapter
        binding.recyclerGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id:Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id:Int) {
                viewModel.delete(id)
                viewModel.getAbsent()
            }

        }
        adapter.attachListener(listener)

        observe()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAbsent()
    }
    fun observe(){
        viewModel.guests.observe(viewLifecycleOwner){
            adapter.updateGuests(it)
            // Lista de convidados
        }
    }
}