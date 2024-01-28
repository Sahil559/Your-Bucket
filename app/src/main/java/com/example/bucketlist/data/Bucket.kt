package com.example.bucketlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Bucket")
data class Bucket(
    @PrimaryKey(autoGenerate = true)
    val id: Long= 0L,
    @ColumnInfo(name="Bucket-title")
    val title: String="",
    @ColumnInfo(name="Bucket-desc")
    val desc: String=""
)
