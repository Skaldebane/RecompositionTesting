package dev.skaldebane.test.recomposition.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import dev.skaldebane.test.recomposition.data.Category
import dev.skaldebane.test.recomposition.ui.components.NumberCard
import dev.skaldebane.test.recomposition.ui.components.NumberField
import dev.skaldebane.test.recomposition.ui.lazy.LazyGrid
import dev.skaldebane.test.recomposition.ui.lazy.cellCount
import dev.skaldebane.test.recomposition.util.insert

// testing data
@OptIn(ExperimentalStdlibApi::class)
val Categories = buildList {
    repeat(20) {
        add(
            Category(
                name = "Category #${it}",
                range = (it * 40..(it + 1) * 40).toList()
            )
        )
    }
}

@Composable
fun AppScreen() {
    var fieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    val gridState = rememberLazyListState()

    // calculating cell count to use in the grid
    val cellCount = cellCount(
        density = LocalDensity.current,
        windowWidth = LocalContext.current.resources.displayMetrics.widthPixels
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Log.d("Recomposition", "AppScreen") // Logging recomposition.
        LazyGrid(
            state = gridState,
            data = Categories,
            cellCount = cellCount,
            item = { number ->
                NumberCard(
                    number = number,
                    onClick = {
                        /* FIXME: This line seems to be problematic. Outcommenting it
                         *  fixes the problem... but I can't understand how is this callback
                         *  related to the LazyGrid composable, as fieldValue is only inside the
                         *  callback and is not passed as a parameter.
                         */
                        fieldValue = fieldValue.insert(it)
                    },
                    modifier = Modifier.weight(1f)
                )
            },
            header = { name ->
                Text(
                    text = name,
                    modifier = Modifier.padding(24.dp)
                )
            }
        )
        AnimatedVisibility(
            visible = fieldValue.text.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            NumberField(
                value = fieldValue,
                onValueChange = { fieldValue = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .navigationBarsWithImePadding()
            )
        }
    }
}
