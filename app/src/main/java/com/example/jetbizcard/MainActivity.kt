package com.example.jetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                BizCard()
            }
        }
    }
}

@Composable
fun BizCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(400.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.Yellow ,
            elevation = 5.dp)
        {
            Column(modifier = Modifier.height(300.dp), verticalArrangement = Arrangement.Top , horizontalAlignment = Alignment.CenterHorizontally) {
                CreateImage()
                Divider(/*modifier = Modifier.fillMaxWidth(), color = Color.Gray, thickness = 15.dp , startIndent =  1.dp*/)
                CreateText()
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                }
                    , modifier = Modifier.padding(5.dp)) {
                    Text(text = "Click here" , style = MaterialTheme.typography.button)
                }
                if (buttonClickedState.value)
                {
                    Content()
                }
                else
                {
                    Box() {}
                }
            }


        }

    }
}

@Composable
private fun CreateImage(modifier: Modifier = Modifier) {
    Surface(
        modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CreateText(){
 Column(modifier = Modifier.padding(5.dp)) {
     Text(text = "Alen Pobhen" , style = MaterialTheme.typography.h5)
     Text(text = "Android Compose Program" , modifier = Modifier.padding(3.dp))
     Text(text = "@alenPobhen" , modifier = Modifier.padding(3.dp))

 }
}

/*@Preview(showBackground = true)*/
@Composable
private fun Content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp))
    {
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Portfolio(data = listOf("Project 1", "Project 2", "Project 3"))

        }
    }
}


@Composable
fun Portfolio(data: List<String>) {
 LazyColumn{
     items(data){
         item -> Card(modifier = Modifier
         .padding(13.dp)
         .fillMaxWidth(), shape = RectangleShape , elevation = 4.dp) {
             Row(modifier = Modifier
                 .padding(8.dp)
                 .background(MaterialTheme.colors.surface)
                 .padding(7.dp)) {
                 CreateImage(modifier = Modifier.size(100.dp))
                 Column(modifier = Modifier
                     .padding(7.dp)
                     .align(alignment = Alignment.CenterVertically)) {
                     Text(text = item , fontWeight = FontWeight.Bold)
                     Text(text = " Great projects" , style = MaterialTheme.typography.body2)
                 }

             }

     }
     }
 }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetBizCardTheme {
        BizCard()
    }
}