// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.esial.util.c;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Color;
import com.diginet.digichat.util.ImagesListener;
import com.diginet.digichat.util.s;
import java.awt.Panel;

public class ao extends Panel implements s, ImagesListener
{
    private static final Color a;
    private static boolean b;
    private static boolean c;
    private Dimension dimSize;
    private Vector d;
    private Vector e;
    private ar f;
    public Color g;
    public Color h;
    public Color i;
    public Color j;
    protected int k;
    protected int l;
    protected int m;
    protected int n;
    public as o;
    private String p;
    private String q;
    private String r;
    private int s;
    private ap t;
    private ap u;
    private ap v;
    protected boolean w;
    protected boolean x;
    protected boolean y;
    boolean z;
    private ap aa;
    
    public void a(final ap v) {
        if (this.y && v != this.v) {
            final ap v2 = this.v;
            this.v = v;
            this.repaint();
            this.b();
        }
    }
    
    public void setPopup(final MenuPopup popup) {
        this.f.setPopup(popup);
    }
    
    public void a(final boolean y) {
        if (!(this.y = y)) {
            this.v = null;
        }
        this.repaint();
        this.f.repaint();
    }
    
    void a() {
        if (this.v != null) {
            final m g = this.g();
            this.a(this.d, 0, this.d.size() - 1, this.v);
            if (g != null) {
                this.m = this.b(g);
            }
        }
        this.z = false;
    }
    
    public synchronized void a(final long n) {
        this.z = true;
        this.b(n);
    }
    
    public synchronized void b() {
        this.a(0L);
    }
    
    synchronized void c() {
        for (int size = this.d.size(), i = 0; i < size / 2; ++i) {
            final Object element = this.d.elementAt(i);
            this.d.setElementAt(this.d.elementAt(size - i - 1), i);
            this.d.setElementAt(element, size - i - 1);
        }
        this.f.repaint();
    }
    
    int a(final m m, final m i) {
        int a = 0;
        final int index = this.e.indexOf(this.v);
        for (int size = this.e.size(), j = 0; j < size; ++j) {
            final ap ap = this.e.elementAt((j + index) % size);
            if (ap.d()) {
                a = m.a(i, ap.e());
                if (a != 0) {
                    break;
                }
            }
        }
        return this.x ? a : (-a);
    }
    
    void a(final Vector vector, final int n, final int n2, final ap ap) {
        if (n2 > n) {
            this.e.indexOf(ap);
            this.e.size();
            final int n3 = (n + n2) / 2;
            int n4 = n2;
            int i = n;
            final m a = vector.elementAt(n3).a;
            while (i <= n4) {
                while (i < n2 && this.a(a, vector.elementAt(i).a) > 0) {
                    ++i;
                }
                while (n4 > n && this.a(a, vector.elementAt(n4).a) < 0) {
                    --n4;
                }
                if (i < n4) {
                    final av element = vector.elementAt(i);
                    final av element2 = vector.elementAt(n4);
                    vector.setElementAt(element, n4);
                    vector.setElementAt(element2, i);
                }
                if (i <= n4) {
                    ++i;
                    --n4;
                }
            }
            if (n < n4) {
                this.a(vector, n, n4, ap);
            }
            if (i < n2) {
                this.a(vector, i, n2, ap);
            }
        }
        this.z = false;
    }
    
    public String d() {
        return this.p;
    }
    
    public int e() {
        return this.d.size();
    }
    
    public synchronized int a(final int n) {
        return (n + this.l) / this.k;
    }
    
    public ap b(final int n) {
        final Dimension size = this.size();
        final int n2 = this.y ? this.o.size().width : 2;
        final int size2 = this.e.size();
        if (n < 0) {
            return null;
        }
        if (n > size.width - n2 && n < size.width) {
            return this.aa;
        }
        int n3 = -2;
        int n4 = -1;
        int n5 = 0;
        for (int i = 0; i < size2 - 1; ++i) {
            final ap ap = this.e.elementAt(i);
            final int b = ap.b();
            final int a = ap.a();
            int n6 = n3 + b;
            if (n4 == 0) {
                n6 += n5;
            }
            if (n <= n6 && n >= n3) {
                return ap;
            }
            n3 = n6;
            n4 = b;
            n5 = a;
        }
        return (ap)this.e.elementAt(size2 - 1);
    }
    
