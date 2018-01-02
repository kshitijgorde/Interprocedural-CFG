// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebViewPrivate extends IUnknown
{
    public IWebViewPrivate(final int n) {
        super(n);
    }
    
    public int viewWindow(final int[] array) {
        return OS.VtblCall(5, this.getAddress(), array);
    }
    
    public int setInitialFocus(final int n) {
        return OS.VtblCall(28, this.getAddress(), n);
    }
    
    public int shouldClose(final int[] array) {
        return OS.VtblCall(33, this.getAddress(), array);
    }
}
