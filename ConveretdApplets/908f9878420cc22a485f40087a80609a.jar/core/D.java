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
        this.a.getAppletContext().showDocument(K.b(I.I(568) + this.a.getParameter(I.I(615)) + I.I(617) + this.a.a(I.I(627), false) + I.I(629) + this.a.getParameter(I.I(293)) + I.I(637) + RE.a(this.a) + I.I(641) + K.a(this.a.getDocumentBase().toExternalForm())), I.I(647));
    }
}
