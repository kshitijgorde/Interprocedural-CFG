// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IOleLink extends IUnknown
{
    public IOleLink(final int n) {
        super(n);
    }
    
    public int BindIfRunning() {
        return OS.VtblCall(10, this.address);
    }
    
    public int GetSourceMoniker(final int[] array) {
        return OS.VtblCall(6, this.address, array);
    }
}
