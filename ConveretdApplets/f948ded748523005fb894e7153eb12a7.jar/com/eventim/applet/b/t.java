// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import javax.swing.Icon;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Set;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.eventim.applet.EventimApplet;
import com.eventim.applet.a.g;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

public abstract class t extends JLabel
{
    private BufferedImage b;
    private int c;
    private Dimension d;
    private g e;
    protected EventimApplet a;
    private double[] f;
    private int g;
    
    public t(final g e, final Dimension d, final EventimApplet a) {
        this.g = 0;
        this.f = new double[] { 1.0, 1.0, 1.0 };
        this.e = e;
        this.d = d;
        this.a = a;
        final s s = new s(this);
        this.addMouseMotionListener(s);
        this.addMouseListener(s);
        this.b(this.f[this.g]);
        this.setHorizontalAlignment(0);
        this.setVerticalAlignment(0);
    }
    
    private BufferedImage a(final double n) {
        try {
            final BufferedImage bufferedImage;
            final Graphics2D graphics = (bufferedImage = new BufferedImage((int)Math.ceil(this.d.width * n), (int)Math.ceil(this.d.height * n), 2)).createGraphics();
            this.e.a(n);
            this.e.a(graphics);
            return bufferedImage;
        }
        catch (Throwable t) {
            this.a.a(0);
            return new BufferedImage(1, 1, 2);
        }
    }
    
    public final BufferedImage b() {
        return this.b;
    }
    
    protected final Graphics2D c() {
        final Graphics2D graphics;
        (graphics = this.b.createGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        final double f = this.f();
        graphics.scale(f, f);
        return graphics;
    }
    
    public final int d() {
        return this.c;
    }
    
    public final BufferedImage a(final Dimension dimension) {
        return this.a(Math.min(dimension.width / this.d.width, dimension.height / this.d.height));
    }
    
    public final g e() {
        return this.e;
    }
    
    public final double f() {
        return this.f[this.g];
    }
    
    public final int g() {
        return this.g;
    }
    
    public final void a(final int n, final boolean b) {
        this.e.a(n, -1);
        if (b) {
            this.b(this.f[this.g]);
        }
    }
    
    public final boolean h() {
        return this.g == this.c;
    }
    
    public final void b(final int n, final boolean b) {
        this.e.b(n, -1);
        if (b) {
            this.b(this.f[this.g]);
        }
    }
    
    public final void a(final Set set, final boolean b) {
        this.e.a(set, null);
        if (b) {
            this.b(this.f[this.g]);
        }
    }
    
    public final void a(final int n) {
        this.g = Math.max(0, n);
        this.g = Math.min(this.g, this.c);
        this.b(this.f[this.g]);
    }
    
    public final void a(final int n, final Set set, final int n2) {
        this.e.b(n, -1);
        this.e.a(set, null);
        this.e.a(n2, -1);
        this.b = null;
        this.b = this.a(this.f[this.g]);
        super.setIcon(new ImageIcon(this.b));
    }
    
    private void b(final double n) {
        this.b = null;
        this.b = this.a(n);
        super.setIcon(new ImageIcon(this.b));
    }
    
    public final void b(final Dimension dimension) {
        if (dimension.width >= this.d.width && dimension.height >= this.d.height) {
            this.c = 0;
            this.f[0] = 1.0;
            return;
        }
        final double min;
        if ((min = Math.min((dimension.width - 12) / this.d.width, (dimension.height - 12) / this.d.height)) > 0.75) {
            this.c = 1;
            this.f[0] = 1.0;
            this.f[1] = min;
            return;
        }
        this.c = 2;
        this.f[0] = 1.0;
        this.f[1] = min + (1.0 - min) / 2.0;
        this.f[2] = min;
    }
    
    public final void a(final boolean b) {
        this.a(this.g += (b ? -1 : 1));
    }
}
