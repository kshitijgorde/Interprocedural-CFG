// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IStream extends IUnknown
{
    public IStream(final int n) {
        super(n);
    }
    
    public int Clone(final int[] array) {
        return OS.VtblCall(13, this.address, array);
    }
    
    public int Commit(final int n) {
        return OS.VtblCall(8, this.address, n);
    }
    
    public int Read(final int n, final int n2, final int[] array) {
        return COM.VtblCall(3, this.address, n, n2, array);
    }
    
    public int Revert() {
        return OS.VtblCall(9, this.address);
    }
    
    public int Write(final int n, final int n2, final int[] array) {
        return COM.VtblCall(4, this.address, n, n2, array);
    }
}
