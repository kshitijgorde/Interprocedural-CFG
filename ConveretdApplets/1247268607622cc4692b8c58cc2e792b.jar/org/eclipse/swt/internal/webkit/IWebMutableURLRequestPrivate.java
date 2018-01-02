// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebMutableURLRequestPrivate extends IUnknown
{
    public IWebMutableURLRequestPrivate(final int n) {
        super(n);
    }
    
    public int cfRequest() {
        return OS.VtblCall(4, this.getAddress());
    }
}
