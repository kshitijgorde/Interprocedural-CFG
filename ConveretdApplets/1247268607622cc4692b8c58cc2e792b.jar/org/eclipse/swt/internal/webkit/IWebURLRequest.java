// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebURLRequest extends IUnknown
{
    public IWebURLRequest(final int n) {
        super(n);
    }
    
    public int HTTPMethod(final int[] array) {
        return OS.VtblCall(8, this.getAddress(), array);
    }
    
    public int URL(final int[] array) {
        return OS.VtblCall(13, this.getAddress(), array);
    }
    
    public int mutableCopy(final int[] array) {
        return OS.VtblCall(16, this.getAddress(), array);
    }
}
