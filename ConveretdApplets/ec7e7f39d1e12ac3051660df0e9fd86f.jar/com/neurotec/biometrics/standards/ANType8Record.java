// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.lang.NObject;
import com.neurotec.util.NSimpleReadOnlyCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class ANType8Record extends ANFPImageAsciiBinaryRecord
{
    static final ANType8RecordLibrary LIBRARY;
    public static final int FIELD_SIG = 3;
    public static final int FIELD_SRT = 4;
    private PenVectorCollection penVectors;
    public static final NNativeType NATIVE_TYPE;
    
    ANType8Record(final HNObject handle) {
        super(handle, ANType8Record.NATIVE_TYPE);
        this.penVectors = new PenVectorCollection(this);
    }
    
    public ANSignatureType getSignatureType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType8Record.LIBRARY.ANType8RecordGetSignatureType(this.getHandle(), rValue));
        return ANSignatureType.get(rValue.getValue());
    }
    
    public void setSignatureType(final ANSignatureType value) {
        NResult.check(ANType8Record.LIBRARY.ANType8RecordSetSignatureType(this.getHandle(), value.getValue()));
    }
    
    public ANSignatureRepresentationType getSignatureRepresentationType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANType8Record.LIBRARY.ANType8RecordGetSignatureRepresentationType(this.getHandle(), rValue));
        return ANSignatureRepresentationType.get(rValue.getValue());
    }
    
    public PenVectorCollection getPenVectors() {
        this.check();
        return this.penVectors;
    }
    
    static {
        LIBRARY = (ANType8RecordLibrary)Native.loadLibrary("NBiometricStandards", ANType8RecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANType8Record.LIBRARY.ANType8RecordTypeOf());
    }
    
    public final class PenVectorCollection extends NSimpleReadOnlyCollection<ANPenVector>
    {
        protected PenVectorCollection(final ANType8Record owner) {
            super(owner);
        }
        
        protected ANPenVector[] getAllNative() {
            final int size = this.sizeNative();
            final ANPenVector.ANPenVectorData[] structures = (ANPenVector.ANPenVectorData[])new ANPenVector.ANPenVectorData().toArray(size);
            final ANPenVector[] values = new ANPenVector[size];
            ANType8Record.LIBRARY.ANType8RecordGetPenVectorsEx(this.getOwner().getHandle(), structures, size);
            for (int i = 0; i < values.length; ++i) {
                values[i] = new ANPenVector(structures[i]);
            }
            return values;
        }
        
        protected ANPenVector getNative(final int index) {
            final ANPenVector value = new ANPenVector();
            NResult.check(ANType8Record.LIBRARY.ANType8RecordGetPenVector(this.getOwner().getHandle(), index, value.getData()));
            return value;
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANType8Record.LIBRARY.ANType8RecordGetPenVectorCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
    }
    
    interface ANType8RecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANType8RecordTypeOf();
        
        int ANType8RecordGetPenVectorCount(final HNObject p0, final IntByReference p1);
        
        int ANType8RecordGetPenVector(final HNObject p0, final int p1, final ANPenVector.ANPenVectorData p2);
        
        int ANType8RecordGetPenVectorsEx(final HNObject p0, final ANPenVector.ANPenVectorData[] p1, final int p2);
        
        int ANType8RecordGetSignatureType(final HNObject p0, final IntByReference p1);
        
        int ANType8RecordSetSignatureType(final HNObject p0, final int p1);
        
        int ANType8RecordGetSignatureRepresentationType(final HNObject p0, final IntByReference p1);
    }
}
