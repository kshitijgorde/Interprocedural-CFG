import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_dC implements Cloneable
{
    public int h;
    private int a;
    private String a;
    public Color a;
    public Color b;
    
    public abstract int a();
    
    public void a(final int n) {
    }
    
    public final boolean a(final int n) {
        return (this.a & n) != 0x0;
    }
    
    public final void a(final int n, final boolean b) {
        if (b) {
            this.a |= n;
            return;
        }
        this.a &= ~n;
    }
    
    rp_dC() {
        this.h = -1;
        this.a = 0;
        this.a = null;
        this.a = null;
        this.b = null;
    }
    
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        return clone;
    }
    
    public void a() {
    }
    
    public abstract Point a();
    
    public abstract int b();
    
    public abstract int c();
    
    public Rectangle a(final int n) {
        return null;
    }
    
    public rp_bV[] a() {
        return null;
    }
    
    public boolean a(final Frame frame) {
        return false;
    }
    
    public abstract void a(final rp_eS p0, final rp_eP p1);
    
    public abstract int a(final int p0, final int p1);
    
    public abstract Cursor a(final int p0, final int p1);
    
    public int d() {
        return 0;
    }
    
    public abstract void a(final StringBuffer p0, final rp_eP p1);
    
    public abstract String a();
    
    public abstract void a(final String p0);
    
    public abstract String b();
    
    public abstract void b(final String p0);
    
    public Color a() {
        return this.a;
    }
    
    public Color b() {
        return this.b;
    }
    
    public void a(final rp_N rp_N, final int n, final int n2) {
    }
    
    public rp_dt a(final boolean b) {
        return null;
    }
    
    public void a(final int n, final int n2, final Dimension dimension, final boolean b) {
    }
    
    public abstract void a(final int p0, final int p1);
    
    public abstract void b(final int p0, final int p1);
    
    public abstract void a(final int p0, final int p1, final double p2);
    
    public abstract void a(final int p0, final int p1, final double p2, final boolean p3);
    
    public boolean a(final int n, final int n2) {
        return false;
    }
    
    public int e() {
        return -1;
    }
    
    public void b(final int n) {
    }
    
    public abstract void a(final rp_eg p0);
}
