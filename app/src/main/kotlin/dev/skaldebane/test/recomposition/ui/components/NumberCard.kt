package dev.skaldebane.test.recomposition.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NumberCard(
    number: Int,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val numberStr = remember { number.toString() }
    Log.d("Recomposition", "NumberCard: $numberStr") // Logging recomposition.
    Card(
        shape = MaterialTheme.shapes.large,
        onClick = {
            onClick(numberStr)
        },
        modifier = Modifier
            .padding(4.dp)
            .height(70.dp)
            .then(modifier)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = numberStr,
                fontSize = 30.sp
            )
        }
    }
}
