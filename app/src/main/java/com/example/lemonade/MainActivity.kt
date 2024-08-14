package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Draft"
)
@Composable
fun LemonadeApp() {
    var currentStep by remember {mutableIntStateOf(1)}
    var squeezeLemon by remember {mutableIntStateOf(0)}
    Scaffold(
        topBar = {CenterAlignedTopAppBar(title = { Text(text = "Lemonade", fontWeight = FontWeight.Bold, color = Color.Black) },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = Color.Yellow
            )
        )
    }
    ) {
        innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFC4ECD2))

        ) {
            when (currentStep) {
                1 -> LemonadeButton(
                    textLabelID = R.string.Lemon_Tree,
                    pictureID = R.drawable.lemon_tree,
                    contentDesc =R.string.Lemon_Tree_ContentDesc ,
                    onImageClick = {
                        currentStep = 2
                        squeezeLemon = (2..10).random()
                    })
                2 -> LemonadeButton(
                    textLabelID = R.string.Squeeze_Lemon,
                    pictureID = R.drawable.lemon_squeeze,
                    contentDesc = R.string.Squeeze_Lemon_ContentDesc,
                    onImageClick = {
                        --squeezeLemon
                        if (squeezeLemon == 0){
                            currentStep = 3
                        }
                    })
                3 -> LemonadeButton(
                    textLabelID = R.string.Drink_Lemon,
                    pictureID = R.drawable.lemon_drink,
                    contentDesc = R.string.Drink_Lmeon_ContentDesc,
                    onImageClick = {
                        currentStep = 4
                    })
                4 -> LemonadeButton(
                    textLabelID = R.string.Restart_App,
                    pictureID = R.drawable.lemon_restart,
                    contentDesc = R.string.Restart_App_ContentDesc,
                    onImageClick = {
                        currentStep = 1
                    })
            }
        }
    }
    }


@Composable
fun LemonadeButton(
    textLabelID: Int,
    pictureID: Int,
    contentDesc: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(Color(color = 0xFFC4ECD2))
            ) {
                Image(
                    painter = painterResource(pictureID),
                    contentDescription = stringResource(contentDesc),
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_image_width))
                        .height(dimensionResource(R.dimen.button_image_height))
                       // .padding(dimensionResource(R.dimen.button_interior_padding))
                )
                Spacer(modifier = Modifier
                    .height(dimensionResource(R.dimen.padding_vertical)))
                Text(
                    text = stringResource(textLabelID),
                    fontSize = 20.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
    

}