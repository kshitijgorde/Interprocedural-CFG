// 
// Decompiled by Procyon v0.5.30
// 

package core;

import I.I;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class N extends MouseAdapter
{
    private RevolverEngine a;
    
    N(final RevolverEngine a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.a.getAppletContext().showDocument(E.b(I.I(581) + this.a.getParameter(I.I(628)) + I.I(630) + this.a.a(I.I(640), false) + I.I(648) + this.a.getParameter(I.I(303)) + I.I(656) + this.a.getParameter(I.I(271)) + I.I(660) + E.a(this.a.getDocumentBase().toExternalForm())), I.I(666));
    }
}
