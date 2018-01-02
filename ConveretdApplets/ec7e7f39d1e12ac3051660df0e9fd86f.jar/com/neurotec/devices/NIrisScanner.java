// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices;

import com.sun.jna.win32.StdCallLibrary;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.devices.event.NIrisScannerListener;
import com.neurotec.lang.NCore;
import com.sun.jna.ptr.PointerByReference;
import com.neurotec.lang.NObject;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.lang.NResult;
import com.neurotec.biometrics.NEPosition;
import com.neurotec.devices.event.NIrisScannerPreviewEvent;
import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NEAttributes;
import com.neurotec.images.NImage;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import javax.swing.event.EventListenerList;

public class NIrisScanner extends NBiometricDevice
{
    private NIrisScannerLibrary.NIrisScannerPreviewCallback previewCallback;
    private EventListenerList listenerList;
    static final NIrisScannerLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    NIrisScanner(final HNObject handle) {
        this(handle, NIrisScanner.NATIVE_TYPE);
    }
    
    NIrisScanner(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType);
        this.listenerList = new EventListenerList();
        this.previewCallback = new NIrisScannerLibrary.NIrisScannerPreviewCallback() {
            public void invoke(final HNObject hDevice, final HNObject hImage, final IntByReference rStatus, final Pointer arHObjects, final int objectCount, final Pointer pParam) {
                NIrisScanner.this.onPreview(hDevice, hImage, rStatus, arHObjects, objectCount, pParam);
            }
        };
    }
    
    private void onPreview(final HNObject hDevice, final HNObject hImage, final IntByReference rStatus, final Pointer arHObjects, final int objectCount, final Pointer pParam) {
        NImage image = null;
        NEAttributes[] objects = null;
        try {
            image = ((hImage == null) ? null : NImage.fromHandle(hImage, false));
            objects = new NEAttributes[objectCount];
            final Pointer[] pHObjects = arHObjects.getPointerArray(0L, objectCount);
            for (int i = 0; i < objectCount; ++i) {
                objects[i] = NEAttributes.fromHandle(new HNObject(pHObjects[i]), false);
            }
            final NIrisScannerPreviewEvent event = new NIrisScannerPreviewEvent(this, image, NBiometricStatus.get(rStatus.getValue()), objects);
            this.previewChanged(event);
        }
        finally {
            if (image != null) {
                image.dispose();
            }
            if (objects != null) {
                for (int j = 0; j < objectCount; ++j) {
                    if (objects[j] != null) {
                        objects[j].dispose();
                    }
                }
            }
        }
    }
    
    public final NEPosition[] getSupportedPositions() {
        final int l = NResult.check(NIrisScanner.LIBRARY.NIrisScannerGetSupportedPositions(this.getHandle(), null, 0));
        final int[] values = new int[l];
        NResult.check(NIrisScanner.LIBRARY.NIrisScannerGetSupportedPositions(this.getHandle(), values, l));
        final NEPosition[] positions = new NEPosition[l];
        for (int i = 0; i < l; ++i) {
            positions[i] = NEPosition.get(values[i]);
        }
        return positions;
    }
    
    public NImage capture(final NEPosition position) {
        return this.capture(position, -1);
    }
    
    public final NImage capture(final NEPosition position, final int timeoutMilliseconds) {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NIrisScanner.LIBRARY.NIrisScannerCapture(this.getHandle(), position.getValue(), timeoutMilliseconds, rhValue));
        try {
            hValue = rhValue.getValue();
            value = NImage.fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    public final NIrisScannerResult capture(final NEPosition position, final NEPosition[] missingPositions, final boolean automatic, final int timeoutMilliseconds, final boolean returnObjects) {
        boolean ok = false;
        NImage image = null;
        HNObject hImage = null;
        final HNObjectByReference rhImage = new HNObjectByReference();
        NEAttributes[] objects = null;
        Pointer pArHObjects = null;
        final PointerByReference rArHObjects = new PointerByReference();
        Pointer[] pHObjects = null;
        final IntByReference rStatus = new IntByReference();
        int objectCount = 0;
        final IntByReference rObjectCount = new IntByReference();
        int[] mp = null;
        if (missingPositions != null) {
            mp = new int[missingPositions.length];
            for (int i = 0; i < missingPositions.length; ++i) {
                mp[i] = missingPositions[i].getValue();
            }
        }
        NResult.check(NIrisScanner.LIBRARY.NIrisScannerCaptureEx(this.getHandle(), position.getValue(), mp, (mp == null) ? 0 : mp.length, automatic, timeoutMilliseconds, rStatus, returnObjects ? rArHObjects : null, returnObjects ? rObjectCount : null, rhImage));
        NIrisScannerResult result;
        try {
            if (returnObjects) {
                pArHObjects = rArHObjects.getValue();
                objectCount = rObjectCount.getValue();
                objects = new NEAttributes[objectCount];
                pHObjects = pArHObjects.getPointerArray(0L, objectCount);
                for (int i = 0; i < objectCount; ++i) {
                    objects[i] = NEAttributes.fromHandle(new HNObject(pHObjects[i]), false);
                }
            }
            hImage = rhImage.getValue();
            image = NImage.fromHandle(hImage);
            result = new NIrisScannerResult(image, objects, NBiometricStatus.get(rStatus.getValue()));
            ok = true;
        }
        finally {
            if (!ok) {
                for (int j = 0; j < objectCount; ++j) {
                    if (objects != null && objects[j] != null) {
                        objects[j].dispose();
                    }
                    if (pHObjects != null) {
                        NObject.free(new HNObject(pHObjects[j]));
                    }
                }
                if (image == null) {
                    NObject.free(hImage);
                }
                else {
                    image.dispose();
                }
            }
            NCore.free(pArHObjects);
        }
        return result;
    }
    
    public final synchronized void addScannerListener(final NIrisScannerListener listener) {
        if (this.listenerList.getListenerCount() == 0) {
            NIrisScanner.LIBRARY.NIrisScannerAddPreviewCallback(this.getHandle(), this.previewCallback, Pointer.NULL);
        }
        this.listenerList.add(NIrisScannerListener.class, listener);
    }
    
    public final synchronized void removeScannerListener(final NIrisScannerListener listener) {
        this.listenerList.remove(NIrisScannerListener.class, listener);
        if (this.listenerList.getListenerCount() == 0) {
            NIrisScanner.LIBRARY.NIrisScannerRemovePreviewCallback(this.getHandle(), this.previewCallback, Pointer.NULL);
        }
    }
    
    private void previewChanged(final NIrisScannerPreviewEvent event) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == NIrisScannerListener.class) {
                ((NIrisScannerListener)listeners[i + 1]).preview(event);
            }
        }
    }
    
    static {
        LIBRARY = (NIrisScannerLibrary)Native.loadLibrary("NDevices", NIrisScannerLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NIrisScanner.LIBRARY.NIrisScannerTypeOf());
    }
    
    public final class NIrisScannerResult
    {
        private NImage image;
        private NEAttributes[] objects;
        private NBiometricStatus status;
        
        public NIrisScannerResult(final NImage image, final NEAttributes[] objects, final NBiometricStatus status) {
            this.image = image;
            this.objects = objects;
            this.status = status;
        }
        
        public NImage getImage() {
            return this.image;
        }
        
        public NEAttributes[] getObjects() {
            return this.objects;
        }
        
        public NBiometricStatus getStatus() {
            return this.status;
        }
    }
    
    interface NIrisScannerLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NIrisScannerTypeOf();
        
        int NIrisScannerGetSupportedPositions(final HNObject p0, final int[] p1, final int p2);
        
        int NIrisScannerCapture(final HNObject p0, final int p1, final int p2, final HNObjectByReference p3);
        
        int NIrisScannerCaptureEx(final HNObject p0, final int p1, final int[] p2, final int p3, final boolean p4, final int p5, final IntByReference p6, final PointerByReference p7, final IntByReference p8, final HNObjectByReference p9);
        
        int NIrisScannerAddPreviewCallback(final HNObject p0, final NIrisScannerPreviewCallback p1, final Pointer p2);
        
        int NIrisScannerRemovePreviewCallback(final HNObject p0, final NIrisScannerPreviewCallback p1, final Pointer p2);
        
        public interface NIrisScannerPreviewCallback extends StdCallLibrary.StdCallCallback
        {
            void invoke(final HNObject p0, final HNObject p1, final IntByReference p2, final Pointer p3, final int p4, final Pointer p5);
        }
    }
}
