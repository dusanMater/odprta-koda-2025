package com.example.vreme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vremev2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navMenu()
        }
    }
}

@Composable
fun navMenu() {
    val controller: NavHostController = rememberNavController()
    var imeMesta by remember { mutableStateOf("Nova Gorica") }
    Column(modifier = Modifier.systemBarsPadding()) {
        Row {
            OutlinedTextField(
                value = imeMesta,
                onValueChange = { imeMesta = it},
                label = { Text("vnesi ime Mesta") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }
        Row(modifier = Modifier
            .systemBarsPadding()
            .padding(top = 15.dp)
        ) {
            Column(modifier = Modifier.systemBarsPadding()) {
                Button(onClick = {
                    controller.navigate("todayScreen")
                }) { Text("Today's weather")}
            }
            Column(modifier = Modifier.systemBarsPadding()) {
                Button(onClick = {
                    controller.navigate("weekScreen")
                }) { Text("Week's weather")}
            }
        }
        Row {
            NavHost(navController = controller, startDestination = "weekScreen") {
                composable("todayScreen"){
                    todayScreen(imeMesta)
                }
                composable("weekScreen"){
                    weekScreen(imeMesta)
                }
            }
        }
    }





}

@Composable
fun weekScreen(imeMesta: String){
    var wData by remember { mutableStateOf<List<Day>>(emptyList()) }
    fetchWeather(imeMesta, intervalType.days, object: WeatherCallback {
        override fun onSuccess(weather: WeatherResponse) {
            wData = weather.days
        }

        override fun onError(errorMessage: String) {
            wData = emptyList()
        }
    })
    Column(modifier = Modifier
        .systemBarsPadding()
    ) {
        wData.forEach { Day ->
            addWeatherRowDay(Day)
        }

    }
}

@Composable
fun addWeatherRowDay(rowData:Day){
    Row {
        Column(modifier = Modifier
            .systemBarsPadding()
        ) {
            Text("TIME: ${rowData.datetime} ")
        }
        Column(modifier = Modifier.systemBarsPadding()) {
            Image(
                painter = painterResource(getIconResource(rowData.icon)),
                contentDescription = rowData.icon,
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.systemBarsPadding()) {
            Image(
                painter = painterResource(getIconResource("direction")),
                contentDescription = "windDirection",
                modifier = Modifier.size(30.dp).rotate(rowData.winddir.toFloat()),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.systemBarsPadding()) {
            Text(" Temp: ${rowData.temp} °C")
        }
    }
    Row {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Gray
        )
    }

}

@Composable
fun todayScreen(imeMesta: String){
    var wData by remember { mutableStateOf<List<Hour>>(emptyList()) }
    fetchWeather(imeMesta, intervalType.hours, object: WeatherCallback {
        override fun onSuccess(weather: WeatherResponse) {
            wData = weather.days[0].hours
        }

        override fun onError(errorMessage: String) {
            wData = emptyList()
        }
    })
    Column(modifier = Modifier
        .systemBarsPadding()
    ) {
        wData.forEach { Hour ->
            addWeatherRowHour(Hour)
        }

    }
}

@Composable
fun addWeatherRowHour(rowData:Hour){
    Row {
        Column(modifier = Modifier.systemBarsPadding()) {
            Text("TIME: ${rowData.datetime} ")
        }
        Column(modifier = Modifier.systemBarsPadding()) {
            Image(
                painter = painterResource(getIconResource(rowData.icon)),
                contentDescription = rowData.icon,
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.systemBarsPadding()) {
            Image(
                painter = painterResource(getIconResource("direction")),
                contentDescription = "windDirection",
                modifier = Modifier.size(30.dp).rotate(rowData.winddir.toFloat()),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.systemBarsPadding()) {
            Text(" Temp: ${rowData.temp} °C")
        }
    }
    Row {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Gray
        )
    }

}

fun getIconResource(icon: String): Int {
    val iconRes: Int = when (icon) {
        "clear-day" -> R.drawable.clear_day
        "clear-night" -> R.drawable.clear_night
        "partly-cloudy-day" -> R.drawable.partly_cloudy_day
        "partly-cloudy-night" -> R.drawable.partly_cloudy_night
        "cloudy" -> R.drawable.cloudy
        "rain" -> R.drawable.rain
        "snow" -> R.drawable.snowy
        "thunderstorm" -> R.drawable.thunderstorm
        "direction" -> R.drawable.direction
        // Add more mappings as needed
        else -> R.drawable.clear_day // fallback icon
    }
    return iconRes
}

fun fetchWeather(city:String, timeInterval:intervalType, callback: WeatherCallback){
    val call = RetrofitClient.instance.getWeather(
        location = city,
        include = timeInterval.name
    )

    call.enqueue(object: Callback<WeatherResponse> {
        override fun onResponse(
            call: Call<WeatherResponse>,
            response: Response<WeatherResponse>
        ) {
            if(response.isSuccessful && response.body() != null){
                callback.onSuccess(response.body()!!)
            } else {
                callback.onError("Response error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            callback.onError("Network error: ${t.message}")
        }
    })
}

interface WeatherCallback {
    fun onSuccess(weather: WeatherResponse)
    fun onError(errorMessage: String)
}

interface WeatherApiService {
    @GET("VisualCrossingWebServices/rest/services/timeline")
    fun getWeather(
        @Query("location") location: String,
        @Query("unitGroup") unitGroup: String = "metric",
        @Query("key") apiKey: String = "BLXC8483ZXE2Q9HJ97CYCX8CJ",
        @Query("include") include: String
    ): Call<WeatherResponse>

}

data class WeatherResponse(
    val days: List<Day>
)

data class Day(
    val datetime: String,
    val temp: Double,
    val windspeed: Double,
    val winddir: Double,
    val icon: String,
    val hours: List<Hour>
)

data class Hour(
    val datetime: String,
    val temp: Double,
    val windspeed: Double,
    val winddir: Double,
    val icon: String,
)

object RetrofitClient {
    private const val BASE_URL = "https://weather.visualcrossing.com/"

    val instance: WeatherApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(WeatherApiService::class.java)
    }
}

enum class intervalType {
    days,
    hours
}



