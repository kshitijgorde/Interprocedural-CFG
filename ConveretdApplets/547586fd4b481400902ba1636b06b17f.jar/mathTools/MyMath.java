// 
// Decompiled by Procyon v0.5.30
// 

package mathTools;

import java.text.DecimalFormat;
import parser.Function;

public class MyMath
{
    public static void wait(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {}
    }
    
    public static double getConstant(final String s) {
        final Function function = new Function(s);
        if (function.isCompiled()) {
            double value = function.value(0.0);
            if (!function.isConstant()) {
                value = Double.NaN;
            }
            if (Double.isInfinite(value) || Double.isNaN(value)) {
                value = Double.NaN;
            }
            return value;
        }
        return Double.NaN;
    }
    
    public static double power(final double n, final int n2) {
        double n3 = 1.0;
        for (int i = 0; i < Math.abs(n2); ++i) {
            n3 *= n;
        }
        if (n2 < 0) {
            return 1.0 / n3;
        }
        return n3;
    }
    
    public static long posIntPower(final int n, final int n2) {
        long n3 = 1L;
        for (int i = 0; i < Math.abs(n2); ++i) {
            n3 *= n;
        }
        return n3;
    }
    
    public static double round2(final double n) {
        return Math.round(100.0 * n) / 100.0;
    }
    
    public static double roundp(final double n, final int n2) {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            return Double.NaN;
        }
        return Math.round(n * power(10.0, n2)) / power(10.0, n2);
    }
    
    public static String round(final double n, final int n2) {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            return "UNDEFINED";
        }
        String string = "#.";
        for (int i = 1; i <= n2; ++i) {
            string = String.valueOf(string) + "0";
        }
        String s;
        for (s = new DecimalFormat(string).format(n); s.length() < 4 + n2; s = " " + s) {}
        return s;
    }
    
    public static String roundNoLeading(final double n, final int n2) {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            return "UNDEFINED";
        }
        String string = "#.";
        for (int i = 1; i <= n2; ++i) {
            string = String.valueOf(string) + "0";
        }
        return new DecimalFormat(string).format(n);
    }
    
    public static String rndStr(final double n, final int n2) {
        return rndStr(n, n2, 2, 10);
    }
    
    public static String rndStrSci(final double n, final int n2) {
        return rndStr(n, n2, -1, 0);
    }
    
    public static String rndStr(double n, final int n2, final int n3, final int n4) {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            return Double.toString(n);
        }
        final boolean b = n < 0.0;
        n = Math.abs(n);
        int n5 = (int)(Math.floor(Math.log(n) / Math.log(10.0)) + 1.0);
        n *= power(10.0, -n5);
        n = roundp(n, n2);
        if (n >= 1.0) {
            n /= 10.0;
            ++n5;
        }
        if (n5 < -500) {
            n5 = 0;
        }
        final String substring = String.valueOf(n).substring(2);
        final StringBuffer sb = new StringBuffer();
        if (n5 < -n3 || n5 > n4) {
            sb.append(substring);
            if (sb.length() == 1) {
                sb.append(".0");
            }
            else {
                sb.insert(1, ".");
            }
            sb.append("e" + (n5 - 1));
            if (b) {
                sb.insert(0, "-");
            }
            return new String(sb);
        }
        for (int i = n5; i < 0; ++i) {
            sb.append("0");
        }
        sb.append(substring);
        if (n5 <= 0) {
            sb.insert(0, "0.");
        }
        else if (n5 < sb.length()) {
            sb.insert(n5, ".");
        }
        else {
            for (int length = sb.length(), j = 0; j < n5 - length; ++j) {
                sb.append("0");
            }
        }
        if (b) {
            sb.insert(0, "-");
        }
        return new String(sb);
    }
    
    public static String rational(final double n, final int n2, final int n3) {
        int n4 = 0;
        int n5 = 0;
        for (int i = 1; i <= n2; ++i) {
            if (Math.abs(n * i - Math.round(n * i)) < i * Math.pow(10.0, -n3)) {
                n5 = i;
                n4 = (int)Math.round(n * n5);
                break;
            }
        }
        if (n5 == 0) {
            return roundNoLeading(n, n3);
        }
        if (n5 == 1) {
            return String.valueOf(n4);
        }
        return n4 + "/" + n5;
    }
    
    public static String rationalPI(final double n, final int n2, final int n3) {
        if (n > power(10.0, n3 - 2)) {
            return String.valueOf(n);
        }
        int n4 = 0;
        int n5 = 0;
        final double n6 = n / 3.141592653589793;
        for (int i = 1; i <= n2; ++i) {
            if (Math.abs(n6 * i - Math.round(n6 * i)) < i * Math.pow(10.0, -n3) / 3.141592653589793 / 2.0) {
                n5 = i;
                n4 = (int)Math.round(n6 * n5);
                break;
            }
        }
        if (n5 == 0) {
            return roundNoLeading(n, n3);
        }
        if (n4 == 0) {
            return "0";
        }
        if (n5 == 1) {
            if (n4 == 1) {
                return "pi";
            }
            return n4 + "pi";
        }
        else {
            if (n4 == 1) {
                return "pi/" + n5;
            }
            return n4 + "pi/" + n5;
        }
    }
    
    public static double roundPI(final double n, final int n2, final int n3) {
        final double n4 = n / 3.141592653589793;
        for (int i = 1; i <= n2; ++i) {
            if (Math.abs(n4 * i - Math.round(n4 * i)) < i * Math.pow(10.0, -n3) / 3.141592653589793 / 2.0) {
                final int n5 = i;
                return (int)Math.round(n4 * n5) * 3.141592653589793 / n5;
            }
        }
        return roundp(n, n3);
    }
    
    public static int signum(final double n) {
        return (n < 0.0) ? -1 : ((n > 0.0) ? 1 : 0);
    }
    
    double bisect(final Function function, double n, double n2, final double n3) {
        final int signum = signum(function.value(n));
        do {
            final double n4 = (n + n2) / 2.0;
            if (signum(function.value(n4)) != signum) {
                n2 = n4;
            }
            else {
                n = n4;
            }
        } while (Math.abs(n2 - n) / (1.0 + Math.abs(n2)) > n3);
        return (n + n2) / 2.0;
    }
}
