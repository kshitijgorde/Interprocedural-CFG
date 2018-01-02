import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.applet.Applet;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class e extends j
{
    s s;
    private Component o;
    private p v;
    private Dimension f;
    private boolean q;
    private int y;
    private int x;
    private boolean h;
    private int i;
    private int n;
    public boolean u;
    public boolean k;
    private boolean m;
    public String w;
    public String l;
    public String j;
    public u t;
    private boolean g;
    private k r;
    private Color p;
    
    public e(final p v, final s s, final Dimension dimension, final Component o) {
        this.q = false;
        this.y = -100;
        this.x = -100;
        this.h = true;
        this.i = -1;
        this.u = false;
        this.k = false;
        this.m = false;
        this.g = false;
        this.p = new Color(6684672);
        this.v = v;
        this.o = o;
        this.do(s.byte);
        this.a(s, dimension);
    }
    
    protected synchronized void finalize() throws Throwable {
        this.k = true;
        this.char();
        super.finalize();
    }
    
    public void byte() {
        this.k = true;
        this.char();
        this.a(this.s.b);
    }
    
    public synchronized void a(final Vector vector) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                final d d = vector.elementAt(i);
                if (d.a != null) {
                    d.a.flush();
                    d.a = null;
                }
            }
        }
    }
    
    public void if(final boolean g) {
        this.g = g;
    }
    
    public void if(final k r) {
        this.r = r;
    }
    
    public void a(final Color p) {
        if (p != null) {
            this.p = p;
        }
    }
    
    public synchronized void a(final String s, final String s2, final int n) {
        Applet applet = null;
        final Component do1 = this.v.do();
        if (do1 instanceof Applet) {
            applet = (Applet)do1;
        }
        if (applet != null) {
            new i(s, s2, applet.getCodeBase(), n, this, do1);
            return;
        }
        new i(s, s2, null, n, this, do1);
    }
    
    public synchronized void a(final s s) {
        final s s2 = this.s;
        this.a(s, this.f);
        this.v.a(s, this.for());
        if (s2.void == null || !s.void.equals(s2.void)) {
            this.a(s, 10);
        }
        if (s2.void != null) {
            this.a(s, 90);
        }
        this.u = false;
        this.a(s2.b);
        if (s.do > 0 && s != s2) {
            if (s.try != null && s.try.length() > 0) {
                this.a(s.try, s.new, s.do);
                return;
            }
            this.a(s.void, s.new, s.do);
        }
    }
    
    public synchronized void a(final s s, final int n) {
        u u = null;
        if (s == null || this.s != s) {
            return;
        }
        if (n == 10) {
            u = s.goto.try;
        }
        else if (n == 60) {
            u = s.goto.a;
        }
        else if (n == 70) {
            u = s.goto.c;
        }
        else if (n == 20) {
            u = s.goto.byte;
        }
        else if (n == 30) {
            u = s.goto.if;
        }
        else if (n == 40) {
            u = s.goto.case;
        }
        else if (n == 50) {
            u = s.goto.void;
        }
        else if (n == 80) {
            u = s.goto.goto;
        }
        else if (n == 90) {
            u = s.goto.for;
        }
        else if (n == 100) {
            u = s.goto.int;
        }
        else if (n == 110) {
            u = s.goto.null;
        }
        else if (n == 120) {
            u = s.goto.new;
        }
        if (u != null) {
            this.if(u);
        }
    }
    
    public void a() {
        this.v.a();
    }
    
    public synchronized void a(final Dimension dimension) {
        if (dimension != null && dimension.width > 0 && dimension.height > 0) {
            if (this.f != null && this.f.width == dimension.width && this.f.height == dimension.height) {
                return;
            }
            this.a(this.s, dimension);
            this.a(this.s, 120);
        }
    }
    
    public synchronized Dimension new() {
        return this.f;
    }
    
    public int case() {
        return this.s.a;
    }
    
    public int try() {
        return this.s.byte;
    }
    
    public boolean a(final int n, final boolean b, final int n2, final int n3) {
        int n4 = -1;
        final k[] if1 = this.s.if;
        final Dimension f = this.f;
        final int a = this.s.a;
        if (this.n > 0 || !b || if1 == null || f == null) {
            return false;
        }
        if (n + f.height > a && a > f.height) {
            n4 = f.height + n - a;
        }
        for (int i = 0; i < if1.length; ++i) {
            if (a(if1[i], n, n2, n3, a, n4, f)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean a(final u u, final String s, final String s2, final String s3) {
        return (u != null && ((u.q != null && u.q.length > 0) || (u.a != null && u.a.trim().length() > 0))) || (s3 != null && s3.trim().length() > 0 && s2 != null && s2.trim().length() > 0) || (s != null && s.trim().length() > 0);
    }
    
    private static boolean a(final k k, final int n, final int n2, final int n3, final int n4, final int n5, final Dimension dimension) {
        if (k == null) {
            return false;
        }
        int int1 = k.int;
        int for1 = k.for;
        if (n5 > 0 && k.int <= n5 && n3 >= n4 - n) {
            int1 += n4;
            for1 += n4;
        }
        if (n2 >= k.case && n2 <= k.byte && n3 >= int1 - n && n3 <= for1 - n) {
            if (k.a != null) {
                if ((k.a.c != null || k.a.b != null) && k.case + k.a.d <= n2 && k.byte - k.a.if >= n2 && int1 - n + k.a.char <= n3 && for1 - n - k.a.do >= n3) {
                    return true;
                }
                if (k.a.else != null) {
                    for (int i = 0; i < k.a.else.length; ++i) {
                        if (a(k.a.else[i], n, n2, n3, n4, n5, dimension)) {
                            return true;
                        }
                    }
                }
            }
            else if (k.if != null) {
                if ((k.if.b != null || k.if.void != null) && k.case + k.if.d <= n2 && k.byte - k.if.for >= n2 && int1 - n + k.if.byte <= n3 && for1 - n - k.if.int >= n3) {
                    return true;
                }
                if (a(k, n, n4, n5, n2, n3)) {
                    return true;
                }
            }
            else if (k.new != null && k.case + k.new.char <= n2 && k.case + k.new.char + k.new.void - 1 >= n2 && int1 + k.new.case - n <= n3 && int1 + k.new.case - n + k.new.goto - 1 >= n3) {
                return true;
            }
        }
        return false;
    }
    
    private synchronized void a(final s s, final Dimension f) {
        this.f = f;
        if (s.long != null && f != null) {
            final Vector vector = new Vector();
            final Vector vector2 = new Vector();
            final Vector vector3 = new Vector();
            final k[] a = this.a(s.long, vector, vector2, vector3, f, s.int);
            if (a != null) {
                s.if = a;
                s.char = h.do(vector);
                s.null = h.do(vector2);
                s.case = h.do(vector3);
                super.for(s.a = a[a.length - 1].for + 1);
            }
            else {
                super.for(s.a = 0);
            }
            super.int(f.height);
            super.if();
        }
        else {
            this.char();
            super.for(0);
            if (f != null) {
                super.int(f.height);
            }
            else {
                super.int(0);
            }
        }
        this.s = s;
        this.a();
    }
    
    public synchronized void char() {
        super.int();
    }
    
    public void a(final Graphics graphics, final s s, final boolean q, final int n, final int y, final int x, final boolean b) {
        final Dimension f = this.f;
        final boolean m = this.m;
        this.y = y;
        this.x = x;
        this.q = q;
        this.w = null;
        this.l = null;
        this.j = null;
        this.t = null;
        this.m = false;
        if (this.n > 0 || this.s != s || s.if == null || graphics == null || f == null || f.width <= 0 || f.height <= 0) {
            return;
        }
        for (int i = 0; i < s.if.length; ++i) {
            this.a(graphics, n, f, s.a, s.if[i], y, x, b, false);
        }
        if (!b && this.m && !m) {
            this.a(s, 20);
            return;
        }
        if (m && !this.m) {
            this.a(s, 30);
        }
    }
    
    private u a(final u[] array, final int n, final int n2) {
        u u = null;
        if (array == null) {
            return u;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i].o == n) {
                if (n2 == 50) {
                    return array[i];
                }
                u = array[i];
            }
            else if (array[i].o > n) {
                return u;
            }
        }
        return u;
    }
    
    private u a(final u[] array, String upperCase) {
        if (array != null && upperCase != null) {
            upperCase = upperCase.replace('i', 'I').toUpperCase();
            for (int i = 0; i < array.length; ++i) {
                if (upperCase.equals(array[i].goto)) {
                    return array[i];
                }
            }
        }
        return null;
    }
    
    private int if(final u[] array, final String s) {
        if (s != null && array != null) {
            for (int i = 0; i < array.length; ++i) {
                if (s.equals(array[i].goto)) {
                    return array[i].o;
                }
            }
        }
        return -1;
    }
    
    public synchronized u if(final v v) {
        if (v != null) {
            if (v.try != -100 && v.try != -200 && !this.s.new.equals(v.a)) {
                this.v.a(this.s, v);
                return null;
            }
            if (v.do == 100) {
                this.if(System.currentTimeMillis() + v.for);
            }
            else if (v.do == 500) {
                this.new(v.for);
            }
            else if (v.do == 600) {
                this.a(v.for);
            }
            else if (v.do == 700) {
                if (!this.h || v.for >= this.i) {
                    this.i = -1;
                    this.h = false;
                    this.a(0L);
                }
            }
            else if (v.do == 300 && !this.h) {
                this.if(0L);
            }
            else if (v.do == 800) {
                if (this.i < v.for) {
                    this.i = v.for;
                }
                this.h = true;
                this.int();
            }
            else if (v.do == 900) {
                if (this.h && this.i > v.for) {
                    this.i = v.for;
                }
            }
            else if (v.do == 200 && !this.h) {
                this.if(-1000L);
            }
            else if (!super.c && v.do == 20) {
                final int if1 = this.if(this.s.case, v.byte);
                if (if1 >= 0) {
                    this.if(if1);
                }
            }
            else if (v.do == 1100) {
                this.a(v.byte, this.s.new, -1);
            }
            else if (v.do == 1000) {
                this.a(this.s.void, this.s.new, -1);
            }
            else if (v.do == 1200) {
                if (this.n <= 0) {
                    this.a(this.s, 110);
                }
                this.n = 1;
            }
            else if (v.do == 1300) {
                if (this.n > 0) {
                    this.a(this.s, 100);
                }
                this.n = 0;
            }
            else if (v.do == 1400) {
                ++this.n;
                if (this.n == 1) {
                    this.a(this.s, 110);
                }
            }
            else if (v.do == 1500) {
                --this.n;
                if (this.n == 0) {
                    this.a(this.s, 100);
                }
            }
            else if (v.do == 1600) {
                if (this.n > 0) {
                    this.n = 0;
                }
                else {
                    this.n = 1;
                }
            }
            else if (v.do == 1700) {
                this.v.a(this.s, v);
            }
            else if (v.do == 3000) {
                final u u = new u();
                if (v.if != null) {
                    u.q = v.if;
                    return u;
                }
                final u a = this.a(this.s.else, v.byte);
                if (a == null) {
                    return u;
                }
                return a;
            }
            else if (v.do == 3100) {
                final u a2 = this.a(this.s.null, v.new);
                if (a2 == null) {
                    return null;
                }
                if (v.if != null) {
                    a2.q = v.if;
                }
                else {
                    final u a3 = this.a(this.s.else, v.byte);
                    if (a3 == null) {
                        a2.a = null;
                        a2.q = null;
                    }
                    else {
                        a2.a = a3.a;
                        a2.q = a3.q;
                    }
                }
            }
            else if (v.do == 3200) {
                this.s.goto.try = this.a(v);
            }
            else if (v.do == 3300) {
                this.s.goto.a = this.a(v);
            }
            else if (v.do == 3400) {
                this.s.goto.c = this.a(v);
            }
            else if (v.do == 3500) {
                this.s.goto.byte = this.a(v);
            }
            else if (v.do == 3600) {
                this.s.goto.if = this.a(v);
            }
            else if (v.do == 3700) {
                this.s.goto.case = this.a(v);
            }
            else if (v.do == 3800) {
                this.s.goto.void = this.a(v);
            }
            else if (v.do == 3900) {
                this.s.goto.goto = this.a(v);
            }
            else if (v.do == 4000) {
                this.s.goto.for = this.a(v);
            }
            else if (v.do == 4100) {
                this.s.goto.int = this.a(v);
            }
            else if (v.do == 4200) {
                this.s.goto.null = this.a(v);
            }
            else if (v.do == 4300) {
                this.s.goto.new = this.a(v);
            }
            this.a();
        }
        return null;
    }
    
    private u a(final v v) {
        final u u = new u();
        if (v.if != null) {
            u.q = v.if;
            return u;
        }
        final u a = this.a(this.s.else, v.byte);
        if (a != null) {
            u.q = a.q;
            u.a = a.a;
            return u;
        }
        return null;
    }
    
    public synchronized void a(final u u) {
        if (u != null) {
            final u a = this.a(this.s.else, u.a);
            if (a != null) {
                u.q = a.q;
                u.a = a.a;
                return;
            }
            u.q = null;
            u.a = null;
        }
    }
    
    private synchronized void if(final u u) {
        if (u != null) {
            while (u.q == null && u.a != null) {
                this.a(u);
            }
            final v[] q = u.q;
            if (q != null) {
                for (int i = 0; i < q.length; ++i) {
                    final v v = q[i];
                    if (v.try == -200 || v.try == 1000) {
                        final u a = this.v.a(this.s, v);
                        if (a != null) {
                            u.q = a.q;
                            u.a = a.a;
                        }
                    }
                    else {
                        final u if1 = this.if(v);
                        if (if1 != null) {
                            u.q = if1.q;
                            u.a = if1.a;
                        }
                    }
                }
            }
        }
    }
    
    public synchronized void a(final int n, final int n2) {
        final u a = this.a(this.s.char, n, n2);
        if (a != null) {
            this.if(a);
        }
    }
    
    private void a(final Vector vector, final Vector vector2, final int n, final int n2) {
        if (vector == null || n2 < n) {
            return;
        }
        for (int size = vector.size(), i = 0; i < size; ++i) {
            u u = vector.elementAt(i);
            if (u.n == 4000) {
                int o = u.o - n + 1;
                if (o < 0) {
                    o += n2;
                }
                u.o = o;
            }
            for (int j = 0; j < vector2.size(); ++j) {
                if (vector2.elementAt(j).o > u.o) {
                    vector2.insertElementAt(u, j);
                    u = null;
                    break;
                }
            }
            if (u != null) {
                vector2.addElement(u);
            }
        }
    }
    
    private k[] a(final k[] array, final Vector vector, final Vector vector2, final Vector vector3, final Dimension dimension, final int n) {
        final Vector vector4 = new Vector<k>();
        final Vector<u> vector5 = new Vector<u>();
        int n2 = 0;
        if (array == null) {
            return null;
        }
        for (int i = 0; i < array.length; ++i) {
            final k k = array[i];
            final k j = new k(k);
            if (k.char != null) {
                j.char = new u(k.char);
                if (j.char.L == 400) {
                    vector4.addElement(j);
                    a(vector4, vector4.size() - 1, dimension.height, n2);
                    if (j.for < j.int) {
                        vector4.removeElement(j);
                    }
                    n2 = j.for + 1;
                }
                else if (j.char.L == 10) {
                    if (n2 <= 0) {
                        j.char.o = 0;
                    }
                    else {
                        j.char.o = n2 - 1;
                    }
                    vector3.addElement(j.char);
                }
                else {
                    if (n2 <= 0) {
                        j.char.o = 0;
                    }
                    else {
                        j.char.o = n2 - 1;
                    }
                    vector5.addElement(j.char);
                    if (j.char.goto != null) {
                        vector2.addElement(j.char);
                    }
                }
            }
            else {
                vector4.addElement(j);
                this.a(vector2, 0, n2, k, j, dimension, n);
                a(dimension, j);
                n2 = j.for + 1;
            }
        }
        this.a(vector5, vector, dimension.height, n2);
        return h.int(vector4);
    }
    
    private void a(final Vector vector, final int case1, final int int1, final k k, final k i, final Dimension dimension, final int n) {
        int case2 = case1;
        int int2 = int1;
        i.case = 1000000;
        i.int = 1000000;
        i.byte = -1;
        i.for = -1;
        if (k.a != null) {
            final f a = new f(k.a);
            i.a = a;
            if (k.a.else != null) {
                a.else = new k[k.a.else.length];
                for (int j = 0; j < k.a.else.length; ++j) {
                    final k l = k.a.else[j];
                    final k m = new k(l);
                    this.a(vector, case2, int2, l, a.else[j] = m, dimension, n);
                    if (i.a.case == 100) {
                        case2 = m.byte + 1;
                        int2 = m.int;
                    }
                    else {
                        case2 = m.case;
                        int2 = m.for + 1;
                    }
                    a(i, m.case, m.int, m.byte, m.for);
                }
                a(i);
                return;
            }
            i.case = case1;
            i.int = int1;
            i.byte = case1 - 1;
            i.for = int1 - 1;
        }
        else {
            if (k.if != null) {
                final l if1 = new l(k.if);
                i.if = if1;
                int n2 = if1.a;
                if (if1.a < 0) {
                    n2 = this.f.width;
                }
                int n3 = dimension.width * n2 / n - case2;
                if (case2 + n3 > dimension.width) {
                    n3 = dimension.width - case2;
                }
                a.a(vector, i, case2, int2, n3, this.o);
                return;
            }
            if (k.new != null) {
                final b new1 = new b(k.new);
                i.new = new1;
                int n4 = new1.a;
                if (new1.a < 0) {
                    n4 = this.f.width;
                }
                int n5 = dimension.width * n4 / n - case2;
                if (case2 + n5 > dimension.width) {
                    n5 = dimension.width - case2;
                }
                c.a(vector, i, case2, int2, n5);
            }
        }
    }
    
    private static void a(final Vector vector, int n, final int n2, int n3) {
        final k k = vector.elementAt(n);
        final u char1 = k.char;
        if (char1.byte == 6000) {
            k.int = n3;
            k.for = n3 + n2 - 1;
            return;
        }
        if (char1.byte == 7000) {
            final int n4 = n2 - a(vector, n - 1);
            k.int = n3;
            if (n4 > 0) {
                k.for = n3 + n4 - 1;
                return;
            }
            k.for = n3 - 1;
        }
        else if (char1.byte == 3000) {
            final int if1 = if(vector, n - 1);
            k.int = n3;
            if (if1 > 0 && if1 < n2) {
                k.for = n3 + n2 - if1 - 1;
                return;
            }
            k.for = n3 - 1;
        }
        else if (char1.byte == 1000 || char1.byte == 2000 || char1.byte == 4000 || char1.byte == 5000) {
            final int if2 = if(vector, n - 1);
            if (if2 <= 0 || if2 >= n2) {
                k.int = n3;
                k.for = n3 - 1;
                return;
            }
            int do1 = do(vector, n - 1);
            int n5 = n2 - if2;
            if (char1.byte == 1000 || char1.byte == 2000) {
                n5 /= 2;
            }
            if (do1 >= 0) {
                int a = 0;
                if (char1.byte == 1000 || char1.byte == 4000) {
                    a = a(vector, do1 - 1);
                }
                if (n5 > a) {
                    final int f = n5 - a;
                    final k i = vector.elementAt(do1);
                    final k j = new k();
                    final u char2 = new u();
                    j.int = i.int;
                    j.for = i.int + f - 1;
                    char2.L = 400;
                    char2.F = f;
                    j.char = char2;
                    vector.insertElementAt(j, do1);
                    for (int l = ++do1; l < vector.size() - 1; ++l) {
                        a(vector.elementAt(l), 0, f);
                    }
                    n3 += f;
                    ++n;
                }
            }
            if (char1.byte == 4000 || char1.byte == 5000) {
                k.int = n3;
                k.for = n3 - 1;
                return;
            }
            final int n6 = n2 - if2 - n5;
            k.int = n3;
            if (n6 > 0) {
                k.for = n3 + n6 - 1;
                return;
            }
            k.for = n3 - 1;
        }
        else {
            if (char1.F > 0) {
                k.int = n3;
                k.for = n3 + char1.F - 1;
                return;
            }
            k.int = n3;
            k.for = n3 - 1;
        }
    }
    
    private static int do(final Vector vector, final int n) {
        for (int i = n; i > -1; --i) {
            final k k = vector.elementAt(i);
            if (k.new != null || k.if != null || k.a != null) {
                return i;
            }
        }
        return -1;
    }
    
    private static int a(final Vector vector, final int n) {
        int n2 = 0;
        for (int i = n; i > -1; --i) {
            final k k = vector.elementAt(i);
            if (k.char == null || k.char.L != 400) {
                return n2;
            }
            final int n3 = k.for - k.int + 1;
            if (n3 > 0) {
                n2 += n3;
            }
        }
        return n2;
    }
    
    private static int if(final Vector vector, final int n) {
        int n2 = 0;
        for (int i = n; i > -1; --i) {
            final k k = vector.elementAt(i);
            final int n3 = k.for - k.int + 1;
            if (n3 > 0) {
                n2 += n3;
            }
            if (k.new != null || k.if != null || k.a != null) {
                return n2;
            }
        }
        return 0;
    }
    
    private static void a(final k k, final int case1, final int int1, final int byte1, final int for1) {
        if (case1 < k.case) {
            k.case = case1;
        }
        if (int1 < k.int) {
            k.int = int1;
        }
        if (byte1 > k.byte) {
            k.byte = byte1;
        }
        if (for1 > k.for) {
            k.for = for1;
        }
    }
    
    private static void a(final k k, final int n, final int n2) {
        if (n == 0 && n2 == 0) {
            return;
        }
        k.case += n;
        k.byte += n;
        k.int += n2;
        k.for += n2;
        if (k.a != null && k.a.else != null) {
            for (int i = 0; i < k.a.else.length; ++i) {
                a(k.a.else[i], n, n2);
            }
        }
    }
    
    private static void a(final k k) {
        final int n = k.byte - k.case + 1;
        final int n2 = k.for - k.int + 1;
        if (k.a.else != null) {
            for (int i = 0; i < k.a.else.length; ++i) {
                int n3 = 0;
                int n4 = 0;
                final k j = k.a.else[i];
                if (k.a.case == 100) {
                    if (j.null == 20) {
                        n4 = n2 - (j.for - j.int + 1);
                    }
                    else if (j.null == 30) {
                        n4 = (n2 - (j.for - j.int + 1)) / 2;
                    }
                }
                else if (j.null == 50) {
                    n3 = n - (j.byte - j.case + 1);
                }
                else if (j.null == 30) {
                    n3 = (n - (j.byte - j.case + 1)) / 2;
                }
                a(j, n3, n4);
            }
        }
    }
    
    private static void a(final Dimension dimension, final k k) {
        final int width = dimension.width;
        int n;
        if (k.null == 50) {
            n = width - (k.byte - k.case + 1);
        }
        else {
            if (k.null != 30) {
                return;
            }
            n = (width - (k.byte - k.case + 1)) / 2;
        }
        a(k, n, 0);
    }
    
    private void a(final Graphics graphics, final int n, final Dimension dimension, final int n2, final k k, final int n3, final int n4, final boolean b, boolean b2) {
        int n5 = -1;
        if (n2 > dimension.height && n + dimension.height > n2) {
            n5 = dimension.height + n - n2;
        }
        if (k.int < n5 || (k.int < n + dimension.height - 1 && k.for > n)) {
            if (k.a != null) {
                if ((this.g && this.r == k) || k.a.c != null || k.a.b != null) {
                    this.a(graphics, dimension, k, n, n5, n2, n3, n4);
                }
                if (k.a.else != null) {
                    for (int i = 0; i < k.a.else.length; ++i) {
                        if (!b2 && this.q && a(k.a.null, k.a.for, k.a.void, k.a.try) && n3 >= 0 && n3 < dimension.width && n4 >= 0 && n4 < dimension.height && n3 >= k.case && n3 <= k.byte && ((n4 + n >= k.int && n4 + n <= k.for) || (k.int < n5 && n4 >= k.int + n2 - n && n4 <= k.for + n2 - n)) && a(k, n, n2, n5, n3, n4)) {
                            this.w = k.a.for;
                            this.l = k.a.try;
                            this.j = k.a.void;
                            this.t = k.a.null;
                            b2 = true;
                        }
                        this.a(graphics, n, dimension, n2, k.a.else[i], n3, n4, b, b2);
                    }
                }
            }
            else {
                if (k.if != null) {
                    this.a(graphics, dimension, k, n, n5, n2, n3, n4, b, b2);
                    return;
                }
                if (k.new != null) {
                    this.if(graphics, dimension, k, n, n5, n2, n3, n4, b, b2);
                }
            }
        }
    }
    
    private void a(final Graphics graphics, final Dimension dimension, final k k, final int n, final int n2, final int n3, final int n4, final int n5) {
        final f a = k.a;
        boolean b = false;
        boolean b2 = false;
        final int case1 = k.case;
        final int byte1 = k.byte;
        int n6 = byte1 - case1 + 1 - a.d - a.if;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        if (case1 + a.d + n6 > dimension.width) {
            n6 = dimension.width - case1 - a.d;
        }
        if (k.int < n + dimension.height && k.for >= n) {
            b = true;
            n7 = k.int - n;
            n8 = k.for - n;
            n11 = n8 - n7 + 1 - a.char - a.do;
            if (n7 + n11 > dimension.height) {
                n11 = dimension.height - n7;
            }
        }
        if (k.int < n2) {
            b2 = true;
            n9 = n3 - n + k.int;
            n10 = n3 - n + k.for;
            n12 = n10 - n9 + 1 - a.char - a.do;
            if (n9 + n12 > dimension.height) {
                n12 = dimension.height - n9;
            }
        }
        if ((a.c != null && !this.g) || (this.g && this.r == k)) {
            int n13 = case1;
            int n14 = byte1 - case1 + 1;
            if (this.g) {
                graphics.setColor(this.p);
            }
            else {
                graphics.setColor(a.c);
                n14 = n6;
                n13 = case1 + a.d;
            }
            if (b2) {
                int n15;
                int n16;
                if (this.g) {
                    n15 = n9;
                    n16 = n10 - n9 + 1;
                }
                else {
                    n15 = n9 + a.char;
                    n16 = n12;
                }
                graphics.setClip(n13, n15, n14, n16);
                graphics.fillRect(n13, n15, n14, n16);
            }
            if (b) {
                int n17;
                int n18;
                if (this.g) {
                    n17 = n7;
                    n18 = n8 - n7 + 1;
                }
                else {
                    n17 = n7 + a.char;
                    n18 = n11;
                }
                graphics.setClip(n13, n17, n14, n18);
                graphics.fillRect(n13, n17, n14, n18);
            }
        }
        if (a.b != null && !this.g) {
            final int n19 = case1 + a.d;
            final int n20 = n6;
            if (b2) {
                final int n21 = n9 + a.char;
                graphics.setClip(n19, n21, n20, n12);
                graphics.drawImage(a.b, n19, n21, this.o);
            }
            if (b) {
                final int n22 = n7 + a.char;
                graphics.setClip(n19, n22, n20, n11);
                graphics.drawImage(a.b, n19, n22, this.o);
            }
        }
    }
    
    private static boolean a(final k k, final int n, final int n2, final int n3, final int n4, final int n5) {
        int int1 = k.int;
        int for1 = k.for;
        if (n3 > 0 && k.int <= n3 && n5 >= n2 - n) {
            int1 = k.int + n2;
            for1 = k.for + n2;
        }
        if (n4 < k.case || n4 > k.byte || n5 < int1 - n || n5 > for1 - n) {
            return false;
        }
        if (k.a != null) {
            if (k.a.else != null) {
                for (int i = 0; i < k.a.else.length; ++i) {
                    if (a(k.a.else[i], n, n2, n3, n4, n5)) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (k.if != null) {
            if (k.if.if != null) {
                for (int j = 0; j < k.if.if.length; ++j) {
                    final m m = k.if.if[j];
                    if (m.if != null && a(m, n, k.case, int1, n4, n5)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return k.new != null && ((k.new.d != null || a(k.new.else, k.new.new, k.new.c, k.new.try)) && n4 >= k.case + k.new.char && n4 < k.case + k.new.char + k.new.void && n5 >= int1 - n + k.new.case && n5 < int1 - n + k.new.case + k.new.goto);
    }
    
    private void if(final Graphics graphics, final Dimension dimension, final k k, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final boolean b2) {
        final b new1 = k.new;
        boolean b3 = false;
        boolean b4 = false;
        final int n6 = k.case + new1.char;
        final int n7 = k.byte - new1.g;
        final int n8 = k.int - n + new1.case;
        final int n9 = n8 + new1.goto;
        final int n10 = n3 - n + k.int + new1.case;
        final int n11 = n10 + new1.goto;
        int n12 = n7 - n6 + 1;
        int goto1 = new1.goto;
        int goto2 = new1.goto;
        if (n6 + n12 > dimension.width) {
            n12 = dimension.width - n6;
        }
        if (n8 + goto1 > dimension.height) {
            goto1 = dimension.height - n8;
        }
        if (n10 + goto2 > dimension.height) {
            goto2 = dimension.height - n10;
        }
        if (n8 < dimension.height && n9 >= 0) {
            b3 = true;
        }
        if (k.int < n2) {
            b4 = true;
        }
        if (this.g && this.r == k) {
            graphics.setColor(this.p);
            if (b4) {
                graphics.setClip(k.case, n10 - new1.case, k.byte - k.case, goto2 + new1.int + new1.b);
                graphics.fillRect(k.case, n10 - new1.case, k.byte - k.case, goto2 + new1.int + new1.b);
            }
            if (b3) {
                graphics.setClip(k.case, n8 - new1.case, k.byte - k.case, goto1 + new1.int + new1.b);
                graphics.fillRect(k.case, n8 - new1.case, k.byte - k.case, goto1 + new1.int + new1.b);
            }
        }
        if (!b3 && !b4) {
            return;
        }
        final boolean a = a(new1.else, new1.new, new1.c, new1.try);
        Image image;
        if (!b && (new1.d != null || a) && (b2 || (this.q && n4 >= 0 && n4 < dimension.width && n5 >= 0 && n5 < dimension.height && n4 >= n6 && n4 < n6 + new1.void && ((b3 && n5 >= n8 && n5 < n9) || (b4 && n5 >= n10 && n5 < n11))))) {
            if (!b2) {
                this.w = new1.new;
                this.l = new1.try;
                this.j = new1.c;
                this.t = new1.else;
            }
            if (new1.d != null || a) {
                this.m = true;
            }
            if (new1.d != null) {
                image = new1.d;
            }
            else {
                image = new1.do;
            }
        }
        else {
            image = new1.do;
        }
        if (b3 && image != null) {
            graphics.setClip(n6, n8, n12, goto1);
            if (image == new1.do) {
                graphics.drawImage(image, n6, n8, this.o);
            }
            else {
                graphics.drawImage(image, n6, n8, new1.void, new1.goto, this.o);
            }
        }
        if (b4 && image != null) {
            graphics.setClip(n6, n10, n12, goto2);
            if (image == new1.do) {
                graphics.drawImage(image, n6, n10, this.o);
                return;
            }
            graphics.drawImage(image, n6, n10, new1.void, new1.goto, this.o);
        }
    }
    
    private void a(final Graphics graphics, final Dimension dimension, final k k, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final boolean b2) {
        final l if1 = k.if;
        boolean b3 = false;
        boolean b4 = false;
        final int case1 = k.case;
        final int byte1 = k.byte;
        final int n6 = k.int - n;
        final int n7 = k.for - n;
        final int n8 = n3 - n + k.int;
        final int n9 = n3 - n + k.for;
        int n10 = byte1 - case1 + 1 - if1.null - if1.c;
        int n11 = n7 - n6 + 1 - if1.new - if1.goto;
        int n12 = n9 - n8 + 1 - if1.new - if1.goto;
        if (if1.if == null) {
            return;
        }
        if (case1 + if1.null + n10 > dimension.width) {
            n10 = dimension.width - case1 - if1.null;
        }
        if (n6 + n11 > dimension.height) {
            n11 = dimension.height - n6;
        }
        if (n8 + n12 > dimension.height) {
            n12 = dimension.height - n8;
        }
        if (k.int < n + dimension.height && k.for >= n) {
            b3 = true;
        }
        if (k.int < n2) {
            b4 = true;
        }
        if (!b3 && !b4) {
            return;
        }
        if ((if1.b != null && !this.g) || (this.g && this.r == k)) {
            int n13 = case1;
            int n14 = byte1 - case1 + 1;
            if (this.g) {
                graphics.setColor(this.p);
            }
            else {
                graphics.setColor(if1.b);
                n14 -= if1.for + if1.d;
                n13 = case1 + if1.d;
            }
            if (b4) {
                int n15;
                int n16;
                if (this.g) {
                    n15 = n8;
                    n16 = n9 - n8 + 1;
                }
                else {
                    n15 = n8 + if1.byte;
                    n16 = n9 - n8 + 1 - if1.byte - if1.int;
                }
                graphics.setClip(n13, n15, n14, n16);
                graphics.fillRect(n13, n15, n14, n16);
            }
            if (b3) {
                int n17;
                int n18;
                if (this.g) {
                    n17 = n6;
                    n18 = n7 - n6 + 1;
                }
                else {
                    n17 = n6 + if1.byte;
                    n18 = n7 - n6 + 1 - if1.byte - if1.int;
                }
                graphics.setClip(n13, n17, n14, n18);
                graphics.fillRect(n13, n17, n14, n18);
            }
        }
        if (if1.void != null && !this.g) {
            final int n19 = case1 + if1.d;
            final int n20 = byte1 - case1 + 1 - if1.for - if1.d;
            if (b4) {
                final int n21 = n8 + if1.byte;
                graphics.setClip(n19, n21, n20, n9 - n8 + 1 - if1.byte - if1.int);
                graphics.drawImage(if1.void, n19, n21, this.o);
            }
            if (b3) {
                final int n22 = n6 + if1.byte;
                graphics.setClip(n19, n22, n20, n7 - n6 + 1 - if1.byte - if1.int);
                graphics.drawImage(if1.void, n19, n22, this.o);
            }
        }
        for (int length = if1.if.length, i = 0; i < length; ++i) {
            final m m = if1.if[i];
            int length2;
            if (m.a == null) {
                length2 = 0;
            }
            else {
                length2 = m.a.length;
            }
            if (m.byte != null) {
                graphics.setFont(m.byte);
            }
            final boolean a = a(m.else, m.for, m.goto, m.new);
            if (!b && (m.if != null || a) && (b2 || (this.q && n4 >= 0 && n4 < dimension.width && n5 >= 0 && n5 < dimension.height && n4 >= case1 && n4 <= byte1 && ((b3 && n5 >= n6 && n5 <= n7 && a(m, n, k.case, k.int, n4, n5)) || (b4 && n5 >= n8 && n5 <= n9 && a(m, n, k.case, k.int + n3, n4, n5)))))) {
                if (!b2) {
                    this.w = m.for;
                    this.l = m.new;
                    this.j = m.goto;
                    this.t = m.else;
                }
                if (m.if != null || a) {
                    this.m = true;
                }
                if (m.if != null) {
                    graphics.setColor(m.if);
                }
                else {
                    graphics.setColor(m.int);
                }
            }
            else {
                graphics.setColor(m.int);
            }
            for (int j = 0; j < length2; ++j) {
                final q q = m.a[j];
                if ((!b3 || n6 + q.for > dimension.height - 1) && (!b4 || n8 + q.for > dimension.height - 1)) {
                    return;
                }
                if (b3 && n6 + q.byte >= 0) {
                    graphics.setClip(case1 + if1.null, n6 + if1.new, n10, n11);
                    graphics.drawString(q.try, case1 + q.new, n6 + q.if);
                    if (m.long) {
                        graphics.drawLine(case1 + q.new, n6 + q.if + 1, case1 + q.a, n6 + q.if + 1);
                    }
                }
                if (b4 && n8 + q.int < dimension.height) {
                    graphics.setClip(case1 + if1.null, n8 + if1.new, n10, n12);
                    graphics.drawString(q.try, case1 + q.new, n8 + q.if);
                    if (m.long) {
                        graphics.drawLine(case1 + q.new, n8 + q.if + 1, case1 + q.a, n8 + q.if + 1);
                    }
                }
            }
        }
    }
    
    private static boolean a(final m m, final int n, final int n2, final int n3, int n4, int n5) {
        if (m.a == null) {
            return false;
        }
        final int length = m.a.length;
        n4 -= n2;
        n5 = n5 - n3 + n;
        for (int i = 0; i < length; ++i) {
            final q q = m.a[i];
            if (n4 >= q.new && n4 <= q.a && n5 >= q.int && n5 <= q.byte) {
                return true;
            }
        }
        return false;
    }
}
