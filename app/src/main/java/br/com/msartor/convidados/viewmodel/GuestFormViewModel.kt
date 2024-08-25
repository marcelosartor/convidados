package br.com.msartor.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.msartor.convidados.model.GuestModel
import br.com.msartor.convidados.repository.GuestRepository


class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _SaveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _SaveGuest

    fun save(guest: GuestModel) {
        if(guest.id == 0) insert(guest) else update(guest)
    }
    fun insert(guest: GuestModel) {
        _SaveGuest.value = if(repository.insert(guest)) "Inserido com sucesso" else "Falha ao inserir"
    }

    fun update(guest: GuestModel) {
        _SaveGuest.value = if(repository.update(guest)) "Atualizado com sucesso" else "Falha ao atualizar"
    }

    fun get(guestId: Int) {
        guestModel.value = repository.get(guestId)
    }


}