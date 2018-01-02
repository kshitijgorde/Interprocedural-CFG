// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.StyleManage;

import java.awt.Color;
import java.util.Hashtable;

public class c
{
    Hashtable a;
    
    public c() {
        this.a = new Hashtable();
    }
    
    public void a(final String s, final Color color) {
        final String lowerCase = s.toLowerCase();
        d d = this.a.get(lowerCase);
        if (d == null) {
            this.a.put(lowerCase, new d());
            d = this.a.get(lowerCase);
        }
        d.a(color);
    }
    
    public Color a(final String s) {
        final d d = this.a.get(s.toLowerCase());
        if (d == null) {
            return this.do(s);
        }
        return d.if();
    }
    
    public Color a(final String s, final int n) {
        final d d = this.a.get(s.toLowerCase());
        if (d == null) {
            return this.if(s, n);
        }
        return d.a(n);
    }
    
    private Color do(final String s) {
        if (s.equals("mouseline")) {
            return Color.black;
        }
        if (s.equals("bull")) {
            return this.a("mainsymbol");
        }
        if (s.equals("bear")) {
            return this.a("compares");
        }
        if (s.equals("lowerindic")) {
            return Color.blue;
        }
        return null;
    }
    
    private Color if(final String s, final int n) {
        if (n == 0) {
            return this.do(s);
        }
        if (!s.equals("lowerindic")) {
            return null;
        }
        if (n == 1) {
            return Color.red;
        }
        if (n == 2) {
            return Color.black;
        }
        return null;
    }
    
    public void if(final String s) {
        final d d = this.a.get(s.toLowerCase());
        if (d != null) {
            d.a();
        }
    }
}
