// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

class Preset
{
    public int id;
    public String name;
    
    protected Preset(final int id, final String name) {
        this.id = id;
        this.name = name;
        if (this.name == null) {
            this.name = "Preset " + id;
        }
    }
    
    protected int getID() {
        return this.id;
    }
    
    protected String getName() {
        return this.name;
    }
    
    public String toString() {
        return String.valueOf(this.getName()) + " [" + this.id + "]";
    }
}
