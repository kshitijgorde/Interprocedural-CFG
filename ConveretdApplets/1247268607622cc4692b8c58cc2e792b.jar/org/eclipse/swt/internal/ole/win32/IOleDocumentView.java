// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;

public class IOleDocumentView extends IUnknown
{
    public IOleDocumentView(final int n) {
        super(n);
    }
    
    public int SetInPlaceSite(final int n) {
        return OS.VtblCall(3, this.address, n);
    }
    
    public int SetRect(final RECT rect) {
        return COM.VtblCall(6, this.address, rect);
    }
    
    public int Show(final int n) {
        return OS.VtblCall(9, this.address, n);
    }
    
    public int UIActivate(final int n) {
        return OS.VtblCall(10, this.address, n);
    }
}
