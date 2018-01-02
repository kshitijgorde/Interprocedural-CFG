import java.awt.Color;
import java.awt.Font;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class SIRdata
{
    private static int i;
    private static StringTokenizer st;
    
    public static String IntToStr(final int n, int n2, final int n3) {
        String s = "";
        final String value = String.valueOf(n);
        final int length = value.length();
        if (n2 < 0) {
            n2 = length;
        }
        if (length > n2) {
            SIRdata.i = 0;
            while (SIRdata.i < n2) {
                s += "#";
                ++SIRdata.i;
            }
        }
        else {
            switch (n3) {
                case 1: {
                    final int n4 = (n2 - length) / 2;
                    SIRdata.i = 0;
                    while (SIRdata.i < n4) {
                        s += " ";
                        ++SIRdata.i;
                    }
                    s += value;
                    SIRdata.i = 0;
                    while (SIRdata.i < n2 - length - n4) {
                        s += " ";
                        ++SIRdata.i;
                    }
                    break;
                }
                case 2: {
                    SIRdata.i = length;
                    while (SIRdata.i < n2) {
                        s += " ";
                        ++SIRdata.i;
                    }
                    s += value;
                    break;
                }
                default: {
                    s = value;
                    SIRdata.i = length;
                    while (SIRdata.i < n2) {
                        s += " ";
                        ++SIRdata.i;
                    }
                    break;
                }
            }
        }
        return s;
    }
    
    public static String DoubleToStr(final double n, int max) {
        max = Math.max(0, max);
        if (max == 0 && n < 1000.0 && n > -1000.0) {
            return Math.round(n) + "";
        }
        final long n2 = (long)Math.pow(10.0, max);
        final long round = Math.round(n * n2);
        final long n3 = Math.abs(round) % n2 + n2;
        String s = "";
        if (max > 0) {
            s = "." + (n3 + "").substring(1);
        }
        long n4 = round / n2;
        if (n4 == 0L) {
            String s2;
            if (n < 0.0) {
                s2 = "-0" + s;
            }
            else {
                s2 = "0" + s;
            }
            return s2;
        }
        if (n4 > 0L) {
            while (n4 >= 1L) {
                final long n5 = n4 % 1000L + 1000L;
                if (n5 >= 1000L && n4 >= 1000L) {
                    s = " " + (n5 + "").substring(1) + s;
                }
                else {
                    s = n4 % 1000L + s;
                }
                n4 /= 1000L;
            }
        }
        else if (n4 < 0L) {
            while (n4 <= -1L) {
                final long n6 = n4 % 1000L - 1000L;
                if (n6 <= -1000L && n4 <= -1000L) {
                    s = " " + (n6 + "").substring(2) + s;
                }
                else {
                    s = n4 % 1000L + s;
                }
                n4 /= 1000L;
            }
        }
        return s;
    }
    
    public static String FormatStr(final String s, int n, final int n2) {
        String s2 = "";
        final int length = s.length();
        if (n < 0) {
            n = length;
        }
        String s3 = null;
        switch (n2) {
            case 1: {
                if (length > n) {
                    s3 = s.substring((length - n) / 2, n);
                    break;
                }
                final int n3 = (n - length) / 2;
                SIRdata.i = 0;
                while (SIRdata.i < n3) {
                    s2 += " ";
                    ++SIRdata.i;
                }
                s3 = s2 + s;
                SIRdata.i = 0;
                while (SIRdata.i < n - length - n3) {
                    s3 += " ";
                    ++SIRdata.i;
                }
                break;
            }
            case 2: {
                if (length > n) {
                    s3 = s.substring(length - n, n);
                    break;
                }
                SIRdata.i = length;
                while (SIRdata.i < n) {
                    s2 += " ";
                    ++SIRdata.i;
                }
                s3 = s2 + s;
                break;
            }
            default: {
                if (length > n) {
                    s3 = s.substring(0, n);
                    break;
                }
                s3 = s;
                SIRdata.i = length;
                while (SIRdata.i < n) {
                    s3 += " ";
                    ++SIRdata.i;
                }
                break;
            }
        }
        return s3;
    }
    
    public static String verifystr(final String s, final String s2) {
        String s3;
        if (s == null) {
            s3 = s2;
        }
        else {
            s3 = s;
        }
        return s3;
    }
    
    public static Font verifyfnt(String s, final String s2, final String s3) {
        int n;
        for (s += "  ", n = 0; n < s.length() & s.substring(n, n + 1).equals(" "); ++n) {}
        int n2;
        for (s = s.substring(n, s.length()), n2 = 0; n2 < s.length() & !s.substring(n2, n2 + 1).equals(" "); ++n2) {}
        s = s.substring(0, n2);
        SIRdata.st = new StringTokenizer(verifystr(s, s2), s3);
        Font font;
        try {
            String nextToken;
            if (SIRdata.st.hasMoreTokens()) {
                nextToken = SIRdata.st.nextToken();
            }
            else {
                nextToken = "Courier";
            }
            String upperCase;
            if (SIRdata.st.hasMoreTokens()) {
                upperCase = SIRdata.st.nextToken().toUpperCase();
            }
            else {
                upperCase = "N";
            }
            int int1;
            if (SIRdata.st.hasMoreTokens()) {
                int1 = Integer.parseInt(SIRdata.st.nextToken());
            }
            else {
                int1 = 12;
            }
            if (upperCase.equals("B")) {
                font = new Font(nextToken, 1, int1);
            }
            else if (upperCase.equals("I")) {
                font = new Font(nextToken, 2, int1);
            }
            else if (upperCase.equals("BI") || upperCase.equals("IB")) {
                font = new Font(nextToken, 3, int1);
            }
            else {
                font = new Font(nextToken, 0, int1);
            }
        }
        catch (Exception ex) {
            font = new Font("Courier", 0, 12);
        }
        return font;
    }
    
    public static Color verifyclr(String s, final String s2, final String s3) {
        int n;
        for (s += "  ", n = 0; n < s.length() & s.substring(n, n + 1).equals(" "); ++n) {}
        int n2;
        for (s = s.substring(n, s.length()), n2 = 0; n2 < s.length() & !s.substring(n2, n2 + 1).equals(" "); ++n2) {}
        s = s.substring(0, n2);
        SIRdata.st = new StringTokenizer(verifystr(s, s2), s3);
        Color color;
        try {
            int abs;
            if (SIRdata.st.hasMoreTokens()) {
                abs = Math.abs(Integer.parseInt(SIRdata.st.nextToken()));
            }
            else {
                abs = 0;
            }
            int abs2;
            if (SIRdata.st.hasMoreTokens()) {
                abs2 = Math.abs(Integer.parseInt(SIRdata.st.nextToken()));
            }
            else {
                abs2 = 0;
            }
            int abs3;
            if (SIRdata.st.hasMoreTokens()) {
                abs3 = Math.abs(Integer.parseInt(SIRdata.st.nextToken()));
            }
            else {
                abs3 = 0;
            }
            color = new Color(abs, abs2, abs3);
        }
        catch (Exception ex) {
            color = new Color(0, 0, 0);
        }
        return color;
    }
    
    public static boolean verifybool(String substring, final String s) {
        boolean b = false;
        if (substring == null || substring.length() < 1) {
            substring = s;
        }
        substring = substring.toLowerCase().substring(0, 1);
        if (substring.equals("t")) {
            b = true;
        }
        return b;
    }
    
    public static int verifyint(String s) {
        int n;
        for (s += "  ", n = 0; n < s.length() & s.substring(n, n + 1).equals(" "); ++n) {}
        int n2;
        for (s = s.substring(n, s.length()), n2 = 0; n2 < s.length() & !s.substring(n2, n2 + 1).equals(" "); ++n2) {}
        s = s.substring(0, n2);
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (Exception ex) {
            int1 = 0;
        }
        return int1;
    }
    
    public static double verifydbl(String s) {
        int n;
        for (s += "  ", n = 0; n < s.length() & s.substring(n, n + 1).equals(" "); ++n) {}
        int n2;
        for (s = s.substring(n, s.length()), n2 = 0; n2 < s.length() & !s.substring(n2, n2 + 1).equals(" "); ++n2) {}
        s = s.substring(0, n2);
        double n3;
        try {
            n3 = (float)(Object)Double.valueOf(s);
        }
        catch (Exception ex) {
            n3 = 0.0;
        }
        return n3;
    }
}
