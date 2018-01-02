// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Event;
import java.awt.Scrollbar;

public class VBPTZBar extends Scrollbar implements SessionListener
{
    public static final int kPan = 1;
    public static final int kTilt = 2;
    public static final int kZoom = 3;
    public static final int kPercentMax = 100;
    int scrollKind;
    boolean reverse;
    long lastMove;
    String kind;
    ControlSession session;
    
    private void println(final String s) {
        Debug.println(String.valueOf(this.kind) + " " + s);
    }
    
    private void report(final String s) {
        Debug.report("scroll " + this.kind + " " + s);
    }
    
    public void Assert(final boolean v, final String p) {
        if (!v) {
            this.report("Scroll Assert Failure " + p);
        }
    }
    
    public VBPTZBar(final int itemType) {
        super((itemType != 1) ? 1 : 0, 0, 0, 0, 1);
        this.reverse = false;
        this.lastMove = 0L;
        this.kind = "?";
        this.scrollKind = itemType;
    }
    
    private int normalizeValue(final int i) {
        if (this.reverse) {
            return this.getMaximum() - i;
        }
        return i;
    }
    
    public void setSession(final ControlSession session) {
        if (this.session == session) {
            return;
        }
        if ((this.session = session) == null) {
            return;
        }
        session.addListener(this);
        int value = 0;
        int min = 0;
        int max = 0;
        switch (this.scrollKind) {
            case 1: {
                value = session.getPan();
                min = session.minPan;
                max = session.maxPan;
                this.kind = "pan";
                break;
            }
            case 2: {
                value = session.getTilt();
                min = session.minTilt;
                max = session.maxTilt;
                this.kind = "tilt";
                break;
            }
            case 3: {
                value = session.getZoom();
                max = session.maxZoom;
                min = session.minZoom;
                this.kind = "zoom";
                this.reverse = true;
                break;
            }
            default: {
                return;
            }
        }
        final int range = max - min;
        if (range < 0) {
            this.report("min>max");
        }
        this.setMaximum(max);
        this.setMinimum(min);
        if (value == 999999) {
            this.setEnabled(false);
            return;
        }
        final int nv = this.normalizeValue(value);
        this.println("Scroll: Min:" + min + " Max:" + max + " value=" + value + " nv=" + nv + " reverse:" + (this.reverse ? "t" : "f"));
        this.setValues(nv, 1, min, max);
        if (this.reverse) {
            this.setValue(nv);
        }
        if (value < min) {
            this.report("min range error");
            value = min;
        }
        if (value > max) {
            this.report("max range error");
            value = max;
        }
        this.setUnitIncrement(range / 1000);
        if (this.scrollKind == 3) {
            this.setBlockIncrement(range / 10);
        }
        else {
            this.setBlockIncrement(range / 30);
        }
    }
    
    public String debugStr() {
        final String s = "scrollValue:" + this.getValue() + " range (" + this.getMinimum() + "->" + this.getMaximum() + ")";
        return s;
    }
    
    public void inspect() {
        this.println(this.debugStr());
    }
    
    public boolean handleEvent(final Event event) {
        final boolean target = event.target instanceof VBPTZBar;
        if (target && event.id >= 601 && event.id <= 605) {
            try {
                final int v = this.normalizeValue(this.getValue());
                if (this.session != null) {
                    switch (this.scrollKind) {
                        case 1: {
                            this.session.setPan(v);
                            break;
                        }
                        case 2: {
                            this.session.setTilt(v);
                            break;
                        }
                        case 3: {
                            this.session.setZoom(v);
                            break;
                        }
                    }
                }
            }
            catch (Exception e) {
                Debug.report(e, "Scroll");
            }
            return super.handleEvent(event);
        }
        return super.handleEvent(event);
    }
    
    public void stateChanged(final int i, final int queuePos, final int queueLen) {
        this.setEnabled(this.session != null && this.session.controlsEnabled());
    }
    
    public void cameraChanged(final int p, final int t, int z, final int backlight) {
        switch (this.scrollKind) {
            case 1: {
                if (p != this.getValue()) {
                    this.setValue(p);
                    break;
                }
                break;
            }
            case 2: {
                if (t != this.getValue()) {
                    this.setValue(t);
                    break;
                }
                break;
            }
            case 3: {
                z = this.normalizeValue(z);
                if (z != this.getValue()) {
                    this.setValue(z);
                    break;
                }
                break;
            }
        }
    }
    
    public int getWidth() {
        return this.getBounds().width;
    }
    
    public int getHeight() {
        return this.getBounds().height;
    }
    
    public void moveRequested(final int p, final int t, final int z, final int backlight) {
    }
}
