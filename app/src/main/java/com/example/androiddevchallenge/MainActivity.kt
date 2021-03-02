/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.domain.PUPPIES
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.view.DetailPuppy
import com.example.androiddevchallenge.view.PuppyListItem

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp() {
    Scaffold(
        topBar = { TopBar() }
    ) {
        // We save the scrolling position with this state
        var detailPuppy by remember { mutableStateOf<Puppy?>(null) }
        val scrollState = rememberLazyListState()
        AnimatedVisibility(
            visible = detailPuppy != null,
            enter = slideInVertically(
                initialOffsetY = { fullHeight ->
                    fullHeight * 2
                }
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight ->
                    fullHeight * 2
                }
            )
        ) {
            DetailPuppy(
                detailPuppy,
                modifier = Modifier.clickable {
                    detailPuppy = null
                }
            )
        }
        AnimatedVisibility(
            visible = detailPuppy == null,
            enter = slideInVertically(
                initialOffsetY = { fullHeight ->
                    -fullHeight
                }
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight ->
                    -fullHeight
                }
            )
        ) {
            LazyColumn(state = scrollState) {
                items(PUPPIES.size) {
                    PuppyListItem(
                        PUPPIES[it],
                        modifier = Modifier.clickable {
                            Log.d("MainActivity", "Clicked event $it")
                            detailPuppy = PUPPIES[it]
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Puppy Pals") },
        navigationIcon = {
            Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
        }
    )
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
