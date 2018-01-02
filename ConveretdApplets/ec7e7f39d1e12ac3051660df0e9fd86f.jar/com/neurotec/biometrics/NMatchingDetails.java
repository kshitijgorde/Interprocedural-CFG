// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.neurotec.lang.NObject;
import com.neurotec.util.NObjectReadOnlyCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.Pointer;
import com.neurotec.lang.NCore;
import com.sun.jna.ptr.PointerByReference;
import com.neurotec.lang.NResult;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.NativeSizeByReference;
import com.neurotec.jna.ptr.HNObjectByReference;
import java.nio.ByteBuffer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;

public final class NMatchingDetails extends NMatchingDetailsBase
{
    static final NFRecordLibrary LIBRARY;
    private FingerCollection fingers;
    private FaceCollection faces;
    private IrisCollection irises;
    private PalmCollection palms;
    public static final NNativeType NATIVE_TYPE;
    
    private NMatchingDetails(final HNObject handle, final boolean claimHandle) {
        super(handle, NMatchingDetails.NATIVE_TYPE, claimHandle);
    }
    
    public NMatchingDetails(final ByteBuffer buffer) {
        this(create(buffer, 0), true);
    }
    
    public NMatchingDetails(final ByteBuffer buffer, final int flags) {
        this(create(buffer, flags), true);
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsCreateFromMemory(buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    public static NMatchingDetails fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NMatchingDetails fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NMatchingDetails value = new NMatchingDetails(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    public ByteBuffer save() {
        return this.save(0);
    }
    
    public ByteBuffer save(final int flags) {
        final PointerByReference rValue = new PointerByReference();
        Pointer pValue = null;
        ByteBuffer value = null;
        final NativeSizeByReference rBufferLength = new NativeSizeByReference();
        NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsSaveToMemory(this.getHandle(), flags, rValue, rBufferLength));
        try {
            pValue = rValue.getValue();
            value = NCore.getByteBuffer(pValue, rBufferLength.getValue().longValue());
        }
        finally {
            if (value == null) {
                NCore.free(pValue);
            }
        }
        return value;
    }
    
    public int getFingersScore() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetFingersScore(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getFacesScore() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetFacesScore(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getFacesMatchedIndex() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetFacesMatchedIndex(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getIrisesScore() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetIrisesScore(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public int getPalmsScore() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetPalmsScore(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public FingerCollection getFingers() {
        this.check();
        if (this.fingers == null) {
            this.fingers = new FingerCollection(this);
        }
        return this.fingers;
    }
    
    public FaceCollection getFaces() {
        this.check();
        if (this.faces == null) {
            this.faces = new FaceCollection(this);
        }
        return this.faces;
    }
    
    public IrisCollection getIrises() {
        this.check();
        if (this.irises == null) {
            this.irises = new IrisCollection(this);
        }
        return this.irises;
    }
    
    public PalmCollection getPalms() {
        this.check();
        if (this.palms == null) {
            this.palms = new PalmCollection(this);
        }
        return this.palms;
    }
    
    protected void dispose(final boolean disposing) {
        if (disposing) {
            this.fingers.disposeItems();
            this.faces.disposeItems();
            this.irises.disposeItems();
            this.palms.disposeItems();
        }
        this.fingers = null;
        this.faces = null;
        this.irises = null;
        this.palms = null;
        super.dispose(disposing);
    }
    
    static {
        LIBRARY = (NFRecordLibrary)Native.loadLibrary("NBiometrics", NFRecordLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NMatchingDetails.LIBRARY.NMatchingDetailsTypeOf());
    }
    
    public static final class FingerCollection extends NObjectReadOnlyCollection<NFMatchingDetails>
    {
        protected FingerCollection(final NMatchingDetails owner) {
            super(owner);
        }
        
        public NFMatchingDetails fromHandleNative(final HNObject handle) {
            return NFMatchingDetails.fromHandle(handle);
        }
        
        public HNObject getNative(final int index) {
            final HNObjectByReference rValue = new HNObjectByReference();
            NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetFinger(this.getOwner().getHandle(), index, rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetFingerCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    public static final class FaceCollection extends NObjectReadOnlyCollection<NLMatchingDetails>
    {
        protected FaceCollection(final NMatchingDetails owner) {
            super(owner);
        }
        
        public NLMatchingDetails fromHandleNative(final HNObject handle) {
            return NLMatchingDetails.fromHandle(handle);
        }
        
        public HNObject getNative(final int index) {
            final HNObjectByReference rValue = new HNObjectByReference();
            NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetFace(this.getOwner().getHandle(), index, rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetFaceCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    public static final class IrisCollection extends NObjectReadOnlyCollection<NEMatchingDetails>
    {
        protected IrisCollection(final NMatchingDetails owner) {
            super(owner);
        }
        
        public NEMatchingDetails fromHandleNative(final HNObject handle) {
            return NEMatchingDetails.fromHandle(handle);
        }
        
        public HNObject getNative(final int index) {
            final HNObjectByReference rValue = new HNObjectByReference();
            NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetIris(this.getOwner().getHandle(), index, rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetIrisCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    public static final class PalmCollection extends NObjectReadOnlyCollection<NFMatchingDetails>
    {
        protected PalmCollection(final NMatchingDetails owner) {
            super(owner);
        }
        
        public NFMatchingDetails fromHandleNative(final HNObject handle) {
            return NFMatchingDetails.fromHandle(handle);
        }
        
        public HNObject getNative(final int index) {
            final HNObjectByReference rValue = new HNObjectByReference();
            NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetPalm(this.getOwner().getHandle(), index, rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NMatchingDetails.LIBRARY.NMatchingDetailsGetPalmCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
    }
    
    interface NFRecordLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NMatchingDetailsTypeOf();
        
        int NMatchingDetailsCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final NativeSizeByReference p3, final HNObjectByReference p4);
        
        int NMatchingDetailsCreateFromStream(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NMatchingDetailsSaveToMemory(final HNObject p0, final int p1, final PointerByReference p2, final NativeSizeByReference p3);
        
        int NMatchingDetailsSaveToStream(final HNObject p0, final HNObject p1, final int p2);
        
        int NMatchingDetailsGetFingersScore(final HNObject p0, final IntByReference p1);
        
        int NMatchingDetailsGetFacesScore(final HNObject p0, final IntByReference p1);
        
        int NMatchingDetailsGetFacesMatchedIndex(final HNObject p0, final IntByReference p1);
        
        int NMatchingDetailsGetIrisesScore(final HNObject p0, final IntByReference p1);
        
        int NMatchingDetailsGetPalmsScore(final HNObject p0, final IntByReference p1);
        
        int NMatchingDetailsGetFingerCount(final HNObject p0, final IntByReference p1);
        
        int NMatchingDetailsGetFinger(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NMatchingDetailsGetFaceCount(final HNObject p0, final IntByReference p1);
        
        int NMatchingDetailsGetFace(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NMatchingDetailsGetIrisCount(final HNObject p0, final IntByReference p1);
        
        int NMatchingDetailsGetIris(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NMatchingDetailsGetPalmCount(final HNObject p0, final IntByReference p1);
        
        int NMatchingDetailsGetPalm(final HNObject p0, final int p1, final HNObjectByReference p2);
    }
}
