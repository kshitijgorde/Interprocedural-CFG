// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.VARIANT;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.ole.win32.IDispatch;

public final class Variant
{
    public static final int sizeof;
    private short type;
    private boolean booleanData;
    private byte byteData;
    private short shortData;
    private char charData;
    private int intData;
    private long longData;
    private float floatData;
    private double doubleData;
    private String stringData;
    private int byRefPtr;
    private IDispatch dispatchData;
    private IUnknown unknownData;
    public static final Variant NULL;
    
    static {
        sizeof = VARIANT.sizeof;
        NULL = new Variant();
        Variant.NULL.type = 1;
    }
    
    public static void win32_copy(final int n, final Variant variant) {
        variant.getData(n);
    }
    
    public static Variant win32_new(final int data) {
        final Variant variant = new Variant();
        variant.setData(data);
        return variant;
    }
    
    public Variant() {
        this.type = 0;
    }
    
    public Variant(final float floatData) {
        this.type = 4;
        this.floatData = floatData;
    }
    
    public Variant(final double doubleData) {
        this.type = 5;
        this.doubleData = doubleData;
    }
    
    public Variant(final int intData) {
        this.type = 3;
        this.intData = intData;
    }
    
    public Variant(final int byRefPtr, final short type) {
        this.type = type;
        this.byRefPtr = byRefPtr;
    }
    
    public Variant(final OleAutomation oleAutomation) {
        this.type = 9;
        this.dispatchData = new IDispatch(oleAutomation.getAddress());
    }
    
    public Variant(final IDispatch dispatchData) {
        this.type = 9;
        this.dispatchData = dispatchData;
    }
    
    public Variant(final IUnknown unknownData) {
        this.type = 13;
        this.unknownData = unknownData;
    }
    
    public Variant(final long longData) {
        this.type = 20;
        this.longData = longData;
    }
    
    public Variant(final String stringData) {
        this.type = 8;
        this.stringData = stringData;
    }
    
    public Variant(final short shortData) {
        this.type = 2;
        this.shortData = shortData;
    }
    
    public Variant(final boolean booleanData) {
        this.type = 11;
        this.booleanData = booleanData;
    }
    
    public void dispose() {
        if ((this.type & 0x4000) == 0x4000) {
            return;
        }
        switch (this.type) {
            case 9: {
                this.dispatchData.Release();
                break;
            }
            case 13: {
                this.unknownData.Release();
                break;
            }
        }
    }
    
