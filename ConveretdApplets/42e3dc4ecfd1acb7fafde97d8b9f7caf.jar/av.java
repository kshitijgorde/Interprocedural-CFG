import java.util.Enumeration;
import java.awt.PopupMenu;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class av
{
    private int a;
    private Vector b;
    private Color c;
    private Color d;
    private boolean e;
    private String f;
    private String g;
    private int h;
    private int i;
    private int j;
    
    public Color h() {
        return this.d;
    }
    
    public void a(final Color d) {
        this.d = d;
    }
    
    public Color i() {
        return this.c;
    }
    
    public void b(final Color c) {
        this.c = c;
    }
    
    public int j() {
        return this.j;
    }
    
    public void b(final int j) {
        this.j = j;
    }
    
    public int k() {
        return this.h;
    }
    
    public int l() {
        return this.i;
    }
    
    public void b(final int h, final int i) {
        this.h = h;
        this.i = i;
    }
    
    public void m() {
        this.o();
    }
    
    public boolean d(final int n, final int n2) {
        return n > this.k() & n <= this.k() + this.g().width & (n2 > this.l() & n2 <= this.l() + this.g().height);
    }
    
    public abstract void b(final Graphics p0, final Component p1, final int p2, final int p3);
    
    public abstract Dimension g();
    
    public abstract PopupMenu a(final int p0, final int p1, final Component p2);
    
    public abstract void c(final int p0, final int p1);
    
    public abstract String b(final int p0, final int p1, final Component p2);
    
    public boolean n() {
        return this.e;
    }
    
    public void b(final boolean e) {
        this.e = e;
    }
    
    public void a(final ac ac) {
        if (this.b == null) {
            this.b = new Vector(0);
        }
        this.b.addElement(ac);
    }
    
    public void o() {
        if (this.b != null) {
            this.b.removeAllElements();
            this.b = null;
        }
    }
    
    public void p() {
        if (this.b != null) {
            final Enumeration<ac> elements = this.b.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().c();
            }
        }
    }
    
    public int q() {
        return this.a;
    }
    
    public void c(final int a) {
        this.a = a;
    }
    
    public String r() {
        return this.f;
    }
    
    public void a(final String f) {
        this.f = f;
    }
    
    public String s() {
        return this.g;
    }
    
    public String toString() {
        if (this.s() != null) {
            return this.s();
        }
        return this.r();
    }
    
    public void b(final String g) {
        this.g = g;
    }
    
    public av() {
        this.c = dj.w;
        this.d = dj.w;
        this.e = false;
    }
}
