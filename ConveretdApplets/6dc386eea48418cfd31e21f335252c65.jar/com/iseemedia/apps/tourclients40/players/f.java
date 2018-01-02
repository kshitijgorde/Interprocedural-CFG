// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.players;

import java.net.MalformedURLException;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import com.iseemedia.apps.tourclients40.toolbar.g;
import com.iseemedia.apps.tourclients40.iip.d;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.applet.AppletContext;
import java.net.URL;
import com.iseemedia.apps.tourclients40.toolbar.a;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Dimension;
import com.iseemedia.apps.tourclients40.iip.b;

public final class f extends h implements b
{
    private String a;
    private String b;
    private boolean c;
    private String d;
    private com.iseemedia.apps.tourclients40.iip.f e;
    private Dimension f;
    private Image g;
    private com.iseemedia.image.b h;
    private Rectangle i;
    private Rectangle j;
    private Color k;
    private com.iseemedia.apps.tourclients40.util.b W;
    private boolean X;
    private boolean Y;
    private int Z;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private float af;
    private float ag;
    private float ah;
    private float ai;
    private float aj;
    private float ak;
    private float al;
    private float am;
    private boolean an;
    private double ao;
    private double ap;
    private boolean aq;
    private double ar;
    private double as;
    private Rectangle at;
    private Rectangle au;
    private int av;
    private int aw;
    private float ax;
    private a ay;
    
    public f(final URL url, final AppletContext o, final Dimension dimension, final Frame q, final boolean aq, final String s, final com.iseemedia.apps.tourclients40.applets.b n, final a ay) {
        this.a = null;
        this.b = null;
        this.c = true;
        this.d = null;
        this.e = null;
        this.f = new Dimension();
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.W = null;
        this.Y = false;
        this.af = 0.0f;
        this.ag = 1.0f;
        this.ah = 0.0f;
        this.ai = 1.0f;
        this.aj = 0.0f;
        this.ak = 1.0f;
        this.al = 0.0f;
        this.am = 1.0f;
        this.an = false;
        this.ao = 0.0;
        this.ap = 0.0;
        this.ar = 1.0;
        this.as = 1.0;
        this.av = 100;
        this.aw = 100;
        this.aq = aq;
        super.o = o;
        super.n = n;
        this.ax = -1.0f;
        this.setLayout(null);
        this.au = new Rectangle(0, 0, 0, 0);
        this.at = new Rectangle(0, 0, 0, 0);
        super.q = q;
        (this.ay = ay).a();
        if (com.iseemedia.apps.tourclients40.players.h.l == 1) {
            this.c(0);
        }
        else {
            this.c(2);
        }
        this.reshape(0, 0, dimension.width, dimension.height);
        this.W = new com.iseemedia.apps.tourclients40.util.b(this, super.o, !this.aq, ay);
        this.X = true;
        super.E = false;
        final int n2 = -1000;
        this.aa = n2;
        this.Z = n2;
        this.ae = n2;
        this.ad = n2;
        this.ac = n2;
        this.ab = n2;
        this.af = 0.0f;
        this.ag = 1.0f;
        this.ah = 0.0f;
        this.ai = 1.0f;
        this.ar = 1.0;
        this.av = this.size().width;
        this.aw = this.size().height;
    }
    
    protected final void a() {
        if (this.i == null) {
            if (com.iseemedia.apps.tourclients40.players.h.l == 1) {
                this.i = new Rectangle(0, 0, this.bounds().width, this.bounds().height - this.ay.c);
            }
            else {
                this.i = new Rectangle(0, 0, this.bounds().width, this.bounds().height);
            }
        }
        if (com.iseemedia.apps.tourclients40.resource.a.b && !this.s()) {
            this.a("You need the MRJ2.2", "Get it from following URL");
            this.e = null;
            return;
        }
        if (this.d == null) {
            this.a("Could not load the FlashPix image.", "Verify that the URL is correct in the html file.");
            return;
        }
        this.e = null;
        try {
            this.e = new com.iseemedia.apps.tourclients40.iip.f(this.d, this, this, 0, 0);
        }
        catch (d d) {
            this.a("Image Exception ... ", d.getMessage());
            this.e = null;
        }
        catch (Exception ex) {
            this.e = null;
            this.a("Could not load the FlashPix image.", "Verify that the URL is correct in the html file.");
        }
        if (super.y == null) {
            super.y = new g(this, this.bounds().height, super.o, false);
        }
        if (com.iseemedia.apps.tourclients40.players.h.l == 1) {
            this.c(0);
        }
        else {
            this.c(2);
        }
        this.requestFocus();
    }
    
