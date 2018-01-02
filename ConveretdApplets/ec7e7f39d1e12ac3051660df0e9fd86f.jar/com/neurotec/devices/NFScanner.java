// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices;

import com.sun.jna.win32.StdCallLibrary;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.neurotec.devices.event.NFScannerListener;
import com.neurotec.lang.NCore;
import com.sun.jna.ptr.PointerByReference;
import com.neurotec.lang.NObject;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.biometrics.NFPosition;
import com.neurotec.lang.NResult;
import com.neurotec.biometrics.NFImpressionType;
import com.neurotec.devices.event.NFScannerPreviewEvent;
import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NFAttributes;
import com.neurotec.images.NImage;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import javax.swing.event.EventListenerList;

public class NFScanner extends NBiometricDevice
{
    private NFScannerLibrary.NFScannerPreviewCallback previewCallback;
    private EventListenerList listenerList;
    static final NFScannerLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    NFScanner(final HNObject handle) {
        this(handle, NFScanner.NATIVE_TYPE);
    }
    
    NFScanner(final HNObject handle, final NNativeType requiredType) {
        super(handle, requiredType);
        this.listenerList = new EventListenerList();
        this.previewCallback = new NFScannerLibrary.NFScannerPreviewCallback() {
            public void invoke(final HNObject hDevice, final HNObject hImage, final IntByReference rStatus, final Pointer arHObjects, final int objectCount, final Pointer pParam) {
                NFScanner.this.onPreview(hDevice, hImage, rStatus, arHObjects, objectCount, pParam);
            }
        };
    }
    
