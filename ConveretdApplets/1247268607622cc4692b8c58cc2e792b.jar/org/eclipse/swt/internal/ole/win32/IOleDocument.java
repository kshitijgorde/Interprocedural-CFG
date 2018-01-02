// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IOleDocument extends IUnknown
{
    public IOleDocument(final int n) {
        super(n);
    }
    
    public int CreateView(final int n, final int n2, final int n3, final int[] array) {
        return OS.VtblCall(3, this.address, n, n2, n3, array);
    }
}
