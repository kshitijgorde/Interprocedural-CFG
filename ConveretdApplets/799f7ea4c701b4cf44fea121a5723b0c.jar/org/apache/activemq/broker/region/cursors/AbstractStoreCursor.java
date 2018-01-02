// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.cursors;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.broker.region.MessageReference;
import java.util.Iterator;
import org.apache.activemq.broker.region.Destination;
import org.slf4j.Logger;
import org.apache.activemq.store.MessageRecoveryListener;

public abstract class AbstractStoreCursor extends AbstractPendingMessageCursor implements MessageRecoveryListener
{
    private static final Logger LOG;
    protected final Destination regionDestination;
    private final PendingList batchList;
    private Iterator<MessageReference> iterator;
    protected boolean batchResetNeeded;
    private boolean storeHasMessages;
    protected int size;
    private MessageId lastCachedId;
    private boolean hadSpace;
    
    protected AbstractStoreCursor(final Destination destination) {
        super(destination != null && destination.isPrioritizedMessages());
        this.iterator = null;
        this.batchResetNeeded = true;
        this.storeHasMessages = false;
        this.hadSpace = false;
        this.regionDestination = destination;
        if (this.prioritizedMessages) {
            this.batchList = new PrioritizedPendingList();
        }
        else {
            this.batchList = new OrderedPendingList();
        }
    }
    
    @Override
    public final synchronized void start() throws Exception {
        if (!this.isStarted()) {
            this.clear();
            super.start();
            this.resetBatch();
            this.size = this.getStoreSize();
            this.storeHasMessages = (this.size > 0);
            this.setCacheEnabled(!this.storeHasMessages && this.useCache);
        }
    }
    
    @Override
    public final synchronized void stop() throws Exception {
        this.resetBatch();
        super.stop();
        this.gc();
    }
    
    @Override
    public final boolean recoverMessage(final Message message) throws Exception {
        return this.recoverMessage(message, false);
    }
    
    public synchronized boolean recoverMessage(final Message message, final boolean cached) throws Exception {
        boolean recovered = false;
        if (this.recordUniqueId(message.getMessageId())) {
            if (!cached) {
                message.setRegionDestination(this.regionDestination);
                if (message.getMemoryUsage() == null) {
                    message.setMemoryUsage(this.getSystemUsage().getMemoryUsage());
                }
            }
            message.incrementReferenceCount();
            this.batchList.addMessageLast(message);
            this.clearIterator(true);
            recovered = true;
            this.storeHasMessages = true;
        }
        else if (AbstractStoreCursor.LOG.isTraceEnabled()) {
            AbstractStoreCursor.LOG.trace(this + " - cursor got duplicate: " + message.getMessageId() + ", " + message.getPriority());
        }
        return recovered;
    }
    
    @Override
    public final void reset() {
        if (this.batchList.isEmpty()) {
            try {
                this.fillBatch();
            }
            catch (Exception e) {
                AbstractStoreCursor.LOG.error(this + " - Failed to fill batch", e);
                throw new RuntimeException(e);
            }
        }
        this.clearIterator(true);
        this.size();
    }
    
    @Override
    public synchronized void release() {
        this.clearIterator(false);
    }
    
    private synchronized void clearIterator(final boolean ensureIterator) {
        final boolean haveIterator = this.iterator != null;
        this.iterator = null;
        if (haveIterator && ensureIterator) {
            this.ensureIterator();
        }
    }
    
    private synchronized void ensureIterator() {
        if (this.iterator == null) {
            this.iterator = this.batchList.iterator();
        }
    }
    
    public final void finished() {
    }
    
    @Override
    public final synchronized boolean hasNext() {
        if (this.batchList.isEmpty()) {
            try {
                this.fillBatch();
            }
            catch (Exception e) {
                AbstractStoreCursor.LOG.error(this + " - Failed to fill batch", e);
                throw new RuntimeException(e);
            }
        }
        this.ensureIterator();
        return this.iterator.hasNext();
    }
    
    @Override
    public final synchronized MessageReference next() {
        MessageReference result = null;
        if (!this.batchList.isEmpty() && this.iterator.hasNext()) {
            result = this.iterator.next();
        }
        if ((this.last = result) != null) {
            result.incrementReferenceCount();
        }
        return result;
    }
    
