// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import neat.system.y;

public class h extends f
{
    private static neat.system.f b;
    private i c;
    private transient o[] d;
    private transient int e;
    private int f;
    private float g;
    private static /* synthetic */ Class h;
    private static String[] z;
    
    public r a() {
        final d a = neat.d.a(this, this.c);
        this.c.a(a);
        return a;
    }
    
    public Object a(final Object o, final Object o2) {
        final Object b = this.b(o, o2);
        if (b == null) {
            this.c.a(o);
        }
        return b;
    }
    
    public Object a(final Object o) {
        final Object j = this.j(o);
        if (j != null) {
            this.c.d(o);
        }
        return j;
    }
    
    public Object b(Object i) {
        i = this.i(i);
        if (i == null) {
            return null;
        }
        final Object j = this.j(i);
        if (j != null) {
            this.c.d(i);
            if (i instanceof y) {
                ((y)i).f();
            }
        }
        return j;
    }
    
    public boolean c(Object i) {
        boolean b = false;
        i = this.i(i);
        if (i != null) {
            final Object j = this.j(i);
            if (j != null) {
                b = true;
                this.c.d(i);
                if (j instanceof y) {
                    ((y)j).f();
                }
                if (i instanceof y) {
                    ((y)i).f();
                }
            }
        }
        return b;
    }
    
    public void c() {
        final r a = this.a();
        while (a.a()) {
            final Object b = a.b();
            final Object g = this.g(b);
            if (g instanceof y) {
                ((y)g).f();
            }
            if (a.c()) {
                a.e();
            }
            if (b instanceof y) {
                ((y)b).f();
            }
        }
        a.f();
        this.m();
    }
    
    public void d() {
        final r a = this.a();
        while (a.a()) {
            final Object b = a.b();
            a.e();
            if (b instanceof y) {
                ((y)b).f();
            }
        }
        a.f();
        this.m();
    }
    
    public static h e() {
        final h h = (h)neat.h.b.a();
        h.i();
        return h;
    }
    
    public static h a(final int n) {
        final h h = (h)neat.h.b.a();
        h.b(n, 0.75f);
        return h;
    }
    
    public static h a(final int n, final float n2) {
        final h h = (h)neat.h.b.a();
        h.b(n, n2);
        return h;
    }
    
    public void f() {
        neat.h.b.a(this);
    }
    
    public void g() {
        super.g();
        this.c = i.k();
    }
    
    public void h() {
        super.h();
        this.m();
        this.c.f();
        this.c = null;
    }
    
    private void b(int length, final float g) {
        if (length < 0) {
            throw new IllegalArgumentException(neat.h.z[0] + length);
        }
        if (g <= 0.0f) {
            throw new IllegalArgumentException(neat.h.z[1] + g);
        }
        if (length == 0) {
            length = 1;
        }
        this.g = g;
        if (this.d == null) {
            this.d = new o[length];
        }
        else if (this.d.length < length) {
            this.d = new o[length];
        }
        else {
            length = this.d.length;
        }
        this.f = (int)(length * g);
    }
    
    private void i() {
        this.b(101, 0.75f);
    }
    
    public int j() {
        return this.e;
    }
    
    public boolean k() {
        return this.e == 0;
    }
    
    public Object d(final Object o) {
        if (o == null) {
            return null;
        }
        final o[] d = this.d;
        int length = d.length;
        while (length-- > 0) {
            for (o e = d[length]; e != null; e = e.e) {
                if (e.d.equals(o)) {
                    return e.c;
                }
            }
        }
        return null;
    }
    
