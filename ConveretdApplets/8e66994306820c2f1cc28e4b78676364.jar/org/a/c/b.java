// 
// Decompiled by Procyon v0.5.30
// 

package org.a.c;

import java.io.InputStream;

public class b implements f
{
    private static Boolean a;
    private d b;
    static /* synthetic */ Class c;
    private static String[] z;
    
    public static boolean a() {
        if (b.a != null) {
            return b.a;
        }
        try {
            Class.forName(b.z[0]);
            b.a = new Boolean(true);
            return true;
        }
        catch (Throwable t) {
            b.a = new Boolean(false);
            return false;
        }
    }
    
    public static boolean a(final InputStream inputStream) {
        return a() && new c().a(inputStream);
    }
    
    public static boolean a(final String s) {
        return a() && a(((b.c == null) ? (b.c = b(b.z[1])) : b.c).getResourceAsStream(s));
    }
    
    public b() {
        if (a()) {
            this.b = new d();
        }
    }
    
    public b(final String s) {
        if (a()) {
            this.b = new d(s);
        }
    }
    
    public void a(final Object o) {
        if (this.b != null) {
            this.b.a(o);
        }
    }
    
    public void a(final Object o, final Throwable t) {
        if (this.b != null) {
            this.b.a(o, t);
        }
    }
    
    public void b(final Object o, final Throwable t) {
        if (this.b != null) {
            this.b.b(o, t);
        }
    }
    
    public void b(final Object o) {
        if (this.b != null) {
            this.b.b(o);
        }
    }
    
    public void c(final Object o, final Throwable t) {
        if (this.b != null) {
            this.b.c(o, t);
        }
    }
    
    public void c(final Object o) {
        if (this.b != null) {
            this.b.c(o);
        }
    }
    
    public void d(final Object o, final Throwable t) {
        if (this.b != null) {
            this.b.d(o, t);
        }
    }
    
    public void d(final Object o) {
        if (this.b != null) {
            this.b.d(o);
        }
    }
    
    public void e(final Object o, final Throwable t) {
        if (this.b != null) {
            this.b.e(o, t);
        }
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "\u0012_=zB\rL9<FSA53\u0017\u0017\u0003\u0016;D\u001aH(".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '}';
                    break;
                }
                case 1: {
                    c2 = '-';
                    break;
                }
                case 2: {
                    c2 = 'Z';
                    break;
                }
                case 3: {
                    c2 = 'T';
                    break;
                }
                default: {
                    c2 = '#';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0012_=zBSNt6".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '}';
                    break;
                }
                case 1: {
                    c4 = '-';
                    break;
                }
                case 2: {
                    c4 = 'Z';
                    break;
                }
                case 3: {
                    c4 = 'T';
                    break;
                }
                default: {
                    c4 = '#';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        b.z = z;
        b.a = null;
    }
}
