// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.mozilla.nsIFactory;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class HelperAppLauncherDialogFactory
{
    XPCOMObject supports;
    XPCOMObject factory;
    int refCount;
    
    HelperAppLauncherDialogFactory() {
        this.refCount = 0;
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return HelperAppLauncherDialogFactory.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return HelperAppLauncherDialogFactory.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return HelperAppLauncherDialogFactory.this.Release();
            }
        };
        this.factory = new XPCOMObject(new int[] { 2, 0, 0, 3, 1 }) {
            public int method0(final int[] array) {
                return HelperAppLauncherDialogFactory.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return HelperAppLauncherDialogFactory.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return HelperAppLauncherDialogFactory.this.Release();
            }
            
            public int method3(final int[] array) {
                return HelperAppLauncherDialogFactory.this.CreateInstance(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return HelperAppLauncherDialogFactory.this.LockFactory(array[0]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.supports != null) {
            this.supports.dispose();
            this.supports = null;
        }
        if (this.factory != null) {
            this.factory.dispose();
            this.factory = null;
        }
    }
    
    int getAddress() {
        return this.factory.getAddress();
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
        if (nsID.Equals(nsIFactory.NS_IFACTORY_IID)) {
            C.memmove(n2, new int[] { this.factory.getAddress() }, C.PTR_SIZEOF);
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
    
    int CreateInstance(final int n, final int n2, final int n3) {
        if (Mozilla.IsPre_1_9) {
            final HelperAppLauncherDialog helperAppLauncherDialog = new HelperAppLauncherDialog();
            helperAppLauncherDialog.AddRef();
            C.memmove(n3, new int[] { helperAppLauncherDialog.getAddress() }, C.PTR_SIZEOF);
        }
        else {
            final HelperAppLauncherDialog_1_9 helperAppLauncherDialog_1_9 = new HelperAppLauncherDialog_1_9();
            helperAppLauncherDialog_1_9.AddRef();
            C.memmove(n3, new int[] { helperAppLauncherDialog_1_9.getAddress() }, C.PTR_SIZEOF);
        }
        return 0;
    }
    
    int LockFactory(final int n) {
        return 0;
    }
}
