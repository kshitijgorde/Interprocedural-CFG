// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebError extends IUnknown
{
    public IWebError(final int n) {
        super(n);
    }
    
    public int code(final int[] array) {
        return OS.VtblCall(4, this.getAddress(), array);
    }
    
    public int localizedDescription(final int[] array) {
        return OS.VtblCall(6, this.getAddress(), array);
    }
    
    public int failingURL(final int[] array) {
        return OS.VtblCall(12, this.getAddress(), array);
    }
}
