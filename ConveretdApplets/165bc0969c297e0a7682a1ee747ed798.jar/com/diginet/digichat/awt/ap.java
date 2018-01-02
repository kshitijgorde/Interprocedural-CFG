// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class ap implements ImageObserver
{
    public static final int UP_LEFT = 0;
    public static final int UP_MIDDLE = 1;
    public static final int UP_RIGHT = 2;
    public static final int UP_OFFSET = 3;
    public static final int MIDDLE_LEFT = 4;
    public static final int CENTER = 5;
    public static final int MIDDLE_RIGHT = 6;
    public static final int MIDDLE_OFFSET = 7;
    public static final int DOWN_LEFT = 8;
    public static final int DOWN_MIDDLE = 9;
    public static final int DOWN_RIGHT = 10;
    public static final int DOWN_OFFSET = 11;
    protected boolean a;
    protected boolean b;
    protected boolean fFixed;
    protected int c;
    protected int d;
    protected int e;
    protected int nOver;
    protected int nAlig;
    protected String f;
    protected String g;
    protected String h;
    protected Object i;
    ao j;
    
    public boolean isFixed() {
        return this.fFixed;
    }
    
    public void a(final int e) {
        this.e = e;
        if (this.j != null) {
            this.j.c(this);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x30) == 0x0) {
            return true;
        }
        this.j.c(this);
        return (n & 0x20) == 0x0 && CommonStyle.fAnim;
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
    
    public final void b(int n) {
        if (n < 15) {
            n = 15;
        }
        this.d = n;
        this.c = n;
        if (this.j != null) {
            this.j.repaint();
            this.j.k();
        }
    }
    
    public final void c(final int c) {
        this.c = c;
        if (this.j != null) {
            this.j.repaint();
        }
    }
    
    public final void d(final int d) {
        this.d = d;
        if (this.j != null) {
            this.j.k();
        }
    }
    
    public final boolean c() {
        return this.a;
    }
    
    public final void b(final boolean a) {
        this.a = a;
    }
    
    public final boolean d() {
        return this.b;
    }
    
    public final void c(final boolean b) {
        this.b = b;
    }
    
    public final String e() {
        return this.f;
    }
    
    public final Object f() {
        return this.i;
    }
    
    public void setOver(final int nOver, final int nAlig) {
        this.nOver = nOver;
        this.nAlig = nAlig;
        if (this.j != null) {
            this.j.repaint();
            this.j.k();
        }
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
            ao.a(graphics, this.i.toString(), n + 7, 0, n2 - 12, n3, 0, 0);
        }
    }
    
    public boolean a(final Event event, final m m) {
        return false;
    }
    
    void a(final Graphics graphics, final av av, final Object o, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (o instanceof Boolean) {
            this.a(graphics, av, (boolean)o, n, n2, n3, n4, b);
        }
        else if (o instanceof Image) {
            this.a(graphics, av, (Image)o, n, n2, n3, n4, b);
        }
        else if (o != null) {
            this.a(graphics, av, o.toString(), n, n2, n3, n4, b);
        }
    }
    
    void a(final Graphics graphics, final av av, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
        if (b) {
            final int n5 = n2 + n4 / 2 + 3;
            final int n6 = n + n3 / 2 - 1;
            if (av.e) {
                graphics.setColor(Color.red);
            }
            else {
                graphics.setColor(ao.l());
            }
            graphics.drawLine(n6, n5, n6 - 4, n5 - 4);
            graphics.drawLine(n6, n5 - 1, n6 - 3, n5 - 4);
            graphics.drawLine(n6, n5, n6 + 6, n5 - 6);
            graphics.drawLine(n6, n5 - 1, n6 + 6, n5 - 7);
        }
    }
    
    void a(final Graphics graphics, final av av, final String s, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (av.b == null) {
            graphics.setFont(this.j.getFont());
        }
        else {
            graphics.setFont(av.b);
        }
        if (b) {
            graphics.setColor(av.e ? av.d : Color.lightGray);
        }
        else {
            graphics.setColor(av.e ? av.c : Color.gray);
        }
        graphics.getFontMetrics().stringWidth(s);
        ao.a(graphics, s, n + 5, n2, n3 - 8, n4, this.e, av.nLines);
    }
    
    void a(final Graphics graphics, final av av, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int height = image.getHeight(this);
        final int width = image.getWidth(this);
        if (height == 0 || height == 0) {
            graphics.drawImage(image, -1, -1, 1, 1, this);
        }
        else {
            graphics.drawImage(image, n + (n3 - width) / 2, n2 + (n4 - height) / 2 - 1, this);
        }
    }
    
    public ap(final String s) {
        this(s, s);
    }
    
    public ap(final Object i, final String f, final boolean fFixed) {
        this.a = false;
        this.b = false;
        this.c = 50;
        this.d = 50;
        this.f = f;
        this.i = i;
        this.nOver = -1;
        this.fFixed = fFixed;
    }
    
    public ap(final Object o, final String s) {
        this(o, s, true);
    }
}
