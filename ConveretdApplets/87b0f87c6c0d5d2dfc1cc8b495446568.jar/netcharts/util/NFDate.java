// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Date;

public class NFDate extends Date
{
    private static String[] a;
    private static String[] b;
    private static String[] c;
    private static String[] d;
    
    public NFDate() {
    }
    
    public NFDate(final long n) {
        super(n);
    }
    
    public NFDate(final String s) {
        super(s);
    }
    
    public NFDate(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        super(n, n2, n3, n4, n5, n6);
    }
    
    public long getDiffInSecs(final NFDate nfDate) {
        return (Date.UTC(this.getYear(), this.getMonth(), this.getDate(), this.getHours(), this.getMinutes(), this.getSeconds()) - Date.UTC(nfDate.getYear(), nfDate.getMonth(), nfDate.getDate(), nfDate.getHours(), nfDate.getMinutes(), nfDate.getSeconds())) / 1000L;
    }
    
    public String toString() {
        return this.format("%D-%n-%Y %h:%m:%s");
    }
    
    public String format(final String s) {
        return this.format(s, this);
    }
    
    public String format(final String s, final Date date) {
        final StringBuffer sb = new StringBuffer();
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\\' && i < length - 1) {
                ++i;
                final char char2 = s.charAt(i);
                switch (char2) {
                    case 110: {
                        sb.append('\n');
                        break;
                    }
                    case 116: {
                        sb.append(' ');
                        break;
                    }
                    default: {
                        sb.append('\\');
                        sb.append(char2);
                        break;
                    }
                }
            }
            else if (char1 != '%' || i == length - 1) {
                sb.append(char1);
            }
            else {
                ++i;
                final char char3 = s.charAt(i);
                switch (char3) {
                    case 76:
                    case 108: {
                        sb.append(date.toLocaleString());
                        break;
                    }
                    case 71:
                    case 103: {
                        sb.append(date.toGMTString());
                        break;
                    }
                    case 89: {
                        sb.append(1900 + date.getYear());
                        break;
                    }
                    case 121: {
                        int year = date.getYear();
                        if (year > 99) {
                            year %= 100;
                        }
                        if (year < 10) {
                            sb.append('0');
                        }
                        sb.append(year);
                        break;
                    }
                    case 77: {
                        final int n = date.getMonth() + 1;
                        if (n < 10) {
                            sb.append('0');
                        }
                        sb.append(n);
                        break;
                    }
                    case 78: {
                        sb.append(NFDate.b[date.getMonth()]);
                        break;
                    }
                    case 110: {
                        sb.append(NFDate.a[date.getMonth()]);
                        break;
                    }
                    case 119: {
                        sb.append(NFDate.c[date.getDay()]);
                        break;
                    }
                    case 87: {
                        sb.append(NFDate.d[date.getDay()]);
                        break;
                    }
                    case 68:
                    case 100: {
                        final int date2 = date.getDate();
                        if (date2 < 10) {
                            sb.append('0');
                        }
                        sb.append(date2);
                        break;
                    }
                    case 72:
                    case 104: {
                        final int hours = date.getHours();
                        if (hours < 10) {
                            sb.append('0');
                        }
                        sb.append(hours);
                        break;
                    }
                    case 109: {
                        final int minutes = date.getMinutes();
                        if (minutes < 10) {
                            sb.append('0');
                        }
                        sb.append(minutes);
                        break;
                    }
                    case 83:
                    case 115: {
                        final int seconds = date.getSeconds();
                        if (seconds < 10) {
                            sb.append('0');
                        }
                        sb.append(seconds);
                        break;
                    }
                    default: {
                        sb.append('%');
                        sb.append(char3);
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }
    
    public long parseDate(String string) {
        long n;
        try {
            n = Date.parse(string);
        }
        catch (Throwable t) {
            string = this.format("%D-%n-%Y ") + string;
            n = Date.parse(string);
        }
        return n;
    }
    
    static {
        NFDate.a = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        NFDate.b = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        NFDate.c = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        NFDate.d = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    }
}
