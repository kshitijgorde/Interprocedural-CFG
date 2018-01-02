// 
// Decompiled by Procyon v0.5.30
// 

public class dms
{
    public static String decdeg2dms(final double n, final int n2) {
        return decdeg2dms(n, n2, true);
    }
    
    public static String decdeg2dms(double n, final int n2, final boolean b) {
        boolean b2 = false;
        if (n < 0.0) {
            b2 = true;
            n *= -1.0;
        }
        long n3 = (long)n;
        n = (n - n3) * 60.0;
        long n4 = (long)n;
        n = (n - n4) * 60.0;
        n = Math.round(n * Math.pow(10.0, n2)) / Math.pow(10.0, n2);
        if ((long)n == 60L) {
            n -= 60.0;
            ++n4;
        }
        if (n4 >= 60L) {
            ++n3;
            n4 = 0L;
        }
        while (n3 >= 360L) {
            n3 -= 360L;
        }
        if (b) {
            return new String((b2 ? "-" : " ") + Long.toString(n3) + "° " + ((n4 < 10L) ? "0" : "") + Long.toString(n4) + "' " + ((Double.valueOf(Format.toString(n, n2)) < 10.0) ? "0" : "") + Format.toString(n, n2) + "''");
        }
        return new String((b2 ? "-" : " ") + Long.toString(n3) + ((n4 < 10L) ? "0" : "") + Long.toString(n4) + ((Double.valueOf(Format.toString(n, n2)) < 10.0) ? "0" : "") + Format.toString(n, n2));
    }
    
    public static double dms2decdeg(String s) throws Invalid_Angular_Data {
        int n = 1;
        s = s.trim();
        if (s.length() == 0) {
            throw new Invalid_Angular_Data("No value!");
        }
        int n2 = 0;
        final String s2 = new String("+-.0123456789");
        for (int i = 0; i < s.length(); ++i) {
            if (s2.indexOf(s.substring(i, i + 1)) == -1) {
                throw new Invalid_Angular_Data("non-numeric in " + s);
            }
            if (s2.indexOf(s.substring(i, i + 1)) == 2) {
                if (n2 != 0) {
                    throw new Invalid_Angular_Data("multiple decimal points in " + s);
                }
                if (s.substring(i, s.length()).length() == 1) {
                    throw new Invalid_Angular_Data("nothing follows decimal in" + s);
                }
                n2 = 1;
            }
            if (s2.indexOf(s.substring(i, i + 1)) == 0 || s2.indexOf(s.substring(i, i + 1)) == 1) {
                if (i != 0) {
                    throw new Invalid_Angular_Data("misplaced sign in " + s);
                }
                if (s.length() == 1) {
                    throw new Invalid_Angular_Data("nothing follows sign in '" + s + "'");
                }
                if (s2.indexOf(s.substring(i, i + 1)) == 1) {
                    n = -1;
                }
            }
        }
        s = s.replace('-', '0');
        s = s.replace('+', '0');
        s = "0000000" + s;
        final int index;
        String substring;
        if ((index = s.indexOf(".")) != -1) {
            substring = s.substring(index, s.length());
            s = s.substring(0, index);
        }
        else {
            substring = "0";
        }
        final int length = s.length();
        final String substring2 = s.substring(length - 2, length);
        final String substring3 = s.substring(length - 4, length - 2);
        final String substring4 = s.substring(0, length - 4);
        final double doubleValue = Double.valueOf(substring);
        final int intValue = Integer.valueOf(substring2);
        final int intValue2 = Integer.valueOf(substring3);
        final int intValue3 = Integer.valueOf(substring4);
        if (intValue3 >= 360 || intValue2 >= 60 || intValue >= 60) {
            throw new Invalid_Angular_Data("D, M, or S out of range");
        }
        return n * (intValue3 + intValue2 / 60.0 + (intValue + doubleValue) / 3600.0);
    }
}
