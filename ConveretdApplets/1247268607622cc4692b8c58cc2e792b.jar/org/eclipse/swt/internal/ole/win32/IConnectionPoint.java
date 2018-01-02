// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IConnectionPoint extends IUnknown
{
    public IConnectionPoint(final int n) {
        super(n);
    }
    
    public int Advise(final int n, final int[] array) {
        return OS.VtblCall(5, this.address, n, array);
    }
    
    public int Unadvise(final int n) {
        return OS.VtblCall(6, this.address, n);
    }
}