    public boolean e(final Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        final o[] d = this.d;
        int length = d.length;
        while (length-- > 0) {
            for (o e = d[length]; e != null; e = e.e) {
                if (e.d.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean f(final Object o) {
        return this.h(o) != null;
    }
    
    public Object g(final Object o) {
        final o h = this.h(o);
        if (h != null) {
            return h.d;
        }
        return null;
    }
    
    protected void l() {
        final int length = this.d.length;
        final o[] d = this.d;
        final int n = length * 2 + 1;
        final o[] d2 = new o[n];
        this.f = (int)(n * this.g);
        this.d = d2;
        int n2 = length;
        while (n2-- > 0) {
            o o;
            int n3;
            for (o e = d[n2]; e != null; e = e.e, n3 = (o.b & Integer.MAX_VALUE) % n, o.e = d2[n3], d2[n3] = o) {
                o = e;
            }
        }
    }
    
    private o h(final Object o) {
        final o[] d = this.d;
        final int hashCode = o.hashCode();
        for (o e = d[(hashCode & Integer.MAX_VALUE) % d.length]; e != null; e = e.e) {
            if (e.b == hashCode) {
                if (e.c == o) {
                    return e;
                }
                if (e.c.equals(o)) {
                    return e;
                }
            }
        }
        return null;
    }
    
    private Object i(final Object o) {
        final o h = this.h(o);
        if (h == null) {
            return null;
        }
        return h.c;
    }
    
    Object b(final Object o, final Object d) {
        if (d == null) {
            throw new NullPointerException();
        }
        o[] array = this.d;
        final int hashCode = o.hashCode();
        int n = (hashCode & Integer.MAX_VALUE) % array.length;
        for (o e = array[n]; e != null; e = e.e) {
            if (e.b == hashCode && (e.c == o || e.c.equals(o))) {
                final Object d2 = e.d;
                e.d = d;
                return d2;
            }
        }
        if (this.e >= this.f) {
            this.l();
            array = this.d;
            n = (hashCode & Integer.MAX_VALUE) % array.length;
        }
        array[n] = o.a(hashCode, o, d, array[n]);
        ++this.e;
        return null;
    }
    
    Object j(final Object o) {
        final o[] d = this.d;
        final int hashCode = o.hashCode();
        final int n = (hashCode & Integer.MAX_VALUE) % d.length;
        o o2 = null;
        for (o e = d[n]; e != null; e = e.e) {
            if (e.b == hashCode && (e.c == o || e.c.equals(o))) {
                if (o2 != null) {
                    o2.e = e.e;
                }
                else {
                    d[n] = e.e;
                }
                --this.e;
                final Object d2 = e.d;
                e.f();
                return d2;
            }
            o2 = e;
        }
        return null;
    }
    
    public void m() {
        final o[] d = this.d;
        int length = d.length;
        while (--length >= 0) {
            o e;
            for (o o = d[length]; o != null; o = e) {
                e = o.e;
                o.f();
            }
            d[length] = null;
        }
        this.e = 0;
        this.c.c();
    }
    
    public String toString() {
        final int n = this.j() - 1;
        return new StringBuffer().toString();
    }
    
    public boolean equals(final Object o) {
        return o == this;
    }
    
    public int hashCode() {
        int n = 0;
        final r a = this.a();
        while (a.a()) {
            n += a.b().hashCode();
        }
        a.f();
        return n;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.d.length);
        objectOutputStream.writeInt(this.e);
        for (int i = this.d.length - 1; i >= 0; --i) {
            for (o e = this.d[i]; e != null; e = e.e) {
                objectOutputStream.writeObject(e.c);
                objectOutputStream.writeObject(e.d);
            }
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        final int int1 = objectInputStream.readInt();
        int i = objectInputStream.readInt();
        int n = (int)(i * this.g) + i / 20 + 3;
        if (n > i && (n & 0x1) == 0x0) {
            --n;
        }
        if (int1 > 0 && n > int1) {
            n = int1;
        }
        this.d = new o[n];
        this.e = 0;
        while (i > 0) {
            this.a(objectInputStream.readObject(), objectInputStream.readObject());
            --i;
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public h() {
        this.d = null;
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "i?SJMA?\u001flKP2\\F^Yi\u001f".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = ' ';
                            break;
                        }
                        case 1: {
                            c2 = 'S';
                            break;
                        }
                        case 2: {
                            c2 = '?';
                            break;
                        }
                        case 3: {
                            c2 = '/';
                            break;
                        }
                        default: {
                            c2 = '*';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "i?SJMA?\u001fcEA7\u0005\u000f".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = ' ';
                            break;
                        }
                        case 1: {
                            c4 = 'S';
                            break;
                        }
                        case 2: {
                            c4 = '?';
                            break;
                        }
                        case 3: {
                            c4 = '/';
                            break;
                        }
                        default: {
                            c4 = '*';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "N6^[\u0004H".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = ' ';
                            break;
                        }
                        case 1: {
                            c6 = 'S';
                            break;
                        }
                        case 2: {
                            c6 = '?';
                            break;
                        }
                        case 3: {
                            c6 = '/';
                            break;
                        }
                        default: {
                            c6 = '*';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                neat.h.z = z;
                neat.h.b = new neat.system.f((neat.h.h != null) ? neat.h.h : (neat.h.h = a(neat.h.z[2])));
                return;
            }
            continue;
        }
    }
}
