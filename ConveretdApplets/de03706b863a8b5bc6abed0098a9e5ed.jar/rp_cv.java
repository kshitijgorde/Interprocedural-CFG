import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Enumeration;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cv extends rp_eF implements rp_h
{
    public rp_dv a;
    private int e;
    private String b;
    public String a;
    private rp_bV[] a;
    private rp_eS a;
    private int f;
    private int g;
    
    public rp_cv(int i, final int n, final int n2, final double n3, final rp_dv a, final rp_fK rp_fK) {
        super(i, n, n2, 0.0);
        this.e = -1;
        this.b = null;
        this.a = null;
        this.a = null;
        this.a = a;
        if (a instanceof rp_fn && ((rp_fn)a).a) {
            this.a(32, true);
            this.a(64, true);
        }
        this.a = a.a(this);
        if (this.a != null) {
            for (i = 0; i < this.a.length; ++i) {
                this.a[i].a(this);
            }
        }
        this.a(1, true);
        this.a(8, true);
        this.a(131072, true);
        this.c();
        this.b();
        this.a = a.a().b();
        final Object a2;
        if ((a2 = a.a("desc")) != null && a2 instanceof String) {
            this.b = (String)a2;
        }
        final Object a3;
        if ((a3 = a.a("c-fill")) != null && a3 instanceof Color) {
            this.b = (Color)a3;
        }
        final Object a4;
        if ((a4 = a.a("c-border")) != null && a4 instanceof Color) {
            this.a = (Color)a4;
        }
        final Object a5;
        if ((a5 = a.a("custom")) != null && a5 instanceof Boolean) {
            this.a(128, (boolean)a5);
        }
    }
    
    public final void a(final rp_aJ rp_aJ) {
        if (this.a() == null && this.b() == null && rp_aJ.a() != null && rp_aJ.b() != null) {
            super.a = rp_aJ.a();
            super.b = rp_aJ.b();
        }
    }
    
    public final Object clone() {
        final rp_cv rp_cv = (rp_cv)super.clone();
        if (this.a != null) {
            rp_cv.a = new rp_bV[this.a.length];
            for (int i = 0; i < this.a.length; ++i) {
                rp_cv.a[i] = (rp_bV)this.a[i].clone();
            }
        }
        if (this.a != null) {
            rp_cv.a = (rp_dv)this.a.clone();
        }
        return rp_cv;
    }
    
    public final void a() {
        if (this.a != null) {
            for (int i = 0; i < this.a.length; ++i) {
                this.a[i].a();
            }
        }
    }
    
    public final int a() {
        return this.a.a;
    }
    
    public final void a(int a) {
        final rp_dv a2 = this.a;
        a = a;
        a2.a = a;
    }
    
    public final int d() {
        int n = 0;
        if (this.a(64)) {
            n = 1;
        }
        if (this.a.b()) {
            n |= 0x40;
        }
        if (this.a(128)) {
            n |= 0x40;
        }
        if (this.a.a().a() != 1) {
            return n | 0x40;
        }
        if (((rp_bZ)rp_au.a.a()).c) {
            n |= 0x40;
        }
        return n | 0x10;
    }
    
    public final rp_bV a() {
        if (this.a != null) {
            for (int i = 0; i < this.a.length; ++i) {
                if (this.a[i].h == rp_bV.a) {
                    return this.a[i];
                }
            }
        }
        return null;
    }
    
    public final boolean a() {
        return this.a() != null;
    }
    
    public final Color a() {
        if (this.a.b()) {
            return rp_aJ.m;
        }
        return super.a();
    }
    
    public final Color b() {
        if (this.a.b()) {
            return rp_aJ.n;
        }
        return super.b();
    }
    
    public final void a(final rp_eS a, final rp_eP rp_eP) {
        this.a = a;
        Color a2 = null;
        Color b = null;
        boolean b2 = false;
        if (this.a(2)) {
            if (!a.a) {
                this.a.a(true);
            }
        }
        else if (this.a() != null && this.b() != null) {
            this.a.a(true);
            a2 = this.a.a();
            b = this.a.b();
            b2 = true;
            this.a.a(this.a(), this.b());
        }
        final rp_bV a3;
        if ((a3 = this.a()) != null) {
            if (!rp_c.a(a, a3, a3.b() ? this.a.a() : this.a.b(), null, null)) {
                this.a.a(a, this);
            }
        }
        else {
            this.a.a(a, this);
        }
        if (b2) {
            this.a.a(a2, b);
        }
        this.a.a(false);
        if (a.a) {
            boolean b3 = false;
            Label_0275: {
                if (this.a.a() != null) {
                    if (this.a.a().a() == 1) {
                        b3 = true;
                        break Label_0275;
                    }
                    final String b4;
                    if ((b4 = this.a.a().b()) != null && b4.length() > 0) {
                        b3 = true;
                        break Label_0275;
                    }
                    final String a4;
                    if ((a4 = this.a()) != null && a4.length() > 0) {
                        b3 = true;
                        break Label_0275;
                    }
                }
                b3 = false;
            }
            if (b3) {
                String string = null;
                if (a.b) {
                    if (string == null) {
                        string = "";
                    }
                    String s;
                    if (this.a != null && this.a.length() > 0) {
                        s = this.a;
                    }
                    else {
                        s = this.a.a().b();
                    }
                    string = string + " " + s;
                }
                if (string != null && string.length() > 0) {
                    a.a(Color.black);
                    a.a(string, this.a, this.b, 1, 2);
                }
            }
        }
    }
    
    public final int a(int n, final int n2) {
        final int n3 = (int)Math.round(this.c * (n - this.a) + this.b * (n2 - this.b));
        n = (int)Math.round(this.c * (n2 - this.b) - this.b * (n - this.a));
        if (this.a.a(n3, n)) {
            return 7;
        }
        return 0;
    }
    
    public final Cursor a(final int n, final int n2) {
        return Cursor.getPredefinedCursor(13);
    }
    
    public final int e() {
        return this.e;
    }
    
    public final void b(final int e) {
        this.e = e;
    }
    
    public final rp_bV[] a() {
        return this.a;
    }
    
    private void b() {
        if (this.a != null) {
            for (int i = 0; i < this.a.length; ++i) {
                this.d(this.a[i].j, this.a[i].k);
                this.a[i].n = this.a + this.c;
                this.a[i].o = this.b + this.d;
                this.d(this.a[i].l, this.a[i].m);
                this.a[i].p = this.a + this.c;
                this.a[i].q = this.b + this.d;
            }
        }
    }
    
    public final void a(final int n, final int n2) {
        super.a(n, n2);
        if (this.a != null) {
            for (int i = 0; i < this.a.length; ++i) {
                final rp_bV rp_bV = this.a[i];
                rp_bV.n += n;
                final rp_bV rp_bV2 = this.a[i];
                rp_bV2.o += n2;
                final rp_bV rp_bV3 = this.a[i];
                rp_bV3.p += n;
                final rp_bV rp_bV4 = this.a[i];
                rp_bV4.q += n2;
            }
        }
    }
    
    public final void a(final int n, final int n2, final double n3) {
        super.a(n, n2, n3);
        this.b();
    }
    
    public final void a(final int n, final int n2, final double n3, final boolean b) {
        super.a(n, n2, n3, b);
        if (!b) {
            this.b();
        }
    }
    
    final void a(final double n) {
        super.a(n);
        this.b();
    }
    
    public final Rectangle a(final int n) {
        final Rectangle a = super.a(n);
        final rp_dC a2;
        if (n == 0 && this.e != -1 && rp_au.a != null && (a2 = rp_au.a.a.a(this.e)) != null) {
            a.add(a2.a(0));
        }
        return a;
    }
    
    public final boolean a(int b, final int n) {
        b = (this.a.b(b, n) ? 1 : 0);
        this.a(this.a.a(this));
        return b != 0;
    }
    
    private void c() {
        if (this.a != null) {
            for (int i = 0; i < this.a.length; ++i) {
                if (this.a[i].i == rp_bV.d || this.a[i].i == rp_bV.e) {
                    this.a(4, true);
                    return;
                }
            }
        }
    }
    
    public final void a(final rp_bV[] a) {
        this.a = a;
        this.c();
        this.b();
        rp_au.a.a.f(this);
    }
    
    public final int b() {
        return this.a.a();
    }
    
    public final int c() {
        return this.a.b();
    }
    
    public final void a_(final int n, final int n2) {
        this.d(n, n2);
        this.f = this.a + this.c;
        this.g = this.b + this.d;
    }
    
    public final void a(final rp_aV rp_aV, final int n, final int n2) {
        double a = this.a;
        if (this.d != 0.0 && (a = (this.a + this.d) % 360.0) < 0.0) {
            a += 360.0;
        }
        this.a.a(rp_aV, a, this.a - this.a.a() / 2, this.b - this.a.b() / 2, n, n2);
    }
    
    public final void c(int f, int g) {
        this.d(f, g);
        f = this.a + this.c;
        g = this.b + this.d;
        this.a.a(this.f, this.g, f, g);
        this.f = f;
        this.g = g;
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4, final int n5, final double n6, final double n7, final double n8) {
        this.d(n, n2);
        this.a.a(this.a + this.c, this.b + this.d, n3, n4, n5, n6, n7, (int)(0.5 + this.a + this.d));
    }
    
    public final void a(final int[] array, final int[] array2, final int n) {
        this.c(array, array2, n);
        this.a.a(array, array2, n);
    }
    
    public final void b(final int[] array, final int[] array2, final int n) {
        this.c(array, array2, n);
        this.a.b(array, array2, n);
    }
    
    public final void a(final int[] array, final int[] array2, final int n, final Color color) {
        this.c(array, array2, n);
        this.a.a(array, array2, n, color);
    }
    
    public final void a(final int[] array, final int[] array2, final int n, final int n2, Point point, Point point2, final Color color, final Color color2) {
        this.c(array, array2, n);
        this.d(point.x, point.y);
        point = new Point(this.a + this.c, this.b + this.d);
        this.d(point2.x, point2.y);
        point2 = new Point(this.a + this.c, this.b + this.d);
        this.a.a(array, array2, n, n2, point, point2, color, color2);
    }
    
    private void c(final int[] array, final int[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            this.d(array[i], array2[i]);
            array[i] = this.a + this.c;
            array2[i] = this.b + this.d;
        }
    }
    
    public final boolean a(final Frame frame) {
        final rp_fT a;
        return this.a != null && (a = this.a.a()) != null && a.a(frame, this, this.a);
    }
    
    public final void a(final StringBuffer sb, rp_eP rp_eP) {
        sb.append("TYPE=SKU\n");
        sb.append("POS:[");
        sb.append(this.a);
        sb.append(';');
        sb.append(this.b);
        sb.append("]\n");
        if (this.a.a() != null) {
            sb.append("BT=" + this.a.a() + "\n");
        }
        sb.append("cat=");
        sb.append(this.a.a().a());
        sb.append("\n");
        sb.append((this.a.a().a() == 2) ? "ei" : "brand");
        sb.append("\n");
        if (this.a(32)) {
            sb.append("Front view\n");
        }
        if (this.a(128)) {
            sb.append("Custom item\n");
        }
        final rp_cv rp_cv = this;
        rp_eP = rp_eP;
        this = rp_cv;
        final Vector<String> vector = new Vector<String>();
        String s;
        if (this.a != null && this.a.length() > 0) {
            s = this.a;
        }
        else {
            s = this.a.a().b();
        }
        vector.addElement(s);
        this.a.a().a(vector, this.a, -10, rp_eP);
        this.a(vector);
        final Vector<String> vector2;
        if ((vector2 = vector) != null && vector2.size() > 0) {
            for (int i = 0; i < vector2.size(); ++i) {
                sb.append(vector2.elementAt(i));
                sb.append('\n');
            }
        }
    }
    
    public final void a(final rp_eg rp_eg) {
        final rp_fT a = this.a.a();
        final Vector<String> vector;
        (vector = new Vector<String>()).addElement(rp_eg.a("id", Integer.toString(this.h)));
        vector.addElement(rp_eg.a("SKU", a.d));
        if (this.a(128)) {
            vector.addElement(rp_eg.a("custom", "true"));
        }
        if (this.a.a() != null) {
            vector.addElement(rp_eg.a("template", this.a.a()));
        }
        vector.addElement(rp_eg.a("cat", a.a()));
        if (this.a.a() == null || a.a() == 1) {
            vector.addElement(rp_eg.a("coll", a.f));
        }
        if (this.e != -1) {
            vector.addElement(rp_eg.a("dim", "" + this.e));
        }
        vector.addElement(rp_eg.a("layer", "" + this.a.a));
        if (a.a() == 2) {
            vector.addElement(rp_eg.a("username", this.a));
        }
        if (a.a() == 1) {
            vector.addElement(rp_eg.a("brand", ((rp_eJ)a).b));
        }
        rp_eg.a("item", vector);
        vector.removeAllElements();
        vector.addElement(rp_eg.a("x", Integer.toString(this.a)));
        vector.addElement(rp_eg.a("y", Integer.toString(this.b)));
        vector.addElement(rp_eg.a("angle", Integer.toString((int)(1000.0 * this.a))));
        rp_eg.a("position", vector, "");
        vector.removeAllElements();
        vector.addElement(rp_eg.a("x", Integer.toString(this.a.a())));
        vector.addElement(rp_eg.a("y", Integer.toString(this.a.b())));
        final int c;
        if ((c = this.a.c()) > 0) {
            vector.addElement(rp_eg.a("z", Integer.toString(c)));
        }
        rp_eg.a("size", vector, "");
        if (this.a() != null && this.b() != null) {
            rp_eg.a("appearance", (Vector)null);
            rp_eg.a("border-color", this.a());
            rp_eg.a("fill-color", this.b());
            rp_eg.a();
        }
        if (this.b != null) {
            rp_eg.a("description", null, this.b);
        }
        rp_eg.a();
    }
    
    public static rp_cv a(rp_eA string, final rp_fK rp_fK, final rp_b rp_b) {
        final int a = string.a("id", -1);
        final String a2 = string.a("SKU", (String)null);
        final String a3 = string.a("template", (String)null);
        final String a4;
        final boolean b = (a4 = string.a("custom", (String)null)) != null && a4.equals("true");
        String s;
        if ((s = string.a("cat", (String)null)) == null) {
            s = string.a("CatNum", "");
        }
        if (s != null && s.length() == 0) {
            s = "generic";
        }
        String f;
        if ((f = string.a("coll", (String)null)) == null) {
            f = string.a("collection", (String)null);
        }
        String b2;
        if ((b2 = string.a("brand", "")).length() == 0) {
            b2 = string.a("mfg", "");
        }
        if (b2.length() == 0) {
            b2 = string.a("manufacturer", "");
        }
        String a5 = string.a("username", (String)null);
        final int a6 = string.a("discontinued", 0);
        final int a7 = string.a("dim", -1);
        if (a5 == null || a5.length() == 0) {
            a5 = a2;
        }
        final int a8 = string.a("layer", -1);
        String b3 = null;
        int a9 = 0;
        int a10 = 0;
        double n = 0.0;
        Dimension dimension = null;
        int a11 = 0;
        Color a12 = null;
        Color b4 = null;
        final Enumeration<rp_eA> elements = (Enumeration<rp_eA>)string.a.elements();
        while (elements.hasMoreElements()) {
            final rp_eA rp_eA;
            if ((rp_eA = elements.nextElement()).a.equals("position")) {
                a9 = rp_eA.a("x", 0);
                a10 = rp_eA.a("y", 0);
                n = rp_eA.a("angle", 0.0) / 1000.0;
            }
            else if (rp_eA.a.equals("size")) {
                final int a13 = rp_eA.a("x", 0);
                final int a14 = rp_eA.a("y", 0);
                a11 = rp_eA.a("z", 0);
                if (a13 <= 0 || a14 <= 0) {
                    continue;
                }
                dimension = new Dimension(a13, a14);
            }
            else if (rp_eA.a.equals("appearance")) {
                final Color[] a15;
                a12 = (a15 = rp_C.a(rp_eA))[0];
                b4 = a15[1];
            }
            else {
                if (!rp_eA.a.equals("description")) {
                    continue;
                }
                b3 = rp_eA.b;
            }
        }
        rp_fn rp_fn = null;
        if (a3 != null) {
            final int n2 = (s != null && !s.equals("generic")) ? 1 : 2;
            if (dimension == null) {
                dimension = new Dimension(50000, 50000);
                rp_C.a(1, "missing <size> element for item " + a2);
            }
            rp_aV a16 = null;
            if (rp_fK != null) {
                a16 = rp_fK.a(a3);
            }
            (rp_fn = new rp_fn(n2, a3, a16, dimension.width, dimension.height, a11)).a.d = a2;
        }
        if (rp_fn == null) {
            final StringBuffer sb;
            (sb = new StringBuffer(s)).append('/');
            sb.append(rp_aJ.f);
            if (f != null && f.length() > 0) {
                sb.append(f.toLowerCase().replace(' ', '_').replace(',', '_').replace('&', '_').replace('\'', '_'));
                sb.append('/');
            }
            sb.append(a2.toLowerCase());
            sb.append(".sep");
            string = (rp_eA)sb.toString();
            if (rp_fn == null) {
                try {
                    rp_fn = new rp_fn((String)string, rp_fK);
                }
                catch (Exception ex) {
                    rp_C.a(3, "Unable to load sep file. Path: " + (String)string);
                    rp_C.a(3, "Brand: " + b2);
                    final int n3 = s.equalsIgnoreCase("generic") ? 2 : 1;
                    final rp_fT a17;
                    (a17 = (rp_fn = new rp_fn(n3)).a).d = a2;
                    if (n3 == 1) {
                        ((rp_eJ)a17).a = s;
                    }
                    if (dimension == null) {
                        dimension = new Dimension(50000, 50000);
                    }
                    rp_fn.a = dimension;
                    rp_fn.b = dimension;
                    rp_fn.b = -dimension.width / 2;
                    rp_fn.c = -dimension.height / 2;
                    dimension = null;
                }
            }
            else {
                rp_fn = (rp_fn)rp_fn.clone();
            }
        }
        if (a9 < 0 || a10 < 0) {
            System.out.println("!!! loading out of paper");
            a10 = (a9 = 4000000);
        }
        final rp_fT a18;
        (a18 = rp_fn.a).f = f;
        if (a18.a() == 1) {
            final rp_eJ rp_eJ;
            (rp_eJ = (rp_eJ)rp_fn.a).b = b2;
            if (s != null) {
                rp_eJ.a = s;
            }
        }
        final rp_cv rp_cv = new rp_cv(a, a9, a10, 0.0, rp_fn, rp_fK);
        if (b) {
            rp_cv.a(128, true);
        }
        rp_cv.a = a12;
        rp_cv.b = b4;
        rp_cv.e = a7;
        rp_cv.a(n);
        if (a6 == 1) {
            rp_fn.b = true;
        }
        if (b3 != null) {
            rp_cv.b = b3;
        }
        if (dimension != null) {
            rp_cv.a(dimension.width, dimension.height);
        }
        if (a18.a() == 2) {
            if (a5 != null) {
                rp_cv.a = a5;
            }
            if (a8 != -1) {
                rp_cv.a(a8);
            }
        }
        if (rp_fn != null && rp_fn.a == null && rp_b != null && rp_fK.a() != null) {
            rp_b.a(a3, rp_fn, rp_fK.a().a, rp_cv, rp_cv);
        }
        return rp_cv;
    }
    
    public final String a() {
        if (this.b != null) {
            return this.b;
        }
        final rp_fT a;
        if ((a = this.a.a()) != null) {
            final rp_fT rp_fT = a;
            final StringBuffer sb = new StringBuffer("");
            if (rp_fT.a != null) {
                for (int i = 0; i < rp_fT.a.size(); ++i) {
                    sb.append((String)rp_fT.a.elementAt(i));
                }
            }
            return sb.toString();
        }
        return null;
    }
    
    private void a(final Vector vector) {
        if (this.b != null) {
            final BufferedReader bufferedReader = new BufferedReader(new StringReader(this.b));
            try {
                String line;
                while (null != (line = bufferedReader.readLine())) {
                    vector.addElement(line);
                }
                return;
            }
            catch (IOException ex) {
                return;
            }
        }
        final rp_fT a;
        if ((a = this.a.a()) != null && a.a != null) {
            final Vector a2 = a.a;
            for (int i = 0; i < a2.size(); ++i) {
                vector.addElement(a2.elementAt(i));
            }
        }
    }
    
    public final void a(final String b) {
        this.b = b;
    }
    
    public final String b() {
        return this.a;
    }
    
    public final void b(final String a) {
        this.a = a;
    }
}
