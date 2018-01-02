// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.SIZE;

public class IViewObject2 extends IUnknown
{
    public IViewObject2(final int n) {
        super(n);
    }
    
    public int GetExtent(final int n, final int n2, final DVTARGETDEVICE dvtargetdevice, final SIZE size) {
        return COM.VtblCall(9, this.address, n, n2, dvtargetdevice, size);
    }
    
    public int SetAdvise(final int n, final int n2, final int n3) {
        return OS.VtblCall(7, this.address, n, n2, n3);
    }
}
