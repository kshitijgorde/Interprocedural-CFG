import java.util.ArrayList;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_N extends rp_J implements rp_eY
{
    int c;
    public rp_aJ a;
    public rp_fx a;
    public boolean a;
    Vector b;
    rp_ah a;
    rp_dC a;
    public rp_aY a;
    boolean b;
    private Vector c;
    
    rp_N(final rp_fx a, final rp_aJ a2) {
        this.a = false;
        this.a = null;
        this.b = false;
        this.c = new Vector(1);
        this.a = a;
        this.a = a2;
    }
    
    public final void a() {
        super.a();
        this.c = 0;
        this.a = false;
        this.b = new Vector(20);
        this.a = new rp_ah(this.a.a("undo_size"));
        this.a = new rp_aY();
    }
    
    public final void a(final boolean a) {
        this.a = a;
        if (this.a) {
            this.a.c();
        }
    }
    
    protected final void a(final rp_eA rp_eA) {
        final Enumeration<rp_eA> elements = rp_eA.a.elements();
        final Vector<rp_c> vector = new Vector<rp_c>(20);
        final rp_b rp_b = new rp_b();
        while (elements.hasMoreElements()) {
            final rp_eA rp_eA2 = elements.nextElement();
            rp_dC rp_dC = null;
            if (rp_eA2.a.equals("item") && (rp_dC = rp_cv.a(rp_eA2, this.a.a(), rp_b)) != null) {
                vector.addElement((rp_c)rp_dC);
            }
            final rp_eA rp_eA3;
            if (rp_eA2.a.equals("tape-dim") && (rp_dC = new rp_cw((rp_eA3 = rp_eA2).a("id", -1), rp_eA3.a("x1", 0), rp_eA3.a("y1", 0), rp_eA3.a("x2", 0), rp_eA3.a("y2", 0))) != null) {
                vector.addElement((rp_c)rp_dC);
            }
            final rp_eA rp_eA4;
            if (rp_eA2.a.equals("dim") && (rp_dC = new rp_aN((rp_eA4 = rp_eA2).a("id", -1), rp_eA4.a("x1", 0), rp_eA4.a("y1", 0), rp_eA4.a("x2", 0), rp_eA4.a("y2", 0))) != null) {
                vector.addElement((rp_c)rp_dC);
            }
            if (rp_eA2.a.equals("label") && (rp_dC = rp_et.a(rp_eA2)) != null) {
                vector.addElement((rp_c)rp_dC);
            }
            if (rp_dC != null) {
                this.b = Math.max(this.b, rp_dC.h + 1);
            }
        }
        if (rp_b.a.size() > 0) {
            rp_b.a(rp_au.a.a(), rp_au.a());
        }
        final Enumeration<rp_eA> elements2 = rp_eA.a.elements();
        while (elements2.hasMoreElements()) {
            final rp_eA rp_eA5 = elements2.nextElement();
            rp_c a = null;
            if (rp_eA5.a.equals("wall") && (a = rp_c.a(this, rp_eA5)) != null) {
                vector.addElement(a);
            }
            if (a != null) {
                this.b = Math.max(this.b, a.h + 1);
            }
        }
        final rp_dC[] array = new rp_dC[vector.size()];
        vector.toArray(array);
        this.e(array);
        final Enumeration a2 = this.a(3);
        while (a2.hasMoreElements()) {
            final rp_dC rp_dC2;
            if ((rp_dC2 = a2.nextElement()).h == -1) {
                rp_dC2.h = this.a();
            }
        }
    }
    
    public final void b(final rp_dC rp_dC) {
        this.a(new rp_dC[] { rp_dC });
    }
    
    public final void a(final rp_dC[] array) {
        final int[] array2 = new int[array.length];
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            final int e;
            if (array[i] != null && (e = array[i].e()) != -1) {
                boolean b = false;
                for (int j = 0; j < n; ++j) {
                    if (array2[j] == e) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    array2[n++] = e;
                }
            }
        }
        for (final int n2 : array2) {
            final rp_an rp_an = new rp_an(this.a.getGraphics());
            rp_aN rp_aN = null;
            final Enumeration a = this.a(3);
            while (a.hasMoreElements()) {
                final rp_dC rp_dC;
                if ((rp_dC = a.nextElement()).h == n2) {
                    rp_aN = (rp_aN)rp_dC;
                }
                if (rp_dC.e() == n2) {
                    rp_dC.a(rp_an, this.a);
                }
            }
            final Rectangle a2;
            if ((a2 = rp_an.a) == null) {
                if (rp_aN != null) {
                    this.a((rp_dC)rp_aN);
                }
            }
            else {
                if (rp_aN == null) {
                    rp_aN = new rp_aN(n2, a2);
                    this.a((rp_dC)rp_aN);
                }
                else {
                    rp_aN.a(a2);
                }
                this.d(rp_aN);
            }
        }
    }
    
    public final void b() {
        this = this;
        final Vector<rp_c> vector = new Vector<rp_c>();
        final Enumeration a = this.a(3);
        while (a.hasMoreElements()) {
            final rp_dC rp_dC;
            if ((rp_dC = a.nextElement()).a(4096)) {
                vector.addElement((rp_c)rp_dC);
            }
        }
        final Vector<rp_c> vector2 = vector;
        for (int i = 0; i < vector2.size(); ++i) {
            final rp_c rp_c;
            if ((rp_c = vector2.elementAt(i)).f == -1 || rp_c.g == -1) {
                for (int j = i + 1; j < vector2.size(); ++j) {
                    final rp_c rp_c2 = vector2.elementAt(j);
                    if (rp_c.f == -1 && rp_c.b().equals(rp_c2.c())) {
                        rp_c.f = rp_c2.h;
                        rp_c2.g = rp_c.h;
                    }
                    if (rp_c.g == -1 && rp_c.c().equals(rp_c2.b())) {
                        rp_c.g = rp_c2.h;
                        rp_c2.f = rp_c.h;
                    }
                }
            }
        }
    }
    
    public final void a(final rp_dC rp_dC, final boolean b) {
        if (rp_dC != null) {
            if (b) {
                this.c();
                this.b(rp_dC, false);
                return;
            }
            this.a(rp_dC, 0);
            this.d(rp_dC);
        }
    }
    
    public final void c(final rp_dC rp_dC) {
        final int size = super.a.size();
        int n = 0;
        final Rectangle a = rp_dC.a(0);
        final Enumeration a2 = this.a(3);
        while (a2.hasMoreElements()) {
            if (a2.nextElement() == rp_dC) {
                if (n >= size - this.c) {
                    --this.c;
                    this.a.e();
                }
                this.a(rp_dC);
            }
            ++n;
        }
        this.a.a(a);
    }
    
    public final boolean a(final rp_dt rp_dt) {
        final rp_ah a;
        if ((a = this.a).c < a.b) {
            a.b = a.c;
        }
        int n2;
        int n;
        if (a.c == a.a) {
            for (int i = 1; i < a.a; ++i) {
                a.a[i - 1] = a.a[i];
            }
            a.a[a.c - 1] = rp_dt;
            n = (n2 = a.c - 1);
        }
        else {
            a.a[a.c] = rp_dt;
            final rp_ah rp_ah = a;
            ++rp_ah.c;
            final rp_ah rp_ah2 = a;
            ++rp_ah2.b;
            n = (n2 = a.c - 1);
        }
        final int n3 = n2;
        if (n != -1) {
            this.a.a(n3);
            this.a(3, true);
            this.a(true);
            return true;
        }
        return false;
    }
    
    public final void d(final rp_dC rp_dC) {
        this.a.a(rp_dC.a(0));
    }
    
    public final void a(final rp_eV rp_eV) {
        final Rectangle a = rp_eV.a();
        if (this.b()) {
            for (int i = 1; i <= 10; ++i) {
                final Enumeration a2 = this.a(1);
                while (a2.hasMoreElements()) {
                    final rp_dC rp_dC;
                    final Rectangle a3;
                    if ((rp_dC = a2.nextElement()).a() == i && (a == null || (a3 = rp_dC.a(0)) == null || a.intersects(a3))) {
                        rp_dC.a(rp_eV, this.a);
                    }
                }
            }
            for (int j = 1; j <= 10; ++j) {
                final Enumeration<rp_dC> elements = this.b.elements();
                while (elements.hasMoreElements()) {
                    final rp_dC rp_dC2;
                    if ((rp_dC2 = elements.nextElement()).a() == j) {
                        rp_eV.a(true);
                        rp_dC2.a(rp_eV, this.a);
                    }
                }
            }
            rp_eV.a(false);
            return;
        }
        super.a(rp_eV, this.a, this.a);
    }
    
    final Rectangle a(final Graphics graphics) {
        final rp_an rp_an = new rp_an(graphics);
        final Enumeration a = this.a(3);
        while (a.hasMoreElements()) {
            final rp_dC rp_dC;
            if ((rp_dC = a.nextElement()).a(4096)) {
                rp_dC.a(rp_an, null);
            }
        }
        final Rectangle a2;
        if ((a2 = rp_an.a) != null) {
            a2.grow(40000, 10000);
            return new Rectangle(a2);
        }
        return null;
    }
    
    final boolean a(final int n, int n2, final boolean b, final boolean b2) {
        if (this.b()) {
            return false;
        }
        final rp_dC a;
        if ((a = this.a(n, n2, -1)) == null) {
            return false;
        }
        if (a.a(4096)) {
            return false;
        }
        if ((n2 = ((a != null && a.a(2)) ? 1 : 0)) && this.c > 0) {
            this.c();
            this.b(a, false);
            return true;
        }
        rp_dC[] a2;
        if (n2 != 0) {
            a2 = this.a(true);
        }
        else {
            final rp_dC[] array = a2 = new rp_dC[] { null };
            array[0] = a;
        }
        if (a2 == null) {
            return false;
        }
        for (int i = 0; i < a2.length; ++i) {
            if (a2[i].a(64)) {
                return false;
            }
        }
        double a3 = rp_au.a.a(b ? "rot_step_sm" : "rot_step");
        if (b2) {
            a3 = -a3;
        }
        return this.a(a2, a3, null, null);
    }
    
    public final boolean a(final rp_dC[] array, final double n, final Point point, final Rectangle rectangle) {
        int n2 = 0;
        int n3 = 0;
        int x;
        int y;
        if (point != null) {
            x = point.x;
            y = point.y;
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                final Point a = array[i].a();
                n2 += a.x;
                n3 += a.y;
            }
            x = n2 / array.length;
            y = n3 / array.length;
        }
        final boolean a2 = this.a(new rp_cQ(array, x, y, n));
        if (rectangle != null) {
            for (int j = 0; j < array.length; ++j) {
                if (j == 0) {
                    rectangle.setBounds(array[j].a(0));
                }
                else {
                    rectangle.setBounds(rectangle.union(array[j].a(0)));
                }
            }
        }
        return a2;
    }
    
    public final void c() {
        this.c = 0;
        final Enumeration a = this.a(3);
        while (a.hasMoreElements()) {
            a.nextElement().a(2, false);
        }
        this.a.e();
    }
    
    public final void b(final rp_dC rp_dC, final boolean b) {
        this.a(rp_dC);
        if (rp_dC.a(1)) {
            this.a(rp_dC);
            rp_dC.a(2, true);
            ++this.c;
            if (!b) {
                this.a.e();
            }
        }
        else {
            this.a(rp_dC, 0);
        }
    }
    
    public final void a(final Rectangle rectangle, final boolean b) {
        final Vector<rp_dC> vector = new Vector<rp_dC>();
        final Enumeration<rp_dC> elements = super.a.elements();
        while (elements.hasMoreElements()) {
            final rp_dC rp_dC;
            if ((rp_dC = elements.nextElement()).a(1) && !rp_dC.a(4096)) {
                final Rectangle a = rp_dC.a(1);
                if ((!b || !rectangle.contains(a)) && (b || !rectangle.contains(a))) {
                    continue;
                }
                vector.addElement(rp_dC);
            }
        }
        for (int i = 0; i < vector.size(); ++i) {
            this.b(vector.elementAt(i), false);
        }
    }
    
    final void e(final rp_dC rp_dC) {
        final int size = super.a.size();
        this.a(rp_dC);
        this.a(rp_dC, size - this.c);
        rp_dC.a(2, false);
        --this.c;
        this.a.e();
    }
    
    public final void a(final int n, final int n2, final int n3) {
        final Enumeration a = this.a(-1);
        while (a.hasMoreElements()) {
            final rp_dC rp_dC;
            if ((rp_dC = a.nextElement()) instanceof rp_c) {
                ((rp_c)rp_dC).b(n2, n3);
            }
            else {
                rp_dC.a(n2, n3);
            }
        }
    }
    
    void a(final int n, final int n2) {
        final Enumeration a = this.a(4);
        while (a.hasMoreElements()) {
            final rp_dC rp_dC = a.nextElement();
            if (this.a.b != 0.0) {
                rp_dC.a(this.a.c, this.a.d, this.a.b);
            }
            rp_dC.a(n, n2);
        }
        final rp_aY a2 = this.a;
        a2.e += n;
        final rp_aY a3 = this.a;
        a3.f += n2;
    }
    
    public final boolean a(final int n, final int n2) {
        this.a.a(n, n2);
        this.a.b();
        boolean b = false;
        int n3;
        int n4;
        if (this.b(this.a.a - this.a.e, this.a.b - this.a.f)) {
            n3 = this.a.g;
            n4 = this.a.h;
        }
        else if (this.a.a != 0.0) {
            b = true;
            this.a.b = 0.0;
            this.a.a = 0.0;
            this.a.e = 0;
            this.a.f = 0;
            n3 = this.a.a;
            n4 = this.a.b;
        }
        else {
            n3 = this.a.a - this.a.e;
            n4 = this.a.b - this.a.f;
        }
        final Enumeration a = this.a(4);
        while (a.hasMoreElements()) {
            final rp_cv rp_cv = a.nextElement();
            if (b) {
                final Point a2 = rp_cv.a();
                rp_cv.a(-a2.x, -a2.y);
                rp_cv.a(0, 0, -rp_cv.a);
            }
            if (this.a.b != 0.0) {
                rp_cv.a(this.a.c, this.a.d, this.a.b);
            }
            rp_cv.a(n3, n4);
        }
        final rp_aY a3 = this.a;
        a3.e += n3;
        final rp_aY a4 = this.a;
        a4.f += n4;
        return n - n3 != 0 || n2 - n4 != 0 || this.a.b != 0.0;
    }
    
    boolean a(final Point location, int n, final int n2) {
        if (location != null) {
            location.setLocation(0, 0);
        }
        final Enumeration a = this.a(4);
        while (a.hasMoreElements()) {
            final rp_dC rp_dC;
            if (!((rp_dC = a.nextElement()) instanceof rp_eF) || !rp_dC.a(131072) || rp_dC.a(4)) {
                return true;
            }
            if (((rp_cv)rp_dC).a()) {
                return true;
            }
        }
        final ArrayList<Point> list = new ArrayList<Point>();
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        list.add(new Point());
        final Enumeration a2 = this.a(4);
        while (a2.hasMoreElements()) {
            final Point[] a3;
            if ((a3 = a2.nextElement().a()) != null) {
                for (int i = 0; i < a3.length; ++i) {
                    list.add(a3[i]);
                    ++n3;
                    n4 += a3[i].x;
                    n5 += a3[i].y;
                }
            }
        }
        list.get(0).x = n4 / n3;
        list.get(0).y = n5 / n3;
        rp_C.a(list, n, n2);
        final Point point = new Point(0, 0);
        for (int j = 0; j < 20; ++j) {
            final Enumeration<rp_dC> elements = (Enumeration<rp_dC>)super.a.elements();
            while (elements.hasMoreElements()) {
                final rp_dC rp_dC2;
                if ((rp_dC2 = elements.nextElement()).a(4096)) {
                    final rp_c rp_c = (rp_c)rp_dC2;
                    final Point a4;
                    if ((a4 = a(list, rp_c.b().x, rp_c.b().y, rp_c.c().x, rp_c.c().y, rp_c.a)) != null) {
                        if (location == null) {
                            return false;
                        }
                        location.translate(a4.x, a4.y);
                        rp_C.a(list, a4.x, a4.y);
                    }
                }
                final rp_cv rp_cv;
                final rp_bV a5;
                if (rp_dC2.a(131072) && (rp_cv = (rp_cv)rp_dC2).a() && (a5 = rp_cv.a()) != null) {
                    n = (a5.b() ? rp_cv.a.a() : rp_cv.a.b());
                    final Point a6;
                    if ((a6 = a(list, a5.a().x, a5.a().y, a5.b().x, a5.b().y, n)) == null) {
                        continue;
                    }
                    if (location == null) {
                        return false;
                    }
                    location.translate(a6.x, a6.y);
                    rp_C.a(list, a6.x, a6.y);
                }
            }
            if (location == null) {
                return true;
            }
            if (location.equals(point)) {
                break;
            }
            point.setLocation(location);
        }
        return location.x == 0 && location.y == 0;
    }
    
    private static Point a(final ArrayList list, final int n, int size, final int n2, final int n3, int i) {
        final double n4 = i / 2.0;
        final rp_cT rp_cT = new rp_cT(new Point(n, size), new Point(n2, n3));
        size = list.size();
        if (!rp_cT.a()) {
            return null;
        }
        final double[] array = new double[size];
        final Point[] array2 = new Point[size];
        for (int j = 1; j < size; ++j) {
            final double a;
            if ((a = rp_cT.a(list.get(j))) >= 0.0 && a <= 1.0) {
                final Point[] array3 = array2;
                final int n5 = j;
                final rp_cT rp_cT2 = rp_cT;
                list.get(j);
                array3[n5] = rp_cT2.a(a);
                array[j] = rp_C.a(list.get(j).x, list.get(j).y, array2[j].x, array2[j].y);
                if (array2[j].x < list.get(j).x || (array2[j].x == list.get(j).x && array2[j].y < list.get(j).y)) {
                    array[j] = -array[j];
                }
            }
        }
        double signum = 0.0;
        boolean b = false;
        for (i = 0; i < size; ++i) {
            if (array2[i] != null) {
                if (Math.abs(array[i]) < n4) {
                    b = true;
                    break;
                }
                if (signum == 0.0) {
                    signum = Math.signum(array[i]);
                }
                else if (signum == -Math.signum(array[i])) {
                    b = true;
                    break;
                }
            }
        }
        if (b) {
            final Point point = new Point(list.get(0));
            final rp_cT rp_cT3 = rp_cT;
            final Point a2 = rp_cT3.a(rp_cT3.a(point));
            double signum2 = Math.signum(rp_C.a(point.x, point.y, a2.x, a2.y));
            if (a2.x < point.x || (a2.x == point.x && a2.y < point.y)) {
                signum2 = -signum2;
            }
            final Point point2 = new Point(0, 0);
            if (signum2 < 0.0) {
                double n6 = -1.7976931348623157E308;
                int n7;
                int n8;
                double n9;
                for (i = 0; i < size; ++i) {
                    if (array2[i] != null && array[i] > -n4 && array[i] > n6) {
                        n6 = array[i];
                        n7 = array2[i].x - list.get(i).x;
                        n8 = array2[i].y - list.get(i).y;
                        n9 = (n4 + array[i]) / array[i];
                        point2.x = (int)Math.round(n9 * n7);
                        point2.y = (int)Math.round(n9 * n8);
                    }
                }
            }
            else {
                double n10 = Double.MAX_VALUE;
                int n11;
                int n12;
                double n13;
                for (i = 0; i < size; ++i) {
                    if (array2[i] != null && array[i] < n4 && array[i] < n10) {
                        n10 = array[i];
                        n11 = array2[i].x - list.get(i).x;
                        n12 = array2[i].y - list.get(i).y;
                        n13 = (array[i] - n4) / array[i];
                        point2.x = (int)Math.round(n13 * n11);
                        point2.y = (int)Math.round(n13 * n12);
                    }
                }
            }
            return point2;
        }
        return null;
    }
    
    boolean b(final int n, final int n2) {
        return this.a(n, n2, rp_dc.b) || this.a(n, n2, rp_dc.a);
    }
    
    private boolean a(final int n, final int n2, final rp_dc rp_dc) {
        final Enumeration a = this.a(4);
        while (a.hasMoreElements()) {
            final rp_bV[] a2;
            if ((a2 = a.nextElement().a()) != null) {
                for (int i = 0; i < a2.length; ++i) {
                    if ((a2[i].a() != 1 || !a2[i].a(0).a()) && a2[i].a == rp_dc) {
                        final Enumeration a3 = this.a(1);
                        while (a3.hasMoreElements()) {
                            final rp_bV[] a4;
                            if ((a4 = a3.nextElement().a()) != null) {
                                for (int j = 0; j < a4.length; ++j) {
                                    if (0 != a4[j].a(a2[i], n, n2, this.a)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    final void a(int i, final boolean b) {
        final Vector<rp_dC> vector = new Vector<rp_dC>();
        final Enumeration a = this.a(i);
        while (a.hasMoreElements()) {
            vector.addElement(a.nextElement());
        }
        if (b) {
            for (int j = 0; j < vector.size(); ++j) {
                final rp_bV[] a2;
                if ((a2 = vector.elementAt(j).a()) != null && a2.length != 0) {
                    for (int k = 0; k < a2.length; ++k) {
                        a2[k].a();
                    }
                }
            }
        }
        for (int l = 0; l < vector.size() - 1; ++l) {
            final rp_bV[] a3;
            if ((a3 = vector.elementAt(l).a()) != null && a3.length != 0) {
                for (int n = 0; n < a3.length; ++n) {
                    if (b || a3[n].a() <= 0) {
                        rp_bV[] a4;
                        int n2;
                        rp_bV rp_bV;
                        rp_bV rp_bV2;
                        rp_bV rp_bV3;
                        for (i = l + 1; i < vector.size(); ++i) {
                            if ((a4 = vector.elementAt(i).a()) != null && a4.length != 0) {
                                for (n2 = 0; n2 < a4.length; ++n2) {
                                    if (b || a4[n2].a() <= 0) {
                                        rp_bV = a3[n];
                                        rp_bV2 = a4[n2];
                                        rp_bV3 = rp_bV;
                                        if ((rp_bV2.h != rp_bV.a || rp_bV3.h != rp_bV.b) ? (0 != rp_bV3.a(rp_bV2)) : (0 != rp_bV2.a(rp_bV3))) {
                                            a3[n].a(a4[n2], true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public final void f(final rp_dC rp_dC) {
        final ArrayList<rp_cv> list = new ArrayList<rp_cv>();
        final ArrayList<rp_cv> list2 = new ArrayList<rp_cv>();
        if (rp_dC != null) {
            list.add((rp_cv)rp_dC);
        }
        final Enumeration<rp_dC> elements = (this = this).a.elements();
        while (elements.hasMoreElements()) {
            final rp_dC rp_dC2 = elements.nextElement();
            if (rp_dC == null && rp_dC2.a(4)) {
                list.add((rp_cv)rp_dC2);
            }
            if (rp_dC2.a(4096)) {
                list2.add((rp_cv)rp_dC2);
            }
            if (rp_dC2.a(131072) && ((rp_cv)rp_dC2).a()) {
                list2.add((rp_cv)rp_dC2);
            }
        }
        for (int i = 0; i < list.size(); ++i) {
            final rp_bV[] a = list.get(i).a();
            rp_aY rp_aY = null;
            if (a != null && a.length != 0) {
                for (int j = 0; j < a.length; ++j) {
                    for (int k = 0; k < list2.size(); ++k) {
                        final rp_bV[] a2;
                        if ((a2 = list2.get(k).a()) != null && a2.length != 0) {
                            for (int l = 0; l < a2.length; ++l) {
                                final rp_aY rp_aY2 = new rp_aY();
                                if (rp_bV.g == a2[l].a(a[j], 0, 0, rp_aY2) && rp_aY2.a < 0.01 && (rp_aY == null || rp_aY2.g + rp_aY2.h < rp_aY.g + rp_aY.h)) {
                                    rp_aY = rp_aY2;
                                }
                            }
                        }
                    }
                }
                if (rp_aY != null) {
                    list.get(i).a(rp_aY.g, rp_aY.h);
                }
            }
        }
    }
    
    void d() {
        for (int i = 0; i < this.b.size(); ++i) {
            ((rp_dC)this.b.elementAt(i)).a();
        }
        this.b.removeAllElements();
    }
    
    public final boolean a(rp_dC[] a) {
        if (a == null) {
            if (this.c == 0) {
                return false;
            }
            a = this.a(false);
        }
        else {
            a = a;
        }
        if (a == null) {
            return false;
        }
        this.a(new rp_fe(a));
        this.d();
        this.a.e();
        return true;
    }
    
    final boolean a() {
        final rp_dC[] array = new rp_dC[super.a.size()];
        int n = 0;
        final Enumeration<rp_dC> elements = super.a.elements();
        while (elements.hasMoreElements()) {
            final rp_dC rp_dC;
            if (!(rp_dC = elements.nextElement()).a(4096) && (!rp_dC.a(131072) || !((rp_cv)rp_dC).a())) {
                array[n++] = rp_dC;
            }
        }
        if (n == 0) {
            return false;
        }
        this.a(new rp_fe(array));
        this.d();
        return true;
    }
    
    public final boolean b(rp_dC[] a) {
        if (a == null) {
            if (this.c == 0) {
                return false;
            }
            a = this.a(false);
        }
        else {
            a = a;
        }
        if (a == null) {
            return false;
        }
        this.a(new rp_ba(a, 50000, -50000));
        this.d();
        return true;
    }
    
    public final boolean c(rp_dC[] a) {
        if (a == null) {
            if (this.c == 0) {
                return false;
            }
            a = this.a(false);
        }
        else {
            a = a;
        }
        if (a == null) {
            return false;
        }
        int n = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i].a(8)) {
                ++n;
            }
        }
        if (n == 0) {
            return false;
        }
        final rp_dC[] array = new rp_dC[n];
        for (int j = 0; j < a.length; ++j) {
            if (a[j].a(8)) {
                array[j] = a[j];
            }
        }
        this.a(new rp_ay(array));
        return true;
    }
    
    public final boolean d(rp_dC[] a) {
        if (a == null) {
            if (this.c == 0) {
                return false;
            }
            a = this.a(false);
        }
        else {
            a = a;
        }
        if (a == null) {
            return false;
        }
        int e = -1;
        for (int i = 0; i < a.length; ++i) {
            if (a[i].e() != -1) {
                e = a[i].e();
                break;
            }
        }
        if (e == -1) {
            return false;
        }
        final rp_dC[] array = new rp_dC[super.a.size()];
        int n = 0;
        rp_aN rp_aN = null;
        final Enumeration a2 = this.a(3);
        while (a2.hasMoreElements()) {
            final rp_dC rp_dC;
            if ((rp_dC = a2.nextElement()).e() == e) {
                array[n++] = rp_dC;
            }
            if (rp_dC.h == e) {
                rp_aN = (rp_aN)rp_dC;
            }
        }
        this.a(new rp_ay(rp_aN, array, n));
        return true;
    }
    
    public final rp_dt a() {
        final Vector<rp_dC> vector = new Vector<rp_dC>();
        final Enumeration a = this.a(3);
        while (a.hasMoreElements()) {
            final rp_dC rp_dC;
            if ((rp_dC = a.nextElement()).a(4096)) {
                vector.addElement(rp_dC);
            }
        }
        if (vector.size() == 0) {
            return null;
        }
        final rp_dC[] array = new rp_dC[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        final rp_fe rp_fe = new rp_fe(array);
        this.d();
        return rp_fe;
    }
    
    public final boolean e(final rp_dC[] array) {
        this.a(new rp_fM(array));
        return true;
    }
    
    public final boolean b(final rp_dC rp_dC) {
        this.a(new rp_fM(new rp_dC[] { rp_dC }));
        return true;
    }
    
    public final rp_dC[] a(final boolean b) {
        final rp_dC[] array = new rp_dC[this.c];
        int n = 0;
        final Enumeration a = this.a(2);
        while (a.hasMoreElements()) {
            final rp_dC rp_dC = a.nextElement();
            if (b || !rp_dC.a(4096)) {
                array[n++] = rp_dC;
            }
        }
        if (n == this.c) {
            return array;
        }
        if (n == 0) {
            return null;
        }
        final rp_dC[] array2 = new rp_dC[n];
        for (int i = 0; i < n; ++i) {
            array2[i] = array[i];
        }
        return array2;
    }
    
    public final Rectangle a() {
        Rectangle rectangle = null;
        for (int i = 0; i < this.b.size(); ++i) {
            final Rectangle a;
            if ((a = this.b.elementAt(i).a(1)) != null) {
                if (rectangle == null) {
                    rectangle = a;
                }
                else {
                    rectangle.add(a);
                }
            }
        }
        return rectangle;
    }
    
    void e() {
        this.d();
        final Enumeration a = this.a(2);
        while (a.hasMoreElements()) {
            final rp_dC rp_dC;
            final rp_bV[] a2;
            if ((a2 = (rp_dC = a.nextElement()).a()) != null) {
                for (int i = 0; i < a2.length; ++i) {
                    a2[i].a();
                }
            }
            this.b.addElement(rp_dC.clone());
        }
        this.a(4, false);
    }
    
    final boolean b() {
        return this.b.size() > 0;
    }
    
    public final void g(final rp_dC rp_dC) {
        this.c.removeAllElements();
        this.c.addElement(rp_dC);
    }
    
    public final Enumeration a(int n) {
        if (n == -1) {
            if (this.b()) {
                n = 5;
            }
            else {
                n = 3;
            }
        }
        if (n == 3) {
            return super.a.elements();
        }
        if (n == 4) {
            if (this.c.size() > 0 && this.c.elementAt(0) != null) {
                return this.c.elements();
            }
            return this.b.elements();
        }
        else {
            if (n == 2) {
                final Enumeration elements = super.a.elements();
                for (int size = super.a.size(), n2 = 0; elements.hasMoreElements() && n2 < size - this.c; ++n2) {
                    elements.nextElement();
                }
                return elements;
            }
            return new rp_ag(this, n);
        }
    }
    
    public final void a(final Object o) {
        if (o != null && o instanceof rp_dC) {
            this.d((rp_dC)o);
        }
    }
}
