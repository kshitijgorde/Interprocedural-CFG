import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class TimeAndDateFormatter
{
    String formattedDate;
    String[] daysOfWeek;
    String[] monthsOfYear;
    
    TimeAndDateFormatter(final Date date, final String s) {
        this.daysOfWeek = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        this.monthsOfYear = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final int hours = date.getHours();
        final int minutes = date.getMinutes();
        final int seconds = date.getSeconds();
        final int day = date.getDay();
        final int date2 = date.getDate();
        final int month = date.getMonth();
        final int year = date.getYear();
        String s2;
        String s3;
        if (hours == 0) {
            s2 = new String("12");
            s3 = new String("am");
        }
        else if (hours > 12) {
            s2 = new String("" + (hours - 12));
            s3 = new String("pm");
        }
        else if (hours == 12) {
            s2 = new String("12");
            s3 = new String("pm");
        }
        else {
            s2 = new String("" + hours);
            s3 = new String("am");
        }
        String s4;
        if (minutes < 10) {
            s4 = new String("0" + minutes);
        }
        else {
            s4 = new String("" + minutes);
        }
        String s5;
        if (seconds < 10) {
            s5 = new String("0" + seconds);
        }
        else {
            s5 = new String("" + seconds);
        }
        this.formattedDate = new String(this.daysOfWeek[day] + ", " + this.monthsOfYear[month] + " " + new String("" + date2) + ", " + new String("" + (1900 + year)) + "  " + s + "  " + s2 + ":" + s4 + ":" + s5 + " " + s3);
    }
    
    public String toString() {
        return this.formattedDate;
    }
}
