// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.sun.jna.PointerType;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.jna.ptr.BooleanByReference;

public final class NNativeType
{
    private NNativeTypeLibrary.HNType handle;
    public static final NNativeType EMPTY;
    static final NNativeTypeLibrary LIBRARY;
    
    public NNativeType(final NNativeTypeLibrary.HNType handle) {
        this.handle = handle;
    }
    
    public String toString() {
        return this.getName();
    }
    
    public boolean equals(final Object obj) {
        return obj != null && obj instanceof NNativeType && this.handle == ((NNativeType)obj).handle;
    }
    
    public int hashCode() {
        return this.handle.hashCode();
    }
    
    public boolean isSubclassOf(final NNativeType type) {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NNativeType.LIBRARY.NTypeIsSubclassOf(this.handle, type.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public boolean isAssignableFrom(final NNativeType type) {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NNativeType.LIBRARY.NTypeIsAssignableFrom(this.handle, type.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public boolean isInstanceOfType(final HNObject hObject) {
        if (hObject == null || hObject.getPointer() == null) {
            throw new NullPointerException("hObject");
        }
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NNativeType.LIBRARY.NTypeIsInstanceOfType(this.handle, hObject, rValue));
        return rValue.getValue();
    }
    
    public boolean isInstanceOfType(final NObject obj) {
        if (obj == null) {
            throw new IllegalArgumentException("object is null");
        }
        return this.isInstanceOfType(obj.getHandle());
    }
    
    public NNativeTypeLibrary.HNType getHandle() {
        return this.handle;
    }
    
    public String getName() {
        final int bLen = NNativeType.LIBRARY.NTypeGetNameEx(this.handle, null, 0);
        if (NResult.isFailed(bLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(bLen);
        try {
            if (NResult.isFailed(NNativeType.LIBRARY.NTypeGetNameEx(this.handle, pValue, bLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public NNativeType getBaseType() {
        final PointerByReference rValue = new PointerByReference();
        NResult.check(NNativeType.LIBRARY.NTypeGetBaseType(this.handle, rValue));
        return new NNativeType(new NNativeTypeLibrary.HNType(rValue.getValue()));
    }
    
    public boolean isAbstract() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NNativeType.LIBRARY.NTypeIsAbstract(this.handle, rValue));
        return rValue.getValue();
    }
    
    public boolean isSupportsParameters() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NNativeType.LIBRARY.NTypeSupportsParameters(this.handle, rValue));
        return rValue.getValue();
    }
    
    static {
        EMPTY = new NNativeType(null);
        LIBRARY = (NNativeTypeLibrary)Native.loadLibrary("NCore", NNativeTypeLibrary.class, W32APIOptions.UNICODE_OPTIONS);
    }
    
    public interface NNativeTypeLibrary extends NLibrary
    {
        int NTypeIsSubclassOf(final HNType p0, final HNType p1, final BooleanByReference p2);
        
        int NTypeIsAssignableFrom(final HNType p0, final HNType p1, final BooleanByReference p2);
        
        int NTypeIsInstanceOfType(final HNType p0, final HNObject p1, final BooleanByReference p2);
        
        int NTypeReset(final HNType p0, final HNObject p1);
        
        int NTypeGetNameEx(final HNType p0, final Pointer p1, final int p2);
        
        int NTypeGetBaseType(final HNType p0, final PointerByReference p1);
        
        int NTypeIsAbstract(final HNType p0, final BooleanByReference p1);
        
        int NTypeSupportsParameters(final HNType p0, final BooleanByReference p1);
        
        public static class HNType extends PointerType
        {
            public HNType(final Pointer pointer) {
                super(pointer);
            }
            
            public HNType() {
            }
        }
    }
}
