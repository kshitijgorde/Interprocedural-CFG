// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.h;

import flaxchat.a.j;
import flaxchat.a.e;
import java.util.Enumeration;
import java.io.Serializable;

public class a implements Cloneable, Serializable
{
    protected Object[] a;
    protected int b;
    protected int c;
    public static int d;
    private static String[] z;
    
    public a(final int n, final int c) {
        this.a = new Object[n];
        this.c = c;
    }
    
    public a(final int n) {
        this(n, 0);
    }
    
    public a() {
        this(10);
    }
    
    private void a(final int n) {
        final int length = this.a.length;
        final Object[] a = this.a;
        int n2 = (this.c > 0) ? (length + this.c) : (length * 2);
        if (n2 < n) {
            n2 = n;
        }
        System.arraycopy(a, 0, this.a = new Object[n2], 0, this.b);
    }
    
    public final int a() {
        return this.b;
    }
    
    public final boolean b() {
        return this.b == 0;
    }
    
    public final Enumeration c() {
        return new g(this);
    }
    
    public final int a(final Object o) {
        return this.a(o, 0);
    }
    
    public final int a(final Object o, final int n) {
        final int d = flaxchat.h.a.d;
        int n2 = n;
        while (true) {
            Label_0030: {
                if (d == 0) {
                    break Label_0030;
                }
                if (o.equals(this.a[n2])) {
                    return n2;
                }
                ++n2;
            }
            if (n2 >= this.b) {
                return -1;
            }
            continue;
        }
    }
    
    public final Object b(final int n) {
        if (n >= this.b) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(n) + flaxchat.h.a.z[0] + this.b);
        }
        try {
            return this.a[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(n) + flaxchat.h.a.z[1]);
        }
    }
    
    public final Object c(final int n) {
        final int d = flaxchat.h.a.d;
        if (n >= this.b) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(n) + flaxchat.h.a.z[0] + this.b);
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        final Object o = this.a[n];
        final int n2 = this.b - n - 1;
        if (n2 > 0) {
            System.arraycopy(this.a, n + 1, this.a, n, n2);
        }
        --this.b;
        this.a[this.b] = null;
        final Object o2 = o;
        if (d != 0) {
            int c = e.c;
            e.c = ++c;
        }
        return o2;
    }
    
    public final void b(final Object o) {
        final int n = this.b + 1;
        if (n > this.a.length) {
            this.a(n);
        }
        this.a[this.b++] = o;
    }
    
    public final void d() {
        final int d = flaxchat.h.a.d;
        int n = 0;
        while (true) {
            Label_0020: {
                if (d == 0) {
                    break Label_0020;
                }
                this.a[n] = null;
                ++n;
            }
            if (n >= this.b) {
                this.b = 0;
                return;
            }
            continue;
        }
    }
    
    public Object clone() {
        try {
            final a a = (a)super.clone();
            a.a = new Object[this.b];
            System.arraycopy(this.a, 0, a.a, 0, this.b);
            return a;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public final String toString() {
        int d = flaxchat.h.a.d;
        final int n = this.a() - 1;
        final j j = new j();
        final Enumeration c = this.c();
        j.a("[");
        int n2 = 0;
        while (true) {
            Label_0077: {
                if (d == 0) {
                    break Label_0077;
                }
                j.a(c.nextElement().toString());
                if (n2 < n) {
                    j.a(flaxchat.h.a.z[2]);
                }
                ++n2;
            }
            if (n2 > n) {
                j.a("]");
                final String string = j.toString();
                if (e.c != 0) {
                    flaxchat.h.a.d = ++d;
                }
                return string;
            }
            continue;
        }
    }
    
    public Object d(final int n) {
        return this.b(n);
    }
    
    public void c(final Object o) {
        this.b(o);
    }
    
    static {
        a.z = new String[] { z(z("V_\ft")), z(z("V]\u0011d")), z(z("ZA")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'k';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'v';
                    break;
                }
                case 1: {
                    c2 = 'a';
                    break;
                }
                case 2: {
                    c2 = '1';
                    break;
                }
                case 3: {
                    c2 = 'T';
                    break;
                }
                default: {
                    c2 = 'k';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}