    private void onPreview(final HNObject hDevice, final HNObject hImage, final IntByReference rStatus, final Pointer arHObjects, final int objectCount, final Pointer pParam) {
        NImage image = null;
        NFAttributes[] objects = null;
        try {
            image = (HNObject.NULL.equals(hImage) ? null : NImage.fromHandle(hImage, false));
            objects = new NFAttributes[objectCount];
            final Pointer[] pHObjects = arHObjects.getPointerArray(0L, objectCount);
            for (int i = 0; i < objectCount; ++i) {
                objects[i] = NFAttributes.fromHandle(new HNObject(pHObjects[i]), false);
            }
            final NFScannerPreviewEvent event = new NFScannerPreviewEvent(this, image, NBiometricStatus.get(rStatus.getValue()), objects);
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
    
    public final NFImpressionType[] getSupportedImpressionTypes() {
        final int l = NResult.check(NFScanner.LIBRARY.NFScannerGetSupportedImpressionTypes(this.getHandle(), null, 0));
        final int[] values = new int[l];
        NResult.check(NFScanner.LIBRARY.NFScannerGetSupportedImpressionTypes(this.getHandle(), values, l));
        final NFImpressionType[] types = new NFImpressionType[l];
        for (int i = 0; i < l; ++i) {
            types[i] = NFImpressionType.get(values[i]);
        }
        return types;
    }
    
    public final NFPosition[] getSupportedPositions() {
        final int l = NResult.check(NFScanner.LIBRARY.NFScannerGetSupportedPositions(this.getHandle(), null, 0));
        final int[] values = new int[l];
        NResult.check(NFScanner.LIBRARY.NFScannerGetSupportedPositions(this.getHandle(), values, l));
        final NFPosition[] positions = new NFPosition[l];
        for (int i = 0; i < l; ++i) {
            positions[i] = NFPosition.get(values[i]);
        }
        return positions;
    }
    
    public NImage capture() {
        return this.capture(-1);
    }
    
    public final NImage capture(final int timeoutMilliseconds) {
        NImage value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NFScanner.LIBRARY.NFScannerCapture(this.getHandle(), timeoutMilliseconds, rhValue));
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
    
    public final NFScannerResult capture(final NFImpressionType impressionType, final NFPosition position) {
        return this.capture(impressionType, position, -1);
    }
    
    public final NFScannerResult capture(final NFImpressionType impressionType, final NFPosition position, final int timeoutMilliseconds) {
        return this.capture(impressionType, position, null, true, timeoutMilliseconds, true);
    }
    
    public final NFScannerResult capture(final NFImpressionType impressionType, final NFPosition position, final NFPosition[] missingPositions, final boolean automatic, final int timeoutMilliseconds, final boolean returnAttributes) {
        NFScannerResult result = null;
        boolean ok = false;
        NImage image = null;
        HNObject hImage = null;
        final HNObjectByReference rhImage = new HNObjectByReference();
        NFAttributes[] objects = null;
        Pointer pArHObjects = null;
        final PointerByReference rArHAttributes = new PointerByReference();
        Pointer[] pHAttributes = null;
        final IntByReference rStatus = new IntByReference();
        int objectCount = 0;
        final IntByReference rAttributesCount = new IntByReference();
        int[] mp = null;
        if (missingPositions != null) {
            mp = new int[missingPositions.length];
            for (int i = 0; i < missingPositions.length; ++i) {
                mp[i] = missingPositions[i].getValue();
            }
        }
        NResult.check(NFScanner.LIBRARY.NFScannerCaptureEx(this.getHandle(), impressionType.getValue(), position.getValue(), mp, (mp == null) ? 0 : mp.length, automatic, timeoutMilliseconds, rStatus, returnAttributes ? rArHAttributes : null, returnAttributes ? rAttributesCount : null, rhImage));
        try {
            if (returnAttributes) {
                pArHObjects = rArHAttributes.getValue();
                objectCount = rAttributesCount.getValue();
                objects = new NFAttributes[objectCount];
                pHAttributes = pArHObjects.getPointerArray(0L, objectCount);
                for (int i = 0; i < objectCount; ++i) {
                    objects[i] = NFAttributes.fromHandle(new HNObject(pHAttributes[i]), false);
                }
            }
            hImage = rhImage.getValue();
            image = (HNObject.NULL.equals(hImage) ? null : NImage.fromHandle(hImage));
            result = new NFScannerResult(image, objects, NBiometricStatus.get(rStatus.getValue()));
            ok = true;
        }
        finally {
            if (!ok) {
                for (int j = 0; j < objectCount; ++j) {
                    if (objects != null && objects[j] != null) {
                        objects[j].dispose();
                    }
                    if (pHAttributes != null) {
                        NObject.free(new HNObject(pHAttributes[j]));
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
    
    public final synchronized void addScannerListener(final NFScannerListener listener) {
        if (this.listenerList.getListenerCount() == 0) {
            NFScanner.LIBRARY.NFScannerAddPreviewCallback(this.getHandle(), this.previewCallback, Pointer.NULL);
        }
        this.listenerList.add(NFScannerListener.class, listener);
    }
    
    public final synchronized void removeScannerListener(final NFScannerListener listener) {
        this.listenerList.remove(NFScannerListener.class, listener);
        if (this.listenerList.getListenerCount() == 0) {
            NFScanner.LIBRARY.NFScannerRemovePreviewCallback(this.getHandle(), this.previewCallback, Pointer.NULL);
        }
    }
    
    private synchronized void previewChanged(final NFScannerPreviewEvent event) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == NFScannerListener.class) {
                ((NFScannerListener)listeners[i + 1]).preview(event);
            }
        }
    }
    
    static {
        LIBRARY = (NFScannerLibrary)Native.loadLibrary("NDevices", NFScannerLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NFScanner.LIBRARY.NFScannerTypeOf());
    }
    
    public final class NFScannerResult
    {
        private NImage image;
        private NFAttributes[] objects;
        private NBiometricStatus status;
        
        public NFScannerResult(final NImage image, final NFAttributes[] objects, final NBiometricStatus status) {
            this.image = image;
            this.objects = objects;
            this.status = status;
        }
        
        public NImage getImage() {
            return this.image;
        }
        
        public NFAttributes[] getObjects() {
            return this.objects;
        }
        
        public NBiometricStatus getStatus() {
            return this.status;
        }
    }
    
    interface NFScannerLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NFScannerTypeOf();
        
        int NFScannerGetSupportedImpressionTypes(final HNObject p0, final int[] p1, final int p2);
        
        int NFScannerGetSupportedPositions(final HNObject p0, final int[] p1, final int p2);
        
        int NFScannerCapture(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NFScannerCaptureEx(final HNObject p0, final int p1, final int p2, final int[] p3, final int p4, final boolean p5, final int p6, final IntByReference p7, final PointerByReference p8, final IntByReference p9, final HNObjectByReference p10);
        
        int NFScannerAddPreviewCallback(final HNObject p0, final NFScannerPreviewCallback p1, final Pointer p2);
        
        int NFScannerRemovePreviewCallback(final HNObject p0, final NFScannerPreviewCallback p1, final Pointer p2);
        
        public interface NFScannerPreviewCallback extends StdCallLibrary.StdCallCallback
        {
            void invoke(final HNObject p0, final HNObject p1, final IntByReference p2, final Pointer p3, final int p4, final Pointer p5);
        }
    }
}
