package br.com.msartor.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import br.com.msartor.convidados.model.GuestModel
import br.com.msartor.convidados.repository.GuestRepository


class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application)
    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }




}