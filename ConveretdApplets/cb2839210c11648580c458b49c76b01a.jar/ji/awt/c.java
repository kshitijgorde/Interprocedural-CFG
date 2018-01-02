// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import ji.util.d;
import java.util.Vector;
import ji.util.l;
import ji.util.k;

public class c
{
    private Object[] a;
    private int[] b;
    private int c;
    private String[] d;
    private String[] e;
    private boolean f;
    private String[] g;
    private Object[] h;
    private String i;
    private int j;
    private int k;
    private static int l;
    private int m;
    private boolean n;
    
    public final c a() {
        final c c = new c(this.i);
        c.a = new Object[this.a.length];
        c.d = new String[this.d.length];
        c.k = this.k;
        c.j = this.j;
        c.n = this.n;
        for (int i = 0; i < this.j; ++i) {
            c.a[i] = this.a[i];
            c.d[i] = this.d[i];
        }
        return c;
    }
    
    public final String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiVector (").append(this.i).append(" - ").append(this.m).append(" - ").append(this.j).append(" elements)")));
    }
    
    public c(final String i, int max, final int n) {
        this.c = 0;
        this.e = null;
        this.f = true;
        this.m = 0;
        this.n = false;
        this.m = ji.awt.c.l++;
        if (max >= 0) {
            max = Math.max(max, 5);
        }
        this.i = i;
        if (max >= 0) {
            this.a = new Object[max];
            this.d = new String[max];
        }
        this.k = Math.max(n, 10);
    }
    
    public c(final String s, final int n) {
        this(s, n, 0);
    }
    
    public c(final String s) {
        this(s, 10);
    }
    
    public c(final String s, final boolean n) {
        this(s);
        this.n = n;
    }
    
    public final void a(final int n) {
        this.e(n);
    }
    
    private final void e(final int n) {
        final int length = this.a.length;
        if (n > length) {
            final Object[] a = this.a;
            final String[] d = this.d;
            int n2 = (this.k > 0) ? (length + this.k) : (length * 2);
            if (n2 < n) {
                n2 = n;
            }
            final Object[] a2 = new Object[n2];
            final String[] d2 = new String[n2];
            for (int i = 0; i < length; ++i) {
                a2[i] = a[i];
                d2[i] = d[i];
            }
            this.a = a2;
            this.d = d2;
            this.g();
            this.k *= 2;
        }
    }
    
    public final int b() {
        return this.j;
    }
    
    public final boolean a(final Object o) {
        return this.c(o, 0) >= 0;
    }
    
    private final int c(final Object o, final int n) {
        if (o == null) {
            for (int i = n; i < this.j; ++i) {
                if (this.a[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = n; j < this.j; ++j) {
                if (o.equals(this.a[j])) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public final Object b(final int n) {
        if (n >= this.j) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n))).append(" >= ").append(this.j))));
        }
        try {
            return this.a[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(String.valueOf(n)).concat(" < 0"));
        }
    }
    
    public final Object c(final int n) {
        if (n >= this.j) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n))).append(" >= ").append(this.j))));
        }
        try {
            return this.d[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(String.valueOf(n)).concat(" < 0"));
        }
    }
    
    public final void a(final Object o, final int n) {
        if (n >= this.j) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n))).append(" >= ").append(this.j))));
        }
        this.a[n] = o;
    }
    
    public final void d(final int n) {
        if (n >= this.j) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n))).append(" >= ").append(this.j))));
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        final int n2 = this.j - n - 1;
        if (n2 > 0) {
            System.arraycopy(this.a, n + 1, this.a, n, n2);
            System.arraycopy(this.d, n + 1, this.d, n, n2);
        }
        --this.j;
        this.a[this.j] = null;
        this.d[this.j] = null;
        this.g();
    }
    
    public final boolean b(final Object o) {
        final int c = this.c(o, 0);
        if (c >= 0) {
            this.d(c);
            return true;
        }
        return false;
    }
    
    public final void b(final Object o, final int n) {
        if (n >= this.j + 1) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n))).append(" > ").append(this.j))));
        }
        this.e(this.j + 1);
        System.arraycopy(this.a, n, this.a, n + 1, this.j - n);
        System.arraycopy(this.d, n, this.d, n + 1, this.j - n);
        this.a[n] = o;
        this.d[n] = null;
        ++this.j;
        this.g();
    }
    
    public final void c(final Object o) {
        this.e(this.j + 1);
        this.a[this.j] = o;
        this.d[this.j] = null;
        this.g();
        ++this.j;
    }
    
    public final void a(final String s, final Object o) {
        this.e(this.j + 1);
        this.a[this.j] = o;
        this.d[this.j] = s;
        this.g();
        ++this.j;
    }
    
    public final void a(final String s) {
        if (s != null) {
            for (int i = 0; i < this.j; ++i) {
                if (this.d[i] != null && this.d[i].equals(s)) {
                    this.d(i);
                    break;
                }
            }
        }
    }
    
    public final void c() {
        for (int i = 0; i < this.j; ++i) {
            this.a[i] = null;
            this.d[i] = null;
        }
        this.j = 0;
        this.g();
    }
    
    public final ax d() {
        this.f();
        return new sp(this.e, this.j);
    }
    
    private final void g() {
        this.f = true;
        if (this.e != null) {
            for (int length = this.e.length, i = 0; i < length; ++i) {
                this.e[i] = null;
            }
            this.e = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        this.c = 0;
    }
    
    private final void h() {
        if (this.g != null) {
            for (int length = this.g.length, i = 0; i < length; ++i) {
                this.g[i] = null;
            }
            this.g = null;
        }
        if (this.h != null) {
            for (int length2 = this.h.length, j = 0; j < length2; ++j) {
                this.h[j] = null;
            }
            this.h = null;
        }
    }
    
    public final Object d(final Object o) {
        if (o == null || this.j <= 0) {
            return null;
        }
        if (this.f) {
            this.f();
        }
        final int e = this.e(o);
        if (e >= this.c) {
            return null;
        }
        final int n = this.b[e];
        if (n >= 0 && this.d[n].equals(o)) {
            return this.a[n];
        }
        try {
            if (this.g != null) {
                for (int length = this.g.length, i = 0; i < length; ++i) {
                    if (this.g[i] != null && this.g[i].equals(o)) {
                        return this.h[i];
                    }
                }
            }
        }
        catch (Exception ex) {}
        try {
            if (this.g != null) {
                for (int length2 = this.g.length, j = 0; j < length2; ++j) {
                    if (this.d[j] != null && this.d[j].equals(o)) {
                        return this.a[j];
                    }
                }
                return null;
            }
        }
        catch (Exception ex2) {}
        return null;
    }
    
    private int e(final Object o) {
        if (o != null) {
            final String string = o.toString();
            final int length = string.length();
            string.toCharArray();
            return ((o.hashCode() & Integer.MAX_VALUE) + length) % 65536;
        }
        return 0;
    }
    
    public k e() {
        if (!this.n) {
            return new k(this.j);
        }
        return new l(this.j);
    }
    
    public final void f() {
        try {
            if (this.f && this.j > 0) {
                System.currentTimeMillis();
                final k e = this.e();
                for (int i = 0; i < this.j; ++i) {
                    if (this.d[i] != null) {
                        e.a(this.d[i]);
                    }
                    else {
                        e.a("");
                    }
                }
                this.e = new String[this.j];
                for (int j = 0; j < this.j; ++j) {
                    this.e[j] = e.b();
                }
                int max = 0;
                for (int k = 0; k < this.j; ++k) {
                    if (this.d[k] != null) {
                        max = Math.max(max, this.e(this.d[k]));
                    }
                }
                this.c = max + 1;
                this.b = new int[this.c];
                final Vector<Integer> vector = new Vector<Integer>();
                this.h();
                for (int l = 0; l < this.j; ++l) {
                    if (this.d[l] != null) {
                        final int e2 = this.e(this.d[l]);
                        if (this.b[e2] >= 0) {
                            if (this.b[e2] != 0) {
                                final int n = this.b[e2];
                                final int size = vector.size();
                                boolean b = false;
                                for (int n2 = 0; n2 < size; ++n2) {
                                    if (vector.elementAt(n2) == n) {
                                        b = true;
                                        break;
                                    }
                                }
                                if (!b) {
                                    vector.addElement(new Integer(n));
                                }
                                final int n3 = l;
                                final int size2 = vector.size();
                                boolean b2 = false;
                                for (int n4 = 0; n4 < size2; ++n4) {
                                    if (vector.elementAt(n4) == n3) {
                                        b2 = true;
                                        break;
                                    }
                                }
                                if (!b2) {
                                    vector.addElement(new Integer(n3));
                                }
                                this.b[e2] = -1;
                            }
                            else {
                                this.b[e2] = l;
                            }
                        }
                        else {
                            final int size3 = vector.size();
                            boolean b3 = false;
                            for (int n5 = 0; n5 < size3; ++n5) {
                                if (vector.elementAt(n5) == l) {
                                    b3 = true;
                                    break;
                                }
                            }
                            if (!b3) {
                                vector.addElement(new Integer(l));
                            }
                        }
                    }
                }
                try {
                    if (vector.size() > 0) {
                        final int size4 = vector.size();
                        final int length = this.d.length;
                        this.g = new String[size4];
                        this.h = new Object[size4];
                        for (int n6 = 0; n6 < size4; ++n6) {
                            final int intValue = vector.elementAt(n6);
                            if (intValue >= 0 && intValue < length) {
                                this.g[n6] = this.d[intValue];
                                this.h[n6] = this.a[intValue];
                            }
                        }
                    }
                }
                catch (Exception ex) {}
                this.f = false;
            }
        }
        catch (Exception ex2) {}
    }
    
    static {
        c.l = 0;
    }
    
    class sp implements ax
    {
        int a;
        int b;
        String[] c;
        
        sp(final c c, final String[] c2, final int b) {
            this.a = 0;
            this.b = 0;
            this.c = c2;
            this.b = b;
        }
        
        public final boolean a() {
            return this.a < this.b;
        }
        
        public final String b() {
            try {
                return this.c[this.a++];
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
                return null;
            }
        }
        
        public final boolean a(final String s) {
            if (this.c != null) {
                for (int length = this.c.length, i = 0; i < length; ++i) {
                    if (s.equals(this.c[i])) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
