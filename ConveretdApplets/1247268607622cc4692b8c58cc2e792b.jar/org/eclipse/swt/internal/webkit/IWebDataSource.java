// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebDataSource extends IUnknown
{
    public IWebDataSource(final int n) {
        super(n);
    }
    
    public int representation(final int[] array) {
        return OS.VtblCall(5, this.getAddress(), array);
    }
    
    public int webFrame(final int[] array) {
        return OS.VtblCall(6, this.getAddress(), array);
    }
    
    public int request(final int[] array) {
        return OS.VtblCall(8, this.getAddress(), array);
    }
    
    public int pageTitle(final int[] array) {
        return OS.VtblCall(12, this.getAddress(), array);
    }
}
