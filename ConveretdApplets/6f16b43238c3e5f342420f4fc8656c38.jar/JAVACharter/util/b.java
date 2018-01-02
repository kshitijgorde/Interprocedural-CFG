// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.util;

import java.util.TimeZone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Date;

public class b
{
    public static int a(final Date date, final Date date2) {
        if (date.before(date2)) {
            return -1;
        }
        if (date.after(date2)) {
            return 1;
        }
        if (date.equals(date2)) {
            return 0;
        }
        return -2;
    }
    
    public static Date for(final Date time, final int n) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        gregorianCalendar.add(10, n);
        return gregorianCalendar.getTime();
    }
    
    public static Date int(final Date time, final int n) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        gregorianCalendar.add(12, n);
        return gregorianCalendar.getTime();
    }
    
    public static Date new(final Date date, final int n) {
        return new Date(date.getTime() + 86400000L * n);
    }
    
    public static Date try(final Date date, final int n) {
        return new Date(date.getTime() - 86400000L * n);
    }
    
    public static Date a(final Date time, final int n) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        gregorianCalendar.add(2, n * -1);
        return gregorianCalendar.getTime();
    }
    
    public static Date do(final Date time, final int n) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        gregorianCalendar.add(1, n * -1);
        return gregorianCalendar.getTime();
    }
    
    public static String a(final String s, final int n) {
        String format = "";
        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy hh:mm:ss aa");
            format = simpleDateFormat.format(do(simpleDateFormat.parse(s), n));
            return format;
        }
        catch (ParseException ex) {
            System.out.println(ex);
            return format;
        }
    }
    
    public static Date char(final Date time) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        final int value = gregorianCalendar.get(7);
        if (7 == value) {
            gregorianCalendar.add(7, 2);
        }
        else if (value != 0) {
            gregorianCalendar.add(7, 1);
        }
        return gregorianCalendar.getTime();
    }
    
    public static String a(final String s) {
        String format = "";
        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy hh:mm:ss aa");
            format = simpleDateFormat.format(char(simpleDateFormat.parse(s)));
            return format;
        }
        catch (ParseException ex) {
            System.out.println(ex);
            return format;
        }
    }
    
    public static String new(final Date date) {
        return new SimpleDateFormat("M/dd/yyyy hh:mm:ss aa").format(date);
    }
    
    public static Date a(final long n) {
        final TimeZone default1 = TimeZone.getDefault();
        final TimeZone timeZone = TimeZone.getTimeZone("CST");
        if (timeZone == null) {
            System.out.println("Don't know about CST");
        }
        final long n2 = timeZone.getRawOffset();
        final long n3 = default1.getRawOffset();
        final long n4 = n * 1000L;
        final Date date = new Date(n4);
        long n5;
        if (n3 < 0L) {
            n5 = n4 + (n2 - n3);
        }
        else {
            n5 = n4 - (n3 - n2);
        }
        Date date2 = new Date(n5);
        if (default1.inDaylightTime(date)) {
            if (!timeZone.inDaylightTime(date2)) {
                date2 = new Date(n5 - 3600000L);
            }
        }
        else if (timeZone.inDaylightTime(date2)) {
            date2 = new Date(n5 + 3600000L);
        }
        return date2;
    }
    
    public static Date else(final Date time) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        int value = gregorianCalendar.get(11);
        if (value < 23) {
            value = 23;
        }
        final int n = 58;
        gregorianCalendar.set(11, value);
        gregorianCalendar.set(12, n);
        return gregorianCalendar.getTime();
    }
    
    public static Date goto(final Date time) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        int value = gregorianCalendar.get(11);
        if (value > 0) {
            value = 0;
        }
        gregorianCalendar.set(11, value);
        gregorianCalendar.set(12, 0);
        return try(gregorianCalendar.getTime());
    }
    
    public static Date for(final Date time) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        return try(gregorianCalendar.getTime());
    }
    
    public static Date long(final Date time) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        return try(gregorianCalendar.getTime());
    }
    
    public static Date case(final Date time) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        gregorianCalendar.set(12, 0);
        return try(gregorianCalendar.getTime());
    }
    
    public static Date do(final Date time) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        while (gregorianCalendar.get(7) != 1) {
            gregorianCalendar.add(5, -1);
        }
        return gregorianCalendar.getTime();
    }
    
    public static Date byte(final Date date) {
        final Date date2 = new Date(date.getTime());
        date2.setDate(1);
        return try(date2);
    }
    
    public static Date int(final Date time) {
        time.setDate(1);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        gregorianCalendar.get(2);
        for (int i = gregorianCalendar.get(2) / 3; i == i; i = gregorianCalendar.get(2) / 3) {
            gregorianCalendar.add(2, -1);
        }
        gregorianCalendar.add(2, 1);
        return try(gregorianCalendar.getTime());
    }
    
    public static Date if(final Date date) {
        final Date date2 = new Date(date.getTime());
        date2.setMonth(0);
        date2.setDate(1);
        return try(date2);
    }
    
    public static Date try(final Date date) {
        return new Date(date.getTime() / 60000L * 60000L);
    }
    
    public static Date a(final Date date) {
        final TimeZone default1 = TimeZone.getDefault();
        final TimeZone timeZone = TimeZone.getTimeZone("EST");
        if (timeZone == null) {
            System.out.println("Don't know about EST");
        }
        final long n = timeZone.getRawOffset();
        final long n2 = default1.getRawOffset();
        long n3;
        if (n2 < 0L) {
            n3 = n - n2;
        }
        else {
            n3 = (n2 - n) * -1L;
        }
        if (default1.inDaylightTime(date)) {
            if (!timeZone.inDaylightTime(new Date(date.getTime() + n3))) {
                n3 -= 3600000L;
            }
        }
        else if (timeZone.inDaylightTime(new Date(date.getTime() + n3))) {
            n3 += 3600000L;
        }
        return new Date(date.getTime() + n3);
    }
    
    public static Date a(final boolean b) {
        final Date date = new Date();
        Date date2;
        if (b) {
            date2 = a(date);
        }
        else {
            date2 = else(a(date));
        }
        if (date2.getYear() * 10000 + date2.getMonth() * 100 + date2.getDate() < date.getYear() * 10000 + date.getMonth() * 100 + date.getDate()) {
            date2 = new Date(date2.getTime() + 86400000L);
        }
        return date2;
    }
    
    public static Date if(final Date time, final int n) {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        int i = 0;
        final int value = gregorianCalendar.get(7);
        if (value != 7 && value != 1) {
            ++i;
        }
        while (i < n) {
            gregorianCalendar.add(5, -1);
            final int value2 = gregorianCalendar.get(7);
            if (value2 != 7 && value2 != 1) {
                ++i;
            }
        }
        return gregorianCalendar.getTime();
    }
    
    public static String a(final int n, final int n2) {
        String s = "";
        switch (n) {
            case 0: {
                s = "January";
                break;
            }
            case 1: {
                s = "Feburary";
                break;
            }
            case 2: {
                s = "March";
                break;
            }
            case 3: {
                s = "April";
                break;
            }
            case 4: {
                s = "May";
                break;
            }
            case 5: {
                s = "June";
                break;
            }
            case 6: {
                s = "July";
                break;
            }
            case 7: {
                s = "August";
                break;
            }
            case 8: {
                s = "September";
                break;
            }
            case 9: {
                s = "October";
                break;
            }
            case 10: {
                s = "November";
                break;
            }
            case 11: {
                s = "December";
                break;
            }
        }
        if (n2 == -1) {
            return s;
        }
        return s.substring(0, n2);
    }
    
    public static String if(final int n, final int n2) {
        String s = "";
        switch (n) {
            case 0: {
                s = "Sunday";
                break;
            }
            case 1: {
                s = "Monday";
                break;
            }
            case 2: {
                s = "Tuesday";
                break;
            }
            case 3: {
                s = "Wednesday";
                break;
            }
            case 4: {
                s = "Thursday";
                break;
            }
            case 5: {
                s = "Friday";
                break;
            }
            case 6: {
                s = "Saturday";
                break;
            }
        }
        String substring;
        if (s.length() >= n2) {
            substring = s.substring(0, n2);
        }
        else {
            substring = s;
        }
        if (n2 == -1) {
            return s;
        }
        return substring;
    }
}
