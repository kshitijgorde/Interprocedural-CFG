// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.LONG;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.SWT;
import java.util.Hashtable;
import org.eclipse.swt.internal.Callback;

public class COMObject
{
    public int ppVtable;
    private static final int MAX_ARG_COUNT = 12;
    private static final int MAX_VTABLE_LENGTH = 80;
    private static Callback[][] Callbacks;
    private static Hashtable ObjectMap;
    
    static {
        COMObject.Callbacks = new Callback[80][12];
        COMObject.ObjectMap = new Hashtable();
    }
    
    public COMObject(final int[] array) {
        final int[] array2 = new int[array.length];
        synchronized (COMObject.Callbacks) {
            for (int i = 0; i < array.length; ++i) {
                if (COMObject.Callbacks[i][array[i]] == null) {
                    COMObject.Callbacks[i][array[i]] = new Callback(this.getClass(), "callback" + i, array[i] + 1, true, -2147467259);
                    if (COMObject.Callbacks[i][array[i]].getAddress() == 0) {
                        SWT.error(3);
                    }
                }
                array2[i] = COMObject.Callbacks[i][array[i]].getAddress();
            }
        }
        // monitorexit(COMObject.Callbacks)
        final int globalAlloc = OS.GlobalAlloc(64, OS.PTR_SIZEOF * array.length);
        OS.MoveMemory(globalAlloc, array2, OS.PTR_SIZEOF * array.length);
        OS.MoveMemory(this.ppVtable = OS.GlobalAlloc(64, OS.PTR_SIZEOF), new int[] { globalAlloc }, OS.PTR_SIZEOF);
        COMObject.ObjectMap.put(new LONG(this.ppVtable), this);
    }
    
    public static GUID IIDFromString(final String s) {
        final char[] charArray = (String.valueOf(s) + "\u0000").toCharArray();
        final GUID guid = new GUID();
        if (COM.IIDFromString(charArray, guid) == 0) {
            return guid;
        }
        return null;
    }
    
    static int callback0(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method0(array2);
    }
    
    static int callback1(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method1(array2);
    }
    
    static int callback2(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method2(array2);
    }
    
    static int callback3(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method3(array2);
    }
    
    static int callback4(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method4(array2);
    }
    
    static int callback5(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method5(array2);
    }
    
    static int callback6(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method6(array2);
    }
    
    static int callback7(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method7(array2);
    }
    
    static int callback8(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method8(array2);
    }
    
    static int callback9(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method9(array2);
    }
    
    static int callback10(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method10(array2);
    }
    
    static int callback11(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method11(array2);
    }
    
    static int callback12(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method12(array2);
    }
    
    static int callback13(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method13(array2);
    }
    
    static int callback14(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method14(array2);
    }
    
    static int callback15(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method15(array2);
    }
    
    static int callback16(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method16(array2);
    }
    
    static int callback17(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method17(array2);
    }
    
    static int callback18(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method18(array2);
    }
    
    static int callback19(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method19(array2);
    }
    
    static int callback20(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method20(array2);
    }
    
    static int callback21(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method21(array2);
    }
    
    static int callback22(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method22(array2);
    }
    
    static int callback23(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method23(array2);
    }
    
    static int callback24(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method24(array2);
    }
    
    static int callback25(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method25(array2);
    }
    
    static int callback26(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method26(array2);
    }
    
    static int callback27(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method27(array2);
    }
    
    static int callback28(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method28(array2);
    }
    
    static int callback29(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method29(array2);
    }
    
    static int callback30(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method30(array2);
    }
    
    static int callback31(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method31(array2);
    }
    
    static int callback32(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method32(array2);
    }
    
    static int callback33(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method33(array2);
    }
    
    static int callback34(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method34(array2);
    }
    
    static int callback35(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method35(array2);
    }
    
    static int callback36(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method36(array2);
    }
    
    static int callback37(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method37(array2);
    }
    
    static int callback38(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method38(array2);
    }
    
    static int callback39(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method39(array2);
    }
    
    static int callback40(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method40(array2);
    }
    
    static int callback41(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method41(array2);
    }
    
