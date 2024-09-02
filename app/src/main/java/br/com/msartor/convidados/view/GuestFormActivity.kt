package br.com.msartor.convidados.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.msartor.convidados.R
import br.com.msartor.convidados.constants.DataBaseConstants
import br.com.msartor.convidados.databinding.ActivityGuestFormBinding
import br.com.msartor.convidados.model.GuestModel
import br.com.msartor.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId:Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guest_form)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresente.isChecked = true

        observe()


        loadData()



    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.button_save){
            val name = binding.editName.text.toString()
            val presence = binding.radioPresente.isChecked
            val guest = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }
            viewModel.save(guest)

            finish()

        }
    }


    fun loadData(){
        val bundle = intent.extras
        if(bundle != null){
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }


    private fun observe() {
        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence)
                binding.radioPresente.isChecked = true
            else
                binding.radioAusente.isChecked = true

        })

        viewModel.saveGuest.observe(this, Observer {
            if (it!="") {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            }
            finish()
        })
    }
}