// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public class SaalplanSperrtexteDetails extends SaalplanBaseDetails implements Serializable
{
    public int color;
    public int laufendeNummer;
    public String sperrText;
    public int vorstellungSperrTexteID;
    
    public int getColor() {
        return this.color;
    }
    
    public int getLaufendeNummer() {
        return this.laufendeNummer;
    }
    
    public String getSperrText() {
        return this.sperrText;
    }
    
    public int getVorstellungSperrTexteID() {
        return this.vorstellungSperrTexteID;
    }
    
    public void setColor(final int color) {
        this.color = color;
    }
    
    public void setLaufendeNummer(final int laufendeNummer) {
        this.laufendeNummer = laufendeNummer;
    }
    
    public void setSperrText(final String sperrText) {
        this.sperrText = sperrText;
    }
    
    public void setVorstellungSperrTexteID(final int vorstellungSperrTexteID) {
        this.vorstellungSperrTexteID = vorstellungSperrTexteID;
    }
}
