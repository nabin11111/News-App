package com.example.newsnow.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.example.newsnow.R
import com.kwabenaberko.newsapilib.models.Article


@Composable
fun HomePage (newsViewModel: NewsViewModel, navController: NavHostController)
{
val articles by newsViewModel.articles.observeAsState(emptyList())


Column (modifier = Modifier.fillMaxSize()){
    CategoriesBar(newsViewModel)
    Spacer(modifier = Modifier.height(24.dp))
    LazyColumn (
        modifier = Modifier.fillMaxSize()

    )
    {
        items(articles) { article->


ArticleItem(article,navController)
        }
    }}

    Text(
        text = "App: Created By NABIN ADHIKARI",
        modifier = Modifier

            .padding(top = 60.dp, start = 54.dp),
        fontSize = 16.sp,
        color = Color.Red,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold

    // You can change this color as needed
    )


}

@Composable
fun ArticleItem (article: Article, navController: NavHostController) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = {
            navController.navigate(NewsArticleScreen(article.url))
        }
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            AsyncImage(
                contentDescription = "Article image",
                model = article.urlToImage?: R.drawable.live,
                modifier = Modifier.size(80.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,



            )
            Column(
                modifier = Modifier.fillMaxSize().padding(start = 8.dp)
            ) {
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3
                )
                Text(
                    text = article.source.name,
                    maxLines = 3,
                    fontSize = 14.sp
                )
            }
        }
    }






}


@Composable
fun CategoriesBar(newsViewModel: NewsViewModel)
{
    var searchQuery by remember{
        mutableStateOf("")
    }
    var isSearchExpanded by remember{
        mutableStateOf(false)
    }

    val categoriesList = listOf(
        "GENERAL",
        "BUSINESS",
        "ENTERTAINMENT",
        "HEALTH",
        "SCIENCE",
        "SPORTS",
        "TECHNOLOGY"
    )
    Row (modifier = Modifier.fillMaxWidth().
    horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ){

        if(isSearchExpanded){
OutlinedTextField(
    modifier = Modifier.padding(12.dp)
        .height(52.dp)
        .border(1.dp,Color.Green, CircleShape)
        .clip(CircleShape)

    ,
    value = searchQuery, onValueChange = {searchQuery= it},
    trailingIcon = {

        IconButton(onClick = {
            isSearchExpanded = false
            if(
                searchQuery.isNotEmpty()
            ){
                newsViewModel.fetchEverythingWithQuery(searchQuery)
            }
        }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    }
    )
        }else{
            IconButton(onClick = {
                isSearchExpanded = true
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }
        categoriesList.forEach {
                category->
            Button(onClick = {
                newsViewModel.fetchNewsTopHeadlines(category)
            },modifier = Modifier.padding(4.dp),colors = ButtonDefaults.buttonColors(Color.Red)) {
                Text(text = category)
            }
        }
    }

}

