// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.util.Enumeration;
import java.util.Dictionary;

class n extends Dictionary implements Cloneable
{
    private d[] a;
    private int b;
    private int c;
    private float d;
    
    public int size() {
        return this.b;
    }
    
    public boolean isEmpty() {
        return this.b == 0;
    }
    
    public synchronized Enumeration keys() {
        return new e(this.a, true);
    }
    
    public synchronized Enumeration elements() {
        return new e(this.a, false);
    }
    
    public synchronized boolean contains(final Object o) {
        final boolean g = GraphSerie.G;
        if (o == null) {
            throw new NullPointerException();
        }
        final d[] a = this.a;
        int length = a.length;
        while (true) {
            Label_0066: {
                if (!g) {
                    break Label_0066;
                }
                d d = a[length];
                while (true) {
                    Label_0061: {
                        if (!g) {
                            break Label_0061;
                        }
                        if (d.c.equals(o)) {
                            return true;
                        }
                        d = d.d;
                    }
                    if (d != null) {
                        continue;
                    }
                    break;
                }
            }
            if (length-- <= 0) {
                return false;
            }
            continue;
        }
    }
    
    public synchronized boolean containsKey(final int n) {
        final boolean g = GraphSerie.G;
        final d[] a = this.a;
        d d = a[(n & Integer.MAX_VALUE) % a.length];
        while (true) {
            while (true) {
                Label_0059: {
                    if (!g) {
                        break Label_0059;
                    }
                    final int a2 = d.a;
                    if (a2 == n && d.b == n) {
                        return true;
                    }
                    d = d.d;
                }
                if (d != null) {
                    continue;
                }
                break;
            }
            int a2;
            final int n2 = a2 = 0;
            if (!g) {
                return n2 != 0;
            }
            continue;
        }
    }
    
    public synchronized Object get(final int n) {
        final boolean g = GraphSerie.G;
        final d[] a = this.a;
        d d = a[(n & Integer.MAX_VALUE) % a.length];
        while (true) {
            while (true) {
                Label_0063: {
                    if (!g) {
                        break Label_0063;
                    }
                    if (d.a == n && d.b == n) {
                        return d.c;
                    }
                    d = d.d;
                }
                if (d != null) {
                    continue;
                }
                break;
            }
            if (!g) {
                return null;
            }
            continue;
        }
    }
    
    public Object get(final Object o) {
        if (!(o instanceof Integer)) {
            throw new InternalError(a("0n#|p(+43m{j4|P5\u007f?;|)"));
        }
        return this.get((int)o);
    }
    
    protected void a() {
        final boolean g = GraphSerie.G;
        final int length = this.a.length;
        final d[] a = this.a;
        final int n = length * 2 + 1;
        final d[] a2 = new d[n];
        this.c = (int)(n * this.d);
        this.a = a2;
        int n2 = length;
        while (true) {
            Label_0110: {
                if (!g) {
                    break Label_0110;
                }
                d d = a[n2];
                while (true) {
                    Label_0105: {
                        if (!g) {
                            break Label_0105;
                        }
                        final d d2 = d;
                        d = d.d;
                        final int n3 = (d2.a & Integer.MAX_VALUE) % n;
                        d2.d = a2[n3];
                        a2[n3] = d2;
                    }
                    if (d != null) {
                        continue;
                    }
                    break;
                }
            }
            if (n2-- <= 0) {
                return;
            }
            continue;
        }
    }
    
    public synchronized Object put(final int n, final Object o) {
        final boolean g = GraphSerie.G;
        if (o == null) {
            throw new NullPointerException();
        }
        final d[] a = this.a;
        final int n2 = (n & Integer.MAX_VALUE) % a.length;
        d d = a[n2];
        int b = 0;
        int c = 0;
        while (true) {
            while (true) {
                Label_0088: {
                    if (!g) {
                        break Label_0088;
                    }
                    final int a2 = d.a;
                    if (b == c && d.b == n) {
                        final Object c2 = d.c;
                        d.c = o;
                        return c2;
                    }
                    d = d.d;
                }
                if (d != null) {
                    continue;
                }
                break;
            }
            b = this.b;
            c = this.c;
            if (g) {
                continue;
            }
            break;
        }
        if (b >= c) {
            this.a();
            return this.put(n, o);
        }
        final d d2 = new d();
        d2.a = n;
        d2.b = n;
        d2.c = o;
        d2.d = a[n2];
        a[n2] = d2;
        ++this.b;
        return null;
    }
    
