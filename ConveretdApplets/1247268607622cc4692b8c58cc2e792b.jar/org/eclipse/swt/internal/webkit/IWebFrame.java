// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebFrame extends IUnknown
{
    public IWebFrame(final int n) {
        super(n);
    }
    
    public int loadRequest(final int n) {
        return OS.VtblCall(8, this.getAddress(), n);
    }
    
    public int loadHTMLString(final int n, final int n2) {
        return OS.VtblCall(10, this.getAddress(), n, n2);
    }
    
    public int dataSource(final int[] array) {
        return OS.VtblCall(13, this.getAddress(), array);
    }
    
    public int globalContext() {
        return OS.VtblCall(23, this.getAddress());
    }
}
