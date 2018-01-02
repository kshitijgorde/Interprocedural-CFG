// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.SWT;

public abstract class Resource
{
    Device device;
    
    public Resource() {
    }
    
    Resource(Device device) {
        if (device == null) {
            device = Device.getDevice();
        }
        if (device == null) {
            SWT.error(4);
        }
        this.device = device;
    }
    
    void destroy() {
    }
    
    public void dispose() {
        if (this.device == null) {
            return;
        }
        if (this.device.isDisposed()) {
            return;
        }
        this.destroy();
        if (this.device.tracking) {
            this.device.dispose_Object(this);
        }
        this.device = null;
    }
    
    public Device getDevice() {
        final Device device = this.device;
        if (device == null || this.isDisposed()) {
            SWT.error(44);
        }
        return device;
    }
    
    void init() {
        if (this.device.tracking) {
            this.device.new_Object(this);
        }
    }
    
    public abstract boolean isDisposed();
}
