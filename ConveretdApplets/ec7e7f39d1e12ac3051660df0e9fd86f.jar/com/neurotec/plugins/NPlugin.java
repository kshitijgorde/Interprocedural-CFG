// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.plugins;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.event.NChangeListener;
import com.sun.jna.ptr.ShortByReference;
import com.neurotec.lang.NVersion;
import com.sun.jna.ptr.LongByReference;
import com.neurotec.lang.NCore;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.Pointer;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.util.concurrent.NSyncChangeObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NPlugin extends NObject
{
    static final NPluginLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    private NNativePluginModule module;
    private Throwable error;
    private NSyncChangeObject changeObject;
    private NObjectLibrary.NObjectCallback changingCallback;
    private NObjectLibrary.NObjectCallback changedCallback;
    
    NPlugin(final HNObject handle) {
        super(handle, NPlugin.NATIVE_TYPE, false);
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NPlugin.LIBRARY.NPluginGetModule(this.getHandle(), rhValue));
        this.module = (HNObject.NULL.equals(rhValue.getValue()) ? null : NNativePluginModule.fromHandle(rhValue.getValue()));
        this.changingCallback = new NObjectLibrary.NObjectCallback() {
            public void invoke(final HNObject hDeviceManager, final Pointer pParam) {
                if (NPlugin.this.changeObject != null) {
                    NPlugin.this.changeObject.changing();
                }
            }
        };
        this.changedCallback = new NObjectLibrary.NObjectCallback() {
            public void invoke(final HNObject hDeviceManager, final Pointer pParam) {
                if (NPlugin.this.changeObject != null && NPlugin.this.changeObject.isChanging()) {
                    NPlugin.this.updateError();
                    NPlugin.this.changeObject.changed();
                }
            }
        };
        NResult.check(NPlugin.LIBRARY.NPluginAddChangingCallback(this.getHandle(), this.changingCallback, Pointer.NULL));
        NResult.check(NPlugin.LIBRARY.NPluginAddChangedCallback(this.getHandle(), this.changedCallback, Pointer.NULL));
        synchronized (this) {
            this.changeObject = new NSyncChangeObject(this, this);
            this.updateError();
        }
    }
    
    private void updateError() {
        final IntByReference rCode = new IntByReference();
        NResult.check(NPlugin.LIBRARY.NPluginGetErrorCode(this.getHandle(), rCode));
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NPlugin.LIBRARY.NPluginGetError(this.getHandle(), rhValue));
        this.error = NResult.getError(rCode.getValue(), rhValue.getValue());
    }
    
    protected void dispose(final boolean disposing) {
        final NSyncChangeObject changeObject = this.changeObject;
        this.changeObject = null;
        if (this.getHandle() != null) {
            try {
                NResult.check(NPlugin.LIBRARY.NPluginRemoveChangingCallback(this.getHandle(), this.changingCallback, null));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            try {
                NResult.check(NPlugin.LIBRARY.NPluginRemoveChangedCallback(this.getHandle(), this.changedCallback, null));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
        }
        if (disposing) {
            changeObject.dispose();
        }
        this.module = null;
        this.error = null;
        this.changingCallback = null;
        this.changedCallback = null;
        super.dispose(disposing);
    }
    
    public void plug() {
        NResult.check(NPlugin.LIBRARY.NPluginPlug(this.getHandle()));
    }
    
    public void unplug() {
        NResult.check(NPlugin.LIBRARY.NPluginUnplug(this.getHandle()));
    }
    
    public void enable() {
        NResult.check(NPlugin.LIBRARY.NPluginEnable(this.getHandle()));
    }
    
    public void disable() {
        NResult.check(NPlugin.LIBRARY.NPluginDisable(this.getHandle()));
    }
    
    public Throwable getError() {
        return this.error;
    }
    
    public NNativePluginModule getModule() {
        return this.module;
    }
    
    public String getFileName() {
        final int pLen = NPlugin.LIBRARY.NPluginGetFileName(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(NPlugin.LIBRARY.NPluginGetFileName(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public long getLoadTime() {
        final LongByReference rValue = new LongByReference();
        NResult.check(NPlugin.LIBRARY.NPluginGetLoadTime(this.getHandle(), rValue));
        return rValue.getValue() / 10000L;
    }
    
    public NPluginState getState() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NPlugin.LIBRARY.NPluginGetState(this.getHandle(), rValue));
        return NPluginState.get(rValue.getValue());
    }
    
    public NVersion getSelectedInterfaceVersion() {
        final ShortByReference rValue = new ShortByReference();
        NResult.check(NPlugin.LIBRARY.NPluginGetSelectedInterfaceVersion(this.getHandle(), rValue));
        return new NVersion(rValue.getValue());
    }
    
    public long getPlugTime() {
        final LongByReference rValue = new LongByReference();
        NResult.check(NPlugin.LIBRARY.NPluginGetPlugTime(this.getHandle(), rValue));
        return rValue.getValue() / 10000L;
    }
    
    public synchronized void addPluginListener(final NChangeListener listener) {
        this.changeObject.addListener(listener);
    }
    
    public synchronized void removePluginListener(final NChangeListener listener) {
        this.changeObject.removeListener(listener);
    }
    
    static {
        LIBRARY = (NPluginLibrary)Native.loadLibrary("NCore", NPluginLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NPlugin.LIBRARY.NPluginTypeOf());
    }
    
    interface NPluginLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NPluginTypeOf();
        
        int NPluginPlug(final HNObject p0);
        
        int NPluginUnplug(final HNObject p0);
        
        int NPluginEnable(final HNObject p0);
        
        int NPluginDisable(final HNObject p0);
        
        int NPluginGetFileName(final HNObject p0, final Pointer p1, final int p2);
        
        int NPluginGetState(final HNObject p0, final IntByReference p1);
        
        int NPluginGetErrorCode(final HNObject p0, final IntByReference p1);
        
        int NPluginGetError(final HNObject p0, final HNObjectByReference p1);
        
        int NPluginGetLoadTime(final HNObject p0, final LongByReference p1);
        
        int NPluginGetModule(final HNObject p0, final HNObjectByReference p1);
        
        int NPluginGetSelectedInterfaceVersion(final HNObject p0, final ShortByReference p1);
        
        int NPluginGetPlugTime(final HNObject p0, final LongByReference p1);
        
        int NPluginAddChangingCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginRemoveChangingCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginAddChangedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginRemoveChangedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
    }
}
