// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.util.NObjectCollection;
import com.neurotec.util.NSimpleCollection;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.Pointer;
import com.sun.jna.Native;
import com.neurotec.lang.NCore;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class ANField extends NObject
{
    private ItemCollection items;
    private SubFieldCollection subFields;
    static final ANFieldLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    ANField(final HNObject handle) {
        super(handle, ANField.NATIVE_TYPE, false);
        this.items = new ItemCollection(this);
        this.subFields = new SubFieldCollection(this);
    }
    
    public void refresh() {
        this.subFields.onChanged();
    }
    
    public char[] getValue(final int length) {
        if (NResult.isFailed(length)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(length + 1);
        try {
            if (NResult.isFailed(ANField.LIBRARY.ANFieldGetValue(this.getHandle(), pValue, length))) {
                return null;
            }
            pValue.setChar((length + 1) * Native.WCHAR_SIZE, '0');
            return pValue.getString(0L, true).toCharArray();
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public int getValueAsNumber() {
        return this.getValueAsNumber(10);
    }
    
    public int getValueAsNumber(final int base) {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANField.LIBRARY.ANFieldGetValueAsNumber(this.getHandle(), base, rValue));
        return rValue.getValue();
    }
    
    public void set(final String value) {
        NResult.check(ANField.LIBRARY.ANFieldSetValueAsString(this.getHandle(), new WString(value)));
    }
    
    public void set(final int value) {
        this.set(value, 1, 10);
    }
    
    public void set(final int value, final int minDigits) {
        this.set(value, minDigits, 10);
    }
    
    public void set(final int value, final int minDigits, final int base) {
        NResult.check(ANField.LIBRARY.ANFieldSetValueAsNumber(this.getHandle(), value, minDigits, base));
    }
    
    public ANRecord getOwner() {
        return (ANRecord)super.getOwner();
    }
    
    public ItemCollection getItems() {
        this.check();
        return this.items;
    }
    
    public SubFieldCollection getSubFields() {
        this.check();
        return this.subFields;
    }
    
    public char[] getValue() {
        final int pLen = ANField.LIBRARY.ANFieldGetValue(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen + 1);
        try {
            if (NResult.isFailed(ANField.LIBRARY.ANFieldGetValue(this.getHandle(), pValue, pLen))) {
                return null;
            }
            pValue.setChar((pLen + 1) * Native.WCHAR_SIZE, '0');
            return pValue.getString(0L, true).toCharArray();
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setValue(final char[] value) {
        NResult.check(ANField.LIBRARY.ANFieldSetValue(this.getHandle(), new WString(new String(value)), value.length));
    }
    
    public String getValueAsString() {
        final int pLen = ANField.LIBRARY.ANFieldGetValueAsStringEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANField.LIBRARY.ANFieldGetValueAsStringEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setValueAsString(final String value) {
        NResult.check(ANField.LIBRARY.ANFieldSetValueAsString(this.getHandle(), new WString(value)));
    }
    
    public int getNumber() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANField.LIBRARY.ANFieldGetNumber(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    protected void dispose(final boolean disposing) {
        if (disposing) {
            this.subFields.disposeItems();
        }
        this.items = null;
        this.subFields = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (ANFieldLibrary)Native.loadLibrary("NBiometricStandards", ANFieldLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANField.LIBRARY.ANFieldTypeOf());
    }
    
    public final class ItemCollection extends NSimpleCollection<char[]>
    {
        ItemCollection(final ANField owner) {
            super(owner, true, false, false);
        }
        
        public int add() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANField.LIBRARY.ANFieldAddItem(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        public int addRange(final int count) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANField.LIBRARY.ANFieldAddItemRange(this.getOwner().getHandle(), count, rValue));
            return rValue.getValue();
        }
        
        public void insert(final int index) {
            NResult.check(ANField.LIBRARY.ANFieldInsertItem(this.getOwner().getHandle(), index));
        }
        
        public void insertRange(final int index, final int count) {
            NResult.check(ANField.LIBRARY.ANFieldInsertItemRange(this.getOwner().getHandle(), index, count));
        }
        
        public char[] get(final int index, final int length) {
            if (NResult.isFailed(length)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(length + 1);
            try {
                if (NResult.isFailed(ANField.LIBRARY.ANFieldGetItem(this.getOwner().getHandle(), index, pValue, length))) {
                    return null;
                }
                pValue.setChar((length + 1) * Native.WCHAR_SIZE, '0');
                return pValue.getString(0L, true).toCharArray();
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        public String getAsString(final int index) {
            final int pLen = ANField.LIBRARY.ANFieldGetItemAsStringEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(ANField.LIBRARY.ANFieldGetItemAsStringEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                return pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        public int getAsNumber(final int index) {
            return this.getAsNumber(index, 10);
        }
        
        public int getAsNumber(final int index, final int base) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANField.LIBRARY.ANFieldGetItemAsNumber(this.getOwner().getHandle(), index, base, rValue));
            return rValue.getValue();
        }
        
        public void set(final int index, final String value) {
            NResult.check(ANField.LIBRARY.ANFieldSetItemAsString(this.getOwner().getHandle(), index, new WString(value)));
        }
        
        public void set(final int index, final int value) {
            this.set(index, value, 1, 10);
        }
        
        public void set(final int index, final int value, final int minDigits) {
            this.set(index, value, minDigits, 10);
        }
        
        public void set(final int index, final int value, final int minDigits, final int base) {
            NResult.check(ANField.LIBRARY.ANFieldSetItemAsNumber(this.getOwner().getHandle(), index, value, minDigits, base));
        }
        
        protected void clearNative() {
            throw new UnsupportedOperationException();
        }
        
        protected char[] getNative(final int index) {
            final int pLen = ANField.LIBRARY.ANFieldGetItem(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen + 1);
            try {
                if (NResult.isFailed(ANField.LIBRARY.ANFieldGetItem(this.getOwner().getHandle(), index, pValue, pLen))) {
                    return null;
                }
                pValue.setChar((pLen + 1) * Native.WCHAR_SIZE, '0');
                return pValue.getString(0L, true).toCharArray();
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANField.LIBRARY.ANFieldGetItemCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(ANField.LIBRARY.ANFieldSetItemCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANField.LIBRARY.ANFieldGetItemCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANField.LIBRARY.ANFieldRemoveItem(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final char[] value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            NResult.check(ANField.LIBRARY.ANFieldRemoveItemRange(this.getOwner().getHandle(), index, count));
        }
        
        protected void addNative(final char[] value) {
            throw new UnsupportedOperationException();
        }
        
        protected void addNative(final int index, final char[] value) {
            throw new UnsupportedOperationException();
        }
        
        protected int addWithIndexNative(final char[] value) {
            throw new UnsupportedOperationException();
        }
        
        protected char[][] getAllNative() {
            throw new UnsupportedOperationException();
        }
        
        protected void setNative(final int index, final char[] value) {
            NResult.check(ANField.LIBRARY.ANFieldSetItem(this.getOwner().getHandle(), index, value, value.length));
        }
    }
    
    public final class SubFieldCollection extends NObjectCollection<ANSubField>
    {
        SubFieldCollection(final NObject owner) {
            super(owner, true);
        }
        
        void onRemoveRange(final int index, final int count) {
            this.removeItemRange(index, count);
        }
        
        void onChanged() {
            this.refreshItems();
        }
        
        public ANSubField add() {
            return this.add(1);
        }
        
        public ANSubField add(final int itemCount) {
            ANSubField value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANField.LIBRARY.ANFieldAddSubField(this.getOwner().getHandle(), itemCount, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANField.LIBRARY.ANFieldRemoveSubField(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public ANSubField insert(final int index) {
            return this.insert(index, 1);
        }
        
        public ANSubField insert(final int index, final int itemCount) {
            ANSubField value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANField.LIBRARY.ANFieldInsertSubField(this.getOwner().getHandle(), index, itemCount, rhValue));
            try {
                value = this.addItem(index, rhValue.getValue());
            }
            finally {
                if (value == null) {
                    ANField.LIBRARY.ANFieldRemoveSubField(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        protected void clearNative() {
            throw new UnsupportedOperationException();
        }
        
        protected ANSubField fromHandleNative(final HNObject handle) {
            return new ANSubField(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(ANField.LIBRARY.ANFieldGetSubField(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANField.LIBRARY.ANFieldGetSubFieldCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(ANField.LIBRARY.ANFieldSetSubFieldCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANField.LIBRARY.ANFieldGetSubFieldCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANField.LIBRARY.ANFieldRemoveSubField(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            NResult.check(ANField.LIBRARY.ANFieldRemoveSubFieldRange(this.getOwner().getHandle(), index, count));
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    interface ANFieldLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANFieldTypeOf();
        
        int ANFieldGetItemCount(final HNObject p0, final IntByReference p1);
        
        int ANFieldGetItemCapacity(final HNObject p0, final IntByReference p1);
        
        int ANFieldSetItemCapacity(final HNObject p0, final int p1);
        
        int ANFieldGetItem(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANFieldGetItemAsStringEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANFieldGetItemAsNumber(final HNObject p0, final int p1, final int p2, final IntByReference p3);
        
        int ANFieldSetItem(final HNObject p0, final int p1, final char[] p2, final int p3);
        
        int ANFieldSetItemAsString(final HNObject p0, final int p1, final WString p2);
        
        int ANFieldSetItemAsNumber(final HNObject p0, final int p1, final int p2, final int p3, final int p4);
        
        int ANFieldAddItem(final HNObject p0, final IntByReference p1);
        
        int ANFieldAddItemRange(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANFieldInsertItem(final HNObject p0, final int p1);
        
        int ANFieldInsertItemRange(final HNObject p0, final int p1, final int p2);
        
        int ANFieldRemoveItem(final HNObject p0, final int p1);
        
        int ANFieldRemoveItemRange(final HNObject p0, final int p1, final int p2);
        
        int ANFieldGetSubFieldCount(final HNObject p0, final IntByReference p1);
        
        int ANFieldGetSubField(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int ANFieldGetSubFieldCapacity(final HNObject p0, final IntByReference p1);
        
        int ANFieldSetSubFieldCapacity(final HNObject p0, final int p1);
        
        int ANFieldAddSubField(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int ANFieldInsertSubField(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int ANFieldRemoveSubField(final HNObject p0, final int p1);
        
        int ANFieldRemoveSubFieldRange(final HNObject p0, final int p1, final int p2);
        
        int ANFieldGetValue(final HNObject p0, final Pointer p1, final int p2);
        
        int ANFieldGetValueAsStringEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANFieldGetValueAsNumber(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANFieldSetValue(final HNObject p0, final WString p1, final int p2);
        
        int ANFieldSetValueAsString(final HNObject p0, final WString p1);
        
        int ANFieldSetValueAsNumber(final HNObject p0, final int p1, final int p2, final int p3);
        
        int ANFieldGetNumber(final HNObject p0, final IntByReference p1);
    }
}
