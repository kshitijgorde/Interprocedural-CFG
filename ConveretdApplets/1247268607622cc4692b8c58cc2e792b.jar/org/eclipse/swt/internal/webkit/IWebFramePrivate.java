// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebFramePrivate extends IUnknown
{
    public IWebFramePrivate(final int n) {
        super(n);
    }
    
    public int setInPrintingMode(final int n, final int n2) {
        return OS.VtblCall(8, this.getAddress(), n, n2);
    }
    
    public int getPrintedPageCount(final int n, final int[] array) {
        return OS.VtblCall(9, this.getAddress(), n, array);
    }
    
    public int spoolPages(final int n, final int n2, final int n3, final int[] array) {
        return OS.VtblCall(10, this.getAddress(), n, n2, n3, array);
    }
}
