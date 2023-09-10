package com.example.myapplication

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.Stack

class MainActivity : ComponentActivity() {
    private lateinit var binding:ActivityMainBinding
    val resultItemList = mutableListOf<ResultItem>()
    val adapter = CustomRecyclerAdapter()

    private fun updateInput(input: String){
        binding.textViewInput.text = "Input: $input"
    }
    private fun updateOutput(value: Boolean){
        return
    }

    private fun addItem(input : String, output : Boolean){
        val resultItem = ResultItem(input, output)
        resultItemList.add(resultItem)
        adapter.submitList(resultItemList.toList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var input = "";
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerviewInputOutput)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        recyclerView.adapter = adapter
        
        binding.buttonAddPlainLeft.setOnClickListener{
            input+="("
            updateInput(input)
        }

        binding.buttonAddPlainRight.setOnClickListener{
            input+=")"
            updateInput(input)
        }

        binding.buttonAddBoxLeft.setOnClickListener{
            input+="["
            updateInput(input)
        }

        binding.buttonAddBoxRight.setOnClickListener {
            input+="]"
            updateInput(input)
        }

        binding.buttonAddCurlyLeft.setOnClickListener {
            input+="{"
            updateInput(input)
        }

        binding.buttonAddCurlyRight.setOnClickListener {
            input+="}"
            updateInput(input)
        }

        binding.buttonClear.setOnClickListener {
            if (input.isEmpty())
                return@setOnClickListener
            input = input.substring(0,input.length-1)
            updateInput(input)
        }

        binding.buttonClearAll.setOnClickListener {
            input = ""
            updateInput(input)
        }

        binding.buttonParse.setOnClickListener {
            addItem(
                input, parse(input)
            )
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