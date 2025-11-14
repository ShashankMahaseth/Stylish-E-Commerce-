package com.example.stylishe_commerceapp.data.local.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.stylishe_commerceapp.data.local.Conveter.StringListConverter
import com.example.stylishe_commerceapp.data.local.Dao.FavoriteDao
import com.example.stylishe_commerceapp.data.remote.Product


@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false

)

@TypeConverters(StringListConverter::class)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao
}