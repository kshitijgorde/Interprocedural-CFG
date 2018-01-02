// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;

public class IWebMutableURLRequest extends IWebURLRequest
{
    public IWebMutableURLRequest(final int n) {
        super(n);
    }
    
    public int setHTTPMethod(final int n) {
        return OS.VtblCall(23, this.getAddress(), n);
    }
    
    public int setURL(final int n) {
        return OS.VtblCall(27, this.getAddress(), n);
    }
    
    public int setValue(final int n, final int n2) {
        return OS.VtblCall(28, this.getAddress(), n, n2);
    }
    
    public int setAllowsAnyHTTPSCertificate() {
        return OS.VtblCall(29, this.getAddress());
    }
}
