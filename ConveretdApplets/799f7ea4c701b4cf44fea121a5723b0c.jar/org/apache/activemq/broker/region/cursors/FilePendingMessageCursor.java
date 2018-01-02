// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.cursors;

import org.apache.activemq.store.kahadb.plist.PListEntry;
import org.slf4j.LoggerFactory;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.filter.NonCachedMessageEvaluationContext;
import java.util.Collection;
import org.apache.activemq.usage.Usage;
import org.apache.activemq.usage.SystemUsage;
import java.io.IOException;
import org.apache.kahadb.util.ByteSequence;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.region.QueueMessageReference;
import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.wireformat.WireFormat;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.broker.region.Destination;
import java.util.Iterator;
import org.apache.activemq.store.kahadb.plist.PList;
import org.apache.activemq.broker.region.MessageReference;
import java.util.LinkedList;
import org.apache.activemq.store.kahadb.plist.PListStore;
import org.apache.activemq.broker.Broker;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.apache.activemq.usage.UsageListener;

public class FilePendingMessageCursor extends AbstractPendingMessageCursor implements UsageListener
{
    static final Logger LOG;
    private static final AtomicLong NAME_COUNT;
    protected Broker broker;
    private final PListStore store;
    private final String name;
    private LinkedList<MessageReference> memoryList;
    private PList diskList;
    private Iterator<MessageReference> iter;
    private Destination regionDestination;
    private boolean iterating;
    private boolean flushRequired;
    private final AtomicBoolean started;
    private final WireFormat wireFormat;
    
    public FilePendingMessageCursor(final Broker broker, final String name, final boolean prioritizedMessages) {
        super(prioritizedMessages);
        this.memoryList = new LinkedList<MessageReference>();
        this.started = new AtomicBoolean();
        this.wireFormat = new OpenWireFormat();
        this.broker = broker;
        this.store = broker.getTempDataStore();
        this.name = FilePendingMessageCursor.NAME_COUNT.incrementAndGet() + "_" + name;
    }
    
    @Override
    public void start() throws Exception {
        if (this.started.compareAndSet(false, true)) {
            super.start();
            if (this.systemUsage != null) {
                this.systemUsage.getMemoryUsage().addUsageListener(this);
            }
        }
    }
    
    @Override
    public void stop() throws Exception {
        if (this.started.compareAndSet(true, false)) {
            super.stop();
            if (this.systemUsage != null) {
                this.systemUsage.getMemoryUsage().removeUsageListener(this);
            }
        }
    }
    
    @Override
    public synchronized boolean isEmpty() {
        if (this.memoryList.isEmpty() && this.isDiskListEmpty()) {
            return true;
        }
        final Iterator<MessageReference> iterator = this.memoryList.iterator();
        while (iterator.hasNext()) {
            final MessageReference node = iterator.next();
            if (node == QueueMessageReference.NULL_MESSAGE) {
                continue;
            }
            if (!node.isDropped()) {
                return false;
            }
            iterator.remove();
        }
        return this.isDiskListEmpty();
    }
    
    @Override
    public synchronized void reset() {
        this.iterating = true;
        this.last = null;
        if (this.isDiskListEmpty()) {
            this.iter = this.memoryList.iterator();
        }
        else {
            this.iter = new DiskIterator();
        }
    }
    
    @Override
    public synchronized void release() {
        this.iterating = false;
        if (this.flushRequired) {
            this.flushRequired = false;
            this.flushToDisk();
        }
    }
    
    @Override
    public synchronized void destroy() throws Exception {
        this.stop();
        for (final Message node : this.memoryList) {
            node.decrementReferenceCount();
        }
        this.memoryList.clear();
        this.destroyDiskList();
    }
    
    private void destroyDiskList() throws Exception {
        if (!this.isDiskListEmpty()) {
            this.store.removePList(this.name);
        }
    }
    
    @Override
    public synchronized LinkedList<MessageReference> pageInList(final int maxItems) {
        final LinkedList<MessageReference> result = new LinkedList<MessageReference>();
        int count = 0;
        for (Iterator<MessageReference> i = this.memoryList.iterator(); i.hasNext() && count < maxItems; ++count) {
            final MessageReference ref = i.next();
            ref.incrementReferenceCount();
            result.add(ref);
        }
        if (count < maxItems && !this.isDiskListEmpty()) {
            for (Iterator<MessageReference> i = new DiskIterator(); i.hasNext() && count < maxItems; ++count) {
                final Message message = i.next();
                message.setRegionDestination(this.regionDestination);
                message.setMemoryUsage(this.getSystemUsage().getMemoryUsage());
                message.incrementReferenceCount();
                result.add(message);
            }
        }
        return result;
    }
    
