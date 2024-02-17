package com.example.viewhomework.data.model.entityDB

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "notes",
    indices = [Index("id")]
)
data class NotesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "text_case") val textCase: String,
    @ColumnInfo(name = "deadline") val deadLine: String,
    @ColumnInfo(name = "importance") val importance: String
): Parcelable
