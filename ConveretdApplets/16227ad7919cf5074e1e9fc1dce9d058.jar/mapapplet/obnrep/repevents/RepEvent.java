// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.obnrep.repevents;

import java.io.Serializable;

public class RepEvent implements Serializable
{
    private float lat;
    private float lon;
    private int id;
    private String service;
    private float magnitude;
    
    public RepEvent(final int id, final String service, final float lat, final float lon, final float magn) {
        this.id = id;
        this.service = service;
        this.lat = lat;
        this.lon = lon;
        this.magnitude = magn;
    }
    
    public float getLat() {
        return this.lat;
    }
    
    public float getLon() {
        return this.lon;
    }
    
    public void setLat(final float lat) {
        this.lat = lat;
    }
    
    public void setLon(final float lon) {
        this.lon = lon;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public String getService() {
        return this.service;
    }
    
    public void setService(final String service) {
        this.service = service;
    }
    
    public float getMagnitude() {
        return this.magnitude;
    }
    
    public void setMagnitude(final float magnitude) {
        this.magnitude = magnitude;
    }
}
