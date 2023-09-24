package com.production.quizharrypotterapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.production.quizharrypotterapp.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Instructions(navController: NavController) {
    Scaffold {
        InfoScreen(navController = navController)
    }
}

@Composable
fun InfoScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = com.production.quizharrypotterapp.R.drawable.fondo_pergamino),
            contentDescription = "Pergamino",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(all = 10.dp)
        ) {
            Text(
                text = stringResource(id = com.production.quizharrypotterapp.R.string.instruction),
                fontSize = 55.sp
            )
            Spacer(modifier = Modifier.size(25.dp))
            Text(
                text = stringResource(com.production.quizharrypotterapp.R.string.info),
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.size(25.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier.size(width = 140.dp, height = 50.dp),
                    onClick = { navController.navigate(AppScreens.EnterScreen.route) })
                {
                    Text(text = "Back", fontSize = 35.sp)
                }
            }
        }
    }
}