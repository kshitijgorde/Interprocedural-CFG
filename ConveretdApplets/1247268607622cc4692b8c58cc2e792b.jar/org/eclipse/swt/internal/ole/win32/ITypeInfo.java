// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class ITypeInfo extends IUnknown
{
    public ITypeInfo(final int n) {
        super(n);
    }
    
    public int GetDocumentation(final int n, final String[] array, final String[] array2, final int[] array3, final String[] array4) {
        int[] array5 = null;
        if (array != null) {
            array5 = new int[] { 0 };
        }
        int[] array6 = null;
        if (array2 != null) {
            array6 = new int[] { 0 };
        }
        int[] array7 = null;
        if (array4 != null) {
            array7 = new int[] { 0 };
        }
        final int vtblCall = COM.VtblCall(12, this.address, n, array5, array6, array3, array7);
        if (array != null && array5[0] != 0) {
            final int sysStringByteLen = COM.SysStringByteLen(array5[0]);
            if (sysStringByteLen > 0) {
                final char[] array8 = new char[(sysStringByteLen + 1) / 2];
                OS.MoveMemory(array8, array5[0], sysStringByteLen);
                array[0] = new String(array8);
                final int index = array[0].indexOf("\u0000");
                if (index > 0) {
                    array[0] = array[0].substring(0, index);
                }
            }
            COM.SysFreeString(array5[0]);
        }
        if (array2 != null && array6[0] != 0) {
            final int sysStringByteLen2 = COM.SysStringByteLen(array6[0]);
            if (sysStringByteLen2 > 0) {
                final char[] array9 = new char[(sysStringByteLen2 + 1) / 2];
                OS.MoveMemory(array9, array6[0], sysStringByteLen2);
                array2[0] = new String(array9);
                final int index2 = array2[0].indexOf("\u0000");
                if (index2 > 0) {
                    array2[0] = array2[0].substring(0, index2);
                }
            }
            COM.SysFreeString(array6[0]);
        }
        if (array4 != null && array7[0] != 0) {
            final int sysStringByteLen3 = COM.SysStringByteLen(array7[0]);
            if (sysStringByteLen3 > 0) {
                final char[] array10 = new char[(sysStringByteLen3 + 1) / 2];
                OS.MoveMemory(array10, array7[0], sysStringByteLen3);
                array4[0] = new String(array10);
                final int index3 = array4[0].indexOf("\u0000");
                if (index3 > 0) {
                    array4[0] = array4[0].substring(0, index3);
                }
            }
            COM.SysFreeString(array7[0]);
        }
        return vtblCall;
    }
    
    public int GetFuncDesc(final int n, final int[] array) {
        return OS.VtblCall(5, this.address, n, array);
    }
    
    public int GetIDsOfNames(final String[] array, final int n, final int[] array2) {
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
            return COM.VtblCall(10, this.address, heapAlloc, n, array2);
        }
        finally {
            for (int j = 0; j < array3.length; ++j) {
                OS.HeapFree(getProcessHeap, 0, array3[j]);
            }
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
    }
    
    public int GetImplTypeFlags(final int n, final int[] array) {
        return OS.VtblCall(9, this.address, n, array);
    }
    
    public int GetNames(final int n, final String[] array, final int n2, final int[] array2) {
        final int length = array.length;
        final int[] array3 = new int[length];
        final int vtblCall = COM.VtblCall(7, this.address, n, array3, length, array2);
        if (vtblCall == 0) {
            for (int i = 0; i < array2[0]; ++i) {
                final int sysStringByteLen = COM.SysStringByteLen(array3[i]);
                if (sysStringByteLen > 0) {
                    final char[] array4 = new char[(sysStringByteLen + 1) / 2];
                    OS.MoveMemory(array4, array3[i], sysStringByteLen);
                    array[i] = new String(array4);
                    final int index = array[i].indexOf("\u0000");
                    if (index > 0) {
                        array[i] = array[i].substring(0, index);
                    }
                }
                COM.SysFreeString(array3[i]);
            }
        }
        return vtblCall;
    }
    
    public int GetRefTypeInfo(final int n, final int[] array) {
        return OS.VtblCall(14, this.address, n, array);
    }
    
    public int GetRefTypeOfImplType(final int n, final int[] array) {
        return OS.VtblCall(8, this.address, n, array);
    }
    
    public int GetTypeAttr(final int[] array) {
        return OS.VtblCall(3, this.address, array);
    }
    
    public int GetVarDesc(final int n, final int[] array) {
        return OS.VtblCall(6, this.address, n, array);
    }
    
    public int ReleaseFuncDesc(final int n) {
        return OS.VtblCall(20, this.address, n);
    }
    
    public int ReleaseTypeAttr(final int n) {
        return OS.VtblCall(19, this.address, n);
    }
    
    public int ReleaseVarDesc(final int n) {
        return OS.VtblCall(21, this.address, n);
    }
}
