// 
// Decompiled by Procyon v0.5.30
// 

public class t extends s
{
    String c;
    private static final String[] z;
    
    public t(final String c) {
        if (!a(c)) {
            throw new IllegalArgumentException(t.z[1] + c + t.z[0]);
        }
        this.c = c;
    }
    
    public String a() {
        return this.c;
    }
    
    public int hashCode() {
        return this.c.hashCode();
    }
    
    boolean a(final r r) {
        return r instanceof t && this.c.equals(((t)r).c);
    }
    
    public String toString() {
        return this.a();
    }
    
    private static boolean a(final String s) {
        if (s.length() < 3 || s.charAt(1) != '.') {
            return false;
        }
        final char char1 = s.charAt(0);
        if (char1 < '0' || char1 > '2') {
            return false;
        }
        boolean b = false;
        for (int i = s.length() - 1; i >= 2; --i) {
            final char char2 = s.charAt(i);
            if ('0' <= char2 && char2 <= '9') {
                b = true;
            }
            else {
                if (char2 != '.') {
                    return false;
                }
                if (!b) {
                    return false;
                }
                b = false;
            }
        }
        return b;
    }
    
    static {
        final String[] z2 = new String[2];
        final int n = 0;
        final char[] charArray = "\u000b\u001d\u0003!4J\u001dL\u001a]o".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '+';
                    break;
                }
                case 1: {
                    c2 = 's';
                    break;
                }
                case 2: {
                    c2 = 'l';
                    break;
                }
                case 3: {
                    c2 = 'U';
                    break;
                }
                default: {
                    c2 = '\u0014';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "X\u0007\u001e<zLS".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '+';
                    break;
                }
                case 1: {
                    c4 = 's';
                    break;
                }
                case 2: {
                    c4 = 'l';
                    break;
                }
                case 3: {
                    c4 = 'U';
                    break;
                }
                default: {
                    c4 = '\u0014';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        z = z2;
    }
}
