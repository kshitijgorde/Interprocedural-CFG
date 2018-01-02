// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.awt.Component;
import java.awt.Cursor;

class a
{
    public void a(final Shout3DViewer shout3DViewer, final boolean b) {
        final Component b2 = shout3DViewer.b();
        if (b2 == null) {
            return;
        }
        if (b) {
            if (b2.getCursor().getType() != 12) {
                b2.setCursor(new Cursor(12));
            }
        }
        else if (b2.getCursor().getType() != 0) {
            b2.setCursor(new Cursor(0));
        }
    }
}
