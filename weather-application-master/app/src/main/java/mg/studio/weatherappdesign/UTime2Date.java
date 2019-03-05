package mg.studio.weatherappdesign;

public class UTime2Date {
    static void xSeconds2Date(int seconds,Xtime time )
    {
        int[] month={
        /*01月*/31,
                /*02月*/28,
                /*03月*/31,
                /*04月*/30,
                /*05月*/31,
                /*06月*/30,
                /*07月*/31,
                /*08月*/31,
                /*09月*/30,
                /*10月*/31,
                /*11月*/30,
                /*12月*/31
    };
        int days;
        int leap_y_count;
        time.second      = seconds % 60;//获得秒
        seconds          /= 60;
        time.minute      =  seconds % 60;//获得分
        seconds          += 8 * 60 ;        //时区矫正 转为UTC+8 bylzs
        seconds          /= 60;
        time.hour        = seconds % 24;//获得时
        days              = seconds / 24;//获得总天数
        leap_y_count = (days + 365) / 1461;//过去了多少个闰年(4年一闰)
        if( ((days + 366) % 1461) == 0)
        {//闰年的最后1天
            time.year = 1970 + (days / 366);//获得年
            time.month = 12;              //调整月
            time.day = 31;
            return;
        }
        days -= leap_y_count;
        time.year = 1970 + (days / 365);     //获得年
        days %= 365;                       //今年的第几天
        days = 1 + days;                  //1日开始
        if( (time.year % 4) == 0 )
        {
            if(days > 60)--days;            //闰年调整
            else
            {
                if(days == 60)
                {
                    time.month = 2;
                    time.day = 29;
                    return;
                }
            }
        }
        for(time.month = 0;month[time.month] < days;time.month++)
        {
            days -= month[time.month];
        }
        ++time.month;               //调整月
        time.day = days;           //获得日
    }
}