    public int c(final int n) {
        final Dimension size = this.size();
        final int n2 = this.y ? this.o.size().width : 2;
        if (n < 0) {
            return -1;
        }
        if (n > size.width - n2 && n < size.width) {
            return -2;
        }
        int n3 = -2;
        for (int i = 0; i < this.e.size() - 1; ++i) {
            final int n4 = n3 + this.e.elementAt(i).a();
            if (n <= n4 && n >= n3) {
                return i;
            }
            n3 = n4;
        }
        return this.e.size() - 1;
    }
    
    public synchronized int d(final int n) {
        return n * this.k - this.l;
    }
    
    public ap e(final int n) {
        return this.e.elementAt(n);
    }
    
    public int f() {
        return this.m;
    }
    
    public synchronized m g() {
        if (this.m >= 0) {
            return this.j(this.m);
        }
        return null;
    }
    
    public void a(final long n, final m m) {
        this.a(n, this.b(m));
    }
    
    public void a(final m m) {
        this.a(0L, m);
    }
    
    public synchronized boolean inside(final int n) {
        return n >= 0 && n < this.d.size();
    }
    
    public synchronized void f(final int n) {
        if (this.inside(n)) {
            final Graphics graphics = this.f.getGraphics();
            if (graphics != null) {
                this.a(n, graphics);
                graphics.dispose();
            }
        }
    }
    
    public void g(final int n) {
        this.a(0L, n);
    }
    
    public void a(final long n, final int n2) {
        this.f.repaint(n, 0, this.d(n2), this.f.size().width, this.k - 1);
    }
    
