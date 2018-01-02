// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import java.nio.Buffer;
import com.neurotec.jna.ptr.NativeSize;
import com.neurotec.jna.ptr.NativeSizeByReference;
import java.nio.ByteBuffer;
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;
import com.neurotec.lang.NNativeType;
import com.neurotec.lang.NObject;

public class NTemplate extends NObject implements Cloneable
{
    private NFTemplate fingers;
    private NLTemplate faces;
    private NETemplate irises;
    private NFTemplate palms;
    static final NTemplateLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    
    private NTemplate(final HNObject handle, final boolean claimHandle) {
        super(handle, NTemplate.NATIVE_TYPE, claimHandle);
        final HNObjectByReference rhFingers = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateGetFingers(handle, rhFingers));
        this.setFingers(rhFingers.getValue());
        final HNObjectByReference rhFaces = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateGetFaces(handle, rhFaces));
        this.setFaces(rhFaces.getValue());
        final HNObjectByReference rhIrises = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateGetIrises(handle, rhIrises));
        this.setIrises(rhIrises.getValue());
        final HNObjectByReference rhPalms = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateGetPalms(handle, rhPalms));
        this.setPalms(rhPalms.getValue());
    }
    
    private static HNObject create(final int flags) {
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateCreateEx(flags, rhValue));
        return rhValue.getValue();
    }
    
    private static HNObject create(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateCreateFromMemory(buffer, new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        buffer.position(buffer.position() + rSize.getValue().intValue());
        return rhValue.getValue();
    }
    
    public static int calculateSize(final int fingersTemplateSize, final int facesTemplateSize, final int irisesTemplateSize, final int palmsTemplateSize) {
        if (fingersTemplateSize < 0) {
            throw new IllegalArgumentException("fingersTemplateSize is less than zero");
        }
        if (facesTemplateSize < 0) {
            throw new IllegalArgumentException("facesTemplateSize is less than zero");
        }
        if (irisesTemplateSize < 0) {
            throw new IllegalArgumentException("irisesTemplateSize is less than zero");
        }
        if (palmsTemplateSize < 0) {
            throw new IllegalArgumentException("palmsTemplateSize is less than zero");
        }
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateCalculateSize(new NativeSize((long)fingersTemplateSize), new NativeSize((long)facesTemplateSize), new NativeSize((long)irisesTemplateSize), new NativeSize((long)palmsTemplateSize), rValue));
        return rValue.getValue().intValue();
    }
    
    public static void check(final ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NResult.check(NTemplate.LIBRARY.NTemplateCheck(buffer.slice(), new NativeSize((long)buffer.remaining())));
    }
    
    public static NTemplate fromHandle(final HNObject handle) {
        return fromHandle(handle, true);
    }
    
    public static NTemplate fromHandle(final HNObject handle, final boolean ownsHandle) {
        final NTemplate value = new NTemplate(handle, false);
        if (ownsHandle) {
            value.claimHandle();
        }
        return value;
    }
    
    private NFTemplate setFingers(final HNObject hFingers) {
        this.fingers = ((hFingers == null || hFingers.getPointer() == null) ? null : NFTemplate.fromHandle(hFingers, false));
        if (this.fingers != null) {
            this.embedObject(this.fingers);
        }
        return this.fingers;
    }
    
    private void removeFingersInternal() {
        this.releaseObject(this.fingers);
        this.fingers = null;
    }
    
    private NLTemplate setFaces(final HNObject hFaces) {
        this.faces = ((hFaces == null || hFaces.getPointer() == null) ? null : NLTemplate.fromHandle(hFaces, false));
        if (this.faces != null) {
            this.embedObject(this.faces);
        }
        return this.faces;
    }
    
    private void removeFacesInternal() {
        this.releaseObject(this.faces);
        this.faces = null;
    }
    
    private NETemplate setIrises(final HNObject hIrises) {
        this.irises = ((hIrises == null || hIrises.getPointer() == null) ? null : NETemplate.fromHandle(hIrises, false));
        if (this.irises != null) {
            this.embedObject(this.irises);
        }
        return this.irises;
    }
    
    private void removeIrisesInternal() {
        this.releaseObject(this.irises);
        this.irises = null;
    }
    
    private NFTemplate setPalms(final HNObject hPalms) {
        this.palms = ((hPalms == null || hPalms.getPointer() == null) ? null : NFTemplate.fromHandle(hPalms, false));
        if (this.palms != null) {
            this.embedObject(this.palms);
        }
        return this.palms;
    }
    
    private void removePalmsInternal() {
        this.releaseObject(this.palms);
        this.palms = null;
    }
    
    private void clearInternal() {
        this.removeFingersInternal();
        this.removeFacesInternal();
        this.removeIrisesInternal();
        this.removePalmsInternal();
    }
    
    public NTemplate() {
        this(0);
    }
    
    public NTemplate(final int flags) {
        this(create(flags), true);
    }
    
    public NTemplate(final ByteBuffer buffer) {
        this(create(buffer, 0), true);
    }
    
    public NTemplate(final ByteBuffer buffer, final int flags) {
        this(create(buffer, flags), true);
    }
    
    public final NFTemplate addFingers() {
        return this.addFingers(0);
    }
    
    public final NFTemplate addFingers(final int flags) {
        NFTemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddFingersEx(this.getHandle(), flags, rhValue));
        try {
            value = this.setFingers(rhValue.getValue());
        }
        finally {
            if (value == null) {
                this.removeFingers();
            }
        }
        return value;
    }
    
    public final NFTemplate addFingers(final ByteBuffer buffer) {
        return this.addFingers(buffer, 0);
    }
    
    public final NFTemplate addFingers(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NFTemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddFingersFromMemoryEx(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        try {
            value = this.setFingers(rhValue.getValue());
        }
        finally {
            if (value == null) {
                this.removeFingers();
            }
            buffer.position(buffer.position() + rSize.getValue().intValue());
        }
        return value;
    }
    
    public final NFTemplate addFingersCopy(final NFTemplate srcFingers) {
        if (srcFingers == null) {
            throw new NullPointerException("srcFingers");
        }
        NFTemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddFingersCopy(this.getHandle(), srcFingers.getHandle(), rhValue));
        try {
            value = this.setFingers(rhValue.getValue());
        }
        finally {
            if (value == null) {
                this.removeFingers();
            }
        }
        return value;
    }
    
    public final void removeFingers() {
        NResult.check(NTemplate.LIBRARY.NTemplateRemoveFingers(this.getHandle()));
        this.removeFingersInternal();
    }
    
    public final NLTemplate addFaces() {
        return this.addFaces(0);
    }
    
    public final NLTemplate addFaces(final int flags) {
        NLTemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddFacesEx(this.getHandle(), flags, rhValue));
        try {
            value = this.setFaces(rhValue.getValue());
        }
        finally {
            if (value == null) {
                this.removeFaces();
            }
        }
        return value;
    }
    
    public final NLTemplate addFaces(final ByteBuffer buffer) {
        return this.addFaces(buffer, 0);
    }
    
    public final NLTemplate addFaces(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NLTemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddFacesFromMemoryEx(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        try {
            value = this.setFaces(rhValue.getValue());
        }
        finally {
            if (value == null) {
                this.removeFaces();
            }
            buffer.position(buffer.position() + rSize.getValue().intValue());
        }
        return value;
    }
    
    public final NLTemplate addFacesCopy(final NLTemplate srcFaces) {
        if (srcFaces == null) {
            throw new NullPointerException("srcFaces");
        }
        NLTemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddFacesCopy(this.getHandle(), srcFaces.getHandle(), rhValue));
        try {
            value = this.setFaces(rhValue.getValue());
        }
        finally {
            if (value == null) {
                this.removeFaces();
            }
        }
        return value;
    }
    
    public final void removeFaces() {
        NResult.check(NTemplate.LIBRARY.NTemplateRemoveFaces(this.getHandle()));
        this.removeFacesInternal();
    }
    
    public final NETemplate addIrises() {
        return this.addIrises(0);
    }
    
    public final NETemplate addIrises(final int flags) {
        NETemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddIrisesEx(this.getHandle(), flags, rhValue));
        try {
            value = this.setIrises(rhValue.getValue());
        }
        finally {
            if (value == null) {
                this.removeIrises();
            }
        }
        return value;
    }
    
    public final NETemplate addIrises(final ByteBuffer buffer) {
        return this.addIrises(buffer, 0);
    }
    
    public final NETemplate addIrises(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NETemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddIrisesFromMemoryEx(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        try {
            value = this.setIrises(rhValue.getValue());
        }
        finally {
            if (value == null) {
                this.removeIrises();
            }
            buffer.position(buffer.position() + rSize.getValue().intValue());
        }
        return value;
    }
    
    public final NETemplate addIrisesCopy(final NETemplate srcIrises) {
        if (srcIrises == null) {
            throw new NullPointerException("srcIrises");
        }
        NETemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddIrisesCopy(this.getHandle(), srcIrises.getHandle(), rhValue));
        try {
            value = this.setIrises(rhValue.getValue());
        }
        finally {
            if (value == null) {
                this.removeIrises();
            }
        }
        return value;
    }
    
    public final void removeIrises() {
        NResult.check(NTemplate.LIBRARY.NTemplateRemoveIrises(this.getHandle()));
        this.removeIrisesInternal();
    }
    
    public final NFTemplate addPalms() {
        return this.addPalms(0);
    }
    
    public final NFTemplate addPalms(final int flags) {
        NFTemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddPalmsEx(this.getHandle(), flags, rhValue));
        try {
            value = this.setPalms(rhValue.getValue());
        }
        finally {
            if (value == null) {
                NTemplate.LIBRARY.NTemplateRemovePalms(this.getHandle());
                this.removePalmsInternal();
            }
        }
        return value;
    }
    
    public final NFTemplate addPalms(final ByteBuffer buffer) {
        return this.addFingers(buffer, 0);
    }
    
    public final NFTemplate addPalms(final ByteBuffer buffer, final int flags) {
        if (buffer == null) {
            throw new NullPointerException("buffer");
        }
        NFTemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddPalmsFromMemoryEx(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize, rhValue));
        try {
            value = this.setFingers(rhValue.getValue());
        }
        finally {
            if (value == null) {
                this.removePalms();
            }
            buffer.position(buffer.position() + rSize.getValue().intValue());
        }
        return value;
    }
    
    public final NFTemplate addPalmsCopy(final NFTemplate srcPalms) {
        if (srcPalms == null) {
            throw new NullPointerException("srcPalms");
        }
        NFTemplate value = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateAddPalmsCopy(this.getHandle(), srcPalms.getHandle(), rhValue));
        try {
            value = this.setPalms(rhValue.getValue());
        }
        finally {
            if (value == null) {
                NTemplate.LIBRARY.NTemplateRemovePalms(this.getHandle());
                this.removePalmsInternal();
            }
        }
        return value;
    }
    
    public final void removePalms() {
        NResult.check(NTemplate.LIBRARY.NTemplateRemovePalms(this.getHandle()));
        this.removePalmsInternal();
    }
    
    public final void clear() {
        NResult.check(NTemplate.LIBRARY.NTemplateClear(this.getHandle()));
        this.clearInternal();
    }
    
    public final int getSize() {
        return this.getSize(0);
    }
    
    public final int getSize(final int flags) {
        final NativeSizeByReference rValue = new NativeSizeByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateGetSize(this.getHandle(), flags, rValue));
        return rValue.getValue().intValue();
    }
    
    public final ByteBuffer save() {
        return this.save(0);
    }
    
    public final ByteBuffer save(final int flags) {
        final int size = this.getSize(flags);
        final ByteBuffer buffer = ByteBuffer.allocateDirect(size);
        if (this.save(buffer, flags) != size) {
            throw new RuntimeException("Packed and calculated size of NTemplate do not match");
        }
        buffer.flip();
        return buffer;
    }
    
    public final int save(final ByteBuffer buffer, final int flags) {
        final NativeSizeByReference rSize = new NativeSizeByReference();
        NResult.check(NTemplate.LIBRARY.NTemplateSaveToMemory(this.getHandle(), buffer.slice(), new NativeSize((long)buffer.remaining()), flags, rSize));
        final int size = rSize.getValue().intValue();
        buffer.position(buffer.position() + size);
        return size;
    }
    
    public final NFTemplate getFingers() {
        this.check();
        return this.fingers;
    }
    
    public final NLTemplate getFaces() {
        this.check();
        return this.faces;
    }
    
    public final NETemplate getIrises() {
        this.check();
        return this.irises;
    }
    
    public final NFTemplate getPalms() {
        this.check();
        return this.palms;
    }
    
    protected final void dispose(final boolean disposing) {
        if (disposing) {
            this.clearInternal();
        }
        this.fingers = null;
        this.faces = null;
        this.irises = null;
        this.palms = null;
        super.dispose(disposing);
    }
    
    public final Object clone() throws CloneNotSupportedException {
        NTemplate value = null;
        HNObject hValue = null;
        final HNObjectByReference rhValue = new HNObjectByReference();
        try {
            NResult.check(NTemplate.LIBRARY.NTemplateClone(this.getHandle(), rhValue));
            hValue = rhValue.getValue();
            value = fromHandle(hValue);
        }
        finally {
            if (value == null) {
                NObject.free(hValue);
            }
        }
        return value;
    }
    
    static {
        LIBRARY = (NTemplateLibrary)Native.loadLibrary("NBiometrics", NTemplateLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NTemplate.LIBRARY.NTemplateTypeOf());
    }
    
    interface NTemplateLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NTemplateTypeOf();
        
        int NTemplateCalculateSize(final NativeSize p0, final NativeSize p1, final NativeSize p2, final NativeSize p3, final NativeSizeByReference p4);
        
        int NTemplateCheck(final Buffer p0, final NativeSize p1);
        
        int NTemplateCreateEx(final int p0, final HNObjectByReference p1);
        
        int NTemplateCreateFromMemory(final Buffer p0, final NativeSize p1, final int p2, final NativeSizeByReference p3, final HNObjectByReference p4);
        
        int NTemplateGetFingers(final HNObject p0, final HNObjectByReference p1);
        
        int NTemplateAddFingers(final HNObject p0, final HNObjectByReference p1);
        
        int NTemplateAddFingersEx(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NTemplateAddFingersFromMemoryEx(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int NTemplateAddFingersCopy(final HNObject p0, final HNObject p1, final HNObjectByReference p2);
        
        int NTemplateRemoveFingers(final HNObject p0);
        
        int NTemplateGetFaces(final HNObject p0, final HNObjectByReference p1);
        
        int NTemplateAddFaces(final HNObject p0, final HNObjectByReference p1);
        
        int NTemplateAddFacesEx(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NTemplateAddFacesFromMemoryEx(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int NTemplateAddFacesCopy(final HNObject p0, final HNObject p1, final HNObjectByReference p2);
        
        int NTemplateRemoveFaces(final HNObject p0);
        
        int NTemplateGetIrises(final HNObject p0, final HNObjectByReference p1);
        
        int NTemplateAddIrises(final HNObject p0, final HNObjectByReference p1);
        
        int NTemplateAddIrisesEx(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NTemplateAddIrisesFromMemoryEx(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int NTemplateAddIrisesCopy(final HNObject p0, final HNObject p1, final HNObjectByReference p2);
        
        int NTemplateRemoveIrises(final HNObject p0);
        
        int NTemplateGetPalms(final HNObject p0, final HNObjectByReference p1);
        
        int NTemplateAddPalms(final HNObject p0, final HNObjectByReference p1);
        
        int NTemplateAddPalmsEx(final HNObject p0, final int p1, final HNObjectByReference p2);
        
        int NTemplateAddPalmsFromMemoryEx(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4, final HNObjectByReference p5);
        
        int NTemplateAddPalmsCopy(final HNObject p0, final HNObject p1, final HNObjectByReference p2);
        
        int NTemplateRemovePalms(final HNObject p0);
        
        int NTemplateClear(final HNObject p0);
        
        int NTemplateClone(final HNObject p0, final HNObjectByReference p1);
        
        int NTemplateGetSize(final HNObject p0, final int p1, final NativeSizeByReference p2);
        
        int NTemplateSaveToMemory(final HNObject p0, final Buffer p1, final NativeSize p2, final int p3, final NativeSizeByReference p4);
    }
}
