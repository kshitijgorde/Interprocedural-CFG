// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices;

import com.neurotec.jna.NLibrary;
import com.neurotec.util.NObjectFlatReadOnlyCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.lang.NCore;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.ptr.IntByReference;
import java.util.EnumSet;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.plugins.NPlugin;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public class NDevice extends NObject
{
    static final NDeviceLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    private NPlugin plugin;
    private NDevice parent;
    private ChildCollection children;
    
    NDevice(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType, false);
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NDevice.LIBRARY.NDeviceGetPlugin(this.getHandle(), rhValue));
        this.plugin = NDeviceManager.pluginManager.getPlugins().get(rhValue.getValue());
        this.children = new ChildCollection(this);
    }
    
    NDevice(final HNObject handle) {
        this(handle, NDevice.NATIVE_TYPE);
    }
    
    static NDevice fromHandle(final HNObject handle) {
        if (!NDevice.NATIVE_TYPE.isInstanceOfType(handle)) {
            throw new IllegalArgumentException("The NDevice handle native type is unknown");
        }
        if (NCamera.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new NCamera(handle);
        }
        if (!NBiometricDevice.NATIVE_TYPE.isInstanceOfType(handle)) {
            return new NDevice(handle);
        }
        if (NFScanner.NATIVE_TYPE.isInstanceOfType(handle)) {
            if (NFingerScanner.NATIVE_TYPE.isInstanceOfType(handle)) {
                return new NFingerScanner(handle);
            }
            if (NPalmScanner.NATIVE_TYPE.isInstanceOfType(handle)) {
                return new NPalmScanner(handle);
            }
            return new NFScanner(handle);
        }
        else {
            if (NIrisScanner.NATIVE_TYPE.isInstanceOfType(handle)) {
                return new NIrisScanner(handle);
            }
            return new NBiometricDevice(handle);
        }
    }
    
    HNObject getParentHandle() {
        final HNObjectByReference rValue = new HNObjectByReference();
        NResult.check(NDevice.LIBRARY.NDeviceGetParent(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final NDeviceManager getOwner() {
        return (NDeviceManager)super.getOwner();
    }
    
    public final NPlugin getPlugin() {
        return this.plugin;
    }
    
    public final EnumSet<NDeviceType> getDeviceType() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NDevice.LIBRARY.NDeviceGetDeviceType(this.getHandle(), rValue));
        return NDeviceType.getSet(rValue.getValue());
    }
    
    public final boolean isAvailable() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NDevice.LIBRARY.NDeviceIsAvailable(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final boolean isPrivate() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NDevice.LIBRARY.NDeviceIsPrivate(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public NDevice getParent() {
        return this.parent;
    }
    
    void setParent(final NDevice parent) {
        this.parent = parent;
    }
    
    public final String getId() {
        final int pLen = NDevice.LIBRARY.NDeviceGetId(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(NDevice.LIBRARY.NDeviceGetId(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public final String getDisplayName() {
        final int pLen = NDevice.LIBRARY.NDeviceGetDisplayName(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(NDevice.LIBRARY.NDeviceGetDisplayName(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public final String getMake() {
        final int pLen = NDevice.LIBRARY.NDeviceGetMake(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(NDevice.LIBRARY.NDeviceGetMake(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public final String getModel() {
        final int pLen = NDevice.LIBRARY.NDeviceGetModel(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(NDevice.LIBRARY.NDeviceGetModel(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public final String getSerialNumber() {
        final int pLen = NDevice.LIBRARY.NDeviceGetSerialNumber(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(NDevice.LIBRARY.NDeviceGetSerialNumber(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public final ChildCollection getChildren() {
        return this.children;
    }
    
    public String toString() {
        return this.getDisplayName();
    }
    
    static {
        LIBRARY = (NDeviceLibrary)Native.loadLibrary("NDevices", NDeviceLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NDevice.LIBRARY.NDeviceTypeOf());
    }
    
    public static final class ChildCollection extends NObjectFlatReadOnlyCollection<NDevice>
    {
        protected ChildCollection(final NObject owner) {
            super(owner, false);
        }
        
        public NDevice fromHandleNative(final HNObject handle) {
            throw new UnsupportedOperationException();
        }
        
        public HNObject getNative(final int index) {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NDevice.LIBRARY.NDeviceGetChildCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
        
        void onAdded(final NDevice item) {
            this.addItem(item);
        }
        
        void onRemoved(final NDevice item) {
            final int index = this.indexOf(item);
            if (index != -1) {
                this.removeItem(index);
            }
        }
        
        void onClear() {
            this.clearItems();
        }
    }
    
    interface NDeviceLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NDeviceTypeOf();
        
        int NDeviceGetPlugin(final HNObject p0, final HNObjectByReference p1);
        
        int NDeviceGetDeviceType(final HNObject p0, final IntByReference p1);
        
        int NDeviceIsAvailable(final HNObject p0, final BooleanByReference p1);
        
        int NDeviceIsPrivate(final HNObject p0, final BooleanByReference p1);
        
        int NDeviceGetParent(final HNObject p0, final HNObjectByReference p1);
        
        int NDeviceGetId(final HNObject p0, final Pointer p1, final int p2);
        
        int NDeviceGetDisplayName(final HNObject p0, final Pointer p1, final int p2);
        
        int NDeviceGetMake(final HNObject p0, final Pointer p1, final int p2);
        
        int NDeviceGetModel(final HNObject p0, final Pointer p1, final int p2);
        
        int NDeviceGetSerialNumber(final HNObject p0, final Pointer p1, final int p2);
        
        int NDeviceGetChildCount(final HNObject p0, final IntByReference p1);
    }
}
