// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.c;

import java.awt.Insets;
import java.awt.Component;
import java.awt.Image;
import java.awt.Panel;

public final class g extends Panel
{
    public int do;
    private a try;
    private c[] new;
    private b int;
    private boolean a;
    public boolean if;
    public boolean for;
    
    public g(final a try1, final boolean a, final boolean if1, final boolean for1) {
        this.do = 0;
        this.new = new c[3];
        this.try = try1;
        this.a = a;
        this.if = if1;
        this.for = for1;
    }
    
    public void a() {
        this.do();
    }
    
    public void if() {
        this.try = null;
        this.int.a();
        this.removeAll();
        this.int = null;
        this.new[0].if();
        this.new[0] = null;
    }
    
    public void a(final int do1) {
        this.do = do1;
    }
    
    public void if(final int n, final int n2) {
        if (this.new[n] != null) {
            this.new[n].a(n2);
        }
    }
    
    public void do(final int n) {
        if (this.new[n] != null) {
            this.new[n].a();
        }
    }
    
    public void for(final int n) {
        if (this.new[n] != null) {
            this.new[n].do();
        }
    }
    
    private void do() {
        this.add(this.new[0] = new c((new String[] { "Resets image to initial view", "Shows active hotspots", "Magnifier" })[0], 0, (new Image[] { e.a("reset_on"), e.a("hotspot_on"), e.a("info_on") })[0], (new Image[] { e.a("reset_off"), e.a("hotspot_off"), e.a("info_off") })[0], (new Image[] { e.a("hotspot_gray"), e.a("hotspot_gray"), e.a("hotspot_gray") })[0], (new boolean[] { true, true, true })[0], this));
        this.new[0].reshape(70 * (this.do + 0), 0, 70, 24);
    }
    
    public void if(final int n) {
        this.try.a(n);
    }
    
    public void a(final int n, final int n2) {
        this.try.a(n, n2);
    }
    
    public Insets insets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public void for() {
        if (this.int != null) {
            this.int.if();
        }
    }
    
    public void a(final String s) {
        this.try.a(s);
    }
}
