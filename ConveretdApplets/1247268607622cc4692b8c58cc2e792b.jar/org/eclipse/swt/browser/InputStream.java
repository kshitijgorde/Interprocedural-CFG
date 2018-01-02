// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.mozilla.nsIInputStream;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class InputStream
{
    XPCOMObject inputStream;
    int refCount;
    byte[] buffer;
    int index;
    
    InputStream(final byte[] buffer) {
        this.refCount = 0;
        this.index = 0;
        this.buffer = buffer;
        this.index = 0;
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.inputStream = new XPCOMObject(new int[] { 2, 0, 0, 0, 1, 3, 4, 1 }) {
            public int method0(final int[] array) {
                return InputStream.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return InputStream.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return InputStream.this.Release();
            }
            
            public int method3(final int[] array) {
                return InputStream.this.Close();
            }
            
            public int method4(final int[] array) {
                return InputStream.this.Available(array[0]);
            }
            
            public int method5(final int[] array) {
                return InputStream.this.Read(array[0], array[1], array[2]);
            }
            
            public int method6(final int[] array) {
                return InputStream.this.ReadSegments(array[0], array[1], array[2], array[3]);
            }
            
            public int method7(final int[] array) {
                return InputStream.this.IsNonBlocking(array[0]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.inputStream != null) {
            this.inputStream.dispose();
            this.inputStream = null;
        }
    }
    
    int getAddress() {
        return this.inputStream.getAddress();
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147467262;
        }
        final nsID nsID = new nsID();
        XPCOM.memmove(nsID, n, 16);
        if (nsID.Equals(nsISupports.NS_ISUPPORTS_IID)) {
            C.memmove(n2, new int[] { this.inputStream.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIInputStream.NS_IINPUTSTREAM_IID)) {
            C.memmove(n2, new int[] { this.inputStream.getAddress() }, C.PTR_SIZEOF);
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
    
    int Close() {
        this.buffer = null;
        return this.index = 0;
    }
    
    int Available(final int n) {
        C.memmove(n, new int[] { (this.buffer == null) ? 0 : (this.buffer.length - this.index) }, 4);
        return 0;
    }
    
    int Read(final int n, final int n2, final int n3) {
        final int min = Math.min(n2, (this.buffer == null) ? 0 : (this.buffer.length - this.index));
        if (min > 0) {
            final byte[] array = new byte[min];
            System.arraycopy(this.buffer, this.index, array, 0, min);
            C.memmove(n, array, min);
            this.index += min;
        }
        C.memmove(n3, new int[] { min }, 4);
        return 0;
    }
    
    int ReadSegments(final int n, final int n2, final int n3, final int n4) {
        int min = (this.buffer == null) ? 0 : (this.buffer.length - this.index);
        if (n3 != -1) {
            min = Math.min(min, n3);
        }
        int i;
        int[] array;
        for (i = min; i > 0; i -= array[0]) {
            array = new int[] { 0 };
            if (XPCOM.Call(n, this.getAddress(), n2, this.buffer, this.index, i, array) != 0) {
                break;
            }
            this.index += array[0];
        }
        C.memmove(n4, new int[] { min - i }, 4);
        return 0;
    }
    
    int IsNonBlocking(final int n) {
        C.memmove(n, new int[1], 4);
        return 0;
    }
}
