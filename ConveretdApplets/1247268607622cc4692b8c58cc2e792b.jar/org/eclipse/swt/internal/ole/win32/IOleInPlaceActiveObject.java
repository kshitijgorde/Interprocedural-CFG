// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.MSG;

public class IOleInPlaceActiveObject extends IOleWindow
{
    public IOleInPlaceActiveObject(final int n) {
        super(n);
    }
    
    public int TranslateAccelerator(final MSG msg) {
        return COM.VtblCall(5, this.address, msg);
    }
    
    public void OnFrameWindowActivate(final boolean b) {
        COM.VtblCall(6, this.getAddress(), b);
    }
    
    public void OnDocWindowActivate(final boolean b) {
        COM.VtblCall(7, this.getAddress(), b);
    }
    
    public int ResizeBorder(final RECT rect, final int n, final boolean b) {
        return COM.VtblCall(8, this.address, rect, n, b);
    }
}
