// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.mozilla.nsEmbedString;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.mozilla.nsIHelperAppLauncher;
import org.eclipse.swt.internal.mozilla.nsIHelperAppLauncher_1_8;
import org.eclipse.swt.internal.mozilla.nsIHelperAppLauncherDialog;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class HelperAppLauncherDialog
{
    XPCOMObject supports;
    XPCOMObject helperAppLauncherDialog;
    int refCount;
    
    HelperAppLauncherDialog() {
        this.refCount = 0;
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return HelperAppLauncherDialog.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return HelperAppLauncherDialog.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return HelperAppLauncherDialog.this.Release();
            }
        };
        this.helperAppLauncherDialog = new XPCOMObject(new int[] { 2, 0, 0, 3, 5 }) {
            public int method0(final int[] array) {
                return HelperAppLauncherDialog.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return HelperAppLauncherDialog.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return HelperAppLauncherDialog.this.Release();
            }
            
            public int method3(final int[] array) {
                return HelperAppLauncherDialog.this.Show(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return HelperAppLauncherDialog.this.PromptForSaveToFile(array[0], array[1], array[2], array[3], array[4]);
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
        if (nsID.Equals(nsIHelperAppLauncherDialog.NS_IHELPERAPPLAUNCHERDIALOG_IID)) {
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
        if (new nsISupports(n).QueryInterface(nsIHelperAppLauncher_1_8.NS_IHELPERAPPLAUNCHER_IID, new int[1]) == 0) {
            final nsIHelperAppLauncher_1_8 nsIHelperAppLauncher_1_8 = new nsIHelperAppLauncher_1_8(n);
            final int saveToDisk = nsIHelperAppLauncher_1_8.SaveToDisk(0, 0);
            nsIHelperAppLauncher_1_8.Release();
            return saveToDisk;
        }
        return new nsIHelperAppLauncher(n).SaveToDisk(0, 0);
    }
    
    int PromptForSaveToFile(final int n, final int n2, final int n3, final int n4, final int n5) {
        boolean b = false;
        boolean b2 = false;
        final nsISupports nsISupports = new nsISupports(n);
        final int[] array = { 0 };
        if (nsISupports.QueryInterface(nsIHelperAppLauncher_1_8.NS_IHELPERAPPLAUNCHER_IID, array) == 0) {
            b2 = true;
            b = true;
            new nsISupports(array[0]).Release();
        }
        else {
            array[0] = 0;
            if (nsISupports.QueryInterface(nsIHelperAppLauncher.NS_IHELPERAPPLAUNCHER_IID, array) == 0) {
                b = true;
                new nsISupports(array[0]).Release();
            }
        }
        array[0] = 0;
        int n6;
        int n7;
        int n8;
        if (b) {
            n6 = n3;
            n7 = n4;
            n8 = n5;
        }
        else {
            n6 = n2;
            n7 = n3;
            n8 = n4;
        }
        final int strlen_PRUnichar = XPCOM.strlen_PRUnichar(n6);
        final char[] array2 = new char[strlen_PRUnichar];
        C.memmove(array2, n6, strlen_PRUnichar * 2);
        final String fileName = new String(array2);
        final int strlen_PRUnichar2 = XPCOM.strlen_PRUnichar(n7);
        final char[] array3 = new char[strlen_PRUnichar2];
        C.memmove(array3, n7, strlen_PRUnichar2 * 2);
        final String s = new String(array3);
        final Shell shell = new Shell();
        final FileDialog fileDialog = new FileDialog(shell, 8192);
        fileDialog.setFileName(fileName);
        fileDialog.setFilterExtensions(new String[] { s });
        final String open = fileDialog.open();
        shell.close();
        if (open != null) {
            final nsEmbedString nsEmbedString = new nsEmbedString(open);
            final int ns_NewLocalFile = XPCOM.NS_NewLocalFile(nsEmbedString.getAddress(), 1, array);
            nsEmbedString.dispose();
            if (ns_NewLocalFile != 0) {
                Mozilla.error(ns_NewLocalFile);
            }
            if (array[0] == 0) {
                Mozilla.error(-2147467261);
            }
            C.memmove(n8, array, C.PTR_SIZEOF);
            return 0;
        }
        if (b) {
            int n9;
            if (b2) {
                n9 = new nsIHelperAppLauncher_1_8(n).Cancel(-2142568446);
            }
            else {
                n9 = new nsIHelperAppLauncher(n).Cancel();
            }
            if (n9 != 0) {
                Mozilla.error(n9);
            }
            return 0;
        }
        return -2147467259;
    }
}
