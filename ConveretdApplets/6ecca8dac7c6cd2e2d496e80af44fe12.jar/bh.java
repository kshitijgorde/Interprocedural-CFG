import java.util.GregorianCalendar;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class bh
{
    p a;
    
    bh(final p a) {
        this.a = a;
    }
    
    void a(final bu bu) {
        new Date();
        this.a.setTitle(a("zD") + bu.a() + a("wI\"p") + bu.b() + " " + irc.bO.format(new GregorianCalendar().getTime()) + p.a);
    }
    
    static String a(final byte b) {
        final String string = new Byte(b).toString();
        if (string.equals("0")) {
            return a("gT");
        }
        if (string.length() == 1) {
            return "0" + string;
        }
        return string;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'W';
                    break;
                }
                case 1: {
                    c2 = 'd';
                    break;
                }
                case 2: {
                    c2 = '\u0002';
                    break;
                }
                case 3: {
                    c2 = 'P';
                    break;
                }
                default: {
                    c2 = '\u0005';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
