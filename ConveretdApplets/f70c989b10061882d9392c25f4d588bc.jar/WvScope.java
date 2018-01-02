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
    
    static {
        key = new String[][] { { "pan_left_end", "pan_right_end", "tilt_down_end", "tilt_up_end", "zoom_tele_end", "zoom_wide_end" }, { "pan_left_limit", "pan_right_limit", "tilt_down_limit", "tilt_up_limit", "zoom_tele_limit", "zoom_wide_limit" }, { "view_left_boundary", "view_right_boundary", "view_down_boundary", "view_up_boundary", "view_tele_boundary", "view_wide_boundary" } };
        hardKey = WvScope.key[0];
        softKey = WvScope.key[1];
        viewKey = WvScope.key[2];
    }
    
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
    
    protected WvScope(final WvCameraInfo wvcamerainfo, final int i) {
        this.pMin = -5000;
        this.pMax = 5000;
        this.tMin = -2000;
        this.tMax = 2000;
        this.zMin = 480;
        this.zMax = 4860;
        this.info = wvcamerainfo;
        if (i < 0 || i > WvScope.key.length) {
            Thread.currentThread();
            Thread.dumpStack();
            throw new IllegalArgumentException();
        }
        this.type = i;
    }
    
    protected WvScope(final WvCameraInfo wvcamerainfo, final int i, final int j, final int k, final int l) {
        this.pMin = -5000;
        this.pMax = 5000;
        this.tMin = -2000;
        this.tMax = 2000;
        this.zMin = 480;
        this.zMax = 4860;
        this.info = wvcamerainfo;
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
    
    public void parseProperty(final String s, final String s1) {
        int i = -1;
        for (int j = 0; j < WvScope.key[this.type].length; ++j) {
            if (s.equals(WvScope.key[this.type][j])) {
                i = j;
                break;
            }
        }
        if (i < 0) {
            return;
        }
        try {
            final int k = i / 2;
            final int l = i % 2;
            final int i2 = Integer.parseInt(s1);
            switch (k) {
                case 0: {
                    if (l == 0) {
                        this.pMin = i2;
                        return;
                    }
                    this.pMax = i2;
                }
                case 1: {
                    if (l == 0) {
                        this.tMin = i2;
                        return;
                    }
                    this.tMax = i2;
                }
                case 2: {
                    if (l == 0) {
                        this.zMin = i2;
                        return;
                    }
                    this.zMax = i2;
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
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
