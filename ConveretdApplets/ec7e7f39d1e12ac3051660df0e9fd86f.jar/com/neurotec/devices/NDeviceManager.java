// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices;

import com.sun.jna.win32.StdCallLibrary;
import com.neurotec.jna.NLibrary;
import com.sun.jna.WString;
import java.util.Iterator;
import com.neurotec.util.NObjectReadOnlyCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import java.util.EventObject;
import com.neurotec.event.NChangeListener;
import com.neurotec.devices.event.NDevicesListener;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObjectByReference;
import java.util.EnumSet;
import com.neurotec.lang.NResult;
import com.neurotec.devices.event.NDeviceEvent;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.HNObject;
import javax.swing.event.EventListenerList;
import com.neurotec.util.concurrent.NSyncChangeObject;
import com.neurotec.plugins.NPluginManager;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public final class NDeviceManager extends NObject
{
    static final NDeviceManagerLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    public static NPluginManager pluginManager;
    private NSyncChangeObject devicesChangeObject;
    private boolean devicesSyncSetup;
    private NObjectLibrary.NObjectCallback devicesChangingCallback;
    private NObjectLibrary.NObjectCallback devicesChangedCallback;
    private NDeviceManagerLibrary.NDeviceManagerDeviceCallback deviceAddedCallback;
    private NDeviceManagerLibrary.NDeviceManagerDeviceCallback deviceRemovedCallback;
    private NObjectLibrary.NObjectCallback devicesRefreshedCallback;
    private EventListenerList devicesListeners;
    private DeviceCollection devices;
    
    private NDeviceManager(final HNObject handle, final boolean claimHandle) {
        super(handle, NDeviceManager.NATIVE_TYPE, claimHandle);
        this.devicesSyncSetup = false;
        this.devicesListeners = new EventListenerList();
        this.devicesChangingCallback = new NObjectLibrary.NObjectCallback() {
            public void invoke(final HNObject hDeviceManager, final Pointer pParam) {
                if (NDeviceManager.this.devicesSyncSetup) {
                    NDeviceManager.this.devicesChangeObject.changing();
                }
            }
        };
        this.devicesChangedCallback = new NObjectLibrary.NObjectCallback() {
            public void invoke(final HNObject hDeviceManager, final Pointer pParam) {
                if (NDeviceManager.this.devicesSyncSetup) {
                    NDeviceManager.this.devicesChangeObject.changed();
                }
            }
        };
        this.deviceAddedCallback = new NDeviceManagerLibrary.NDeviceManagerDeviceCallback() {
            public void invoke(final HNObject hDeviceManager, final HNObject hDevice, final Pointer pParam) {
                if (NDeviceManager.this.devicesChangeObject != null && NDeviceManager.this.devicesChangeObject.isChanging()) {
                    final NDevice device = NDeviceManager.this.devices.onAdded(hDevice);
                    NDeviceManager.this.deviceAdded(new NDeviceEvent(this, device));
                }
            }
        };
        this.deviceRemovedCallback = new NDeviceManagerLibrary.NDeviceManagerDeviceCallback() {
            public void invoke(final HNObject hDeviceManager, final HNObject hDevice, final Pointer pParam) {
                if (NDeviceManager.this.devicesChangeObject != null && NDeviceManager.this.devicesChangeObject.isChanging()) {
                    final NDevice device = NDeviceManager.this.devices.onRemoved(hDevice);
                    try {
                        NDeviceManager.this.deviceRemoved(new NDeviceEvent(this, device));
                    }
                    finally {
                        NDeviceManager.this.releaseObject(device);
                    }
                }
            }
        };
        this.devicesRefreshedCallback = new NObjectLibrary.NObjectCallback() {
            public void invoke(final HNObject hDeviceManager, final Pointer pParam) {
                if (NDeviceManager.this.devicesChangeObject != null && NDeviceManager.this.devicesChangeObject.isChanging()) {
                    NDeviceManager.this.devices.onRefresh();
                    NDeviceManager.this.devicesRefreshed();
                }
            }
        };
        this.devices = new DeviceCollection(this);
        NResult.check(NDeviceManager.LIBRARY.NDeviceManagerAddDevicesChangingCallback(handle, this.devicesChangingCallback, Pointer.NULL));
        NResult.check(NDeviceManager.LIBRARY.NDeviceManagerAddDevicesChangedCallback(handle, this.devicesChangedCallback, Pointer.NULL));
        synchronized (this.devices) {
            this.devicesChangeObject = new NSyncChangeObject(this, this.devices);
            this.devicesSyncSetup = true;
            NResult.check(NDeviceManager.LIBRARY.NDeviceManagerAddDeviceAddedCallback(handle, this.deviceAddedCallback, Pointer.NULL));
            NResult.check(NDeviceManager.LIBRARY.NDeviceManagerAddDeviceRemovedCallback(handle, this.deviceRemovedCallback, Pointer.NULL));
            NResult.check(NDeviceManager.LIBRARY.NDeviceManagerAddDevicesRefreshedCallback(handle, this.devicesRefreshedCallback, Pointer.NULL));
            this.devices.onRefresh();
        }
    }
    
    public NDeviceManager() {
        this(EnumSet.of(NDeviceType.ANY), true, true);
    }
    
    public NDeviceManager(final EnumSet<NDeviceType> deviceTypes) {
        this(deviceTypes, true, true);
    }
    
    public NDeviceManager(final EnumSet<NDeviceType> deviceTypes, final boolean autoPlug) {
        this(deviceTypes, autoPlug, true);
    }
    
    public NDeviceManager(final EnumSet<NDeviceType> deviceTypes, final boolean autoPlug, final boolean autoUpdate) {
        this(create(deviceTypes, autoPlug, autoUpdate), true);
    }
    
    private static HNObject create(final EnumSet<NDeviceType> deviceTypes, final boolean autoPlug, final boolean autoUpdate) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NDeviceManager.LIBRARY.NDeviceManagerCreate(NDeviceType.getValue(deviceTypes), autoPlug, autoUpdate, rhValue));
        return rhValue.getValue();
    }
    
    public static NDeviceManager fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NDeviceManager fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NDeviceManager deviceManager = new NDeviceManager(handle, false);
        if (ownsHandle) {
            deviceManager.claimHandle();
        }
        return deviceManager;
    }
    
    protected void dispose(final boolean disposing) {
        final NSyncChangeObject devicesChangeObject = this.devicesChangeObject;
        this.devicesChangeObject = null;
        if (this.getHandle() != null) {
            try {
                NResult.check(NDeviceManager.LIBRARY.NDeviceManagerRemoveDevicesChangingCallback(this.getHandle(), this.devicesChangingCallback, null));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            try {
                NResult.check(NDeviceManager.LIBRARY.NDeviceManagerRemoveDevicesChangedCallback(this.getHandle(), this.devicesChangedCallback, null));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            try {
                NResult.check(NDeviceManager.LIBRARY.NDeviceManagerRemoveDeviceAddedCallback(this.getHandle(), this.deviceAddedCallback, null));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            try {
                NResult.check(NDeviceManager.LIBRARY.NDeviceManagerRemoveDeviceRemovedCallback(this.getHandle(), this.deviceRemovedCallback, null));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            try {
                NResult.check(NDeviceManager.LIBRARY.NDeviceManagerRemoveDevicesRefreshedCallback(this.getHandle(), this.devicesRefreshedCallback, null));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            this.devicesSyncSetup = false;
        }
        if (disposing) {
            this.devices.disposeItems();
            devicesChangeObject.dispose();
        }
        this.devicesChangingCallback = null;
        this.devicesChangedCallback = null;
        this.deviceAddedCallback = null;
        this.deviceRemovedCallback = null;
        this.devicesRefreshedCallback = null;
        this.devices = null;
        super.dispose(disposing);
    }
    
    public void refresh() {
        NResult.check(NDeviceManager.LIBRARY.NDeviceManagerRefresh(this.getHandle()));
    }
    
    public EnumSet<NDeviceType> getDeviceTypes() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NDeviceManager.LIBRARY.NDeviceManagerGetDeviceTypes(this.getHandle(), rValue));
        return NDeviceType.getSet(rValue.getValue());
    }
    
    public boolean isAutoUpdate() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NDeviceManager.LIBRARY.NDeviceManagerGetAutoUpdate(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public DeviceCollection getDevices() {
        this.check();
        return this.devices;
    }
    
    public synchronized void addDevicesListener(final NDevicesListener listener) {
        this.devicesListeners.add(NDevicesListener.class, listener);
        this.devicesChangeObject.addListener(listener);
    }
    
    public synchronized void removeDevicesListener(final NDevicesListener listener) {
        this.devicesListeners.remove(NDevicesListener.class, listener);
        this.devicesChangeObject.removeListener(listener);
    }
    
    private synchronized void deviceAdded(final NDeviceEvent event) {
        final Object[] listeners = this.devicesListeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == NDevicesListener.class) {
                ((NDevicesListener)listeners[i + 1]).deviceAdded(event);
            }
        }
    }
    
    private synchronized void deviceRemoved(final NDeviceEvent event) {
        final Object[] listeners = this.devicesListeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == NDevicesListener.class) {
                ((NDevicesListener)listeners[i + 1]).deviceRemoved(event);
            }
        }
    }
    
    private synchronized void devicesRefreshed() {
        final Object[] listeners = this.devicesListeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == NDevicesListener.class) {
                ((NDevicesListener)listeners[i + 1]).devicesRefreshed(new EventObject(this));
            }
        }
    }
    
    static {
        LIBRARY = (NDeviceManagerLibrary)Native.loadLibrary("NDevices", NDeviceManagerLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NDeviceManager.LIBRARY.NDeviceManagerTypeOf());
        final HNObjectByReference rhPluginManager = new HNObjectByReference();
        NResult.check(NDeviceManager.LIBRARY.NDeviceManagerGetPluginManager(rhPluginManager));
        NDeviceManager.pluginManager = NPluginManager.fromHandle(rhPluginManager.getValue());
    }
    
    public static final class DeviceCollection extends NObjectReadOnlyCollection<NDevice>
    {
        protected DeviceCollection(final NDeviceManager owner) {
            super(owner, false);
        }
        
        private void updateChildren() {
            for (final NDevice item : this) {
                this.updateChildren(item, true);
            }
        }
        
        private void updateChildren(final NDevice device, final boolean batch) {
            final HNObject hDevice = device.getHandle();
            final HNObject hParent = device.getParentHandle();
            for (final NDevice otherDevice : this) {
                if (!batch) {
                    final HNObject hOtherParent = otherDevice.getParentHandle();
                    if (hOtherParent != null && hOtherParent == hDevice) {
                        otherDevice.setParent(device);
                        device.getChildren().onAdded(otherDevice);
                    }
                }
                if (!hParent.equals(null) && otherDevice.getHandle().equals(hParent)) {
                    device.setParent(otherDevice);
                    otherDevice.getChildren().onAdded(device);
                    if (!batch) {
                        break;
                    }
                    continue;
                }
            }
        }
        
        public NDevice fromHandleNative(final HNObject handle) {
            return NDevice.fromHandle(handle);
        }
        
        public HNObject getNative(final int index) {
            final HNObjectByReference rValue = new HNObjectByReference();
            NResult.check(NDeviceManager.LIBRARY.NDeviceManagerGetDevice(this.getOwner().getHandle(), index, rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NDeviceManager.LIBRARY.NDeviceManagerGetDeviceCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
        
        NDevice onAdded(final HNObject hDevice) {
            final NDevice device = this.addItem(hDevice);
            this.updateChildren(device, false);
            return device;
        }
        
        NDevice onRemoved(final HNObject hDevice) {
            final NDevice device = this.removeItemNoDispose(this.indexOf(hDevice));
            for (final NDevice childDevice : device.getChildren()) {
                childDevice.setParent(null);
            }
            if (device.getParent() != null) {
                device.getParent().getChildren().onRemoved(device);
                device.setParent(null);
            }
            return device;
        }
        
        void onRefresh() {
            this.refreshItems();
            for (final NDevice item : this) {
                item.setParent(null);
                item.getChildren().onClear();
            }
            this.updateChildren();
        }
        
        public int indexOf(final String id) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(NDeviceManager.LIBRARY.NDeviceManagerGetDeviceById(this.getOwner().getHandle(), new WString(id), rhValue));
            return this.indexOf(rhValue.getValue());
        }
        
        public NDevice get(final String id) {
            final HNObjectByReference rhValue = new HNObjectByReference();
            NResult.check(NDeviceManager.LIBRARY.NDeviceManagerGetDeviceById(this.getOwner().getHandle(), new WString(id), rhValue));
            return this.get(rhValue.getValue());
        }
    }
    
    interface NDeviceManagerLibrary extends NLibrary
    {
        int NDeviceManagerGetPluginManager(final HNObjectByReference p0);
        
        NNativeType.NNativeTypeLibrary.HNType NDeviceManagerTypeOf();
        
        int NDeviceManagerCreate(final int p0, final boolean p1, final boolean p2, final HNObjectByReference p3);
        
        int NDeviceManagerRefresh(final HNObject p0);
        
        int NDeviceManagerGetDeviceTypes(final HNObject p0, final IntByReference p1);
        
        int NDeviceManagerGetAutoUpdate(final HNObject p0, final BooleanByReference p1);
        
        int NDeviceManagerAddDevicesChangingCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NDeviceManagerRemoveDevicesChangingCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NDeviceManagerAddDevicesChangedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NDeviceManagerRemoveDevicesChangedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NDeviceManagerAddDeviceAddedCallback(final HNObject p0, final NDeviceManagerDeviceCallback p1, final Pointer p2);
        
        int NDeviceManagerRemoveDeviceAddedCallback(final HNObject p0, final NDeviceManagerDeviceCallback p1, final Pointer p2);
        
        int NDeviceManagerAddDeviceRemovedCallback(final HNObject p0, final NDeviceManagerDeviceCallback p1, final Pointer p2);
        
        int NDeviceManagerRemoveDeviceRemovedCallback(final HNObject p0, final NDeviceManagerDeviceCallback p1, final Pointer p2);
        
        int NDeviceManagerAddDevicesRefreshedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NDeviceManagerRemoveDevicesRefreshedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NDeviceManagerGetDeviceCount(final HNObject p0, final IntByReference p1);
        
        int NDeviceManagerGetDevice(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NDeviceManagerGetDeviceById(final HNObject p0, final WString p1, final HNObjectByReference p2);
        
        public interface NDeviceManagerDeviceCallback extends StdCallLibrary.StdCallCallback
        {
            void invoke(final HNObject p0, final HNObject p1, final Pointer p2);
        }
    }
}
