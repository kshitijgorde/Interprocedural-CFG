// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer;

import java.io.Serializable;

public class StehplatzReservierung implements Serializable
{
    public int anzahl;
    public long stehId;
    public int webRabattstufeId;
    
    public String toString() {
        return "StehplatzReservierung: stehId= " + this.stehId + ", webRabattstufeId=" + this.webRabattstufeId + ", anzahl=" + this.anzahl;
    }
}
