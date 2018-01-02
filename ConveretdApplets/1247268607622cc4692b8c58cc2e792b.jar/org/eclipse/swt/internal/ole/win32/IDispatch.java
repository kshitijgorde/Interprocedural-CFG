// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IDispatch extends IUnknown
{
    public IDispatch(final int n) {
        super(n);
    }
    
    public int GetIDsOfNames(final GUID guid, final String[] array, final int n, final int n2, final int[] array2) {
        final int length = array.length;
        final int getProcessHeap = OS.GetProcessHeap();
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, length * OS.PTR_SIZEOF);
        final int[] array3 = new int[length];
        try {
            for (int i = 0; i < length; ++i) {
                final int length2 = array[i].length();
                final char[] array4 = new char[length2 + 1];
                array[i].getChars(0, length2, array4, 0);
                final int heapAlloc2 = OS.HeapAlloc(getProcessHeap, 8, array4.length * 2);
                OS.MoveMemory(heapAlloc2, array4, array4.length * 2);
                OS.MoveMemory(heapAlloc + OS.PTR_SIZEOF * i, new int[] { heapAlloc2 }, OS.PTR_SIZEOF);
                array3[i] = heapAlloc2;
            }
            return COM.VtblCall(5, this.address, new GUID(), heapAlloc, n, n2, array2);
        }
        finally {
            for (int j = 0; j < array3.length; ++j) {
                OS.HeapFree(getProcessHeap, 0, array3[j]);
            }
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
    }
    
    public int GetTypeInfo(final int n, final int n2, final int[] array) {
        return COM.VtblCall(4, this.address, n, n2, array);
    }
    
    public int GetTypeInfoCount(final int[] array) {
        return OS.VtblCall(3, this.address, array);
    }
    
    public int Invoke(final int n, final GUID guid, final int n2, final int n3, final DISPPARAMS dispparams, final int n4, final EXCEPINFO excepinfo, final int[] array) {
        return COM.VtblCall(6, this.address, n, guid, n2, n3, dispparams, n4, excepinfo, array);
    }
}