    @Override
    public synchronized void addMessageLast(final MessageReference node) throws Exception {
        this.tryAddMessageLast(node, 0L);
    }
    
    @Override
    public synchronized boolean tryAddMessageLast(final MessageReference node, final long maxWaitTime) throws Exception {
        if (!node.isExpired()) {
            try {
                this.regionDestination = node.getMessage().getRegionDestination();
                if (this.isDiskListEmpty() && (this.hasSpace() || this.store == null)) {
                    this.memoryList.add(node);
                    node.incrementReferenceCount();
                    this.setCacheEnabled(true);
                    return true;
                }
                if (!this.hasSpace() && this.isDiskListEmpty()) {
                    this.expireOldMessages();
                    if (this.hasSpace()) {
                        this.memoryList.add(node);
                        node.incrementReferenceCount();
                        return true;
                    }
                    this.flushToDisk();
                }
                if (this.systemUsage.getTempUsage().waitForSpace(maxWaitTime)) {
                    final ByteSequence bs = this.getByteSequence(node.getMessage());
                    this.getDiskList().addLast(node.getMessageId().toString(), bs);
                    return true;
                }
                return false;
            }
            catch (Exception e) {
                FilePendingMessageCursor.LOG.error("Caught an Exception adding a message: " + node + " first to FilePendingMessageCursor ", e);
                throw new RuntimeException(e);
            }
        }
        this.discard(node);
        return true;
    }
    
    @Override
    public synchronized void addMessageFirst(final MessageReference node) {
        if (!node.isExpired()) {
            try {
                this.regionDestination = node.getMessage().getRegionDestination();
                if (this.isDiskListEmpty() && this.hasSpace()) {
                    this.memoryList.addFirst(node);
                    node.incrementReferenceCount();
                    this.setCacheEnabled(true);
                    return;
                }
                if (!this.hasSpace() && this.isDiskListEmpty()) {
                    this.expireOldMessages();
                    if (this.hasSpace()) {
                        this.memoryList.addFirst(node);
                        node.incrementReferenceCount();
                        return;
                    }
                    this.flushToDisk();
                }
                this.systemUsage.getTempUsage().waitForSpace();
                node.decrementReferenceCount();
                final ByteSequence bs = this.getByteSequence(node.getMessage());
                this.getDiskList().addFirst(node.getMessageId().toString(), bs);
                return;
            }
            catch (Exception e) {
                FilePendingMessageCursor.LOG.error("Caught an Exception adding a message: " + node + " first to FilePendingMessageCursor ", e);
                throw new RuntimeException(e);
            }
        }
        this.discard(node);
    }
    
    @Override
    public synchronized boolean hasNext() {
        return this.iter.hasNext();
    }
    
    @Override
    public synchronized MessageReference next() {
        final Message message = this.iter.next();
        this.last = message;
        if (!this.isDiskListEmpty()) {
            message.setRegionDestination(this.regionDestination);
            message.setMemoryUsage(this.getSystemUsage().getMemoryUsage());
        }
        message.incrementReferenceCount();
        return message;
    }
    
    @Override
    public synchronized void remove() {
        this.iter.remove();
        if (this.last != null) {
            this.last.decrementReferenceCount();
        }
    }
    
