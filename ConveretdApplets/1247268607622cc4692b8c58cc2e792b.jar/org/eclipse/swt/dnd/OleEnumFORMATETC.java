// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.COMObject;

final class OleEnumFORMATETC
{
    private COMObject iUnknown;
    private COMObject iEnumFORMATETC;
    private int refCount;
    private int index;
    private FORMATETC[] formats;
    
    OleEnumFORMATETC() {
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    private void createCOMInterfaces() {
        this.iUnknown = new COMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return OleEnumFORMATETC.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleEnumFORMATETC.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleEnumFORMATETC.this.Release();
            }
        };
        this.iEnumFORMATETC = new COMObject(new int[] { 2, 0, 0, 3, 1, 0, 1 }) {
            public int method0(final int[] array) {
                return OleEnumFORMATETC.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleEnumFORMATETC.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleEnumFORMATETC.this.Release();
            }
            
            public int method3(final int[] array) {
                return OleEnumFORMATETC.this.Next(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return OleEnumFORMATETC.this.Skip(array[0]);
            }
            
            public int method5(final int[] array) {
                return OleEnumFORMATETC.this.Reset();
            }
        };
    }
    
    private void disposeCOMInterfaces() {
        if (this.iUnknown != null) {
            this.iUnknown.dispose();
        }
        this.iUnknown = null;
        if (this.iEnumFORMATETC != null) {
            this.iEnumFORMATETC.dispose();
        }
        this.iEnumFORMATETC = null;
    }
    
    int getAddress() {
        return this.iEnumFORMATETC.getAddress();
    }
    
    private FORMATETC[] getNextItems(final int n) {
        if (this.formats == null || n < 1) {
            return null;
        }
        int n2 = this.index + n - 1;
        if (n2 > this.formats.length - 1) {
            n2 = this.formats.length - 1;
        }
        if (this.index > n2) {
            return null;
        }
        final FORMATETC[] array = new FORMATETC[n2 - this.index + 1];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.formats[this.index];
            ++this.index;
        }
        return array;
    }
    
    private int Next(final int n, final int n2, final int n3) {
        if (n2 == 0) {
            return -2147024809;
        }
        if (n3 == 0 && n != 1) {
            return -2147024809;
        }
        final FORMATETC[] nextItems = this.getNextItems(n);
        if (nextItems != null) {
            for (int i = 0; i < nextItems.length; ++i) {
                COM.MoveMemory(n2 + i * FORMATETC.sizeof, nextItems[i], FORMATETC.sizeof);
            }
            if (n3 != 0) {
                OS.MoveMemory(n3, new int[] { nextItems.length }, 4);
            }
            if (nextItems.length == n) {
                return 0;
            }
        }
        else {
            if (n3 != 0) {
                OS.MoveMemory(n3, new int[1], 4);
            }
            COM.MoveMemory(n2, new FORMATETC(), FORMATETC.sizeof);
        }
        return 1;
    }
    
    private int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147467262;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown)) {
            OS.MoveMemory(n2, new int[] { this.iUnknown.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIEnumFORMATETC)) {
            OS.MoveMemory(n2, new int[] { this.iEnumFORMATETC.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
            if (COM.FreeUnusedLibraries) {
                COM.CoFreeUnusedLibraries();
            }
        }
        return this.refCount;
    }
    
    private int Reset() {
        return this.index = 0;
    }
    
    void setFormats(final FORMATETC[] formats) {
        this.formats = formats;
        this.index = 0;
    }
    
    private int Skip(final int n) {
        if (n < 1) {
            return -2147024809;
        }
        this.index += n;
        if (this.index > this.formats.length - 1) {
            this.index = this.formats.length - 1;
            return 1;
        }
        return 0;
    }
}
