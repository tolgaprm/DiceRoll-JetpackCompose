package com.tolgapirim.diceroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tolgapirim.diceroll.ui.theme.DiceRollTheme
import kotlin.reflect.KProperty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyContent()
        }
    }
}


@Composable
fun DiceRollApp(content:@Composable ()-> Unit){
    DiceRollTheme() {
        Scaffold() {
           content()
        }
    }


}

@Composable
fun TopBar(){
    TopAppBar() {
        Text(
            text ="Rice Roller",
           fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun MyContent(){
    val firstDiceDrawable = remember { mutableStateOf(R.drawable.dice_1) }
    val secondDiceDrawable = remember { mutableStateOf(R.drawable.dice_1)}
    val firstDiceDescription = remember { mutableStateOf("one")}
    val secondDiceDescription = remember { mutableStateOf("one")}

    TopBar()
    Column(modifier = Modifier
        .fillMaxSize()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = firstDiceDrawable.value),
                contentDescription =firstDiceDescription.value )
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id =secondDiceDrawable.value),
                contentDescription =secondDiceDescription.value )
        }
        
        Button(
            onClick = {
                roll(
                    firstDiceDrawable,
                    firstDiceDescription,
                    secondDiceDrawable,
                    secondDiceDescription
                )
            },
            modifier = Modifier
                .width(120.dp)
                .padding(vertical = 50.dp)

        ) {
            Text(
                text = stringResource(id = R.string.roll)
            )
        }
    }
}




fun roll(firstDiceStat:MutableState<Int>, forFirstContentDes:MutableState<String>, secondDiceState: MutableState<Int>,forSecondContentDes:MutableState<String>){

    val firstDiceRoll = (1..6).random()
    val secondDiceRoll = (1..6).random()

    val firstDice = when(firstDiceRoll){
        1-> R.drawable.dice_1
        2-> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }



    val secondDice = when(secondDiceRoll){
        1-> R.drawable.dice_1
        2-> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }


    firstDiceStat.value = firstDice
    secondDiceState.value = secondDice

    forFirstContentDes.value = firstDiceRoll.toString()
    forSecondContentDes.value = secondDiceRoll.toString()

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiceRollApp {
        MyContent()
    }
}