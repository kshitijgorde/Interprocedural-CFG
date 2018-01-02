// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.neurotec.jna.NLibrary;
import com.neurotec.images.NImage;
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

public final class IirIris extends NObject
{
    private IrisImageCollection irisImages;
    static final IirIrisLibrary LIBRARY;
    public static final int MAX_IRIS_IMAGE_COUNT = 32767;
    public static final int FLAG_PROCESS_FIRST_IRIS_IMAGE_ONLY = 4096;
    public static final NNativeType NATIVE_TYPE;
    
    IirIris(final HNObject handle) {
        super(handle, IirIris.NATIVE_TYPE, false);
        this.irisImages = new IrisImageCollection(this);
    }
    
    public static int calculateSize(final BdifStandard standard, int[] irisImageSizes) {
        if (irisImageSizes == null) {
            irisImageSizes = new int[0];
        }
        final int irisImageCount = irisImageSizes.length;
        final NativeSize[] irImageSizes = new NativeSize[irisImageCount];
        for (int i = 0; i != irisImageCount; ++i) {
            if (irisImageSizes[i] < 0) {
                throw new IllegalArgumentException("One of irisImageSizes is less than zero");
            }
            irImageSizes[i] = new NativeSize((long)i);
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(IirIris.LIBRARY.IirIrisCalculateSize(standard.getValue(), irisImageCount, irImageSizes, rValue));
        return rValue.getValue().intValue();
    }
    
    public IIRecord getOwner() {
        return (IIRecord)super.getOwner();
    }
    
    public BdifFPPosition getPosition() {
        final IntByReference rValue = new IntByReference();
        NResult.check(IirIris.LIBRARY.IirIrisGetPosition(this.getHandle(), rValue));
        return BdifFPPosition.get(rValue.getValue());
    }
    
    public void setPosition(final BdifFPPosition value) {
        NResult.check(IirIris.LIBRARY.IirIrisSetPosition(this.getHandle(), value.getValue()));
    }
    
    public IrisImageCollection getIrisImages() {
        this.check();
        return this.irisImages;
    }
    
    protected void dispose(final boolean disposing) {
        if (disposing) {
            this.irisImages.disposeItems();
        }
        this.irisImages = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (IirIrisLibrary)Native.loadLibrary("NBiometricStandards", IirIrisLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(IirIris.LIBRARY.IirIrisTypeOf());
    }
    
    public final class IrisImageCollection extends NObjectCollection<IirIrisImage>
    {
        IrisImageCollection(final IirIris owner) {
            super(owner, false);
        }
        
        void onRemoveRange(final int index, final int count) {
            this.removeItemRange(index, count);
        }
        
        public IirIrisImage add(final HNObject rhImageData, final int imageDataLength, final int flags) {
            if (imageDataLength < 0) {
                throw new IllegalArgumentException("imageDataLength is less than zero");
            }
            IirIrisImage value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(IirIris.LIBRARY.IirIrisAddIrisImage(this.getOwner().getHandle(), rhImageData, new NativeSize((long)imageDataLength), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    IirIris.LIBRARY.IirIrisRemoveIrisImage(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public IirIrisImage add(final NImage nImage, final int flags) {
            if (nImage == null) {
                throw new NullPointerException("nImage");
            }
            IirIrisImage value = null;
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(IirIris.LIBRARY.IirIrisAddIrisImageFromNImage(this.getOwner().getHandle(), nImage.getHandle(), flags, rhValue));
            try {
                value = this.addItem(rhValue.getValue());
            }
            finally {
                if (value == null) {
                    IirIris.LIBRARY.IirIrisRemoveIrisImage(this.getOwner().getHandle(), this.size() - 1);
                }
            }
            return value;
        }
        
        public IirIrisImage add(final NImage nImage) {
            return this.add(nImage, 0);
        }
        
        protected void onInsertItem(final int index, final IirIrisImage item) {
            super.onInsertItem(index, item);
            ((IirIris)this.getOwner()).getOwner().onInsertIrisImage((IirIris)this.getOwner(), index, item);
        }
        
        protected void onRemoveItemRange(final int index, final int count) {
            super.onRemoveItemRange(index, count);
        }
        
        protected void clearNative() {
            NResult.check(IirIris.LIBRARY.IirIrisClearIrisImages(this.getOwner().getHandle()));
        }
        
        protected IirIrisImage fromHandleNative(final HNObject handle) {
            return new IirIrisImage(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(IirIris.LIBRARY.IirIrisGetIrisImage(this.getOwner().getHandle(), index, rhValue));
            return rhValue.getValue();
        }
        
        protected int getCapacityNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(IirIris.LIBRARY.IirIrisGetIrisImageCapacity(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void setCapacityNative(final int value) {
            NResult.check(IirIris.LIBRARY.IirIrisSetIrisImageCapacity(this.getOwner().getHandle(), value));
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(IirIris.LIBRARY.IirIrisGetIrisImageCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(IirIris.LIBRARY.IirIrisRemoveIrisImage(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    interface IirIrisLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType IirIrisTypeOf();
        
        int IirIrisCalculateSize(final int p0, final int p1, final NativeSize[] p2, final NativeSizeByReference p3);
        
        int IirIrisGetIrisImageCount(final HNObject p0, final IntByReference p1);
        
        int IirIrisGetIrisImage(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int IirIrisGetIrisImageCapacity(final HNObject p0, final IntByReference p1);
        
        int IirIrisSetIrisImageCapacity(final HNObject p0, final int p1);
        
        int IirIrisAddIrisImage(final HNObject p0, final HNObject p1, final NativeSize p2, final int p3, final HNObjectByReference p4);
        
        int IirIrisAddIrisImageFromNImage(final HNObject p0, final HNObject p1, final int p2, final HNObjectByReference p3);
        
        int IirIrisRemoveIrisImage(final HNObject p0, final int p1);
        
        int IirIrisClearIrisImages(final HNObject p0);
        
        int IirIrisGetPosition(final HNObject p0, final IntByReference p1);
        
        int IirIrisSetPosition(final HNObject p0, final int p1);
    }
}
