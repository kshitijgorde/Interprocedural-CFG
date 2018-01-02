import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_cf
{
    protected int a;
    Object a;
    public Rectangle a;
    
    public rp_cf(final Object a) {
        this.a = 0;
        this.a = null;
        this.a = a;
    }
    
    public final boolean a(final int n) {
        return (this.a & 0x1) != 0x0;
    }
    
    public final void a(final int n, final boolean b) {
        this.a |= 0x1;
    }
    
    public abstract void a(final Rectangle p0);
    
    public boolean a(final rp_eV rp_eV) {
        return false;
    }
    
    public Rectangle a() {
        return null;
    }
    
    public int a() {
        return 0;
    }
    
    public rp_dC a(final int n) {
        return null;
    }
    
    public String a(final Point point) {
        return null;
    }
    
    public void a(final int n, final int n2) {
    }
    
    public abstract void a(final Graphics p0);
    
    public abstract boolean a(final Point p0, final boolean p1);
    
    public abstract boolean c(final MouseEvent p0);
    
    public abstract boolean a(final MouseEvent p0);
    
    public abstract boolean b(final MouseEvent p0);
    
    public abstract boolean d(final MouseEvent p0);
}
