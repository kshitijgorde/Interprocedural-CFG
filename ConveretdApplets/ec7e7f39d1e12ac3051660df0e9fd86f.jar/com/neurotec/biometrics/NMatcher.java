// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import com.sun.jna.ptr.IntByReference;
import java.nio.ByteBuffer;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NMatcher extends NObject
{
    static final NMatcherLibrary LIBRARY;
    private static final int PARAMETER_PARTS_BASE = 3536;
    private static final int PARAMETER_PARTS_COUNT = 4000;
    private static final int PARAMETER_FINGERS_MATCHER_BASE = 3536;
    private static final int PARAMETER_FACES_MATCHER_BASE = 11536;
    private static final int PARAMETER_IRISES_MATCHER_BASE = 19536;
    private static final int PARAMETER_PALMS_MATCHER_BASE = 27536;
    public static final int PART_NONE = 0;
    public static final int PART_FINGERS = 10;
    public static final int PART_FACES = 20;
    public static final int PART_IRISES = 30;
    public static final int PART_PALMS = 40;
    public static final int PARAMETER_MATCHING_THRESHOLD = 100;
    public static final int PARAMETER_FUSION_TYPE = 200;
    public static final int PARAMETER_FACES_MATCHING_THRESHOLD = 215;
    public static final int PARAMETER_IRISES_MATCHING_THRESHOLD = 220;
    public static final int PARAMETER_FINGERS_MAXIMAL_ROTATION = 3737;
    public static final int PARAMETER_FINGERS_MATCHING_SPEED = 3756;
    public static final int PARAMETER_FINGERS_MODE = 4536;
    public static final int PARAMETER_FINGERS_MIN_MATCHED_COUNT = 4737;
    public static final int PARAMETER_FINGERS_MIN_MATCHED_COUNT_THRESHOLD = 4738;
    public static final int PARAMETER_FACES_MATCHING_SPEED = 11537;
    public static final int PARAMETER_IRISES_MATCHING_SPEED = 19537;
    public static final int PARAMETER_IRISES_MAXIMAL_ROTATION = 19538;
    public static final int PARAMETER_IRISES_MIN_MATCHED_COUNT = 20737;
    public static final int PARAMETER_IRISES_MIN_MATCHED_COUNT_THRESHOLD = 20738;
    public static final int PARAMETER_PALMS_MAXIMAL_ROTATION = 27737;
    public static final int PARAMETER_PALMS_MATCHING_SPEED = 27756;
    public static final int PARAMETER_PALMS_MIN_MATCHED_COUNT = 28737;
    public static final int PARAMETER_PALMS_MIN_MATCHED_COUNT_THRESHOLD = 28738;
    public static final int FINGERS_MODE_GENERAL = 0;
    public static final int FINGERS_MODE_DIGITAL_PERSONA_U_ARE_U = 100;
    public static final int FINGERS_MODE_BIOMETRIKA_FX2000 = 200;
    public static final int FINGERS_MODE_BIOMETRIKA_FX3000 = 201;
    public static final int FINGERS_MODE_KEYTRONIC_SECURE_DESKTOP = 300;
    public static final int FINGERS_MODE_IDENTIX_TOUCH_VIEW = 400;
    public static final int FINGERS_MODE_IDENTIX_DFR2090 = 401;
    public static final int FINGERS_MODE_PRECISE_BIOMETRICS_100CS = 500;
    public static final int FINGERS_MODE_UPEK_TOUCH_CHIP = 600;
    public static final int FINGERS_MODE_IDENTICATOR_TECHNOLOGY_DF90 = 700;
    public static final int FINGERS_MODE_AUTHENTEC_AFS2 = 800;
    public static final int FINGERS_MODE_AUTHENTEC_AES4000 = 810;
    public static final int FINGERS_MODE_AUTHENTEC_AES2501B = 811;
    public static final int FINGERS_MODE_ATMEL_FINGER_CHIP = 900;
    public static final int FINGERS_MODE_BMF_BLP100 = 1000;
    public static final int FINGERS_MODE_SECUGEN_HAMSTER = 1100;
    public static final int FINGERS_MODE_ETHENTICA = 1200;
    public static final int FINGERS_MODE_CROSS_MATCH_VERIFIER_300 = 1300;
    public static final int FINGERS_MODE_NITGEN_FINGKEY_HAMSTER = 1400;
    public static final int FINGERS_MODE_TESTECH_BIO_I = 1500;
    public static final int FINGERS_MODE_DIGENT_IZZIX = 1600;
    public static final int FINGERS_MODE_STARTEK_FM200 = 1700;
    public static final int FINGERS_MODE_FUJITSU_MBF200 = 1800;
    public static final int FINGERS_MODE_FUTRONIC_FS80 = 1900;
    public static final int FINGERS_MODE_LIGH_TUNING_LTTC500 = 2000;
    public static final int FINGERS_MODE_TACOMA_CMOS = 2100;
    public static final NNativeType NATIVE_TYPE;
    
    private NMatcher(final HNObject handle, final boolean claimHandle) {
        super(handle, NMatcher.NATIVE_TYPE, claimHandle);
    }
    
    public NMatcher() {
        this(create(), true);
    }
    
    public static NMatcher fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NMatcher fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NMatcher value = new NMatcher(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private static HNObject create() {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NMatcher.LIBRARY.NMCreate(rhValue));
        return rhValue.getValue();
    }
    
    public NMatcherResult verify(final ByteBuffer template1, final ByteBuffer template2, final boolean returnMatchingDetails) {
        if (template1 == null) {
            throw new NullPointerException("template1");
        }
        if (template2 == null) {
            throw new NullPointerException("template2");
        }
        NMatcherResult result = null;
        final IntByReference rScore = new IntByReference();
        HNObjectByReference rhMatchingDetails = null;
        HNObject hMatchingDetails = null;
        NMatchingDetails matchingDetails = null;
        if (returnMatchingDetails) {
            rhMatchingDetails = new HNObjectByReference();
        }
        try {
            final ByteBuffer t1 = template1.slice();
            template1.position(template1.limit());
            final ByteBuffer t2 = template2.slice();
            template2.position(template2.limit());
            NResult.check(NMatcher.LIBRARY.NMVerifyEx(this.getHandle(), t1, new NativeSize((long)t1.limit()), t2, new NativeSize((long)t2.limit()), rhMatchingDetails, rScore));
            if (returnMatchingDetails) {
                hMatchingDetails = rhMatchingDetails.getValue();
                matchingDetails = NMatchingDetails.fromHandle(hMatchingDetails);
            }
            result = new NMatcherResult(matchingDetails, rScore.getValue());
        }
        finally {
            if (matchingDetails == null) {
                NObject.free(hMatchingDetails);
            }
        }
        return result;
    }
    
    public NMatchingDetails identifyStart(final ByteBuffer template) {
        if (template == null) {
            throw new NullPointerException("template");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        HNObject hValue = null;
        NMatchingDetails value = null;
        try {
            NResult.check(NMatcher.LIBRARY.NMIdentifyStartEx(this.getHandle(), template.slice(), new NativeSize((long)template.remaining()), rhValue));
            template.position(template.limit());
            hValue = rhValue.getValue();
            value = NMatchingDetails.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public int identifyNext(final ByteBuffer template) {
        return this.identifyNext(template, null);
    }
    
    public int identifyNext(final ByteBuffer template, final NMatchingDetails matchingDetails) {
        if (template == null) {
            throw new NullPointerException("template");
        }
        final IntByReference rValue = new IntByReference();
        NResult.check(NMatcher.LIBRARY.NMIdentifyNextEx(this.getHandle(), template.slice(), new NativeSize((long)template.remaining()), (matchingDetails == null) ? HNObject.NULL : matchingDetails.getHandle(), rValue));
        template.position(template.limit());
        return rValue.getValue();
    }
    
    public void identifyEnd() {
        NResult.check(NMatcher.LIBRARY.NMIdentifyEnd(this.getHandle()));
    }
    
    public void prepareTemplate(final NTemplate template) {
        if (template == null) {
            throw new NullPointerException("template");
        }
        NResult.check(NMatcher.LIBRARY.NMPrepareTemplate(this.getHandle(), template.getHandle()));
    }
    
    public int getMatchingThreshold() {
        return (int)this.getParameter(100, Integer.class);
    }
    
    public void setMatchingThreshold(final int value) {
        this.setParameter(100, Integer.class, value);
    }
    
    public NMFusionType getFusionType() {
        return NMFusionType.get((int)this.getParameter(200, Integer.class));
    }
    
    public void setFusionType(final NMFusionType value) {
        this.setParameter(200, Integer.class, value.getValue());
    }
    
    public int getFacesMatchingThreshold() {
        return (int)this.getParameter(215, Integer.class);
    }
    
    public void setFacesMatchingThreshold(final int value) {
        this.setParameter(215, Integer.class, value);
    }
    
    public int getIrisesMatchingThreshold() {
        return (int)this.getParameter(220, Integer.class);
    }
    
    public void setIrisesMatchingThreshold(final int value) {
        this.setParameter(220, Integer.class, value);
    }
    
    public int getFingersMaximalRotation() {
        return (byte)this.getParameter(3737, Byte.class) & 0xFF;
    }
    
    public void setFingersMaximalRotation(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(3737, Byte.class, (byte)value);
    }
    
    public NMSpeed getFingersMatchingSpeed() {
        return NMSpeed.get((int)this.getParameter(3756, Integer.class));
    }
    
    public void setFingersMatchingSpeed(final NMSpeed value) {
        this.setParameter(3756, Integer.class, value.getValue());
    }
    
    public int getFingersMode() {
        return (int)this.getParameter(4536, Integer.class);
    }
    
    public void setFingersMode(final int value) {
        this.setParameter(4536, Integer.class, value);
    }
    
    public int getFingersMinMatchedCount() {
        return (int)this.getParameter(4737, Integer.class);
    }
    
    public void setFingersMinMatchedCount(final int value) {
        this.setParameter(4737, Integer.class, value);
    }
    
    public int getFingersMinMatchedCountThreshold() {
        return (int)this.getParameter(4738, Integer.class);
    }
    
    public void setFingersMinMatchedCountThreshold(final int value) {
        this.setParameter(4738, Integer.class, value);
    }
    
    public NMSpeed getFacesMatchingSpeed() {
        return NMSpeed.get((int)this.getParameter(11537, Integer.class));
    }
    
    public void setFacesMatchingSpeed(final NMSpeed value) {
        this.setParameter(11537, Integer.class, value.getValue());
    }
    
    public NMSpeed getIrisesMatchingSpeed() {
        return NMSpeed.get((int)this.getParameter(19537, Integer.class));
    }
    
    public void setIrisesMatchingSpeed(final NMSpeed value) {
        this.setParameter(19537, Integer.class, value.getValue());
    }
    
    public int getIrisesMaximalRotation() {
        return (byte)this.getParameter(19538, Byte.class) & 0xFF;
    }
    
    public void setIrisesMaximalRotation(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(19538, Byte.class, (byte)value);
    }
    
    public int getIrisesMinMatchedCount() {
        return (int)this.getParameter(20737, Integer.class);
    }
    
    public void setIrisesMinMatchedCount(final int value) {
        this.setParameter(20737, Integer.class, value);
    }
    
    public int getIrisesMinMatchedCountThreshold() {
        return (int)this.getParameter(20738, Integer.class);
    }
    
    public void setIrisesMinMatchedCountThreshold(final int value) {
        this.setParameter(20738, Integer.class, value);
    }
    
    public int getPalmsMaximalRotation() {
        return (byte)this.getParameter(27737, Byte.class) & 0xFF;
    }
    
    public void setPalmsMaximalRotation(final int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("value must be in range [0..255].");
        }
        this.setParameter(27737, Byte.class, (byte)value);
    }
    
    public NMSpeed getPalmsMatchingSpeed() {
        return NMSpeed.get((int)this.getParameter(27756, Integer.class));
    }
    
    public void setPalmsMatchingSpeed(final NMSpeed value) {
        this.setParameter(27756, Integer.class, value.getValue());
    }
    
    public int getPalmsMinMatchedCount() {
        return (int)this.getParameter(28737, Integer.class);
    }
    
    public void setPalmsMinMatchedCount(final int value) {
        this.setParameter(28737, Integer.class, value);
    }
    
    public int getPalmsMinMatchedCountThreshold() {
        return (int)this.getParameter(28738, Integer.class);
    }
    
    public void setPalmsMinMatchedCountThreshold(final int value) {
        this.setParameter(28738, Integer.class, value);
    }
    
    static {
        LIBRARY = (NMatcherLibrary)Native.loadLibrary("NBiometrics", NMatcherLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NMatcher.LIBRARY.NMatcherTypeOf());
    }
    
    public final class NMatcherResult
    {
        private NMatchingDetails matchDetails;
        private int score;
        
        public NMatcherResult(final NMatchingDetails matchDetails, final int score) {
            this.matchDetails = matchDetails;
            this.score = score;
        }
        
        public NMatchingDetails getMatchingDetails() {
            return this.matchDetails;
        }
        
        public void setMatchingDetails(final NMatchingDetails matchDetails) {
            this.matchDetails = matchDetails;
        }
        
        public int getScore() {
            return this.score;
        }
        
        public void setScore(final int score) {
            this.score = score;
        }
    }
    
    interface NMatcherLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NMatcherTypeOf();
        
        int NMCreate(final HNObjectByReference p0);
        
        int NMVerifyEx(final HNObject p0, final Buffer p1, final NativeSize p2, final Buffer p3, final NativeSize p4, final HNObjectByReference p5, final IntByReference p6);
        
        int NMIdentifyStartEx(final HNObject p0, final Buffer p1, final NativeSize p2, final HNObjectByReference p3);
        
        int NMIdentifyNextEx(final HNObject p0, final Buffer p1, final NativeSize p2, final HNObject p3, final IntByReference p4);
        
        int NMIdentifyEnd(final HNObject p0);
        
        int NMPrepareTemplate(final HNObject p0, final HNObject p1);
    }
}
