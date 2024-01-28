package com.example.bucketlist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Bucket::class],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {
    abstract fun bucketDao(): Bucket_Dao

}