// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.lang.NNativeType;
import com.neurotec.jna.NLibrary;
import com.neurotec.util.NObjectStaticReadOnlyCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.sun.jna.ptr.ShortByReference;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.WString;
import com.neurotec.lang.NRange;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NVersion;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NObject;

public final class ANRecordType extends NObject
{
    static final ANRecordTypeLibrary LIBRARY;
    public static final int MAX_NUMBER = 99;
    private static HNObject hType1;
    private static HNObject hType2;
    private static HNObject hType3;
    private static HNObject hType4;
    private static HNObject hType5;
    private static HNObject hType6;
    private static HNObject hType7;
    private static HNObject hType8;
    private static HNObject hType9;
    private static HNObject hType10;
    private static HNObject hType13;
    private static HNObject hType14;
    private static HNObject hType15;
    private static HNObject hType16;
    private static HNObject hType17;
    private static HNObject hType99;
    public static ANRecordType Type1;
    public static ANRecordType Type2;
    public static ANRecordType Type3;
    public static ANRecordType Type4;
    public static ANRecordType Type5;
    public static ANRecordType Type6;
    public static ANRecordType Type7;
    public static ANRecordType Type8;
    public static ANRecordType Type9;
    public static ANRecordType Type10;
    public static ANRecordType Type13;
    public static ANRecordType Type14;
    public static ANRecordType Type15;
    public static ANRecordType Type16;
    public static ANRecordType Type17;
    public static ANRecordType Type99;
    public static RecordTypeCollection types;
    
    private ANRecordType(final HNObject handle) {
        super(handle, ANRecordType.NATIVE_TYPE, false);
    }
    
    public static ANRecordType fromHandle(final HNObject handle) {
        if (handle == null || handle.getPointer() == null) {
            throw new NullPointerException("handle");
        }
        if (handle.equals(ANRecordType.hType1)) {
            return ANRecordType.Type1;
        }
        if (handle.equals(ANRecordType.hType2)) {
            return ANRecordType.Type2;
        }
        if (handle.equals(ANRecordType.hType3)) {
            return ANRecordType.Type3;
        }
        if (handle.equals(ANRecordType.hType4)) {
            return ANRecordType.Type4;
        }
        if (handle.equals(ANRecordType.hType5)) {
            return ANRecordType.Type5;
        }
        if (handle.equals(ANRecordType.hType6)) {
            return ANRecordType.Type6;
        }
        if (handle.equals(ANRecordType.hType7)) {
            return ANRecordType.Type7;
        }
        if (handle.equals(ANRecordType.hType8)) {
            return ANRecordType.Type8;
        }
        if (handle.equals(ANRecordType.hType9)) {
            return ANRecordType.Type9;
        }
        if (handle.equals(ANRecordType.hType10)) {
            return ANRecordType.Type10;
        }
        if (handle.equals(ANRecordType.hType13)) {
            return ANRecordType.Type13;
        }
        if (handle.equals(ANRecordType.hType14)) {
            return ANRecordType.Type14;
        }
        if (handle.equals(ANRecordType.hType15)) {
            return ANRecordType.Type15;
        }
        if (handle.equals(ANRecordType.hType16)) {
            return ANRecordType.Type16;
        }
        if (handle.equals(ANRecordType.hType17)) {
            return ANRecordType.Type17;
        }
        if (handle.equals(ANRecordType.hType99)) {
            return ANRecordType.Type99;
        }
        throw new IllegalArgumentException("Unknown ANRecordType handle");
    }
    
