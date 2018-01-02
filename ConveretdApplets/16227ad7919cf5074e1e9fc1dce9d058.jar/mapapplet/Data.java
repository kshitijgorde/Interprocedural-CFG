// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet;

import java.awt.Dimension;
import java.util.Observable;

public class Data extends Observable
{
    public float maplonleft;
    public float maplatdown;
    public float maplonright;
    public float maplatup;
    MapProjection prj;
    private Dimension mapSize;
    public boolean initialized;
    public String extend;
    public String tempExtend;
    public int projection;
    
    public Data() {
        this.initialized = false;
        this.projection = 0;
        this.mapSize = null;
    }
    
    public Dimension getMapSize() {
        return this.mapSize;
    }
    
    public void setExtend(final String extend) {
        this.extend = extend;
        this.maplonleft = Main.atof(Main.getToken(extend, 1));
        this.maplatdown = Main.atof(Main.getToken(extend, 2));
        this.maplonright = Main.atof(Main.getToken(extend, 3));
        this.maplatup = Main.atof(Main.getToken(extend, 4));
    }
    
    public void setTempExtend(final String extend) {
        this.tempExtend = extend;
    }
    
    public String getExtend() {
        return this.extend;
    }
    
    public void setMapSize(final Dimension mapSize) {
        if (this.projection == 0) {
            this.prj = new MapProjection(0.0f, 0.0f, this.maplatup, this.maplonleft, mapSize.width, mapSize.height, this.maplatdown, this.maplonright);
        }
        else if (this.projection == 1) {
            String hemisph = "n";
            if (this.maplatup == 90.0f) {
                hemisph = "n";
            }
            else if (this.maplatdown == -90.0f) {
                hemisph = "s";
            }
            this.prj = new MapProjection(0.0f, 0.0f, mapSize.width, mapSize.height, (hemisph.compareTo("n") == 0) ? this.maplatdown : this.maplatup, hemisph);
        }
        this.mapSize = mapSize;
        this.initialized = true;
    }
    
    public void calculatePrj() {
        this.setMapSize(this.mapSize);
    }
}
