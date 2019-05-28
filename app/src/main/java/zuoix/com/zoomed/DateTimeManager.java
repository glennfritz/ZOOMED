package zuoix.com.zoomed;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeManager {
    private Pattern pattern;
    private Matcher matcher;

    String date;

    public DateTimeManager(String date) {
        this.date = date;
    }
    public DateTimeManager(){
        //empty
    }


        public  Date parse (String value , DateFormat...formatters){
            Date date = null;
            for (DateFormat formatter : formatters) {
                try {
                    date = formatter.parse (value);
                    break;
                } catch (ParseException e) {
                }
            }
            return date;
        }
    static  String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols ();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }


        String weekday[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        public String getDateRelativetoToday(String date){
            SimpleDateFormat sdf[] = new SimpleDateFormat[]{new SimpleDateFormat ("ddMMyyyyhhmmss") };
            SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
            Date timestamp = parse (s.format(new Date ()),sdf);
            Date sentdate = parse(date,sdf);
                       if(timestamp.getYear () == sentdate.getYear ()){
                           if (timestamp.getMonth () == sentdate.getMonth ()) {
                               if(timestamp.getDate () == sentdate.getDate ()){
                                   return "Today";
                               }else{
                                 int days = timestamp.getDate () - sentdate.getDate ();
                                 if(days == 0){ return "Today"; }
                                 else if(days == 1){return  "Yesterday";}
                                 else if(days == 2){return  "2 days ago";}
                                 else{
                                     return weekday[(days + timestamp.getDay ())%7];}
                               }
                           }else{
                               return getMonthForInt (sentdate.getMonth ()-1)+ " " + sentdate.getDate ();
                           }
                           }else{
                           return getMonthForInt (sentdate.getMonth ()-1)+ " " + sentdate.getDate () + " " + sentdate.getYear ();
                       }

                       }
        public String getTime(String date){
            SimpleDateFormat sdf[] = new SimpleDateFormat[]{new SimpleDateFormat ("ddMMyyyyhhmmss") };
            Date timestamp = parse (date,sdf);
            return timestamp.getHours () + " : "+timestamp.getMinutes ();
            }


            }

