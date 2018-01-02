// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IEnum extends IUnknown
{
    public IEnum(final int n) {
        super(n);
    }
    
    public int Clone(final int[] array) {
        return OS.VtblCall(6, this.address, array);
    }
    
    public int Next(final int n, final int n2, final int[] array) {
        return COM.VtblCall(3, this.address, n, n2, array);
    }
    
    public int Reset() {
        return OS.VtblCall(5, this.address);
    }
    
    public int Skip(final int n) {
        return OS.VtblCall(4, this.address, n);
    }
}
