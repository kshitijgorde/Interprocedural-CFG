// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.images.NImage;
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

public final class FirFinger extends NObject
{
    private FingerViewCollection fingerViews;
    static final FirFingerLibrary LIBRARY;
    public static final int MAX_FINGER_VIEW_COUNT = 255;
    public static final int FLAG_PROCESS_FIRST_FINGER_VIEW_ONLY = 4096;
    public static final NNativeType NATIVE_TYPE;
    
    FirFinger(final HNObject handle) {
        super(handle, FirFinger.NATIVE_TYPE, false);
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
        NResult.check(FirFinger.LIBRARY.FirFingerCalculateSize(standard.getValue(), fingerViewCount, finViewSizes, rValue));
        return rValue.getValue().intValue();
    }
    
    public FIRecord getOwner() {
        return (FIRecord)super.getOwner();
    }
    
    public BdifFPPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(FirFinger.LIBRARY.FirFingerGetPosition(this.getHandle(), rValue));
        return BdifFPPosition.get(rValue.getValue());
    }
    
    public void setPosition(final BdifFPPosition value) {
        NResult.check(FirFinger.LIBRARY.FirFingerSetPosition(this.getHandle(), value.getValue()));
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
        LIBRARY = (FirFingerLibrary)Native.loadLibrary("NBiometricStandards", FirFingerLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(FirFinger.LIBRARY.FirFingerTypeOf());
    }
    
    public final class FingerViewCollection extends NObjectCollection<FirFingerView>
    {
        FingerViewCollection(final NObject owner) {
            super(owner, false);
        }
        
        void onRemoveRange(final int index, final int count) {
            this.removeItemRange(index, count);
        }
        
        public FirFingerView add(final NImage nImage, final int flags) {
            if (nImage == null) {
                throw new NullPointerException("nImage");
            }
            FirFingerView value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FirFinger.LIBRARY.FirFingerAddFingerViewFromNImage(this.getOwner().getHandle(), nImage.getHandle(), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    FirFinger.LIBRARY.FirFingerRemoveFingerView(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public FirFingerView add(final NImage nImage) {
            return this.add(nImage, 0);
        }
        
        protected void clearNative() {
            NResult.check(FirFinger.LIBRARY.FirFingerClearFingerViews(this.getOwner().getHandle()));
        }
        
        protected FirFingerView fromHandleNative(final HNObject handle) {
            return new FirFingerView(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(FirFinger.LIBRARY.FirFingerGetFingerView(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FirFinger.LIBRARY.FirFingerGetFingerViewCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(FirFinger.LIBRARY.FirFingerSetFingerViewCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(FirFinger.LIBRARY.FirFingerGetFingerViewCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(FirFinger.LIBRARY.FirFingerRemoveFingerView(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    interface FirFingerLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType FirFingerTypeOf();
        
        int FirFingerCalculateSize(final int p0, final int p1, final NativeSize[] p2, final NativeSizeByReference p3);
        
        int FirFingerGetFingerViewCount(final HNObject p0, final IntByReference p1);
        
        int FirFingerGetFingerView(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int FirFingerGetFingerViewCapacity(final HNObject p0, final IntByReference p1);
        
        int FirFingerSetFingerViewCapacity(final HNObject p0, final int p1);
        
        int FirFingerAddFingerView(final HNObject p0, final short p1, final short p2, final HNObject p3, final NativeSize p4, final int p5, final HNObjectByReference p6);
        
        int FirFingerAddFingerViewFromNImage(final HNObject p0, final HNObject p1, final int p2, final HNObjectByReference p3);
        
        int FirFingerRemoveFingerView(final HNObject p0, final int p1);
        
        int FirFingerClearFingerViews(final HNObject p0);
        
        int FirFingerGetPosition(final HNObject p0, final IntByReference p1);
        
        int FirFingerSetPosition(final HNObject p0, final int p1);
    }
}
