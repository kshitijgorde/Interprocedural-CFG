// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import org.josql.Query;
import org.apache.activemq.util.ByteSequence;
import org.apache.activemq.command.DataStructure;
import org.apache.activemq.kaha.impl.async.Location;

public class Entry
{
    Location location;
    DataStructure record;
    private ByteSequence data;
    private String type;
    private String formater;
    private Query query;
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(final Location location) {
        this.location = location;
    }
    
    public DataStructure getRecord() {
        return this.record;
    }
    
    public void setRecord(final DataStructure record) {
        this.record = record;
    }
    
    public void setData(final ByteSequence data) {
        this.data = data;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public ByteSequence getData() {
        return this.data;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setFormater(final String formater) {
        this.formater = formater;
    }
    
    public String getFormater() {
        return this.formater;
    }
    
    public void setQuery(final Query query) {
        this.query = query;
    }
    
    public Query getQuery() {
        return this.query;
    }
}
