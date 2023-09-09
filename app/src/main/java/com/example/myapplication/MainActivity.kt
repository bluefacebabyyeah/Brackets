package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.Stack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val input = "()[]{}"  // строку с инпутом можешь менять
        val result = parse(input)
        Toast.makeText(this, "Input = $input\nAnswer = $result", Toast.LENGTH_LONG).show()
    }
    private fun parse(input: String) : Boolean {
        val stack = Stack<String>()
        for (i in 0 until input.length){
            if (input[i] == '(' || input[i] == '[' || input[i] == '{')
            {
                stack.push(input[i].toString());
            }
            if (input[i] == ')')
                if (stack.isNotEmpty() && stack.peek() == "(")
                    stack.pop();
                else return false;
            if (input[i] == ']')
                if (stack.isNotEmpty() && stack.peek() == "[")
                    stack.pop();
                else return false;
            if (input[i] == '}')
                if (stack.isNotEmpty() && stack.peek() == "{")
                    stack.pop();
                else return false;
        }
        return stack.isEmpty();
    }
}