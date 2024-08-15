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
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE,presence )
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME,guest.name )
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true
        }catch (error: Exception){
           false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val select = """${DataBaseConstants.GUEST.COLUMNS.ID}  = ? """
            val args = arrayOf(guest.id.toString())
            val contentValues = ContentValues()
            val presence = if (guest.presence) 1 else 0
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, presence)

            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, select, args)
            true
        }catch (error: Exception){
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val select = """${DataBaseConstants.GUEST.COLUMNS.ID}  = ? """
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, select, args)
            true
        }catch (error: Exception){
            false
        }
    }

    fun getAll(): List<GuestModel>{
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )


            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME, projection, null, null, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    val guest = GuestModel(id, name, presence == 1)
                    list.add(guest)
                }
            }

            cursor.close()

            return list
        }catch (error: Exception){
            return list
        }
    }

    fun getPresent(): List<GuestModel>{
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
            val selection = """ ${DataBaseConstants.GUEST.COLUMNS.PRESENCE} = ? """
            val args = arrayOf("1")

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    val guest = GuestModel(id, name, presence == 1)
                    list.add(guest)
                }
            }

            cursor.close()

            return list
        }catch (error: Exception){
            return list
        }
    }

    fun getAbsent(): List<GuestModel>{
        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDataBase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
            val selection = """ ${DataBaseConstants.GUEST.COLUMNS.PRESENCE} != ? """
            val args = arrayOf("1")

            val cursor = db.query(DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    val guest = GuestModel(id, name, presence == 1)
                    list.add(guest)
                }
            }

            cursor.close()

            return list
        }catch (error: Exception){
            return list
        }
    }
}