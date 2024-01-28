package com.example.bucketlist

import android.app.Application
import com.example.bucketlist.data.Graph

class BucketListApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}