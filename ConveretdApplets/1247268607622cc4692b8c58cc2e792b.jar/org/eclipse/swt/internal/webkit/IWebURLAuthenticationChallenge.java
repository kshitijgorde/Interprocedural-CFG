// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebURLAuthenticationChallenge extends IUnknown
{
    public IWebURLAuthenticationChallenge(final int n) {
        super(n);
    }
    
    public int previousFailureCount(final int[] array) {
        return OS.VtblCall(7, this.getAddress(), array);
    }
    
    public int proposedCredential(final int[] array) {
        return OS.VtblCall(8, this.getAddress(), array);
    }
    
    public int protectionSpace(final int[] array) {
        return OS.VtblCall(9, this.getAddress(), array);
    }
    
    public int sender(final int[] array) {
        return OS.VtblCall(10, this.getAddress(), array);
    }
}
