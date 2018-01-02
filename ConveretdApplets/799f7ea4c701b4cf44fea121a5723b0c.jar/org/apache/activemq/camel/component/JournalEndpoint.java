// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.camel.component;

import org.slf4j.LoggerFactory;
import org.apache.camel.CamelExchangeException;
import org.apache.camel.impl.DefaultProducer;
import org.apache.camel.Producer;
import org.apache.camel.Exchange;
import org.apache.activemq.util.ByteSequence;
import java.io.InterruptedIOException;
import org.apache.camel.RuntimeCamelException;
import java.io.IOException;
import org.apache.camel.Endpoint;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.activemq.kaha.impl.async.Location;
import org.apache.activemq.kaha.impl.async.AsyncDataManager;
import org.apache.camel.impl.DefaultConsumer;
import java.util.concurrent.atomic.AtomicReference;
import java.io.File;
import org.slf4j.Logger;
import org.apache.camel.impl.DefaultEndpoint;

public class JournalEndpoint extends DefaultEndpoint
{
    private static final transient Logger LOG;
    private final File directory;
    private final AtomicReference<DefaultConsumer> consumer;
    private final Object activationMutex;
    private int referenceCount;
    private AsyncDataManager dataManager;
    private Thread thread;
    private Location lastReadLocation;
    private long idleDelay;
    private boolean syncProduce;
    private boolean syncConsume;
    
    public JournalEndpoint(final String uri, final JournalComponent journalComponent, final File directory) {
        super(uri, journalComponent.getCamelContext());
        this.consumer = new AtomicReference<DefaultConsumer>();
        this.activationMutex = new Object();
        this.idleDelay = 1000L;
        this.syncProduce = true;
        this.directory = directory;
    }
    
    public JournalEndpoint(final String endpointUri, final File directory) {
        super(endpointUri);
        this.consumer = new AtomicReference<DefaultConsumer>();
        this.activationMutex = new Object();
        this.idleDelay = 1000L;
        this.syncProduce = true;
        this.directory = directory;
    }
    
    public boolean isSingleton() {
        return true;
    }
    
    public File getDirectory() {
        return this.directory;
    }
    
    public Consumer createConsumer(final Processor processor) throws Exception {
        return (Consumer)new DefaultConsumer(this, processor) {
            public void start() throws Exception {
                super.start();
                JournalEndpoint.this.activateConsumer(this);
            }
            
            public void stop() throws Exception {
                JournalEndpoint.this.deactivateConsumer(this);
                super.stop();
            }
        };
    }
    
    protected void decrementReference() throws IOException {
        synchronized (this.activationMutex) {
            --this.referenceCount;
            if (this.referenceCount == 0) {
                JournalEndpoint.LOG.debug("Closing data manager: " + this.directory);
                JournalEndpoint.LOG.debug("Last mark at: " + this.lastReadLocation);
                this.dataManager.close();
                this.dataManager = null;
            }
        }
    }
    
    protected void incrementReference() throws IOException {
        synchronized (this.activationMutex) {
            ++this.referenceCount;
            if (this.referenceCount == 1) {
                JournalEndpoint.LOG.debug("Opening data manager: " + this.directory);
                (this.dataManager = new AsyncDataManager()).setDirectory(this.directory);
                this.dataManager.start();
                this.lastReadLocation = this.dataManager.getMark();
                JournalEndpoint.LOG.debug("Last mark at: " + this.lastReadLocation);
            }
        }
    }
    
    protected void deactivateConsumer(final DefaultConsumer consumer) throws IOException {
        synchronized (this.activationMutex) {
            if (this.consumer.get() != consumer) {
                throw new RuntimeCamelException("Consumer was not active.");
            }
            this.consumer.set(null);
            try {
                this.thread.join();
            }
            catch (InterruptedException e) {
                throw new InterruptedIOException();
            }
            this.decrementReference();
        }
    }
    
    protected void activateConsumer(final DefaultConsumer consumer) throws IOException {
        synchronized (this.activationMutex) {
            if (this.consumer.get() != null) {
                throw new RuntimeCamelException("Consumer already active: journal endpoints only support 1 active consumer");
            }
            this.incrementReference();
            this.consumer.set(consumer);
            (this.thread = new Thread() {
                @Override
                public void run() {
                    JournalEndpoint.this.dispatchToConsumer();
                }
            }).setName("Dipatch thread: " + this.getEndpointUri());
            this.thread.setDaemon(true);
            this.thread.start();
        }
    }
    
    protected void dispatchToConsumer() {
        try {
            DefaultConsumer consumer;
            while ((consumer = this.consumer.get()) != null) {
                final Location location = this.dataManager.getNextLocation(this.lastReadLocation);
                if (location != null) {
                    final ByteSequence read = this.dataManager.read(location);
                    final Exchange exchange = this.createExchange();
                    exchange.getIn().setBody((Object)read);
                    exchange.getIn().setHeader("journal", (Object)this.getEndpointUri());
                    exchange.getIn().setHeader("location", (Object)location);
                    consumer.getProcessor().process(exchange);
                    if (JournalEndpoint.LOG.isDebugEnabled()) {
                        JournalEndpoint.LOG.debug("Consumed record at: " + location);
                    }
                    this.dataManager.setMark(location, this.syncConsume);
                    this.lastReadLocation = location;
                }
                else {
                    JournalEndpoint.LOG.debug("Sleeping due to no records being available.");
                    Thread.sleep(this.idleDelay);
                }
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }
    
    public Producer createProducer() throws Exception {
        return (Producer)new DefaultProducer(this) {
            public void process(final Exchange exchange) throws Exception {
                JournalEndpoint.this.incrementReference();
                try {
                    ByteSequence body = (ByteSequence)exchange.getIn().getBody((Class)ByteSequence.class);
                    if (body == null) {
                        final byte[] bytes = (byte[])exchange.getIn().getBody((Class)byte[].class);
                        if (bytes != null) {
                            body = new ByteSequence(bytes);
                        }
                    }
                    if (body == null) {
                        throw new CamelExchangeException("In body message could not be converted to a ByteSequence or a byte array.", exchange);
                    }
                    JournalEndpoint.this.dataManager.write(body, JournalEndpoint.this.syncProduce);
                }
                finally {
                    JournalEndpoint.this.decrementReference();
                }
            }
        };
    }
    
    public boolean isSyncConsume() {
        return this.syncConsume;
    }
    
    public void setSyncConsume(final boolean syncConsume) {
        this.syncConsume = syncConsume;
    }
    
    public boolean isSyncProduce() {
        return this.syncProduce;
    }
    
    public void setSyncProduce(final boolean syncProduce) {
        this.syncProduce = syncProduce;
    }
    
    boolean isOpen() {
        synchronized (this.activationMutex) {
            return this.referenceCount > 0;
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(JournalEndpoint.class);
    }
}
