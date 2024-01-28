package com.example.bucketlist.data

import kotlinx.coroutines.flow.Flow

class Repository(private val bucketDao: Bucket_Dao) {

        suspend fun addBucket(bucket: Bucket){
            bucketDao.addBucket(bucket)
        }

        fun getBucket(): Flow<List<Bucket>> = bucketDao.getBucket()


        suspend fun updateBucket(bucket:Bucket){
            bucketDao.updateBucket(bucket)
        }

        suspend fun deleteBucket(bucket: Bucket){
            bucketDao.deleteBucket(bucket)
        }

    fun getBucketById(id:Long) :Flow<Bucket> {
        return bucketDao.getBucketById(id)
    }


}
