// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.lang.NObject;
import com.neurotec.biometrics.standards.util.ANRecordSimpleCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NResult;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public abstract class ANFImageBinaryRecord extends ANImageBinaryRecord
{
    static final ANFImageBinaryRecordLibrary LIBRARY;
    public static final int FIELD_IMP = 3;
    public static final int FIELD_FGP = 4;
    public static final int FIELD_CA = 8;
    public static final int MAX_POSITION_COUNT = 6;
    public static final NNativeType NATIVE_TYPE;
    private PositionCollection positions;
    
    ANFImageBinaryRecord(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType);
        this.positions = new PositionCollection(this);
    }
    
    public final BdifFPImpressionType getImpressionType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(ANFImageBinaryRecord.LIBRARY.ANFImageBinaryRecordGetImpressionType(this.getHandle(), rValue));
        return BdifFPImpressionType.get(rValue.getValue());
    }
    
    public final void setImpressionType(final BdifFPImpressionType value) {
        NResult.check(ANFImageBinaryRecord.LIBRARY.ANFImageBinaryRecordSetImpressionType(this.getHandle(), value.getValue()));
    }
    
    public final PositionCollection getPositions() {
        this.check();
        return this.positions;
    }
    
    static {
        LIBRARY = (ANFImageBinaryRecordLibrary)Native.loadLibrary("NBiometricStandards", ANFImageBinaryRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(ANFImageBinaryRecord.LIBRARY.ANFImageBinaryRecordTypeOf());
    }
    
    public final class PositionCollection extends ANRecordSimpleCollection<BdifFPPosition>
    {
        protected PositionCollection(final ANFImageBinaryRecord owner) {
            super(owner, 4, false, false, false);
        }
        
        protected void addNative(final BdifFPPosition value) {
            NResult.check(ANFImageBinaryRecord.LIBRARY.ANFImageBinaryRecordAddPosition(this.getOwner().getHandle(), value.getValue()));
        }
        
        protected int addWithIndexNative(final BdifFPPosition value) {
            throw new UnsupportedOperationException();
        }
        
        protected BdifFPPosition[] getAllNative() {
            final int size = this.sizeNative();
            final BdifFPPosition[] values = new BdifFPPosition[size];
            final int[] data = new int[size];
            ANFImageBinaryRecord.LIBRARY.ANFImageBinaryRecordGetPositionsEx(this.getOwner().getHandle(), data, size);
            for (int i = 0; i < data.length; ++i) {
                values[i] = BdifFPPosition.get(i);
            }
            return values;
        }
        
        protected BdifFPPosition getNative(final int index) {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANFImageBinaryRecord.LIBRARY.ANFImageBinaryRecordGetPosition(this.getOwner().getHandle(), index, rValue));
            return BdifFPPosition.get(rValue.getValue());
        }
        
        protected void addNative(final int index, final BdifFPPosition value) {
            NResult.check(ANFImageBinaryRecord.LIBRARY.ANFImageBinaryRecordInsertPosition(this.getOwner().getHandle(), index, value.getValue()));
        }
        
        protected void setNative(final int index, final BdifFPPosition value) {
            NResult.check(ANFImageBinaryRecord.LIBRARY.ANFImageBinaryRecordSetPosition(this.getOwner().getHandle(), index, value.getValue()));
        }
        
        protected void clearNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(ANFImageBinaryRecord.LIBRARY.ANFImageBinaryRecordGetPositionCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(ANFImageBinaryRecord.LIBRARY.ANFImageBinaryRecordRemovePosition(this.getOwner().getHandle(), index));
        }
        
        protected int removeNative(final BdifFPPosition value) {
            throw new UnsupportedOperationException();
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
    }
    
    interface ANFImageBinaryRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType ANFImageBinaryRecordTypeOf();
        
        int ANFImageBinaryRecordGetPositionCount(final HNObject p0, final IntByReference p1);
        
        int ANFImageBinaryRecordGetPosition(final HNObject p0, final int p1, final IntByReference p2);
        
        int ANFImageBinaryRecordGetPositionsEx(final HNObject p0, final int[] p1, final int p2);
        
        int ANFImageBinaryRecordSetPosition(final HNObject p0, final int p1, final int p2);
        
        int ANFImageBinaryRecordAddPosition(final HNObject p0, final int p1);
        
        int ANFImageBinaryRecordInsertPosition(final HNObject p0, final int p1, final int p2);
        
        int ANFImageBinaryRecordRemovePosition(final HNObject p0, final int p1);
        
        int ANFImageBinaryRecordGetImpressionType(final HNObject p0, final IntByReference p1);
        
        int ANFImageBinaryRecordSetImpressionType(final HNObject p0, final int p1);
    }
}
