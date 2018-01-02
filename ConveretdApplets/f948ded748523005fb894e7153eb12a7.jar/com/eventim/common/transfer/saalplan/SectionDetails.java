// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.util.Hashtable;
import java.io.Serializable;

public class SectionDetails implements Serializable
{
    public Hashtable anzFreiProPrId;
    public int anzPlaetze;
    public Hashtable anzahlPlaetzeProTdlPkIdProPrId;
    public Hashtable anzahlZusammenhaengendProTdlPkIdProPrId;
    public short height;
    public short id;
    public String name;
    public short width;
    
    public SectionDetails(final short id, final short width, final short height, final String name) {
        this.anzFreiProPrId = new Hashtable();
        this.anzahlPlaetzeProTdlPkIdProPrId = new Hashtable();
        this.anzahlZusammenhaengendProTdlPkIdProPrId = new Hashtable();
        this.id = id;
        this.width = width;
        this.height = height;
        this.name = name;
    }
    
    public String toString() {
        return "SectionDetails: id = " + this.id + ", name = " + this.name + ", width = " + this.width + ", height = " + this.height + ", anzPlaetze = " + this.anzPlaetze + ", anzFreiProPrId = " + this.anzFreiProPrId;
    }
}
