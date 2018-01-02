// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.plugins;

import com.sun.jna.win32.StdCallLibrary;
import com.neurotec.jna.NLibrary;
import com.neurotec.util.NSimpleCollection;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.util.NObjectReadOnlyCollection;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.event.NChangeListener;
import com.neurotec.event.NPluginsListener;
import com.neurotec.jna.ptr.BooleanByReference;
import com.sun.jna.WString;
import com.neurotec.lang.NCore;
import com.neurotec.lang.NVersionRange;
import com.neurotec.lang.NResult;
import com.neurotec.event.NPluginEvent;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import javax.swing.event.EventListenerList;
import com.neurotec.util.concurrent.NSyncChangeObject;
import com.neurotec.lang.NObject;

public final class NPluginManager extends NObject
{
    static final NPluginManagerLibrary LIBRARY;
    private boolean pluginsSyncSetup;
    private NObjectLibrary.NObjectCallback pluginsChangingCallback;
    private NObjectLibrary.NObjectCallback pluginsChangedCallback;
    private NPluginManagerLibrary.NPluginManagerPluginCallback pluginAddedCallback;
    private PluginCollection plugins;
    private NSyncChangeObject pluginsChangeObject;
    private boolean disabledPluginsSyncSetup;
    private NObjectLibrary.NObjectCallback disabledPluginsChangingCallback;
    private NObjectLibrary.NObjectCallback disabledPluginsChangedCallback;
    private DisabledPluginCollection disabledPlugins;
    private NSyncChangeObject disabledPluginsChangeObject;
    private EventListenerList listeners;
    public static final NNativeType NATIVE_TYPE;
    
    private NPluginManager(final HNObject handle) {
        super(handle, NPluginManager.NATIVE_TYPE, false);
        this.pluginsSyncSetup = false;
        this.disabledPluginsSyncSetup = false;
        this.listeners = new EventListenerList();
        this.pluginsChangingCallback = new NObjectLibrary.NObjectCallback() {
            public void invoke(final HNObject hDeviceManager, final Pointer pParam) {
                if (NPluginManager.this.pluginsSyncSetup) {
                    NPluginManager.this.pluginsChangeObject.changing();
                }
            }
        };
        this.pluginsChangedCallback = new NObjectLibrary.NObjectCallback() {
            public void invoke(final HNObject hDeviceManager, final Pointer pParam) {
                if (NPluginManager.this.pluginsSyncSetup) {
                    NPluginManager.this.pluginsChangeObject.changed();
                }
            }
        };
        this.pluginAddedCallback = new NPluginManagerLibrary.NPluginManagerPluginCallback() {
            public void invoke(final HNObject hPluginManager, final HNObject hPlugin, final Pointer pParam) {
                final NPlugin plugin = NPluginManager.this.plugins.onAdded(hPlugin);
                NPluginManager.this.pluginAdded(new NPluginEvent(this, plugin));
            }
        };
        this.plugins = new PluginCollection(this);
        this.pluginsChangeObject = new NSyncChangeObject(this, this.plugins);
        NResult.check(NPluginManager.LIBRARY.NPluginManagerAddPluginsChangingCallback(this.getHandle(), this.pluginsChangingCallback, Pointer.NULL));
        NResult.check(NPluginManager.LIBRARY.NPluginManagerAddPluginsChangedCallback(this.getHandle(), this.pluginsChangedCallback, Pointer.NULL));
        synchronized (this.plugins) {
            this.pluginsSyncSetup = true;
            NResult.check(NPluginManager.LIBRARY.NPluginManagerAddPluginAddedCallback(handle, this.pluginAddedCallback, Pointer.NULL));
            this.plugins.onRefresh();
        }
        this.disabledPluginsChangingCallback = new NObjectLibrary.NObjectCallback() {
            public void invoke(final HNObject hDeviceManager, final Pointer pParam) {
                if (NPluginManager.this.disabledPluginsSyncSetup) {
                    NPluginManager.this.disabledPluginsChangeObject.changing();
                }
            }
        };
        this.disabledPluginsChangedCallback = new NObjectLibrary.NObjectCallback() {
            public void invoke(final HNObject hDeviceManager, final Pointer pParam) {
                if (NPluginManager.this.disabledPluginsSyncSetup) {
                    NPluginManager.this.disabledPluginsChangeObject.changed();
                }
            }
        };
        this.disabledPlugins = new DisabledPluginCollection(this);
        this.disabledPluginsChangeObject = new NSyncChangeObject(this, this.disabledPlugins);
        NResult.check(NPluginManager.LIBRARY.NPluginManagerAddDisabledPluginsChangingCallback(this.getHandle(), this.disabledPluginsChangingCallback, Pointer.NULL));
        NResult.check(NPluginManager.LIBRARY.NPluginManagerAddDisabledPluginsChangedCallback(this.getHandle(), this.disabledPluginsChangedCallback, Pointer.NULL));
        synchronized (this.disabledPlugins) {
            this.disabledPluginsSyncSetup = true;
        }
    }
    
