package br.com.msartor.convidados.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.msartor.convidados.R
import br.com.msartor.convidados.databinding.ActivityGuestFormBinding
import br.com.msartor.convidados.model.GuestModel
import br.com.msartor.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guest_form)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresente.isChecked = true
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.button_save){
            val name = binding.editName.text.toString()
            val presence = binding.radioPresente.isChecked
            val guest = GuestModel(0, name, presence)
            viewModel.insert(guest)

        }
    }
}