    static int callback42(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method42(array2);
    }
    
    static int callback43(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method43(array2);
    }
    
    static int callback44(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method44(array2);
    }
    
    static int callback45(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method45(array2);
    }
    
    static int callback46(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method46(array2);
    }
    
    static int callback47(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method47(array2);
    }
    
    static int callback48(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method48(array2);
    }
    
    static int callback49(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method49(array2);
    }
    
    static int callback50(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method50(array2);
    }
    
    static int callback51(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method51(array2);
    }
    
    static int callback52(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method52(array2);
    }
    
    static int callback53(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method53(array2);
    }
    
    static int callback54(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method54(array2);
    }
    
    static int callback55(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method55(array2);
    }
    
    static int callback56(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method56(array2);
    }
    
    static int callback57(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method57(array2);
    }
    
    static int callback58(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method58(array2);
    }
    
    static int callback59(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method59(array2);
    }
    
    static int callback60(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method60(array2);
    }
    
    static int callback61(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method61(array2);
    }
    
    static int callback62(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method62(array2);
    }
    
    static int callback63(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method63(array2);
    }
    
    static int callback64(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method64(array2);
    }
    
    static int callback65(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method65(array2);
    }
    
    static int callback66(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method66(array2);
    }
    
    static int callback67(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method67(array2);
    }
    
    static int callback68(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method68(array2);
    }
    
    static int callback69(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method69(array2);
    }
    
    static int callback70(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method70(array2);
    }
    
    static int callback71(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method71(array2);
    }
    
    static int callback72(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method72(array2);
    }
    
    static int callback73(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method73(array2);
    }
    
    static int callback74(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method74(array2);
    }
    
    static int callback75(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method75(array2);
    }
    
    static int callback76(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method76(array2);
    }
    
    static int callback77(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method77(array2);
    }
    
    static int callback78(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method78(array2);
    }
    
    static int callback79(final int[] array) {
        final COMObject value = COMObject.ObjectMap.get(new LONG(array[0]));
        if (value == null) {
            return -2147467259;
        }
        final int[] array2 = new int[array.length - 1];
        System.arraycopy(array, 1, array2, 0, array2.length);
        return value.method79(array2);
    }
    
    public void dispose() {
        final int[] array = { 0 };
        OS.MoveMemory(array, this.ppVtable, OS.PTR_SIZEOF);
        OS.GlobalFree(array[0]);
        OS.GlobalFree(this.ppVtable);
        COMObject.ObjectMap.remove(new LONG(this.ppVtable));
        this.ppVtable = 0;
    }
    
    public int getAddress() {
        return this.ppVtable;
    }
    
    public int method0(final int[] array) {
        return -2147467263;
    }
    
    public int method1(final int[] array) {
        return -2147467263;
    }
    
    public int method2(final int[] array) {
        return -2147467263;
    }
    
    public int method3(final int[] array) {
        return -2147467263;
    }
    
    public int method4(final int[] array) {
        return -2147467263;
    }
    
    public int method5(final int[] array) {
        return -2147467263;
    }
    
    public int method6(final int[] array) {
        return -2147467263;
    }
    
    public int method7(final int[] array) {
        return -2147467263;
    }
    
    public int method8(final int[] array) {
        return -2147467263;
    }
    
    public int method9(final int[] array) {
        return -2147467263;
    }
    
    public int method10(final int[] array) {
        return -2147467263;
    }
    
    public int method11(final int[] array) {
        return -2147467263;
    }
    
    public int method12(final int[] array) {
        return -2147467263;
    }
    
    public int method13(final int[] array) {
        return -2147467263;
    }
    
    public int method14(final int[] array) {
        return -2147467263;
    }
    
    public int method15(final int[] array) {
        return -2147467263;
    }
    
    public int method16(final int[] array) {
        return -2147467263;
    }
    
    public int method17(final int[] array) {
        return -2147467263;
    }
    
    public int method18(final int[] array) {
        return -2147467263;
    }
    
    public int method19(final int[] array) {
        return -2147467263;
    }
    
    public int method20(final int[] array) {
        return -2147467263;
    }
    
    public int method21(final int[] array) {
        return -2147467263;
    }
    
