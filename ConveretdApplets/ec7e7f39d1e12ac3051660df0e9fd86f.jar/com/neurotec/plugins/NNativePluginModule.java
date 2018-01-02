// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.plugins;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.neurotec.lang.NResult;
import com.neurotec.lang.NVersionRange;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NNativeModule;

public final class NNativePluginModule extends NNativeModule
{
    static final NNativePluginModuleLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    public static NNativePluginModule fromHandle(final HNObject handle) {
        return new NNativePluginModule(handle);
    }
    
    NNativePluginModule(final HNObject handle) {
        super(handle, NNativePluginModule.NATIVE_TYPE);
    }
    
    public String toString() {
        return this.getPluginName();
    }
    
    public NVersionRange[] getInterfaceVersions() {
        final int count = NResult.check(NNativePluginModule.LIBRARY.NPluginModuleGetInterfaceVersions(this.getHandle(), null, 0));
        final NVersionRange[] values = new NVersionRange[count];
        final int[] rValues = new int[count];
        NResult.check(NNativePluginModule.LIBRARY.NPluginModuleGetInterfaceVersions(this.getHandle(), rValues, count));
        for (int i = 0; i < values.length; ++i) {
            values[i] = new NVersionRange(rValues[i]);
        }
        return values;
    }
    
    public String getPluginName() {
        final int mLen = NNativePluginModule.LIBRARY.NPluginModuleGetPluginName(this.getHandle(), null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NNativePluginModule.LIBRARY.NPluginModuleGetPluginName(this.getHandle(), pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public String getInterfaceType() {
        final int mLen = NNativePluginModule.LIBRARY.NPluginModuleGetInterfaceType(this.getHandle(), null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NNativePluginModule.LIBRARY.NPluginModuleGetInterfaceType(this.getHandle(), pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public int getPriority() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NNativePluginModule.LIBRARY.NPluginModuleGetPriority(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public String getIncompatiblePlugins() {
        final int mLen = NNativePluginModule.LIBRARY.NPluginModuleGetIncompatiblePlugins(this.getHandle(), null, 0);
        if (NResult.isFailed(mLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(mLen);
        try {
            if (NResult.isFailed(NNativePluginModule.LIBRARY.NPluginModuleGetIncompatiblePlugins(this.getHandle(), pValue, mLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    static {
        LIBRARY = (NNativePluginModuleLibrary)Native.loadLibrary("NCore", NNativePluginModuleLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NNativePluginModule.LIBRARY.NPluginModuleTypeOf());
    }
    
    interface NNativePluginModuleLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NPluginModuleTypeOf();
        
        int NPluginModuleGetPluginName(final HNObject p0, final Pointer p1, final int p2);
        
        int NPluginModuleGetInterfaceType(final HNObject p0, final Pointer p1, final int p2);
        
        int NPluginModuleGetInterfaceVersions(final HNObject p0, final int[] p1, final int p2);
        
        int NPluginModuleGetPriority(final HNObject p0, final IntByReference p1);
        
        int NPluginModuleGetIncompatiblePlugins(final HNObject p0, final Pointer p1, final int p2);
    }
}
