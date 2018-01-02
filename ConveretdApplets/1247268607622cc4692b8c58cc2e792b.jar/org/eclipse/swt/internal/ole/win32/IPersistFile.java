// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IPersistFile extends IPersist
{
    public IPersistFile(final int n) {
        super(n);
    }
    
    public int IsDirty() {
        return OS.VtblCall(4, this.address);
    }
    
    public int Load(final int n, final int n2) {
        return OS.VtblCall(5, this.address, n, n2);
    }
    
    public int Save(final int n, final boolean b) {
        return COM.VtblCall(6, this.address, n, b);
    }
    
    public int SaveCompleted(final int n) {
        return OS.VtblCall(7, this.address, n);
    }
    
    public int GetCurFile(final int[] array) {
        return OS.VtblCall(8, this.address, array);
    }
}
