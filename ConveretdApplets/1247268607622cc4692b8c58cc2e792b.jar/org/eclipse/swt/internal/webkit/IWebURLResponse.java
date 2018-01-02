// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebURLResponse extends IUnknown
{
    public IWebURLResponse(final int n) {
        super(n);
    }
    
    public int expectedContentLength(final long[] array) {
        return OS.VtblCall(3, this.getAddress(), array);
    }
    
    public int URL(final int[] array) {
        return OS.VtblCall(8, this.getAddress(), array);
    }
}
