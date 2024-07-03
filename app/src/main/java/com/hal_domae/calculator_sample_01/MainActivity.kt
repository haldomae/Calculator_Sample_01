package com.hal_domae.calculator_sample_01

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hal_domae.calculator_sample_01.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var input: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setKeyClickListener()
    }

    private fun setKeyClickListener(){
        val numKeyButtons = listOf(
            binding.key0,
            binding.key1,
            binding.key2,
            binding.key3,
            binding.key4,
            binding.key5,
            binding.key6,
            binding.key7,
            binding.key8,
            binding.key9,
        )

        for(numKeyButton in numKeyButtons){
            setNumButtonClickListener(numKeyButton)
        }

        val operatorButtons = listOf(
            binding.keyAdd,
            binding.keySub,
            binding.keyMul,
            binding.keyDiv,
        )

        for(operatorButton in operatorButtons){
            setOperatorButtonClickListener(operatorButton)
        }

        binding.keyEqual.setOnClickListener {
            binding.calcLabel.text = Calculator().calcForEquation(binding.calcLabel.text.toString()).toInt().toString()
        }

        // Cボタンのイベント
        binding.keyClear.setOnClickListener {
            binding.calcLabel.text = ""
            input = ""
        }
    }

    // 数値のキーが押された時のイベントを付与
    private fun setNumButtonClickListener(numKeyButton: View){
        numKeyButton.setOnClickListener{
            // 入力項目を画面に表示
            displayInputKey(getButtonNumerics((numKeyButton as Button)).toString())
        }
    }

    // 演算子のキーが押されたときのイベントを付与
    private fun setOperatorButtonClickListener(operatorButton: View){
        operatorButton.setOnClickListener{
            displayInputKey(getButtonOperator((operatorButton as Button)))
        }

    }
    // ボタンの表示されている数値を取得
    private fun getButtonNumerics(button: Button): Int = Integer.parseInt(button.getText().toString())
    // ボタンに表示されている演算子を取得
    private fun getButtonOperator(button: Button): String = button.getText().toString()

    // 押されたキーを画面に表示
    private fun displayInputKey(inputKey: String){
        input += inputKey
        binding.calcLabel.text = input
    }
}