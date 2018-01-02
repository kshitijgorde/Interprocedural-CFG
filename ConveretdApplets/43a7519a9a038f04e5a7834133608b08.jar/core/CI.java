// 
// Decompiled by Procyon v0.5.30
// 

package core;

import I.I;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class CI extends MouseAdapter
{
    private RE a;
    
    CI(final RE a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.a.getAppletContext().showDocument(K.b(I.I(661)), I.I(654));
    }
}
