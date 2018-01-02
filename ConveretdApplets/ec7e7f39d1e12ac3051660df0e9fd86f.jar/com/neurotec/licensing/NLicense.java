// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.licensing;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import java.io.IOException;
import com.neurotec.lang.NResult;
import com.sun.jna.WString;
import com.neurotec.jna.ptr.BooleanByReference;

public final class NLicense
{
    static NLicenseLibrary LIBRARY;
    
    public static boolean obtain(final String address, final String port, final String products) throws IOException {
        final BooleanByReference available = new BooleanByReference();
        NResult.checkAll(NResult.checkIO(NResult.checkUnchecked(NLicense.LIBRARY.NLicenseObtain(new WString(address), new WString(port), new WString(products), available))));
        return available.getValue();
    }
    
    public static boolean obtain(final String address, final int port, final String products) throws IOException {
        return obtain(address, String.valueOf(port), products);
    }
    
    public static boolean obtainComponents(final String address, final String port, final String components) throws IOException {
        final BooleanByReference available = new BooleanByReference();
        NResult.checkAll(NResult.checkIO(NResult.checkUnchecked(NLicense.LIBRARY.NLicenseObtainComponents(new WString(address), new WString(port), new WString(components), available))));
        return available.getValue();
    }
    
    public static boolean obtainComponents(final String address, final int port, final String components) throws IOException {
        return obtainComponents(address, String.valueOf(port), components);
    }
    
    public static void release(final String products) {
        NResult.check(NLicense.LIBRARY.NLicenseRelease(new WString(products)));
    }
    
    public static void releaseComponents(final String components) {
        NResult.check(NLicense.LIBRARY.NLicenseReleaseComponents(new WString(components)));
    }
    
    public static NLicenseInfo getInfo(final String product) throws IOException {
        final NLicenseInfo licenseInfo = new NLicenseInfo();
        NResult.checkAll(NResult.checkIO(NResult.checkUnchecked(NLicense.LIBRARY.NLicenseGetInfo(new WString(product), licenseInfo.getData()))));
        return licenseInfo;
    }
    
    public static boolean isComponentActivated(final String name) throws IOException {
        final BooleanByReference value = new BooleanByReference();
        NResult.checkAll(NResult.checkIO(NResult.checkUnchecked(NLicense.LIBRARY.NLicenseIsComponentActivated(new WString(name), value))));
        return value.getValue();
    }
    
    static {
        NLicense.LIBRARY = null;
        try {
            NLicense.LIBRARY = (NLicenseLibrary)Native.loadLibrary("NLicensing", NLicenseLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        }
        catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public interface NLicenseLibrary extends NLibrary
    {
        int NLicenseObtain(final WString p0, final WString p1, final WString p2, final BooleanByReference p3);
        
        int NLicenseRelease(final WString p0);
        
        int NLicenseObtainComponents(final WString p0, final WString p1, final WString p2, final BooleanByReference p3);
        
        int NLicenseReleaseComponents(final WString p0);
        
        int NLicenseGetInfo(final WString p0, final NLicenseInfo.NLicenseInfoData p1);
        
        int NLicenseIsComponentActivated(final WString p0, final BooleanByReference p1);
    }
}
