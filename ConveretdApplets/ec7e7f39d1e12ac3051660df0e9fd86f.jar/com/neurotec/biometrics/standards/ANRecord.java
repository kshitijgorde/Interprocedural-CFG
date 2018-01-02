// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NObjectCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.BooleanByReference;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.lang.NCore;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.sun.jna.ptr.PointerByReference;
import java.nio.ByteBuffer;
import java.util.Iterator;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public abstract class ANRecord extends NObject
{
    private ANRecordType recordType;
    private FieldCollection fields;
    static final ANRecordLibrary LIBRARY;
    public static final int MAX_FIELD_NUMBER = 999;
    public static final int FIELD_LEN = 1;
    public static final int FIELD_IDC = 2;
    public static final int FIELD_DATA = 999;
    public static final int MAX_IDC = 255;
    public static final int FLAG_MERGE_DUBLICATE_FIELDS = 256;
    public static final int FLAG_RECOVER_FROM_BINARY_DATA = 512;
    public static final NNativeType NATIVE_TYPE;
    
    ANRecord(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType, false);
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordGetRecordType(this.getHandle(), rhValue));
        this.recordType = ANRecordType.fromHandle(rhValue.getValue());
        this.fields = new FieldCollection(this);
    }
    
    static ANRecord fromHandle(final HNObject handle) {
        if (ANType1Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType1Record(handle);
        }
        if (ANType2Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType2Record(handle);
        }
        if (ANType3Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType3Record(handle);
        }
        if (ANType4Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType4Record(handle);
        }
        if (ANType5Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType5Record(handle);
        }
        if (ANType6Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType6Record(handle);
        }
        if (ANType7Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType7Record(handle);
        }
        if (ANType8Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType8Record(handle);
        }
        if (ANType9Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType9Record(handle);
        }
        if (ANType10Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType10Record(handle);
        }
        if (ANType13Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType13Record(handle);
        }
        if (ANType14Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType14Record(handle);
        }
        if (ANType15Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType15Record(handle);
        }
        if (ANType16Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType16Record(handle);
        }
        if (ANType17Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType17Record(handle);
        }
        if (ANType99Record.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new ANType99Record(handle);
        }
        throw new IllegalArgumentException("The ANRecord handle native type is unknown");
    }
    
    public final void refresh() {
        this.fields.onChanged();
    }
    
    public final ANField getFieldByNumber(final int fieldNumber) {
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordGetFieldByNumber(this.getHandle(), fieldNumber, rhValue));
        hValue = rhValue.getValue();
        if (hValue == null) {
            return null;
        }
        for (final ANField field : this.fields) {
            if (hValue.equals(field.getHandle())) {
                return field;
            }
        }
        throw new RuntimeException("Field handle is not equal to any record field handle");
    }
    
    public ByteBuffer getBinaryFields() {
        final PointerByReference rBinaryFields = new PointerByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordGetBinaryFieldsPtr(this.getHandle(), rBinaryFields));
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordGetBinaryFieldsLength(this.getHandle(), rSize));
        return NCore.getByteBuffer(rBinaryFields.getValue(), rSize.getValue().longValue(), false);
    }
    
    public final void setBinaryFields(final ByteBuffer value) {
        if (value == null) {
            throw new NullPointerException("value");
        }
        NResult.check(ANRecord.LIBRARY.ANRecordSetBinaryFieldsEx(this.getHandle(), value.slice(), new NativeSize((long)value.remaining())));
    }
    
    public final void setDataLength(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value is less than zero");
        }
        NResult.check(ANRecord.LIBRARY.ANRecordSetDataLength(this.getHandle(), new NativeSize((long)value)));
    }
    
    public final ByteBuffer getData() {
        final PointerByReference rData = new PointerByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordGetDataPtr(this.getHandle(), rData));
        final NativeSizeByReference rDataLength = new NativeSizeByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordGetDataLength(this.getHandle(), rDataLength));
        return NCore.getByteBuffer(rData.getValue(), rDataLength.getValue().longValue(), false);
    }
    
    public final void setData(final ByteBuffer value) {
        if (value == null) {
            throw new NullPointerException("value");
        }
        NResult.check(ANRecord.LIBRARY.ANRecordSetData(this.getHandle(), value.slice(), new NativeSize((long)value.remaining())));
    }
    
    public final ANTemplate getOwner() {
        return (ANTemplate)super.getOwner();
    }
    
    public final ANRecordType getRecordType() {
        return this.recordType;
    }
    
    public final boolean isValidated() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordIsValidated(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final int size() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordGetLength(this.getHandle(), rValue));
        return rValue.getValue().intValue();
    }
    
    public final int getIdc() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordGetIdc(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final void setIdc(final int value) {
        NResult.check(ANRecord.LIBRARY.ANRecordSetIdc(this.getHandle(), value));
    }
    
    public int getBinaryFieldsLength() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordGetBinaryFieldsLength(this.getHandle(), rValue));
        return rValue.getValue().intValue();
    }
    
    public final int getDataLength() {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(ANRecord.LIBRARY.ANRecordGetDataLength(this.getHandle(), rValue));
        return rValue.getValue().intValue();
    }
    
    public final FieldCollection getFields() {
        this.check();
        return this.fields;
    }
    
    protected final void dispose(final boolean disposing) {
        if (disposing) {
            this.fields.disposeItems();
        }
        this.fields = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (ANRecordLibrary)Native.loadLibrary("NBiometricStandards", ANRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANRecord.LIBRARY.ANRecordTypeOf());
    }
    
    public final class FieldCollection extends NObjectCollection<ANField>
    {
        FieldCollection(final NObject owner) {
            super(owner, false);
        }
        
        void onChanged() {
            this.refreshItems();
        }
        
        public ANField add(final int fieldNumber) {
            return this.add(fieldNumber, 1, 0);
        }
        
        public ANField add(final int fieldNumber, final int fieldIndex) {
            return this.add(fieldNumber, 1, fieldIndex);
        }
        
        public ANField add(final int fieldNumber, final int itemCount, final int fieldIndex) {
            ANField value = null;
            final IntByReference rFieldIndex = new IntByReference(fieldIndex);
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANRecord.LIBRARY.ANRecordAddField(this.getOwner().getHandle(), fieldNumber, itemCount, rFieldIndex, rhValue));
            try {
                value = this.addItem(rFieldIndex.getValue(), rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANRecord.LIBRARY.ANRecordRemoveField(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public int indexOf(final int fieldNumber) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANRecord.LIBRARY.ANRecordGetFieldIndexByNumber(this.getOwner().getHandle(), fieldNumber, rValue));
            return rValue.getValue();
        }
        
        public boolean contains(final int fieldNumber) {
            return this.indexOf(fieldNumber) != -1;
        }
        
        protected void clearNative() {
            throw new UnsupportedOperationException();
        }
        
        protected ANField fromHandleNative(final HNObject handle) {
            return new ANField(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANRecord.LIBRARY.ANRecordGetField(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANRecord.LIBRARY.ANRecordGetFieldCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(ANRecord.LIBRARY.ANRecordSetFieldCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANRecord.LIBRARY.ANRecordGetFieldCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANRecord.LIBRARY.ANRecordRemoveField(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    interface ANRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANRecordTypeOf();
        
        int ANRecordGetFieldCount(final HNObject p0, final IntByReference p1);
        
        int ANRecordGetField(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int ANRecordGetFieldCapacity(final HNObject p0, final IntByReference p1);
        
        int ANRecordSetFieldCapacity(final HNObject p0, final int p1);
        
        int ANRecordGetFieldIndexByNumber(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANRecordAddField(final HNObject p0, final int p1, final int p2, final IntByReference p3, final HNObjectByReference p4);
        
        int ANRecordRemoveField(final HNObject p0, final int p1);
        
        int ANRecordGetFieldByNumber(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int ANRecordGetRecordType(final HNObject p0, final HNObjectByReference p1);
        
        int ANRecordIsValidated(final HNObject p0, final BooleanByReference p1);
        
        int ANRecordGetLength(final HNObject p0, final NativeSizeByReference p1);
        
        int ANRecordGetIdc(final HNObject p0, final IntByReference p1);
        
        int ANRecordSetIdc(final HNObject p0, final int p1);
        
        int ANRecordSetBinaryFieldsEx(final HNObject p0, final ByteBuffer p1, final NativeSize p2);
        
        int ANRecordGetBinaryFieldsLength(final HNObject p0, final NativeSizeByReference p1);
        
        int ANRecordGetBinaryFieldsPtr(final HNObject p0, final PointerByReference p1);
        
        int ANRecordGetDataLength(final HNObject p0, final NativeSizeByReference p1);
        
        int ANRecordSetDataLength(final HNObject p0, final NativeSize p1);
        
        int ANRecordGetDataPtr(final HNObject p0, final PointerByReference p1);
        
        int ANRecordSetData(final HNObject p0, final ByteBuffer p1, final NativeSize p2);
    }
}
