// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IPersistStreamInit extends IPersist
{
    public IPersistStreamInit(final int n) {
        super(n);
    }
    
    public int Load(final int n) {
        return OS.VtblCall(5, this.address, n);
    }
    
    public int InitNew() {
        return OS.VtblCall(8, this.address);
    }
}