    @Override
    public final synchronized void addMessageLast(final MessageReference node) throws Exception {
        if (this.hasSpace()) {
            if (!this.isCacheEnabled() && this.size == 0 && this.isStarted() && this.useCache) {
                if (AbstractStoreCursor.LOG.isTraceEnabled()) {
                    AbstractStoreCursor.LOG.trace(this + " - enabling cache for empty store " + node.getMessageId());
                }
                this.setCacheEnabled(true);
            }
            if (this.isCacheEnabled()) {
                this.recoverMessage(node.getMessage(), true);
                this.lastCachedId = node.getMessageId();
            }
        }
        else if (this.isCacheEnabled()) {
            this.setCacheEnabled(false);
            if (this.lastCachedId != null) {
                if (AbstractStoreCursor.LOG.isTraceEnabled()) {
                    AbstractStoreCursor.LOG.trace(this + " - disabling cache" + ", lastCachedId: " + this.lastCachedId + " current node Id: " + node.getMessageId());
                }
                this.setBatch(this.lastCachedId);
                this.lastCachedId = null;
            }
        }
        this.storeHasMessages = true;
        ++this.size;
    }
    
    protected void setBatch(final MessageId messageId) throws Exception {
    }
    
    @Override
    public final synchronized void addMessageFirst(final MessageReference node) throws Exception {
        this.setCacheEnabled(false);
        ++this.size;
    }
    
    @Override
    public final synchronized void remove() {
        --this.size;
        if (this.iterator != null) {
            this.iterator.remove();
        }
        if (this.last != null) {
            this.last.decrementReferenceCount();
        }
    }
    
    @Override
    public final synchronized void remove(final MessageReference node) {
        --this.size;
        this.setCacheEnabled(false);
        this.batchList.remove(node);
    }
    
    @Override
    public final synchronized void clear() {
        this.gc();
    }
    
    @Override
    public synchronized void gc() {
        for (final MessageReference msg : this.batchList) {
            this.rollback(msg.getMessageId());
            msg.decrementReferenceCount();
        }
        this.batchList.clear();
        this.clearIterator(false);
        this.batchResetNeeded = true;
        this.setCacheEnabled(false);
    }
    
    @Override
    public boolean hasSpace() {
        return this.hadSpace = super.hasSpace();
    }
    
    @Override
    protected final synchronized void fillBatch() {
        if (AbstractStoreCursor.LOG.isTraceEnabled()) {
            AbstractStoreCursor.LOG.trace(this + " - fillBatch");
        }
        if (this.batchResetNeeded) {
            this.resetBatch();
            this.batchResetNeeded = false;
        }
        if (this.batchList.isEmpty() && this.storeHasMessages && this.size > 0) {
            this.storeHasMessages = false;
            try {
                this.doFillBatch();
            }
            catch (Exception e) {
                AbstractStoreCursor.LOG.error(this + " - Failed to fill batch", e);
                throw new RuntimeException(e);
            }
            if (!this.batchList.isEmpty() || !this.hadSpace) {
                this.storeHasMessages = true;
            }
        }
    }
    
    @Override
    public final synchronized boolean isEmpty() {
        return this.size == 0;
    }
    
    @Override
    public final synchronized boolean hasMessagesBufferedToDeliver() {
        return !this.batchList.isEmpty();
    }
    
    @Override
    public final synchronized int size() {
        if (this.size < 0) {
            this.size = this.getStoreSize();
        }
        return this.size;
    }
    
    @Override
    public String toString() {
        return this.regionDestination.getActiveMQDestination().getPhysicalName() + ",batchResetNeeded=" + this.batchResetNeeded + ",storeHasMessages=" + this.storeHasMessages + ",size=" + this.size + ",cacheEnabled=" + this.isCacheEnabled();
    }
    
    protected abstract void doFillBatch() throws Exception;
    
    protected abstract void resetBatch();
    
    protected abstract int getStoreSize();
    
    protected abstract boolean isStoreEmpty();
    
    static {
        LOG = LoggerFactory.getLogger(AbstractStoreCursor.class);
    }
}
