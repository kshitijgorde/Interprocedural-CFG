// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.util.Hashtable;
import java.io.Serializable;

public class SeatplanObjects extends SaalplanBaseDetails implements Serializable
{
    public GraphDetails[] graphDetails;
    
    public SeatplanObjects(final int ret, final String retText) {
        super(ret, retText);
    }
    
    public SeatplanObjects() {
    }
    
    public Object getPrimaryKey() {
        return null;
    }
    
    public Hashtable getValuesAsHashtable() {
        return new Hashtable();
    }
    
    public String toString() {
        return "SeatplanObjects: [anzGraphDetails=" + ((this.graphDetails != null) ? String.valueOf(this.graphDetails.length) : null) + "]";
    }
    
    public String toText() {
        final StringBuffer sb = new StringBuffer(this.toString());
        if (this.graphDetails != null) {
            for (int i = 0; i < this.graphDetails.length; ++i) {
                sb.append("\r\n" + (i + 1) + ": " + this.graphDetails[i]);
            }
        }
        return sb.toString();
    }
}
