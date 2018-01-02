// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;

public class IOleInPlaceObject extends IOleWindow
{
    public IOleInPlaceObject(final int n) {
        super(n);
    }
    
    public int InPlaceDeactivate() {
        return OS.VtblCall(5, this.address);
    }
    
    public int UIDeactivate() {
        return OS.VtblCall(6, this.address);
    }
    
    public int SetObjectRects(final RECT rect, final RECT rect2) {
        return COM.VtblCall(7, this.address, rect, rect2);
    }
    
    public int ReactivateAndUndo() {
        return OS.VtblCall(8, this.address);
    }
}
