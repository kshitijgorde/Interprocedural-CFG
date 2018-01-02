// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebPolicyDecisionListener extends IUnknown
{
    public IWebPolicyDecisionListener(final int n) {
        super(n);
    }
    
    public int use() {
        return OS.VtblCall(3, this.getAddress());
    }
    
    public int download() {
        return OS.VtblCall(4, this.getAddress());
    }
    
    public int ignore() {
        return OS.VtblCall(5, this.getAddress());
    }
}
