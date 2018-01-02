// 
// Decompiled by Procyon v0.5.30
// 

package core;

import I.I;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class D extends MouseAdapter
{
    private RE a;
    
    D(final RE a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.a.getAppletContext().showDocument(K.b(I.I(556) + this.a.getParameter(I.I(603)) + I.I(605) + this.a.getParameter(I.I(1)) + I.I(610) + this.a.a(I.I(620), false) + I.I(628) + this.a.getParameter(I.I(286)) + I.I(636) + this.a.a(I.I(526), false) + I.I(644) + this.a.getParameter(I.I(262)) + I.I(648) + K.a(this.a.getDocumentBase().toExternalForm())), I.I(654));
    }
}
