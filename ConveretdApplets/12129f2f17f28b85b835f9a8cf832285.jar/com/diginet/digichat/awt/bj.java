// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.esial.util.d;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Color;
import com.diginet.digichat.util.s;
import java.awt.Panel;

public class bj extends Panel implements s
{
    private static final Color a;
    private static boolean b;
    private static boolean c;
    private Vector d;
    private Vector e;
    private bk f;
    public Color g;
    public Color h;
    public Color i;
    public Color j;
    protected int k;
    protected int l;
    protected int m;
    protected int n;
    public a3 o;
    private String p;
    private String q;
    private String r;
    private int s;
    private bl t;
    private bl u;
    private bl v;
    protected boolean w;
    protected boolean x;
    protected boolean y;
    boolean z;
    private bl aa;
    
    public void a(final bl v) {
        if (this.y && v != this.v) {
            final ImageObserver v2 = this.v;
            this.v = v;
            this.repaint();
            this.b();
        }
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
                this.m = this.a(g);
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
            final ImageObserver imageObserver = this.e.elementAt((j + index) % size);
            if (imageObserver.d()) {
                a = m.a(i, imageObserver.e());
                if (a != 0) {
                    break;
                }
            }
        }
        return this.x ? a : (-a);
    }
    
    void a(final Vector vector, final int n, final int n2, final bl imageObserver) {
        if (n2 > n) {
            this.e.indexOf(imageObserver);
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
                    final Object element = vector.elementAt(i);
                    final Object element2 = vector.elementAt(n4);
                    vector.setElementAt(element, n4);
                    vector.setElementAt(element2, i);
                }
                if (i <= n4) {
                    ++i;
                    --n4;
                }
            }
            if (n < n4) {
                this.a(vector, n, n4, imageObserver);
            }
            if (i < n2) {
                this.a(vector, i, n2, imageObserver);
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
    
    public bl b(final int n) {
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
            final ImageObserver imageObserver = this.e.elementAt(i);
            final int b = imageObserver.b();
            final int a = imageObserver.a();
            int n6 = n3 + b;
            if (n4 == 0) {
                n6 += n5;
            }
            if (n <= n6 && n >= n3) {
                return imageObserver;
            }
            n3 = n6;
            n4 = b;
            n5 = a;
        }
        return (bl)this.e.elementAt(size2 - 1);
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
    
    public bl e(final int n) {
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
    
    public synchronized void f(final int n) {
        if (n >= 0 && n < this.d.size()) {
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
            final int d = this.d(n);
            final int size = this.e.size();
            final int n2 = this.y ? this.o.size().width : 2;
            final Dimension size2 = this.f.size();
            final Object object = this.d.elementAt(n);
            graphics.setColor(Color.white);
            graphics.drawLine(0, d + this.k - 1, size2.width, d + this.k - 1);
            int n3 = 0;
            for (int i = 0; i < size; ++i) {
                final ImageObserver imageObserver = this.e.elementAt(i);
                int a;
                if (i == size - 1) {
                    a = size2.width - n3;
                }
                else {
                    a = imageObserver.a();
                    if (i == 0) {
                        --a;
                    }
                }
                if (n == this.m) {
                    graphics.setColor(this.g);
                }
                else {
                    graphics.setColor(this.h);
                }
                graphics.fillRect(n3, d, a, this.k - 1);
                imageObserver.a(graphics, object, object.a.e(imageObserver.e()), n3, d, a, this.k, n == this.m);
                n3 += a;
                if (n3 > size2.width - n2) {
                    break;
                }
            }
        }
    }
    
    protected int h(final int n) {
        int n2 = 1;
        for (int i = 0; i < n; ++i) {
            int b = this.e.elementAt(i).b();
            if (i > 0) {
                final ImageObserver imageObserver = this.e.elementAt(i - 1);
                if (imageObserver.b() == 0) {
                    b += imageObserver.a();
                }
            }
            n2 += b;
        }
        return n2;
    }
    
    public synchronized int a(final m m) {
        for (int i = 0; i < this.d.size(); ++i) {
            if (((bq)this.d.elementAt(i)).a.equals(m)) {
                return i;
            }
        }
        return -1;
    }
    
    protected synchronized void i(final int n) {
        final Dimension size = this.f.size();
        this.l -= n;
        final Graphics a = this.f.a(0, 0, size.width, size.height - 1, n);
        this.f.paint(a);
        a.dispose();
    }
    
    protected synchronized void a(final int n, final int n2) {
        final int n3 = (n < 0) ? (-n) : n;
        for (int i = 0; i < n3 / n2; ++i) {
            this.i((n < 0) ? (-n2) : n2);
        }
        if (n3 % n2 != 0) {
            this.i((n < 0) ? (-(n3 % n2)) : (n3 % n2));
        }
    }
    
    public synchronized m j(final int n) {
        return this.d.elementAt(n).a;
    }
    
    public synchronized void k(final int m) {
        if (m >= 0 && m < this.d.size()) {
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
    
    public synchronized void a(final m m, int d) {
        if (this.v != null) {
            d = this.d(m);
        }
        this.d.insertElementAt(new Object(m) {
            m a;
            Font b;
            Color c;
            Color d;
            boolean e;
            boolean f;
            
            {
                this.c = Color.black;
                this.d = Color.white;
                this.e = true;
                this.f = false;
                this.a = a;
            }
        }, d);
        if (this.m >= d) {
            ++this.m;
        }
        final int d2 = this.d(d);
        if (this.isShowing()) {
            if (this.l == this.o.getMaximum() && this.l > 0 && d == this.d.size() - 1) {
                this.a(-this.k, this.k);
            }
            else {
                final Dimension size = this.f.size();
                if (d2 >= 0 && d2 < size.height) {
                    if (this.d.size() > 1) {
                        this.f.a(0, d2 - 1, size.width, size.height - d2, this.k);
                    }
                    this.g(d);
                }
            }
        }
        if (d2 < 0) {
            this.l += this.k;
        }
        this.h();
    }
    
    bl a(final Event event) {
        if (event.y < this.n) {
            int n = 0;
            for (int i = 0; i < this.e.size() - 1; ++i) {
                final ImageObserver imageObserver = this.e.elementAt(i);
                final int n2 = n + imageObserver.a();
                if (event.x > n2 - 4 && event.x < n2 + 4 && imageObserver.c()) {
                    return imageObserver;
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
        Label_0487: {
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
                    final ImageObserver a = this.a(event);
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
                        break Label_0487;
                    }
                    if (this.u != this.aa && this.u != this.v) {
                        this.a(this.u);
                        break Label_0487;
                    }
                    if (this.u == this.aa) {
                        this.x = !this.x;
                        this.c();
                    }
                    final Graphics graphics2 = this.getGraphics();
                    this.a(graphics2, this.u, false, this.u == this.v);
                    graphics2.dispose();
                    break Label_0487;
                }
                case 503: {
                    if (event.target == this && event.y < this.n) {
                        ImageObserver imageObserver;
                        if (this.y && event.x > this.size().width - this.o.size().width) {
                            imageObserver = this.aa;
                        }
                        else {
                            imageObserver = this.b(event.x);
                        }
                        if (imageObserver != null) {
                            this.q = imageObserver.a(this.v == imageObserver);
                            if (this.q == null || !this.q.equals(this.r)) {
                                this.postEvent(new Event(this, 7689, this.q));
                                this.r = this.q;
                            }
                        }
                    }
                    if (bj.b) {
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
                            this.t.a(n3);
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
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    public void l(final int n) {
        this.k = n;
        this.o.setLineIncrement(n);
        this.h();
        this.f.repaint();
    }
    
    public void b(final bl imageObserver) {
        this.a(imageObserver, this.e.size());
    }
    
    public void a(final bl imageObserver, final int n) {
        this.e.insertElementAt(imageObserver, n);
        imageObserver.j = this;
    }
    
    public void b(final m m) {
        this.a(m, this.d.size());
    }
    
    public synchronized void b(final m m, final int n) {
        this.d.setElementAt(new Object(m) {
            m a = a;
            Font b;
            Color c = Color.black;
            Color d = Color.white;
            boolean e = true;
            boolean f = false;
        }, n);
        if (this.v != null) {
            this.a(200L);
        }
        else {
            this.a(200L, n);
        }
    }
    
    public boolean c(final m m) {
        final int a = this.a(m);
        if (a != -1) {
            this.m(a);
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
    
    public void c(final bl imageObserver) {
        final int index = this.e.indexOf(imageObserver);
        if (index >= 0) {
            this.n(index);
        }
    }
    
    public void n(final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 += ((bl)this.e.elementAt(i)).a();
        }
        this.a(n2, 0, this.e.elementAt(n).a(), this.f.size().height);
    }
    
    public void i() {
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
    
    public int d(final m m) {
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
        return (n3 < 0) ? n2 : (n2 + 1);
    }
    
    protected static void a(final Graphics graphics, String string, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        final int n6 = n2 + (n4 + graphics.getFontMetrics().getAscent()) / 2 - 1;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int stringWidth = fontMetrics.stringWidth(string);
        if (stringWidth > n3) {
            final int stringWidth2 = fontMetrics.stringWidth("...");
            final int length = string.length();
            int n7 = 0;
            int n8;
            int i = n8 = length;
            String substring = string;
            while (i > n7) {
                substring = string.substring(0, n8);
                stringWidth = fontMetrics.stringWidth(substring) + stringWidth2;
                if (stringWidth <= n3 && n8 == length) {
                    break;
                }
                if (stringWidth < n3) {
                    n7 = 1 + n8;
                }
                else {
                    i = n8;
                }
                n8 = (n7 + i) / 2;
            }
            string = substring + "...";
        }
        int n9 = 0;
        switch (n5) {
            case 2: {
                n9 = n + n3 - stringWidth;
                break;
            }
            case 1: {
                n9 = n + (n3 - stringWidth) / 2;
                break;
            }
            default: {
                n9 = n;
                break;
            }
        }
        graphics.drawString(string, n9, n6);
        if (b) {
            graphics.setColor(Color.red);
            graphics.drawLine(n9, n2 + n4 / 2, n9 + stringWidth, n2 + n4 / 2);
        }
    }
    
    protected void a(final Graphics graphics, final bl imageObserver, final boolean b, final boolean b2) {
        final Dimension size = this.size();
        final int n = this.y ? this.o.size().width : 2;
        int a;
        int h;
        if (imageObserver == this.aa) {
            a = n - 2;
            h = size.width - n + 1;
        }
        else {
            final int index = this.e.indexOf(imageObserver);
            h = this.h(index);
            if (index == this.e.size() - 1) {
                a = size.width - h - n + 1;
            }
            else {
                a = imageObserver.a();
                if (index > 0) {
                    final ImageObserver imageObserver2 = this.e.elementAt(index - 1);
                    if (imageObserver2.b() == 0) {
                        a += imageObserver2.a();
                    }
                }
            }
        }
        this.a(graphics, imageObserver, h, a, this.n, b, b2);
    }
    
    protected void a(final Graphics graphics, final bl imageObserver, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
        final Color color = (b || b2) ? this.j : this.i;
        final Color brighter = color.brighter();
        final Color darker = color.darker();
        graphics.setColor(color);
        graphics.fillRect(n + 1, 2, n2 - 2, n3 - 3);
        graphics.setColor((b || b2) ? darker : brighter);
        graphics.drawLine(n, 1, n, n3 - 1);
        graphics.drawLine(n, 1, n + n2 - 1, 1);
        graphics.setColor((b || b2) ? brighter : darker);
        graphics.drawLine(n + n2 - 1, n3 - 1, n + n2 - 1, 1);
        graphics.drawLine(n, n3 - 1, n + n2 - 1, n3 - 1);
        if (imageObserver == this.aa) {
            graphics.setColor(Color.black);
            for (int i = 0; i < 4; ++i) {
                final int n4 = n3 / 2 + (this.x ? (2 * i - 4) : (-(2 * i - 4)));
                graphics.drawLine(n + n2 / 2 - i - 1, n4, n + n2 / 2 + i - 1, n4);
            }
        }
        else {
            imageObserver.a(graphics, n, n2, n3, b, b2);
        }
    }
    
    public void paint(final Graphics graphics) {
        final Color b = b6.b;
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
            ImageObserver imageObserver;
            int b2;
            for (int n4 = 0; n4 < size2 && n2 < size.width - 1; n2 += b2, a = imageObserver.a(), n3 = b2, ++n4) {
                imageObserver = this.e.elementAt(n4);
                if (n4 == size2 - 1) {
                    b2 = size.width - n2 - n + 1;
                }
                else {
                    b2 = imageObserver.b();
                    if (n3 == 0) {
                        b2 += a;
                    }
                    if (n2 + b2 >= size.width - n) {
                        b2 = size.width - n2 - n + 1;
                    }
                }
                if (b2 != 0) {
                    this.a(graphics, imageObserver, n2, b2, this.n, false, imageObserver == this.v);
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
    
    public synchronized void a(final int n, final boolean f) {
        this.d.elementAt(n).f = f;
        this.g(n);
    }
    
    public synchronized void a(final m m, final boolean b) {
        final int a = this.a(m);
        if (a >= 0) {
            this.a(a, b);
        }
    }
    
    public synchronized void a(final int n, final Color c, final Color d) {
        final Object object = this.d.elementAt(n);
        object.c = c;
        object.d = d;
        this.g(n);
    }
    
    public synchronized void a(final m m, final Color color, final Color color2) {
        final int a = this.a(m);
        if (a >= 0) {
            this.a(a, color, color2);
        }
    }
    
    public bj(final int n) {
        this.d = new Vector();
        this.e = new Vector();
        this.f = new s(this) {
            private boolean a = false;
            private Event b;
            private String c;
            private String d;
            private int e = -1;
            
            public void invalidate() {
                super.invalidate();
                this.a = false;
            }
            
            public void update(final Graphics graphics) {
                this.paint(graphics);
            }
            
            public void paint(final Graphics graphics) {
                if (bj.this.z) {
                    bj.this.a();
                }
                if (!this.a) {
                    bj.this.h();
                    this.a = true;
                }
                final Rectangle clipRect = graphics.getClipRect();
                final Dimension size = this.size();
                int a = bj.this.a(clipRect.y);
                int a2 = bj.this.a(clipRect.y + clipRect.height);
                final int e = bj.this.e();
                final int d = bj.this.d(e);
                if (a2 >= e) {
                    a2 = e - 1;
                }
                if (a < 0) {
                    a = 0;
                }
                for (int i = a; i <= a2; ++i) {
                    bj.this.a(i, graphics);
                }
                if (d < size.height) {
                    graphics.clearRect(0, d, size.width, size.height - d);
                }
            }
            
            public String a(final Object o) {
                return this.d;
            }
            
            public boolean handleEvent(final Event b) {
                int a = bj.this.a(b.y);
                final int f = bj.this.f();
                final int e = bj.this.e();
                final int c = bj.this.c(b.x);
                final ImageObserver imageObserver = (c < 0) ? null : bj.this.e(c);
                Object arg;
                if (a >= 0 && a < e) {
                    arg = bj.this.d.elementAt(a);
                }
                else {
                    arg = null;
                }
                switch (b.id) {
                    case 503:
                    case 504: {
                        if (arg == null) {
                            this.d = bj.this.d();
                        }
                        else if (imageObserver != null) {
                            this.d = arg.a.f(imageObserver.e());
                        }
                        if (this.d == null || !this.d.equals(this.c)) {
                            this.postEvent(new Event(this, 7689, this.d));
                            this.c = this.d;
                        }
                        break;
                    }
                    case 501: {
                        this.requestFocus();
                        this.b = b;
                        this.b.arg = arg;
                        if (arg != null && arg.e && b.clickCount > 1 && b.clickCount != 3 && a == f && a >= 0) {
                            final Event event = new Event(bj.this, b.when, 1001, b.x, b.y, c, b.modifiers, arg);
                            if (imageObserver != null && !imageObserver.a(event, arg.a)) {
                                this.postEvent(event);
                            }
                        }
                        else {
                            bj.this.k(a);
                        }
                        return true;
                    }
                    case 502: {
                        if (arg != null && arg.e && this.b != null && arg == this.b.arg) {
                            final Event event2 = new Event(bj.this, b.when, 6301, b.x, b.y, c, b.modifiers, arg);
                            if (imageObserver != null && !imageObserver.a(event2, arg.a)) {
                                this.postEvent(event2);
                            }
                        }
                        if (this.e >= 0) {
                            final int width = this.size().width;
                            final int d = bj.this.d(this.e);
                            final Graphics graphics = this.getGraphics();
                            graphics.setColor((this.e == bj.this.f()) ? bj.this.g : bj.this.h);
                            graphics.drawLine(0, d, width, d);
                            graphics.setColor(Color.white);
                            graphics.drawLine(0, d - 1, width, d - 1);
                            this.e = -1;
                            graphics.dispose();
                        }
                        break;
                    }
                    case 506: {
                        if (f == -1 || f == a) {
                            return true;
                        }
                        if (a < 0) {
                            a = 0;
                        }
                        else if (a >= e) {
                            a = e - 1;
                        }
                        bj.this.k(a);
                        return true;
                    }
                }
                return super.handleEvent(b);
            }
        };
        this.g = new Color(3355545);
        this.h = b6.c;
        this.i = b6.b;
        this.j = new Color(9211020);
        this.l = 0;
        this.m = -1;
        this.x = true;
        this.y = false;
        this.z = false;
        (this.aa = new ImageObserver(null, null) {
            protected boolean a = false;
            protected boolean b = false;
            protected int c = 50;
            protected int d = 50;
            protected int e;
            protected String f = f;
            protected String g;
            protected String h;
            protected Object i = i;
            bj j;
            
            public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
                if ((n & 0x20) != 0x0) {
                    this.j.c(this);
                    return false;
                }
                return true;
            }
            
            public final void a(final String h, final String g) {
                this.g = g;
                this.h = h;
            }
            
            public String a(final boolean b) {
                return b ? this.g : this.h;
            }
            
            public final int a() {
                return this.d;
            }
            
            public final int b() {
                return this.c;
            }
            
            public final void a(int n) {
                if (n < 15) {
                    n = 15;
                }
                this.d = n;
                this.c = n;
                if (this.j != null) {
                    this.j.repaint();
                    this.j.i();
                }
            }
            
            public final void b(final int c) {
                this.c = c;
                if (this.j != null) {
                    this.j.repaint();
                }
            }
            
            public final void c(final int d) {
                this.d = d;
                if (this.j != null) {
                    this.j.i();
                }
            }
            
            public final boolean c() {
                return this.a;
            }
            
            public final boolean d() {
                return this.b;
            }
            
            public final void b(final boolean b) {
                this.b = b;
            }
            
            public final String e() {
                return this.f;
            }
            
            public void a(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final boolean b2) {
                if (this.i instanceof Image) {
                    final Image image = (Image)this.i;
                    final int height = image.getHeight(this.j);
                    final int width = image.getWidth(this.j);
                    if (height <= 0 || height <= 0) {
                        graphics.drawImage(image, -1, -1, 1, 1, this.j);
                    }
                    else {
                        graphics.drawImage(image, n + (n2 - width) / 2, (n3 - height) / 2, this.j);
                    }
                }
                else if (this.i != null) {
                    graphics.setColor(b ? Color.white : this.j.getForeground());
                    bj.a(graphics, this.i.toString(), n + 7, 0, n2 - 12, n3, 0, false);
                }
            }
            
            public boolean a(final Event event, final m m) {
                return false;
            }
            
            void a(final Graphics graphics, final bq object, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
                if (o instanceof Boolean) {
                    this.a(graphics, object, (boolean)o, n, n2, n3, n4, b);
                }
                else if (o instanceof Image) {
                    this.a(graphics, object, (Image)o, n, n2, n3, n4, b);
                }
                else if (o != null) {
                    this.a(graphics, object, o.toString(), n, n2, n3, n4, b);
                }
            }
            
            void a(final Graphics graphics, final bq object, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
                if (b) {
                    final int n5 = n2 + n4 / 2 + 3;
                    final int n6 = n + n3 / 2 - 1;
                    if (object.e) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(bj.a);
                    }
                    graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
                    graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
                    graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
                    graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
                }
            }
            
            void a(final Graphics graphics, final bq object, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
                if (object.b == null) {
                    graphics.setFont(this.j.getFont());
                }
                else {
                    graphics.setFont(object.b);
                }
                if (b) {
                    graphics.setColor(object.e ? object.d : Color.lightGray);
                }
                else {
                    graphics.setColor(object.e ? object.c : Color.gray);
                }
                graphics.getFontMetrics().stringWidth(s);
                bj.a(graphics, s, n + 5, n2, n3 - 8, n4, this.e, object.f);
            }
            
            void a(final Graphics graphics, final bq object, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
                final int height = image.getHeight(this);
                final int width = image.getWidth(this);
                if (height == 0 || height == 0) {
                    graphics.drawImage(image, -1, -1, 1, 1, this);
                }
                else {
                    graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
                }
            }
        }).b(true);
        this.aa.a(com.esial.util.d.a("Click here to reverse the sort order of items in this list."), null);
        if (!bj.c) {
            bj.c = true;
            try {
                this.setCursor(Cursor.getDefaultCursor());
                bj.b = true;
            }
            catch (Throwable t) {
                bj.b = false;
            }
        }
        this.n = n;
        this.setBackground(Color.white);
        this.f.setBackground(Color.white);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.o = new a3(1);
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
        this.setFont(com.diginet.digichat.awt.p.c);
    }
    
    public bj() {
        this(18);
    }
    
    static {
        a = new Color(16737894);
        bj.c = false;
    }
}
