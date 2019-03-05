package mg.studio.weatherappdesign;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadUpdate().execute();
    }

    public void btnClick(View view) {
            new DownloadUpdate().execute();
    }


    private class DownloadUpdate extends AsyncTask<String, Void, WeatherInfo[]> {
        private void setWeatherImage(ImageView view, String weather){
            switch(weather){
                case "Clear":{
                    view.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.sunny_small));
                    break;
                }
                case "Thunderstorm":{
                    view.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.rainy_small));
                    break;
                }
                case "Rain":{
                    view.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.rainy_small));
                    break;
                }
                case "Drizzle":{
                    view.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.rainy_small));
                    break;
                }
                case "Snow":{
                    view.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.rainy_small));
                    break;
                }
                case "Clouds":{
                    view.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.partly_sunny_small));
                    break;
                }
                default:{

                }
            }
        }

        @Override
        protected WeatherInfo[] doInBackground(String... strings) {
            boolean isNetConn;
            try {
                ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                isNetConn = cwjManager.getActiveNetworkInfo().isAvailable();
            }catch(NullPointerException e) {
                e.printStackTrace();
                return null;
            }

            if(isNetConn) {
                WeatherInfo[] weatherInfo = new WeatherInfo[5];
                weatherInfo[0] = WeatherUtils.GetWeather(WeatherUtils.GetWeatherData("Chongqing", false));
                WeatherInfo[] forecasts = WeatherUtils.GetForecast(WeatherUtils.GetWeatherData("Chongqing", true));
                int i = 0;
                while (i < 4) {
                    weatherInfo[i + 1] = forecasts[i];
                    i++;
                }
                return weatherInfo;
            }else{
                return null;
            }
        }

        @Override
        protected void onPostExecute(WeatherInfo[] weatherInfo) {
            if(weatherInfo!=null) {
                //Update the temperature displayed
                ((TextView) findViewById(R.id.temperature_of_the_day)).setText(weatherInfo[0].getTemperature());
                setWeatherImage(((ImageView) findViewById(R.id.img_weather_condition)), weatherInfo[0].getWeather());
                setWeatherImage(((ImageView) findViewById(R.id.Image_Forecast_1)), weatherInfo[1].getWeather());
                setWeatherImage(((ImageView) findViewById(R.id.Image_Forecast_2)), weatherInfo[2].getWeather());
                setWeatherImage(((ImageView) findViewById(R.id.Image_Forecast_3)), weatherInfo[3].getWeather());
                setWeatherImage(((ImageView) findViewById(R.id.Image_Forecast_4)), weatherInfo[4].getWeather());
                Toast.makeText(getApplicationContext(), "updated!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "network problem!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
