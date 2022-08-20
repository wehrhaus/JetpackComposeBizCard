package com.example.jetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 4.dp,
        ) {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CreateProfileImage(
                    profileImage = painterResource(id = R.drawable.profile_image),
                    description = "Justin Wehrman's Bio Picture",
                    modifier = Modifier
                        .padding(5.dp)
                        .size(150.dp))
                Divider(
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                    color = Color.LightGray,
                )
                CreateProfileMeta(
                    modifier = Modifier.padding(5.dp),
                    profileName = "Justin Wehrman",
                    occupation = "Senior Software Engineer",
                    githubUserName = "WehrHaus"
                )
            }
        }
    }
}

@Composable
private fun CreateProfileImage(
    profileImage: Painter,
    description: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier,
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
    ) {
        Image(
            painter = profileImage,
            contentDescription = description,
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun CreateProfileMeta(
    modifier: Modifier = Modifier,
    profileName: String,
    occupation: String,
    githubUserName: String
) {
    Column(modifier = modifier) {
        Text(
            text = profileName,
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.h4,
        )
        Text(
            text = occupation,
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1,
        )
        GitHubUrl(userName = githubUserName)
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                Log.d("Clicked", "Portfolio Button Clicked")
            }) {
            Text(text = "Portfolio", style = MaterialTheme.typography.button)
        }
    }
}

@Composable
private fun GitHubUrl(modifier: Modifier = Modifier, userName: String) {
    val uriHandler = LocalUriHandler.current
    Text(
        text = "@$userName",
        modifier = modifier
            .clickable { uriHandler.openUri("https://github.com/$userName") },
        color = MaterialTheme.colors.primaryVariant,
        textDecoration = TextDecoration.Underline,
        style = MaterialTheme.typography.subtitle2,
    )
}

@Preview(name = "App - Light Mode")
@Composable
fun PreviewApp() {
    JetBizCardTheme {
        CreateBizCard()
    }
}

