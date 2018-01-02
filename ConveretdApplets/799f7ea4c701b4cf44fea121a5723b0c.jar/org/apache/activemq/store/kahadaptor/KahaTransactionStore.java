// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.slf4j.LoggerFactory;
import org.apache.activemq.kaha.RuntimeStoreException;
import org.apache.activemq.command.BaseCommand;
import java.util.Iterator;
import org.apache.activemq.command.XATransactionId;
import org.apache.activemq.store.TransactionRecoveryListener;
import org.apache.activemq.command.TransactionId;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.store.ProxyTopicMessageStore;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.command.MessageAck;
import java.io.IOException;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.store.ProxyMessageStore;
import org.apache.activemq.store.MessageStore;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.broker.BrokerService;
import java.util.Map;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerServiceAware;
import org.apache.activemq.store.TransactionStore;

public class KahaTransactionStore implements TransactionStore, BrokerServiceAware
{
    private static final Logger LOG;
    private final Map transactions;
    private final Map prepared;
    private final KahaPersistenceAdapter adaptor;
    private BrokerService brokerService;
    
    KahaTransactionStore(final KahaPersistenceAdapter adaptor, final Map preparedMap) {
        this.transactions = new ConcurrentHashMap();
        this.adaptor = adaptor;
        this.prepared = preparedMap;
    }
    
    public MessageStore proxy(final MessageStore messageStore) {
        return new ProxyMessageStore(messageStore) {
            @Override
            public void addMessage(final ConnectionContext context, final Message send) throws IOException {
                KahaTransactionStore.this.addMessage(this.getDelegate(), send);
            }
            
            @Override
            public void removeMessage(final ConnectionContext context, final MessageAck ack) throws IOException {
                KahaTransactionStore.this.removeMessage(this.getDelegate(), ack);
            }
        };
    }
    
    public TopicMessageStore proxy(final TopicMessageStore messageStore) {
        return new ProxyTopicMessageStore(messageStore) {
            @Override
            public void addMessage(final ConnectionContext context, final Message send) throws IOException {
                KahaTransactionStore.this.addMessage(this.getDelegate(), send);
            }
            
            @Override
            public void removeMessage(final ConnectionContext context, final MessageAck ack) throws IOException {
                KahaTransactionStore.this.removeMessage(this.getDelegate(), ack);
            }
            
            @Override
            public void acknowledge(final ConnectionContext context, final String clientId, final String subscriptionName, final MessageId messageId, final MessageAck ack) throws IOException {
                KahaTransactionStore.this.acknowledge((TopicMessageStore)this.getDelegate(), clientId, subscriptionName, messageId, ack);
            }
        };
    }
    
    @Override
    public void prepare(final TransactionId txid) {
        final KahaTransaction tx = this.getTx(txid);
        if (tx != null) {
            tx.prepare();
            this.prepared.put(txid, tx);
        }
    }
    
    @Override
    public void commit(final TransactionId txid, final boolean wasPrepared, final Runnable before, final Runnable after) throws IOException {
        if (before != null) {
            before.run();
        }
        final KahaTransaction tx = this.getTx(txid);
        if (tx != null) {
            tx.commit(this);
            this.removeTx(txid);
        }
        if (after != null) {
            after.run();
        }
    }
    
    @Override
    public void rollback(final TransactionId txid) {
        final KahaTransaction tx = this.getTx(txid);
        if (tx != null) {
            tx.rollback();
            this.removeTx(txid);
        }
    }
    
    @Override
    public void start() throws Exception {
    }
    
    @Override
    public void stop() throws Exception {
    }
    
    @Override
    public synchronized void recover(final TransactionRecoveryListener listener) throws IOException {
        for (final Map.Entry entry : this.prepared.entrySet()) {
            final XATransactionId xid = entry.getKey();
            final KahaTransaction kt = entry.getValue();
            listener.recover(xid, kt.getMessages(), kt.getAcks());
        }
    }
    
    void addMessage(final MessageStore destination, final Message message) throws IOException {
        try {
            if (message.isInTransaction()) {
                final KahaTransaction tx = this.getOrCreateTx(message.getTransactionId());
                tx.add((KahaMessageStore)destination, message);
            }
            else {
                destination.addMessage(null, message);
            }
        }
        catch (RuntimeStoreException rse) {
            if (rse.getCause() instanceof IOException) {
                this.brokerService.handleIOException((IOException)rse.getCause());
            }
            throw rse;
        }
    }
    
    final void removeMessage(final MessageStore destination, final MessageAck ack) throws IOException {
        try {
            if (ack.isInTransaction()) {
                final KahaTransaction tx = this.getOrCreateTx(ack.getTransactionId());
                tx.add((KahaMessageStore)destination, ack);
            }
            else {
                destination.removeMessage(null, ack);
            }
        }
        catch (RuntimeStoreException rse) {
            if (rse.getCause() instanceof IOException) {
                this.brokerService.handleIOException((IOException)rse.getCause());
            }
            throw rse;
        }
    }
    
    final void acknowledge(final TopicMessageStore destination, final String clientId, final String subscriptionName, final MessageId messageId, final MessageAck ack) throws IOException {
        try {
            if (ack.isInTransaction()) {
                final KahaTransaction tx = this.getOrCreateTx(ack.getTransactionId());
                tx.add((KahaMessageStore)destination, clientId, subscriptionName, messageId, ack);
            }
            else {
                destination.acknowledge(null, clientId, subscriptionName, messageId, ack);
            }
        }
        catch (RuntimeStoreException rse) {
            if (rse.getCause() instanceof IOException) {
                this.brokerService.handleIOException((IOException)rse.getCause());
            }
            throw rse;
        }
    }
    
    protected synchronized KahaTransaction getTx(final TransactionId key) {
        KahaTransaction result = this.transactions.get(key);
        if (result == null) {
            result = this.prepared.get(key);
        }
        return result;
    }
    
    protected synchronized KahaTransaction getOrCreateTx(final TransactionId key) {
        KahaTransaction result = this.transactions.get(key);
        if (result == null) {
            result = new KahaTransaction();
            this.transactions.put(key, result);
        }
        return result;
    }
    
    protected synchronized void removeTx(final TransactionId key) {
        this.transactions.remove(key);
        this.prepared.remove(key);
    }
    
    public void delete() {
        this.transactions.clear();
        this.prepared.clear();
    }
    
    protected MessageStore getStoreById(final Object id) {
        return this.adaptor.retrieveMessageStore(id);
    }
    
    @Override
    public void setBrokerService(final BrokerService brokerService) {
        this.brokerService = brokerService;
    }
    
    static {
        LOG = LoggerFactory.getLogger(KahaTransactionStore.class);
    }
}
