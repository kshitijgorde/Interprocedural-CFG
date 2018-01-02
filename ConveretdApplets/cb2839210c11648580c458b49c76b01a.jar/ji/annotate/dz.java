// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.image.du;
import java.awt.Toolkit;
import ji.awt.bb;
import ji.awt.ax;
import ji.res.ay;
import ji.image.ds;
import ji.image.ev;
import ji.filter.ew;
import ji.io.ac;
import ji.v1event.ao;
import java.net.URL;
import ji.image.dw;
import ji.awt.d5;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import ji.awt.dc;
import java.awt.Dimension;
import ji.io.h;
import java.awt.Color;
import java.awt.Point;
import ji.awt.c;
import ji.net.a0;
import ji.document.ad;
import java.awt.Graphics;
import ji.render.dt;
import ji.image.dx;
import ji.util.d;
import ji.util.e;
import ji.util.i;
import ji.v1event.af;
import java.awt.Component;
import ji.awt.d4;
import java.awt.Font;
import ji.awt.da;
import ji.image.cy;
import java.awt.Rectangle;

public class dz
{
    private Rectangle a;
    private String b;
    private boolean c;
    private cy d;
    private boolean e;
    private boolean f;
    private u6 g;
    private Rectangle h;
    private boolean i;
    private cy j;
    private Thread k;
    private da l;
    private boolean m;
    private Rectangle n;
    private Rectangle o;
    private boolean p;
    private boolean q;
    private Font r;
    private boolean s;
    private boolean t;
    private int u;
    private boolean v;
    private boolean w;
    private d4 x;
    private boolean y;
    private String z;
    private boolean aa;
    
    public dz(final String z, final String b, final boolean c, final cy d, final boolean aa) {
        this.a = null;
        this.e = true;
        this.f = false;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = false;
        this.r = null;
        this.s = true;
        this.t = false;
        this.u = 0;
        this.v = false;
        this.w = false;
        this.x = null;
        this.y = false;
        this.z = null;
        this.aa = false;
        this.b = b;
        this.c = c;
        this.d = d;
        this.z = z;
        this.aa = aa;
    }
    
