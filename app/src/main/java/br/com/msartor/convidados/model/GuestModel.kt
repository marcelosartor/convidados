package br.com.msartor.convidados.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.msartor.convidados.constants.DataBaseConstants
/*
@Entity(tableName = DataBaseConstants.GUEST.TABLE_NAME)
class GuestModel() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "presence")
    var _presence: Int = 0

}
 */

@Entity(tableName = "guest")
data class GuestModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "presence")
    var presence: Boolean = false // Armazena como INTEGER no banco

) {}
