// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebURLAuthenticationChallengeSender extends IUnknown
{
    public IWebURLAuthenticationChallengeSender(final int n) {
        super(n);
    }
    
    public int cancelAuthenticationChallenge(final int n) {
        return OS.VtblCall(3, this.getAddress(), n);
    }
    
    public int useCredential(final int n, final int n2) {
        return OS.VtblCall(5, this.getAddress(), n, n2);
    }
}
