package br.com.msartor.convidados.repository

import android.content.ContentValues
import android.content.Context
import br.com.msartor.convidados.constants.DataBaseConstants
import br.com.msartor.convidados.model.GuestModel

class GuestRepository private  constructor(context: Context){
    private  val guestDataBase = GuestDataBase(context)
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
            val db = guestDataBase.writableDatabase
            val contentValues = ContentValues()
            val presence = if (guest.presence) 1 else 0
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, presence)
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true
        }catch (error: Exception){
           false
        }
    }

    fun update(){}
}