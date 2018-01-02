// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IDragSourceHelper2 extends IDragSourceHelper
{
    public IDragSourceHelper2(final int n) {
        super(n);
    }
    
    public int SetFlags(final int n) {
        return OS.VtblCall(5, this.address, n);
    }
}
