import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class RFC822Date extends Date
{
    String RFCString;
    private static final String[] dayOfWeek;
    private static final String[] monthsOfYear;
    
    public RFC822Date() {
    }
    
    public RFC822Date(final String s) {
        super(s);
    }
    
    public String toString() {
        final String s = RFC822Date.dayOfWeek[this.getDay()];
        final String s2 = RFC822Date.monthsOfYear[this.getMonth()];
        final int date = this.getDate();
        String s3;
        if (date < 10) {
            s3 = "0" + date;
        }
        else {
            s3 = String.valueOf(date);
        }
        final String string = String.valueOf(this.getYear() + 1900) + " ";
        final int hours = this.getHours();
        final int minutes = this.getMinutes();
        final int seconds = this.getSeconds();
        String s4;
        if (hours < 10) {
            s4 = "0" + hours;
        }
        else {
            s4 = String.valueOf(hours);
        }
        String s5;
        if (minutes < 10) {
            s5 = "0" + minutes;
        }
        else {
            s5 = String.valueOf(minutes);
        }
        String s6;
        if (seconds < 10) {
            s6 = "0" + seconds;
        }
        else {
            s6 = String.valueOf(seconds);
        }
        final String string2 = String.valueOf(s4) + ":" + s5 + ":" + s6;
        int n = this.getTimezoneOffset() * 100 / -60;
        boolean b;
        if (n < 0) {
            b = true;
            n *= -1;
        }
        else {
            b = false;
        }
        String s8;
        if (n < 1000) {
            String s7;
            if (b) {
                s7 = " -";
            }
            else {
                s7 = " +";
            }
            s8 = String.valueOf(s7) + "0" + n;
        }
        else {
            String s9;
            if (b) {
                s9 = " -";
            }
            else {
                s9 = " +";
            }
            s8 = String.valueOf(s9) + n;
        }
        return String.valueOf(s) + s3 + s2 + string + string2 + s8;
    }
    
    public String toShort() {
        int month = this.getMonth();
        String s;
        if (++month < 10) {
            s = " " + month;
        }
        else {
            s = String.valueOf(month);
        }
        final int date = this.getDate();
        String s2;
        if (date < 10) {
            s2 = "0" + date;
        }
        else {
            s2 = String.valueOf(date);
        }
        final String value = String.valueOf(this.getYear());
        final String substring = this.toString().substring(17, 25);
        final String substring2 = substring.substring(0, 2);
        final String substring3 = substring.substring(2);
        final int intValue = new Integer(substring2);
        String s3;
        if (intValue == 0) {
            s3 = "12" + substring3 + " AM";
        }
        else if (intValue == 12) {
            s3 = "12" + substring3 + " PM";
        }
        else if (intValue > 12) {
            s3 = String.valueOf(intValue - 12) + substring3 + " PM";
        }
        else {
            s3 = String.valueOf(intValue) + substring3 + " AM";
        }
        return String.valueOf(s) + "/" + (int)new Integer(s2) + "/" + value + " " + s3;
    }
    
    static {
        dayOfWeek = new String[] { "Sun, ", "Mon, ", "Tue, ", "Wed, ", "Thu, ", "Fri, ", "Sat, " };
        monthsOfYear = new String[] { " Jan ", " Feb ", " Mar ", " Apr ", " May ", " Jun ", " Jul ", " Aug ", " Sep ", " Oct ", " Nov ", " Dec " };
    }
}
