package com.esgi.events.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sylvainvincent on 23/01/16.
 */
public class FonctionsHelper {

    public static String dateToString(Date date){

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(new Date());
    }

}
