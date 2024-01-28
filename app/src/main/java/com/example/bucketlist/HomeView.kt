

package com.example.bucketlist

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.SwipeToDismiss
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bucketlist.R.color.lite_blue
import com.example.bucketlist.R.color.purple_500
import com.example.bucketlist.data.Bucket

@SuppressLint("SuspiciousIndentation", "ResourceAsColor")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: BucketViewModel
){

    Scaffold(
        topBar= {AppBarView(title = "Your BUCKET!!")},
        containerColor = Color.Cyan,
        contentColor = Color.White,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(30.dp),
                backgroundColor = Color(purple_500),
                onClick = {
                    navController.navigate(Screen.EditScreen.route +"/0L")
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ){

        val bucketList= viewModel.getBuckets.collectAsState(initial = listOf())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {

                items(bucketList.value, key = { bucket -> bucket.id }) { bucket ->
                    val dismissState = rememberDismissState(confirmStateChange = {
                        if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                            viewModel.deleteBucket(bucket)
                        }
                        true
                    }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(
                            androidx.compose.material.DismissDirection.EndToStart,
                            androidx.compose.material.DismissDirection.StartToEnd
                        ),
                        background = {},
                        dismissContent = {
                            BucketItem(bucket = bucket) {
                                val id = bucket.id
                                navController.navigate(Screen.EditScreen.route + "/$id")
                            }
                        },
                    )
                }
            }
        }
    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun BucketItem(bucket: Bucket, onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onClick()
        },
        elevation= 10.dp,
        backgroundColor = Color(purple_500)
    ) {
        Column(modifier = Modifier.padding(16.dp)){
            Text(text = bucket.title, fontWeight = FontWeight.ExtraBold)
            Text(text = bucket.desc)
        }
    }
}