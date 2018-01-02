// 
// Decompiled by Procyon v0.5.30
// 

public class WvScope implements WvPropertyParser
{
    public static final int HARD = 0;
    public static final int SOFT = 1;
    public static final int VIEW = 2;
    public static final int PANO = 3;
    private static final String[][] key;
    public static final String[] hardKey;
    public static final String[] softKey;
    public static final String[] viewKey;
    int pMin;
    int pMax;
    int tMin;
    int tMax;
    int zMin;
    int zMax;
    private int type;
    public WvCameraInfo info;
    
    public String toString() {
        return "xMin:" + this.pMin + "\txMax:" + this.pMax + "\ttMin:" + this.tMin + "\ttMax:" + this.tMax;
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
    
    protected WvScope(final WvCameraInfo info, final int type) {
        this.pMin = -5000;
        this.pMax = 5000;
        this.tMin = -2000;
        this.tMax = 2000;
        this.zMin = 480;
        this.zMax = 4860;
        this.info = info;
        if (type < 0 || type > WvScope.key.length) {
            Thread.currentThread();
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
        this.type = type;
    }
    
    protected WvScope(final WvCameraInfo info, final int pMin, final int pMax, final int tMin, final int tMax) {
        this.pMin = -5000;
        this.pMax = 5000;
        this.tMin = -2000;
        this.tMax = 2000;
        this.zMin = 480;
        this.zMax = 4860;
        this.info = info;
        this.type = 3;
        this.pMin = pMin;
        this.pMax = pMax;
        this.tMin = tMin;
        this.tMax = tMax;
    }
    
    protected void setViewScope(final WvScope wvScope, final WvScope wvScope2) {
        this.pMin = ((wvScope.pMin < wvScope2.pMin) ? wvScope2.pMin : wvScope.getPanMin());
        this.pMax = ((wvScope.pMax > wvScope2.pMax) ? wvScope2.pMax : wvScope.getPanMax());
        this.tMax = ((wvScope.tMax > wvScope2.tMax) ? wvScope2.tMax : wvScope.getTiltMax());
        this.tMin = ((wvScope.tMin < wvScope2.tMin) ? wvScope2.tMin : wvScope.getTiltMin());
        this.zMin = wvScope2.zMin;
        this.zMax = wvScope2.zMax;
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
    
    static {
        key = new String[][] { { "pan_left_end", "pan_right_end", "tilt_down_end", "tilt_up_end", "zoom_tele_end", "zoom_wide_end" }, { "pan_left_limit", "pan_right_limit", "tilt_down_limit", "tilt_up_limit", "zoom_tele_limit", "zoom_wide_limit" }, { "view_left_boundary", "view_right_boundary", "view_down_boundary", "view_up_boundary", "view_tele_boundary", "view_wide_boundary" } };
        hardKey = WvScope.key[0];
        softKey = WvScope.key[1];
        viewKey = WvScope.key[2];
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
    
    public void parseProperty(final String s, final String s2) {
        int n = -1;
        for (int i = 0; i < WvScope.key[this.type].length; ++i) {
            if (s.equals(WvScope.key[this.type][i])) {
                n = i;
                break;
            }
        }
        if (n < 0) {
            return;
        }
        try {
            final int n2 = n / 2;
            final int n3 = n % 2;
            final int int1 = Integer.parseInt(s2);
            switch (n2) {
                case 0: {
                    if (n3 == 0) {
                        this.pMin = int1;
                        return;
                    }
                    this.pMax = int1;
                }
                case 1: {
                    if (n3 == 0) {
                        this.tMin = int1;
                        return;
                    }
                    this.tMax = int1;
                }
                case 2: {
                    if (n3 == 0) {
                        this.zMin = int1;
                        return;
                    }
                    this.zMax = int1;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
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
    
    public int getDegRangeY() {
        if (this.type == 0) {
            return this.tMax - this.tMin + this.info.getZoomHeight(this.zMax);
        }
        return this.tMax - this.tMin;
    }
}