    protected synchronized void a(final int n, final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            int n2 = 0;
            final int d = this.d(n);
            final int size = this.e.size();
            final ap[][] array = new ap[size][];
            for (int i = 0; i < size; ++i) {
                final ap ap;
                if ((ap = this.e.elementAt(i)).nOver < 0) {
                    n2 = i;
                }
                else {
                    int nOver = i;
                    int n3;
                    while ((nOver = ((ap)this.e.elementAt(n3 = nOver)).nOver) >= 0) {}
                    int n4 = 0;
                    ap[] array2;
                    if ((array2 = array[n3]) == null) {
                        array2 = (array[n3] = new ap[size]);
                    }
                    else {
                        while (array2[++n4] != null) {}
                    }
                    array2[n4] = ap;
                }
            }
            final int n5 = this.y ? this.o.size().width : 2;
            final Dimension size2 = this.f.size();
            final av av = this.d.elementAt(n);
            final Color color = (av.clrBack == null) ? this.h : av.clrBack;
            graphics.setColor(Color.white);
            graphics.drawLine(0, d + this.k - 1, size2.width, d + this.k - 1);
            int n6 = 0;
            for (int j = 0; j < size; ++j) {
                final ap ap2;
                final Object e;
                if ((ap2 = this.e.elementAt(j)).nOver < 0 && ((e = av.a.e(ap2.e())) != null || ap2.isFixed())) {
                    int a;
                    if (j == n2) {
                        a = size2.width - n6;
                    }
                    else {
                        a = ap2.a();
                    }
                    final Color color2;
                    graphics.setColor(color2 = ((n == this.m) ? this.g : color));
                    graphics.fillRect(n6, d, a, this.k - 1);
                    ap2.a(graphics, av, e, n6, d, a, this.k, n == this.m);
                    final ap[] array3;
                    if ((array3 = array[j]) != null) {
                        int n7 = 0;
                        final int n8 = n6;
                        ap ap3;
                        while ((ap3 = array3[n7]) != null) {
                            final Object e2;
                            if ((e2 = av.a.e(ap3.e())) != null) {
                                int n9;
                                int n10;
                                if (e2 instanceof Boolean) {
                                    n9 = 10;
                                    n10 = 11;
                                }
                                else if (e2 instanceof Image) {
                                    n9 = ((Image)e2).getHeight(ap3);
                                    n10 = ((Image)e2).getWidth(ap3);
                                }
                                else {
                                    final FontMetrics fontMetrics = this.getFontMetrics((av.b == null) ? this.getFont() : av.b);
                                    n9 = fontMetrics.stringWidth(e2.toString());
                                    n10 = fontMetrics.getHeight();
                                }
                                int n11 = 0;
                                switch (ap3.nAlig & 0x3) {
                                    default: {
                                        n11 = n8;
                                        break;
                                    }
                                    case 1: {
                                        n11 = n8 + (a - n9 >> 1);
                                        break;
                                    }
                                    case 2: {
                                        n11 = n8 + a - n9;
                                        break;
                                    }
                                    case 3: {
                                        final int n13;
                                        final int n12;
                                        if ((n12 = (n13 = n8 + (n9 >> 1)) - n6) > 0) {
                                            graphics.setColor(color2);
                                            graphics.fillRect(n6 + a, d, n12, this.k - 1);
                                            n6 = n13;
                                        }
                                        n11 = n13 + a - n9;
                                        break;
                                    }
                                }
                                int n14 = 0;
                                switch (ap3.nAlig & 0xC) {
                                    case 0: {
                                        n14 = d;
                                        break;
                                    }
                                    case 4: {
                                        n14 = d + (this.k - n10 >> 1);
                                        break;
                                    }
                                    default: {
                                        n14 = d + this.k - n10;
                                        break;
                                    }
                                }
                                ap3.a(graphics, av, e2, n11, n14, n9, n10, ap3.nOver == this.m);
                            }
                            ++n7;
                        }
                    }
                    if ((n6 += a) > size2.width - n5) {
                        break;
                    }
                }
            }
        }
    }
    
    protected int h(final int n) {
        int n2 = 1;
        for (int i = 0; i < n; ++i) {
            int b = this.e.elementAt(i).b();
            if (i > 0) {
                final ap ap = this.e.elementAt(i - 1);
                if (ap.b() == 0) {
                    b += ap.a();
                }
            }
            n2 += b;
        }
        return n2;
    }
    
    public synchronized int b(final m m) {
        for (int i = 0; i < this.d.size(); ++i) {
            if (((av)this.d.elementAt(i)).a.equals(m)) {
                return i;
            }
        }
        return -1;
    }
    
    public synchronized void c(final m m) {
        final int b = this.b(m);
        if (b >= 0) {
            this.k(b);
        }
    }
    
    protected synchronized void i(final int n) {
        final Dimension size = this.f.size();
        this.l -= n;
        final Graphics a = this.f.a(0, 0, size.width, size.height - 1, n);
        this.f.paint(a);
        a.dispose();
    }
    
    protected synchronized void a(final int n, final int n2) {
        final int n3 = (n >= 0) ? n : (-n);
        for (int i = 0; i < n3 / n2; ++i) {
            this.i((n >= 0) ? n2 : (-n2));
        }
        if (n3 % n2 != 0) {
            this.i((n >= 0) ? (n3 % n2) : (-(n3 % n2)));
        }
    }
    
    public synchronized m j(final int n) {
        return this.d.elementAt(n).a;
    }
    
    public synchronized void k(final int m) {
        if (this.inside(m)) {
            if (this.m != m) {
                final int i = this.m;
                this.m = m;
                this.f(i);
                this.f(m);
            }
            int n = 0;
            final int n2 = this.f.size().height - this.k + 2;
            final int d = this.d(m);
            if (d < 0) {
                n = -d;
            }
            else if (d > n2) {
                n = n2 - d;
            }
            if (n != 0) {
                this.i(n);
                this.o.setValue(this.l);
            }
            this.postEvent(new Event(this, 701, this.j(m)));
        }
        else {
            final int j = this.m;
            this.m = -1;
            this.f(j);
            this.postEvent(new Event(this, 702, null));
        }
    }
    
    public synchronized void a(final m m, int f) {
        if (this.v != null) {
            f = this.f(m);
        }
        this.d.insertElementAt(new av(this, m), f);
        if (this.m >= f) {
            ++this.m;
        }
        final int d = this.d(f);
        if (this.isShowing()) {
            if (this.l == this.o.getMaximum() && this.l > 0 && f == this.d.size() - 1) {
                this.a(-this.k, this.k);
            }
            else {
                final Dimension size = this.f.size();
                if (d >= 0 && d < size.height) {
                    if (this.d.size() > 1) {
                        this.f.a(0, d - 1, size.width, size.height - d, this.k);
                    }
                    this.g(f);
                }
            }
        }
        if (d < 0) {
            this.l += this.k;
        }
        this.h();
    }
    
    ap a(final Event event) {
        if (event.y < this.n) {
            int n = 0;
            for (int i = 0; i < this.e.size() - 1; ++i) {
                final ap ap = this.e.elementAt(i);
                final int n2 = n + ap.a();
                if (event.x > n2 - 4 && event.x < n2 + 4 && ap.c()) {
                    return ap;
                }
                if (event.x < n2) {
                    return null;
                }
                n = n2;
            }
        }
        return null;
    }
    
    public String a(final Object o) {
        return this.q;
    }
    
    public boolean handleEvent(final Event event) {
        Label_0492: {
            switch (event.id) {
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    final int n = this.l - this.o.getValue();
                    if (n != 0) {
                        this.i(n);
                    }
                    return true;
                }
                case 401:
                case 403: {
                    final int size = this.d.size();
                    if (event.key == 1005 && this.m < size - 1) {
                        this.k(this.m + 1);
                    }
                    else if (event.key == 1004 && this.m > 0) {
                        this.k(this.m - 1);
                    }
                    else if ((event.key == 10 || event.key == 13) && this.m >= 0) {
                        this.postEvent(new Event(this, 1001, this.g()));
                    }
                    return true;
                }
                case 501: {
                    this.requestFocus();
                    final ap a = this.a(event);
                    this.t = a;
                    if (a != null) {
                        this.s = event.x;
                    }
                    else if (this.y) {
                        this.u = this.b(event.x);
                        if (this.u.d()) {
                            final Graphics graphics = this.getGraphics();
                            this.a(graphics, this.u, true, this.u == this.v);
                            graphics.dispose();
                            this.w = true;
                        }
                        else {
                            this.u = null;
                        }
                    }
                    return true;
                }
                case 502: {
                    if (!this.w || !this.y || event.target != this || this.u == null) {
                        break Label_0492;
                    }
                    if (this.u != this.aa && this.u != this.v) {
                        this.a(this.u);
                        break Label_0492;
                    }
                    if (this.u == this.aa) {
                        this.x = !this.x;
                        this.c();
                    }
                    final Graphics graphics2 = this.getGraphics();
                    this.a(graphics2, this.u, false, this.u == this.v);
                    graphics2.dispose();
                    break Label_0492;
                }
                case 503: {
                    if (event.target == this && event.y < this.n) {
                        ap ap;
                        if (this.y && event.x > this.size().width - this.o.size().width) {
                            ap = this.aa;
                        }
                        else {
                            ap = this.b(event.x);
                        }
                        if (ap != null) {
                            this.q = ap.a(this.v == ap);
                            if (this.q == null || !this.q.equals(this.r)) {
                                this.postEvent(new Event(this, 7689, this.q));
                                this.r = this.q;
                            }
                        }
                    }
                    if (ao.b) {
                        if (this.a(event) != null) {
                            this.setCursor(Cursor.getPredefinedCursor(11));
                        }
                        else {
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                    }
                    return false;
                }
                case 506: {
                    if (event.target == this && this.t != null) {
                        final int a2 = this.t.a();
                        int x = event.x;
                        final int width = this.size().width;
                        final int n2 = this.y ? this.o.size().width : 2;
                        if (x > width - n2) {
                            x = width - n2;
                        }
                        int n3 = a2 + (x - this.s);
                        if (n3 < 15) {
                            x += 15 - n3;
                            n3 = 15;
                        }
                        if (n3 != a2) {
                            this.t.b(n3);
                        }
                        this.s = x;
                    }
                    if (event.target == this && this.u != null) {
                        final boolean b = event.y >= 0 && event.y < this.n && this.b(event.x) == this.u;
                        if (this.w && !b) {
                            this.w = false;
                            final Graphics graphics3 = this.getGraphics();
                            this.a(graphics3, this.u, false, this.u == this.v);
                            graphics3.dispose();
                        }
                        else if (!this.w && b) {
                            this.w = true;
                            final Graphics graphics4 = this.getGraphics();
                            this.a(graphics4, this.u, true, this.u == this.v);
                            graphics4.dispose();
                        }
                    }
                    return true;
                }
                default: {
                    return super.handleEvent(event);
                }
            }
        }
    }
    
    protected void h() {
        this.o.setValues(this.l, this.f.size().height, 0, this.d.size() * this.k - 2);
        this.l = this.o.getValue();
        if (this.m < 0) {
            this.postEvent(new Event(this, 702, null));
        }
    }
    
    public void resize(final int n, final int n2) {
        this.f.resize(n - this.o.size().width, n2 - this.n);
        this.dimSize = new Dimension(n, n2);
        super.resize(n, n2);
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    public int i() {
        return this.k;
    }
    
    public void l(final int n) {
        this.k = n;
        this.o.setLineIncrement(n);
        this.h();
        this.f.repaint();
    }
    
    public void b(final ap ap) {
        this.a(ap, this.e.size());
    }
    
    public void a(final ap ap, final int n) {
        this.e.insertElementAt(ap, n);
        ap.j = this;
    }
    
    public void d(final m m) {
        this.a(m, this.d.size());
    }
    
    public synchronized void b(final m m, final int n) {
        this.d.setElementAt(new av(this, m), n);
        if (this.v != null) {
            this.a(200L);
        }
        else {
            this.a(200L, n);
        }
    }
    
    public boolean e(final m m) {
        final int b = this.b(m);
        if (b != -1) {
            this.m(b);
            return true;
        }
        return false;
    }
    
    public void m(final int n) {
        if (this.m == n) {
            if (n == this.d.size() - 1) {
                this.k(n - 1);
            }
            else {
                this.k(n + 1);
                --this.m;
            }
        }
        else if (this.m > n) {
            this.k(n - 1);
        }
        this.d.elementAt(n);
        this.d.removeElementAt(n);
        int d = this.d(n);
        final Dimension size = this.f.size();
        if (d < size.height && d > -this.k && this.isShowing()) {
            int k;
            if (d < 0) {
                k = this.k + d;
                d = 0;
            }
            else {
                k = this.k;
            }
            final int maximum = this.o.getMaximum();
            if (this.l > maximum - this.k && maximum != 0 && this.l != 0) {
                if (this.l < this.k) {
                    final int n2 = d + this.l;
                    this.a(this.l, this.l);
                    this.f.paint(this.f.a(0, n2, size.width, size.height - n2 - 1, -k));
                }
                else {
                    this.l -= this.k;
                    this.f.paint(this.f.a(0, 0, size.width, d + this.k, this.k));
                }
            }
            else {
                this.f.paint(this.f.a(0, d, size.width, size.height - d - 1, -k));
            }
        }
        this.h();
    }
    
    public synchronized void j() {
        this.d.removeAllElements();
        this.m = -1;
        this.l = 0;
        this.o.setValues(0, 0, 0, 0);
        this.f.repaint();
        this.postEvent(new Event(this, 702, null));
    }
    
    public void c(final ap ap) {
        final int index = this.e.indexOf(ap);
        if (index >= 0) {
            this.n(index);
        }
    }
    
    public void n(final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 += ((ap)this.e.elementAt(i)).a();
        }
        this.a(n2, 0, this.e.elementAt(n).a(), this.f.size().height);
    }
    
    public void k() {
        this.b(0L);
    }
    
    public void b(final long n) {
        if (this.isShowing()) {
            this.f.repaint(n);
        }
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        if (this.isShowing()) {
            this.f.repaint(n, n2, n3, n4);
        }
    }
    
    public int f(final m m) {
        int i = 0;
        int n = this.d.size() - 1;
        int n2 = 0;
        if (n == -1) {
            return 0;
        }
        int n3 = this.a(m, this.d.elementAt(n).a);
        if (n3 > 0) {
            return n + 1;
        }
        while (i <= n) {
            n2 = i + (n - i) / 2;
            n3 = this.a(m, this.d.elementAt(n2).a);
            if (n3 == 0) {
                return n2;
            }
            if (n3 < 0) {
                n = n2 - 1;
            }
            else {
                i = n2 + 1;
            }
        }
        return (n3 >= 0) ? (n2 + 1) : n2;
    }
    
    protected static void a(final Graphics graphics, String concat, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = n2 + (n4 + graphics.getFontMetrics().getAscent()) / 2 - 1;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int stringWidth = fontMetrics.stringWidth(concat);
        if (stringWidth > n3) {
            final int stringWidth2 = fontMetrics.stringWidth("...");
            final int length = concat.length();
            int n8 = 0;
            int n9;
            int i = n9 = length;
            String substring = concat;
            while (i > n8) {
                substring = concat.substring(0, n9);
                stringWidth = fontMetrics.stringWidth(substring) + stringWidth2;
                if (stringWidth <= n3 && n9 == length) {
                    break;
                }
                if (stringWidth < n3) {
                    n8 = 1 + n9;
                }
                else {
                    i = n9;
                }
                n9 = (n8 + i) / 2;
            }
            concat = String.valueOf(substring).concat(String.valueOf("..."));
        }
        int n10 = 0;
        switch (n5) {
            case 2: {
                n10 = n + n3 - stringWidth;
                break;
            }
            case 1: {
                n10 = n + (n3 - stringWidth) / 2;
                break;
            }
            default: {
                n10 = n;
                break;
            }
        }
        graphics.drawString(concat, n10, n7);
        if (n6 != 0) {
            graphics.setColor(Color.red);
            final int n11 = n2 + n4 / 2;
            if (n6 == 1) {
                graphics.drawLine(n10, n11, n10 + stringWidth, n11);
            }
            else {
                graphics.drawLine(n10, n11 - 2, n10 + stringWidth, n11 - 2);
                graphics.drawLine(n10, n11 + 2, n10 + stringWidth, n11 + 2);
            }
        }
    }
    
    protected void a(final Graphics graphics, final ap ap, final boolean b, final boolean b2) {
        final Dimension size = this.size();
        final int n = this.y ? this.o.size().width : 2;
        int a;
        int h;
        if (ap == this.aa) {
            a = n - 2;
            h = size.width - n + 1;
        }
        else {
            final int index = this.e.indexOf(ap);
            h = this.h(index);
            if (index == this.e.size() - 1) {
                a = size.width - h - n + 1;
            }
            else {
                a = ap.a();
                if (index > 0) {
                    final ap ap2 = this.e.elementAt(index - 1);
                    if (ap2.b() == 0) {
                        a += ap2.a();
                    }
                }
            }
        }
        this.a(graphics, ap, h, a, this.n, b, b2);
    }
    
    protected void a(final Graphics graphics, final ap ap, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        final Color color = (!b && !b2) ? this.i : this.j;
        final Color brighter = color.brighter();
        final Color darker = color.darker();
        graphics.setColor(color);
        graphics.fillRect(n + 1, 2, n2 - 2, n3 - 3);
        graphics.setColor((!b && !b2) ? brighter : darker);
        graphics.drawLine(n, 1, n, n3 - 1);
        graphics.drawLine(n, 1, n + n2 - 1, 1);
        graphics.setColor((!b && !b2) ? darker : brighter);
        graphics.drawLine(n + n2 - 1, n3 - 1, n + n2 - 1, 1);
        graphics.drawLine(n, n3 - 1, n + n2 - 1, n3 - 1);
        if (ap == this.aa) {
            graphics.setColor((!b && !b2) ? CommonStyle.clrCtrlText : Color.black);
            for (int i = 0; i < 4; ++i) {
                final int n4 = n3 / 2 + (this.x ? (2 * i - 4) : (-(2 * i - 4)));
                graphics.drawLine(n + n2 / 2 - i - 1, n4, n + n2 / 2 + i - 1, n4);
            }
        }
        else {
            ap.a(graphics, n, n2, n3, b, b2);
        }
    }
    
    public void loadDone() {
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final Color b = cv.b;
        b.brighter();
        b.darker();
        final int n = this.y ? this.o.size().width : 2;
        final Dimension size = this.size();
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        graphics.drawLine(0, this.n, size.width - 1, this.n);
        if (this.n > 0) {
            final int size2 = this.e.size();
            int n2 = 1;
            int a = 0;
            int n3 = -1;
            int n4 = size2;
            while (((ap)this.e.elementAt(--n4)).nOver >= 0) {}
            for (int n5 = 0; n5 < size2 && n2 < size.width - 1; ++n5) {
                final ap ap = this.e.elementAt(n5);
                if (ap.nOver < 0 && (n5 == n4 || ap.isFixed())) {
                    int b2;
                    if (n5 == n4) {
                        b2 = size.width - n2 - n + 1;
                    }
                    else {
                        b2 = ap.b();
                        if (n3 == 0) {
                            b2 += a;
                        }
                        if (n2 + b2 >= size.width - n) {
                            b2 = size.width - n2 - n + 1;
                        }
                    }
                    if (b2 != 0) {
                        this.a(graphics, ap, n2, b2, this.n, false, ap == this.v);
                    }
                    n2 += b2;
                    a = ap.a();
                    n3 = b2;
                }
            }
            if (this.y) {
                this.a(graphics, this.aa, false, false);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized boolean o(final int n) {
        return this.d.elementAt(n).e;
    }
    
    public synchronized boolean g(final m m) {
        return this.o(this.b(m));
    }
    
    public synchronized void a(final int n, final boolean e) {
        this.d.elementAt(n).e = e;
        this.g(n);
        if (n == this.m) {
            this.k(n);
        }
    }
    
    public synchronized void setLines(final int n, final int nLines) {
        this.d.elementAt(n).nLines = nLines;
        this.g(n);
    }
    
    public synchronized void setLines(final m m, final int n) {
        final int b = this.b(m);
        if (b >= 0) {
            this.setLines(b, n);
        }
    }
    
    public synchronized void a(final m m, final boolean b) {
        final int b2 = this.b(m);
        if (b2 >= 0) {
            this.a(b2, b);
        }
    }
    
    public synchronized void b(final int n, final boolean b) {
        this.setLines(n, b ? 1 : 0);
    }
    
    public synchronized void b(final m m, final boolean b) {
        this.setLines(m, b ? 1 : 0);
    }
    
    public synchronized void a(final int n, final Color c, final Color d) {
        final av av = this.d.elementAt(n);
        av.c = c;
        av.d = d;
        this.g(n);
    }
    
    public synchronized void a(final m m, final Color color, final Color color2) {
        final int b = this.b(m);
        if (b >= 0) {
            this.a(b, color, color2);
        }
    }
    
    public synchronized void setBackground(final m m, final Color clrBack) {
        final int b;
        if ((b = this.b(m)) >= 0) {
            ((av)this.d.elementAt(b)).clrBack = clrBack;
            this.g(b);
        }
    }
    
    public Dimension preferredSize() {
        return (this.dimSize == null) ? super.preferredSize() : this.dimSize;
    }
    
    public Dimension getPreferredSize() {
        return this.preferredSize();
    }
    
    static Vector a(final ao ao) {
        return ao.d;
    }
    
    static Color l() {
        return ao.a;
    }
    
    public ao(final int n) {
        this.d = new Vector();
        this.e = new Vector();
        this.f = new ar(this, this);
        this.g = new Color(3355545);
        this.h = cv.c;
        this.i = CommonStyle.clrCtrlBack;
        this.j = new Color(9211020);
        this.l = 0;
        this.m = -1;
        this.x = true;
        this.y = false;
        this.z = false;
        (this.aa = new ap(null, null)).c(true);
        this.dimSize = null;
        this.aa.a(com.esial.util.c.a("Click here to reverse the sort order of items in this list."), null);
        if (!ao.c) {
            ao.c = true;
            try {
                this.setCursor(Cursor.getDefaultCursor());
                ao.b = true;
            }
            catch (Throwable t) {
                ao.b = false;
            }
        }
        this.n = n;
        this.setBackground(Color.white);
        this.f.setBackground(Color.white);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.o = new as(1);
        this.l(19);
        gridBagConstraints.insets = new Insets(1 + n, 1, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.f, gridBagConstraints);
        this.add(this.f);
        gridBagConstraints.insets = new Insets(n, 0, 0, 0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.o, gridBagConstraints);
        this.add(this.o);
        this.setFont(dw.c);
    }
    
    public ao() {
        this(18);
    }
    
    static {
        a = new Color(16737894);
        ao.c = false;
    }
}
