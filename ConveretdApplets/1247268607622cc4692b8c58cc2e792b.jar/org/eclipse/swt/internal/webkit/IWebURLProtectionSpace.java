// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebURLProtectionSpace extends IUnknown
{
    public IWebURLProtectionSpace(final int n) {
        super(n);
    }
    
    public int host(final int[] array) {
        return OS.VtblCall(4, this.getAddress(), array);
    }
    
    public int port(final int[] array) {
        return OS.VtblCall(8, this.getAddress(), array);
    }
    
    public int realm(final int[] array) {
        return OS.VtblCall(11, this.getAddress(), array);
    }
}
