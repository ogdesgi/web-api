package com.esgi.events.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sylvainvincent on 23/01/16.
 */
public class FonctionsHelper {

    public static String dateToString(Date date){

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

    public static Date StringToDate(String date){
        Date newDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        try {
            newDate = simpleDateFormat.parse(date);
            System.out.println(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

}
