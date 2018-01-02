// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class ANSubField extends NObject
{
    private ItemCollection items;
    static final ANSubFieldLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    ANSubField(final HNObject handle) {
        super(handle, ANSubField.NATIVE_TYPE, false);
        this.items = new ItemCollection(this);
    }
    
    public char[] getValue(final int length) {
        if (length < 1) {
            return null;
        }
        final char[] buf = new char[length + 1];
        if (ANSubField.LIBRARY.ANSubFieldGetValue(this.getHandle(), buf, length) == -1) {
            return null;
        }
        return buf;
    }
    
    public int getValueAsNumber() {
        return this.getValueAsNumber(10);
    }
    
    public int getValueAsNumber(final int base) {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANSubField.LIBRARY.ANSubFieldGetValueAsNumber(this.getHandle(), base, rValue));
        return rValue.getValue();
    }
    
    public void set(final String value) {
        NResult.check(ANSubField.LIBRARY.ANSubFieldSetValueAsString(this.getHandle(), new WString(value)));
    }
    
    public void set(final int value) {
        this.set(value, 1, 10);
    }
    
    public void set(final int value, final int minDigits) {
        this.set(value, minDigits, 10);
    }
    
    public void set(final int value, final int minDigits, final int base) {
        NResult.check(ANSubField.LIBRARY.ANSubFieldSetValueAsNumber(this.getHandle(), value, minDigits, base));
    }
    
    public ANField getOwner() {
        return (ANField)super.getOwner();
    }
    
    public ItemCollection getItems() {
        this.check();
        return this.items;
    }
    
    public char[] getValue() {
        final int pLen = ANSubField.LIBRARY.ANSubFieldGetValue(this.getHandle(), null, 0);
        if (pLen == -1) {
            return null;
        }
        final char[] buf = new char[pLen + 1];
        if (ANSubField.LIBRARY.ANSubFieldGetValue(this.getHandle(), buf, pLen) == -1) {
            return null;
        }
        return buf;
    }
    
    public void setValue(final char[] value) {
        NResult.check(ANSubField.LIBRARY.ANSubFieldSetValue(this.getHandle(), value, value.length));
    }
    
    public String getValueAsString() {
        final int pLen = ANSubField.LIBRARY.ANSubFieldGetValueAsStringEx(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(ANSubField.LIBRARY.ANSubFieldGetValueAsStringEx(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public void setValueAsString(final String value) {
        NResult.check(ANSubField.LIBRARY.ANSubFieldSetValueAsString(this.getHandle(), new WString(value)));
    }
    
    protected void dispose(final boolean disposing) {
        this.items = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (ANSubFieldLibrary)Native.loadLibrary("NBiometricStandards", ANSubFieldLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANSubField.LIBRARY.ANSubFieldTypeOf());
    }
    
    public final class ItemCollection extends NSimpleCollection<char[]>
    {
        ItemCollection(final ANSubField owner) {
            super(owner, true, false, false);
        }
        
        public int add() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANSubField.LIBRARY.ANSubFieldAddItem(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        public int addRange(final int count) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANSubField.LIBRARY.ANSubFieldAddItemRange(this.getOwner().getHandle(), count, rValue));
            return rValue.getValue();
        }
        
        public void insert(final int index) {
            NResult.check(ANSubField.LIBRARY.ANSubFieldInsertItem(this.getOwner().getHandle(), index));
        }
        
        public void insertRange(final int index, final int count) {
            NResult.check(ANSubField.LIBRARY.ANSubFieldInsertItemRange(this.getOwner().getHandle(), index, count));
        }
        
        public char[] get(final int index, final int length) {
            final char[] value = new char[length + 1];
            ANSubField.LIBRARY.ANSubFieldGetItem(this.getOwner().getHandle(), index, value, length);
            return value;
        }
        
        public String getAsString(final int index) {
            final int pLen = ANSubField.LIBRARY.ANSubFieldGetItemAsStringEx(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(ANSubField.LIBRARY.ANSubFieldGetItemAsStringEx(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
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
            NResult.check(ANSubField.LIBRARY.ANSubFieldGetItemAsNumber(this.getOwner().getHandle(), index, base, rValue));
            return rValue.getValue();
        }
        
        public void set(final int index, final String value) {
            NResult.check(ANSubField.LIBRARY.ANSubFieldSetItemAsString(this.getOwner().getHandle(), index, new WString(value)));
        }
        
        public void set(final int index, final int value) {
            this.set(index, value, 1, 10);
        }
        
        public void set(final int index, final int value, final int minDigits) {
            this.set(index, value, minDigits, 10);
        }
        
        public void set(final int index, final int value, final int minDigits, final int base) {
            NResult.check(ANSubField.LIBRARY.ANSubFieldSetItemAsNumber(this.getOwner().getHandle(), index, value, minDigits, base));
        }
        
        protected void clearNative() {
            throw new UnsupportedOperationException();
        }
        
        protected char[] getNative(final int index) {
            final int pLen = ANSubField.LIBRARY.ANSubFieldGetItem(this.getOwner().getHandle(), index, null, 0);
            if (pLen == -1) {
                return null;
            }
            final char[] buf = new char[pLen + 1];
            if (ANSubField.LIBRARY.ANSubFieldGetItem(this.getOwner().getHandle(), index, buf, buf.length) == -1) {
                return null;
            }
            return buf;
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANSubField.LIBRARY.ANSubFieldGetItemCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(ANSubField.LIBRARY.ANSubFieldSetItemCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANSubField.LIBRARY.ANSubFieldGetItemCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANSubField.LIBRARY.ANSubFieldRemoveItem(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final char[] value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            NResult.check(ANSubField.LIBRARY.ANSubFieldRemoveItemRange(this.getOwner().getHandle(), index, count));
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
            NResult.check(ANSubField.LIBRARY.ANSubFieldSetItem(this.getOwner().getHandle(), index, value, value.length));
        }
    }
    
    interface ANSubFieldLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANSubFieldTypeOf();
        
        int ANSubFieldGetValue(final HNObject p0, final char[] p1, final int p2);
        
        int ANSubFieldGetValueAsStringEx(final HNObject p0, final Pointer p1, final int p2);
        
        int ANSubFieldGetValueAsNumber(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANSubFieldSetValue(final HNObject p0, final char[] p1, final int p2);
        
        int ANSubFieldSetValueAsString(final HNObject p0, final WString p1);
        
        int ANSubFieldSetValueAsNumber(final HNObject p0, final int p1, final int p2, final int p3);
        
        int ANSubFieldGetItemCount(final HNObject p0, final IntByReference p1);
        
        int ANSubFieldGetItemCapacity(final HNObject p0, final IntByReference p1);
        
        int ANSubFieldSetItemCapacity(final HNObject p0, final int p1);
        
        int ANSubFieldGetItem(final HNObject p0, final int p1, final char[] p2, final int p3);
        
        int ANSubFieldGetItemAsStringEx(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int ANSubFieldGetItemAsNumber(final HNObject p0, final int p1, final int p2, final IntByReference p3);
        
        int ANSubFieldSetItem(final HNObject p0, final int p1, final char[] p2, final int p3);
        
        int ANSubFieldSetItemAsString(final HNObject p0, final int p1, final WString p2);
        
        int ANSubFieldSetItemAsNumber(final HNObject p0, final int p1, final int p2, final int p3, final int p4);
        
        int ANSubFieldAddItem(final HNObject p0, final IntByReference p1);
        
        int ANSubFieldAddItemRange(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANSubFieldInsertItem(final HNObject p0, final int p1);
        
        int ANSubFieldInsertItemRange(final HNObject p0, final int p1, final int p2);
        
        int ANSubFieldRemoveItem(final HNObject p0, final int p1);
        
        int ANSubFieldRemoveItemRange(final HNObject p0, final int p1, final int p2);
    }
}