    public void a(final dg dg, final int n, final int n2, final int n3, final int n4) {
        dg.a(false);
        if (dg.at()) {
            final da aj = dg.aj();
            final da da = new da(aj);
            switch (n) {
                case 0: {
                    switch (n4) {
                        case 0: {
                            final da da2 = aj;
                            da2.a += n2;
                            final da da3 = aj;
                            da3.b += n3;
                            final da da4 = aj;
                            da4.c -= n2;
                            final da da5 = aj;
                            da5.d -= n3;
                            break;
                        }
                        case 90: {
                            final da da6 = aj;
                            da6.a += n2;
                            final da da7 = aj;
                            da7.c -= n2;
                            final da da8 = aj;
                            da8.d += n3;
                            break;
                        }
                        case 180: {
                            final da da9 = aj;
                            da9.c += n2;
                            final da da10 = aj;
                            da10.d += n3;
                            break;
                        }
                        case 270: {
                            final da da11 = aj;
                            da11.b += n3;
                            final da da12 = aj;
                            da12.c += n2;
                            final da da13 = aj;
                            da13.d -= n3;
                            break;
                        }
                    }
                    dg.ac(true);
                    break;
                }
                case 1: {
                    switch (n4) {
                        case 0: {
                            final da da14 = aj;
                            da14.c += n2;
                            final da da15 = aj;
                            da15.b += n3;
                            final da da16 = aj;
                            da16.d -= n3;
                            break;
                        }
                        case 90: {
                            final da da17 = aj;
                            da17.a += n2;
                            final da da18 = aj;
                            da18.b += n3;
                            final da da19 = aj;
                            da19.c -= n2;
                            final da da20 = aj;
                            da20.d -= n3;
                            break;
                        }
                        case 180: {
                            final da da21 = aj;
                            da21.c -= n2;
                            final da da22 = aj;
                            da22.a += n2;
                            final da da23 = aj;
                            da23.d += n3;
                            break;
                        }
                        case 270: {
                            final da da24 = aj;
                            da24.c += n2;
                            final da da25 = aj;
                            da25.d += n3;
                            break;
                        }
                    }
                    dg.ac(true);
                    break;
                }
                case 2: {
                    switch (n4) {
                        case 0: {
                            final da da26 = aj;
                            da26.c += n2;
                            final da da27 = aj;
                            da27.d += n3;
                            break;
                        }
                        case 90: {
                            final da da28 = aj;
                            da28.b += n3;
                            final da da29 = aj;
                            da29.c += n2;
                            final da da30 = aj;
                            da30.d -= n3;
                            break;
                        }
                        case 180: {
                            final da da31 = aj;
                            da31.a += n2;
                            final da da32 = aj;
                            da32.b += n3;
                            final da da33 = aj;
                            da33.c -= n2;
                            final da da34 = aj;
                            da34.d -= n3;
                            break;
                        }
                        case 270: {
                            final da da35 = aj;
                            da35.a += n2;
                            final da da36 = aj;
                            da36.c -= n2;
                            final da da37 = aj;
                            da37.d += n3;
                            break;
                        }
                    }
                    dg.ac(true);
                    break;
                }
                case 3: {
                    switch (n4) {
                        case 0: {
                            final da da38 = aj;
                            da38.a += n2;
                            final da da39 = aj;
                            da39.c -= n2;
                            final da da40 = aj;
                            da40.d += n3;
                            break;
                        }
                        case 90: {
                            final da da41 = aj;
                            da41.c += n2;
                            final da da42 = aj;
                            da42.d += n3;
                            break;
                        }
                        case 180: {
                            final da da43 = aj;
                            da43.b += n3;
                            final da da44 = aj;
                            da44.c += n2;
                            final da da45 = aj;
                            da45.d -= n3;
                            break;
                        }
                        case 270: {
                            final da da46 = aj;
                            da46.a += n2;
                            final da da47 = aj;
                            da47.b += n3;
                            final da da48 = aj;
                            da48.c -= n2;
                            final da da49 = aj;
                            da49.d -= n3;
                            break;
                        }
                    }
                    dg.ac(true);
                    break;
                }
            }
            try {
                if ((aj.a <= this.d.r().q - 2 - (int)(dg.c(this.d, dg.cu(), this.d).a(this.d, "W", false, null) * dg.c6()) || !ji.util.i.c(125)) && aj.c >= ji.util.e.bb && aj.d >= ji.util.e.bb && n != 999) {
                    dg.d(aj);
                    dg.b(-1, -1);
                    dg.e((d4)null);
                    dg.w(false);
                    dg.c7();
                    if (aj.b > da.b || aj.a > da.a) {
                        dg.ab(true);
                        dg.ad();
                    }
                    if (aj.c < da.c || aj.d < da.d) {
                        dg.q(true);
                    }
                }
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public final void a(final dx dx, final dt dt, final Graphics graphics, final dg dg, final Component component, final ad ad, final af af, final a0 a0, final int n, final dy dy, final String s, final da da, final boolean b, final boolean b2, final int n2, final double n3, final double n4, final int n5, final long n6, final int n7, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final boolean b7, final double n8, final boolean b8, final c c, final int n9) throws Exception {
        try {
            if (dg.bl()) {
                return;
            }
            if (b4 && !b3) {
                dg.aj(false);
            }
            if (dg.c(this.b, this.c)) {
                if (b5) {
                    return;
                }
            }
            else if (b5 && (b6 || dg.gl())) {
                return;
            }
            int n10 = 0;
            int n11 = 0;
            boolean b9 = b7;
            if ((n2 & 0x1) > 0) {
                n10 = 1;
            }
            if ((n2 & 0x2) > 0) {
                n11 = 1;
            }
            if ((dg.ca() & 0x1) > 0 && n10 != 0) {
                n10 = 0;
            }
            else if ((dg.ca() & 0x1) > 0 && n10 == 0) {
                n10 = 1;
            }
            if ((dg.ca() & 0x2) > 0 && n11 != 0) {
                n11 = 0;
            }
            else if ((dg.ca() & 0x2) > 0 && n11 == 0) {
                n11 = 1;
            }
            if (dg.cd() && b9) {
                b9 = false;
            }
            else if (dg.cd() && !b9) {
                b9 = true;
            }
            int n12 = 0;
            if (dg.b3() == 90 || dg.b3() == 270) {
                if (n10 != 0) {
                    n12 |= 0x2;
                }
                if (n11 != 0) {
                    n12 |= 0x1;
                }
            }
            else {
                if (n10 != 0) {
                    n12 |= 0x1;
                }
                if (n11 != 0) {
                    n12 |= 0x2;
                }
            }
            final boolean b10 = (n12 & 0x1) > 0;
            final boolean b11 = (n12 & 0x2) > 0;
            Image image = null;
            final boolean b12 = graphics != null && b;
            boolean b13 = false;
            boolean b14 = false;
            if (b2 && ji.util.i.c(99)) {
                final da a2 = dg.a();
                final da fr = dg.fr();
                if (a2 != null && fr != null) {
                    da b15 = null;
                    if (fr.c > 0 && fr.d > 0) {
                        b13 = true;
                        b14 = true;
                        try {
                            if (dt != null) {
                                final d5 a3 = dy.a(dg.bv(), graphics, dt);
                                final Point point = new Point((int)a3.a, (int)a3.b);
                                final dc fs = dg.fs();
                                final int m = dg.m(dg.b3() + n9);
                                final Point point2 = new Point();
                                switch (m) {
                                    case 0: {
                                        point2.x = point.x;
                                        point2.y = point.y;
                                        if ((n12 & 0x1) > 0) {
                                            final Point point3 = point2;
                                            point3.x -= (int)fs.a;
                                        }
                                        if ((n12 & 0x2) > 0) {
                                            final Point point4 = point2;
                                            point4.y -= (int)fs.b;
                                            break;
                                        }
                                        break;
                                    }
                                    case 90: {
                                        point2.x = point.x - (int)fs.a;
                                        point2.y = point.y;
                                        if ((n12 & 0x2) > 0) {
                                            final Point point5 = point2;
                                            point5.x += (int)fs.a;
                                        }
                                        if ((n12 & 0x1) > 0) {
                                            final Point point6 = point2;
                                            point6.y -= (int)fs.b;
                                            break;
                                        }
                                        break;
                                    }
                                    case 180: {
                                        point2.x = point.x - (int)fs.a;
                                        point2.y = point.y - (int)fs.b;
                                        if ((n12 & 0x1) > 0) {
                                            final Point point7 = point2;
                                            point7.x += (int)fs.a;
                                        }
                                        if ((n12 & 0x2) > 0) {
                                            final Point point8 = point2;
                                            point8.y += (int)fs.b;
                                            break;
                                        }
                                        break;
                                    }
                                    case 270: {
                                        point2.x = point.x;
                                        point2.y = point.y - (int)fs.b;
                                        if ((n12 & 0x2) > 0) {
                                            final Point point9 = point2;
                                            point9.x -= (int)fs.a;
                                        }
                                        if ((n12 & 0x1) > 0) {
                                            final Point point10 = point2;
                                            point10.y += (int)fs.b;
                                            break;
                                        }
                                        break;
                                    }
                                }
                                a2.a = point2.x;
                                a2.b = point2.y;
                                if (a2.c > 0 && a2.d > 0) {
                                    b15 = new da(Math.abs(dt.i()), Math.abs(dt.j()), dt.g(), dt.h()).b(fr);
                                }
                            }
                        }
                        catch (Exception ex3) {}
                    }
                    if (b15 != null && (b15.c <= 0 || b15.d <= 0)) {
                        b13 = false;
                    }
                }
            }
            dg.a((da)null);
            final d4 d4 = new d4();
            final Rectangle rectangle = new Rectangle();
            final d5 a4 = dy.a(dg.bv(), graphics, dt);
            final d5 a5 = dy.a(dg.bv(), graphics, null);
            final Point point11 = new Point((int)a4.a, (int)a4.b);
            final boolean o9 = ad.o9;
            final boolean a6 = dg.a(dg, o9);
            final Color c2 = dg.c(dg, o9);
            final Color b16 = dg.b(dg, o9);
            try {
                if (!a6 && c2 == null) {
                    dg.d(Color.white);
                }
                if (dg.cm()) {
                    return;
                }
                double n13 = n3;
                if (b3) {
                    n13 *= dg.ec();
                }
                if (dg.e9()) {
                    if (b2 && n8 < 1.0) {
                        n13 = n8;
                    }
                    boolean b17 = ji.util.d.by(dg.ev()) || (dg.dy() && dg.e9());
                    if (!b17 && dg.e9() && dg.at() && dg.d3()) {
                        b17 = true;
                    }
                    image = dg.a(c, n, ad, dx, dg.b3() + n9, n12, b9, n13, af, n13, b17, dg.cu(), b2, b8, dy.a(dt), dx.am > 1, ad.iv(), ad.hw());
                    if (image == null && b12 && b17) {
                        dg.aq(true);
                    }
                    if (ji.util.d.dv() || ji.util.d.dr()) {
                        ji.io.h.e(s, String.valueOf(String.valueOf(new StringBuffer("Created font annotation for text:").append(dg.ev()).append("."))));
                    }
                }
                else {
                    boolean b18 = false;
                    this.e = true;
                    final boolean b19 = dg.b6() && this.e;
                    double n14 = dg.cb() * n13;
                    if (!b19) {
                        n14 *= dg.b8();
                        if (dg.b9() != n14) {
                            dg.cy();
                            b18 = true;
                        }
                    }
                    if (dg.b(ad.el(), ad.ek())) {
                        dg.cy();
                        b18 = true;
                    }
                    else if (dg.cc() == n14 || !ji.util.i.c(113)) {
                        dg.cy();
                        b18 = true;
                    }
                    dg.a(n14);
                    if (dg.cj() != n9) {
                        dg.cy();
                        b18 = true;
                    }
                    if (dg.b6() && !this.e) {
                        b18 = true;
                    }
                    if (b18 || !dg.a(n9, n12, b9)) {
                        if (ji.util.d.dv() || ji.util.d.dr()) {
                            ji.io.h.e(s, String.valueOf(String.valueOf(new StringBuffer("Loading image for annotation:").append(dg.fa()).append("..."))));
                        }
                        dg.cy();
                        final Dimension dimension = new Dimension(0, 0);
                        final Dimension dimension2 = new Dimension();
                        boolean b20 = b9;
                        if (dg.gi()) {
                            b20 = false;
                        }
                        image = this.a(dx, component, ad, dg.fa(), dg, a0, dg.m(dg.b3() + n9), n12, b20, af, dimension, dimension2, n14, s, n7, b3, ad, b2);
                        if (image == null && b12) {
                            dg.aq(true);
                        }
                        if (!dg.cm()) {
                            if (dg.f9() && dimension2 != null) {
                                final int gb = dg.gb();
                                final int ga = dg.ga();
                                if (ji.util.i.c(5)) {
                                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Annotation image stamp: resetting image dimensions to: ").append(gb).append(" * ").append(ga))));
                                }
                                double n15;
                                if (gb > ga) {
                                    n15 = gb / dimension2.width;
                                }
                                else {
                                    n15 = ga / dimension2.height;
                                }
                                dg.b(n15);
                                n14 *= n15;
                                dg.af(0);
                                dg.ag(0);
                            }
                            dg.a(!this.t, n14, ad.el(), ad.ek());
                            dg.a(dimension2);
                            dg.e(dg.fb());
                            Dimension cw = null;
                            if (!this.t) {
                                cw = dg.cw();
                                dg.cx();
                            }
                            if (!dg.cl()) {
                                dg.a(image, cw, dimension.width, dimension.height, dimension.width, dimension.height, n9, n12, b20, this.u);
                            }
                            if (cw != null) {
                                dg.cj();
                            }
                        }
                        if (ji.util.d.dv() || ji.util.d.dr()) {
                            ji.io.h.e(s, String.valueOf(String.valueOf(new StringBuffer("Loaded image for annotation:").append(dg.fa()).append("."))));
                        }
                    }
                    if (dg.cm()) {
                        return;
                    }
                    if (!dg.b6()) {
                        n14 = 1.0;
                    }
                    image = dg.a(c, n, ad, dx, dg.b3() + n9, n12, b9, n14, af, n13, dg.dy(), dg.cu(), b2, b8, dy.a(dt), dx.am > 1, ad.iv(), ad.hw());
                }
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
            final dc fs2 = dg.fs();
            final int i = dg.m(dg.b3() + n9);
            dc bx = new dc(fs2);
            final dc dc = new dc(bx);
            if (dg.at()) {
                bx = dg.bx();
            }
            switch (i) {
                case 0: {
                    rectangle.x = point11.x;
                    rectangle.y = point11.y;
                    rectangle.width = (int)fs2.a;
                    rectangle.height = (int)fs2.b;
                    if ((n12 & 0x1) > 0) {
                        final Rectangle rectangle2 = rectangle;
                        rectangle2.x -= (int)dc.a;
                        final d5 d5 = a5;
                        d5.a -= (int)dc.a;
                    }
                    if ((n12 & 0x2) > 0) {
                        final Rectangle rectangle3 = rectangle;
                        rectangle3.y -= (int)bx.b;
                        final d5 d6 = a5;
                        d6.b -= (int)bx.b;
                        break;
                    }
                    break;
                }
                case 90: {
                    rectangle.x = point11.x - (int)fs2.a;
                    rectangle.y = point11.y;
                    a5.a -= fs2.a;
                    rectangle.width = (int)fs2.a;
                    rectangle.height = (int)fs2.b;
                    if ((n12 & 0x2) > 0) {
                        final Rectangle rectangle4 = rectangle;
                        rectangle4.x += (int)bx.a;
                        final d5 d7 = a5;
                        d7.a += (int)bx.a;
                    }
                    if ((n12 & 0x1) > 0) {
                        final Rectangle rectangle5 = rectangle;
                        rectangle5.y -= (int)dc.b;
                        final d5 d8 = a5;
                        d8.b -= (int)dc.b;
                        break;
                    }
                    break;
                }
                case 180: {
                    rectangle.x = point11.x - (int)fs2.a;
                    rectangle.y = point11.y - (int)fs2.b;
                    a5.a -= fs2.a;
                    a5.b -= fs2.b;
                    rectangle.width = (int)fs2.a;
                    rectangle.height = (int)fs2.b;
                    if ((n12 & 0x1) > 0) {
                        final Rectangle rectangle6 = rectangle;
                        rectangle6.x += (int)dc.a;
                        final d5 d9 = a5;
                        d9.a += (int)dc.a;
                    }
                    if ((n12 & 0x2) > 0) {
                        final Rectangle rectangle7 = rectangle;
                        rectangle7.y += (int)bx.b;
                        final d5 d10 = a5;
                        d10.b += (int)bx.b;
                        break;
                    }
                    break;
                }
                case 270: {
                    rectangle.x = point11.x;
                    rectangle.y = point11.y - (int)fs2.b;
                    rectangle.width = (int)fs2.a;
                    a5.b -= fs2.b;
                    rectangle.height = (int)fs2.b;
                    if ((n12 & 0x2) > 0) {
                        final Rectangle rectangle8 = rectangle;
                        rectangle8.x -= (int)bx.a;
                        final d5 d11 = a5;
                        d11.a -= (int)bx.a;
                    }
                    if ((n12 & 0x1) > 0) {
                        final Rectangle rectangle9 = rectangle;
                        rectangle9.y += (int)dc.b;
                        final d5 d12 = a5;
                        d12.b += (int)dc.b;
                        break;
                    }
                    break;
                }
            }
            d4.addPoint(rectangle.x, rectangle.y);
            d4.addPoint(rectangle.x + rectangle.width, rectangle.y);
            d4.addPoint(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
            d4.addPoint(rectangle.x, rectangle.y + rectangle.height);
            final Point point12 = new Point(rectangle.x, rectangle.y);
            boolean b21 = true;
            int n16 = 0;
            boolean b22 = false;
            boolean b23 = false;
            Rectangle rectangle10 = null;
            Color color = null;
            Rectangle rectangle11 = null;
            Rectangle rectangle12 = null;
            Rectangle rectangle13 = null;
            Rectangle rectangle14 = null;
            if (!b14) {
                da da2 = null;
                try {
                    if (dt != null) {
                        if (rectangle.width > 0 && rectangle.height > 0) {
                            final dc fs3 = dg.fs();
                            final da da3 = new da(0L, 0L, dt.g(), dt.h());
                            final da da4 = new da(point12.x, point12.y, fs3.a, fs3.b);
                            dg.a(da4);
                            da2 = da3.b(da4);
                        }
                    }
                    else if (rectangle.width > 0 && rectangle.height > 0) {
                        dg.a(new da(rectangle));
                        da2 = da.b(new da(rectangle));
                    }
                }
                catch (Exception ex4) {}
                if (da2 != null && (da2.c <= 0 || da2.d <= 0)) {
                    b21 = false;
                }
            }
            else {
                b21 = b13;
            }
            boolean b24 = true;
            if (dg.b4() != 0 || n9 != 0) {
                b24 = false;
            }
            try {
                boolean e8 = true;
                if (dg.e9()) {
                    e8 = dg.e8();
                }
                if (b21) {
                    ji.util.d.am(true);
                    da da5 = null;
                    Label_11774: {
                        if (graphics != null) {
                            if (!b) {
                                break Label_11774;
                            }
                            if (!b3) {
                                dg.aj(true);
                            }
                            if (dg.an()) {
                                dg.al();
                                Point point13 = null;
                                Point point14 = null;
                                Point point15 = null;
                                switch (n9) {
                                    case 0: {
                                        point13 = this.d.e(rectangle.x, rectangle.y);
                                        point14 = this.d.e(rectangle.x + rectangle.width, rectangle.y);
                                        point15 = this.d.e(rectangle.x, rectangle.y + rectangle.height);
                                        break;
                                    }
                                    case 90: {
                                        point13 = this.d.e(rectangle.x + rectangle.width, rectangle.y);
                                        point14 = this.d.e(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
                                        point15 = this.d.e(rectangle.x, rectangle.y + rectangle.height);
                                        break;
                                    }
                                    case 180: {
                                        point13 = this.d.e(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
                                        point14 = this.d.e(rectangle.x, rectangle.y + rectangle.height);
                                        point15 = this.d.e(rectangle.x, rectangle.y);
                                        break;
                                    }
                                    case 270: {
                                        point13 = this.d.e(rectangle.x, rectangle.y + rectangle.height);
                                        point14 = this.d.e(rectangle.x, rectangle.y);
                                        point15 = this.d.e(rectangle.x + rectangle.width, rectangle.y);
                                        break;
                                    }
                                }
                                dg.a(point14.x - point13.x, point15.y - point14.y);
                                dy.a(component, af, dg, dx, ad);
                            }
                            if (dg.at()) {
                                da5 = dy.a(dg.by(), graphics, dt);
                                switch (n9) {
                                    case 0: {
                                        dg.k(new da(rectangle.x, rectangle.y, (int)da5.c, (int)da5.d));
                                        break;
                                    }
                                    case 90: {
                                        dg.k(new da(rectangle.x - (da5.c - rectangle.width), rectangle.y, (int)da5.c, (int)da5.d));
                                        break;
                                    }
                                    case 180: {
                                        dg.k(new da(rectangle.x - (da5.c - rectangle.width), rectangle.y - (da5.d - rectangle.height), (int)da5.c, (int)da5.d));
                                        break;
                                    }
                                    case 270: {
                                        dg.k(new da(rectangle.x, rectangle.y - (da5.d - rectangle.height), (int)da5.c, (int)da5.d));
                                        break;
                                    }
                                }
                            }
                            else {
                                dg.k(new da(rectangle));
                            }
                            Rectangle c3 = dg.c(da);
                            if (!ji.util.d.ls || !b24) {
                                c3 = null;
                            }
                            boolean b25 = false;
                            final boolean w = dg.w();
                            Rectangle rectangle15 = null;
                            if (this.a != null) {
                                rectangle15 = new Rectangle(this.a);
                            }
                            if (dg.at() && da5 != null) {
                                this.a = new Rectangle((int)da5.a, (int)da5.b, (int)da5.c, (int)da5.d);
                            }
                            else {
                                this.a = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                            }
                            if (rectangle15 != null && (rectangle15.x != this.a.x || rectangle15.y != this.a.y || rectangle15.width != this.a.width || rectangle15.height != this.a.height)) {
                                b25 = true;
                            }
                            boolean b26 = true;
                            final boolean b27 = true;
                            if (rectangle15 != null && (c3 != null || !a6)) {
                                b26 = false;
                                if (b25 || c3 != null) {
                                    b26 = true;
                                }
                            }
                            if (this.d != null && (b26 || c3 != null)) {
                                final Graphics b28 = this.d.b8();
                                final Rectangle clipBounds = b28.getClipBounds();
                                final boolean fv = ji.util.d.fv;
                                try {
                                    ji.util.d.fv = false;
                                    if (c3 != null && b27) {
                                        if (b26 && rectangle15 != null && this.a != null) {
                                            final Rectangle rectangle16 = new Rectangle(this.a);
                                            final Rectangle rectangle17 = new Rectangle(rectangle15);
                                            if (rectangle15.width > this.a.width) {
                                                if (rectangle15.x < this.a.x) {
                                                    this.d.hp.a(b28, dg, null, 0, 0, 0, new Rectangle(rectangle15.x, rectangle15.y, rectangle15.width - this.a.width, rectangle15.height));
                                                }
                                                if (rectangle15.x + rectangle15.width > this.a.x + this.a.width) {
                                                    this.d.hp.a(b28, dg, null, 0, 0, 0, new Rectangle(this.a.x + this.a.width, rectangle15.y, rectangle15.width - this.a.width, rectangle15.height));
                                                }
                                            }
                                            if (rectangle15.height > this.a.height) {
                                                if (rectangle15.y < this.a.y) {
                                                    this.d.hp.a(graphics, dg, null, 0, 0, 0, new Rectangle(rectangle15.x, rectangle15.y, rectangle15.width, rectangle15.height - this.a.height));
                                                }
                                                if (rectangle15.y + rectangle15.height > this.a.y + this.a.height) {
                                                    this.d.hp.a(graphics, dg, null, 0, 0, 0, new Rectangle(rectangle15.x, this.a.y + this.a.height, rectangle15.width, rectangle15.height - this.a.height));
                                                }
                                            }
                                            if (b25) {
                                                dg.b(System.currentTimeMillis());
                                            }
                                            if (ji.util.d.ls && b24 && b24 && b25) {
                                                dg.l(false);
                                            }
                                            if (!dg.a4()) {
                                                if (this.h != null && !this.i) {
                                                    this.b(true);
                                                    this.i = true;
                                                    if (dg.a(dg, o9)) {
                                                        this.d.hp.a(b28, dg, null, 0, 0, 0, this.h);
                                                    }
                                                    else if (this.a.inside(this.h.x, this.h.y)) {
                                                        Color j = c2;
                                                        if (b7) {
                                                            j = ji.util.d.i(j);
                                                        }
                                                        Rectangle rectangle18 = new Rectangle(rectangle17);
                                                        final Rectangle clipBounds2 = graphics.getClipBounds();
                                                        if (clipBounds2 != null) {
                                                            final Rectangle rectangle19 = clipBounds2;
                                                            final int height = clipBounds2.height;
                                                            rectangle19.y = height;
                                                            final int n17 = height;
                                                            final Rectangle rectangle20 = rectangle18;
                                                            final int height2 = rectangle18.height;
                                                            rectangle20.y = height2;
                                                            final int n18 = height2;
                                                            if (n18 < n17) {
                                                                final Rectangle rectangle21;
                                                                rectangle18 = (rectangle21 = clipBounds2);
                                                                rectangle21.height -= n17 - n18;
                                                            }
                                                        }
                                                        graphics.setClip(rectangle18.x, rectangle18.y, rectangle18.width, rectangle18.height);
                                                        graphics.setColor(j);
                                                        graphics.fillRect(this.h.x, this.h.y, this.h.width, this.h.height);
                                                        if (rectangle18 != null && clipBounds2 != null) {
                                                            graphics.setClip(clipBounds2.x, clipBounds2.y, clipBounds2.width, clipBounds2.height);
                                                        }
                                                        else {
                                                            graphics.setClip(null);
                                                        }
                                                    }
                                                    else {
                                                        this.d.hp.a(b28, dg, null, 0, 0, 0, this.h);
                                                    }
                                                }
                                                Rectangle rectangle22 = null;
                                                Rectangle rectangle23 = null;
                                                Rectangle rectangle24 = null;
                                                Rectangle rectangle25 = null;
                                                Rectangle rectangle26 = null;
                                                Rectangle rectangle27 = null;
                                                Rectangle rectangle28 = null;
                                                Rectangle rectangle29 = null;
                                                if (ji.util.d.ls && b24) {
                                                    final Rectangle rectangle30 = new Rectangle(rectangle17);
                                                    final int n19 = 5;
                                                    if (dg.at()) {
                                                        final int n20 = 1;
                                                        final int n21 = 11 + n20;
                                                        rectangle22 = new Rectangle(rectangle30.x + 6, rectangle30.y - n19 - n20, rectangle30.width - 10, 6 + n20);
                                                        rectangle23 = new Rectangle(rectangle30.x - 5 - n20, rectangle30.y - n19 - n20, n21, n21);
                                                        rectangle24 = new Rectangle(rectangle30.x + rectangle30.width - 4 - n20, rectangle30.y - n19 - n20, n21 + n20, n21 + n20);
                                                        rectangle28 = new Rectangle(rectangle30.x - n19 - n20, rectangle30.y + 6, 6 + 2 * n20, rectangle30.height - 10);
                                                        rectangle29 = new Rectangle(rectangle30.x + rectangle30.width - n20, rectangle30.y + 6, n19 + 2 + 2 * n20, rectangle30.height - 10);
                                                        rectangle25 = new Rectangle(rectangle30.x + 6, rectangle30.y + rectangle30.height, rectangle30.width - 10, n19 + 6);
                                                        rectangle26 = new Rectangle(rectangle30.x - 5 - n20, rectangle30.y + rectangle30.height - 4 - n20, n21 + n20, n21 + n20);
                                                        rectangle27 = new Rectangle(rectangle30.x + rectangle30.width - 4 - n20, rectangle30.y + rectangle30.height - 4 - n20, n21 + n20, n21 + n20);
                                                    }
                                                    else {
                                                        rectangle22 = new Rectangle(rectangle30.x, rectangle30.y - n19 + 1, rectangle30.width, 6);
                                                        rectangle28 = new Rectangle(rectangle30.x - n19 + 1, rectangle30.y, 6, rectangle30.height);
                                                        rectangle29 = new Rectangle(rectangle30.x + rectangle30.width - 1, rectangle30.y, n19 + 2, rectangle30.height);
                                                        rectangle25 = new Rectangle(rectangle30.x, rectangle30.y + rectangle30.height - 1, rectangle30.width, n19 + 6);
                                                    }
                                                }
                                                try {
                                                    if (b25 && ji.util.d.ls && b24 && (dg.w() || dg.x())) {
                                                        dg.l(true);
                                                        if (rectangle22 != null) {
                                                            this.d.hp.a(b28, dg, null, 0, 0, 0, rectangle22);
                                                        }
                                                        if (rectangle23 != null) {
                                                            this.d.hp.a(b28, dg, null, 0, 0, 0, rectangle23);
                                                        }
                                                        if (rectangle24 != null) {
                                                            this.d.hp.a(b28, dg, null, 0, 0, 0, rectangle24);
                                                        }
                                                        if (rectangle28 != null) {
                                                            this.d.hp.a(b28, dg, null, 0, 0, 0, rectangle28);
                                                        }
                                                        if (rectangle29 != null) {
                                                            this.d.hp.a(b28, dg, null, 0, 0, 0, rectangle29);
                                                        }
                                                        if (rectangle25 != null) {
                                                            this.d.hp.a(b28, dg, null, 0, 0, 0, rectangle25);
                                                        }
                                                        if (rectangle26 != null) {
                                                            this.d.hp.a(b28, dg, null, 0, 0, 0, rectangle26);
                                                        }
                                                        if (rectangle27 != null) {
                                                            this.d.hp.a(b28, dg, null, 0, 0, 0, rectangle27);
                                                        }
                                                        dg.m(false);
                                                        dg.n(false);
                                                    }
                                                }
                                                catch (Exception ex5) {}
                                                if (dg.c((da)null) != null) {
                                                    final Rectangle c4 = dg.c((da)null);
                                                    if (rectangle22 != null) {
                                                        if (rectangle22 != null && c4.intersects(rectangle22)) {
                                                            b22 = true;
                                                        }
                                                        if (rectangle23 != null && c4.intersects(rectangle23)) {
                                                            b22 = true;
                                                        }
                                                        if (rectangle24 != null && c4.intersects(rectangle24)) {
                                                            b22 = true;
                                                        }
                                                        if (rectangle28 != null && c4.intersects(rectangle28)) {
                                                            b22 = true;
                                                        }
                                                        if (rectangle29 != null && c4.intersects(rectangle29)) {
                                                            b22 = true;
                                                        }
                                                        if (rectangle25 != null && c4.intersects(rectangle25)) {
                                                            b22 = true;
                                                        }
                                                        if (rectangle26 != null && c4.intersects(rectangle26)) {
                                                            b22 = true;
                                                        }
                                                        if (rectangle27 != null && c4.intersects(rectangle27)) {
                                                            b22 = true;
                                                        }
                                                    }
                                                }
                                                if (ji.util.d.ls && b24 && a6 && dg.c((da)null) != null) {
                                                    dg.l(false);
                                                    this.d.hp.a(b28, dg, null, 0, 0, 0, dg.c(da));
                                                }
                                                if (!b25 && ji.util.d.ls && b24 && (dg.w() || dg.x())) {
                                                    dg.o(true);
                                                }
                                            }
                                        }
                                    }
                                    else if (c3 != null && !b27 && ji.util.d.ls && b24) {
                                        if (b25) {
                                            this.d.hp.a(b28, dg, null, 0, 0, 0, null);
                                        }
                                        else if (a6) {
                                            this.d.hp.a(b28, dg, null, 0, 0, 0, c3);
                                        }
                                    }
                                    else {
                                        this.d.hp.a(b28, dg, null, 0, 0, 0, null);
                                    }
                                }
                                finally {
                                    try {
                                        ji.util.d.fv = fv;
                                        if (clipBounds != null) {
                                            b28.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
                                        }
                                    }
                                    catch (Exception ex6) {}
                                }
                            }
                            this.l = new da(rectangle);
                            if (image != null) {
                                if (c3 != null) {
                                    if (b26) {
                                        if (b27) {
                                            graphics.setClip(c3.x, c3.y, c3.width, c3.height);
                                        }
                                        else if (!b25) {
                                            graphics.setClip(c3.x, c3.y, c3.width, c3.height);
                                        }
                                        else if (b25) {
                                            dy.e(true);
                                        }
                                    }
                                    else {
                                        graphics.setClip(c3.x, c3.y, c3.width, c3.height);
                                    }
                                }
                                Rectangle a7 = null;
                                if (dg.at()) {
                                    try {
                                        final da a8 = dy.a(dg.by(), graphics, dt);
                                        if (a8 != null) {
                                            a7 = a8.a();
                                        }
                                    }
                                    catch (Exception ex7) {}
                                }
                                if (dg.e9() && dg.at()) {
                                    if (!dg.d3() || dg.as()) {
                                        if (dg.ew() < 3) {
                                            graphics.setClip((int)da.a, (int)da.b, (int)da.c, (int)da.d);
                                        }
                                        if (dg.c((da)null) == null) {
                                            final Rectangle c5 = dg.c(da);
                                            if (c5 != null) {
                                                graphics.setClip(c5.x, c5.y, c5.width, c5.height);
                                            }
                                            else {
                                                graphics.setClip((int)da.a, (int)da.b, (int)da.c, (int)da.d);
                                            }
                                            if (a7 != null) {
                                                ji.util.d.a(graphics, a7);
                                            }
                                            graphics.drawImage(image, rectangle.x, rectangle.y, null);
                                        }
                                        else if (a6) {
                                            if (a7 != null) {
                                                ji.util.d.a(graphics, a7);
                                            }
                                            graphics.drawImage(image, rectangle.x, rectangle.y, null);
                                        }
                                        else {
                                            if (ji.util.d.ls && b24 && !a6) {
                                                if (b25) {
                                                    final Rectangle intersection = rectangle.intersection(da.a());
                                                    graphics.setClip(intersection.x, intersection.y, intersection.width, intersection.height);
                                                }
                                                b22 = true;
                                            }
                                            if (ji.util.i.c(247)) {
                                                ji.util.d.a(graphics, dg.c5(), 0);
                                            }
                                            if (a7 != null) {
                                                ji.util.d.a(graphics, a7);
                                            }
                                            if (da != null) {
                                                ji.util.d.a(graphics, da.a());
                                            }
                                            graphics.drawImage(image, rectangle.x, rectangle.y, null);
                                        }
                                    }
                                }
                                else {
                                    if (dg.ew() < 3) {
                                        graphics.setClip((int)da.a, (int)da.b, (int)da.c, (int)da.d);
                                    }
                                    if (ji.util.d.ls && b24 && !a6) {
                                        if (b25) {
                                            final Rectangle intersection2 = rectangle.intersection(da.a());
                                            graphics.setClip(intersection2.x, intersection2.y, intersection2.width, intersection2.height);
                                        }
                                        b22 = true;
                                    }
                                    if (a7 != null) {
                                        ji.util.d.a(graphics, a7);
                                    }
                                    graphics.drawImage(image, rectangle.x, rectangle.y, null);
                                }
                            }
                            if (c3 != null) {
                                graphics.setClip(null);
                                dg.b((Rectangle)null);
                            }
                            if (this.d == null || this.d.b8() != null) {}
                            final Point f = this.d.f(rectangle.x, rectangle.y);
                            final Point f2 = this.d.f(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
                            final Rectangle rectangle31 = new Rectangle(f.x, f.y, f2.x - f.x, f2.y - f.y);
                            rectangle31.width = rectangle31.width * 120 / 100;
                            rectangle31.height = rectangle31.height * 120 / 100;
                            dg.c(rectangle31);
                            final Color color2 = graphics.getColor();
                            try {
                                if (!dg.at() && ji.util.d.ls && b24) {
                                    final da fr2 = dg.fr();
                                    if (dg.d7() || a6) {
                                        boolean b29 = true;
                                        if (dg.d9() == null) {
                                            if (b7) {
                                                graphics.setColor(ji.util.d.i(Color.black));
                                            }
                                            else {
                                                graphics.setColor(Color.black);
                                            }
                                        }
                                        else if (b7) {
                                            graphics.setColor(ji.util.d.i(dg.d9()));
                                        }
                                        else {
                                            graphics.setColor(dg.d9());
                                        }
                                        final int n22 = (int)fr2.a;
                                        final int n23 = (int)fr2.a + (int)fr2.c;
                                        final int n24 = (int)fr2.b;
                                        final int n25 = (int)fr2.b + (int)fr2.d;
                                        if (ji.util.d.ls && b24) {
                                            final Rectangle c6 = dg.c(da);
                                            if (c6 != null) {
                                                graphics.setClip(c6);
                                            }
                                        }
                                        dg.a(graphics.getColor());
                                        dg.a(n22, n24, n23, n25);
                                        if (ji.util.d.ls && b24 && !dg.w() && dg.dy() && a6) {
                                            if (!dg.a4() && !dg.a5()) {
                                                b29 = false;
                                            }
                                            if (this.d.ci() > 0) {
                                                b29 = false;
                                            }
                                        }
                                        if (b29) {
                                            final int n26 = 0;
                                            final int n27 = 1;
                                            if (n16 != 0 && !this.d.en() && !b22) {
                                                rectangle10 = graphics.getClipBounds();
                                                color = graphics.getColor();
                                                rectangle11 = new Rectangle(n22 + n26 + n27, n24, n23 - n26 - 2 * n27, n24);
                                                rectangle12 = new Rectangle(n22 + n27, n24 + n26, n22 + n27, n25 - n26 - 2 * n27);
                                                rectangle13 = new Rectangle(n23 - n27, n24 + n26, n23 - n27, n25 - n26 - n27);
                                                rectangle14 = new Rectangle(n22 + n26 + n27, n25 - n27, n23 - n26 - 2 * n27, n25 - n27);
                                            }
                                            else {
                                                graphics.drawLine(n22 + n26 + n27, n24, n23 - n26 - 2 * n27, n24);
                                                graphics.drawLine(n22 + n27, n24 + n26 + n27, n22 + n27, n25 - n26 - 2 * n27);
                                                graphics.drawLine(n23 - n27, n24 + n26, n23 - n27, n25 - n26 - n27);
                                                graphics.drawLine(n22 + n26 + n27, n25 - n27, n23 - n26 - 2 * n27, n25 - n27);
                                                b23 = true;
                                                dg.n(true);
                                            }
                                        }
                                    }
                                }
                                else if (dg.at() && da5 != null) {
                                    if (da5 != null && !a6) {
                                        if (dg.d3()) {
                                            rectangle.width = 0;
                                            rectangle.height = 0;
                                        }
                                        Color k = c2;
                                        if (b7) {
                                            k = ji.util.d.i(k);
                                        }
                                        final Rectangle c7 = dg.c(da);
                                        if (c7 != null && da5 != null) {
                                            c7.width = (int)da5.a + (int)da5.c - c7.x;
                                            graphics.setClip(c7);
                                            ji.util.d.a(graphics, da.a());
                                        }
                                        switch (n9) {
                                            case 0: {
                                                final int n28 = rectangle.x + rectangle.width;
                                                final int n29 = rectangle.y + rectangle.height;
                                                final int n30 = (int)(rectangle.x + da5.c);
                                                final int n31 = (int)(rectangle.y + da5.d);
                                                final int n32 = n30 - n28;
                                                final int n33 = n31 - n29;
                                                if (n32 > 0 && da5.d > 0) {
                                                    graphics.setColor(k);
                                                    graphics.fillRect(n28 - 2, rectangle.y, n32 + 2, (int)da5.d);
                                                    final Rectangle rectangle32 = new Rectangle(n28 - 2, rectangle.y, n32 + 2, (int)da5.d);
                                                    graphics.fillRect(rectangle32.x, rectangle32.y, rectangle32.width, rectangle32.height);
                                                    if ((n12 & 0x1) > 0) {
                                                        final da b30 = da5.b(new da(0L, 0L, da.c, da.d));
                                                        if (rectangle.x > b30.a) {
                                                            b30.c = rectangle.x - b30.a;
                                                            graphics.fillRect((int)b30.a, (int)b30.b, (int)b30.c, (int)b30.d);
                                                        }
                                                    }
                                                }
                                                if (n33 > 0 && rectangle.width > 0) {
                                                    graphics.setColor(k);
                                                    graphics.fillRect(point12.x, n29, rectangle.width, n33);
                                                    break;
                                                }
                                                break;
                                            }
                                            case 90: {
                                                final int n34 = (int)da5.a;
                                                final int n35 = (int)da5.b;
                                                final int x = point12.x;
                                                final int n36 = point12.y + rectangle.height;
                                                final int n37 = x - n34;
                                                final int n38 = (int)(da5.b + da5.d - (point12.y + rectangle.height));
                                                if (n37 > 0 && da5.d > 0) {
                                                    graphics.setColor(k);
                                                    graphics.fillRect(n34, (int)da5.b, n37, (int)da5.d);
                                                    if ((n12 & 0x1) > 0) {
                                                        final da b31 = da5.b(new da(0L, 0L, da.c, da.d));
                                                        if (rectangle.y > b31.b) {
                                                            b31.d = rectangle.y - b31.b;
                                                            graphics.fillRect((int)b31.a, (int)b31.b, (int)b31.c, (int)b31.d);
                                                        }
                                                    }
                                                }
                                                if (n38 > 0 && rectangle.width > 0) {
                                                    graphics.setColor(k);
                                                    graphics.fillRect(n34, n36, (int)da5.c, n38);
                                                    break;
                                                }
                                                break;
                                            }
                                            case 180: {
                                                final int n39 = (int)da5.a;
                                                final int n40 = (int)da5.b;
                                                final int n41 = (int)(da5.a + da5.c);
                                                final int y = point12.y;
                                                final int n42 = n41 - n39;
                                                final int n43 = y - n40;
                                                if (n42 > 0 && n43 > 0) {
                                                    graphics.setColor(k);
                                                    graphics.fillRect(n39, n40, n42, n43);
                                                    if ((n12 & 0x1) > 0) {
                                                        final da b32 = da5.b(new da(0L, 0L, da.c, da.d));
                                                        if (rectangle.width < b32.c) {
                                                            final long n44 = b32.a + b32.c + 1;
                                                            b32.a = rectangle.x + rectangle.width;
                                                            b32.c = n44 - b32.a;
                                                            graphics.fillRect((int)b32.a - 1, (int)b32.b, (int)b32.c, (int)b32.d);
                                                        }
                                                    }
                                                }
                                                if (n43 > 0 && rectangle.width > 0) {
                                                    graphics.setColor(k);
                                                    graphics.fillRect(n39, y, point12.x - n39 + 1, point12.y + rectangle.height - y);
                                                    break;
                                                }
                                                break;
                                            }
                                            case 270: {
                                                final int n45 = (int)da5.a;
                                                final int n46 = (int)da5.b;
                                                final int n47 = (int)(da5.a + da5.c);
                                                final int y2 = point12.y;
                                                final int n48 = n47 - n45;
                                                final int n49 = y2 - n46;
                                                if (n48 > 0 && n49 > 0) {
                                                    graphics.setColor(k);
                                                    graphics.fillRect(n45, n46, n48, n49 + 1);
                                                }
                                                if (n48 <= rectangle.width) {
                                                    break;
                                                }
                                                graphics.setColor(k);
                                                graphics.fillRect(point12.x + rectangle.width, y2, (int)(da5.a + da5.c - (point12.x + rectangle.width)), rectangle.height);
                                                if ((n12 & 0x1) <= 0) {
                                                    break;
                                                }
                                                final da b33 = da5.b(new da(0L, 0L, da.c, da.d));
                                                if (rectangle.x + rectangle.height < b33.a + b33.d) {
                                                    final long n50 = b33.b + b33.d;
                                                    b33.b = rectangle.y + rectangle.height;
                                                    b33.d = n50 - b33.b;
                                                    graphics.fillRect((int)b33.a, (int)b33.b, (int)b33.c, (int)b33.d);
                                                    break;
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    Shape clipRect = null;
                                    if (graphics != null) {
                                        clipRect = graphics.getClipRect();
                                        if (c3 != null && (!ji.util.d.ls || !b24)) {
                                            ji.util.d.a(graphics, c3);
                                        }
                                    }
                                    if (da5 != null && (dg.d7() || a6)) {
                                        boolean b34 = true;
                                        if (dg.d9() == null) {
                                            if (b7) {
                                                graphics.setColor(ji.util.d.i(Color.black));
                                            }
                                            else {
                                                graphics.setColor(Color.black);
                                            }
                                        }
                                        else if (b7) {
                                            graphics.setColor(ji.util.d.i(dg.d9()));
                                        }
                                        else {
                                            graphics.setColor(dg.d9());
                                        }
                                        final int n51 = (int)da5.a;
                                        final int n52 = (int)da5.a + (int)da5.c;
                                        final int n53 = (int)da5.b;
                                        final int n54 = (int)da5.b + (int)da5.d;
                                        if (ji.util.d.ls && b24) {
                                            final Rectangle c8 = dg.c(da);
                                            if (c8 != null) {
                                                graphics.setClip(c8);
                                            }
                                        }
                                        dg.a(graphics.getColor());
                                        dg.a(n51, n53, n52, n54);
                                        if (ji.util.d.ls && b24 && !dg.w() && dg.dy() && a6) {
                                            if (!dg.a4() && !dg.a5() && dg.ak()) {
                                                b34 = false;
                                            }
                                            if (this.d.ci() > 0) {
                                                b34 = false;
                                            }
                                        }
                                        if (b34) {
                                            int n55 = 0;
                                            if (dg.dy() && w) {
                                                if (a6) {
                                                    n55 = 5;
                                                }
                                                else if (dg.w()) {
                                                    n55 = 5;
                                                }
                                            }
                                            if (dg.d5() != 9 && dg.at()) {
                                                n16 = 1;
                                            }
                                            if (n16 != 0 && !this.d.en() && !b22) {
                                                rectangle10 = graphics.getClipBounds();
                                                color = graphics.getColor();
                                                rectangle11 = new Rectangle(n51 + n55, n53, n52 - n55, n53);
                                                rectangle12 = new Rectangle(n51, n53 + n55, n51, n54 - n55);
                                                rectangle13 = new Rectangle(n52, n53 + n55, n52, n54 - n55);
                                                rectangle14 = new Rectangle(n51 + n55, n54, n52 - n55, n54);
                                            }
                                            else {
                                                final Rectangle clipBounds3 = graphics.getClipBounds();
                                                graphics.setClip((int)da.a, (int)da.b, (int)da.c, (int)da.d);
                                                graphics.drawLine(n51 + n55, n53, n52 - n55, n53);
                                                graphics.drawLine(n51, n53 + n55, n51, n54 - n55);
                                                graphics.drawLine(n52, n53 + n55, n52, n54 - n55);
                                                graphics.drawLine(n51 + n55, n54, n52 - n55, n54);
                                                if (clipBounds3 != null) {
                                                    graphics.setClip(clipBounds3.x, clipBounds3.y, clipBounds3.width, clipBounds3.height);
                                                }
                                                b23 = true;
                                                dg.n(true);
                                            }
                                        }
                                    }
                                    if (clipRect != null) {
                                        graphics.setClip(clipRect);
                                    }
                                }
                                break Label_11774;
                            }
                            catch (Exception ex8) {
                                break Label_11774;
                            }
                            finally {
                                graphics.setColor(color2);
                            }
                        }
                        if (!b3) {
                            dg.aj(true);
                        }
                        final dc fs4 = dg.fs();
                        if (dg.at()) {
                            da5 = dy.a(dg.by(), graphics, dt);
                            switch (n9) {
                                case 0: {
                                    dg.k(new da(a5.a, a5.b, (int)da5.c, (int)da5.d));
                                    break;
                                }
                                case 90: {
                                    dg.k(new da(a5.a - (da5.c - fs4.a), a5.b, (int)da5.c, (int)da5.d));
                                    break;
                                }
                                case 180: {
                                    dg.k(new da(a5.a - (da5.c - fs4.a), a5.b - (da5.d - fs4.b), (int)da5.c, (int)da5.d));
                                    break;
                                }
                                case 270: {
                                    dg.k(new da(a5.a, a5.b - (da5.d - fs4.b), (int)da5.c, (int)da5.d));
                                    break;
                                }
                            }
                        }
                        else {
                            dg.k(new da(a5.a, a5.b, (int)fs4.a, (int)fs4.b));
                        }
                        final boolean dz = dg.dz();
                        if (dg.e9()) {
                            final dw a9 = dg.a(c, n, ad, dx, af, b2, dy.a(dt), dx.am > 1, ad.iv(), ad.hw());
                            if (!e8) {
                                dy.i(this.a(dt, a9, point12.x, point12.y, (int)fs4.a, (int)fs4.b, dg.d6(), component, dz, dg.av()));
                            }
                            final Color o10 = dt.o();
                            try {
                                if (dg.at() && da5 != null && !a6) {
                                    Color color3 = c2;
                                    final boolean b35 = ad.h4() && b2 && dx.am == 1;
                                    if (b7) {
                                        color3 = ji.util.d.i(color3);
                                    }
                                    final boolean p29 = dt.p();
                                    final boolean s2 = dt.s();
                                    final Color o11 = dt.o();
                                    if (b35 && !a6) {
                                        color3 = Color.white;
                                        dt.f(false);
                                    }
                                    if (dg.dz()) {
                                        dt.e(true);
                                    }
                                    else {
                                        dt.e(false);
                                    }
                                    switch (n9) {
                                        case 0: {
                                            final int n56 = point12.x + (int)fs4.a;
                                            final int n57 = point12.y + (int)fs4.b;
                                            int min = (int)(point12.x + da5.c);
                                            final int n58 = (int)(point12.y + da5.d);
                                            if ((n12 & 0x1) > 0) {
                                                min = Math.min((int)(da5.a + da5.c + 1), min);
                                            }
                                            final int n59 = min - n56 + 1;
                                            final int n60 = n58 - n57;
                                            if (n59 > 0 && da5.d > 0) {
                                                dt.a(color3);
                                                final Rectangle rectangle33 = new Rectangle(n56 - 1, point12.y, n59, (int)da5.d);
                                                dy.c(dt, rectangle33.x, rectangle33.y, rectangle33.width, rectangle33.height, component);
                                                if ((n12 & 0x1) > 0) {
                                                    final da b36 = da5.b(new da(0L, 0L, da.c, da.d));
                                                    if (rectangle.x > b36.a) {
                                                        b36.c = rectangle.x - b36.a;
                                                        dy.c(dt, (int)b36.a, (int)b36.b, (int)b36.c, (int)b36.d, component);
                                                    }
                                                }
                                            }
                                            if (n60 > 0 && fs4.a > 0) {
                                                dt.a(color3);
                                                dy.c(dt, point12.x, n57, (int)fs4.a, n60, component);
                                                break;
                                            }
                                            break;
                                        }
                                        case 90: {
                                            final int n61 = (int)da5.a;
                                            final int n62 = (int)da5.b;
                                            final int x2 = point12.x;
                                            final int n63 = (int)(point12.y + fs4.b);
                                            final int n64 = x2 - n61;
                                            final int n65 = (int)(da5.b + da5.d - (point12.y + fs4.b));
                                            if (n64 > 0 && da5.d > 0) {
                                                dt.a(color3);
                                                dy.c(dt, n61, (int)da5.b, n64, (int)da5.d, component);
                                                if ((n12 & 0x1) > 0) {
                                                    final da b37 = da5.b(new da(0L, 0L, da.c, da.d));
                                                    if (rectangle.y > b37.b) {
                                                        b37.d = rectangle.y - b37.b;
                                                        dy.c(dt, (int)b37.a, (int)b37.b, (int)b37.c, (int)b37.d, component);
                                                    }
                                                }
                                            }
                                            if (n65 > 0 && fs4.a > 0) {
                                                dt.a(color3);
                                                dy.c(dt, n61, n63, (int)da5.c, n65, component);
                                                break;
                                            }
                                            break;
                                        }
                                        case 180: {
                                            final int n66 = (int)da5.a;
                                            final int n67 = (int)da5.b;
                                            final int n68 = (int)(da5.a + da5.c);
                                            final int y3 = point12.y;
                                            final int n69 = n68 - n66;
                                            final int n70 = y3 - n67;
                                            if (n69 > 0 && n70 > 0) {
                                                dt.a(color3);
                                                dy.c(dt, n66, n67, n69, n70, component);
                                                if ((n12 & 0x1) > 0) {
                                                    final da b38 = da5.b(new da(0L, 0L, da.c, da.d));
                                                    if (rectangle.width < b38.c) {
                                                        final long n71 = b38.a + b38.c;
                                                        b38.a = rectangle.x + rectangle.width;
                                                        b38.c = n71 - b38.a;
                                                        dy.c(dt, (int)b38.a, (int)b38.b, (int)b38.c, (int)b38.d, component);
                                                    }
                                                }
                                            }
                                            if (n70 > 0 && fs4.a > 0) {
                                                dt.a(color3);
                                                dy.c(dt, n66, y3, point12.x - n66 + 1, (int)(point12.y + fs4.b - y3), component);
                                                break;
                                            }
                                            break;
                                        }
                                        case 270: {
                                            final int n72 = (int)da5.a;
                                            final int n73 = (int)da5.b;
                                            final int n74 = (int)(da5.a + da5.c);
                                            final int y4 = point12.y;
                                            final int n75 = n74 - n72;
                                            final int n76 = y4 - n73;
                                            if (n75 > 0 && n76 > 0) {
                                                dt.a(color3);
                                                dy.c(dt, n72, n73, n75, n76 + 1, component);
                                            }
                                            if (n75 <= fs4.a) {
                                                break;
                                            }
                                            dt.a(color3);
                                            dy.c(dt, (int)(point12.x + fs4.a), y4, (int)(da5.a + da5.c - (point12.x + fs4.a)), (int)fs4.b, component);
                                            if ((n12 & 0x1) <= 0) {
                                                break;
                                            }
                                            final da b39 = da5.b(new da(0L, 0L, da.c, da.d));
                                            if (rectangle.x + rectangle.height < b39.a + b39.d) {
                                                final long n77 = b39.b + b39.d;
                                                b39.b = rectangle.y + rectangle.height;
                                                b39.d = n77 - b39.b;
                                                dy.c(dt, (int)b39.a, (int)b39.b, (int)b39.c, (int)b39.d, component);
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                    dt.e(p29);
                                    dt.f(s2);
                                    dt.a(o11);
                                }
                                if (dg.d7()) {
                                    if (dg.d9() == null) {
                                        if (b7) {
                                            dt.a(ji.util.d.i(Color.black));
                                        }
                                        else {
                                            dt.a(Color.black);
                                        }
                                    }
                                    else if (b7) {
                                        dt.a(ji.util.d.i(dg.d9()));
                                    }
                                    else {
                                        dt.a(dg.d9());
                                    }
                                    final int n78 = (int)da5.a;
                                    final int n79 = (int)da5.a + (int)da5.c;
                                    final int n80 = (int)da5.b;
                                    final int n81 = (int)da5.b + (int)da5.d;
                                    dy.a(dt, n78, n80, n79 + 1, n80, component);
                                    dy.a(dt, n78, n80, n78, n81, component);
                                    dy.a(dt, n79, n80, n79, n81, component);
                                    dy.a(dt, n78, n81, n79 + 1, n81, component);
                                }
                            }
                            catch (Exception ex9) {}
                            finally {
                                dt.a(o10);
                            }
                            if (!b2) {
                                dg.cz();
                            }
                        }
                        else {
                            dy.i(this.a(dt, dg.a(component, af), point12.x, point12.y, (int)fs4.a, (int)fs4.b, dg.d6(), component, dz, dg.av()));
                            if (b2) {
                                dg.cz();
                            }
                        }
                    }
                    this.f = false;
                    boolean b40 = true;
                    if (dg.dy()) {
                        dg.f0();
                        if (dg.d5() == 9) {
                            final d4 d13 = new d4();
                            final int l = dg.m(n9 + dg.b3());
                            final Rectangle boundingBox = d4.getBoundingBox();
                            final Point point16 = new Point(boundingBox.x, boundingBox.y);
                            final Point point17 = new Point(boundingBox.x + boundingBox.width, boundingBox.y);
                            final Point point18 = new Point(boundingBox.x, boundingBox.y + boundingBox.height);
                            final Point point19 = new Point(boundingBox.x + boundingBox.width, boundingBox.y + boundingBox.height);
                            switch (l) {
                                case 90: {
                                    d13.a(point17);
                                    d13.a(point19);
                                    d13.a(point18);
                                    d13.a(point16);
                                    break;
                                }
                                case 180: {
                                    d13.a(point19);
                                    d13.a(point18);
                                    d13.a(point16);
                                    d13.a(point17);
                                    break;
                                }
                                case 270: {
                                    d13.a(point18);
                                    d13.a(point16);
                                    d13.a(point17);
                                    d13.a(point19);
                                    break;
                                }
                                default: {
                                    d13.a(point16);
                                    d13.a(point17);
                                    d13.a(point19);
                                    d13.a(point18);
                                    break;
                                }
                            }
                            final da a10 = dy.a(graphics, dg, d13, component, ad, b8.a(1, dg, dx, n4, b3), null, ji.util.i.c(113), af, false, false);
                            if (a10 != null) {
                                dg.b(a10.a, a10.b, a10.c, a10.d);
                                for (int n82 = 0; n82 < 4; ++n82) {
                                    dg.e(n82, n82);
                                }
                            }
                            else {
                                for (int n83 = 0; n83 < 4; ++n83) {
                                    dg.e(n83, n83);
                                }
                            }
                        }
                        else if (dg.at()) {
                            final da a11 = dy.a(dg.by(), graphics, dt);
                            final d4 d14 = new d4();
                            if (a11 != null) {
                                d14.addPoint((int)a11.a, (int)a11.b);
                                d14.addPoint((int)(a11.a + a11.c), (int)a11.b);
                                d14.addPoint((int)(a11.a + a11.c), (int)(a11.b + a11.d));
                                d14.addPoint((int)a11.a, (int)(a11.b + a11.d));
                            }
                            final da a12 = dy.a(graphics, dg, d14, component, ad, b8.a(1, dg, dx, n4, b3), null, false, af, false, b23);
                            for (int b41 = d14.b(), n84 = 0; n84 < b41; ++n84) {
                                dg.e(n84, n84);
                            }
                            if (a12 != null) {
                                dg.b(a12.a, a12.b, a12.c, a12.d);
                            }
                            dg.k(new da(d14.getBounds()));
                            dg.i(new da(d14.getBounds()));
                            b40 = false;
                            if (n16 != 0 && dg.w()) {
                                if (rectangle10 != null) {
                                    graphics.setClip(rectangle10.x, rectangle10.y, rectangle10.width, rectangle10.height);
                                }
                                if (rectangle11 != null) {
                                    graphics.setColor(color);
                                    dg.a(color);
                                    graphics.setClip((int)da.a, (int)da.b, (int)da.c, (int)da.d);
                                    graphics.drawLine(rectangle11.x, rectangle11.y, rectangle11.width, rectangle11.height);
                                    graphics.drawLine(rectangle12.x, rectangle12.y, rectangle12.width, rectangle12.height);
                                    graphics.drawLine(rectangle13.x, rectangle13.y, rectangle13.width, rectangle13.height);
                                    graphics.drawLine(rectangle14.x, rectangle14.y, rectangle14.width, rectangle14.height);
                                    if (rectangle10 != null) {
                                        graphics.setClip(rectangle10.x, rectangle10.y, rectangle10.width, rectangle10.height);
                                    }
                                    dg.n(true);
                                }
                            }
                        }
                        else {
                            dy.a(graphics, dg, d4, component, ad, b8.a(1, dg, dx, n4, b3), null, false, af, false, false);
                        }
                        if (dg.d5() == 7) {
                            this.f = true;
                        }
                    }
                    if (b40) {
                        dg.i(new da(rectangle));
                    }
                    if (dg.d5() == 7 && n5 >= 0 && n6 == dg.d0()) {
                        this.f = true;
                    }
                    if (this.f) {
                        final d4 r = dg.r(dg.ep());
                        final int min2 = Math.min(rectangle.x, rectangle.x + rectangle.width);
                        final int max = Math.max(rectangle.y, rectangle.y + rectangle.height);
                        final d4 d15 = new d4();
                        if (r != null) {
                            if (dg.cj() + dg.b4() == 0) {
                                for (int n85 = 0; n85 < r.b(); ++n85) {
                                    final d5 a13 = r.a(n85);
                                    d15.addPoint((int)(min2 + a13.a), (int)(max - (rectangle.height - a13.b)));
                                }
                            }
                            else {
                                for (int n86 = 0; n86 < r.b(); ++n86) {
                                    final d5 a14 = r.a(n86);
                                    d15.addPoint((int)(min2 + a14.a), (int)(max - a14.b));
                                }
                            }
                        }
                        final da fr3 = dg.fr();
                        Rectangle a15 = null;
                        if (fr3 != null) {
                            a15 = fr3.a();
                        }
                        if (ji.util.d.ls && b24) {
                            if (dg.w()) {
                                this.a(d15, graphics, b16, a15, ji.util.d.ls && dg.b(n9), da, dy, dg);
                            }
                        }
                        else {
                            this.a(d15, graphics, b16, a15, ji.util.d.ls && dg.b(n9), da, dy, dg);
                        }
                        dg.a(d15);
                        dg.a(a15);
                    }
                }
                else if (!b2) {
                    ji.util.d.am(true);
                    if (graphics != null) {
                        if (b) {
                            dg.k(new da(rectangle));
                        }
                    }
                    else {
                        final dc fs5 = dg.fs();
                        final da da6 = new da(a5.a, a5.b, (int)fs5.a, (int)fs5.b);
                        Label_14383: {
                            if (dg.at()) {
                                final da a16 = dy.a(dg.by(), graphics, dt);
                                switch (n9) {
                                    case 0: {
                                        dg.k(new da(a5.a, a5.b, (int)a16.c, (int)a16.d));
                                        break;
                                    }
                                    case 90: {
                                        dg.k(new da(a5.a - (a16.c - fs5.a), a5.b, (int)a16.c, (int)a16.d));
                                        break;
                                    }
                                    case 180: {
                                        dg.k(new da(a5.a - (a16.c - fs5.a), a5.b - (a16.d - fs5.b), (int)a16.c, (int)a16.d));
                                        break;
                                    }
                                    case 270: {
                                        dg.k(new da(a5.a, a5.b - (a16.d - fs5.b), (int)a16.c, (int)a16.d));
                                        break;
                                    }
                                }
                                final Color o12 = dt.o();
                                try {
                                    if (dg.at() && a16 != null && !a6) {
                                        switch (n9) {
                                            case 0: {
                                                final int n87 = point12.x + (int)fs5.a;
                                                final int n88 = point12.y + (int)fs5.b;
                                                final int n89 = (int)(point12.x + a16.c);
                                                final int n90 = (int)(point12.y + a16.d);
                                                final int n91 = n89 - n87;
                                                final int n92 = n90 - n88;
                                                if (n91 > 0 && a16.d > 0) {
                                                    dt.a(c2);
                                                    dy.c(dt, n87 - 2, point12.y, n91 + 2, (int)a16.d, component);
                                                }
                                                if (n92 > 0 && fs5.a > 0) {
                                                    dt.a(c2);
                                                    dy.c(dt, point12.x, n88, (int)fs5.a, n92, component);
                                                    break;
                                                }
                                                break;
                                            }
                                            case 90: {
                                                final int n93 = (int)a16.a;
                                                final int n94 = (int)a16.b;
                                                final int x3 = point12.x;
                                                final int n95 = (int)(point12.y + fs5.b);
                                                final int n96 = x3 - n93;
                                                final int n97 = (int)(a16.b + a16.d - (point12.y + fs5.b));
                                                if (n96 > 0 && a16.d > 0) {
                                                    dt.a(c2);
                                                    dy.c(dt, n93, (int)a16.b, n96, (int)a16.d, component);
                                                }
                                                if (n97 > 0 && fs5.a > 0) {
                                                    dt.a(c2);
                                                    dy.c(dt, n93, n95, (int)a16.c, n97, component);
                                                    break;
                                                }
                                                break;
                                            }
                                            case 180: {
                                                final int n98 = (int)a16.a;
                                                final int n99 = (int)a16.b;
                                                final int n100 = (int)(a16.a + a16.c);
                                                final int y5 = point12.y;
                                                final int n101 = n100 - n98;
                                                final int n102 = y5 - n99;
                                                if (n101 > 0 && n102 > 0) {
                                                    dt.a(c2);
                                                    dy.c(dt, n98, n99, n101, n102, component);
                                                }
                                                if (n102 > 0 && fs5.a > 0) {
                                                    dt.a(c2);
                                                    dy.c(dt, n98, y5, point12.x - n98 + 1, (int)(point12.y + fs5.b - y5), component);
                                                    break;
                                                }
                                                break;
                                            }
                                            case 270: {
                                                final int n103 = (int)a16.a;
                                                final int n104 = (int)a16.b;
                                                final int n105 = (int)(a16.a + a16.c);
                                                final int y6 = point12.y;
                                                final int n106 = n105 - n103;
                                                final int n107 = y6 - n104;
                                                if (n106 > 0 && n107 > 0) {
                                                    dt.a(c2);
                                                    dy.c(dt, n103, n104, n106, n107 + 1, component);
                                                }
                                                if (n106 > fs5.a) {
                                                    dt.a(c2);
                                                    dy.c(dt, (int)(point12.x + fs5.a), y6, (int)(a16.a + a16.c - (point12.x + fs5.a)), (int)fs5.b, component);
                                                    break;
                                                }
                                                break;
                                            }
                                        }
                                        if (dg.d7()) {
                                            if (dg.d9() == null) {
                                                dt.a(Color.black);
                                            }
                                            else {
                                                dt.a(dg.d9());
                                            }
                                            final int n108 = (int)a16.a;
                                            final int n109 = (int)a16.a + (int)a16.c;
                                            final int n110 = (int)a16.b;
                                            final int n111 = (int)a16.b + (int)a16.d;
                                            dy.a(dt, n108, n110, n109, n110, component);
                                            dy.a(dt, n108, n110, n108, n111, component);
                                            dy.a(dt, n109, n110, n109, n111, component);
                                            dy.a(dt, n108, n111, n109 + 1, n111, component);
                                        }
                                    }
                                    break Label_14383;
                                }
                                catch (Exception ex10) {
                                    break Label_14383;
                                }
                                finally {
                                    dt.a(o12);
                                }
                            }
                            dg.k(da6);
                        }
                        if (da != null) {
                            final da b42 = da.b(da6);
                            if (b42 != null && b42.c > 0 && b42.d > 0 && !dg.bm()) {
                                dg.aj(true);
                            }
                        }
                    }
                    if (dg.d5() == 7 && n5 >= 0 && n6 == dg.d0()) {
                        this.b(false);
                    }
                }
                dg.ac(false);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        finally {}
    }
    
    private final Image a(final dx dx, final Component component, final ad ad, String s, final dg dg, final a0 a0, final int n, final int n2, final boolean b, final af af, final Dimension dimension, final Dimension dimension2, final double n3, final String s2, final int n4, final boolean b2, final ad ad2, final boolean b3) throws Exception {
        Image bd = null;
        try {
            this.t = false;
            ji.util.e.a(true, 757, null, null);
            this.w = true;
            String s3 = s;
            String j = null;
            String k = null;
            if (this.j == null) {
                (this.j = new cy(ad, af, s2, n4, false, true)).aj(false);
                this.j.c0(false);
                this.j.b(0);
                this.j.s(false);
                this.j.aa(true);
                this.j.k(true);
                this.j.cf(!b2);
            }
            if ((ji.util.d.bj(s) || ji.util.d.eg()) && !dg.gi()) {
                final URL i = ji.util.e.j(s2);
                if (i != null) {
                    s3 = a0.b(new URL(i, s), false, true, null, component, af, ad);
                }
                else {
                    s3 = a0.b(new URL(s), false, true, null, component, af, ad);
                }
                j = a0.j();
                k = a0.k();
            }
            else {
                if (dg.gi() && s.startsWith("file:")) {
                    s = s.substring("file:".length());
                }
                if (ac.d(s, s2)) {
                    s3 = a0.a(s, null, component, af);
                }
                else if (dg.gi()) {
                    final df a2 = ad.dm().am().a(new ac(dx.h, false, false, 0, component, s2), new df(s2), dx, null, ad, s2, dx.v, true, dg.gj());
                    boolean b4 = false;
                    final ax g = a2.g();
                    while (g.a()) {
                        final dg b5 = a2.b(g.b());
                        if (b5.gj() == dg.gj()) {
                            dg.a(component, "image:".concat(String.valueOf(String.valueOf(b5.fa()))));
                            b4 = true;
                            s = b5.fa();
                            if (s.startsWith("file:")) {
                                s = s.substring("file:".length());
                                break;
                            }
                            break;
                        }
                    }
                    a2.b.c();
                    if (b4 && ac.d(s, s2)) {
                        s3 = a0.a(s, null, component, af);
                    }
                }
                else {
                    s3 = ji.util.d.a(ji.util.e.an(), s, s2);
                    if (s3 != null) {
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(ji.util.d.bu(s2)).append(s)));
                    }
                }
            }
            ac ac = null;
            try {
                ac = new ac(s3, false, false, 0, false, component, s2);
                final dx a3 = ew.a(s, ac, s.toString(), s3, af, false, ad, j, k, s2, false, 0, 0, 1, true, -1, null, b3, false, this.aa, null, null, null, false, false, true, false);
                this.u = a3.am;
                this.j.a(ac, s, s, s3, null, null, false, a3.bj, a3, null, a0, null, ad2, ad.aa());
                if (ji.util.i.c(5)) {
                    ji.io.h.d(s2, "Annotation image stamp: depth: ".concat(String.valueOf(String.valueOf(this.u))));
                }
                final boolean b6 = a3.am == 1;
                if (ji.util.i.c(5)) {
                    ji.io.h.d(s2, "Annotation image stamp: canScale: ".concat(String.valueOf(String.valueOf(b6))));
                    ji.io.h.d(s2, "Annotation image stamp: autoScaleEnabled: ".concat(String.valueOf(String.valueOf(this.e))));
                    ji.io.h.d(s2, "Annotation image stamp: Auto-scale-new-stamps: ".concat(String.valueOf(String.valueOf(ji.util.i.c(151)))));
                }
                int width;
                int height;
                if (b6 && this.e && ji.util.i.c(151)) {
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(s2, "Annotation image stamp: scaling...");
                    }
                    if (n == 0 || n == 180) {
                        width = (int)Math.round(a3.m * n3);
                        height = (int)Math.round(a3.n * n3);
                        dimension2.width = a3.m;
                        dimension2.height = a3.n;
                    }
                    else {
                        width = (int)Math.round(a3.n * n3);
                        height = (int)Math.round(a3.m * n3);
                        dimension2.width = a3.n;
                        dimension2.height = a3.m;
                    }
                    if (b3) {
                        this.j.cy(true);
                        this.j.a(3, false, false, true);
                        this.j.cp(true);
                    }
                    else {
                        this.j.cy(true);
                        this.j.a(ad.ek(), false, false, true);
                        this.j.cp(true);
                    }
                    this.t = true;
                }
                else {
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(s2, "Annotation image stamp: not scaling...");
                    }
                    if (n == 0 || n == 180) {
                        width = a3.m;
                        height = a3.n;
                    }
                    else {
                        width = a3.n;
                        height = a3.m;
                    }
                    dimension2.width = width;
                    dimension2.height = height;
                }
                this.j.wo = true;
                if (ji.util.i.c(5)) {
                    ji.io.h.d(s2, "Annotation image stamp: destination width: ".concat(String.valueOf(String.valueOf(dimension2.width))));
                    ji.io.h.d(s2, "Annotation image stamp: destination height: ".concat(String.valueOf(String.valueOf(dimension2.height))));
                }
                if (ji.util.i.c(121)) {
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(s2, "Annotation image stamp: matching resolution...");
                        ji.io.h.d(s2, "Annotation image stamp: main image resolution...".concat(String.valueOf(String.valueOf(dx.ac))));
                        ji.io.h.d(s2, "Annotation image stamp: annotation image resolution...".concat(String.valueOf(String.valueOf(a3.ac))));
                    }
                    final double n5 = dx.ac / a3.ac;
                    width *= (int)n5;
                    height *= (int)n5;
                    dimension2.width *= (int)n5;
                    dimension2.height *= (int)n5;
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(s2, "Annotation image stamp: factor: ".concat(String.valueOf(String.valueOf(n5))));
                    }
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(s2, "Annotation image stamp: new width: ".concat(String.valueOf(String.valueOf(dimension2.width))));
                    }
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(s2, "Annotation image stamp: new height: ".concat(String.valueOf(String.valueOf(dimension2.height))));
                    }
                }
                else if (ji.util.i.c(5)) {
                    ji.io.h.d(s2, "Annotation image stamp: not matching resolution...");
                }
                final double n6 = dx.d() / a3.d();
                dimension2.width *= (int)n6;
                dimension2.height *= (int)n6;
                final int width2 = (int)(width * n6);
                final int height2 = (int)(height * n6);
                this.j.setBounds(0, 0, width2, height2);
                this.j.b(1, 2, false);
                this.j.f(n2, false);
                this.j.e(n, false);
                this.j.cv(b);
                this.j.l(true);
                this.j.s(ji.util.d.dr);
                this.j.t(ji.util.d.ds);
                this.j.u(ji.util.d.dt);
                bd = this.j.bd(false);
                if (bd == null) {
                    ji.io.h.d(s2, "Annotation: ResNotFound ".concat(String.valueOf(String.valueOf(s))));
                    ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(254, s2)))).append(": ").append(s))), component, 30, null, af, s2);
                }
                dimension.width = width2;
                dimension.height = height2;
                if (ji.util.i.c(5)) {
                    ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Annotation image stamp: image dimensions now: ").append(dimension.width).append(" * ").append(dimension.height))));
                }
                if (a3.bj != null) {
                    a3.bj.close(this.j.r(), ad);
                    a3.bj = null;
                }
                this.j.dc();
                this.j.q();
            }
            finally {
                if (ac != null) {
                    try {
                        ac.a(component);
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ex.toString()))).append(": ").append(s))), component, 30, null, af, s2);
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        finally {
            ji.util.e.a(this.w = false, 0, af, component);
        }
        return bd;
    }
    
    public void a(final boolean s) {
        this.s = s;
    }
    
    public final void a(final Polygon polygon, final Graphics graphics, final Color color, final Rectangle rectangle, final boolean b, final da da, final dy dy, final dg dg) {
        try {
            if (!this.m && dg.b(this.b, this.c)) {
                try {
                    this.m = true;
                    if (this.f && this.s) {
                        if (this.g == null) {
                            (this.g = new u6()).a(graphics, color, b, dy, da);
                            this.x = new d4(polygon);
                            this.g.a(polygon, rectangle);
                            (this.k = new bb(this.z, this.g)).start();
                        }
                        else {
                            this.g.a(polygon, rectangle);
                        }
                    }
                }
                finally {
                    this.m = false;
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final boolean b(final boolean q) {
        try {
            if (this.g != null) {
                this.f = false;
                this.q = q;
                this.g.a();
                ji.util.d.b(this.k, "editBar");
                this.q = false;
                this.k = null;
                this.g = null;
                this.n = null;
                this.o = null;
                this.p = false;
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    private final Font a(final Component component, final int n, final boolean b) {
        Font font = component.getFont();
        Font r = null;
        boolean b2 = false;
        if (this.r != null && n != this.r.getSize()) {
            this.r = null;
        }
        if (this.r != null && b != this.r.isBold()) {
            this.r = null;
        }
        if (this.r != null) {
            return this.r;
        }
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        if (fontList != null) {
            for (int i = 0; i < fontList.length; ++i) {
                if (fontList[i].toLowerCase().indexOf("mono") < 0) {
                    if (b) {
                        font = new Font(fontList[i], 1, n);
                    }
                    else {
                        font = new Font(fontList[i], 0, n);
                    }
                    b2 = true;
                    break;
                }
                if (r == null && fontList[i].toLowerCase().indexOf("courier") >= 0) {
                    if (b) {
                        r = new Font(fontList[i], 1, n);
                    }
                    else {
                        r = new Font(fontList[i], 0, n);
                    }
                }
            }
        }
        if (!b2 && r != null) {
            return this.r = r;
        }
        return this.r = font;
    }
    
    public void a(final Graphics graphics, final dg dg, final double n, final dx dx, final Component component, final dy dy, final da da, final d4 d4, final int n2, final dt dt, final boolean b, final int n3) {
        if (ji.util.i.c(67)) {
            final String h = dg.h();
            if (!ji.util.d.by(h)) {
                final Color black = Color.black;
                final int n4 = (int)(12.0 * n * 2.0);
                if (n4 > 3) {
                    if (graphics != null) {
                        graphics.setFont(this.a(component, n4, true));
                        final int height = graphics.getFontMetrics().getHeight();
                        final int stringWidth = graphics.getFontMetrics().stringWidth("w");
                        final Rectangle boundingBox = d4.getBoundingBox();
                        int n5 = 0;
                        int n6 = 0;
                        final int n7 = 1 + (int)dy.a(n2, dg, dx);
                        final Rectangle rectangle = new Rectangle(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
                        switch (n3) {
                            case 0: {
                                n5 = boundingBox.y + height;
                                n6 = boundingBox.x + 3 * n7 / 4;
                                final Rectangle rectangle2 = rectangle;
                                rectangle2.width -= n7 + stringWidth / 2;
                                break;
                            }
                            case 90: {
                                n5 = boundingBox.y + height;
                                n6 = boundingBox.x + n7 + stringWidth / 2;
                                final Rectangle rectangle3 = rectangle;
                                rectangle3.width -= stringWidth / 2;
                                break;
                            }
                            case 180: {
                                n5 = boundingBox.y + boundingBox.height - height / 2;
                                n6 = boundingBox.x + n7 + stringWidth;
                                final Rectangle rectangle4 = rectangle;
                                rectangle4.width -= stringWidth / 2;
                                break;
                            }
                            case 270: {
                                n5 = boundingBox.y + n7 + height;
                                n6 = boundingBox.x + stringWidth / 2;
                                final Rectangle rectangle5 = rectangle;
                                rectangle5.width -= 3 * n7;
                                break;
                            }
                        }
                        graphics.setClip(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                        ji.util.d.a(graphics, da.a());
                        graphics.setColor(black);
                        graphics.drawString(h, n6, n5);
                    }
                    else {
                        int n8 = 10;
                        int y = 10;
                        final Font a = this.a(component, n4, false);
                        final int height2 = component.getGraphics().getFontMetrics().getHeight();
                        final int stringWidth2 = component.getGraphics().getFontMetrics().stringWidth("w");
                        final Rectangle boundingBox2 = d4.getBoundingBox();
                        final int n9 = 1 + (int)dy.a(n2, dg, dx);
                        int width = boundingBox2.width;
                        switch (n3) {
                            case 0: {
                                y = boundingBox2.y + height2 / 2;
                                n8 = boundingBox2.x + 3 * n9 / 4;
                                width = boundingBox2.width - 2 * n9;
                                break;
                            }
                            case 90: {
                                y = boundingBox2.y;
                                n8 = boundingBox2.x + n9 + stringWidth2 / 2;
                                width = boundingBox2.width - n9 - stringWidth2;
                                break;
                            }
                            case 180: {
                                y = boundingBox2.y + boundingBox2.height - height2 - height2 / 2;
                                n8 = boundingBox2.x + n9 + stringWidth2;
                                width = boundingBox2.width - n9 - 2 * stringWidth2;
                                break;
                            }
                            case 270: {
                                y = boundingBox2.y + n9;
                                n8 = boundingBox2.x + stringWidth2 / 2;
                                width = boundingBox2.width - 3 * n9;
                                break;
                            }
                        }
                        dy.i(this.a(dt, h, n8, y, black, a, component, width, b));
                    }
                }
            }
        }
    }
    
    public boolean a() {
        return this.w;
    }
    
    public void b() {
        this.r = null;
        try {
            if (this.j != null) {
                this.j.q();
                this.j.releaseResources();
                this.j = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public void c() {
        this.a = null;
    }
    
    private boolean a(final dt dt, final dw dw, final int n, final int n2, final int n3, final int n4, final Color color, final Component component, final boolean b, final boolean b2) {
        return dt.a(dw, n, n2, n3, n4, color, component, b, b2, this.aa);
    }
    
    private boolean a(final dt dt, final du du, final int n, final int n2, final int n3, final int n4, final Color color, final Component component, final boolean b, final boolean b2) {
        return dt.a(du, n, n2, n3, n4, color, component, b, b2, this.aa);
    }
    
    private boolean a(final dt dt, final String s, final int n, final int n2, final Color color, final Font font, final Component component, final int n3, final boolean b) {
        return dt.a(s, n, n2, color, font, component, b, n3, this.aa);
    }
    
    class u6 implements Runnable
    {
        dy a;
        da b;
        Graphics c;
        Color d;
        Rectangle e;
        boolean f;
        boolean g;
        int h;
        boolean i;
        int j;
        
        u6() {
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = true;
            this.g = true;
            this.h = 0;
            this.i = false;
            this.j = 0;
        }
        
        public void a(final Graphics graphics, final Color xorMode, final boolean f, final dy a, final da b) {
            try {
                if (graphics != null && xorMode != null) {
                    this.a = a;
                    this.b = b;
                    this.f = f;
                    this.c = graphics.create();
                    this.d = new Color(xorMode.getRGB());
                    this.c.setColor(Color.white);
                    this.c.setXORMode(xorMode);
                }
            }
            catch (Exception ex) {}
        }
        
        private final void a(final da da) {
            try {
                if (dz.this.h != null && !dz.this.d.ga()) {
                    if (dz.this.d != null && dz.this.d.b3() != null && dz.this.d.b3().w()) {
                        if (this.e != null) {
                            this.c.setClip(this.e);
                        }
                        if (dz.this.l != null) {
                            ji.util.d.a(this.c, dz.this.l.a());
                        }
                        if (da != null) {
                            ji.util.d.a(this.c, da.a());
                        }
                        dz.this.n = new Rectangle(dz.this.h);
                        dz.this.o = this.c.getClipBounds();
                        this.c.fillRect(dz.this.h.x, dz.this.h.y, dz.this.h.width, dz.this.h.height);
                        dz.this.p = true;
                        dz.this.i = false;
                        ji.util.d.ew();
                    }
                    this.c.setClip(null);
                }
            }
            catch (Exception ex) {}
        }
        
        private final void b(final da da) {
            try {
                if (dz.this.n != null) {
                    if (dz.this.p) {
                        this.c.setClip(dz.this.o);
                        if (dz.this.l != null) {
                            ji.util.d.a(this.c, dz.this.l.a());
                        }
                        if (da != null) {
                            ji.util.d.a(this.c, da.a());
                        }
                        this.c.fillRect(dz.this.n.x, dz.this.n.y, dz.this.n.width, dz.this.n.height);
                        dz.this.p = false;
                        ji.util.d.ew();
                    }
                    this.c.setClip(null);
                }
            }
            catch (Exception ex) {}
        }
        
        public void a(final Polygon polygon, final Rectangle e) {
            try {
                this.e = e;
                if (polygon != null) {
                    dz.this.h = polygon.getBoundingBox();
                }
                this.j = 0;
            }
            catch (Exception ex) {}
            ++this.h;
        }
        
        private void a() {
            synchronized (this) {
                this.i = true;
            }
        }
        
        private boolean b() {
            synchronized (this) {
                return this.i;
            }
        }
        
        public void run() {
            try {
                if (dz.this.h != null && this.c != null && this.d != null) {
                    final int j = 500;
                    final int n = 10;
                    final int n2 = 39;
                    while (dz.this.f && dz.this.s && !this.b()) {
                        dz.this.v = true;
                        if (dz.this.f && dz.this.s) {
                            if (this.j <= 0) {
                                if (this.h > 1 || !this.a.x()) {
                                    if (this.g) {
                                        this.a(this.b);
                                    }
                                    else {
                                        this.b(this.b);
                                    }
                                    this.g = !this.g;
                                }
                                ++this.h;
                                this.j = j;
                            }
                            ji.util.d.b(n, n2, dz.this.z);
                            this.j -= n;
                        }
                    }
                }
            }
            catch (Exception ex) {}
            finally {
                try {
                    if (dz.this.q || this.f) {
                        this.b(this.b);
                    }
                }
                catch (Exception ex2) {}
                dz.this.f = false;
                dz.this.k = null;
                dz.this.v = false;
                dz.this.g = null;
            }
        }
    }
}
