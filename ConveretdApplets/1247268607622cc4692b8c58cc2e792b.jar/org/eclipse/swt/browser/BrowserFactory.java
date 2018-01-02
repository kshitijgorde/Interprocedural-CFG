// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.SWTError;
import org.eclipse.swt.internal.win32.OS;

class BrowserFactory
{
    WebBrowser createWebBrowser(final int n) {
        if (OS.IsWinCE && (n & 0x18000) != 0x0) {
            throw new SWTError(2, "Unsupported Browser type");
        }
        if ((n & 0x8000) != 0x0) {
            return new Mozilla();
        }
        if ((n & 0x10000) != 0x0) {
            return new WebKit();
        }
        return new IE();
    }
}
