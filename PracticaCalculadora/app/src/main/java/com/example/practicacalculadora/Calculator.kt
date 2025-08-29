package com.example.practicacalculadora

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicacalculadora.ui.theme.PracticaCalculadoraTheme
import kotlin.div
import kotlin.times

@Composable
fun Calculator(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf("") }
    var prevNumber by remember { mutableIntStateOf(0) }
    var currentOperation by remember { mutableStateOf("") }
    var memoryResult by remember {mutableStateOf(0)}
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Row {
            Text(
                text = result.ifEmpty { "0" },
                fontSize = 24.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }
        MemoryPanel(
            onMemoryResultChange = {newMemoryResult -> memoryResult = newMemoryResult},
            memoryResult = memoryResult,
            result =result,
            onResultChange = {newResult -> result = newResult}
        )
        NumberPanel(
            onNumberClick = { num ->
                result += num
            }
        )
        OperationsPanel(
            selectOperationType = { theOperation ->
                if (result.isNotEmpty()) {
                    prevNumber = result.toInt()
                    result = ""
                    currentOperation = theOperation
                }
            },
            onEqualsClick = {
                val currentNumber = result.toIntOrNull() ?: 0
                var operationResult = 0
                when (currentOperation) {
                    "+" -> operationResult = prevNumber + currentNumber
                    "-" -> operationResult = prevNumber - currentNumber
                    "x" -> operationResult = prevNumber * currentNumber
                    "/" -> {
                        if (currentNumber != 0) {
                            operationResult = prevNumber / currentNumber
                        } else {
                            operationResult = 0 // Avoid division by zero
                        }
                    }
                }
                result = operationResult.toString()
                currentOperation = ""
                prevNumber = 0
            }
        )
        ClearOperationsPanel(
            onClearOneClick = {
                result = result.substring(0, result.length - 1)
            },
            onClearAllClick = {
                result = ""
            }
        )
    }
}

@Composable
fun ClearOperationsPanel(
    onClearOneClick: () -> Unit = {},
    onClearAllClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(
            text = "C",
            onClick = {
                onClearOneClick()
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "CE",
            onClick = {
                onClearAllClick()
            },
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun OperationsPanel(
    selectOperationType: (operation: String) -> Unit,
    onEqualsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TextButton(
            text = "+",
            onClick = {
                selectOperationType("+")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "-",
            onClick = {
                selectOperationType("-")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "x",
            onClick = {
                selectOperationType("x")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "/",
            onClick = {
                selectOperationType("/")
            },
            modifier = Modifier
                .weight(1f)
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(
            text = "=",
            onClick = {
                onEqualsClick()
            },
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun MemoryPanel(
    onMemoryResultChange: (Int) -> Unit,
    memoryResult: Int,
    result:String,
    onResultChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TextButton(
            text = "M+",
            onClick = {
                if(result.isNotEmpty()){
                    onMemoryResultChange(memoryResult+result.toInt())
                    onResultChange("")
                }

            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "M-",
            onClick = {
                if(result.isNotEmpty()){
                    onMemoryResultChange(memoryResult-result.toInt())
                    onResultChange("")
                }

            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "MR",
            onClick = {
                onResultChange(memoryResult.toString())
            },
            
            modifier = Modifier
                .weight(1f),
            enable = memoryResult != 0
        )
        
        TextButton(
            text = "MC",
            onClick = {
                onMemoryResultChange(0)
                onResultChange("")
            },
            modifier = Modifier
                .weight(1f),
            enable = memoryResult != 0
        )
    }
}

@Composable
fun NumberPanel(
    onNumberClick: (num: String) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(
            text = "1",
            onClick = {
                onNumberClick("1")
            },
            modifier = Modifier
                .weight(1f),
        )
        TextButton(
            text = "2",
            onClick = {
                onNumberClick("2")

            },
            modifier = Modifier
                .weight(1f),
        )
        TextButton(
            text = "3",
            onClick = {
                onNumberClick("3")

            },
            modifier = Modifier
                .weight(1f),
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(
            text = "4",
            onClick = {
                onNumberClick("4")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "5",
            onClick = {
                onNumberClick("5")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "6",
            onClick = {
                onNumberClick("6")
            },
            modifier = Modifier
                .weight(1f)
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(
            text = "7",
            onClick = {
                onNumberClick("7")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "8",
            onClick = {
                onNumberClick("8")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "9",
            onClick = {
                onNumberClick("9")
            },
            modifier = Modifier
                .weight(1f)
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(
            text = "0",
            onClick = {
                onNumberClick("0")
            },
            modifier = Modifier
                .weight(1f)
        )

    }
}

@Composable
fun TextButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enable: Boolean = true) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enable
    ) {
        Text(
            text,
            fontSize = 16.sp,
        )
    }
}

@Preview
@Composable
fun ClearOperationsPanelPreview() {
    PracticaCalculadoraTheme {
        Column {
            ClearOperationsPanel(onClearOneClick = {}, onClearAllClick = {})
        }
    }
}

@Preview
@Composable
fun OperationsPanelPreview() {
    PracticaCalculadoraTheme {
        Column {
            OperationsPanel(selectOperationType = {}, onEqualsClick = {})
        }
    }
}


@Preview
@Composable
fun NumberPanelPreview() {
    PracticaCalculadoraTheme {
        Column {
            NumberPanel(onNumberClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    PracticaCalculadoraTheme {
        Calculator()
    }
}
