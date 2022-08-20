package com.example.jetbizcard

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
private fun CreatePortfolioList() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            PorfolioItem(data = listOf("Project 1", "Project 2", "Project 3", "Project 4"))
        }
    }
}

@Composable
private fun PorfolioItem(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp,
            ) {
                Row(
                    modifier = Modifier
                        .padding(7.dp)
                        .background(MaterialTheme.colors.surface)
                ) {
                    CreateProfileImage(
                        profileImage = painterResource(id = R.drawable.profile_image),
                        description = item,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(end = 7.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(7.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ){
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "test", style = MaterialTheme.typography.body2)
                    }
                }
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
    val portfolioToggleState = remember {
        mutableStateOf(false)
    }

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
                portfolioToggleState.value = !portfolioToggleState.value
            }) {
            Text(text = "Portfolio", style = MaterialTheme.typography.button)
        }
        if (portfolioToggleState.value) {
            CreatePortfolioList()
        } else {
            Box() {}
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

