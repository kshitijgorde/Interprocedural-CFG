// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import ji.awt.bb;
import java.awt.Dimension;
import java.awt.Point;
import ji.io.h;
import ji.util.i;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Component;
import ji.v1base.bl;
import ji.util.d;

class abv implements Runnable
{
    private final /* synthetic */ gt a;
    
    abv(final gt a) {
        this.a = a;
    }
    
    public void run() {
        while (this.a.a) {
            if (!d.dg()) {
                this.a.toFront();
            }
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
}
