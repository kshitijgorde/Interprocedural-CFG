// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import dlt.mandelbrot.a.j;

public class h extends Thread
{
    private j for;
    private boolean if;
    Graphics do;
    JPanel a;
    
    public h(final j for1, final Graphics do1, final JPanel a) {
        this.for = for1;
        this.do = do1;
        this.a = a;
    }
    
    public void run() {
        this.if = true;
        final double n = new Double(this.a.getWidth()) / new Double(this.for.if());
        final Color[] a = this.for.a();
        for (int n2 = 0; n2 < this.for.if() && this.if; ++n2) {
            this.do.setColor(a[n2]);
            final int intValue = (int)(Object)new Double(n * n2);
            this.do.fillRect(intValue, 0, intValue + (int)(Object)new Double(n), this.a.getHeight());
        }
        this.a.repaint();
    }
    
    public void a() {
        this.if = false;
    }
}
