// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.obnrep;

import mapapplet.obnrep.repevents.RepEvent;
import mapapplet.MapPoint;

public class Event extends MapPoint
{
    public boolean isSelected;
    int number;
    String service;
    float magnitude;
    
    protected Event(final RepEvent rev) {
        this.setLat(rev.getLat());
        this.setLon(rev.getLon());
        this.setNumber(rev.getId());
        this.setService(rev.getService());
        this.setMagnitude(rev.getMagnitude());
    }
    
    public float getDistance(final float dlat, final float dlon) {
        final float distlat = Math.abs(dlat - this.getLat());
        final float distlon = Math.abs(dlon - this.getLon());
        return (float)Math.sqrt(distlat * distlat + distlon * distlon);
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(final int number) {
        this.number = number;
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