    public OleAutomation getAutomation() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 9) {
            return new OleAutomation(this.dispatchData);
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)9);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getAutomation();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public IDispatch getDispatch() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 9) {
            return this.dispatchData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)9);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getDispatch();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public boolean getBoolean() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 11) {
            return this.booleanData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)11);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getBoolean();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public int getByRef() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if ((this.type & 0x4000) == 0x4000) {
            return this.byRefPtr;
        }
        return 0;
    }
    
    public byte getByte() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 16) {
            return this.byteData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)16);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getByte();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public char getChar() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 18) {
            return this.charData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)18);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getChar();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    void getData(final int n) {
        if (n == 0) {
            OLE.error(1007);
        }
        COM.VariantInit(n);
        if ((this.type & 0x4000) == 0x4000) {
            OS.MoveMemory(n, new short[] { this.type }, 2);
            OS.MoveMemory(n + 8, new int[] { this.byRefPtr }, OS.PTR_SIZEOF);
            return;
        }
        switch (this.type) {
            case 0:
            case 1: {
                OS.MoveMemory(n, new short[] { this.type }, 2);
                break;
            }
            case 11: {
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new short[] { this.booleanData ? -1 : 0 }, 2);
                break;
            }
            case 16: {
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new byte[] { this.byteData }, 1);
                break;
            }
            case 2: {
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new short[] { this.shortData }, 2);
                break;
            }
            case 18: {
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new char[] { this.charData }, 2);
                break;
            }
            case 3: {
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new int[] { this.intData }, 4);
                break;
            }
            case 20: {
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new long[] { this.longData }, 8);
                break;
            }
            case 4: {
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new float[] { this.floatData }, 4);
                break;
            }
            case 5: {
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new double[] { this.doubleData }, 8);
                break;
            }
            case 9: {
                this.dispatchData.AddRef();
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new int[] { this.dispatchData.getAddress() }, OS.PTR_SIZEOF);
                break;
            }
            case 13: {
                this.unknownData.AddRef();
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new int[] { this.unknownData.getAddress() }, OS.PTR_SIZEOF);
                break;
            }
            case 8: {
                OS.MoveMemory(n, new short[] { this.type }, 2);
                OS.MoveMemory(n + 8, new int[] { COM.SysAllocString((String.valueOf(this.stringData) + "\u0000").toCharArray()) }, OS.PTR_SIZEOF);
                break;
            }
            default: {
                OLE.error(20);
                break;
            }
        }
    }
    
    public double getDouble() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 5) {
            return this.doubleData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)5);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getDouble();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public float getFloat() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 4) {
            return this.floatData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)4);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getFloat();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public int getInt() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 3) {
            return this.intData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)3);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getInt();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public long getLong() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 20) {
            return this.longData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)20);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getLong();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public short getShort() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 2) {
            return this.shortData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)2);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getShort();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public String getString() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 8) {
            return this.stringData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)8);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getString();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public short getType() {
        return this.type;
    }
    
    public IUnknown getUnknown() {
        if (this.type == 0) {
            OLE.error(1010, -1);
        }
        if (this.type == 13) {
            return this.unknownData;
        }
        final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
        final int globalAlloc2 = OS.GlobalAlloc(64, Variant.sizeof);
        try {
            this.getData(globalAlloc);
            final int variantChangeType = COM.VariantChangeType(globalAlloc2, globalAlloc, (short)0, (short)13);
            if (variantChangeType != 0) {
                OLE.error(1010, variantChangeType);
            }
            final Variant variant = new Variant();
            variant.setData(globalAlloc2);
            return variant.getUnknown();
        }
        finally {
            COM.VariantClear(globalAlloc);
            OS.GlobalFree(globalAlloc);
            COM.VariantClear(globalAlloc2);
            OS.GlobalFree(globalAlloc2);
        }
    }
    
    public void setByRef(final boolean b) {
        if ((this.type & 0x4000) == 0x0 || (this.type & 0xB) == 0x0) {
            OLE.error(1010);
        }
        OS.MoveMemory(this.byRefPtr, new short[] { b ? -1 : 0 }, 2);
    }
    
    public void setByRef(final float n) {
        if ((this.type & 0x4000) == 0x0 || (this.type & 0x4) == 0x0) {
            OLE.error(1010);
        }
        OS.MoveMemory(this.byRefPtr, new float[] { n }, 4);
    }
    
    public void setByRef(final int n) {
        if ((this.type & 0x4000) == 0x0 || (this.type & 0x3) == 0x0) {
            OLE.error(1010);
        }
        OS.MoveMemory(this.byRefPtr, new int[] { n }, OS.PTR_SIZEOF);
    }
    
    public void setByRef(final short n) {
        if ((this.type & 0x4000) == 0x0 || (this.type & 0x2) == 0x0) {
            OLE.error(1010);
        }
        OS.MoveMemory(this.byRefPtr, new short[] { n }, 2);
    }
    
    void setData(final int n) {
        if (n == 0) {
            OLE.error(5);
        }
        final short[] array = { 0 };
        OS.MoveMemory(array, n, 2);
        this.type = array[0];
        if ((this.type & 0x4000) == 0x4000) {
            final int[] array2 = { 0 };
            OS.MoveMemory(array2, n + 8, OS.PTR_SIZEOF);
            this.byRefPtr = array2[0];
            return;
        }
        switch (this.type) {
            case 0:
            case 1: {
                break;
            }
            case 11: {
                final short[] array3 = { 0 };
                OS.MoveMemory(array3, n + 8, 2);
                this.booleanData = (array3[0] != 0);
                break;
            }
            case 16: {
                final byte[] array4 = { 0 };
                OS.MoveMemory(array4, n + 8, 1);
                this.byteData = array4[0];
                break;
            }
            case 2: {
                final short[] array5 = { 0 };
                OS.MoveMemory(array5, n + 8, 2);
                this.shortData = array5[0];
                break;
            }
            case 18: {
                final char[] array6 = { '\0' };
                OS.MoveMemory(array6, n + 8, 2);
                this.charData = array6[0];
                break;
            }
            case 3: {
                final int[] array7 = { 0 };
                OS.MoveMemory(array7, n + 8, 4);
                this.intData = array7[0];
                break;
            }
            case 20: {
                final long[] array8 = { 0L };
                OS.MoveMemory(array8, n + 8, 8);
                this.longData = array8[0];
                break;
            }
            case 4: {
                final float[] array9 = { 0.0f };
                OS.MoveMemory(array9, n + 8, 4);
                this.floatData = array9[0];
                break;
            }
            case 5: {
                final double[] array10 = { 0.0 };
                OS.MoveMemory(array10, n + 8, 8);
                this.doubleData = array10[0];
                break;
            }
            case 9: {
                final int[] array11 = { 0 };
                OS.MoveMemory(array11, n + 8, OS.PTR_SIZEOF);
                if (array11[0] == 0) {
                    this.type = 0;
                    break;
                }
                (this.dispatchData = new IDispatch(array11[0])).AddRef();
                break;
            }
            case 13: {
                final int[] array12 = { 0 };
                OS.MoveMemory(array12, n + 8, OS.PTR_SIZEOF);
                if (array12[0] == 0) {
                    this.type = 0;
                    break;
                }
                (this.unknownData = new IUnknown(array12[0])).AddRef();
                break;
            }
            case 8: {
                final int[] array13 = { 0 };
                OS.MoveMemory(array13, n + 8, OS.PTR_SIZEOF);
                if (array13[0] == 0) {
                    this.type = 0;
                    break;
                }
                final int sysStringByteLen = COM.SysStringByteLen(array13[0]);
                if (sysStringByteLen > 0) {
                    final char[] array14 = new char[(sysStringByteLen + 1) / 2];
                    OS.MoveMemory(array14, array13[0], sysStringByteLen);
                    this.stringData = new String(array14);
                    break;
                }
                this.stringData = "";
                break;
            }
            default: {
                final int globalAlloc = OS.GlobalAlloc(64, Variant.sizeof);
                if (COM.VariantChangeType(globalAlloc, n, (short)0, (short)4) == 0) {
                    this.setData(globalAlloc);
                }
                else if (COM.VariantChangeType(globalAlloc, n, (short)0, (short)3) == 0) {
                    this.setData(globalAlloc);
                }
                else if (COM.VariantChangeType(globalAlloc, n, (short)0, (short)8) == 0) {
                    this.setData(globalAlloc);
                }
                COM.VariantClear(globalAlloc);
                OS.GlobalFree(globalAlloc);
                break;
            }
        }
    }
    
    public String toString() {
        switch (this.type) {
            case 11: {
                return "VT_BOOL{" + this.booleanData + "}";
            }
            case 16: {
                return "VT_I1{" + this.byteData + "}";
            }
            case 2: {
                return "VT_I2{" + this.shortData + "}";
            }
            case 18: {
                return "VT_UI2{" + this.charData + "}";
            }
            case 3: {
                return "VT_I4{" + this.intData + "}";
            }
            case 20: {
                return "VT_I8{" + this.longData + "}";
            }
            case 4: {
                return "VT_R4{" + this.floatData + "}";
            }
            case 5: {
                return "VT_R8{" + this.doubleData + "}";
            }
            case 8: {
                return "VT_BSTR{" + this.stringData + "}";
            }
            case 9: {
                return "VT_DISPATCH{" + ((this.dispatchData == null) ? 0 : this.dispatchData.getAddress()) + "}";
            }
            case 13: {
                return "VT_UNKNOWN{" + ((this.unknownData == null) ? 0 : this.unknownData.getAddress()) + "}";
            }
            case 0: {
                return "VT_EMPTY";
            }
            case 1: {
                return "VT_NULL";
            }
            default: {
                if ((this.type & 0x4000) != 0x0) {
                    return "VT_BYREF|" + (this.type & 0xFFFFBFFF) + "{" + this.byRefPtr + "}";
                }
                return "Unsupported Type " + this.type;
            }
        }
    }
}
