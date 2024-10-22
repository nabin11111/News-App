package com.example.newsnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsnow.ui.theme.HomePage
import com.example.newsnow.ui.theme.HomePageScreen
import com.example.newsnow.ui.theme.NewsArticlePage
import com.example.newsnow.ui.theme.NewsArticleScreen
import com.example.newsnow.ui.theme.NewsNowTheme
import com.example.newsnow.ui.theme.NewsViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val newsViewModel = ViewModelProvider(this) [ NewsViewModel::class.java]
        setContent {
            val navController = rememberNavController()
            NewsNowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                    ) {
Text(text = " INTERNATIONAL NEWS",
    modifier = Modifier.align(Alignment.CenterHorizontally),
    color = androidx.compose.ui.graphics.Color.Black,
    fontSize = 32.sp,
    fontFamily = FontFamily.Cursive
    )

                        NavHost(navController = navController, startDestination = HomePageScreen) {
                            composable<HomePageScreen> {
                                HomePage(newsViewModel,navController)
                            }
                            composable<NewsArticleScreen> {
                             val args = it.toRoute<NewsArticleScreen>()
                                NewsArticlePage(args.url)
                            }

                        }


                        }

                    }
                }
            }}}

