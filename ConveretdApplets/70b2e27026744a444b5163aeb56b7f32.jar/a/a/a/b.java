// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import b.a.d.d;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class b extends MouseAdapter
{
    private final a a;
    private final n b;
    
    b(final n b, final a a) {
        this.b = b;
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final String parameter = this.a.getParameter("MARQUEEURL");
        if (!d.a(parameter)) {
            String parameter2 = this.a.getParameter("MARQUEEURLTARGET");
            if (d.a(parameter2)) {
                parameter2 = "_self";
            }
            this.a.a(parameter, parameter2);
        }
    }
}
