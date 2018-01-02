// 
// Decompiled by Procyon v0.5.30
// 

package core;

import I.I;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class M extends MouseAdapter
{
    private RevolverEngine a;
    
    M(final RevolverEngine a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.a.getAppletContext().showDocument(E.b(I.I(673)), I.I(666));
    }
}
