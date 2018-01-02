// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebCookieManager extends IUnknown
{
    public IWebCookieManager(final int n) {
        super(n);
    }
    
    public int cookieStorage(final int[] array) {
        return OS.VtblCall(3, this.getAddress(), array);
    }
}
