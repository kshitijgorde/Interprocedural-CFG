// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebPreferences extends IUnknown
{
    public IWebPreferences(final int n) {
        super(n);
    }
    
    public int initWithIdentifier(final int n, final int[] array) {
        return OS.VtblCall(4, this.getAddress(), n, array);
    }
    
    public int setJavaEnabled(final int n) {
        return OS.VtblCall(33, this.getAddress(), n);
    }
    
    public int setJavaScriptEnabled(final int n) {
        return OS.VtblCall(35, this.getAddress(), n);
    }
    
    public int setJavaScriptCanOpenWindowsAutomatically(final int n) {
        return OS.VtblCall(37, this.getAddress(), n);
    }
    
    public int setTabsToLinks(final int n) {
        return OS.VtblCall(52, this.getAddress(), n);
    }
    
    public int setFontSmoothing(final int n) {
        return OS.VtblCall(63, this.getAddress(), n);
    }
}
