// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

public final class Bdif
{
    public static final int IRIS_DEVICE_UNIQUE_IDENTIFIER_LENGTH = 16;
    public static final int FLAG_NON_STRICT_READ = 1;
    public static final int FLAG_DO_NOT_CHECK_CBEFF_PRODUCT_ID = 2;
    public static final int FLAG_ALLOW_QUALITY = 4;
    public static final int FLAG_ALLOW_OUT_OF_BOUNDS_FEATURES = 8;
    
    static boolean standardIsValid(final BdifStandard value) {
        return value == BdifStandard.ANSI || value == BdifStandard.ISO;
    }
}