    protected final void c() {
        super.o = null;
        if (this.e != null) {
            this.e.a(true);
        }
        this.e = null;
        if (super.y != null) {
            super.y.a();
            super.y = null;
        }
        if (this.W != null) {
            this.W = null;
        }
        if (this.g != null) {
            this.g.flush();
        }
        if (this.h != null) {
            this.h.b();
        }
        this.removeAll();
        this.g = null;
        this.h = null;
    }
    
    public final float d() {
        return this.ax;
    }
    
    protected final boolean e() {
        if (super.K) {
            this.m();
            super.K = false;
        }
        if (super.D && this.p()) {
            this.o();
            super.n.e();
        }
        if (this.X || super.E || this.Y) {
            this.X = false;
            this.g();
            return true;
        }
        return false;
    }
    
    public final void a(final int n) {
        if (n == 100) {
            this.W.b();
        }
        this.X = true;
    }
    
    public final void a(final String d, final boolean z) {
        super.z = z;
        this.d = d;
    }
    
    private void a(final String a, final String b) {
        if (this.a != null) {
            return;
        }
        this.a = a;
        this.b = b;
        this.X = true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.c && super.z) {
            graphics.setColor(this.u());
            graphics.fillRect(0, 0, this.au.width, this.au.height);
            this.a(graphics);
        }
        if (this.a != null) {
            this.g();
        }
        this.X = true;
    }
    
    private void c(final Graphics graphics) {
        graphics.setColor(this.u());
        graphics.fillRect(0, 0, this.size().width, this.at.y);
        graphics.fillRect(0, 0, this.at.x, this.size().height);
        graphics.fillRect(this.at.x + this.at.width, 0, this.size().width - (this.at.x + this.at.width), this.size().height);
        graphics.fillRect(0, this.at.y + this.at.height, this.size().width, this.size().height - (this.at.y + this.at.height));
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void a(final Color k) {
        this.k = k;
    }
    
    private synchronized void g() {
        if (this.i == null) {
            return;
        }
        if (this.a != null) {
            final Graphics graphics;
            final FontMetrics fontMetrics = (graphics = this.getGraphics()).getFontMetrics();
            graphics.setColor(this.k);
            graphics.drawString(this.a, this.i.x + (this.i.width >> 1) - (fontMetrics.stringWidth(this.a) >> 1), this.i.y + (this.i.height >> 1));
            if (this.b != null) {
                graphics.drawString(this.b, this.i.x + (this.i.width >> 1) - (fontMetrics.stringWidth(this.b) >> 1), this.i.y + (this.i.height >> 1) + fontMetrics.getHeight());
            }
            graphics.dispose();
            return;
        }
        if (this.j()) {
            if (this.g == null) {
                this.g = this.createImage(this.av, this.aw);
                final Graphics graphics2;
                (graphics2 = this.g.getGraphics()).setColor(this.k);
                graphics2.fillRect(0, 0, this.size().width, this.size().height);
                this.a(graphics2);
                graphics2.dispose();
            }
            if (this.h != null) {
                this.h.b();
                this.h = null;
            }
            this.h = new com.iseemedia.image.b(this.at.width, this.at.height);
            if (super.m && super.M && super.L) {
                super.L = false;
                new Thread(new j(this), "Player").start();
            }
            Label_0450: {
                try {
                    this.e.q = 2;
                    this.e.a();
                    if (this.c) {
                        this.W.a();
                    }
                    this.e.a(this.au, this.at, this.h, !super.E && !this.Y, this.W, this.c);
                    if (this.c) {
                        this.W.b();
                    }
                    super.m = false;
                    super.A = 0;
                    this.h.a();
                    break Label_0450;
                }
                catch (d d) {
                    this.X = true;
                    this.W.b();
                    return;
                }
                return;
            }
            final Graphics graphics3;
            (graphics3 = this.g.getGraphics()).setColor(this.k);
            if (this.c) {
                this.c = false;
            }
            else {
                this.c(graphics3);
                graphics3.drawImage(this.h.a, this.at.x, this.at.y, this.at.width, this.at.height, null);
            }
            if (this.j != null) {
                graphics3.setColor(this.k);
                graphics3.setXORMode(Color.white);
                graphics3.drawRect(this.j.x, this.j.y, this.j.width - 1, this.j.height - 1);
                graphics3.drawRect(this.j.x + 1, this.j.y + 1, this.j.width - 3, this.j.height - 3);
                graphics3.setPaintMode();
            }
            graphics3.dispose();
            final Graphics graphics4;
            (graphics4 = this.getGraphics()).drawImage(this.g, 0, 0, null);
            this.ax = 0.0f;
            if (super.s) {
                super.n.b();
                super.s = false;
            }
            graphics4.dispose();
            if (this.an) {
                this.i();
            }
        }
    }
    
    private void i() {
        this.an = false;
        this.af = this.aj;
        this.ag = this.ak;
        this.ah = this.al;
        this.ai = this.am;
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4, final boolean b) {
        if (b) {
            this.b(n, n2, n3, n4);
            return;
        }
        this.an = true;
        this.aj = this.af;
        this.ak = this.ag;
        this.al = this.ah;
        this.am = this.ai;
        this.b(n, n2, n3, n4);
    }
    
    private void b(final float af, final float ag, final float ah, final float ai) {
        this.af = af;
        this.ag = ag;
        this.ah = ah;
        this.ai = ai;
    }
    
    private boolean j() {
        final int n = this.ad - this.ab;
        final int n2 = this.ae - this.ac;
        if (this.e == null || !this.e.a(this.f) || this.ai - this.ah <= 0.0f || this.ag - this.af <= 0.0f) {
            return false;
        }
        if (super.x) {
            super.x = false;
            this.c(this.af, this.ag, this.ah, this.ai);
        }
        switch (super.r) {
            case 0: {
                if (Math.abs(this.ad - this.Z) >= 3 && Math.abs(this.ae - this.aa) >= 3 && super.E) {
                    if (this.j == null) {
                        this.j = new Rectangle();
                    }
                    if (this.ad < this.Z) {
                        this.j.x = this.ad;
                    }
                    else {
                        this.j.x = this.Z;
                    }
                    if (this.ae < this.aa) {
                        this.j.y = this.ae;
                    }
                    else {
                        this.j.y = this.aa;
                    }
                    this.j.width = Math.abs(this.ad - this.Z);
                    this.j.height = Math.abs(this.ae - this.aa);
                    this.j = this.j.intersection(this.at);
                    break;
                }
                this.j = null;
                break;
            }
            case 1: {
                this.j = null;
                this.ab = this.ad;
                this.ac = this.ae;
                break;
            }
            case 2: {
                this.j = null;
                this.ab = this.ad;
                this.ac = this.ae;
                this.a(n, n2);
                break;
            }
        }
        this.x();
        return true;
    }
    
    private void b(final int n, final int n2) {
        final double ar = this.ar;
        if (this.ar >= 1.0) {
            this.ar = 1.0;
            if (this.W != null) {
                this.W.c();
            }
            return;
        }
        int n3;
        for (n3 = 1; this.ar < 1.0 / Math.pow(2.0, n3); ++n3) {}
        this.ar = 1.0 / Math.pow(2.0, n3 - 1);
        this.ao *= ar / this.ar;
        this.ap *= ar / this.ar;
        this.ao -= ((n - this.au.x) * this.ar / ar + this.au.x - (this.i.x + this.i.width / 2)) / (this.ar * this.f.width);
        this.ap -= ((n2 - this.au.y) * this.ar / ar + this.au.y - (this.i.y + this.i.height / 2)) / (this.ar * this.f.height);
    }
    
    private void c(final int n, final int n2) {
        final double ar = this.ar;
        this.ar /= 2.0;
        if (this.ar < this.as) {
            this.c(this.af, this.ag, this.ah, this.ai);
            return;
        }
        if (this.ar * this.f.width < this.i.width / 1.95 && this.ar * this.f.height < this.i.height / 1.95) {
            this.ar = Math.min(this.i.width / (1.95 * this.f.width), this.i.height / (1.95 * this.f.height));
            if (this.ar > 1.0) {
                this.ar = 1.0;
            }
        }
        this.ao *= ar / this.ar;
        this.ap *= ar / this.ar;
        this.ao -= ((n - this.au.x) * this.ar / ar + this.au.x - (this.i.x + this.i.width / 2)) / (this.ar * this.f.width);
        this.ap -= ((n2 - this.au.y) * this.ar / ar + this.au.y - (this.i.y + this.i.height / 2)) / (this.ar * this.f.height);
    }
    
    public final void a(final int n, final int n2) {
        this.ao += n / (this.ar * this.f.width);
        this.ap += n2 / (this.ar * this.f.height);
        this.x();
        this.f();
    }
    
    private void x() {
        final Dimension dimension = new Dimension();
        if (!this.e.a(dimension)) {
            return;
        }
        final double n = 1.0E-4;
        final double n2 = 1.0E-4;
        final double n3 = n + this.ao * this.ar * dimension.width;
        double n4;
        if (this.i.height > (int)Math.ceil(this.ar * dimension.height)) {
            n4 = this.i.height - this.ar * dimension.height;
        }
        else {
            n4 = n2 + this.ap * this.ar * dimension.height;
        }
        this.au.reshape((int)Math.round(n3), (int)Math.round(n4), (int)Math.round(dimension.width * this.ar), (int)Math.round(dimension.height * this.ar));
        this.at = this.au.intersection(this.i);
    }
    
    private void c(float n, float n2, float n3, float n4) {
        final Dimension dimension = new Dimension();
        if (this.e == null || !this.e.a(dimension)) {
            return;
        }
        final float n5 = this.i.width / this.i.height;
        final double ar = this.ar;
        int n8;
        int n9;
        if ((n2 - n) * dimension.width / ((n4 - n3) * dimension.height) > n5) {
            this.ar = this.i.width / ((n2 - n) * dimension.width);
            if (this.ar > 32.0 && this.aq) {
                this.ar = ar;
                return;
            }
            if (this.ar > 1.0 && !this.aq) {
                final float n6 = (float)((n2 - n) / 2.0f * this.ar);
                n = (n2 = (n2 + n) / 2.0f);
                n -= n6;
                n = (float)Math.max(0.0, n);
                n2 += n6;
                Math.min(1.0, n2);
                final float n7 = (float)((n4 - n3) / 2.0f * this.ar);
                n3 = (n4 = (n4 + n3) / 2.0f);
                n3 -= n7;
                n3 = (float)Math.max(0.0, n3);
                n4 += n7;
                n4 = (float)Math.min(1.0, n4);
                this.ar = 1.0;
                if (this.W != null) {
                    this.W.c();
                }
            }
            if (this.i.width > (int)Math.ceil(this.ar * dimension.width)) {
                n8 = (int)(this.i.x - this.size().width / 2 + this.i.width / 2 + (this.i.width / (2.0 * this.ar * dimension.width) - 0.5) * (this.ar * dimension.width));
            }
            else {
                n8 = (int)(this.i.x - this.ar * n * dimension.width);
            }
            if (this.i.height > (int)Math.ceil(this.ar * dimension.height)) {
                n9 = (int)(this.i.y - this.size().height / 2 + this.i.height / 2 + (this.i.height / (2.0 * this.ar * dimension.height) - 0.5) * (this.ar * dimension.height));
            }
            else {
                n9 = (int)(this.i.y - (n4 + n3 - this.i.height / (this.ar * dimension.height)) / 2.0 * this.ar * dimension.height);
            }
        }
        else {
            this.ar = this.i.height / ((n4 - n3) * dimension.height);
            if (this.ar > 32.0 && this.aq) {
                this.ar = ar;
                return;
            }
            if (this.ar > 1.0 && !this.aq) {
                final float n10 = (float)((n2 - n) / 2.0f * this.ar);
                n = (n2 = (n2 + n) / 2.0f);
                n -= n10;
                n = (float)Math.max(0.0, n);
                n2 += n10;
                n2 = (float)Math.min(1.0, n2);
                final float n11 = (float)((n4 - n3) / 2.0f * this.ar);
                n3 = (n4 = (n4 + n3) / 2.0f);
                n3 -= n11;
                n3 = (float)Math.max(0.0, n3);
                n4 += n11;
                Math.min(1.0, n4);
                this.ar = 1.0;
                if (this.W != null) {
                    this.W.c();
                }
            }
            if (this.i.width > (int)Math.ceil(this.ar * dimension.width)) {
                n8 = (int)(this.i.x - this.size().width / 2 + this.i.width / 2 + (this.size().width / (2.0 * this.ar * dimension.width) - 0.5) * (this.ar * dimension.width));
            }
            else {
                n8 = (int)(this.i.x - (n2 + n - this.i.width / (this.ar * dimension.width)) / 2.0 * this.ar * dimension.width);
            }
            if (this.i.height > (int)Math.ceil(this.ar * dimension.height)) {
                final int n12 = (int)(this.i.y - this.size().height / 2 + this.i.height / 2 + (this.i.height / (2.0 * this.ar * dimension.height) - 0.5) * (this.ar * dimension.height));
                n9 = n12 + (n12 - this.i.y);
            }
            else {
                n9 = (int)(this.i.y - this.ar * n3 * dimension.height);
            }
        }
        this.ao = 0.0;
        this.ap = 0.0;
        this.x();
        this.ao = (n8 - this.au.x) / (this.ar * dimension.width);
        this.ap = (n9 - this.au.y) / (this.ar * dimension.height);
        this.f();
        this.as = this.ar;
        this.x();
    }
    
    public final void k() {
        if (this.at.inside(this.i.x + this.i.width / 2, this.i.y + this.i.height / 2)) {
            this.b(this.i.x + this.i.width / 2, this.i.y + this.i.height / 2);
        }
        else {
            this.b(this.at.x + this.at.width / 2, this.at.y + this.at.height / 2);
        }
        this.f();
    }
    
    public final void l() {
        if (this.at.inside(this.i.x + this.i.width / 2, this.i.y + this.i.height / 2)) {
            this.c(this.i.x + this.i.width / 2, this.i.y + this.i.height / 2);
        }
        else {
            this.c(this.at.x + this.at.width / 2, this.at.y + this.at.height / 2);
        }
        this.f();
    }
    
    public final void m() {
        super.x = true;
        super.n.b();
        if (super.y != null) {
            super.y.c();
        }
        this.X = true;
        super.A = 0;
    }
    
    public final void a(final float n, final boolean b) {
    }
    
    public final void a(final float n) {
    }
    
    public final void a(final float[] array) {
    }
    
    public final void processKeyEvent(final KeyEvent keyEvent) {
        Label_0109: {
            switch (keyEvent.getID()) {
                case 401: {
                    switch (keyEvent.getKeyCode()) {
                        case 65:
                        case 90: {
                            this.X = true;
                            break;
                        }
                    }
                    break;
                }
                case 402: {
                    switch (keyEvent.getKeyCode()) {
                        case 65:
                        case 90: {
                            this.X = true;
                            break Label_0109;
                        }
                    }
                    break;
                }
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    public final void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            super.F = true;
        }
        else if (super.F) {
            super.F = false;
        }
        else {
            switch (mouseEvent.getID()) {
                case 501: {
                    if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
                        super.E = true;
                        final int x = mouseEvent.getX();
                        this.Z = x;
                        this.ad = x;
                        this.ab = x;
                        final int y = mouseEvent.getY();
                        this.aa = y;
                        this.ae = y;
                        this.ac = y;
                        break;
                    }
                    break;
                }
                case 502: {
                    if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
                        super.E = false;
                        this.ad = mouseEvent.getX();
                        this.ae = mouseEvent.getY();
                        if (Math.abs(this.ad - this.Z) < 3 && Math.abs(this.ae - this.aa) < 3) {
                            if (super.r == 0) {
                                this.b(this.ad, this.ae);
                                this.f();
                            }
                            else if (super.r == 1) {
                                this.c(this.ad, this.ae);
                                this.f();
                            }
                        }
                        else if (super.r == 0 && this.j != null) {
                            this.c((this.j.x - this.au.x) / this.au.width, (this.j.x + this.j.width - this.au.x) / this.au.width, (this.j.y - this.au.y) / this.au.height, (this.j.y + this.j.height - this.au.y) / this.au.height);
                        }
                    }
                    this.X = true;
                    break;
                }
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public final void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                if ((mouseEvent.getModifiers() & 0x4) != 0x4) {
                    this.ad = mouseEvent.getX();
                }
                this.ae = mouseEvent.getY();
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    public final void n() {
        try {
            super.o.showDocument(new URL(com.iseemedia.apps.tourclients40.players.h.G), "iseemedia");
        }
        catch (MalformedURLException ex) {}
    }
    
    public final void f() {
        Rectangle rectangle;
        if (com.iseemedia.apps.tourclients40.players.h.l == 1) {
            rectangle = new Rectangle(this.bounds().width, this.bounds().height - this.ay.c);
        }
        else {
            rectangle = new Rectangle(this.bounds().width, this.bounds().height);
        }
        if (rectangle.width > 0 && rectangle.height > 0) {
            final double n = rectangle.width / (this.f.width * this.ar);
            final double n2 = rectangle.height / (this.f.height * this.ar);
            final double ao = n / rectangle.width;
            final double ap = n2 / rectangle.height;
            if (n >= 1.0) {
                this.ao = -(1.0 - n) / 2.0;
            }
            else if (this.ao > ao) {
                this.ao = ao;
            }
            else if (this.ao < -(1.0 + ao - n)) {
                this.ao = -(1.0 + ao - n);
            }
            if (n2 >= 1.0) {
                this.ap = -(1.0 - n2) / 2.0;
                return;
            }
            if (this.ap > ap) {
                this.ap = ap;
                return;
            }
            if (this.ap < -(1.0 + ap - n2)) {
                this.ap = -(1.0 + ap - n2);
            }
        }
    }
}
