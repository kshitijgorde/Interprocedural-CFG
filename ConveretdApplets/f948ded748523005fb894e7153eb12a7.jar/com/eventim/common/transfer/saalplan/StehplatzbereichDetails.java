// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.util.Hashtable;
import java.io.Serializable;

public class StehplatzbereichDetails extends GraphDetails implements Serializable
{
    public Hashtable anzFreiProPrId;
    public int anzPlaetze;
    public String block;
    public String eingang;
    public boolean horizontal;
    public long id;
    public GraphDetails linkObjekt;
    public String stehplatzNr;
    public long tdlPkid;
    public long tdlVgLfndNr;
    
    public StehplatzbereichDetails(final long id, final long pkid, final PixPoint[] coords, final GraphDetails linkObjekt, final String block, final String eingang, final String stehplatzNr, final int anzPlaetze, final boolean horizontal) {
        super(coords, (byte)10);
        this.anzFreiProPrId = new Hashtable();
        this.id = id;
        this.tdlPkid = pkid;
        this.linkObjekt = linkObjekt;
        this.block = block;
        this.eingang = eingang;
        this.stehplatzNr = stehplatzNr;
        this.anzPlaetze = anzPlaetze;
        this.horizontal = horizontal;
    }
    
    public boolean isStehplatzbereich() {
        return true;
    }
    
    public String toString() {
        return "[StehplD: id=" + this.id + " pkid=" + this.tdlPkid + " anz=" + this.anzPlaetze + " anzF=" + this.anzFreiProPrId + " b=" + this.block + " e=" + this.eingang + " sNr=" + this.stehplatzNr + " h=" + this.horizontal + " lobj=" + this.linkObjekt + " tdlVgLfndNr=" + this.tdlVgLfndNr + " " + super.toString() + "]";
    }
}
