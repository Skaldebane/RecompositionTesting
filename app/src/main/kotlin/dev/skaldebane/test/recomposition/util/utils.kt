package dev.skaldebane.test.recomposition.util

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

fun TextFieldValue.insert(insertedText: String): TextFieldValue {
    var startText = ""
    var endText = ""

    if (text.isNotEmpty()) {
        if (selection.collapsed) {
            startText = text.substring(range = 0 until selection.min)
            endText = text.substring(range = selection.max until text.length)
        } else {
            startText = text.substring(range = 0 until selection.min)
            endText = text.substring(range = selection.max until text.length)
        }
    }

    return copy(
        text = startText + insertedText + endText,
        selection = TextRange(selection.min + insertedText.length)
    )
}
