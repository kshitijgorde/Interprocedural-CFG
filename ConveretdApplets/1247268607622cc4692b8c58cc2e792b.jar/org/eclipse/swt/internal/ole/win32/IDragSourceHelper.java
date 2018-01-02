// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.SHDRAGIMAGE;

public class IDragSourceHelper extends IUnknown
{
    public IDragSourceHelper(final int n) {
        super(n);
    }
    
    public int InitializeFromBitmap(final SHDRAGIMAGE shdragimage, final int n) {
        return COM.VtblCall(3, this.address, shdragimage, n);
    }
    
    public int InitializeFromWindow(final int n, final POINT point, final int n2) {
        return COM.VtblCall(4, this.address, n, point, n2);
    }
}
