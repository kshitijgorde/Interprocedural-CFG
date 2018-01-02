// 
// Decompiled by Procyon v0.5.30
// 

public class WvPTZB implements WvPropertyParser
{
    public static final int PAN = 0;
    public static final int TILT = 1;
    public static final int ZOOM = 2;
    public static final String[] key;
    public int pan;
    public int tilt;
    public int zoom;
    public int cameraId;
    public boolean backLight;
    public boolean pInitialized;
    public boolean tInitialized;
    public boolean zInitialized;
    public boolean cInitialized;
    public boolean bInitialized;
    
    static {
        key = new String[] { "pan_current_value", "pan", "tilt_current_value", "tilt", "zoom_current_value", "zoom", "camera_id", "back_light" };
    }
    
    public String toString() {
        return "p:" + this.pan + " t:" + this.tilt + " z:" + this.zoom + " id:" + this.cameraId + " p:" + this.pInitialized + " t:" + this.tInitialized + " z:" + this.zInitialized;
    }
    
    public WvPTZB() {
        this.zoom = 3000;
        this.cameraId = 1;
    }
    
    public boolean equals(final WvPTZB wvptzb) {
        return wvptzb.pan == this.pan && wvptzb.tilt == this.tilt && wvptzb.zoom == this.zoom && wvptzb.cameraId == this.cameraId && wvptzb.backLight == this.backLight;
    }
    
    public void parseProperty(final String s, final String s1) {
        int i = -1;
        for (int j = 0; j < WvPTZB.key.length; ++j) {
            if (WvPTZB.key[j].equals(s)) {
                i = j;
                break;
            }
        }
        if (i < 0) {
            return;
        }
        switch (i) {
            case 0:
            case 1: {
                this.pan = Integer.parseInt(s1);
                this.pInitialized = true;
            }
            case 2:
            case 3: {
                this.tilt = Integer.parseInt(s1);
                this.tInitialized = true;
            }
            case 4:
            case 5: {
                this.zoom = Integer.parseInt(s1);
                this.zInitialized = true;
            }
            case 6: {
                this.cameraId = Integer.parseInt(s1);
                this.cInitialized = true;
            }
            case 7: {
                this.backLight = s1.equalsIgnoreCase("on");
                this.bInitialized = true;
            }
            default: {}
        }
    }
    
    public String getPresetCommand() {
        String s = new String();
        if (this.pInitialized) {
            s = String.valueOf(s) + "&pan=" + this.pan;
        }
        if (this.tInitialized) {
            s = String.valueOf(s) + "&tilt=" + this.tilt;
        }
        if (this.zInitialized) {
            s = String.valueOf(s) + "&zoom=" + this.zoom;
        }
        if (this.bInitialized) {
            s = String.valueOf(s) + "&back_light=" + (this.backLight ? "on" : "off");
        }
        if (this.cInitialized) {
            s = String.valueOf(s) + "&camera_id=" + this.cameraId;
        }
        if (s.length() == 0) {
            return null;
        }
        return "?" + s.substring(1);
    }
}
