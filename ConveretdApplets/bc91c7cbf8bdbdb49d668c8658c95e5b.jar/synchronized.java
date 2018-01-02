// 
// Decompiled by Procyon v0.5.30
// 

public class synchronized
{
    private String Ba;
    private String Ca;
    private static boolean Da;
    private static String Ea = "";
    private static String Fa = "\uba68";
    private static String Ga = "\uba03";
    private static String Ha = "\uba6b";
    private static String Ia = "\uba6d";
    private static String Ja = "\uba76";
    private static String Ka = "\uba6b\uba76";
    private static String La = "\uba23\uba3e\uba36\uba12\uba23\uba3e\uba32";
    private static String Ma = "\uba76\uba68";
    private static String Na = "\uba6d\uba6b\uba68\uba76";
    
    public synchronized(final String ba, final String ca) {
        this.Ba = ba;
        this.Ca = ca;
    }
    
    public static boolean _(final String s, final String s2, final boolean b) {
        for (int i = 0; i < s2.lastIndexOf(synchronized.Ea); ++i) {
            if (s.indexOf(s2.charAt(i)) == -1) {
                return false;
            }
        }
        return !b || b(s2);
    }
    
    private static boolean b(String s) {
        s = s.replace(',', '.');
        if (s.indexOf(synchronized.Fa) != s.lastIndexOf(synchronized.Fa)) {
            return false;
        }
        s = s.replace('e', 'E');
        if (s.indexOf(synchronized.Ga) == -1) {
            if (s.lastIndexOf(synchronized.Ha) > 0) {
                return false;
            }
            if (s.lastIndexOf(synchronized.Ia) > 0) {
                return false;
            }
        }
        else {
            if (s.indexOf(synchronized.Fa) > s.indexOf(synchronized.Ga)) {
                return false;
            }
            if (s.indexOf(synchronized.Ga) != s.lastIndexOf(synchronized.Ga)) {
                return false;
            }
            if (s.lastIndexOf(synchronized.Ha) > 0 && s.indexOf(synchronized.Ga) != s.lastIndexOf(synchronized.Ha) - 1) {
                return false;
            }
            if (s.lastIndexOf(synchronized.Ia) > 0 && s.indexOf(synchronized.Ga) != s.lastIndexOf(synchronized.Ia) - 1) {
                return false;
            }
            final int n = s.lastIndexOf(synchronized.Ea) - (s.indexOf(synchronized.Ga) + 1);
            if (n > 4) {
                return false;
            }
            if (n == 4 && s.charAt(s.indexOf(synchronized.Ga) + 1) != '+' && s.charAt(s.indexOf(synchronized.Ga) + 1) != '-') {
                return false;
            }
            if (s.indexOf(synchronized.Ha, 1) != -1 && s.indexOf(synchronized.Ha, 1) < s.indexOf(synchronized.Ga)) {
                return false;
            }
            if (s.indexOf(synchronized.Ia, 1) != -1 && s.indexOf(synchronized.Ia, 1) < s.indexOf(synchronized.Ga)) {
                return false;
            }
        }
        return true;
    }
    
