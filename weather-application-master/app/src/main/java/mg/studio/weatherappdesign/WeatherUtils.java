package mg.studio.weatherappdesign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;
import   java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author 22786
 *
 */
public class WeatherUtils {
    /**
     * 通过城市名称获取该城市的天气信息
     *
     * @param cityName
     * @return
     */

    public static String GetWeatherData(String cityname, boolean forecast) {
        StringBuilder sb = new StringBuilder();
        ;
        try {
            //cityname = URLEncoder.encode(cityName, "UTF-8");
            String weather_url;
            if(forecast){
                weather_url = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityname + "&units=metric&APPID=971808d09cc6c7b9c02b0147e1ea0c2b";
            }else {
                weather_url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityname + "&units=metric&APPID=971808d09cc6c7b9c02b0147e1ea0c2b";
            }

            URL url = new URL(weather_url);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8"); // 设置读取流的编码格式，自定义编码
            BufferedReader reader = new BufferedReader(isr);
            String line = null;
            while ((line = reader.readLine()) != null)
                sb.append(line + " ");
            reader.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println(sb.toString());
        return sb.toString();

    }

    public static WeatherInfo[] GetForecast(String weatherInfobyJson) {
        JSONObject dataOfJson;
        WeatherInfo[] weatherInfo = new WeatherInfo[4];
        try {
            dataOfJson = new JSONObject(weatherInfobyJson);

            //创建WeatherInfo对象，提取所需的天气信息
            int i = 0;
            while(i < 4) {
                weatherInfo[i] = new WeatherInfo();
                i++;
            }

            //获取预测的天气预报信息
            JSONArray list = dataOfJson.getJSONArray("list");
            i = 1;
            while(i < 5){
                JSONObject data = (JSONObject)list.get(8 * i);
                JSONArray weather = data.getJSONArray("weather");
                JSONObject weatherData = weather.getJSONObject(0);
                weatherInfo[i-1].setWeather(weatherData.getString("main"));
                i++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return weatherInfo;
    }
    /**
     * 将JSON格式数据进行解析 ，返回一个weather对象
     *
     * @param str
     * @return
     */
    public static WeatherInfo GetWeather(String weatherInfobyJson) {
        JSONObject dataOfJson;
        WeatherInfo weatherInfo;
        try {
            dataOfJson = new JSONObject(weatherInfobyJson);

            //创建WeatherInfo对象，提取所需的天气信息
            weatherInfo = new WeatherInfo();

            //从json数据中提取数据
            String main = dataOfJson.getString("main");
            JSONObject dataOfMain;
            dataOfMain = new JSONObject(main);
            //获取预测的天气预报信息
            JSONArray weather = dataOfJson.getJSONArray("weather");
            //取得当天的
            JSONObject result = weather.getJSONObject(0);
            int uTime = dataOfJson.getInt("dt");
            Xtime xTime = new Xtime();
            UTime2Date.xSeconds2Date(uTime, xTime);

            weatherInfo.setDate("" + xTime.month + "/" + xTime.day + "/" + xTime.year);

            weatherInfo.setTemperature(dataOfMain.getString("temp"));

            weatherInfo.setWeather(result.getString("main"));

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return weatherInfo;
    }
}
/*
---------------------
    作者：PettyKoKo
    来源：CSDN
    原文：https://blog.csdn.net/qq_31028891/article/details/78597548
    版权声明：本文为博主原创文章，转载请附上博文链接！*/