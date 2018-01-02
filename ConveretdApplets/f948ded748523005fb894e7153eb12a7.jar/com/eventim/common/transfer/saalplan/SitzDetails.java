// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public class SitzDetails extends GraphDetails implements Serializable
{
    public String bereich;
    public int[] buchbar;
    public String eingang;
    public boolean horizontal;
    public long id;
    public String platz;
    public String reihe;
    public long seqNo;
    public long sitzgruppeId;
    public long tdlPkid;
    public long tdlVgLfndNr;
    
    public SitzDetails(final long id, final PixPoint[] coords, final String bereich, final String eingang, final String reihe, final String platz, final long sitzgruppeId, final long seqNo) {
        super(coords, (byte)12);
        this.id = id;
        this.bereich = bereich;
        this.eingang = eingang;
        this.reihe = reihe;
        this.platz = platz;
        this.sitzgruppeId = sitzgruppeId;
        this.seqNo = seqNo;
    }
    
    public boolean isSitz() {
        return true;
    }
    
    public String toString() {
        String bb = "[ ";
        if (this.buchbar != null) {
            for (int i = 0; i < this.buchbar.length; ++i) {
                bb = bb + this.buchbar[i] + " ";
            }
        }
        bb += "]";
        return "[SitzD: id=" + this.id + " bu=" + bb + " tdlPkid=" + this.tdlPkid + " b=" + this.bereich + " e=" + this.eingang + " r=" + this.reihe + " p=" + this.platz + " sg=" + this.sitzgruppeId + " seq=" + this.seqNo + " tdlVgLfndNr=" + this.tdlVgLfndNr + " " + super.toString() + "]";
    }
}
