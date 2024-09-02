package br.com.msartor.convidados.repository

import androidx.room.TypeConverter

class TypeConverterBoolean {
    @TypeConverter
    fun fromBoolean(value: Boolean): Int {
        return if (value) 1 else 0
    }

    @TypeConverter
    fun toBoolean(value: Int): Boolean {
        return value != 0
    }
}