    public Object put(final Object o, final Object o2) {
        if (!(o instanceof Integer)) {
            throw new InternalError(a("0n#|p(+43m{j4|P5\u007f?;|)"));
        }
        return this.put((int)o, o2);
    }
    
    public synchronized Object remove(final int n) {
        final boolean g = GraphSerie.G;
        final d[] a = this.a;
        final int n2 = (n & Integer.MAX_VALUE) % a.length;
        d d = a[n2];
        d d2 = null;
        while (true) {
            if (d == null) {
                if (!g) {
                    return null;
                }
            }
            else {
                if (d.a == n && d.b == n) {
                    Label_0080: {
                        if (d2 != null) {
                            d2.d = d.d;
                            if (!g) {
                                break Label_0080;
                            }
                        }
                        a[n2] = d.d;
                    }
                    --this.b;
                    return d.c;
                }
                d2 = d;
            }
            d = d.d;
        }
    }
    
    public Object remove(final Object o) {
        if (!(o instanceof Integer)) {
            throw new InternalError(a("0n#|p(+43m{j4|P5\u007f?;|)"));
        }
        return this.remove((int)o);
    }
    
    public synchronized void clear() {
        final d[] a = this.a;
        int length = a.length;
        while (true) {
            Label_0018: {
                if (!GraphSerie.G) {
                    break Label_0018;
                }
                a[length] = null;
            }
            if (--length < 0) {
                this.b = 0;
                return;
            }
            continue;
        }
    }
    
    public synchronized Object clone() {
        final boolean g = GraphSerie.G;
        try {
            final n n = (n)super.clone();
            n.a = new d[this.a.length];
            int length = this.a.length;
            while (true) {
                Label_0065: {
                    if (!g) {
                        break Label_0065;
                    }
                    final n n2;
                    n2.a[length] = ((this.a[length] != null) ? ((d)this.a[length].clone()) : null);
                }
                if (length-- > 0) {
                    continue;
                }
                final n n2 = n;
                if (!g) {
                    return n2;
                }
                continue;
            }
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public synchronized String toString() {
        final boolean g = GraphSerie.G;
        final int n = this.size() - 1;
        final StringBuffer sb = new StringBuffer();
        final Enumeration keys = this.keys();
        final Enumeration elements = this.elements();
        sb.append("{");
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0118: {
                    if (!g) {
                        break Label_0118;
                    }
                    keys.nextElement().toString();
                    final String string;
                    sb.append(string + "=" + elements.nextElement().toString());
                    if (n2 < n) {
                        sb.append(a("w+"));
                    }
                    ++n2;
                }
                if (n2 <= n) {
                    continue;
                }
                break;
            }
            sb.append("}");
            final String string = sb.toString();
            if (!g) {
                return string;
            }
            continue;
        }
    }
    
    public n(final int n, final float d) {
        if (n <= 0 || d <= 0.0) {
            throw new IllegalArgumentException();
        }
        this.d = d;
        this.a = new d[n];
        this.c = (int)(n * d);
    }
    
    public n(final int n) {
        this(n, 0.75f);
    }
    
    public n() {
        this(101, 0.75f);
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '[';
                            break;
                        }
                        case 1: {
                            c2 = '\u000b';
                            break;
                        }
                        case 2: {
                            c2 = 'Z';
                            break;
                        }
                        case 3: {
                            c2 = '\\';
                            break;
                        }
                        default: {
                            c2 = '\u0019';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
