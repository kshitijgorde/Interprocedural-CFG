// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.StyleManage;

import java.awt.Color;
import java.util.Vector;

public class d
{
    private Vector if;
    private int a;
    
    public d() {
        this.if = new Vector();
        this.a = 0;
    }
    
    public void a(final Color color) {
        this.if.addElement(color);
    }
    
    public Color if() {
        if (this.a < this.if.size()) {
            final Color color = this.if.elementAt(this.a);
            ++this.a;
            return color;
        }
        return new Color((int)(Math.random() * 255.0), (int)(Math.random() * 255.0), (int)(Math.random() * 255.0));
    }
    
    public Color a(final int n) {
        if (n < this.if.size()) {
            return this.if.elementAt(n);
        }
        return new Color((int)(Math.random() * 255.0), (int)(Math.random() * 255.0), (int)(Math.random() * 255.0));
    }
    
    public void a() {
        this.a = 0;
    }
}
