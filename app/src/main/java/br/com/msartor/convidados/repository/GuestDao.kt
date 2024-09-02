package br.com.msartor.convidados.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.msartor.convidados.constants.DataBaseConstants
import br.com.msartor.convidados.model.GuestModel

@Dao
interface GuestDao {
    @Insert
    fun insert(guest: GuestModel): Long
    @Update
    fun update(guest: GuestModel): Int
    @Delete
    fun delete(guest: GuestModel)
    @Query("SELECT * FROM ${DataBaseConstants.GUEST.TABLE_NAME} WHERE id = :guestId")
    fun get(guestId: Int):GuestModel
    @Query("SELECT * FROM ${DataBaseConstants.GUEST.TABLE_NAME}")
    fun getAll(): List<GuestModel>
    @Query("SELECT * FROM ${DataBaseConstants.GUEST.TABLE_NAME} WHERE presence = 1")
    fun getPresent(): List<GuestModel>
    @Query("SELECT * FROM ${DataBaseConstants.GUEST.TABLE_NAME} WHERE presence = 0")
    fun getAbsent(): List<GuestModel>
}