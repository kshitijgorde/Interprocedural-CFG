// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.POINT;

public class IDropTargetHelper extends IUnknown
{
    public IDropTargetHelper(final int n) {
        super(n);
    }
    
    public int DragEnter(final int n, final int n2, final POINT point, final int n3) {
        return COM.VtblCall(3, this.address, n, n2, point, n3);
    }
    
    public int DragLeave() {
        return OS.VtblCall(4, this.address);
    }
    
    public int DragOver(final POINT point, final int n) {
        return COM.VtblCall(5, this.address, point, n);
    }
    
    public int Drop(final int n, final POINT point, final int n2) {
        return COM.VtblCall(6, this.address, n, point, n2);
    }
    
    public int Show(final boolean b) {
        return COM.VtblCall(7, this.address, b);
    }
}
