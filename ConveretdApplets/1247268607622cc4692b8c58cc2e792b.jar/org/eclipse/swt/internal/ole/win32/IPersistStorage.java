// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IPersistStorage extends IPersist
{
    public IPersistStorage(final int n) {
        super(n);
    }
    
    public int IsDirty() {
        return OS.VtblCall(4, this.address);
    }
    
    public int InitNew(final int n) {
        return OS.VtblCall(5, this.address, n);
    }
    
    public int Load(final int n) {
        return OS.VtblCall(6, this.address, n);
    }
    
    public int Save(final int n, final boolean b) {
        return COM.VtblCall(7, this.address, n, b);
    }
    
    public int SaveCompleted(final int n) {
        return OS.VtblCall(8, this.address, n);
    }
    
    public int HandsOffStorage() {
        return OS.VtblCall(9, this.address);
    }
}
