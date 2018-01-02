// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.gui;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Component;
import java.lang.reflect.Method;
import java.awt.Color;
import java.awt.Rectangle;

public abstract class E
{
    protected Object A;
    public Rectangle D;
    protected Color F;
    protected Color E;
    public boolean C;
    public boolean B;
    protected static final Method G;
    static /* synthetic */ Class class$java$awt$Graphics;
    
    private static final Method C() {
        try {
            return Class.forName("com.cc.gui.JavaVersion").getDeclaredMethod("setAntialias", (E.class$java$awt$Graphics == null) ? (E.class$java$awt$Graphics = class$("java.awt.Graphics")) : E.class$java$awt$Graphics);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public E(final Component a) {
        this.D = new Rectangle(0, 0, 0, 0);
        this.F = Color.black;
        this.E = Color.white;
        this.C = false;
        this.B = false;
        this.A = a;
        this.E = a.getBackground();
        this.F = a.getForeground();
    }
    
    public E(final E a) {
        this.D = new Rectangle(0, 0, 0, 0);
        this.F = Color.black;
        this.E = Color.white;
        this.C = false;
        this.B = false;
        this.A = a;
        this.E = a.A();
        this.F = a.D();
    }
    
    public static void A(final Graphics graphics) {
        if (E.G == null) {
            return;
        }
        try {
            E.G.invoke(null, graphics);
        }
        catch (Exception ex) {}
    }
    
    protected void E() {
        this.A(0, 0, this.D.width, this.D.height);
    }
    
    protected void A(final int n, final int n2, final int n3, final int n4) {
        final int n5 = this.D.x + n;
        final int n6 = this.D.y + n2;
        this.C = false;
        if (this.A instanceof Component) {
            ((Component)this.A).repaint(n5, n6, n3 + 2, n4 + 2);
        }
        else {
            ((E)this.A).A(n5, n6, n3, n4);
        }
    }
    
    public void B(final Graphics graphics) {
        if (this.C && this.B) {
            this.B = false;
            return;
        }
        this.C = true;
        this.B = false;
        A(graphics);
        this.C(graphics);
    }
    
    protected abstract void C(final Graphics p0);
    
    public Color A() {
        return this.E;
    }
    
    public void B(final Color e) {
        if (!A(this.E, e)) {
            this.E = e;
            this.E();
        }
    }
    
    public Rectangle B() {
        return new Rectangle(this.D);
    }
    
    public final void B(final int n, final int n2, final int n3, final int n4) {
        this.A(new Rectangle(n, n2, n3, n4));
    }
    
    public void A(final Rectangle d) {
        this.D = d;
    }
    
    public Color D() {
        return this.F;
    }
    
    public void A(final Color f) {
        if (!A(this.F, f)) {
            this.F = f;
            this.E();
        }
    }
    
    private static boolean A(final Object o, final Object o2) {
        if (o == null) {
            return o2 == null;
        }
        return o.equals(o2);
    }
    
    public Point A(final Point point) {
        return new Point(point.x - this.D.x, point.y - this.D.y);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        G = C();
    }
}