    public static ANRecordType getTypeByNumber(final int number) {
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetTypeByNumber(number, rhValue));
        hValue = rhValue.getValue();
        return (hValue == null) ? null : fromHandle(hValue);
    }
    
    public int getMaxFieldNumber(final NVersion version) {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetMaxFieldNumber(this.getHandle(), version.getValue(), rValue));
        return rValue.getValue();
    }
    
    public int[] getStandardFieldNumbers(final NVersion version) {
        final int[] value = new int[NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetStandardFieldNumbersEx(this.getHandle(), version.getValue(), null, 0))];
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetStandardFieldNumbersEx(this.getHandle(), version.getValue(), value, value.length));
        return value;
    }
    
    public NRange[] getUserDefinedFieldNumbers(final NVersion version) {
        final int size = NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetUserDefinedFieldNumbersEx(this.getHandle(), version.getValue(), null, 0));
        final NRange[] ranges = new NRange[size];
        final NRange.NRangeData[] rangesData = new NRange.NRangeData[size];
        for (int i = 0; i < size; ++i) {
            final NRange range = new NRange();
            ranges[i] = range;
            rangesData[i] = range.getData();
        }
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetUserDefinedFieldNumbersEx(this.getHandle(), version.getValue(), rangesData, size));
        return ranges;
    }
    
    public int getFieldNumberById(final NVersion version, final String id) {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetFieldNumberById(this.getHandle(), version.getValue(), new WString(id), rValue));
        return rValue.getValue();
    }
    
    public boolean isFieldKnown(final NVersion version, final int fieldNumber) {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeIsFieldKnown(this.getHandle(), version.getValue(), fieldNumber, rValue));
        return rValue.getValue();
    }
    
    public boolean isFieldStandard(final NVersion version, final int fieldNumber) {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeIsFieldStandard(this.getHandle(), version.getValue(), fieldNumber, rValue));
        return rValue.getValue();
    }
    
    public boolean isFieldMandatory(final NVersion version, final int fieldNumber) {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeIsFieldMandatory(this.getHandle(), version.getValue(), fieldNumber, rValue));
        return rValue.getValue();
    }
    
    public int getFieldInitialItemCount(final NVersion version, final int fieldNumber) {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetFieldInitialItemCount(this.getHandle(), version.getValue(), fieldNumber, rValue));
        return rValue.getValue();
    }
    
    public String getFieldId(final NVersion version, final int fieldNumber) {
        final int pLen = ANRecordType.LIBRARY.ANRecordTypeGetFieldIdEx(this.getHandle(), version.getValue(), fieldNumber, null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANRecordType.LIBRARY.ANRecordTypeGetFieldIdEx(this.getHandle(), version.getValue(), fieldNumber, pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getFieldName(final NVersion version, final int fieldNumber) {
        final int pLen = ANRecordType.LIBRARY.ANRecordTypeGetFieldNameEx(this.getHandle(), version.getValue(), fieldNumber, null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANRecordType.LIBRARY.ANRecordTypeGetFieldNameEx(this.getHandle(), version.getValue(), fieldNumber, pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public NVersion getVersion() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetVersion(this.getHandle(), rValue));
        return new NVersion(rValue.getValue());
    }
    
    public int getNumber() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetNumber(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public String getName() {
        final int pLen = ANRecordType.LIBRARY.ANRecordTypeGetNameEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANRecordType.LIBRARY.ANRecordTypeGetNameEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public ANRecordDataType getDataType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetDataType(this.getHandle(), rValue));
        return ANRecordDataType.get(rValue.getValue());
    }
    
    public int getBinaryFieldsLength() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetBinaryFieldsLength(this.getHandle(), rValue));
        return rValue.getValue().intValue();
    }
    
    static {
        LIBRARY = (ANRecordTypeLibrary)Native.loadLibrary("NBiometricStandards", ANRecordTypeLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        final HNObjectByReference rhType1 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType1(rhType1));
        ANRecordType.hType1 = rhType1.getValue();
        ANRecordType.Type1 = new ANRecordType(ANRecordType.hType1);
        final HNObjectByReference rhType2 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType2(rhType2));
        ANRecordType.hType2 = rhType2.getValue();
        ANRecordType.Type2 = new ANRecordType(ANRecordType.hType2);
        final HNObjectByReference rhType3 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType3(rhType3));
        ANRecordType.hType3 = rhType3.getValue();
        ANRecordType.Type3 = new ANRecordType(ANRecordType.hType3);
        final HNObjectByReference rhType4 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType4(rhType4));
        ANRecordType.hType4 = rhType4.getValue();
        ANRecordType.Type4 = new ANRecordType(ANRecordType.hType4);
        final HNObjectByReference rhType5 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType5(rhType5));
        ANRecordType.hType5 = rhType5.getValue();
        ANRecordType.Type5 = new ANRecordType(ANRecordType.hType5);
        final HNObjectByReference rhType6 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType6(rhType6));
        ANRecordType.hType6 = rhType6.getValue();
        ANRecordType.Type6 = new ANRecordType(ANRecordType.hType6);
        final HNObjectByReference rhType7 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType7(rhType7));
        ANRecordType.hType7 = rhType7.getValue();
        ANRecordType.Type7 = new ANRecordType(ANRecordType.hType7);
        final HNObjectByReference rhType8 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType8(rhType8));
        ANRecordType.hType8 = rhType8.getValue();
        ANRecordType.Type8 = new ANRecordType(ANRecordType.hType8);
        final HNObjectByReference rhType9 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType9(rhType9));
        ANRecordType.hType9 = rhType9.getValue();
        ANRecordType.Type9 = new ANRecordType(ANRecordType.hType9);
        final HNObjectByReference rhType10 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType10(rhType10));
        ANRecordType.hType10 = rhType10.getValue();
        ANRecordType.Type10 = new ANRecordType(ANRecordType.hType10);
        final HNObjectByReference rhType11 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType13(rhType11));
        ANRecordType.hType13 = rhType11.getValue();
        ANRecordType.Type13 = new ANRecordType(ANRecordType.hType13);
        final HNObjectByReference rhType12 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType14(rhType12));
        ANRecordType.hType14 = rhType12.getValue();
        ANRecordType.Type14 = new ANRecordType(ANRecordType.hType14);
        final HNObjectByReference rhType13 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType15(rhType13));
        ANRecordType.hType15 = rhType13.getValue();
        ANRecordType.Type15 = new ANRecordType(ANRecordType.hType15);
        final HNObjectByReference rhType14 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType16(rhType14));
        ANRecordType.hType16 = rhType14.getValue();
        ANRecordType.Type16 = new ANRecordType(ANRecordType.hType16);
        final HNObjectByReference rhType15 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType17(rhType15));
        ANRecordType.hType17 = rhType15.getValue();
        ANRecordType.Type17 = new ANRecordType(ANRecordType.hType17);
        final HNObjectByReference rhType16 = new HNObjectByReference();
        NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType99(rhType16));
        ANRecordType.hType99 = rhType16.getValue();
        ANRecordType.Type99 = new ANRecordType(ANRecordType.hType99);
        ANRecordType.types = new RecordTypeCollection();
    }
    
    public static final class RecordTypeCollection extends NObjectStaticReadOnlyCollection<ANRecordType>
    {
        protected ANRecordType fromHandleNative(final HNObject handle) {
            return ANRecordType.fromHandle(handle);
        }
        
        protected ANRecordType fromHandleWithObject(final HNObject hItem, final NObject owner) {
            throw new UnsupportedOperationException();
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rValue = new HNObjectByReference();
            NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetType(index, rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANRecordType.LIBRARY.ANRecordTypeGetTypeCount(rValue));
            return rValue.getValue();
        }
    }
    
    interface ANRecordTypeLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANRecordTypeTypeOf();
        
        int ANRecordTypeGetTypeCount(final IntByReference p0);
        
        int ANRecordTypeGetType(final int p0, final HNObjectByReference p1);
        
        int ANRecordTypeGetTypeByNumber(final int p0, final HNObjectByReference p1);
        
        int ANRecordTypeGetType1(final HNObjectByReference p0);
        
        int ANRecordTypeGetType2(final HNObjectByReference p0);
        
        int ANRecordTypeGetType3(final HNObjectByReference p0);
        
        int ANRecordTypeGetType4(final HNObjectByReference p0);
        
        int ANRecordTypeGetType5(final HNObjectByReference p0);
        
        int ANRecordTypeGetType6(final HNObjectByReference p0);
        
        int ANRecordTypeGetType7(final HNObjectByReference p0);
        
        int ANRecordTypeGetType8(final HNObjectByReference p0);
        
        int ANRecordTypeGetType9(final HNObjectByReference p0);
        
        int ANRecordTypeGetType10(final HNObjectByReference p0);
        
        int ANRecordTypeGetType13(final HNObjectByReference p0);
        
        int ANRecordTypeGetType14(final HNObjectByReference p0);
        
        int ANRecordTypeGetType15(final HNObjectByReference p0);
        
        int ANRecordTypeGetType16(final HNObjectByReference p0);
        
        int ANRecordTypeGetType17(final HNObjectByReference p0);
        
        int ANRecordTypeGetType99(final HNObjectByReference p0);
        
        int ANRecordTypeGetMaxFieldNumber(final HNObject p0, final short p1, final IntByReference p2);
        
        int ANRecordTypeGetStandardFieldNumbersEx(final HNObject p0, final short p1, final int[] p2, final int p3);
        
        int ANRecordTypeGetUserDefinedFieldNumbersEx(final HNObject p0, final short p1, final NRange.NRangeData[] p2, final int p3);
        
        int ANRecordTypeGetFieldNumberById(final HNObject p0, final short p1, final WString p2, final IntByReference p3);
        
        int ANRecordTypeIsFieldKnown(final HNObject p0, final short p1, final int p2, final BooleanByReference p3);
        
        int ANRecordTypeIsFieldStandard(final HNObject p0, final short p1, final int p2, final BooleanByReference p3);
        
        int ANRecordTypeIsFieldMandatory(final HNObject p0, final short p1, final int p2, final BooleanByReference p3);
        
        int ANRecordTypeGetFieldInitialItemCount(final HNObject p0, final short p1, final int p2, final IntByReference p3);
        
        int ANRecordTypeGetFieldIdEx(final HNObject p0, final short p1, final int p2, final Pointer p3, final int p4);
        
        int ANRecordTypeGetFieldNameEx(final HNObject p0, final short p1, final int p2, final Pointer p3, final int p4);
        
        int ANRecordTypeGetVersion(final HNObject p0, final ShortByReference p1);
        
        int ANRecordTypeGetNumber(final HNObject p0, final IntByReference p1);
        
        int ANRecordTypeGetNameEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANRecordTypeGetDataType(final HNObject p0, final IntByReference p1);
        
        int ANRecordTypeGetBinaryFieldsLength(final HNObject p0, final NativeSizeByReference p1);
    }
}
