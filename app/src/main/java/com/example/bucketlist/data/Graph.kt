package com.example.bucketlist.data

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: Database
    val bucketRepo by lazy {
        Repository(bucketDao = database.bucketDao())
    }

    fun provide(context: Context){
        database= Room.databaseBuilder(context,Database::class.java, "BucketTable.db").build()
    }

}