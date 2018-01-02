// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IFont extends IUnknown
{
    public IFont(final int n) {
        super(n);
    }
    
    public int get_hFont(final int[] array) {
        return OS.VtblCall(3, this.address, array);
    }
}
