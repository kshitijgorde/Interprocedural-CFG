// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

final class ViewModeDetail
{
    private boolean _$6731;
    private boolean _$2924;
    private boolean _$6736;
    private boolean _$6741;
    private boolean _$6748;
    private boolean _$6754;
    private boolean _$162910;
    private boolean _$162921;
    private boolean _$6758;
    private boolean _$162930;
    private boolean _$162937;
    
    public ViewModeDetail() {
        this._$6731 = false;
        this._$2924 = false;
        this._$6736 = false;
        this._$6741 = false;
        this._$6748 = false;
        this._$6754 = false;
        this._$6758 = false;
        this._$162930 = false;
        this._$162937 = false;
        this._$162910 = false;
        this._$162921 = false;
    }
    
    public boolean isFRateActive() {
        return this._$6731;
    }
    
    public boolean isVSizeActive() {
        return this._$2924;
    }
    
    public boolean isDZoomActive() {
        return this._$6736;
    }
    
    public boolean isControlActive() {
        return this._$162930;
    }
    
    public boolean isPresetPositionActive() {
        return this._$162937;
    }
    
    public boolean isPresetPositionIndipendent() {
        return this.isPresetPositionActive() && !this.isControlActive();
    }
    
    public boolean isTriggerActive() {
        return this._$6741;
    }
    
    public boolean isVolumeActive() {
        return this._$6748;
    }
    
    public boolean isDateActive() {
        return this._$6754;
    }
    
    public boolean isVolumeAtMinActive() {
        return this._$162910;
    }
    
    public boolean isDateAtMinActive() {
        return this._$162921;
    }
    
    public boolean isFrameActive() {
        return this._$6758;
    }
    
    public void enableFRate() {
        this._$6731 = true;
    }
    
    public void enableVSize() {
        this._$2924 = true;
    }
    
    public void enableDZoom() {
        this._$6736 = true;
    }
    
    public void enableControl() {
        this._$162930 = true;
    }
    
    public void enablePresetPosition() {
        this._$162937 = true;
    }
    
    public void enableTrigger() {
        this._$6741 = true;
    }
    
    public void enableVolume() {
        this._$6748 = true;
    }
    
    public void enableDate() {
        this._$6754 = true;
    }
    
    public void enableVolumeAtMin() {
        this._$6748 = true;
        this._$162910 = true;
    }
    
    public void enableDateAtMin() {
        this._$162921 = true;
    }
    
    public void enableFrame() {
        this._$6758 = true;
    }
    
    public void disableAllParts() {
        this._$6731 = false;
        this._$2924 = false;
        this._$6736 = false;
        this._$6741 = false;
        this._$6748 = false;
        this._$6754 = false;
        this._$162910 = false;
        this._$162921 = false;
        this._$6758 = false;
        this._$162930 = false;
        this._$162937 = false;
    }
    
    public long getViewBit() {
        long n = 0L;
        if (this._$6758) {
            ++n;
        }
        if (this._$6754) {
            n += 2;
        }
        if (this._$6748) {
            n += 4;
        }
        if (this._$162921) {
            n += 2;
        }
        if (this._$2924) {
            n += 8;
        }
        if (this._$6736) {
            n += 32;
        }
        if (this._$6731) {
            n += 64;
        }
        if (this._$6741) {
            n += 128;
        }
        if (this._$162930) {
            n += 512;
        }
        if (this._$162937) {
            n += 1024;
        }
        return n;
    }
}
