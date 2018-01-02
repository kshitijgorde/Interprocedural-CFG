import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Rectangle;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_J
{
    public Vector a;
    int a;
    protected int b;
    
    public rp_J() {
        this.a = 0;
        this.b = 1;
    }
    
    public void a() {
        this.a = new Vector(20);
    }
    
    public final void a(final rp_eS rp_eS, final rp_eP rp_eP, final rp_dC rp_dC) {
        Rectangle a = null;
        if (rp_eS instanceof rp_eV) {
            a = ((rp_eV)rp_eS).a();
        }
        for (int i = 1; i <= 10; ++i) {
            final Enumeration<rp_dC> elements = this.a.elements();
            while (elements.hasMoreElements()) {
                final rp_dC rp_dC2;
                final Rectangle a2;
                if ((rp_dC2 = elements.nextElement()).a() == i && (a == null || (a2 = rp_dC2.a(0)) == null || a.intersects(a2))) {
                    rp_eS.a(rp_dC == rp_dC2);
                    rp_dC2.a(rp_eS, rp_eP);
                    rp_eS.a(false);
                }
            }
        }
    }
    
    final rp_dC a(final int n, final int n2, final int n3) {
        this.a = 0;
        rp_dC rp_dC = null;
        int a = 0;
        for (int i = 10; i >= 1; --i) {
            final Enumeration<rp_dC> elements = (Enumeration<rp_dC>)this.a.elements();
            while (elements.hasMoreElements()) {
                final rp_dC rp_dC2;
                final int a2;
                if ((rp_dC2 = elements.nextElement()).a() == i && (a2 = rp_dC2.a(n, n2)) != 0) {
                    rp_dC = rp_dC2;
                    a = a2;
                }
            }
            if (rp_dC != null) {
                this.a = a;
                return rp_dC;
            }
        }
        return null;
    }
    
    public final void a(final rp_dC rp_dC) {
        this.a.addElement(rp_dC);
    }
    
    public final boolean a(final rp_dC rp_dC) {
        return this.a.removeElement(rp_dC);
    }
    
    final void a(final rp_dC rp_dC, final int n) {
        this.a.insertElementAt(rp_dC, n);
    }
    
    public final rp_dC a(final int n) {
        final Enumeration<rp_dC> elements = (this = this).a.elements();
        while (elements.hasMoreElements()) {
            final rp_dC rp_dC;
            if ((rp_dC = elements.nextElement()).h == n) {
                return rp_dC;
            }
        }
        return null;
    }
    
    public final int a() {
        return this.b++;
    }
    
    public final Rectangle a(final Graphics graphics, final rp_eP rp_eP, final boolean b) {
        return this.a(new rp_an(graphics), rp_eP, false);
    }
    
    public final Rectangle a(final rp_an rp_an, final rp_eP rp_eP, final boolean a) {
        rp_an.a = a;
        int n = 0;
        final Enumeration<rp_dC> elements = (Enumeration<rp_dC>)this.a.elements();
        while (elements.hasMoreElements()) {
            ++n;
            elements.nextElement().a(rp_an, rp_eP);
        }
        if (n == 0) {
            return new Rectangle(0, 0);
        }
        return new Rectangle(rp_an.a);
    }
    
    final void a(final rp_eg rp_eg) {
        rp_eg.a("items", (Vector)null);
        int n = 0;
        final Enumeration<rp_dC> elements = (this = this).a.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(rp_eg);
            ++n;
        }
        System.out.println("Saved " + n + "items");
        rp_eg.a();
    }
}
