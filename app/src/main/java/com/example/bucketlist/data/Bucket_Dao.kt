package com.example.bucketlist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bucketlist.data.Bucket
import kotlinx.coroutines.flow.Flow

@Dao
abstract class Bucket_Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addBucket(bucketEntity:Bucket)

    @Query("Select * from Bucket ")
    abstract fun getBucket(): Flow<List<Bucket>>

    @Update()
    abstract suspend fun updateBucket(bucketEntity:Bucket)

    @Delete()
    abstract suspend fun deleteBucket(bucketEntity:Bucket)

    @Query("Select * from Bucket where id=:id ")
    abstract fun getBucketById(id:Long): Flow<Bucket>

}