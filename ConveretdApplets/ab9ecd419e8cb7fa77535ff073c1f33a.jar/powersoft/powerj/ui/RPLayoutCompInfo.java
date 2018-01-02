// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.ui;

import java.awt.Rectangle;
import java.io.Serializable;

class RPLayoutCompInfo implements Serializable
{
    static final long serialVersionUID = -3841322316186940358L;
    protected Rectangle percent;
    protected Rectangle pending;
    
    RPLayoutCompInfo(final Rectangle p) {
        if (p == null) {
            this.percent = new Rectangle(0, 0, 0, 0);
        }
        else {
            this.percent = p;
        }
        this.pending = new Rectangle(0, 0, 0, 0);
    }
}
