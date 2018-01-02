// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

public class IpPortRange
{
    int sp;
    int ep;
    public static final int min = 0;
    public static final int max = 65535;
    
    public IpPortRange(final int sp, final int ep) throws Exception {
        this.sp = 0;
        this.ep = 0;
        if (sp < 0 || sp > 65535) {
            throw new Exception("Invalid Port Number");
        }
        if (ep < 0 || ep > 65535) {
            throw new Exception("Invalid Port Number");
        }
        if (sp > ep) {
            throw new Exception("Invalid Port Range - SP:" + Integer.toString(sp) + " EP:" + Integer.toString(ep));
        }
        this.sp = sp;
        this.ep = ep;
    }
    
    public IpPortRange(final int sp) throws Exception {
        this.sp = 0;
        this.ep = 0;
        if (sp < 0 || sp > 65535) {
            throw new Exception("Invalid Port Number");
        }
        if ((this.ep = sp) > this.ep) {
            throw new Exception("Invalid Port Range - SP:" + Integer.toString(sp) + " EP:" + Integer.toString(this.ep));
        }
        this.sp = sp;
    }
    
    public int sp() {
        return this.sp;
    }
    
    public int ep() {
        return this.ep;
    }
    
    public boolean isInRange(final int port) {
        return port >= this.sp && port <= this.ep;
    }
    
    public boolean isInRange(final IpPortRange range) {
        return range.sp() >= this.sp && range.ep() <= this.ep;
    }
    
    public boolean equals(final IpPortRange range) {
        return range.sp() == this.sp && range.ep() == this.ep;
    }
    
    public boolean all() {
        return this.sp == 0 && this.ep == 65535;
    }
    
    public static int compare(final IpPortRange range1, final IpPortRange range2) {
        if (range1 == null || range2 == null) {
            return -1;
        }
        if (range1.sp() == range2.sp() && range1.ep() == range2.ep()) {
            return 0;
        }
        if (range1.sp() <= range2.sp() && range1.ep() >= range2.ep()) {
            return 1;
        }
        if (range2.sp() <= range1.sp() && range2.ep() >= range1.ep()) {
            return 2;
        }
        if (findx(range1, range2) == null) {
            return -1;
        }
        return -2;
    }
    
    public static IpPortRange findx(final IpPortRange range1, final IpPortRange range2) {
        int sp = 0;
        int ep = 0;
        IpPortRange r = null;
        if (range1.isInRange(range2.sp())) {
            sp = range2.sp();
        }
        else {
            if (!range2.isInRange(range1.sp())) {
                return null;
            }
            sp = range1.sp();
        }
        if (range1.isInRange(range2.ep())) {
            ep = range2.ep();
        }
        else {
            if (!range2.isInRange(range1.ep())) {
                return null;
            }
            ep = range1.ep();
        }
        try {
            r = new IpPortRange(sp, ep);
            return r;
        }
        catch (Exception e) {
            return null;
        }
    }
}
