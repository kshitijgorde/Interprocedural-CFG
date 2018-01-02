import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class Pretty
{
    private static boolean eighthsFlag;
    
    public static String commas(final int n) {
        Math.abs(n);
        final String string = new Integer(n).toString();
        String s = "";
        String string2 = "";
        int n2 = -1;
        for (int i = string.length() - 1; i >= 0; --i) {
            if (++n2 != 0 && n2 % 3 == 0) {
                s += ",";
            }
            s += string.charAt(i);
        }
        for (int j = s.length() - 1; j >= 0; --j) {
            string2 += s.charAt(j);
        }
        return string2;
    }
    
    public static String date(final Date date) {
        return date.getMonth() + 1 + "/" + date.getDate() + "/" + date.getYear();
    }
    
    public static String dollars(final int n) {
        String s;
        if (n == 0) {
            s = udollars(n);
        }
        else if (n > 0) {
            s = "+" + udollars(n);
        }
        else {
            s = "-" + udollars(-n);
        }
        return s;
    }
    
    public static int metric(final int n, final int n2, final int n3) {
        final int n4 = n3 / 2;
        int n5 = 0;
        int n6 = 0;
        if (n2 > n4) {
            n5 = 100 * (n2 - n4) / n4;
        }
        if (n < n4) {
            n6 = 100 * n / n4;
        }
        return 101 + (n5 - n6);
    }
    
    public static String udollars(final int n) {
        final int abs = Math.abs(n);
        final int n2 = abs / 8;
        final int n3 = abs % 8;
        String s = "$";
        if (n2 != 0) {
            s += commas(n2);
        }
        if (n3 != 0) {
            if (s.length() != 0) {
                s += " ";
            }
            if (Pretty.eighthsFlag) {
                s = s + new Integer(n3).toString() + "/8";
            }
            else {
                s += (new String[] { "1/8", "1/4", "3/8", "1/2", "5/8", "3/4", "7/8" })[n3 - 1];
            }
        }
        else if (n == 0) {
            s += "0";
        }
        return s;
    }
}