    public static String b(final String s) {
        final String ga = synchronized.Ga;
        String s2 = synchronized.Ea;
        String s3 = s.trim();
        if (s3.lastIndexOf(synchronized.Ea) == 0) {
            s3 = synchronized.Ja;
        }
        String s4 = s3.replace('e', 'E').replace(',', '.');
        final int index = s4.indexOf(ga);
        if (index != -1) {
            if (index == 0) {
                s4 = synchronized.Ja;
            }
            if (s4.lastIndexOf(synchronized.Ea) - 1 == index) {
                s4 = s4.replace('E', ' ').trim();
            }
        }
        if (s4.indexOf(synchronized.Ha) != 0 && s4.indexOf(synchronized.Ha) == s4.lastIndexOf(synchronized.Ea) - 1) {
            s4 = s4.substring(0, s4.lastIndexOf(synchronized.Ea) - 2);
        }
        if (s4.indexOf(synchronized.Ia) != 0 && s4.indexOf(synchronized.Ia) == s4.lastIndexOf(synchronized.Ea) - 1) {
            s4 = s4.substring(0, s4.lastIndexOf(synchronized.Ea) - 2);
        }
        if (s4.indexOf(synchronized.Ia) == 0) {
            if (s4.indexOf(synchronized.Ia) == s4.lastIndexOf(synchronized.Ea) - 1) {
                s4 = s4.replace('+', '0');
            }
            else {
                s4 = s4.replace('+', ' ').trim();
            }
        }
        if (s4.indexOf(synchronized.Ha) == 0 && s4.indexOf(synchronized.Fa) == 1) {
            s4 = synchronized.Ka + s4.substring(1);
        }
        if (s4.indexOf(synchronized.Fa) == 0) {
            s4 = synchronized.Ja + s4;
        }
        if (s4.indexOf(synchronized.Fa) == s4.lastIndexOf(synchronized.Ea) - 1) {
            s4 = s4.substring(0, s4.lastIndexOf(synchronized.Ea) - 1);
        }
        int i = 1;
        if (s4.indexOf(synchronized.Fa) != 1) {
            while (i == 1) {
                if (s4.lastIndexOf(synchronized.Ja) == s4.indexOf(synchronized.Ja) && s4.charAt(s4.lastIndexOf(synchronized.Ea) - 1) == '0') {
                    i = 0;
                }
                if (s4.indexOf(synchronized.Ha) == 0 && s4.indexOf(synchronized.Ja) == 1) {
                    if (s4.indexOf(synchronized.Fa) != 2) {
                        s4 = synchronized.Ha + s4.substring(2);
                    }
                    if (s4.indexOf(synchronized.Fa) == 2) {
                        i = 0;
                    }
                }
                else if (s4.indexOf(synchronized.Ja) == 0) {
                    s4 = s4.substring(1);
                    if (s4.indexOf(synchronized.Fa) == 1) {
                        i = 0;
                    }
                }
                else {
                    i = 0;
                }
                if (s4.indexOf(synchronized.Ha) == s4.lastIndexOf(synchronized.Ea) - 1) {
                    s4 = synchronized.Ja;
                    i = 0;
                }
            }
        }
        final int index2 = s4.indexOf(synchronized.La);
        final int index3 = s4.indexOf(synchronized.Fa);
        if (index2 != -1 && index3 != -1) {
            for (int n = index2 - 1; n > 2 && s4.charAt(n) == '0'; --n) {
                s4 = s4.substring(0, n) + s4.substring(n + 1);
                if (s4 == synchronized.Ma) {
                    break;
                }
            }
            if (index3 == s4.indexOf(synchronized.La) - 1) {
                s4 = s4.substring(0, index3) + s4.substring(index3 + 1);
            }
        }
        if (s4.indexOf(ga) != -1) {
            final int n2 = s4.lastIndexOf(synchronized.Ea) - (s4.indexOf(ga) + 1);
            if (n2 <= 3) {
                if (s4.charAt(s4.indexOf(ga) + 1) == '+') {
                    for (int j = 1; j < 5 - n2; ++j) {
                        s2 += '0';
                    }
                    s4 = s4.substring(0, s4.indexOf(ga) + 2) + s2 + s4.substring(s4.indexOf(ga) + 2, s4.lastIndexOf(synchronized.Ea));
                }
                else if (s4.charAt(s4.indexOf(ga) + 1) == '-') {
                    for (int k = 1; k < 5 - n2; ++k) {
                        s2 += '0';
                    }
                    s4 = s4.substring(0, s4.indexOf(ga) + 2) + s2 + s4.substring(s4.indexOf(ga) + 2, s4.lastIndexOf(synchronized.Ea));
                }
                s2 = synchronized.Ea;
            }
            if (n2 <= 3) {
                if (s4.charAt(s4.indexOf(ga) + 1) != '+') {
                    if (s4.charAt(s4.indexOf(ga) + 1) != '-') {
                        s2 = synchronized.Ia;
                        for (int l = 1; l < 4 - n2; ++l) {
                            s2 += '0';
                        }
                    }
                }
                else {
                    s2 = synchronized.Ea;
                }
                s4 = s4.substring(0, s4.indexOf(ga) + 1) + s2 + s4.substring(s4.indexOf(ga) + 1, s4.lastIndexOf(synchronized.Ea));
            }
            final String ea = synchronized.Ea;
            for (int n3 = s4.indexOf(ga) + 2; n3 < s4.lastIndexOf(synchronized.Ea) && s4.charAt(n3) == '0'; ++n3) {
                if (n3 == s4.lastIndexOf(synchronized.Ea) - 1) {
                    s4 = s4.substring(0, s4.indexOf(ga));
                    break;
                }
            }
        }
        if (s4.indexOf(ga) != -1 && _(synchronized.Na, s4.substring(0, s4.indexOf(ga)), false)) {
            s4 = synchronized.Ja;
        }
        if (s4.indexOf(48) != -1 && s4.indexOf(46) != -1 && s4.indexOf(48) == s4.lastIndexOf(synchronized.Ea) - 1 && s4.indexOf(46) == s4.lastIndexOf(synchronized.Ea) - 2) {
            s4 = s4.substring(0, s4.lastIndexOf(synchronized.Ea) - 2);
        }
        return s4;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFBA46);
        }
        return new String(array);
    }
    
    static {
        synchronized.Ea = _(synchronized.Ea);
        synchronized.Fa = _(synchronized.Fa);
        synchronized.Ga = _(synchronized.Ga);
        synchronized.Ha = _(synchronized.Ha);
        synchronized.Ia = _(synchronized.Ia);
        synchronized.Ja = _(synchronized.Ja);
        synchronized.Ka = _(synchronized.Ka);
        synchronized.La = _(synchronized.La);
        synchronized.Ma = _(synchronized.Ma);
        synchronized.Na = _(synchronized.Na);
    }
}
