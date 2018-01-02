// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.pluraprocessing.common.data.file.SimpleFileDAO;

public class Log
{
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss:SSS";
    private static String path;
    private static String file;
    
    static {
        Log.path = "/etc/plura/";
        Log.file = String.valueOf(Log.path) + "log.txt";
    }
    
    public static void log(final String message) {
        writeLog(Log.file, message);
    }
    
    public static void log(final String message, final String fileName) {
        writeLog(String.valueOf(Log.path) + fileName, message);
    }
    
    private static void writeLog(final String location, final String message) {
        try {
            SimpleFileDAO.appendStringToFile(location, String.valueOf(now()) + "  " + Thread.currentThread().getId() + "  " + message + "\n");
        }
        catch (Exception ex) {}
    }
    
    public static String now() {
        final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return sdf.format(cal.getTime());
    }
}
