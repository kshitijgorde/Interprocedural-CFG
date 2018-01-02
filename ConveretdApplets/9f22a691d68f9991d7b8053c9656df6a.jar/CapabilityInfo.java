// 
// Decompiled by Procyon v0.5.30
// 

class CapabilityInfo
{
    protected int code;
    protected String vendorSignature;
    protected String nameSignature;
    protected String description;
    protected boolean enabled;
    
    public CapabilityInfo(final int code, final String vendorSignature, final String nameSignature, final String description) {
        this.code = code;
        this.vendorSignature = vendorSignature;
        this.nameSignature = nameSignature;
        this.description = description;
        this.enabled = false;
    }
    
    public CapabilityInfo(final int code, final byte[] array, final byte[] array2) {
        this.code = code;
        this.vendorSignature = new String(array);
        this.nameSignature = new String(array2);
        this.description = null;
        this.enabled = false;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void enable() {
        this.enabled = true;
    }
    
    public boolean equals(final CapabilityInfo capabilityInfo) {
        return capabilityInfo != null && this.code == capabilityInfo.code && this.vendorSignature.equals(capabilityInfo.vendorSignature) && this.nameSignature.equals(capabilityInfo.nameSignature);
    }
    
    public boolean enableIfEquals(final CapabilityInfo capabilityInfo) {
        if (this.equals(capabilityInfo)) {
            this.enable();
        }
        return this.isEnabled();
    }
}
