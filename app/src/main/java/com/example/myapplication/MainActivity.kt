package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.Stack

class MainActivity : ComponentActivity() {
    private lateinit var binding:ActivityMainBinding
    private fun updateInput(input: String){
        binding.textViewInput.text = "Input: $input"
    }
    private fun updateOutput(value: Boolean){
        binding.textViewOutput.text = "Output: $value"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var input = "";
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAddPlainLeft.setOnClickListener{
            input+="("
            updateInput(input)
            var result = parse(input)
            updateOutput(result)
        }
        binding.buttonAddPlainRight.setOnClickListener{
            input+=")"
            updateInput(input)
            var result = parse(input)
            updateOutput(result)
        }
        binding.buttonAddBoxLeft.setOnClickListener{
            input+="["
            updateInput(input)
            var result = parse(input)
            updateOutput(result)
        }
        binding.buttonAddBoxRight.setOnClickListener {
            input+="]"
            updateInput(input)
            var result = parse(input)
            updateOutput(result)
        }
        binding.buttonAddCurlyLeft.setOnClickListener {
            input+="{"
            updateInput(input)
            var result = parse(input)
            updateOutput(result)
        }
        binding.buttonAddCurlyRight.setOnClickListener {
            input+="}"
            updateInput(input)
            var result = parse(input)
            updateOutput(result)
        }
        binding.buttonClear.setOnClickListener {
            if (input.isEmpty())
                return@setOnClickListener
            input = input.substring(0,input.length-1)
            updateInput(input)
            var result = parse(input)
            updateOutput(result)
        }
        binding.buttonClearAll.setOnClickListener {
            input = ""
            updateInput(input)
            var result = parse(input)
            updateOutput(result)
        }
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