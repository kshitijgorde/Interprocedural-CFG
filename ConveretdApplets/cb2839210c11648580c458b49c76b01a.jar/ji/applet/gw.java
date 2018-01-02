// 
// Decompiled by Procyon v0.5.30
// 

package ji.applet;

import java.awt.Rectangle;
import ji.util.d;
import ji.io.p;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import ji.document.ad;
import ji.v1base.gr;

class gw extends gr
{
    ad a;
    int b;
    String c;
    
    public gw(final String s, final ad a, final String c) {
        super(s);
        this.a = null;
        this.b = 0;
        this.c = null;
        this.a = a;
        this.c = c;
        this.addWindowListener(new x9());
    }
    
    class x9 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            final Rectangle bounds;
            final Rectangle rectangle = bounds = gw.this.getBounds();
            bounds.height -= gw.this.b;
            new p(gw.this.c).c(rectangle, null);
            try {
                gw.this.a.c3();
            }
            catch (Exception ex) {}
            gw.this.a.b(false, -1);
            gw.this.a.az();
            if (ji.util.d.c) {
                System.out.println("System Exit 10");
            }
            else {
                System.exit(0);
            }
        }
    }
}
