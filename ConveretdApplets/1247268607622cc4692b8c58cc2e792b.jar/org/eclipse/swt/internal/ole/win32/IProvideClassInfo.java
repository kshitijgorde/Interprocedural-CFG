// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IProvideClassInfo extends IUnknown
{
    public IProvideClassInfo(final int n) {
        super(n);
    }
    
    public int GetClassInfo(final int[] array) {
        return OS.VtblCall(3, this.address, array);
    }
}
