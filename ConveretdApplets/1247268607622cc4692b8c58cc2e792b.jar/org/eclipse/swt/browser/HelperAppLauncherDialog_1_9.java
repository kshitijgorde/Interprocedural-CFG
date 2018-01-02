// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.mozilla.nsEmbedString;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.mozilla.nsIHelperAppLauncher_1_9;
import org.eclipse.swt.internal.mozilla.nsIHelperAppLauncherDialog_1_9;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class HelperAppLauncherDialog_1_9
{
    XPCOMObject supports;
    XPCOMObject helperAppLauncherDialog;
    int refCount;
    
    HelperAppLauncherDialog_1_9() {
        this.refCount = 0;
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return HelperAppLauncherDialog_1_9.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return HelperAppLauncherDialog_1_9.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return HelperAppLauncherDialog_1_9.this.Release();
            }
        };
        this.helperAppLauncherDialog = new XPCOMObject(new int[] { 2, 0, 0, 3, 6 }) {
            public int method0(final int[] array) {
                return HelperAppLauncherDialog_1_9.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return HelperAppLauncherDialog_1_9.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return HelperAppLauncherDialog_1_9.this.Release();
            }
            
            public int method3(final int[] array) {
                return HelperAppLauncherDialog_1_9.this.Show(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return HelperAppLauncherDialog_1_9.this.PromptForSaveToFile(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.supports != null) {
            this.supports.dispose();
            this.supports = null;
        }
        if (this.helperAppLauncherDialog != null) {
            this.helperAppLauncherDialog.dispose();
            this.helperAppLauncherDialog = null;
        }
    }
    
    int getAddress() {
        return this.helperAppLauncherDialog.getAddress();
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147467262;
        }
        final nsID nsID = new nsID();
        XPCOM.memmove(nsID, n, 16);
        if (nsID.Equals(nsISupports.NS_ISUPPORTS_IID)) {
            C.memmove(n2, new int[] { this.supports.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIHelperAppLauncherDialog_1_9.NS_IHELPERAPPLAUNCHERDIALOG_IID)) {
            C.memmove(n2, new int[] { this.helperAppLauncherDialog.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        C.memmove(n2, new int[1], C.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
        }
        return this.refCount;
    }
    
    int Show(final int n, final int n2, final int n3) {
        return new nsIHelperAppLauncher_1_9(n).SaveToDisk(0, 0);
    }
    
    int PromptForSaveToFile(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n3);
        final char[] array = new char[strlen_PRUnichar];
        C.memmove(array, n3, strlen_PRUnichar * 2);
        final String fileName = new String(array);
        final int strlen_PRUnichar2 = XPCOM.strlen_PRUnichar(n4);
        final char[] array2 = new char[strlen_PRUnichar2];
        C.memmove(array2, n4, strlen_PRUnichar2 * 2);
        final String s = new String(array2);
        final Shell shell = new Shell();
        final FileDialog fileDialog = new FileDialog(shell, 8192);
        fileDialog.setFileName(fileName);
        fileDialog.setFilterExtensions(new String[] { s });
        final String open = fileDialog.open();
        shell.close();
        if (open == null) {
            final int cancel = new nsIHelperAppLauncher_1_9(n).Cancel(-2142568446);
            if (cancel != 0) {
                Mozilla.error(cancel);
            }
            return -2147467259;
        }
        final nsEmbedString nsEmbedString = new nsEmbedString(open);
        final int[] array3 = { 0 };
        final int ns_NewLocalFile = XPCOM.NS_NewLocalFile(nsEmbedString.getAddress(), 1, array3);
        nsEmbedString.dispose();
        if (ns_NewLocalFile != 0) {
            Mozilla.error(ns_NewLocalFile);
        }
        if (array3[0] == 0) {
            Mozilla.error(-2147467261);
        }
        C.memmove(n6, array3, C.PTR_SIZEOF);
        return 0;
    }
}
