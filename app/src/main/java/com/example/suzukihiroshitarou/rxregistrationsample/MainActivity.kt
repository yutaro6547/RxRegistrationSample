package com.example.suzukihiroshitarou.rxregistrationsample

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.suzukihiroshitarou.rxregistrationsample.databinding.ActivityMainBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // お名前入力部分
        RxTextView.textChanges(binding.nameInput)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {

                })

    }
}
