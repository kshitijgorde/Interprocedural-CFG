// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebErrorPrivate extends IUnknown
{
    public IWebErrorPrivate(final int n) {
        super(n);
    }
    
    public int sslPeerCertificate(final int[] array) {
        return OS.VtblCall(3, this.getAddress(), array);
    }
}
