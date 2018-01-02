// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.command.store.amq.reader;

import org.apache.activemq.util.ByteSequence;
import org.apache.activemq.command.DataStructure;
import org.apache.activemq.kaha.impl.async.Location;
import java.util.Iterator;
import org.apache.activemq.selector.SelectorParser;
import org.apache.activemq.openwire.OpenWireFormat;
import javax.jms.InvalidSelectorException;
import java.util.Collection;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.activemq.filter.BooleanExpression;
import java.io.File;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.kaha.impl.async.AsyncDataManager;
import javax.jms.Message;

public class AMQReader implements Iterable<Message>
{
    private AsyncDataManager dataManager;
    private WireFormat wireFormat;
    private File file;
    private BooleanExpression expression;
    
    public static Set<File> listDataFiles(final File directory) throws IOException {
        final Set<File> result = new HashSet<File>();
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            throw new IOException("Invalid Directory " + directory);
        }
        final AsyncDataManager dataManager = new AsyncDataManager();
        dataManager.setDirectory(directory);
        dataManager.start();
        final Set<File> set = dataManager.getFiles();
        if (set != null) {
            result.addAll(set);
        }
        dataManager.close();
        return result;
    }
    
    public AMQReader(final File file) throws InvalidSelectorException, IOException {
        this(file, null);
    }
    
    public AMQReader(final File file, final String selector) throws IOException, InvalidSelectorException {
        this.wireFormat = new OpenWireFormat();
        final String str = (selector != null) ? selector.trim() : null;
        if (str != null && str.length() > 0) {
            this.expression = SelectorParser.parse(str);
        }
        (this.dataManager = new AsyncDataManager()).setArchiveDataLogs(false);
        if (file.isDirectory()) {
            this.dataManager.setDirectory(file);
        }
        else {
            this.dataManager.setDirectory(file.getParentFile());
            this.dataManager.setDirectoryArchive(file);
            this.file = file;
        }
        this.dataManager.start();
    }
    
    @Override
    public Iterator<Message> iterator() {
        return new AMQIterator(this, this.expression);
    }
    
    protected MessageLocation getNextMessage(final MessageLocation lastLocation) throws IllegalStateException, IOException {
        if (this.file != null) {
            return this.getInternalNextMessage(this.file, lastLocation);
        }
        return this.getInternalNextMessage(lastLocation);
    }
    
    private MessageLocation getInternalNextMessage(final MessageLocation lastLocation) throws IllegalStateException, IOException {
        return this.getInternalNextMessage(null, lastLocation);
    }
    
    private MessageLocation getInternalNextMessage(final File file, final MessageLocation lastLocation) throws IllegalStateException, IOException {
        MessageLocation result = lastLocation;
        if (result != null) {
            result.setMessage(null);
        }
        Message message = null;
        Location pos = (lastLocation != null) ? lastLocation.getLocation() : null;
        while ((pos = this.getNextLocation(file, pos)) != null) {
            message = this.getMessage(pos);
            if (message != null) {
                if (result == null) {
                    result = new MessageLocation();
                }
                result.setMessage(message);
                break;
            }
        }
        result.setLocation(pos);
        if (pos == null && message == null) {
            result = null;
        }
        else {
            result.setLocation(pos);
        }
        return result;
    }
    
    private Location getNextLocation(final File file, final Location last) throws IllegalStateException, IOException {
        if (file != null) {
            return this.dataManager.getNextLocation(file, last, true);
        }
        return this.dataManager.getNextLocation(last);
    }
    
    private Message getMessage(final Location location) throws IOException {
        final ByteSequence data = this.dataManager.read(location);
        final DataStructure c = (DataStructure)this.wireFormat.unmarshal(data);
        if (c instanceof Message) {
            return (Message)c;
        }
        return null;
    }
}
