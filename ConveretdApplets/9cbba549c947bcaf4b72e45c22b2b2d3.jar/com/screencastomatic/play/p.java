// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.geom.Point2D;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import com.screencastomatic.play.stream.CapturedMouse$ButtonAction;
import java.awt.Rectangle;
import com.screencastomatic.play.c.b;
import java.awt.Point;
import java.awt.image.BufferedImage;
import com.screencastomatic.play.stream.j;
import java.awt.Color;
import java.awt.Image;

public class p
{
    private Image a;
    private Color b;
    private j c;
    private static final BufferedImage d;
    private static final BufferedImage[] e;
    private static final BufferedImage[] f;
    private static final int g;
    private long h;
    private Point i;
    private BufferedImage[] j;
    private boolean k;
    private boolean l;
    private boolean m;
    private Point n;
    private boolean o;
    
    public p() {
        this.b = Color.BLUE;
        this.h = -1L;
        this.k = true;
        this.l = true;
        this.m = true;
        this.a = com.screencastomatic.play.c.b.a("mousepointer.gif");
        this.a = com.screencastomatic.play.c.b.a(this.a, this.b);
    }
    
    public void a(final boolean k) {
        this.k = k;
    }
    
    public void b(final boolean l) {
        this.l = l;
    }
    
    public void c(final boolean m) {
        this.m = m;
    }
    
    public Rectangle a(final com.screencastomatic.play.stream.b b, final long n) {
        if (b != null && b.b != -1 && this.l) {
            Point i = null;
            if (b.d != CapturedMouse$ButtonAction.a) {
                i = new Point(b.b, b.c);
            }
            else if (this.i != null && n - this.h < 750L) {
                i = this.i;
            }
            if (i != null) {
                final int width = p.e[p.e.length - 1].getWidth();
                return new Rectangle(i.x - width / 2, i.y - width / 2, width, width);
            }
        }
        return null;
    }
    
    public Rectangle a(final com.screencastomatic.play.stream.b b) {
        if (b == null || b.b == -1) {
            return null;
        }
        if (this.m && b.f > 0.0f) {
            final int width = p.d.getWidth();
            return new Rectangle(b.b - width / 2, b.c - width / 2, width, width);
        }
        final j e = b.e;
        if (e == null) {
            return new Rectangle(b.b, b.c, this.a.getWidth(null), this.a.getHeight(null));
        }
        return e.a(b.b, b.c);
    }
    
    public void a(final Graphics2D graphics2D, final com.screencastomatic.play.stream.b b) {
        if (b == null) {
            return;
        }
        final Point i = new Point(b.b, b.c);
        this.c = b.e;
        boolean o = false;
        if (b.b != -1) {
            final Object renderingHint = graphics2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (this.l) {
                final long currentTimeMillis = System.currentTimeMillis();
                if (b.d != CapturedMouse$ButtonAction.a) {
                    this.i = i;
                    this.h = System.currentTimeMillis();
                    this.j = ((b.d == CapturedMouse$ButtonAction.b) ? p.e : p.f);
                }
                else if (this.i != null && currentTimeMillis - this.h >= 750L) {
                    this.i = null;
                }
                if (this.i != null) {
                    final BufferedImage bufferedImage = this.j[(int)(currentTimeMillis - this.h) / p.g];
                    final int width = bufferedImage.getWidth();
                    graphics2D.drawImage(bufferedImage, this.i.x - width / 2, this.i.y - width / 2, null);
                }
            }
            if (this.m && b.f > 0.0f && (this.i == null || this.i.distance(this.n) > 30.0) && (this.o || this.n == null || this.n.distance(i) > 5.0)) {
                final AlphaComposite instance = AlphaComposite.getInstance(3, b.f);
                final Composite composite = graphics2D.getComposite();
                graphics2D.setComposite(instance);
                final int width2 = p.d.getWidth();
                graphics2D.drawImage(p.d, b.b - width2 / 2, b.c - width2 / 2, null);
                graphics2D.setComposite(composite);
                o = (b.f > 0.0f);
            }
            if (this.k) {
                if (this.c == null) {
                    graphics2D.drawImage(this.a, b.b, b.c, null);
                }
                else {
                    this.c.a(graphics2D, b.b, b.c);
                }
            }
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, renderingHint);
        }
        this.n = new Point(b.b, b.c);
        this.o = o;
    }
    
    static {
        d = b.b("mouse_highlight.png");
        e = new BufferedImage[] { b.b("mouse_left_0.png"), b.b("mouse_left_1.png"), b.b("mouse_left_2.png"), b.b("mouse_left_3.png"), b.b("mouse_left_4.png") };
        f = new BufferedImage[] { b.b("mouse_right_0.png"), b.b("mouse_right_1.png"), b.b("mouse_right_2.png"), b.b("mouse_right_3.png"), b.b("mouse_right_4.png") };
        g = 750 / p.e.length;
    }
}
