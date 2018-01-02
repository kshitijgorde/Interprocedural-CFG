// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

class $G4C
{
    static String $H4C(String s) {
        final String s2 = "45GALPMIJ7DF60KNOSTUV123WXYZQR89BCH";
        s = s.toUpperCase();
        if (s.length() < 5) {
            s = (String.valueOf(s) + "12345").substring(0, 5);
        }
        char c = '\u0017';
        for (int i = 0; i < s.length(); ++i) {
            c ^= s.charAt(i);
        }
        final StringBuffer sb = new StringBuffer();
        for (int j = 0; j < 5; ++j) {
            sb.append(s2.charAt(Math.abs(s.charAt(j) ^ c * (j + 1)) % s2.length()));
        }
        return sb.toString();
    }
}
