// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cR
{
    public int a;
    private boolean a;
    private boolean b;
    
    public rp_cR(final int n, final boolean b) {
        this.a = 1;
        this.a = true;
        this.b = true;
        this.a(n);
        this.a(true);
    }
    
    public final void a(final int a) {
        if (a >= 0 && a <= 3) {
            this.a = a;
        }
    }
    
    public final boolean a() {
        return this.a == 0;
    }
    
    public final boolean a(final boolean a) {
        final boolean a2 = this.a;
        this.a = a;
        return a2;
    }
    
    public final boolean b(final boolean b) {
        final boolean b2 = this.b;
        this.b = b;
        return b2;
    }
    
    public final rp_eE a(int c) {
        int b = (c = (int)(((c = ((c < 0) ? (-c) : c)) + 79.375) / 158.75)) / 16;
        int a = 0;
        if (this.a) {
            a = b / 12;
            b -= a * 12;
        }
        c %= 16;
        int d = 16;
        if (this.b) {
            if (c == 0) {
                d = 0;
            }
            else {
                while (c % 2 == 0) {
                    c /= 2;
                    d /= 2;
                }
            }
        }
        else {
            if (c > 8) {
                ++b;
                if (this.a && b >= 12) {
                    ++a;
                    b -= 12;
                }
            }
            c = 0;
        }
        final rp_eE rp_eE;
        (rp_eE = new rp_eE()).a = a;
        rp_eE.b = b;
        rp_eE.c = c;
        rp_eE.d = d;
        return rp_eE;
    }
    
    public final String a(final int n) {
        return this.a(n, this.a);
    }
    
    public final String a(final int n, int n2) {
        switch (n2) {
            case 1: {
                return "" + (n + 50) / 100 + " mm";
            }
            case 2: {
                return "" + (n + 500) / 1000 + " cm";
            }
            case 3: {
                int n4;
                int n3;
                if (n < 0) {
                    n3 = (n4 = -n);
                }
                else {
                    n3 = n;
                    n4 = n;
                }
                final int n5 = n4;
                n2 = n3 / 100000;
                final int n6 = (n5 - n2 * 100000) / 10000;
                return ((n < 0) ? "-" : "") + n2 + "." + n6 + (n5 - n2 * 100000 - n6 * 10000 + 500) / 1000 + " m";
            }
            case 0: {
                final rp_eE a = this.a(n);
                final StringBuffer sb = new StringBuffer();
                if (n < 0) {
                    sb.append('-');
                }
                if (a.a > 0) {
                    sb.append(a.a).append("' ");
                }
                sb.append(a.b);
                if (a.c > 0) {
                    sb.append(' ').append(a.c).append('/').append(a.d);
                }
                sb.append('\"');
                return new String(sb);
            }
            default: {
                return "";
            }
        }
    }
    
    public final int a(final String s) {
        return a(s, this.a);
    }
    
    public static int a(String lowerCase, final int n) {
        if (lowerCase == null) {
            return 0;
        }
        if ((lowerCase = lowerCase.trim().toLowerCase()).length() == 0) {
            return 0;
        }
        int n2 = 0;
        int n3 = 1;
        if (lowerCase.charAt(0) == '+') {
            ++n2;
        }
        else if (lowerCase.charAt(0) == '-') {
            ++n2;
            n3 = -1;
        }
        final String substring;
        lowerCase = (substring = lowerCase.substring(n2));
        int n4 = -1;
        if (substring.indexOf(39) != -1 || substring.indexOf(34) != -1 || substring.indexOf(47) != -1) {
            n4 = 0;
        }
        else if (substring.indexOf("cm") != -1) {
            n4 = 2;
        }
        else if (substring.indexOf("mm") != -1) {
            n4 = 1;
        }
        else if (substring.indexOf(109) != -1) {
            n4 = 3;
        }
        if (n4 == -1) {
            n4 = n;
        }
        switch (n4) {
            case 0: {
                return n3 * b(lowerCase);
            }
            case 3: {
                return (int)(n3 * 100000 * a(lowerCase));
            }
            case 2: {
                return (int)(n3 * 1000 * a(lowerCase));
            }
            case 1: {
                return (int)(n3 * 100 * a(lowerCase));
            }
            default: {
                throw new rp_fS();
            }
        }
    }
    
    private static int b(String s) {
        int n = 0;
        String trim = s;
        final int index;
        if ((index = s.indexOf(39)) != -1) {
            try {
                n = 30480 * Integer.valueOf(s.substring(0, index), 10);
            }
            catch (NumberFormatException ex) {
                throw new rp_fS();
            }
            trim = s.substring(index + 1).trim();
        }
        if (trim.length() == 0) {
            return n;
        }
        s = a(trim);
        int intValue;
        try {
            intValue = Integer.valueOf(s, 10);
        }
        catch (NumberFormatException ex2) {
            throw new rp_fS();
        }
        if ((s = trim.substring(s.length()).trim()).length() == 0 || s.charAt(0) == '\"') {
            return n + intValue * 2540;
        }
        if (s.charAt(0) == '/') {
            int n2;
            for (n2 = 1; n2 < s.length() && Character.isDigit(s.charAt(n2)); ++n2) {}
            s.substring(1, n2);
            final String a = a(s.substring(1));
            int intValue2;
            try {
                intValue2 = Integer.valueOf(a, 10);
            }
            catch (NumberFormatException ex3) {
                throw new rp_fS();
            }
            if (intValue < intValue2) {
                return n + intValue * 2540 / intValue2;
            }
            throw new rp_fS();
        }
        else {
            if (!Character.isDigit(s.charAt(0))) {
                throw new rp_fS();
            }
            final int n3 = n + intValue * 2540;
            final int index2;
            if ((index2 = s.indexOf(47)) == -1) {
                throw new rp_fS();
            }
            final String trim2 = s.substring(0, index2).trim();
            s = a(s.substring(index2 + 1));
            int intValue3;
            int intValue4;
            try {
                intValue3 = Integer.valueOf(trim2, 10);
                intValue4 = Integer.valueOf(s, 10);
            }
            catch (NumberFormatException ex4) {
                throw new rp_fS();
            }
            if (intValue3 < intValue4) {
                return n3 + intValue3 * 2540 / intValue4;
            }
            throw new rp_fS();
        }
    }
    
    private static double a(String replace) {
        int n;
        char char1;
        for (replace = replace, n = 0; n < replace.length() && (Character.isDigit(char1 = replace.charAt(n)) || char1 == '.' || char1 == ','); ++n) {}
        replace = replace.substring(0, n).replace(',', '.');
        double doubleValue;
        try {
            doubleValue = Double.valueOf(replace);
        }
        catch (NumberFormatException ex) {
            throw new rp_fS();
        }
        return doubleValue;
    }
    
    private static String a(final String s) {
        int n;
        for (n = 0; n < s.length() && Character.isDigit(s.charAt(n)); ++n) {}
        return s.substring(0, n);
    }
}
