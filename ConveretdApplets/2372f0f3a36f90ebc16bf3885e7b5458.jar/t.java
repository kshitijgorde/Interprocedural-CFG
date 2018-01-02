// 
// Decompiled by Procyon v0.5.30
// 

public class t
{
    public static byte[] a;
    public byte[][] b;
    public int[] c;
    public int d;
    public byte[] e;
    
    public void a() {
        this.b = null;
        this.d = 0;
        this.e = null;
    }
    
    public int a(final w w) {
        int d = w.d(32);
        if (d < 0) {
            this.b();
            return -1;
        }
        this.e = new byte[d + 1];
        int n = 0;
        while (d-- != 0) {
            this.e[n++] = (byte)w.d(8);
        }
        this.d = w.d(32);
        if (this.d < 0) {
            this.b();
            return -1;
        }
        this.b = new byte[this.d + 1][];
        this.c = new int[this.d + 1];
        for (int i = 0; i < this.d; ++i) {
            int d2 = w.d(32);
            if (d2 < 0) {
                this.b();
                return -1;
            }
            this.c[i] = d2;
            this.b[i] = new byte[d2 + 1];
            int n2 = 0;
            while (d2-- != 0) {
                this.b[i][n2++] = (byte)w.d(8);
            }
        }
        if (w.d(1) != 1) {
            this.b();
            return -1;
        }
        return 0;
    }
    
    public void b() {
        for (int i = 0; i < this.d; ++i) {
            this.b[i] = null;
        }
        this.b = null;
        this.e = null;
    }
    
    static {
        t.a = zkmToString("\u001a \u0013\u0006o\u001f").getBytes();
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'l';
                    break;
                }
                case 1: {
                    c2 = 'O';
                    break;
                }
                case 2: {
                    c2 = 'a';
                    break;
                }
                case 3: {
                    c2 = 'd';
                    break;
                }
                default: {
                    c2 = '\u0006';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
