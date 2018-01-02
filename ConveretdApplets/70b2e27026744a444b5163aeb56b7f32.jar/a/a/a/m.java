// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import b.a.d.b;
import b.a.d.d;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Font;

public class m extends b.a.e.m implements Runnable
{
    protected int c;
    protected Thread d;
    protected int e;
    protected boolean f;
    protected int g;
    protected int h;
    protected String i;
    protected int j;
    
    public m() {
        this.c = 0;
        this.e = 55;
        this.f = false;
        this.g = 0;
        this.h = 2;
        this.i = "";
        this.j = 0;
        this.e();
    }
    
    private void e() {
        this.setFont(new Font("Dialog", 0, 11));
        this.a(new Insets(0, 2, 0, 2));
    }
    
    public synchronized void a(String i) {
        if (i == null) {
            i = "";
        }
        if (!this.i.equals(i)) {
            this.i = i;
            this.g = 0;
            this.repaint();
            if (i.length() == 0 && this.d != null) {
                this.d.interrupt();
            }
        }
    }
    
    protected synchronized void a(final Graphics graphics) {
        if (this.i.length() == 0) {
            return;
        }
        b.a.d.b.a(graphics, b.a.d.d.a());
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.c = this.a().width;
        this.j = fontMetrics.stringWidth(this.i);
        graphics.drawString(this.i, this.c - this.g, fontMetrics.getAscent());
        if (this.d == null) {
            (this.d = new Thread(this, "AlmanacMarquee")).start();
        }
    }
    
    public Dimension c() {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return super.c();
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        return new Dimension(fontMetrics.stringWidth(this.i), fontMetrics.getHeight());
    }
    
    public void run() {
        while (this.d != null && this.isShowing()) {
            if (!b.a.d.d.a(this.e)) {
                return;
            }
            if (this.f) {
                continue;
            }
            synchronized (this) {
                this.g += this.h;
                if (this.g > this.j + this.c) {
                    this.g = 0;
                }
            }
            this.repaint();
        }
        synchronized (this) {
            this.d = null;
        }
    }
    
    protected void b(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
}
