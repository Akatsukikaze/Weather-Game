package mg.studio.weatherappdesign;

public class WeatherInfo {
    private String date;//时间
    private String weather;//天气
    private String temperature;//气温

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "WeatherInfo [date=" + date + ", weather=" + weather + ", temperature=" + temperature
                + "]";
    }
/*
---------------------
    作者：PettyKoKo
    来源：CSDN
    原文：https://blog.csdn.net/qq_31028891/article/details/78597548
    版权声明：本文为博主原创文章，转载请附上博文链接！
    */
}
