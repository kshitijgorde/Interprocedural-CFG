// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.Color;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.TextArea;
import COM.NextBus.Applets.d;
import java.awt.Frame;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

final class S implements MouseListener
{
    private /* synthetic */ EventDialog a;
    
    S(final EventDialog a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.a._mapInfo.b("http://java.sun.com/getjava");
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
}
