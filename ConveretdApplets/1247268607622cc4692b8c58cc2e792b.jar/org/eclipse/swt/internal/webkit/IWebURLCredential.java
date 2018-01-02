// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebURLCredential extends IUnknown
{
    public IWebURLCredential(final int n) {
        super(n);
    }
    
    public int hasPassword(final int[] array) {
        return OS.VtblCall(3, this.getAddress(), array);
    }
    
    public int initWithUser(final int n, final int n2, final int n3) {
        return OS.VtblCall(4, this.getAddress(), n, n2, n3);
    }
    
    public int password(final int[] array) {
        return OS.VtblCall(5, this.getAddress(), array);
    }
    
    public int user(final int[] array) {
        return OS.VtblCall(7, this.getAddress(), array);
    }
}
