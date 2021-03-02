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
package com.example.androiddevchallenge.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.domain.Puppy
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PuppyListItem(
    puppy: Puppy,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = 8.dp
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        backgroundColor = backgroundColor,
        shape = MaterialTheme.shapes.medium,
        elevation = elevation
    ) {
        Row() {
            CoilImage(
                data = puppy.image,
                contentDescription = puppy.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp),
                error = { err ->
                    Log.e("MainActivity", "Image loading failed $err")
                }
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text("Hi I'm ${puppy.name}", style = MaterialTheme.typography.h5)
                Text("I am a ${puppy.breed}", style = MaterialTheme.typography.subtitle1)
                PuppyRating(puppy = puppy, modifier = Modifier.padding(5.dp))
            }
        }
    }
}
