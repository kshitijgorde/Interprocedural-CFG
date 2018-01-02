// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;

public class NNativeModule extends NObject
{
    static final NNativeModuleLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    public static NNativeModule fromHandle(final HNObject handle) {
        return new NNativeModule(handle, NNativeModule.NATIVE_TYPE);
    }
    
    protected NNativeModule(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType, false);
    }
    
    public String toString() {
        return this.getName();
    }
    
    public boolean equals(final Object obj) {
        final NNativeModule module = (NNativeModule)obj;
        return obj != null && this.getHandle().equals(module.getHandle());
    }
    
    public int hashCode() {
        return this.getHandle().hashCode();
    }
    
    public NModuleOptions getOptions() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NNativeModule.LIBRARY.NModuleGetOptions(this.getHandle(), rValue));
        return NModuleOptions.get(rValue.getValue());
    }
    
    public String getName() {
        final int mLen = NNativeModule.LIBRARY.NModuleGetName(this.getHandle(), null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NNativeModule.LIBRARY.NModuleGetName(this.getHandle(), pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getTitle() {
        final int mLen = NNativeModule.LIBRARY.NModuleGetTitle(this.getHandle(), null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NNativeModule.LIBRARY.NModuleGetTitle(this.getHandle(), pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getProduct() {
        final int mLen = NNativeModule.LIBRARY.NModuleGetProduct(this.getHandle(), null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NNativeModule.LIBRARY.NModuleGetProduct(this.getHandle(), pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getCompany() {
        final int mLen = NNativeModule.LIBRARY.NModuleGetCompany(this.getHandle(), null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NNativeModule.LIBRARY.NModuleGetCompany(this.getHandle(), pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getCopyright() {
        final int mLen = NNativeModule.LIBRARY.NModuleGetCopyright(this.getHandle(), null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NNativeModule.LIBRARY.NModuleGetCopyright(this.getHandle(), pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getVersion() {
        final IntByReference rVersionMajor = new IntByReference();
        final IntByReference rVersionMinor = new IntByReference();
        final IntByReference rVersionBuild = new IntByReference();
        final IntByReference rVersionRevision = new IntByReference();
        NResult.check(NNativeModule.LIBRARY.NModuleGetVersionMajor(this.getHandle(), rVersionMajor));
        NResult.check(NNativeModule.LIBRARY.NModuleGetVersionMinor(this.getHandle(), rVersionMinor));
        NResult.check(NNativeModule.LIBRARY.NModuleGetVersionBuild(this.getHandle(), rVersionBuild));
        NResult.check(NNativeModule.LIBRARY.NModuleGetVersionRevision(this.getHandle(), rVersionRevision));
        return String.format("%s.%s.%s.%s", rVersionMajor.getValue(), rVersionMinor.getValue(), rVersionBuild.getValue(), rVersionRevision.getValue());
    }
    
    public String getActivated() {
        final int mLen = NNativeModule.LIBRARY.NModuleGetActivated(this.getHandle(), null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NNativeModule.LIBRARY.NModuleGetActivated(this.getHandle(), pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    static {
        LIBRARY = (NNativeModuleLibrary)Native.loadLibrary("NCore", NNativeModuleLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NNativeModule.LIBRARY.NModuleTypeOf());
    }
    
    interface NNativeModuleLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NModuleTypeOf();
        
        int NModuleGetName(final HNObject p0, final Pointer p1, final int p2);
        
        int NModuleGetOptions(final HNObject p0, final IntByReference p1);
        
        int NModuleGetTitle(final HNObject p0, final Pointer p1, final int p2);
        
        int NModuleGetProduct(final HNObject p0, final Pointer p1, final int p2);
        
        int NModuleGetCompany(final HNObject p0, final Pointer p1, final int p2);
        
        int NModuleGetCopyright(final HNObject p0, final Pointer p1, final int p2);
        
        int NModuleGetVersionMajor(final HNObject p0, final IntByReference p1);
        
        int NModuleGetVersionMinor(final HNObject p0, final IntByReference p1);
        
        int NModuleGetVersionBuild(final HNObject p0, final IntByReference p1);
        
        int NModuleGetVersionRevision(final HNObject p0, final IntByReference p1);
        
        int NModuleGetActivated(final HNObject p0, final Pointer p1, final int p2);
    }
}
