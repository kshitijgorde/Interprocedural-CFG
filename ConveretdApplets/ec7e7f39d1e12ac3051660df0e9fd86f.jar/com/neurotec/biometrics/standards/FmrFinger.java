// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.util.NObjectCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class FmrFinger extends NObject
{
    private FingerViewCollection fingerViews;
    static final FmrFingerLibrary LIBRARY;
    public static final int MAX_FINGER_VIEW_COUNT = 16;
    public static final int FLAG_PROCESS_FIRST_FINGER_VIEW_ONLY = 4096;
    public static final NNativeType NATIVE_TYPE;
    
    FmrFinger(final HNObject handle) {
        super(handle, FmrFinger.NATIVE_TYPE, false);
        this.fingerViews = new FingerViewCollection(this);
    }
    
    public static int calculateSize(final BdifStandard standard, int[] fingerViewSizes) {
        if (fingerViewSizes == null) {
            fingerViewSizes = new int[0];
        }
        final int fingerViewCount = fingerViewSizes.length;
        final NativeSize[] finViewSizes = new NativeSize[fingerViewCount];
        for (int i = 0; i != fingerViewCount; ++i) {
            if (fingerViewSizes[i] < 0) {
                throw new IllegalArgumentException("One of fingerViewSizes is less than zero");
            }
            finViewSizes[i] = new NativeSize((long)i);
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(FmrFinger.LIBRARY.FmrFingerCalculateSize(standard.getValue(), fingerViewCount, finViewSizes, rValue));
        return rValue.getValue().intValue();
    }
    
    public FMRecord getOwner() {
        return (FMRecord)super.getOwner();
    }
    
    public BdifFPPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FmrFinger.LIBRARY.FmrFingerGetFingerPosition(this.getHandle(), rValue));
        return BdifFPPosition.get(rValue.getValue());
    }
    
    public void setPosition(final BdifFPPosition value) {
        NResult.check(FmrFinger.LIBRARY.FmrFingerSetFingerPosition(this.getHandle(), value.getValue()));
    }
    
    public FingerViewCollection getFingerViews() {
        this.check();
        return this.fingerViews;
    }
    
    protected void dispose(final boolean disposing) {
        if (disposing) {
            this.fingerViews.disposeItems();
        }
        this.fingerViews = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (FmrFingerLibrary)Native.loadLibrary("NBiometricStandards", FmrFingerLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(FmrFinger.LIBRARY.FmrFingerTypeOf());
    }
    
    public final class FingerViewCollection extends NObjectCollection<FmrFingerView>
    {
        FingerViewCollection(final FmrFinger owner) {
            super(owner, false);
        }
        
        void onRemoveRange(final int index, final int count) {
            this.removeItemRange(index, count);
        }
        
        public FmrFingerView add(final int flags) {
            FmrFingerView value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FmrFinger.LIBRARY.FmrFingerAddFingerView(this.getOwner().getHandle(), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    FmrFinger.LIBRARY.FmrFingerRemoveFingerView(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public FmrFingerView add() {
            return this.add(0);
        }
        
        protected void clearNative() {
            NResult.check(FmrFinger.LIBRARY.FmrFingerClearFingerViews(this.getOwner().getHandle()));
        }
        
        protected FmrFingerView fromHandleNative(final HNObject handle) {
            return new FmrFingerView(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FmrFinger.LIBRARY.FmrFingerGetFingerView(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FmrFinger.LIBRARY.FmrFingerGetFingerViewCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(FmrFinger.LIBRARY.FmrFingerSetFingerViewCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FmrFinger.LIBRARY.FmrFingerGetFingerViewCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FmrFinger.LIBRARY.FmrFingerRemoveFingerView(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    interface FmrFingerLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType FmrFingerTypeOf();
        
        int FmrFingerCalculateSize(final int p0, final int p1, final NativeSize[] p2, final NativeSizeByReference p3);
        
        int FmrFingerGetFingerViewCount(final HNObject p0, final IntByReference p1);
        
        int FmrFingerGetFingerView(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FmrFingerGetFingerViewCapacity(final HNObject p0, final IntByReference p1);
        
        int FmrFingerSetFingerViewCapacity(final HNObject p0, final int p1);
        
        int FmrFingerAddFingerView(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FmrFingerRemoveFingerView(final HNObject p0, final int p1);
        
        int FmrFingerClearFingerViews(final HNObject p0);
        
        int FmrFingerGetFingerPosition(final HNObject p0, final IntByReference p1);
        
        int FmrFingerSetFingerPosition(final HNObject p0, final int p1);
    }
}
