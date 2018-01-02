// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Cursor;
import com.diginet.digichat.awt.a1;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import com.diginet.digichat.awt.a3;
import java.awt.Image;
import java.awt.Panel;

public abstract class az extends Panel
{
    public static int a;
    public Image b;
    private ay[] c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    protected a3 i;
    protected a0 j;
    protected ay k;
    protected i l;
    protected boolean m;
    protected boolean n;
    
    public int a() {
        return this.i.size().width;
    }
    
    public synchronized void a(final ay ay) {
        try {
            int b = 0;
            int f = -1;
            for (int i = 0; i < this.g; ++i) {
                f += this.a(i).b;
            }
            if (f != this.f) {
                this.f = f;
            }
            if (this.g == this.c.length) {
                b = this.a(0).b;
                this.f -= b;
                final int n = b - this.d;
                if (n > 0) {
                    if (this.l.bo == 4) {
                        this.b(-n);
                    }
                    else {
                        for (int j = 0; j < n / 4; ++j) {
                            this.b(-4);
                        }
                        if (n % 4 != 0) {
                            this.b(-(n % 4));
                        }
                    }
                    this.d = 0;
                    b = 0;
                }
                this.d -= b;
                ++this.h;
            }
            else {
                ++this.g;
            }
            this.a(this.g - 1, ay);
            this.a(ay, this.j.size().width - 12 - az.a, this.getFontMetrics(this.l.ca.b()));
            final int height = this.j.size().height;
            this.f += ay.b;
            if (this.isShowing() && this.d == this.i.getMaximum() - b) {
                final int n2 = this.f - this.d - height - 1;
                if (n2 < ay.b) {
                    final Graphics graphics = this.j.getGraphics();
                    if (graphics != null) {
                        this.a(ay, this.f - ay.b - this.d + 1, graphics);
                    }
                }
                if (n2 > 0) {
                    if (this.l.bo == 4) {
                        this.b(-n2);
                    }
                    else {
                        final int n3 = (int)Math.ceil(n2 / Math.pow(3.0, 4 - this.l.bo));
                        for (int n4 = n2 / n3, k = 0; k < n4; ++k) {
                            if (k != 0) {
                                Thread.sleep(5L);
                            }
                            this.b(-n3);
                        }
                        if (n2 % n3 != 0) {
                            this.b(-(n2 % n3));
                        }
                    }
                }
                this.i.setValues(this.f, height, 0, this.f - 1);
                this.d = this.i.getValue();
            }
            else {
                this.i.setValues(this.d, height, 0, this.f - 1);
            }
        }
        catch (Exception ex) {}
    }
    
    public abstract void a(final ay p0, final int p1, final FontMetrics p2);
    
    protected abstract void a(final ay p0, final int p1, final Graphics p2);
    
    public synchronized ay a(final int n) {
        return this.c[(n + this.h) % this.c.length];
    }
    
    public int b() {
        return this.g;
    }
    
