// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebDownload extends IUnknown
{
    public IWebDownload(final int n) {
        super(n);
    }
    
    public int cancel() {
        return OS.VtblCall(4, this.getAddress());
    }
    
    public int setDeletesFileUponFailure(final int n) {
        return OS.VtblCall(12, this.getAddress(), n);
    }
    
    public int setDestination(final int n, final int n2) {
        return OS.VtblCall(13, this.getAddress(), n, n2);
    }
}
