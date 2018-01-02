// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import com.screencastomatic.play.c.b;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Image;
import java.awt.Font;
import java.awt.Composite;

public class s implements l
{
    private final Composite a;
    private final Composite b;
    private static final Font c;
    private Image d;
    private Image e;
    private Image f;
    private String g;
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;
    private Point l;
    private Dimension m;
    private r n;
    
    public s(final Point point, final r n, final String g, final Graphics2D graphics2D, final boolean i) {
        this.a = AlphaComposite.getInstance(3, 0.3f);
        this.b = AlphaComposite.getInstance(3, 0.5f);
        this.n = n;
        this.i = i;
        this.g = g;
        this.e = com.screencastomatic.play.c.b.a("checkbox_checked.gif");
        this.d = com.screencastomatic.play.c.b.a("checkbox_empty.gif");
        this.f = (this.i ? this.e : this.d);
        this.h = graphics2D.getFontMetrics(s.c).stringWidth(this.g);
        this.m = new Dimension(this.d.getWidth(null) + this.h + 3, this.d.getHeight(null));
        this.l = new Point(point.x - this.m.width, point.y);
    }
    
    public boolean d() {
        return this.i;
    }
    
    public void a(final boolean i) {
        this.i = i;
        this.f = (this.i ? this.e : this.d);
        this.n.a();
    }
    
    public boolean e() {
        return this.j;
    }
    
    public void b(final boolean j) {
        this.j = j;
    }
    
    public Rectangle f() {
        return new Rectangle(this.l, this.m);
    }
    
    public void a() {
        if (!this.j) {
            this.a(!this.i);
        }
    }
    
    public void b() {
        this.k = true;
        this.n.a();
    }
    
    public void c() {
        this.k = false;
        this.n.a();
    }
    
    public void a(final Graphics2D graphics2D) {
        final Font font = graphics2D.getFont();
        final Composite composite = graphics2D.getComposite();
        final Color color = graphics2D.getColor();
        graphics2D.setFont(s.c);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(this.g, this.l.x, this.l.y + 13);
        graphics2D.drawImage(this.f, this.l.x + this.h + 3, this.l.y, null);
        if (this.k || this.j) {
            graphics2D.setColor(Color.BLACK);
            graphics2D.setComposite(this.j ? this.b : this.a);
            graphics2D.fillRect(this.l.x + this.h + 3 + 2, this.l.y + 2, this.f.getWidth(null) - 4, this.f.getHeight(null) - 4);
        }
        graphics2D.setColor(color);
        graphics2D.setFont(font);
        graphics2D.setComposite(composite);
    }
    
    static {
        c = new Font("SansSerif", 0, 14);
    }
}