    public static NPluginManager fromHandle(final HNObject handle) {
        return new NPluginManager(handle);
    }
    
    protected void dispose(final boolean disposing) {
        if (!HNObject.NULL.equals(this.getHandle())) {
            try {
                NResult.check(NPluginManager.LIBRARY.NPluginManagerRemoveDisabledPluginsChangedCallback(this.getHandle(), this.disabledPluginsChangedCallback, Pointer.NULL));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            try {
                NResult.check(NPluginManager.LIBRARY.NPluginManagerRemoveDisabledPluginsChangingCallback(this.getHandle(), this.disabledPluginsChangingCallback, Pointer.NULL));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            try {
                NResult.check(NPluginManager.LIBRARY.NPluginManagerRemovePluginAddedCallback(this.getHandle(), this.pluginAddedCallback, Pointer.NULL));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            try {
                NResult.check(NPluginManager.LIBRARY.NPluginManagerRemovePluginsChangedCallback(this.getHandle(), this.pluginsChangedCallback, Pointer.NULL));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            try {
                NResult.check(NPluginManager.LIBRARY.NPluginManagerRemovePluginsChangingCallback(this.getHandle(), this.pluginsChangingCallback, Pointer.NULL));
            }
            catch (Exception ex) {
                NResult.suppressError(ex);
            }
            if (disposing) {
                this.plugins.disposeItems();
            }
            this.plugins = null;
            this.disabledPlugins = null;
            super.dispose(disposing);
        }
    }
    
    public void refresh() {
        NResult.check(NPluginManager.LIBRARY.NPluginManagerRefresh(this.getHandle()));
    }
    
    public void plugAll() {
        NResult.check(NPluginManager.LIBRARY.NPluginManagerPlugAll(this.getHandle()));
    }
    
    public void unplugAll() {
        NResult.check(NPluginManager.LIBRARY.NPluginManagerUnplugAll(this.getHandle()));
    }
    
    public NVersionRange[] getInterfaceVersions() {
        final int count = NResult.check(NPluginManager.LIBRARY.NPluginManagerGetInterfaceVersions(this.getHandle(), null, 0));
        final NVersionRange[] values = new NVersionRange[count];
        final int[] rValues = new int[count];
        NResult.check(NPluginManager.LIBRARY.NPluginManagerGetInterfaceVersions(this.getHandle(), rValues, count));
        for (int i = 0; i < values.length; ++i) {
            values[i] = new NVersionRange(rValues[i]);
        }
        return values;
    }
    
    public synchronized String getPluginSearchPath() {
        final int pLen = NPluginManager.LIBRARY.NPluginManagerGetPluginSearchPath(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(NPluginManager.LIBRARY.NPluginManagerGetPluginSearchPath(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public synchronized void setPluginSearchPath(final String value) {
        NResult.check(NPluginManager.LIBRARY.NPluginManagerSetPluginSearchPath(this.getHandle(), new WString(value)));
    }
    
    public String getInterfaceType() {
        final int pLen = NPluginManager.LIBRARY.NPluginManagerGetInterfaceType(this.getHandle(), null, 0);
        if (NResult.isFailed(pLen)) {
            return null;
        }
        final Pointer pValue = NCore.allocString(pLen);
        try {
            if (NResult.isFailed(NPluginManager.LIBRARY.NPluginManagerGetInterfaceType(this.getHandle(), pValue, pLen + 1))) {
                return null;
            }
            return pValue.getString(0L, true);
        }
        finally {
            NCore.free(pValue);
        }
    }
    
    public boolean isAllowsUnplug() {
        final BooleanByReference rValue = new BooleanByReference();
        NPluginManager.LIBRARY.NPluginManagerAllowsUnplug(this.getHandle(), rValue);
        return rValue.getValue();
    }
    
    public PluginCollection getPlugins() {
        this.check();
        return this.plugins;
    }
    
    public DisabledPluginCollection getDisabledPlugins() {
        this.check();
        return this.disabledPlugins;
    }
    
    public synchronized void addPluginManagerListener(final NPluginsListener listener) {
        this.listeners.add(NPluginsListener.class, listener);
        this.pluginsChangeObject.addListener(listener);
        this.disabledPluginsChangeObject.addListener(listener);
    }
    
    public synchronized void removePluginManagerListener(final NPluginsListener listener) {
        this.listeners.remove(NPluginsListener.class, listener);
        this.pluginsChangeObject.removeListener(listener);
        this.disabledPluginsChangeObject.removeListener(listener);
    }
    
    private synchronized void pluginAdded(final NPluginEvent event) {
        final Object[] listeners = this.listeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == NPluginsListener.class) {
                ((NPluginsListener)listeners[i + 1]).pluginAdded(event);
            }
        }
    }
    
    static {
        LIBRARY = (NPluginManagerLibrary)Native.loadLibrary("NCore", NPluginManagerLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NPluginManager.LIBRARY.NPluginManagerTypeOf());
    }
    
    public static final class PluginCollection extends NObjectReadOnlyCollection<NPlugin>
    {
        protected PluginCollection(final NPluginManager owner) {
            super(owner, false);
        }
        
        protected NPlugin fromHandleNative(final HNObject handle) {
            return new NPlugin(handle);
        }
        
        protected HNObject getNative(final int index) {
            final HNObjectByReference rValue = new HNObjectByReference();
            NResult.check(NPluginManager.LIBRARY.NPluginManagerGetPlugin(this.getOwner().getHandle(), index, rValue));
            return rValue.getValue();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NPluginManager.LIBRARY.NPluginManagerGetPluginCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
        
        protected void disposeItems() {
            super.disposeItems();
        }
        
        NPlugin onAdded(final HNObject hPlugin) {
            return this.addItem(hPlugin);
        }
        
        void onRefresh() {
            this.refreshItems();
        }
        
        public NPlugin add(final String fileName) {
            final HNObjectByReference rhPlugin = new HNObjectByReference();
            NResult.check(NPluginManager.LIBRARY.NPluginManagerAddPluginFromFile(this.getOwner().getHandle(), new WString(fileName), rhPlugin));
            return this.get(rhPlugin.getValue());
        }
    }
    
    public final class DisabledPluginCollection extends NSimpleCollection<String>
    {
        protected DisabledPluginCollection(final NPluginManager owner) {
            super(owner, false, true, true);
        }
        
        protected void addNative(final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected void addNative(final int index, final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected int addWithIndexNative(final String value) {
            final IntByReference rIndex = new IntByReference();
            NResult.check(NPluginManager.LIBRARY.NPluginManagerAddDisabledPlugin(this.getOwner().getHandle(), new WString(value), rIndex));
            return rIndex.getValue();
        }
        
        protected void clearNative() {
            NResult.check(NPluginManager.LIBRARY.NPluginManagerClearDisabledPlugins(this.getOwner().getHandle()));
        }
        
        protected String[] getAllNative() {
            throw new UnsupportedOperationException();
        }
        
        protected int getCapacityNative() {
            throw new UnsupportedOperationException();
        }
        
        protected String getNative(final int index) {
            final int pLen = NPluginManager.LIBRARY.NPluginManagerGetDisabledPlugin(this.getOwner().getHandle(), index, null, 0);
            if (NResult.isFailed(pLen)) {
                return null;
            }
            final Pointer pValue = NCore.allocString(pLen);
            try {
                if (NResult.isFailed(NPluginManager.LIBRARY.NPluginManagerGetDisabledPlugin(this.getOwner().getHandle(), index, pValue, pLen + 1))) {
                    return null;
                }
                return pValue.getString(0L, true);
            }
            finally {
                NCore.free(pValue);
            }
        }
        
        protected int removeNative(final String value) {
            final IntByReference rIndex = new IntByReference();
            NResult.check(NPluginManager.LIBRARY.NPluginManagerRemoveDisabledPlugin(this.getOwner().getHandle(), new WString(value), rIndex));
            return rIndex.getValue();
        }
        
        protected void removeNative(final int index) {
            NResult.check(NPluginManager.LIBRARY.NPluginManagerRemoveDisabledPluginAt(this.getOwner().getHandle(), index));
        }
        
        protected void removeRangeNative(final int index, final int count) {
            throw new UnsupportedOperationException();
        }
        
        protected void setCapacityNative(final int value) {
            throw new UnsupportedOperationException();
        }
        
        protected void setNative(final int index, final String value) {
            throw new UnsupportedOperationException();
        }
        
        protected int sizeNative() {
            final IntByReference rValue = new IntByReference();
            NResult.check(NPluginManager.LIBRARY.NPluginManagerGetDisabledPluginCount(this.getOwner().getHandle(), rValue));
            return rValue.getValue();
        }
    }
    
    interface NPluginManagerLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NPluginManagerTypeOf();
        
        int NPluginManagerRefresh(final HNObject p0);
        
        int NPluginManagerPlugAll(final HNObject p0);
        
        int NPluginManagerUnplugAll(final HNObject p0);
        
        int NPluginManagerGetPluginSearchPath(final HNObject p0, final Pointer p1, final int p2);
        
        int NPluginManagerSetPluginSearchPath(final HNObject p0, final WString p1);
        
        int NPluginManagerGetInterfaceType(final HNObject p0, final Pointer p1, final int p2);
        
        int NPluginManagerGetInterfaceVersions(final HNObject p0, final int[] p1, final int p2);
        
        int NPluginManagerAllowsUnplug(final HNObject p0, final BooleanByReference p1);
        
        int NPluginManagerAddPluginsChangingCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginManagerRemovePluginsChangingCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginManagerAddPluginsChangedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginManagerRemovePluginsChangedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginManagerAddPluginAddedCallback(final HNObject p0, final NPluginManagerPluginCallback p1, final Pointer p2);
        
        int NPluginManagerRemovePluginAddedCallback(final HNObject p0, final NPluginManagerPluginCallback p1, final Pointer p2);
        
        int NPluginManagerAddDisabledPluginsChangingCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginManagerRemoveDisabledPluginsChangingCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginManagerAddDisabledPluginsChangedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginManagerRemoveDisabledPluginsChangedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NPluginManagerGetPluginCount(final HNObject p0, final IntByReference p1);
        
        int NPluginManagerGetPlugin(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NPluginManagerAddPluginFromFile(final HNObject p0, final WString p1, final HNObjectByReference p2);
        
        int NPluginManagerGetDisabledPluginCount(final HNObject p0, final IntByReference p1);
        
        int NPluginManagerGetDisabledPlugin(final HNObject p0, final int p1, final Pointer p2, final int p3);
        
        int NPluginManagerAddDisabledPlugin(final HNObject p0, final WString p1, final IntByReference p2);
        
        int NPluginManagerRemoveDisabledPlugin(final HNObject p0, final WString p1, final IntByReference p2);
        
        int NPluginManagerRemoveDisabledPluginAt(final HNObject p0, final int p1);
        
        int NPluginManagerClearDisabledPlugins(final HNObject p0);
        
        public interface NPluginManagerPluginCallback extends StdCallLibrary.StdCallCallback
        {
            void invoke(final HNObject p0, final HNObject p1, final Pointer p2);
        }
    }
}
