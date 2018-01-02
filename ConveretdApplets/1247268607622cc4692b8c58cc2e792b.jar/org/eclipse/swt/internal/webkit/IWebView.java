// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.IUnknown;

public class IWebView extends IUnknown
{
    public IWebView(final int n) {
        super(n);
    }
    
    public int canShowMIMEType(final int n, final int[] array) {
        return OS.VtblCall(3, this.getAddress(), n, array);
    }
    
    public int initWithFrame(final RECT rect, final int n, final int n2) {
        return COM.VtblCall(9, this.getAddress(), rect, n, n2);
    }
    
    public int setUIDelegate(final int n) {
        return OS.VtblCall(10, this.getAddress(), n);
    }
    
    public int setResourceLoadDelegate(final int n) {
        return OS.VtblCall(12, this.getAddress(), n);
    }
    
    public int setDownloadDelegate(final int n) {
        return OS.VtblCall(14, this.getAddress(), n);
    }
    
    public int setFrameLoadDelegate(final int n) {
        return OS.VtblCall(16, this.getAddress(), n);
    }
    
    public int setPolicyDelegate(final int n) {
        return OS.VtblCall(18, this.getAddress(), n);
    }
    
    public int mainFrame(final int[] array) {
        return OS.VtblCall(20, this.getAddress(), array);
    }
    
    public int goBack(final int[] array) {
        return OS.VtblCall(24, this.getAddress(), array);
    }
    
    public int goForward(final int[] array) {
        return OS.VtblCall(25, this.getAddress(), array);
    }
    
    public int setCustomUserAgent(final int n) {
        return OS.VtblCall(31, this.getAddress(), n);
    }
    
    public int setPreferences(final int n) {
        return OS.VtblCall(41, this.getAddress(), n);
    }
    
    public int preferences(final int[] array) {
        return OS.VtblCall(42, this.getAddress(), array);
    }
    
    public int setHostWindow(final int n) {
        return OS.VtblCall(45, this.getAddress(), n);
    }
    
    public int hostWindow(final int[] array) {
        return OS.VtblCall(46, this.getAddress(), array);
    }
    
    public int estimatedProgress(final int n) {
        return OS.VtblCall(51, this.getAddress(), n);
    }
}
