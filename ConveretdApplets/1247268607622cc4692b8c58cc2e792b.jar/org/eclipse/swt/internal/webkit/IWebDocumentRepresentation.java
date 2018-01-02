// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebDocumentRepresentation extends IUnknown
{
    public IWebDocumentRepresentation(final int n) {
        super(n);
    }
    
    public int documentSource(final int[] array) {
        return OS.VtblCall(8, this.getAddress(), array);
    }
}
