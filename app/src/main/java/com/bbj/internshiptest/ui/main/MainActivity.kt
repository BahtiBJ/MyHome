package com.bbj.internshiptest.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.bbj.internshiptest.R
import com.bbj.internshiptest.ui.main.model.MainEvent
import com.bbj.internshiptest.ui.main.model.MainState
import com.bbj.internshiptest.ui.model.UICamera
import com.bbj.internshiptest.ui.model.UIDoor
import com.bbj.internshiptest.ui.screen.camera.CameraItem
import com.bbj.internshiptest.ui.screen.door.DoorItem
import com.bbj.internshiptest.ui.screen.home.HomeScreen
import com.bbj.internshiptest.ui.theme.BlueF4
import com.bbj.internshiptest.ui.theme.InternshipTestTheme
import com.bbj.internshiptest.ui.theme.Typography
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[ViewModelMain::class.java]

        setContent {
            InternshipTestTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(Color.Black)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModelMain: ViewModelMain) {
    val mainState = remember {
        viewModelMain.mainState
    }
    val sectionState = remember {
        viewModelMain.sectionState
    }
    Column {
        HomeScreen(sectionState.value) {
            viewModelMain.obtainEvent(MainEvent.ChangeSectionEvent(it))
        }
        when (mainState.value) {
            is MainState.Initial -> {
                viewModelMain.obtainEvent(MainEvent.LoadData(sectionState.value))
            }

            is MainState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center ) {
                    CircularProgressIndicator(color = BlueF4,modifier = Modifier.size(80.dp),
                        strokeWidth = 10.dp)
                }
            }
            is MainState.Error -> {
                Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()) {
                    Column {
                        Text(
                            stringResource(R.string.error), style = Typography.titleMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            stringResource(R.string.try_again), style = Typography.displayMedium,
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .align(Alignment.CenterHorizontally)
                                .clickable {
                                    viewModelMain.obtainEvent(MainEvent.LoadData(sectionState.value))
                                }
                        )
                    }
                }
            }

            is MainState.DoorSection -> {
                DoorsList(doors = (mainState.value as MainState.DoorSection).doorList,
                    onEditClick = {
                        viewModelMain.obtainEvent(MainEvent.ChangeDoorNameEvent(it))
                    },
                    onFavoriteClick = {
                        viewModelMain.obtainEvent(MainEvent.AddToFavoriteDoorEvent(it))
                    })
            }

            is MainState.CameraSection -> {
                CamerasList(cameras = (mainState.value as MainState.CameraSection).cameraList){
                    viewModelMain.obtainEvent(MainEvent.AddToFavoriteCameraEvent(it))
                }
            }
        }
    }
}

@Composable
fun CamerasList(cameras: List<UICamera>,onFavoriteClick : (UICamera) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        var previousRoom: String? = null
        LazyColumn() {
            item() {
                Spacer(Modifier.size(15.dp))
            }
            items(cameras.size) {
                val room = cameras[it].room
                room?.let {
                    if (previousRoom != it) {
                        previousRoom = it
                        Text(
                            text = it, style = Typography.titleSmall,
                            modifier = Modifier.padding(horizontal = 21.dp, vertical = 10.dp)
                        )
                    }
                }
                CameraItem(camera = cameras[it]) {
                    onFavoriteClick(it)
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun DoorsList(doors: List<UIDoor>,onEditClick : (UIDoor) -> Unit, onFavoriteClick: (UIDoor) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        var previousRoom: String? = null
        LazyColumn() {
            item() {
                Spacer(Modifier.size(15.dp))
            }
            items(doors.size) {
                val room = doors[it].room
                room?.let {
                    if (previousRoom != it) {
                        previousRoom = it
                        Text(
                            text = it, style = Typography.titleSmall,
                            modifier = Modifier.padding(horizontal = 21.dp, vertical = 10.dp)
                        )
                    }
                }
                DoorItem(door = doors[it], onEditClick = {
                    onEditClick(it)
                }, onFavoriteClick = {
                    onFavoriteClick(it)
                })
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    InternshipTestTheme {
//        Greeting(name = "s")
//    }
//}