    public int method22(final int[] array) {
        return -2147467263;
    }
    
    public int method23(final int[] array) {
        return -2147467263;
    }
    
    public int method24(final int[] array) {
        return -2147467263;
    }
    
    public int method25(final int[] array) {
        return -2147467263;
    }
    
    public int method26(final int[] array) {
        return -2147467263;
    }
    
    public int method27(final int[] array) {
        return -2147467263;
    }
    
    public int method28(final int[] array) {
        return -2147467263;
    }
    
    public int method29(final int[] array) {
        return -2147467263;
    }
    
    public int method30(final int[] array) {
        return -2147467263;
    }
    
    public int method31(final int[] array) {
        return -2147467263;
    }
    
    public int method32(final int[] array) {
        return -2147467263;
    }
    
    public int method33(final int[] array) {
        return -2147467263;
    }
    
    public int method34(final int[] array) {
        return -2147467263;
    }
    
    public int method35(final int[] array) {
        return -2147467263;
    }
    
    public int method36(final int[] array) {
        return -2147467263;
    }
    
    public int method37(final int[] array) {
        return -2147467263;
    }
    
    public int method38(final int[] array) {
        return -2147467263;
    }
    
    public int method39(final int[] array) {
        return -2147467263;
    }
    
    public int method40(final int[] array) {
        return -2147467263;
    }
    
    public int method41(final int[] array) {
        return -2147467263;
    }
    
    public int method42(final int[] array) {
        return -2147467263;
    }
    
    public int method43(final int[] array) {
        return -2147467263;
    }
    
    public int method44(final int[] array) {
        return -2147467263;
    }
    
    public int method45(final int[] array) {
        return -2147467263;
    }
    
    public int method46(final int[] array) {
        return -2147467263;
    }
    
    public int method47(final int[] array) {
        return -2147467263;
    }
    
    public int method48(final int[] array) {
        return -2147467263;
    }
    
    public int method49(final int[] array) {
        return -2147467263;
    }
    
    public int method50(final int[] array) {
        return -2147467263;
    }
    
    public int method51(final int[] array) {
        return -2147467263;
    }
    
    public int method52(final int[] array) {
        return -2147467263;
    }
    
    public int method53(final int[] array) {
        return -2147467263;
    }
    
    public int method54(final int[] array) {
        return -2147467263;
    }
    
    public int method55(final int[] array) {
        return -2147467263;
    }
    
    public int method56(final int[] array) {
        return -2147467263;
    }
    
    public int method57(final int[] array) {
        return -2147467263;
    }
    
    public int method58(final int[] array) {
        return -2147467263;
    }
    
    public int method59(final int[] array) {
        return -2147467263;
    }
    
    public int method60(final int[] array) {
        return -2147467263;
    }
    
    public int method61(final int[] array) {
        return -2147467263;
    }
    
    public int method62(final int[] array) {
        return -2147467263;
    }
    
    public int method63(final int[] array) {
        return -2147467263;
    }
    
    public int method64(final int[] array) {
        return -2147467263;
    }
    
    public int method65(final int[] array) {
        return -2147467263;
    }
    
    public int method66(final int[] array) {
        return -2147467263;
    }
    
    public int method67(final int[] array) {
        return -2147467263;
    }
    
    public int method68(final int[] array) {
        return -2147467263;
    }
    
    public int method69(final int[] array) {
        return -2147467263;
    }
    
    public int method70(final int[] array) {
        return -2147467263;
    }
    
    public int method71(final int[] array) {
        return -2147467263;
    }
    
    public int method72(final int[] array) {
        return -2147467263;
    }
    
    public int method73(final int[] array) {
        return -2147467263;
    }
    
    public int method74(final int[] array) {
        return -2147467263;
    }
    
    public int method75(final int[] array) {
        return -2147467263;
    }
    
    public int method76(final int[] array) {
        return -2147467263;
    }
    
    public int method77(final int[] array) {
        return -2147467263;
    }
    
    public int method78(final int[] array) {
        return -2147467263;
    }
    
    public int method79(final int[] array) {
        return -2147467263;
    }
}
