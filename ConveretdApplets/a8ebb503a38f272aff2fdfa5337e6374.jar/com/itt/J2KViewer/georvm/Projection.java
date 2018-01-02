// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm;

public class Projection
{
    public static final int HEMISPHERE_NORTH = 0;
    public static final int HEMISPHERE_SOUTH = 1;
    private String name;
    private int zone;
    private int hemisphere;
    private char band;
    
    public Projection(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setZone(final int zone) {
        this.zone = zone;
    }
    
    public int getZone() {
        return this.zone;
    }
    
    public void setHemisphere(final int hemisphere) {
        this.hemisphere = hemisphere;
    }
    
    public int getHemisphere() {
        return this.hemisphere;
    }
    
    public void setBand(final char band) {
        this.band = band;
    }
    
    public char getBand() {
        return this.band;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        return o instanceof Projection && this.getName() == ((Projection)o).getName();
    }
}
