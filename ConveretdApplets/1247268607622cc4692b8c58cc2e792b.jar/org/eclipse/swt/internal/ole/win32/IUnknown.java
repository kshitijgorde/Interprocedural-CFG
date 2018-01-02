// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IUnknown
{
    int address;
    
    public IUnknown(final int address) {
        this.address = address;
    }
    
    public int AddRef() {
        return OS.VtblCall(1, this.address);
    }
    
    public int getAddress() {
        return this.address;
    }
    
    public int QueryInterface(final GUID guid, final int[] array) {
        return COM.VtblCall(0, this.address, guid, array);
    }
    
    public int Release() {
        return OS.VtblCall(2, this.address);
    }
}
