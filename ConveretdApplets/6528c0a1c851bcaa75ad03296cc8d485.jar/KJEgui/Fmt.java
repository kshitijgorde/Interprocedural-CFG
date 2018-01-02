// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

public class Fmt
{
    public static final int FMT_DOLLARS = 3;
    public static final int FMT_PERCENT = 4;
    public static final int FMT_NUMBER = 5;
    public static final int FMT_INPUT = 6;
    public static String sCP;
    public static String sCS;
    public static String sPP;
    public static String sPS;
    public static String sNP;
    public static String sNS;
    public static String sPC;
    public static String sPD;
    public static String sCC;
    public static String sCD;
    public static String YES;
    public static String NO;
    public static boolean bEuropeDecimals;
    public static boolean ACCOUNTING_NEGATIVE;
    
    static {
        Fmt.sCP = "$";
        Fmt.sCS = "";
        Fmt.sPP = "";
        Fmt.sPS = "%";
        Fmt.sNP = "";
        Fmt.sNS = "";
        Fmt.sPC = ",";
        Fmt.sPD = ".";
        Fmt.sCC = ",";
        Fmt.sCD = ".";
        Fmt.YES = "yes";
        Fmt.NO = "no";
        Fmt.bEuropeDecimals = false;
        Fmt.ACCOUNTING_NEGATIVE = false;
    }
    
    public static String axisDollars(final double n) {
        return (n > 100.0) ? number(n, 0, true, false, Fmt.sCP, Fmt.sCS, Fmt.sCC, Fmt.sCD, 0, false, true, ' ') : number(n, 2, true, true, Fmt.sCP, Fmt.sCS, Fmt.sCC, Fmt.sCD, 0, false, true, ' ');
    }
    
    public static String dollars(final double n) {
        return number(n, 0, true, false, Fmt.sCP, Fmt.sCS, Fmt.sCC, Fmt.sCD, 0, false, true, ' ');
    }
    
    public static String dollars(final double n, final int n2) {
        return number(n, n2, true, true, Fmt.sCP, Fmt.sCS, Fmt.sCC, Fmt.sCD, 0, false, true, ' ');
    }
    
    public static String fmt(final int n, final double n2, final int n3) {
        switch (n) {
            case 3: {
                return dollars(n2, n3);
            }
            case 4: {
                return percent(n2 / 100.0, n3);
            }
            case 6: {
                return input(n2, n3);
            }
            default: {
                return number(n2, n3);
            }
        }
    }
    
    public static String input(final double n) {
        return number(n, 0, false, false, "", "", "", Fmt.sPD, 0, false, true, ' ');
    }
    
    public static String input(final double n, final int n2) {
        return number(n, n2, false, true, "", "", "", Fmt.sPD, 0, false, true, ' ');
    }
    
    public static String number(final double n) {
        return number(n, 0, true, false, Fmt.sNP, Fmt.sNS, Fmt.sPC, Fmt.sPD, 0, false, true, ' ');
    }
    
    public static String number(final double n, final int n2) {
        return number(n, n2, true, false, Fmt.sNP, Fmt.sNS, Fmt.sPC, Fmt.sPD, 0, false, true, ' ');
    }
    
    public static String number(double n, final int n2, final boolean b, final boolean b2, String s, String string, final String s2, final String s3, final int n3, final boolean b3, final boolean b4, final char c) {
        if (b4 && n2 < 10) {
            n = ((n2 > 0) ? (Math.round(n * Math.pow(10.0, n2)) / Math.pow(10.0, n2)) : Math.round(n));
        }
        final boolean b5 = n < 0.0;
        if (b5) {
            n *= -1.0;
        }
        long n4 = (long)n;
        String s4 = "";
        if (n2 > 0) {
            final String string2 = "000000000000000" + String.valueOf((long)(Math.round(n * Math.pow(10.0, (n2 > 10) ? 10 : n2)) - n4 * Math.pow(10.0, (n2 > 10) ? 10 : n2)));
            s4 = String.valueOf(s3) + string2.substring((n2 > 10) ? (string2.length() - 10) : (string2.length() - n2), string2.length());
        }
        if (n2 == 99) {
            int length;
            for (length = s4.length(); s4.substring(length - 1, length).equals("0"); --length) {}
            s4 = s4.substring(0, length);
            if (s4.equals(s3)) {
                s4 = "";
            }
        }
        String s5 = String.valueOf(n4);
        if (b && n > 999.0) {
            s5 = "";
            while (n4 > 0L) {
                final String string3 = "000" + (n4 - n4 / 1000L * 1000L);
                s5 = String.valueOf(string3.substring(string3.length() - 3, string3.length())) + s2 + s5;
                n4 /= 1000L;
            }
            if (s5.length() > 1) {
                s5 = s5.substring(0, s5.length() - 1);
            }
            int n5 = 0;
            if (s5.length() > 1) {
                while (s5.charAt(n5) == '0') {
                    ++n5;
                }
                if (n5 > 0) {
                    s5 = s5.substring(n5, s5.length());
                }
            }
        }
        String s6 = String.valueOf(s5) + s4;
        if (b5) {
            if (Fmt.ACCOUNTING_NEGATIVE) {
                s = "(" + s;
                string = String.valueOf(string) + ")";
            }
            else {
                s = "-" + s;
            }
        }
        final int n6 = n3 - s6.length() - string.length() - s.length();
        if (!b3) {
            s6 = String.valueOf(s) + s6;
        }
        if (n6 > 0) {
            final char[] array = new char[n6];
            for (int i = 0; i < n6; ++i) {
                array[i] = c;
            }
            s6 = String.valueOf(s6) + new String(array);
        }
        if (b3) {
            s6 = String.valueOf(s) + s6;
        }
        return String.valueOf(s6) + string;
    }
    
    public static String percent(final double n) {
        return number(n * 100.0, 0, true, false, Fmt.sPP, Fmt.sPS, Fmt.sPC, Fmt.sPD, 0, false, true, ' ');
    }
    
    public static String percent(final double n, final int n2) {
        return number(n * 100.0, n2, true, true, Fmt.sPP, Fmt.sPS, Fmt.sPC, Fmt.sPD, 0, false, true, ' ');
    }
    
    public static double round(final double n, final int n2) {
        return Math.round(n * Math.pow(10.0, n2)) / Math.pow(10.0, n2);
    }
    
    public static void setDecimalType(final String s) {
        if (s.equals("TRUE")) {
            Fmt.sPC = ".";
            Fmt.sPD = ",";
            Fmt.sCC = ".";
            Fmt.sCD = ",";
            Fmt.bEuropeDecimals = true;
        }
        else if (s.equals("FRANCE")) {
            Fmt.sPC = " ";
            Fmt.sPD = ",";
            Fmt.sCC = " ";
            Fmt.sCD = ",";
            Fmt.bEuropeDecimals = true;
        }
        else {
            Fmt.sPC = ",";
            Fmt.sPD = ".";
            Fmt.sCC = ",";
            Fmt.sCD = ".";
            Fmt.bEuropeDecimals = false;
        }
    }
    
    public static String yesno(final double n) {
        return (n == 0.0) ? Fmt.NO : Fmt.YES;
    }
    
    public static String yesno(final String s) {
        return s.equals("0") ? Fmt.NO : Fmt.YES;
    }
    
    public static String yesno(final boolean b) {
        return b ? Fmt.NO : Fmt.YES;
    }
}
