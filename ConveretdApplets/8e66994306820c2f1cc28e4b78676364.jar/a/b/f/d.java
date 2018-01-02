// 
// Decompiled by Procyon v0.5.30
// 

package a.b.f;

import java.io.IOException;

public class d implements i
{
    private h a;
    private h b;
    private int c;
    private static String[] z;
    
    public d(final h h) {
        this(h, new l());
    }
    
    public d(final h h, final h h2) {
        this.a = null;
        this.b = null;
        this.c = -1;
        this.a(h);
        this.b(h2);
    }
    
    private void a(final h a) {
        this.a = a;
    }
    
    protected h a() {
        return this.a;
    }
    
    private void b(final h b) {
        this.b = b;
    }
    
    private h b() {
        return this.b;
    }
    
    public int available() {
        int available = -1;
        int available2 = -1;
        if (this.a() != null) {
            available = this.a().available();
        }
        if (this.b() != null) {
            available2 = this.b().available();
        }
        if (available > 0 || available2 > 0) {
            return 1;
        }
        if (available == 0 || available2 == 0) {
            return 0;
        }
        return -1;
    }
    
    public byte[] a(int n) throws IOException {
        if (n < 0) {
            n = 0;
        }
        byte[] a;
        if (this.a() != null) {
            a = this.a().a(n);
        }
        else {
            a = new byte[0];
        }
        final int n2 = n - a.length;
        byte[] a2 = null;
        Label_0121: {
            if (n2 > 0) {
                if (this.b() == null) {
                    throw new IOException(d.z[1]);
                }
                try {
                    a2 = this.b().a(n2);
                    break Label_0121;
                }
                catch (IOException ex) {
                    throw new IOException(d.z[0] + ex.getMessage());
                }
            }
            a2 = new byte[0];
        }
        final byte[] array = new byte[n];
        System.arraycopy(a, 0, array, 0, a.length);
        System.arraycopy(a2, 0, array, a.length, a2.length);
        this.c = a.length;
        return array;
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "\r4.Z\u001e59*\u0014]==?\u001b]).$\f\u0014=99Z\u001e82%\u0015\ty,9\u0015\u000b08.Z\u001cy2.\u001f\u0019<8k\u0014\b4>.\b]6:k\u0018\u0004-98@]".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'Y';
                    break;
                }
                case 1: {
                    c2 = '\\';
                    break;
                }
                case 2: {
                    c2 = 'K';
                    break;
                }
                case 3: {
                    c2 = 'z';
                    break;
                }
                default: {
                    c2 = '}';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\r4.Z\u001e59*\u0014]==?\u001b]).$\f\u0014=99Z\u0014*|8\u001f\ty($Z\u0013,0'A]73k\u001e\u001c-=k\u0019\u001c7|)\u001f]).$\f\u0014=9/T".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'Y';
                    break;
                }
                case 1: {
                    c4 = '\\';
                    break;
                }
                case 2: {
                    c4 = 'K';
                    break;
                }
                case 3: {
                    c4 = 'z';
                    break;
                }
                default: {
                    c4 = '}';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        d.z = z;
    }
}
