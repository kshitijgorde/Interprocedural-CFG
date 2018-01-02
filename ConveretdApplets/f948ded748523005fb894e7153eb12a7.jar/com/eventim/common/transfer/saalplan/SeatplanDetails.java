// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.util.Hashtable;
import java.io.Serializable;

public class SeatplanDetails extends SaalplanBaseDetails implements Serializable
{
    public SaalplanAffiliateDetails affiliateDetails;
    public int hintergrundfarbe;
    public SaalplanPlatzkategorieDetails[] platzkategorieDetails;
    public SectionDetails[] sectionDetails;
    public SaalplanSperrtexteDetails[] sperrtexteDetais;
    public byte verkaufsart;
    
    public Object getPrimaryKey() {
        return null;
    }
    
    public Hashtable getValuesAsHashtable() {
        return new Hashtable();
    }
    
    public String toString() {
        return "SeatplanDetails [ anzSectionDetails=" + ((this.sectionDetails != null) ? String.valueOf(this.sectionDetails.length) : null) + " anzPlatzkategorieDetails=" + ((this.platzkategorieDetails != null) ? String.valueOf(this.platzkategorieDetails.length) : null) + " verkaufsart=" + (char)this.verkaufsart + " hg=" + Integer.toHexString(this.hintergrundfarbe).toUpperCase() + " aff=" + ((this.affiliateDetails != null) ? this.affiliateDetails.getKuerzel() : null) + "]";
    }
    
    public String toText() {
        final String NEWLINE = "\r\n";
        final StringBuffer sb = new StringBuffer();
        if (this.sectionDetails != null) {
            sb.append("[--> Start sectionDetails: ]\r\n");
            for (int i = 0; i < this.sectionDetails.length; ++i) {
                sb.append(this.sectionDetails[i].toString() + "\r\n");
            }
            sb.append("[<-- End sectionDetails: ]\r\n");
        }
        if (this.platzkategorieDetails != null) {
            sb.append("[--> Start platzkategorieDetails: ]\r\n");
            for (int i = 0; i < this.platzkategorieDetails.length; ++i) {
                sb.append(this.platzkategorieDetails[i].toString() + "\r\n");
            }
            sb.append("[<-- End platzkategorieDetails: ]\r\n");
        }
        return sb.toString();
    }
}
