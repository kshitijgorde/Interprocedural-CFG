// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebOpenPanelResultListener extends IUnknown
{
    public IWebOpenPanelResultListener(final int n) {
        super(n);
    }
    
    public int chooseFilename(final int n) {
        return OS.VtblCall(3, this.getAddress(), n);
    }
    
    public int cancel() {
        return OS.VtblCall(4, this.getAddress());
    }
}
