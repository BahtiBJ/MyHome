package com.bbj.internshiptest.ui.screen.door

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bbj.internshiptest.R
import com.bbj.internshiptest.ui.common.DragAnchors
import com.bbj.internshiptest.ui.model.UIDoor
import com.bbj.internshiptest.ui.theme.GrayF6
import com.bbj.internshiptest.ui.theme.InternshipTestTheme
import com.bbj.internshiptest.ui.theme.Typography


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoorItem(
    door: UIDoor,
    onEditClick: (UIDoor) -> Unit,
    onFavoriteClick: (UIDoor) -> Unit
) {
    val draggableState = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { 0.5f },
            animationSpec = tween(),
        ).apply {
            updateAnchors(
                DraggableAnchors {
                    DragAnchors.Start at 0f
                    DragAnchors.End at -300f
                }
            )
        }
    }
    val roundedCornerShape = RoundedCornerShape(16.dp)
    val localFocusManager = LocalFocusManager.current
    var doorName by remember {
        mutableStateOf(door.name)
    }
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier
            .padding(horizontal = 21.dp)
    ) {
        Row(Modifier.padding(end = 21.dp)) {
            Image(
                painterResource(id = R.drawable.edit), contentDescription = null,
                Modifier
                    .size(36.dp)
                    .clickable {
                        onEditClick(door.apply {
                            if (isLocked)
                                isLocked = !isLocked
                        })
                    }
            )
            Spacer(modifier = Modifier.size(5.dp, 0.dp))
            Image(
                painterResource(id = R.drawable.star_shape), contentDescription = null,
                Modifier
                    .size(36.dp)
                    .clickable {
                        onFavoriteClick(door.apply {
                            favorites = !favorites
                        })
                    }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        x = draggableState
                            .requireOffset()
                            .toInt(),
                        y = 0
                    )
                }
                .anchoredDraggable(
                    draggableState, orientation = Orientation.Horizontal
                )
                .background(GrayF6)
                .clip(roundedCornerShape)
                .padding(1.dp)
                .shadow(1.dp, roundedCornerShape)
        ) {
            DoorImage(door.snapshot, door.favorites)
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 16.dp, end = 24.dp, top = 20.dp, bottom = 20.dp)
            ) {
                Column() {
                    BasicTextField(
                        doorName,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            onEditClick(door.apply {
                                if (name != doorName) {
                                    name = doorName
                                }
                                localFocusManager.clearFocus()
                            })
                        }),
                        singleLine = true,
                        onValueChange = {
                            doorName = it
                        },
                        textStyle = Typography.labelMedium,
                        modifier = Modifier
                            .clickable(enabled = !door.isLocked) {}
                    )
                    Text(
                        text = stringResource(id = R.string.status), style = Typography.labelSmall
                    )
                }
                Box(Modifier.matchParentSize(), contentAlignment = Alignment.CenterEnd) {
                    Image(
                        painterResource(
                            id =
                            if (door.isLocked) R.drawable.lockon else R.drawable.lockoff
                        ), contentDescription = null,
                        Modifier
                            .size(24.dp)
                            .clickable {
                                onEditClick(door.apply {
                                    isLocked = !isLocked
                                    if (name != doorName) {
                                        name = doorName
                                    }
                                    localFocusManager.clearFocus()
                                })
                            }
                    )
                }
            }
        }

    }
}

@Composable
fun DoorImage(imageUrl: String?, isFavorite: Boolean) {
    if (imageUrl != null) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(Color.White),
                modifier = Modifier.matchParentSize()
            )
            Box(
                Modifier
                    .matchParentSize()
                    .background(Color(0x80000000)), contentAlignment = Alignment.TopEnd
            ) {
                if (isFavorite) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier
                            .size(44.dp)
                            .padding(10.dp)
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = null,
                Modifier
                    .size(50.dp)
                    .padding(5.dp),
                colorFilter = ColorFilter.tint(Color.White),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InternshipTestTheme {
    }
}