package br.com.msartor.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import br.com.msartor.convidados.constants.DataBaseConstants
import br.com.msartor.convidados.model.GuestModel

@Database(entities = [GuestModel::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterBoolean::class)
abstract class GuestDataBase(): RoomDatabase() {

    abstract fun guestDao(): GuestDao
    companion object{

        private lateinit var INSTANCE: GuestDataBase
        fun getDatabase(context: Context): GuestDataBase {
            if(!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .addCallback(GuestDataBaseCallback(context))
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }


}

private class GuestDataBaseCallback(
    private val context: Context
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Log.i("TESTE","Entrou no onCreate")
        // Executar o script SQL para criar o esquema
        try {
            db.execSQL("""
                CREATE TABLE ${DataBaseConstants.GUEST.TABLE_NAME}(
                    ${DataBaseConstants.GUEST.COLUMNS.ID} integer primary key autoincrement NOT NULL,
                    ${DataBaseConstants.GUEST.COLUMNS.NAME} text NOT NULL,
                    ${DataBaseConstants.GUEST.COLUMNS.PRESENCE} integer NOT NULL
                );
            """.trimIndent())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

/*
class GuestDataBase(context:Context): SQLiteOpenHelper(context,NAME,null,VERSION) {
    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE ${DataBaseConstants.GUEST.TABLE_NAME}(
                ${DataBaseConstants.GUEST.COLUMNS.ID} integer primary key autoincrement,
                ${DataBaseConstants.GUEST.COLUMNS.NAME} text,
                ${DataBaseConstants.GUEST.COLUMNS.PRESENCE} integer
            );
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //TODO("Not yet implemented")
    }
}
 */