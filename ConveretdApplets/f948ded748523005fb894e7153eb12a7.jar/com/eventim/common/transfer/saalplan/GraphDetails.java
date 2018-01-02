// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public abstract class GraphDetails implements Serializable
{
    public static final byte PRIO_ELLIPSE = 2;
    public static final byte PRIO_POLYGON = 3;
    public static final byte PRIO_POLYLINE = 4;
    public static final byte PRIO_RECHTECK = 1;
    public static final byte PRIO_REIHENTEXT = 11;
    public static final byte PRIO_SITZ = 12;
    public static final byte PRIO_STEHPLATZ = 10;
    public static final byte PRIO_TEXT = 5;
    public PixPoint[] coords;
    public byte prio;
    public short zielabschnittNr;
    
    public GraphDetails(final PixPoint[] coords, final byte prio) {
        this.zielabschnittNr = -1;
        this.coords = coords;
        this.prio = prio;
    }
    
    public boolean isEllipse() {
        return false;
    }
    
    public boolean isPolygon() {
        return false;
    }
    
    public boolean isPolyline() {
        return false;
    }
    
    public boolean isRechteck() {
        return false;
    }
    
    public boolean isReiheBez() {
        return false;
    }
    
    public boolean isSitz() {
        return false;
    }
    
    public boolean isStehplatzbereich() {
        return false;
    }
    
    public boolean isText() {
        return false;
    }
    
    public void setPrio(final byte prio) {
        this.prio = prio;
    }
    
    public void setZielabschnittNr(final short zielabschnittNr) {
        this.zielabschnittNr = zielabschnittNr;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("coords=");
        if (this.coords == null) {
            sb.append("null");
        }
        else {
            for (int i = 0; i < this.coords.length; ++i) {
                sb.append(this.coords[i].toString());
            }
        }
        sb.append(" za=" + this.zielabschnittNr);
        return sb.toString();
    }
}
