// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices.event;

import java.util.Collections;
import java.util.Arrays;
import com.neurotec.biometrics.NFAttributes;
import java.util.List;
import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.images.NImage;
import java.util.EventObject;

public final class NFScannerPreviewEvent extends EventObject
{
    private NImage image;
    private NBiometricStatus status;
    private List<NFAttributes> objects;
    
    public NFScannerPreviewEvent(final Object source, final NImage image, final NBiometricStatus status, final NFAttributes[] objects) {
        this(source, image, status, Collections.unmodifiableList((List<? extends NFAttributes>)Arrays.asList((T[])objects)));
    }
    
    public NFScannerPreviewEvent(final Object source, final NImage image, final NBiometricStatus status, final List<NFAttributes> objects) {
        super(source);
        this.image = image;
        this.status = status;
        this.objects = objects;
    }
    
    public NImage getImage() {
        return this.image;
    }
    
    public NBiometricStatus getStatus() {
        return this.status;
    }
    
    public void setStatus(final NBiometricStatus status) {
        this.status = status;
    }
    
    public List<NFAttributes> getObjects() {
        return this.objects;
    }
}
