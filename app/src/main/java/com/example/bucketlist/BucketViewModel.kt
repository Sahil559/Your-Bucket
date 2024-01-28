package com.example.bucketlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bucketlist.data.Bucket
import com.example.bucketlist.data.Graph
import com.example.bucketlist.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BucketViewModel( private var bucketRepo: Repository= Graph.bucketRepo):ViewModel() {
    var titleState by mutableStateOf("")
    var descState by mutableStateOf("")

    fun onTitleChange(newString:String ){
        titleState=newString
    }

    fun onDescChange(newString:String ){
        descState=newString
    }

    lateinit var getBuckets: Flow<List<Bucket>>
    init{
        viewModelScope.launch {
            getBuckets= bucketRepo.getBucket()
        }
    }
    fun addBucket(bucket: Bucket){
        viewModelScope.launch(Dispatchers.IO) {
            bucketRepo.addBucket(bucket= bucket)
        }
    }
    fun updateBucket(bucket: Bucket){
        viewModelScope.launch(Dispatchers.IO) {
            bucketRepo.updateBucket(bucket= bucket)
        }
    }
    fun deleteBucket(bucket: Bucket){
        viewModelScope.launch(Dispatchers.IO) {
            bucketRepo.deleteBucket(bucket= bucket)
        }
    }
    fun getBucketByIdBucket(id:Long) :Flow<Bucket>{

           return bucketRepo.getBucketById(id)

    }
}