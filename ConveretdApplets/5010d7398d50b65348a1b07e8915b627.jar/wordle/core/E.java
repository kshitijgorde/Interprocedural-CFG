// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.util.TimerTask;
import java.awt.geom.AffineTransform;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Color;
import wordle.core.a.c;
import java.awt.Font;

public final class E implements j
{
    private static final Font a;
    private final n b;
    private final w c;
    private volatile c d;
    private volatile String e;
    private volatile int f;
    private volatile int g;
    private volatile int h;
    private volatile boolean i;
    private static final Color j;
    private static final Color k;
    
    static {
        a = new Font("SansSerif", 0, 11);
        j = new Color(1.0f, 1.0f, 1.0f, 0.5f);
        k = new Color(0.0f, 0.0f, 1.0f, 0.3f);
    }
    
    public E(final n b) {
        this.c = new w(16);
        this.d = null;
        this.e = "";
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = true;
        this.b = b;
    }
    
    public final void a(final Graphics2D graphics2D) {
        if (this.d == null) {
            return;
        }
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final int n = this.b.getWidth() - 2;
        final AffineTransform transform = graphics2D.getTransform();
        try {
            graphics2D.translate(2, 2);
            graphics2D.setColor(E.j);
            graphics2D.fillRoundRect(0, 0, n - 2, 24, 5, 5);
            graphics2D.setColor(Color.LIGHT_GRAY);
            graphics2D.drawRoundRect(0, 0, n - 2, 24, 5, 5);
            this.c.a(graphics2D, 3, 4, this.b);
            graphics2D.setFont(E.a);
            if (!this.i) {
                graphics2D.setColor(E.k);
                graphics2D.fillRoundRect(21, 3, (int)((n - 21 - 10) * ((1.0 * this.h - this.f) / (1.0 * this.g - this.f))), 19, 5, 5);
            }
            graphics2D.setColor(Color.BLACK);
            if (this.e != null) {
                graphics2D.drawString(this.e, 25, 16);
            }
        }
        finally {
            graphics2D.setTransform(transform);
        }
        graphics2D.setTransform(transform);
    }
    
    private void d() {
        this.b.repaint(0, 0, this.b.getWidth(), 24);
    }
    
    public final void a() {
        this.e = null;
        this.d();
    }
    
    public final synchronized void a(final String e) {
        this.a(true);
        this.e = e;
        this.d();
        this.b();
    }
    
    public final synchronized void b() {
        if (this.d != null) {
            return;
        }
        (this.d = new c("WordleProgressBar animator")).scheduleAtFixedRate(new k(this), 0L, 140L);
    }
    
    public final synchronized void c() {
        if (this.d == null) {
            return;
        }
        this.d.cancel();
        this.d = null;
    }
    
    public final synchronized void a(final boolean i) {
        this.i = i;
        this.d();
    }
    
    public final synchronized void a(final int n, final int g) {
        this.f = 0;
        this.g = g;
        this.h = Math.max(0, Math.min(g, this.h));
        this.d();
    }
    
    public final synchronized void a(final int n) {
        this.h = Math.max(this.f, Math.min(this.g, n));
        this.d();
    }
}
