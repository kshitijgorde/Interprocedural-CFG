// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

final class ViewModeDetail
{
    private boolean _$1927;
    private boolean _$1928;
    private boolean _$897;
    private boolean _$1929;
    private boolean _$1930;
    private boolean _$1931;
    private boolean _$1932;
    private boolean _$1933;
    private boolean _$1934;
    private boolean _$1935;
    private boolean _$1433;
    
    public ViewModeDetail() {
        this._$1927 = false;
        this._$1928 = false;
        this._$897 = false;
        this._$1929 = false;
        this._$1930 = false;
        this._$1931 = false;
        this._$1934 = false;
        this._$1935 = false;
        this._$1433 = false;
        this._$1932 = false;
        this._$1933 = false;
    }
    
    public boolean isFRateActive() {
        return this._$1927;
    }
    
    public boolean isVSizeActive() {
        return this._$1928;
    }
    
    public boolean isDZoomActive() {
        return this._$897;
    }
    
    public boolean isControlActive() {
        return this._$1935;
    }
    
    public boolean isPresetPositionActive() {
        return this._$1433;
    }
    
    public boolean isPresetPositionIndipendent() {
        return this.isPresetPositionActive() && !this.isControlActive();
    }
    
    public boolean isTriggerActive() {
        return this._$1929;
    }
    
    public boolean isVolumeActive() {
        return this._$1930;
    }
    
    public boolean isDateActive() {
        return this._$1931;
    }
    
    public boolean isVolumeAtMinActive() {
        return this._$1932;
    }
    
    public boolean isDateAtMinActive() {
        return this._$1933;
    }
    
    public boolean isFrameActive() {
        return this._$1934;
    }
    
    public void enableFRate() {
        this._$1927 = true;
    }
    
    public void enableVSize() {
        this._$1928 = true;
    }
    
    public void enableDZoom() {
        this._$897 = true;
    }
    
    public void enableControl() {
        this._$1935 = true;
    }
    
    public void enablePresetPosition() {
        this._$1433 = true;
    }
    
    public void enableTrigger() {
        this._$1929 = true;
    }
    
    public void enableVolume() {
        this._$1930 = true;
    }
    
    public void enableDate() {
        this._$1931 = true;
    }
    
    public void enableVolumeAtMin() {
        this._$1930 = true;
        this._$1932 = true;
    }
    
    public void enableDateAtMin() {
        this._$1933 = true;
    }
    
    public void enableFrame() {
        this._$1934 = true;
    }
    
    public void disableAllParts() {
        this._$1927 = false;
        this._$1928 = false;
        this._$897 = false;
        this._$1929 = false;
        this._$1930 = false;
        this._$1931 = false;
        this._$1932 = false;
        this._$1933 = false;
        this._$1934 = false;
        this._$1935 = false;
        this._$1433 = false;
    }
    
    public long getViewBit() {
        long n = 0L;
        if (this._$1934) {
            ++n;
        }
        if (this._$1931) {
            n += 2L;
        }
        if (this._$1930) {
            n += 4L;
        }
        if (this._$1933) {
            n += 2L;
        }
        if (this._$1928) {
            n += 8L;
        }
        if (this._$897) {
            n += 32L;
        }
        if (this._$1927) {
            n += 64L;
        }
        if (this._$1929) {
            n += 128L;
        }
        if (this._$1935) {
            n += 512L;
        }
        if (this._$1433) {
            n += 1024L;
        }
        return n;
    }
}