    public boolean handleEvent(final Event event) {
        Label_0224: {
            switch (event.id) {
                case 403: {
                    switch (event.key) {
                        case 1004: {
                            this.i.setValue(this.d - this.i.getLineIncrement());
                            break Label_0224;
                        }
                        case 1005: {
                            this.i.setValue(this.d + this.i.getLineIncrement());
                            break Label_0224;
                        }
                        case 1002: {
                            this.i.setValue(this.d - this.i.getPageIncrement());
                            break Label_0224;
                        }
                        case 1003: {
                            this.i.setValue(this.d + this.i.getPageIncrement());
                            break Label_0224;
                        }
                        case 1000: {
                            this.i.setValue(0);
                            break Label_0224;
                        }
                        case 1001: {
                            this.i.setValue(this.i.getMaximum());
                            break Label_0224;
                        }
                    }
                    break;
                }
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    final int n = this.d - this.i.getValue();
                    if (n != 0) {
                        this.b(n);
                    }
                    return true;
                }
                default: {
                    return super.handleEvent(event);
                }
            }
        }
    }
    
    public synchronized ay a(final Event event) {
        if (event.x >= az.a) {
            int n = event.y + this.d;
            final int n2 = event.x - az.a;
            for (int i = 0; i < this.g; ++i) {
                final ay a = this.a(i);
                if (n < a.b) {
                    a.e = -1;
                    final FontMetrics fontMetrics = this.getFontMetrics(this.l.ca.b());
                    final int height = fontMetrics.getHeight();
                    final int n3 = n - (3 + fontMetrics.getAscent());
                    final int n4 = n3 / height;
                    if (n3 > 0 && n4 < a.c) {
                        int n5;
                        for (n5 = a.l[n4]; n5 < a.l[n4 + 1] && a.i.charAt(n5) == ' '; ++n5) {}
                        final String substring = a.i.substring(n5, a.l[n4 + 1]);
                        int n6 = 0;
                        int j = substring.length();
                        int n7 = (n6 + j) / 2;
                        if (n2 > fontMetrics.stringWidth(substring.substring(0, j))) {
                            return a;
                        }
                        while (j > n6 + 1) {
                            if (fontMetrics.stringWidth(substring.substring(0, n7)) < n2) {
                                n6 = n7;
                            }
                            else {
                                j = n7;
                            }
                            n7 = (n6 + j) / 2;
                        }
                        a.e = n5 + n7;
                    }
                    return a;
                }
                n -= a.b;
            }
        }
        return null;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.l.ca.v == 0) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, size.width, size.height - 1);
        }
    }
    
    public synchronized void c() {
        int height = this.j.size().height;
        final boolean b = this.i.getValue() >= this.i.getMaximum();
        if (height < 0) {
            height = 0;
        }
        this.f = -1;
        if (this.g == 0) {
            this.d = 0;
            this.i.setValues(0, height, 0, 0);
        }
        else {
            final int n = this.j.size().width - 6 - az.a;
            final FontMetrics fontMetrics = this.getFontMetrics(this.l.ca.b());
            for (int i = 0; i < this.g; ++i) {
                final ay a = this.a(i);
                this.a(a, n, fontMetrics);
                this.f += a.b;
            }
            this.i.setValues(b ? this.f : this.d, height, 0, this.f);
            this.d = this.i.getValue();
        }
    }
    
    public void d() {
        this.m = false;
        this.repaint();
        this.j.repaint();
    }
    
    public void resize(final int n, final int n2) {
        this.j.resize(n - this.i.size().width, n2);
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    private final void a(final int n, final ay ay) {
        this.c[(n + this.h) % this.c.length] = ay;
    }
    
    public synchronized void b(final int n) {
        final Dimension size = this.j.size();
        this.d -= n;
        this.e -= n;
        this.j.a(this.j.getGraphics(), 0, size.height);
    }
    
    public synchronized void e() {
        final int maximum = this.i.getMaximum();
        final int n = maximum - this.d;
        this.i.setValue(maximum);
        if (n > 0) {
            if (this.l.bo == 4) {
                this.b(n);
            }
            else {
                final int n2 = (int)Math.ceil(n / Math.log(n) / 3.0);
                for (int i = 0; i < n / n2; ++i) {
                    this.b(-n2);
                }
                if (n % n2 > 0) {
                    this.b(-(n % n2));
                }
            }
        }
        this.d = maximum;
    }
    
    public az(final i l, final boolean n) {
        this.b = null;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.j = new a1() {
            private int a = 0;
            private boolean b = true;
            private boolean c = false;
            private Image d = null;
            private Graphics e = null;
            private int f = 0;
            private int g = 0;
            
            public boolean handleEvent(final Event event) {
                switch (event.id) {
                    case 503: {
                        if (this.b) {
                            boolean b = false;
                            final ay a = az.this.a(event);
                            if (a != null && a.e != -1) {
                                for (int i = 0; i < a.d; ++i) {
                                    if (a.e >= a.m[2 * i] && a.e <= a.m[2 * i + 1]) {
                                        b = true;
                                        break;
                                    }
                                }
                            }
                            try {
                                if (b && !this.c) {
                                    this.c = true;
                                    this.setCursor(Cursor.getPredefinedCursor(12));
                                }
                                else if (!b) {
                                    this.c = false;
                                    this.setCursor(Cursor.getPredefinedCursor(0));
                                }
                            }
                            catch (Throwable t) {
                                this.b = false;
                            }
                            break;
                        }
                        break;
                    }
                    case 501: {
                        this.requestFocus();
                        this.a = event.y;
                        final ay a2 = az.this.a(event);
                        if (a2 != null && a2.e != -1) {
                            for (int j = 0; j < a2.d; ++j) {
                                if (a2.e >= a2.m[2 * j] && a2.e <= a2.m[2 * j + 1]) {
                                    String s = a2.i.substring(a2.m[2 * j], a2.m[2 * j + 1]);
                                    final String lowerCase = s.toLowerCase();
                                    final int index = s.indexOf(64);
                                    final int index2 = s.indexOf("://");
                                    if (index > 0 && index2 == -1 && lowerCase.indexOf("mailto:") == -1) {
                                        s = "mailto:" + s;
                                    }
                                    try {
                                        this.postEvent(new Event(az.this, event.when, 1001, event.x, event.y, event.key, event.modifiers, new URL(s)));
                                    }
                                    catch (Exception ex) {}
                                    break;
                                }
                            }
                        }
                        az.this.k = a2;
                        return true;
                    }
                    case 502: {
                        final ay a3 = az.this.a(event);
                        if (a3 != null && a3 == az.this.k) {
                            this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, a3));
                            az.this.k = null;
                        }
                        return true;
                    }
                    case 506: {
                        az.this.k = null;
                        az.this.i.setValue(az.this.d - (event.y - this.a));
                        final int n = az.this.d - az.this.i.getValue();
                        if (n != 0) {
                            az.this.b(n);
                        }
                        this.a = event.y;
                        return true;
                    }
                    case 1001: {
                        if (!(event.arg instanceof ay)) {
                            break;
                        }
                        final ay ay = (ay)event.arg;
                        if (ay.g != -1) {
                            az.this.l.d(ay.g);
                            break;
                        }
                        break;
                    }
                }
                return super.handleEvent(event);
            }
            
            public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
                if ((n & 0x20) != 0x0) {
                    this.repaint(6, 0, 24, this.size().height);
                    return false;
                }
                return true;
            }
            
            public synchronized void invalidate() {
                super.invalidate();
                az.this.m = false;
            }
            
            public void paint(final Graphics graphics) {
                if (!az.this.m) {
                    az.this.c();
                    az.this.m = true;
                }
                this.a(graphics, 0, this.size().height);
            }
            
            public void a(final Graphics graphics, final int n, final int n2) {
                if (this.d == null || this.size().height != this.f || this.size().width != this.g) {
                    this.f = this.size().height;
                    this.g = this.size().width;
                    this.d = this.createImage(this.g, this.f);
                    this.e = this.d.getGraphics();
                }
                if (az.this.n) {
                    if (az.this.b == null) {
                        az.this.b = az.this.l.ca.h();
                    }
                    if (az.this.b != null) {
                        final Dimension size = this.size();
                        final Insets insets = az.this.insets();
                        final int n3 = size.width - insets.right - insets.left + 30;
                        final int n4 = size.height - insets.bottom - insets.top + 30;
                        int width = az.this.b.getWidth(null);
                        int height = az.this.b.getHeight(null);
                        if (az.this.l.ca.k()) {
                            height = height * n3 / width;
                            width = n3;
                        }
                        int i;
                        for (i = az.this.e; i >= height; i -= height) {}
                        this.e.clearRect(0, -i, width, height - i);
                        this.e.drawImage(az.this.b, 0, -i, width, height, null);
                        this.e.drawImage(az.this.b, 0, height - i, width, height, null);
                        for (int j = 0; j <= n3 / width; ++j) {
                            for (int k = 0; k <= n4 / height; ++k) {
                                if (j + k > 0) {
                                    this.e.copyArea(0, 0, width, height, j * width, k * height);
                                }
                            }
                        }
                    }
                }
                int n5 = -az.this.d;
                for (int l = 0; l < az.this.b(); ++l) {
                    final ay a = az.this.a(l);
                    if (n5 + a.b >= n) {
                        az.this.a(a, n5, this.e);
                    }
                    if ((n5 += a.b) >= n + n2) {
                        break;
                    }
                }
                graphics.drawImage(this.d, 0, 0, null);
            }
        };
        this.k = null;
        this.l = l;
        this.c = new ay[75];
        this.n = n;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 1, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        this.j.setBackground(Color.white);
        layout.setConstraints(this.j, gridBagConstraints);
        this.add(this.j);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        (this.i = new a3(1)).setLineIncrement(10);
        layout.setConstraints(this.i, gridBagConstraints);
        this.add(this.i);
    }
}
