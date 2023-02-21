package ru.osmanov.myweather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.osmanov.myweather.R
import ru.osmanov.myweather.databinding.ActivityMainBinding
import ru.osmanov.myweather.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}