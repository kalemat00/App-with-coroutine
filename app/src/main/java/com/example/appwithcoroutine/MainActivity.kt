package com.example.appwithcoroutine

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.appwithcoroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var number: Int? = null


        binding.buttonView.setOnClickListener{
            if(number == null && binding.editView.text.isNotBlank()) {
                number = binding.editView.text.toString().toInt()
            }else if (number != null) {
                lifecycleScope.launch {
                    number = increaseByOne(number)
                    binding.textView.text = number.toString()
                }
            }else{
                binding.textView.text = "insert a number first!!"
            }
        }
    }


    private suspend fun increaseByOne(number: Int?): Int {
        var value = number
        delay(2000)
        return if (value != null) {
            ++value
        }else{
            0
        }
    }
}