// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Hashtable;
import java.awt.Component;
import java.awt.Canvas;

public class cx extends Canvas
{
    private Component ou;
    private Component pu;
    private Component qu;
    private Hashtable ru;
    private Hashtable su;
    
    public cx(final Component qu) {
        this.ru = new Hashtable();
        this.su = new Hashtable();
        this.ou = qu;
        this.pu = qu;
        this.qu = qu;
    }
    
    public void sc(final Component pu) {
        this.ru.put(this.pu, pu);
        this.su.put(pu, this.pu);
        this.pu = pu;
    }
    
    public void ni(final Component component, final Component qu) {
        final Component component2 = this.ru.remove(component);
        if (component2 != null) {
            this.ru.put(qu, component2);
        }
        final Component component3 = this.su.remove(component);
        if (component3 != null) {
            this.su.put(qu, component3);
        }
        if (component == this.ou) {
            this.ou = qu;
        }
        if (component == this.pu) {
            this.pu = qu;
        }
        if (component == this.qu) {
            (this.qu = qu).requestFocus();
        }
    }
    
    public void pi() {
        this.qu = this.su.get(this.qu);
        if (this.qu == null) {
            this.qu = this.pu;
        }
        this.qu.requestFocus();
    }
    
    public void oi() {
        this.qu = this.ru.get(this.qu);
        if (this.qu == null) {
            this.qu = this.ou;
        }
        this.qu.requestFocus();
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        this.qu.requestFocus();
    }
    
    public void update(final Graphics graphics) {
        super.update(graphics);
        this.qu.requestFocus();
    }
    
    public void mi(final Component qu) {
        this.qu = qu;
        this.repaint();
    }
    
    public Dimension preferredSize() {
        return new Dimension(1, 1);
    }
    
    public Dimension minimumSize() {
        return new Dimension(1, 1);
    }
}
