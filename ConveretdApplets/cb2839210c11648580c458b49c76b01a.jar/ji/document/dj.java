// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import ji.v1event.cx;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import ji.util.e;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import ji.util.d;
import java.awt.Component;
import ji.v1event.aq;
import java.awt.LayoutManager;
import ji.awt.c;
import ji.awt.dk;
import ji.v1base.jiPanel;

public class dj extends jiPanel
{
    private String a;
    private dk b;
    private c c;
    private boolean d;
    private ad e;
    
    public dj(final String a, final ad e) {
        super(a);
        this.a = null;
        this.d = false;
        this.e = null;
        this.e = e;
        try {
            this.a = a;
            this.setLayout(null);
            super.setBorderStyle(0);
            (this.b = new dk(a, e)).a(e);
            this.add(this.b);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public void a(final String s, final boolean b) {
        if (this.b != null) {
            this.b.a(s, b);
        }
    }
    
    public void a() {
        if (this.b != null) {
            this.b.a();
        }
    }
    
    public final void a(final boolean d) {
        this.d = d;
    }
    
    public final void releaseResources() {
        this.e = null;
        try {
            if (this.b != null) {
                this.remove(this.b);
                this.b.releaseResources();
                this.b = null;
            }
        }
        catch (Exception ex) {}
        super.releaseResources();
    }
    
    public final void a(final ActionListener actionListener) {
        if (this.c != null && this.c.a(actionListener)) {
            this.c.b(actionListener);
        }
    }
    
    public final void b(final ActionListener actionListener) {
        if (this.c == null) {
            this.c = new c("jiImageButton1", 2);
        }
        if (!this.c.a(actionListener)) {
            this.c.c(actionListener);
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        try {
            this.a(graphics);
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        try {
            super.paint(graphics);
            if (!this.isSwing()) {
                this.a(graphics);
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final Graphics graphics) {
        try {
            final Dimension size = this.getSize();
            if (size.width < 10) {
                return;
            }
            Color color;
            if (ji.util.e.g != null) {
                color = ji.util.e.g;
            }
            else {
                color = SystemColor.controlShadow;
            }
            Color color2;
            if (ji.util.e.h != null) {
                color2 = ji.util.e.h;
            }
            else {
                color2 = SystemColor.controlLtHighlight;
            }
            graphics.setColor(color2);
            graphics.drawLine(0, 0, 0, size.height);
            graphics.drawLine(0, 0, size.width, 0);
            graphics.setColor(color);
            graphics.drawLine(size.width - 1, 0, size.width - 1, size.height);
            graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
            ji.util.e.a0();
            ji.util.e.a3();
            graphics.setColor(this.getBackground());
            graphics.fillRect(1, 1, size.width - 2, size.height - 2);
        }
        catch (Exception ex) {}
    }
    
    public final void setBounds(final Rectangle bounds) {
        super.setBounds(bounds);
        this.e();
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.e();
    }
    
    private final boolean d() {
        try {
            final Dimension size = this.getSize();
            return size.width > size.height;
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    private final void e() {
        try {
            final Dimension size = this.getSize();
            if (this.d()) {
                final int n = 5;
                final int n2 = 5;
                if (this.b != null) {
                    ji.util.e.a(this.b, n, n2, size.width - 5 - 6, this.b.getPreferredSize().height);
                }
            }
            else {
                final int n3 = 1;
                final int n4 = 1;
                if (this.b != null) {
                    ji.util.e.a(this.b, n4, n3, size.width - 2, this.b.getPreferredSize().height);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public int b() {
        int n = 0;
        if (this.b != null) {
            n += this.b.getPreferredSize().height;
            if (n > 0) {
                n += 5;
                n += 5;
            }
        }
        return n;
    }
    
    public void a(final double n) {
        if (this.b != null) {
            this.b.a((int)n);
        }
    }
    
    public final void b(final boolean b) {
        if (this.b != null) {
            this.b.a(b);
        }
    }
    
    public final void c(final boolean b) {
        if (this.b != null) {
            this.b.b(b);
        }
    }
    
    public final void d(final boolean b) {
        if (this.b != null) {
            this.b.c(b);
        }
    }
    
    public final void a(final cx cx) {
        if (this.b != null) {
            this.b.a(cx);
        }
    }
    
    public final void b(final cx cx) {
        if (this.b != null) {
            this.b.b(cx);
        }
    }
    
    public final boolean c() {
        return this.b != null && this.b.b();
    }
}
