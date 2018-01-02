// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import org.apache.activeio.journal.JournalEventListener;
import org.apache.activemq.util.ByteSequence;
import org.apache.activeio.packet.ByteArrayPacket;
import org.apache.activeio.packet.Packet;
import org.apache.activeio.journal.InvalidRecordLocationException;
import java.io.IOException;
import org.apache.activeio.journal.RecordLocation;
import org.apache.activeio.journal.Journal;

public final class JournalFacade implements Journal
{
    private final AsyncDataManager dataManager;
    
    public JournalFacade(final AsyncDataManager dataManager) {
        this.dataManager = dataManager;
    }
    
    private static RecordLocation convertToRecordLocation(final Location location) {
        if (location == null) {
            return null;
        }
        return (RecordLocation)new RecordLocationFacade(location);
    }
    
    private static Location convertFromRecordLocation(final RecordLocation location) {
        if (location == null) {
            return null;
        }
        return ((RecordLocationFacade)location).getLocation();
    }
    
    public void close() throws IOException {
        this.dataManager.close();
    }
    
    public RecordLocation getMark() throws IllegalStateException {
        return convertToRecordLocation(this.dataManager.getMark());
    }
    
    public RecordLocation getNextRecordLocation(final RecordLocation location) throws InvalidRecordLocationException, IOException, IllegalStateException {
        return convertToRecordLocation(this.dataManager.getNextLocation(convertFromRecordLocation(location)));
    }
    
    public Packet read(final RecordLocation location) throws InvalidRecordLocationException, IOException, IllegalStateException {
        final ByteSequence rc = this.dataManager.read(convertFromRecordLocation(location));
        if (rc == null) {
            return null;
        }
        return (Packet)new ByteArrayPacket(rc.getData(), rc.getOffset(), rc.getLength());
    }
    
    public void setJournalEventListener(final JournalEventListener listener) throws IllegalStateException {
    }
    
    public void setMark(final RecordLocation location, final boolean sync) throws InvalidRecordLocationException, IOException, IllegalStateException {
        this.dataManager.setMark(convertFromRecordLocation(location), sync);
    }
    
    public RecordLocation write(final Packet packet, final boolean sync) throws IOException, IllegalStateException {
        final org.apache.activeio.packet.ByteSequence data = packet.asByteSequence();
        final ByteSequence sequence = new ByteSequence(data.getData(), data.getOffset(), data.getLength());
        return convertToRecordLocation(this.dataManager.write(sequence, sync));
    }
    
    public RecordLocation write(final Packet packet, final Runnable onComplete) throws IOException, IllegalStateException {
        final org.apache.activeio.packet.ByteSequence data = packet.asByteSequence();
        final ByteSequence sequence = new ByteSequence(data.getData(), data.getOffset(), data.getLength());
        return convertToRecordLocation(this.dataManager.write(sequence, onComplete));
    }
    
    public static class RecordLocationFacade implements RecordLocation
    {
        private final Location location;
        
        public RecordLocationFacade(final Location location) {
            this.location = location;
        }
        
        public Location getLocation() {
            return this.location;
        }
        
        public int compareTo(final Object o) {
            final RecordLocationFacade rlf = (RecordLocationFacade)o;
            final int rc = this.location.compareTo(rlf.location);
            return rc;
        }
    }
}
