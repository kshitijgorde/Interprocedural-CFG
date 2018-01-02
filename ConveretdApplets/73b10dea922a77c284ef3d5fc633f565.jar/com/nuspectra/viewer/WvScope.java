// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

public class WvScope
{
    public static final int HARD = 0;
    public static final int SOFT = 1;
    public static final int VIEW = 2;
    public static final int PANO = 3;
    int pMin;
    int pMax;
    int tMin;
    int tMax;
    int zMin;
    int zMax;
    boolean zoomAdjust;
    private int type;
    ControlSession session;
    
    public String toString() {
        final int pr = this.pMax - this.pMin;
        final int tr = this.tMax - this.tMin;
        final int zr = this.zMax - this.zMin;
        return "xMin:" + this.pMin + " xMax:" + this.pMax + " tMin:" + this.tMin + " tMax:" + this.tMax + " zMin:" + this.zMin + " zMax:" + this.zMax + " [" + pr + "," + tr + "," + zr + "]" + " getDegRangeX=" + this.getDegRangeX() + " getDegRangeY=" + this.getDegRangeY();
    }
    
    protected int getTiltMin() {
        if (this.type == 0) {
            return this.tMin - this.zMax * 3 / 4 / 2;
        }
        return this.tMin;
    }
    
    protected int getZoomMin() {
        return this.zMin;
    }
    
    protected WvScope(final int i, final ControlSession session) {
        this.zoomAdjust = true;
        if (session == null) {
            return;
        }
        final int zoomRating = session.getCamInfoInt("zoomXRating", 0);
        final String cameraType = session.getCamInfoString("camera_type");
        if (cameraType != null && cameraType.length() > 0) {
            if (cameraType.compareTo("Celery") == 0 || cameraType.compareTo("Canon VB-C10") == 0 || cameraType.compareTo("Canon VB-C10R") == 0) {
                this.zoomAdjust = true;
            }
        }
        else if (zoomRating == 16) {
            this.zoomAdjust = true;
        }
        this.pMin = session.minPan;
        this.pMax = session.maxPan;
        this.tMin = session.minTilt;
        this.tMax = session.maxTilt;
        this.zMin = session.minZoom;
        this.zMax = session.maxZoom;
        this.zMin = 197;
        this.zMax = 4126;
        this.type = i;
        this.session = session;
        this.zMin = session.getCamInfoInt("minViewAngle", 0);
        this.zMax = session.getCamInfoInt("maxViewAngle", 0);
        switch (this.type) {
            case 2: {
                this.pMin = session.getCamInfoInt("minViewX", 0);
                this.pMax = session.getCamInfoInt("maxViewX", 0);
                this.tMin = session.getCamInfoInt("minViewY", 0);
                this.tMax = session.getCamInfoInt("maxViewY", 0);
                break;
            }
            case 1: {
                this.pMin = session.getCamInfoInt("minPanLimit", 0);
                this.pMax = session.getCamInfoInt("maxPanLimit", 0);
                this.tMin = session.getCamInfoInt("minTiltLimit", 0);
                this.tMax = session.getCamInfoInt("maxTiltLimit", 0);
                break;
            }
            case 0: {
                this.pMin = session.minPan;
                this.pMax = session.maxPan;
                this.tMin = session.minTilt;
                this.tMax = session.maxTilt;
                break;
            }
        }
        if (this.type == 2) {}
    }
    
    protected WvScope(final int i, final int j, final int k, final int l) {
        this.zoomAdjust = true;
        this.pMin = -5000;
        this.pMax = 5000;
        this.tMin = -2000;
        this.tMax = 2000;
        this.zMin = 480;
        this.zMax = 4860;
        this.type = 3;
        this.pMin = i;
        this.pMax = j;
        this.tMin = k;
        this.tMax = l;
    }
    
    protected void setViewScope(final WvScope wvscope, final WvScope wvscope1) {
        this.pMin = ((wvscope.pMin >= wvscope1.pMin) ? wvscope.getPanMin() : wvscope1.pMin);
        this.pMax = ((wvscope.pMax <= wvscope1.pMax) ? wvscope.getPanMax() : wvscope1.pMax);
        this.tMax = ((wvscope.tMax <= wvscope1.tMax) ? wvscope.getTiltMax() : wvscope1.tMax);
        this.tMin = ((wvscope.tMin >= wvscope1.tMin) ? wvscope.getTiltMin() : wvscope1.tMin);
        this.zMin = wvscope1.zMin;
        this.zMax = wvscope1.zMax;
    }
    
    public int getDegRangeX() {
        if (this.type == 0) {
            return this.pMax - this.pMin + this.zMax;
        }
        return this.pMax - this.pMin;
    }
    
    protected int getPanMin() {
        if (this.type == 0) {
            return this.pMin - this.zMax / 2;
        }
        return this.pMin;
    }
    
    protected int getTiltMax() {
        if (this.type == 0) {
            return this.tMax + this.zMax * 3 / 4 / 2;
        }
        return this.tMax;
    }
    
    protected int getZoomMax() {
        return this.zMax;
    }
    
    public boolean isEmpty() {
        return (this.pMax - this.pMin) * (this.tMax - this.tMin) <= 4;
    }
    
    protected int getPanMax() {
        if (this.type == 0) {
            return this.pMax + this.zMax / 2;
        }
        return this.pMax;
    }
    
    public int getZoomHeight(final int z) {
        if (this.zoomAdjust) {
            return z * 11 / 10;
        }
        return z;
    }
    
    public int getDegRangeY() {
        if (this.type == 0) {
            return this.tMax - this.tMin + this.getZoomHeight(this.zMax);
        }
        return this.tMax - this.tMin;
    }
}
