package com.example.weatherapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.example.weatherapplication.Datamodel.RetrofitBuilder
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var repo: Repo
    private lateinit var viewModel: WeatherViewModel
    private lateinit var factory: WeatherViewModelFactory
    private lateinit var loader:ProgressBar
    private lateinit var EnterCity:EditText
    private lateinit var BtnReport:Button
    private lateinit var imagedynamic:ImageView
    private lateinit var condition:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        BtnReport.setOnClickListener {
            viewModel.getweatherdetails(EnterCity.text.toString())
        }
        viewModel.weatherdetaillivedata.observe(this){
            val currentweathertype=it.current.condition.text
            val currenttempinC=it.current.temp_c
            condition.text="$currentweathertype ,$currenttempinC"

            val imagelink="https:${it.current.condition.icon}"
            Glide.with(this)
                .load(imagelink)
                .into(imagedynamic)

        }
        


        viewModel.weatherdetaillivedata.observe(this){
            Log.i("Deatsils",it.toString())
        }
        viewModel.isloading.observe(this){
            if (it){
                loader.visibility=View.VISIBLE
            }
            else{
                loader.visibility=View.GONE
            }
        }
    }
    private fun init(){
        repo= Repo(RetrofitBuilder.getinstance())
        factory= WeatherViewModelFactory(repo)
        viewModel=ViewModelProvider(this,factory).get(WeatherViewModel::class.java)
        loader=findViewById(R.id.loader)
        EnterCity=findViewById(R.id.edt_city_name)
        BtnReport=findViewById(R.id.btn_report)
        imagedynamic=findViewById(R.id.iv_weather_image)
        condition=findViewById(R.id.tv_condition)
    }
}