    @Override
    public synchronized void remove(final MessageReference node) {
        if (this.memoryList.remove(node)) {
            node.decrementReferenceCount();
        }
        if (!this.isDiskListEmpty()) {
            try {
                this.getDiskList().remove(node.getMessageId().toString());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @Override
    public synchronized int size() {
        return this.memoryList.size() + (this.isDiskListEmpty() ? 0 : this.getDiskList().size());
    }
    
    @Override
    public synchronized void clear() {
        this.memoryList.clear();
        if (!this.isDiskListEmpty()) {
            try {
                this.getDiskList().destroy();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.last = null;
    }
    
    @Override
    public synchronized boolean isFull() {
        return super.isFull() || (this.systemUsage != null && this.systemUsage.getTempUsage().isFull());
    }
    
    @Override
    public boolean hasMessagesBufferedToDeliver() {
        return !this.isEmpty();
    }
    
    @Override
    public void setSystemUsage(final SystemUsage usageManager) {
        super.setSystemUsage(usageManager);
    }
    
    @Override
    public void onUsageChanged(final Usage usage, final int oldPercentUsage, final int newPercentUsage) {
        if (newPercentUsage >= this.getMemoryUsageHighWaterMark()) {
            synchronized (this) {
                this.flushRequired = true;
                if (!this.iterating) {
                    this.expireOldMessages();
                    if (!this.hasSpace()) {
                        this.flushToDisk();
                        this.flushRequired = false;
                    }
                }
            }
        }
    }
    
    @Override
    public boolean isTransient() {
        return true;
    }
    
    protected boolean isSpaceInMemoryList() {
        return this.hasSpace() && this.isDiskListEmpty();
    }
    
    protected synchronized void expireOldMessages() {
        if (!this.memoryList.isEmpty()) {
            final LinkedList<MessageReference> tmpList = new LinkedList<MessageReference>(this.memoryList);
            this.memoryList = new LinkedList<MessageReference>();
            while (!tmpList.isEmpty()) {
                final MessageReference node = tmpList.removeFirst();
                if (node.isExpired()) {
                    this.discard(node);
                }
                else {
                    this.memoryList.add(node);
                }
            }
        }
    }
    
    protected synchronized void flushToDisk() {
        if (!this.memoryList.isEmpty()) {
            while (!this.memoryList.isEmpty()) {
                final MessageReference node = this.memoryList.removeFirst();
                node.decrementReferenceCount();
                try {
                    final ByteSequence bs = this.getByteSequence(node.getMessage());
                    this.getDiskList().addLast(node.getMessageId().toString(), bs);
                }
                catch (IOException e) {
                    FilePendingMessageCursor.LOG.error("Failed to write to disk list", e);
                    throw new RuntimeException(e);
                }
            }
            this.memoryList.clear();
            this.setCacheEnabled(false);
        }
    }
    
    protected boolean isDiskListEmpty() {
        return this.diskList == null || this.diskList.isEmpty();
    }
    
    protected PList getDiskList() {
        if (this.diskList == null) {
            try {
                this.diskList = this.store.getPList(this.name);
            }
            catch (Exception e) {
                FilePendingMessageCursor.LOG.error("Caught an IO Exception getting the DiskList " + this.name, e);
                throw new RuntimeException(e);
            }
        }
        return this.diskList;
    }
    
    protected void discard(final MessageReference message) {
        message.decrementReferenceCount();
        if (FilePendingMessageCursor.LOG.isDebugEnabled()) {
            FilePendingMessageCursor.LOG.debug("Discarding message " + message);
        }
        final ConnectionContext ctx = new ConnectionContext(new NonCachedMessageEvaluationContext());
        ctx.setBroker(this.broker);
        this.broker.getRoot().sendToDeadLetterQueue(ctx, message, null);
    }
    
    protected ByteSequence getByteSequence(final Message message) throws IOException {
        final org.apache.activemq.util.ByteSequence packet = this.wireFormat.marshal(message);
        return new ByteSequence(packet.data, packet.offset, packet.length);
    }
    
    protected Message getMessage(final ByteSequence bs) throws IOException {
        final org.apache.activemq.util.ByteSequence packet = new org.apache.activemq.util.ByteSequence(bs.getData(), bs.getOffset(), bs.getLength());
        return (Message)this.wireFormat.unmarshal(packet);
    }
    
    static {
        LOG = LoggerFactory.getLogger(FilePendingMessageCursor.class);
        NAME_COUNT = new AtomicLong();
    }
    
    final class DiskIterator implements Iterator<MessageReference>
    {
        private PListEntry next;
        private PListEntry current;
        PList list;
        
        DiskIterator() {
            this.next = null;
            this.current = null;
            try {
                synchronized (this.list = FilePendingMessageCursor.this.getDiskList()) {
                    this.current = this.list.getFirst();
                    this.next = this.current;
                }
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        @Override
        public boolean hasNext() {
            return this.next != null;
        }
        
        @Override
        public MessageReference next() {
            this.current = this.next;
            try {
                final ByteSequence bs = this.current.getByteSequence();
                synchronized (this.list) {
                    this.current = this.list.refresh(this.current);
                    this.next = this.list.getNext(this.current);
                }
                return FilePendingMessageCursor.this.getMessage(bs);
            }
            catch (IOException e) {
                FilePendingMessageCursor.LOG.error("I/O error", e);
                throw new RuntimeException(e);
            }
        }
        
        @Override
        public void remove() {
            try {
                synchronized (this.list) {
                    this.current = this.list.refresh(this.current);
                    this.list.remove(this.current);
                }
            }
            catch (IOException e) {
                FilePendingMessageCursor.LOG.error("I/O error", e);
                throw new RuntimeException(e);
            }
        }
    }
}
