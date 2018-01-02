// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IOleWindow extends IUnknown
{
    public IOleWindow(final int n) {
        super(n);
    }
    
    public int GetWindow(final int[] array) {
        return OS.VtblCall(3, this.address, array);
    }
}
