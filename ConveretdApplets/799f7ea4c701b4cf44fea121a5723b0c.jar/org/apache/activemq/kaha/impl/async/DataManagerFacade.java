// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import org.apache.activemq.kaha.impl.data.RedoListener;
import java.io.DataOutput;
import org.apache.activemq.util.DataByteArrayOutputStream;
import java.io.IOException;
import java.io.DataInput;
import org.apache.activemq.util.DataByteArrayInputStream;
import org.apache.activemq.kaha.StoreLocation;
import org.apache.activemq.kaha.Marshaller;
import org.apache.activemq.util.ByteSequence;
import org.apache.activemq.kaha.impl.DataManager;

public final class DataManagerFacade implements DataManager
{
    private static final ByteSequence FORCE_COMMAND;
    private AsyncDataManager dataManager;
    private final String name;
    private Marshaller redoMarshaller;
    
    public DataManagerFacade(final AsyncDataManager dataManager, final String name) {
        this.dataManager = dataManager;
        this.name = name;
    }
    
    private static StoreLocation convertToStoreLocation(final Location location) {
        if (location == null) {
            return null;
        }
        return new StoreLocationFacade(location);
    }
    
    private static Location convertFromStoreLocation(final StoreLocation location) {
        if (location == null) {
            return null;
        }
        if (location.getClass() == StoreLocationFacade.class) {
            return ((StoreLocationFacade)location).getLocation();
        }
        final Location l = new Location();
        l.setOffset((int)location.getOffset());
        l.setSize(location.getSize());
        l.setDataFileId(location.getFile());
        return l;
    }
    
    @Override
    public Object readItem(final Marshaller marshaller, final StoreLocation location) throws IOException {
        final ByteSequence sequence = this.dataManager.read(convertFromStoreLocation(location));
        final DataByteArrayInputStream dataIn = new DataByteArrayInputStream(sequence);
        return marshaller.readPayload(dataIn);
    }
    
    @Override
    public StoreLocation storeDataItem(final Marshaller marshaller, final Object payload) throws IOException {
        final DataByteArrayOutputStream buffer = new DataByteArrayOutputStream();
        marshaller.writePayload(payload, buffer);
        final ByteSequence data = buffer.toByteSequence();
        return convertToStoreLocation(this.dataManager.write(data, (byte)1, false));
    }
    
    @Override
    public void force() throws IOException {
        this.dataManager.write(DataManagerFacade.FORCE_COMMAND, (byte)2, true);
    }
    
    @Override
    public void updateItem(final StoreLocation location, final Marshaller marshaller, final Object payload) throws IOException {
        final DataByteArrayOutputStream buffer = new DataByteArrayOutputStream();
        marshaller.writePayload(payload, buffer);
        final ByteSequence data = buffer.toByteSequence();
        this.dataManager.update(convertFromStoreLocation(location), data, false);
    }
    
    @Override
    public void close() throws IOException {
        this.dataManager.close();
    }
    
    @Override
    public void consolidateDataFiles() throws IOException {
        this.dataManager.consolidateDataFiles();
    }
    
    @Override
    public boolean delete() throws IOException {
        return this.dataManager.delete();
    }
    
    @Override
    public void addInterestInFile(final int file) throws IOException {
        this.dataManager.addInterestInFile(file);
    }
    
    @Override
    public void removeInterestInFile(final int file) throws IOException {
        this.dataManager.removeInterestInFile(file);
    }
    
    @Override
    public void recoverRedoItems(final RedoListener listener) throws IOException {
        throw new RuntimeException("Not Implemented..");
    }
    
    @Override
    public StoreLocation storeRedoItem(final Object payload) throws IOException {
        throw new RuntimeException("Not Implemented..");
    }
    
    @Override
    public Marshaller getRedoMarshaller() {
        return this.redoMarshaller;
    }
    
    @Override
    public void setRedoMarshaller(final Marshaller redoMarshaller) {
        this.redoMarshaller = redoMarshaller;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    static {
        FORCE_COMMAND = new ByteSequence(new byte[] { 70, 79, 82, 67, 69 });
    }
    
    private static class StoreLocationFacade implements StoreLocation
    {
        private final Location location;
        
        public StoreLocationFacade(final Location location) {
            this.location = location;
        }
        
        @Override
        public int getFile() {
            return this.location.getDataFileId();
        }
        
        @Override
        public long getOffset() {
            return this.location.getOffset();
        }
        
        @Override
        public int getSize() {
            return this.location.getSize();
        }
        
        public Location getLocation() {
            return this.location;
        }
    }
}
