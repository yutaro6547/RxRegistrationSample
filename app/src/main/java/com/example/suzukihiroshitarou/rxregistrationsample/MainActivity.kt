package com.example.suzukihiroshitarou.rxregistrationsample

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.suzukihiroshitarou.rxregistrationsample.databinding.ActivityMainBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // お名前入力部分
        val nameStream = RxTextView.textChanges(binding.nameInput).skip(1).map { it.isNotEmpty() }
        // 誕生日部分
        val birthdayStream = RxTextView.textChanges(binding.birthdayInput).skip(1).map { it.isNotEmpty() }
        // 性別部分
        val genderStream = RxTextView.textChanges(binding.genderInput).skip(1).map { it.isNotEmpty() }

        Observables.combineLatest(
                nameStream,
                birthdayStream,
                genderStream,
                { name, birthday, gender -> name && birthday && gender})
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it == true) {
                        setRegisterButton()
                    } else {
                        setUnRegisterButton()
                    }
                }, {
                    Timber.e(it.message)
                })

    }

    private fun setRegisterButton() {
        with(binding.registerButton) {
            background = resources.getDrawable(R.drawable.button_background, null)
            isClickable = true
            setOnClickListener {
                Toast.makeText(this@MainActivity, "register Success!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUnRegisterButton() {
        with(binding.registerButton) {
            background = resources.getDrawable(R.drawable.button_background_unregister, null)
            isClickable = false
        }
    }
}
