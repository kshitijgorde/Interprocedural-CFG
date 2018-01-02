// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebIBActions extends IUnknown
{
    public IWebIBActions(final int n) {
        super(n);
    }
    
    public int stopLoading(final int n) {
        return OS.VtblCall(4, this.getAddress(), n);
    }
    
    public int reload(final int n) {
        return OS.VtblCall(5, this.getAddress(), n);
    }
    
    public int canGoBack(final int n, final int[] array) {
        return OS.VtblCall(6, this.getAddress(), n, array);
    }
    
    public int canGoForward(final int n, final int[] array) {
        return OS.VtblCall(8, this.getAddress(), n, array);
    }
}
