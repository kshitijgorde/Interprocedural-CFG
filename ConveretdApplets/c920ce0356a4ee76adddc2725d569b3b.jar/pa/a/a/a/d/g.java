// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.d;

import java.awt.Insets;
import java.awt.Component;
import java.awt.Image;
import java.awt.Panel;

public final class g extends Panel
{
    public int int;
    private a if;
    private f[] do;
    private c new;
    private boolean for;
    public boolean a;
    
    public g(final a if1, final boolean for1, final boolean a) {
        this.int = 4;
        this.do = new f[3];
        this.if = if1;
        this.for = for1;
        this.a = a;
    }
    
    public void a(final int n, final int n2) {
        this.if.a(n, n2);
    }
    
    private void int() {
        final String[] array = { "Resets image to initial view", "Shows active hotspots", "Magnifier" };
        final Image[] array2 = { b.a("reset_on"), b.a("hotspot_on"), b.a("info_on") };
        final Image[] array3 = { b.a("reset_off"), b.a("hotspot_off"), b.a("info_off") };
        final Image[] array4 = { b.a("hotspot_gray"), b.a("hotspot_gray"), b.a("hotspot_gray") };
        final boolean[] array5 = { false, true, true };
        int n = 2;
        if (!this.for && !this.a) {
            n = 3;
        }
        for (int i = 0; i < n; ++i) {
            this.add(this.do[i] = new f(array[i], i, array2[i], array3[i], array4[i], array5[i], this));
            this.do[i].reshape(24 * (this.int + i), 0, 24, 24);
        }
    }
    
    public void if() {
        this.a();
        this.int();
    }
    
    private void a() {
        String[] array = { "Zoom In mode", "Zoom Out mode", "Pan mode", "Rotate mode" };
        Image[] array2 = { b.a("zoomIn_on"), b.a("zoomOut_on"), b.a("pan_on"), b.a("rotate_on") };
        Image[] array3 = { b.a("zoomIn_off"), b.a("zoomOut_off"), b.a("pan_off"), b.a("rotate_off") };
        if (!this.for) {
            array = new String[] { "Zoom In mode", "Zoom Out mode", "Pan mode" };
            if (this.a) {
                array2 = new Image[] { b.a("zoomIn_on"), b.a("zoomOut_on"), b.a("rotate_on") };
                array3 = new Image[] { b.a("zoomIn_off"), b.a("zoomOut_off"), b.a("rotate_off") };
            }
            else {
                array2 = new Image[] { b.a("zoomIn_on"), b.a("zoomOut_on"), b.a("pan_on") };
                array3 = new Image[] { b.a("zoomIn_off"), b.a("zoomOut_off"), b.a("pan_off") };
            }
            this.int = 3;
        }
        this.add(this.new = new c(this.int, array, array2, array3, this));
        this.new.reshape(0, 0, 24 * this.int, 24);
    }
    
    public void a(final int n) {
        if (this.do[n] != null) {
            this.do[n].a();
        }
    }
    
    public void if(final int n) {
        if (this.do[n] != null) {
            this.do[n].do();
        }
    }
    
    public void for() {
        this.new.if();
    }
    
    public void do(final int n) {
        this.if.do(n);
    }
    
    public void if(final int n, final int n2) {
        if (this.do[n] != null) {
            this.do[n].a(n2);
        }
    }
    
    public void a(final String s) {
        this.if.a(s);
    }
    
    public void do() {
        this.if = null;
        this.new.a();
        this.removeAll();
        this.new = null;
        int n = 2;
        if (!this.for && !this.a) {
            n = 3;
        }
        for (int i = 0; i < n; ++i) {
            this.do[i].if();
            this.do[i] = null;
        }
    }
    
    public Insets insets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public void for(final int int1) {
        this.int = int1;
    }
}
