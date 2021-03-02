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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.ui.theme.Creamy
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DetailPuppy(puppy: Puppy?, modifier: Modifier = Modifier) {
    if (puppy != null) {
        Box(modifier = modifier.background(MaterialTheme.colors.surface)) {
            CoilImage(
                data = puppy.image,
                contentDescription = puppy.name,
                modifier = modifier,
                contentScale = ContentScale.FillHeight,
                error = { err ->
                    Log.e("MainActivity", "Image loading failed $err")
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Creamy, RoundedCornerShape(10.dp))
                        .padding(5.dp)
                ) {
                    Text(
                        "Hello, my name is ${puppy.name}",
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .background(Creamy, RoundedCornerShape(10.dp))
                        .padding(5.dp)
                ) {

                    Text(
                        "I am ${puppy.age} old and I weigh ${puppy.weight}",
                        style = MaterialTheme.typography.subtitle2
                    )
                    Text(
                        puppy.info,
                        style = MaterialTheme.typography.body1
                    )
                    PuppyRating(puppy = puppy)
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailPreview() {
    MyTheme {
        val pup = Puppy("A0838790", "Bruce", "3 years", "63 pounds", "Bruce is a loveable, happy and a friendly guy who will light up and enhance your family life. He is strong on leash and walks best with a no pull harness. He loves to run and would make a great exercise buddy! He is playful and loves . . . loves . . . loves treats, hehe. Bruce can be sneaky, so watch out for open doors and weak backyard fences. Due to past interactions with other dogs, Bruce would be happiest as the only pet. He won’t make a great dog park dog- he’s happiest spending one on one time with you. He needs an active, experienced owner with kids over 12.", "Bull Terrier/Mix", 3, 4, 1)
        DetailPuppy(puppy = pup)
    }
}
