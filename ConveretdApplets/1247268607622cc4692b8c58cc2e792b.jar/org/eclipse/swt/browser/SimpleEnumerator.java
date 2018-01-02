// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.mozilla.nsISimpleEnumerator;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class SimpleEnumerator
{
    XPCOMObject supports;
    XPCOMObject simpleEnumerator;
    int refCount;
    nsISupports[] values;
    int index;
    
    SimpleEnumerator(final nsISupports[] values) {
        this.refCount = 0;
        this.index = 0;
        this.values = values;
        for (int i = 0; i < values.length; ++i) {
            values[i].AddRef();
        }
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return SimpleEnumerator.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return SimpleEnumerator.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return SimpleEnumerator.this.Release();
            }
        };
        this.simpleEnumerator = new XPCOMObject(new int[] { 2, 0, 0, 1, 1 }) {
            public int method0(final int[] array) {
                return SimpleEnumerator.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return SimpleEnumerator.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return SimpleEnumerator.this.Release();
            }
            
            public int method3(final int[] array) {
                return SimpleEnumerator.this.HasMoreElements(array[0]);
            }
            
            public int method4(final int[] array) {
                return SimpleEnumerator.this.GetNext(array[0]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.supports != null) {
            this.supports.dispose();
            this.supports = null;
        }
        if (this.simpleEnumerator != null) {
            this.simpleEnumerator.dispose();
            this.simpleEnumerator = null;
        }
        if (this.values != null) {
            for (int i = 0; i < this.values.length; ++i) {
                this.values[i].Release();
            }
            this.values = null;
        }
    }
    
    int getAddress() {
        return this.simpleEnumerator.getAddress();
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
        if (nsID.Equals(nsISimpleEnumerator.NS_ISIMPLEENUMERATOR_IID)) {
            C.memmove(n2, new int[] { this.simpleEnumerator.getAddress() }, C.PTR_SIZEOF);
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
    
    int HasMoreElements(final int n) {
        C.memmove(n, new int[] { this.values != null && this.index < this.values.length }, 4);
        return 0;
    }
    
    int GetNext(final int n) {
        if (this.values == null || this.index == this.values.length) {
            return -2147418113;
        }
        final nsISupports nsISupports = this.values[this.index++];
        nsISupports.AddRef();
        C.memmove(n, new int[] { nsISupports.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
}
