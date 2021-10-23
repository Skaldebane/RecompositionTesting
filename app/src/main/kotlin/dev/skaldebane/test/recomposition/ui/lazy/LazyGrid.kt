package dev.skaldebane.test.recomposition.ui.lazy

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import dev.skaldebane.test.recomposition.data.Category
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyGrid(
    state: LazyListState,
    data: List<Category>,
    cellCount: Int,
    item: @Composable RowScope.(number: Int) -> Unit,
    header: @Composable (name: String) -> Unit,
) {
    Log.d("Recomposition", "LazyGrid") // Logging recomposition.
    LazyColumn(state = state) {
        for ((index, category) in data.withIndex()) {
            val list = (category.range.first()..category.range.last()).toList()
            val listRows = list.chunked(size = cellCount)
            stickyHeader(key = index) {
                header(category.name)
            }
            items(listRows) { row ->
                GridRow(row, cellCount, item)
            }
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(178.dp)
                    .navigationBarsWithImePadding()
            )
        }
    }
}

@Composable
fun GridRow(
    row: List<Int>,
    cellCount: Int,
    item: @Composable RowScope.(number: Int) -> Unit
) {
    Row(modifier = Modifier.padding(horizontal = 12.dp)) {
        for (index in 0 until cellCount) {
            val number = row.getOrElse(index) { -1 }
            if (number == -1) Spacer(modifier = Modifier.weight(1f))
            else item(number)
        }
    }
}

fun cellCount(density: Density, windowWidth: Int): Int {
    return (windowWidth / density.density).roundToInt() / 70
}
