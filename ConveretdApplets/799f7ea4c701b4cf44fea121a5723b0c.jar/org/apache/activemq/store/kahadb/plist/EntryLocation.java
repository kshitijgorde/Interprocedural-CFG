// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadb.plist;

import org.apache.kahadb.util.VariableMarshaller;
import java.io.DataOutput;
import java.io.IOException;
import java.io.DataInput;
import org.apache.kahadb.journal.Location;
import org.apache.kahadb.page.Page;

class EntryLocation
{
    static final long NOT_SET = -1L;
    private String id;
    private Page<EntryLocation> page;
    private long next;
    private long prev;
    private Location location;
    
    EntryLocation(final Location location) {
        this.location = location;
    }
    
    EntryLocation() {
    }
    
    EntryLocation copy() {
        final EntryLocation result = new EntryLocation();
        result.id = this.id;
        result.location = this.location;
        result.next = this.next;
        result.prev = this.prev;
        result.page = this.page;
        return result;
    }
    
    void reset() {
        this.id = "";
        this.next = -1L;
        this.prev = -1L;
    }
    
    public void readExternal(final DataInput in) throws IOException {
        this.id = in.readUTF();
        this.prev = in.readLong();
        this.next = in.readLong();
        if (this.location == null) {
            this.location = new Location();
        }
        this.location.readExternal(in);
    }
    
    public void writeExternal(final DataOutput out) throws IOException {
        out.writeUTF(this.id);
        out.writeLong(this.prev);
        out.writeLong(this.next);
        if (this.location == null) {
            this.location = new Location();
        }
        this.location.writeExternal(out);
    }
    
    String getId() {
        return this.id;
    }
    
    void setId(final String id) {
        this.id = id;
    }
    
    Location getLocation() {
        return this.location;
    }
    
    void setLocation(final Location location) {
        this.location = location;
    }
    
    long getNext() {
        return this.next;
    }
    
    void setNext(final long next) {
        this.next = next;
    }
    
    long getPrev() {
        return this.prev;
    }
    
    void setPrev(final long prev) {
        this.prev = prev;
    }
    
    Page<EntryLocation> getPage() {
        return this.page;
    }
    
    void setPage(final Page<EntryLocation> page) {
        this.page = page;
    }
    
    static class EntryLocationMarshaller extends VariableMarshaller<EntryLocation>
    {
        static final EntryLocationMarshaller INSTANCE;
        
        @Override
        public EntryLocation readPayload(final DataInput dataIn) throws IOException {
            final EntryLocation result = new EntryLocation();
            result.readExternal(dataIn);
            return result;
        }
        
        @Override
        public void writePayload(final EntryLocation value, final DataOutput dataOut) throws IOException {
            value.writeExternal(dataOut);
        }
        
        static {
            INSTANCE = new EntryLocationMarshaller();
        }
    }
}
