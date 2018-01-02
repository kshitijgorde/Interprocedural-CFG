import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageLatTime
{
    Calendar startCal;
    Calendar endCal;
    SimpleDateFormat df;
    SimpleDateFormat df2;
    public long startMS;
    public long endMS;
    
    ImageLatTime(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.startCal = Calendar.getInstance();
        this.endCal = Calendar.getInstance();
        this.df = new SimpleDateFormat("yyyy-MM-dd");
        this.df2 = new SimpleDateFormat("yyyyMMdd");
        this.startCal.set(n, n2 - 1, n3, 12, 0, 0);
        this.endCal.set(n4, n5 - 1, n6, 12, 0, 0);
        final Date time = this.startCal.getTime();
        final Date time2 = this.endCal.getTime();
        this.startMS = time.getTime();
        this.endMS = time2.getTime();
    }
    
    String dateToLatStr(final String s) {
        String latStr;
        try {
            latStr = this.getLatStr(this.df.parse(s).getTime());
        }
        catch (Exception ex) {
            return null;
        }
        return latStr;
    }
    
    Calendar getCalendar(final double n) {
        final Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(this.getMS(n)));
        return instance;
    }
    
    String getDateStr(final double n) {
        return this.df.format(this.getCalendar(n).getTime());
    }
    
    double getLat(final long n) {
        return 90.0 - 180L * (this.endMS - n) / (this.endMS - this.startMS);
    }
    
    String getLatStr(final long n) {
        final double lat = this.getLat(n);
        String s;
        if (lat >= 0.0) {
            s = String.valueOf(lat) + " N";
        }
        else {
            s = String.valueOf(-1.0 * lat) + " S";
        }
        return s;
    }
    
    long getMS(final double n) {
        return this.endMS + (long)((this.startMS - this.endMS) * (90.0 - n) / 180.0);
    }
    
    String toDateStr2(final String s) {
        String format;
        try {
            final Date parse = this.df.parse(s);
            final Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            format = this.df2.format(instance.getTime());
        }
        catch (Exception ex) {
            return null;
        }
        return format;
    }
}
