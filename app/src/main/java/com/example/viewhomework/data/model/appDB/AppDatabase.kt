package com.example.viewhomework.data.model.appDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.viewhomework.data.model.dao.NotesDao
import com.example.viewhomework.data.model.entityDB.NotesEntity

@Database(
    version = 1,
    entities = [
        NotesEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "note_database.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}