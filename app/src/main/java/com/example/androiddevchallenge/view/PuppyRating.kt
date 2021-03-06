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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.ui.theme.Blue300
import com.example.androiddevchallenge.ui.theme.Green300
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.Orange300

@Composable
fun PuppyRating(puppy: Puppy, modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        RatingPill(filledIcon = Icons.Filled.Bolt, value = puppy.energy, label = "Energy", color = Orange300)
        Spacer(modifier = Modifier.width(4.dp))
        RatingPill(filledIcon = Icons.Filled.VolumeUp, value = puppy.volume, label = "Volume", color = Green300)
        Spacer(modifier = Modifier.width(4.dp))
        RatingPill(filledIcon = Icons.Filled.Chair, value = puppy.shedding, label = "Shedding", color = Blue300)
    }
}

@Composable
fun RatingPill(filledIcon: ImageVector, value: Int, label: String, color: Color, modifier: Modifier = Modifier, max: Int = 5) {
    Box(
        modifier = modifier
            .padding(vertical = 7.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 3.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.overline,
            )
            Row {
                for (i in 1..max) {
                    if (i < value) {
                        Icon(
                            imageVector = filledIcon,
                            contentDescription = "$i",
                            modifier = Modifier.size(15.dp).alpha(0.8f)
                        )
                    } else {
                        Icon(
                            imageVector = filledIcon, contentDescription = "$i",
                            modifier = Modifier
                                .size(15.dp)
                                .alpha(0.2f)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRatingPill() {
    MyTheme {
        RatingPill(filledIcon = Icons.Default.Chair, value = 3, max = 5, label = "Shedding", color = Orange300)
    }
}
