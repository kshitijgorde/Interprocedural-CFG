// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.neurotec.jna.ptr.BooleanByReference;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.images.NImage;
import com.neurotec.lang.NResult;
import com.sun.jna.Pointer;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import javax.swing.event.EventListenerList;
import com.neurotec.lang.NObject;

public class NCamera extends NDevice
{
    private final NObjectLibrary.NObjectCallback isCapturingChangedCallback;
    private EventListenerList listeners;
    static final NBiometricDeviceLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    NCamera(final HNObject handle) {
        super(handle, NCamera.NATIVE_TYPE);
        this.listeners = new EventListenerList();
        this.isCapturingChangedCallback = new NObjectLibrary.NObjectCallback() {
            public void invoke(final HNObject hObject, final Pointer pParam) {
                NCamera.this.capturingChanged();
            }
        };
    }
    
    public final void startCapturing() {
        NResult.check(NCamera.LIBRARY.NCameraStartCapturing(this.getHandle()));
    }
    
    public final NImage getFrame() {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NCamera.LIBRARY.NCameraGetFrame(this.getHandle(), rhValue));
        try {
            hValue = rhValue.getValue();
            value = (HNObject.NULL.equals(hValue) ? null : NImage.fromHandle(hValue));
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public final void stopCapturing() {
        NResult.check(NCamera.LIBRARY.NCameraStopCapturing(this.getHandle()));
    }
    
    public final boolean isCapturing() {
        final BooleanByReference rValue = new BooleanByReference();
        NResult.check(NCamera.LIBRARY.NCameraIsCapturing(this.getHandle(), rValue));
        return rValue.getValue();
    }
    
    public final synchronized void addChangeListener(final ChangeListener listener) {
        if (this.listeners.getListenerCount() == 0) {
            NCamera.LIBRARY.NCameraAddIsCapturingChangedCallback(this.getHandle(), this.isCapturingChangedCallback, Pointer.NULL);
        }
        this.listeners.add(ChangeListener.class, listener);
    }
    
    public final synchronized void removeChangeListener(final ChangeListener listener) {
        this.listeners.remove(ChangeListener.class, listener);
        if (this.listeners.getListenerCount() == 0) {
            NCamera.LIBRARY.NCameraRemoveIsCapturingChangedCallback(this.getHandle(), this.isCapturingChangedCallback, Pointer.NULL);
        }
    }
    
    private synchronized void capturingChanged() {
        final Object[] listeners = this.listeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                ((ChangeListener)listeners[i + 1]).stateChanged(new ChangeEvent(this));
            }
        }
    }
    
    static {
        LIBRARY = (NBiometricDeviceLibrary)Native.loadLibrary("NDevices", NBiometricDeviceLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NCamera.LIBRARY.NCameraTypeOf());
    }
    
    interface NBiometricDeviceLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NCameraTypeOf();
        
        int NCameraStartCapturing(final HNObject p0);
        
        int NCameraGetFrame(final HNObject p0, final HNObjectByReference p1);
        
        int NCameraStopCapturing(final HNObject p0);
        
        int NCameraIsCapturing(final HNObject p0, final BooleanByReference p1);
        
        int NCameraAddIsCapturingChangedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
        
        int NCameraRemoveIsCapturingChangedCallback(final HNObject p0, final NObjectLibrary.NObjectCallback p1, final Pointer p2);
    }
}
