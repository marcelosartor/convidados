package br.com.msartor.convidados.repository

import android.content.Context
import android.util.Log
import br.com.msartor.convidados.model.GuestModel

class GuestRepository private  constructor(context: Context){
    private  val guestDataBase = GuestDataBase.getDatabase(context).guestDao()
    companion object {
        private lateinit var repository: GuestRepository
        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest:GuestModel):Boolean{
        return try {
            guestDataBase.insert(guest) > 0
        }catch (error: Exception){
           false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            return guestDataBase.update(guest) > 0
        }catch (error: Exception){
            false
        }
    }

    fun delete(id: Int) {
        return try{
            val guest = get(id)
            guestDataBase.delete(guest)
        }catch (error: Exception){
            Unit
        }
    }

    fun get(guestId: Int):GuestModel {
        return guestDataBase.get(guestId)
    }

    fun getAll(): List<GuestModel>{
        var list = mutableListOf<GuestModel>()
        try {
            list = guestDataBase.getAll().toMutableList()
            return list
        }catch (error: Exception){
            Log.e("TESTE", error.message.toString())
            return list
        }
    }

    fun getPresent(): List<GuestModel>{
        var list = mutableListOf<GuestModel>()
        try {
           list = guestDataBase.getPresent().toMutableList()
            return list
        }catch (error: Exception){
            return list
        }
    }

    fun getAbsent(): List<GuestModel>{
        var list = mutableListOf<GuestModel>()
        try {
            list = guestDataBase.getAbsent().toMutableList()
            return list
        }catch (error: Exception){
            return list
        }
    }



}