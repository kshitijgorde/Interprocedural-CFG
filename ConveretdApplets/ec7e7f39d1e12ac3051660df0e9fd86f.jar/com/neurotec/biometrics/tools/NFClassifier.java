// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.tools;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.biometrics.NFPatternClass;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.images.NGrayscaleImage;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NFClassifier extends NObject
{
    static final NFClassifierLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NFClassifier(final HNObject handle, final boolean claimHandle) {
        super(handle, NFClassifier.NATIVE_TYPE, claimHandle);
    }
    
    public NFClassifier() {
        this(create(), true);
    }
    
    public static NFClassifier fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NFClassifier fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NFClassifier value = new NFClassifier(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private static HNObject create() {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NFClassifier.LIBRARY.NfcCreate(rhValue));
        return rhValue.getValue();
    }
    
    public NFClassifierResult classify(final NGrayscaleImage image) {
        if (image == null) {
            throw new NullPointerException("image");
        }
        final IntByReference rPatternClass = new IntByReference();
        final DoubleByReference rConfidence = new DoubleByReference();
        NResult.check(NFClassifier.LIBRARY.NfcClassify(this.getHandle(), image.getHandle(), rPatternClass, rConfidence));
        return new NFClassifierResult(NFPatternClass.get(rPatternClass.getValue()), rConfidence.getValue());
    }
    
    static {
        LIBRARY = (NFClassifierLibrary)Native.loadLibrary("NBiometricTools", NFClassifierLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NFClassifier.LIBRARY.NFClassifierTypeOf());
    }
    
    public final class NFClassifierResult
    {
        private NFPatternClass patternClass;
        private double confidence;
        
        public NFClassifierResult(final NFPatternClass patternClass, final double confidence) {
            this.patternClass = patternClass;
            this.confidence = confidence;
        }
        
        public NFPatternClass getPatternClass() {
            return this.patternClass;
        }
        
        public double getConfidence() {
            return this.confidence;
        }
    }
    
    interface NFClassifierLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NFClassifierTypeOf();
        
        int NfcCreate(final HNObjectByReference p0);
        
        int NfcClassify(final HNObject p0, final HNObject p1, final IntByReference p2, final DoubleByReference p3);
    }
}
