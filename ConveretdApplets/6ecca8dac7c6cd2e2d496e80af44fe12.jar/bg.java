// 
// Decompiled by Procyon v0.5.30
// 

public class bg
{
    static String a;
    
    static {
        bg.a = a("b\u001aC #\u0015\u001cD(");
    }
    
    static String a(String s, final String s2) {
        final boolean dx = bm.dX;
        final String a = a("z=h\u0001\u001f}8c\f\u0010p3f\u000b\u0015k.y\u0016\u000en)|\u001d\u0003a");
        String s3 = "";
        if (s == null) {
            s = bg.a;
        }
        s += s2;
        int n = 0;
        while (true) {
            while (true) {
                Label_0096: {
                    if (!dx) {
                        break Label_0096;
                    }
                    new StringBuffer().append(s3).append(a.charAt((('\u0003' * s2.charAt(n) ^ s.charAt(n)) + '\u0006') % '\u001a')).toString();
                    final String s4;
                    s3 = s4;
                    ++n;
                }
                if (n != s2.length()) {
                    continue;
                }
                break;
            }
            final String s4 = s3;
            if (!dx) {
                return s4;
            }
            continue;
        }
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
                    c2 = ';';
                    break;
                }
                case 1: {
                    c2 = '\u007f';
                    break;
                }
                case 2: {
                    c2 = '+';
                    break;
                }
                case 3: {
                    c2 = 'E';
                    break;
                }
                default: {
                    c2 = 'Z';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
