// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.memory;

import java.util.concurrent.CountedCompleter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Spliterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.LockSupport;
import sun.misc.Contended;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.ToLongBiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.ToDoubleBiFunction;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Enumeration;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.Collection;
import java.util.Map;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import sun.misc.Unsafe;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.activemq.command.XATransactionId;
import org.apache.activemq.store.TransactionRecoveryListener;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.store.ProxyTopicMessageStore;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.store.AbstractMessageStore;
import java.util.concurrent.Future;
import java.io.IOException;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.store.ProxyMessageStore;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.command.TransactionId;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.store.TransactionStore;

public class MemoryTransactionStore implements TransactionStore
{
    ConcurrentHashMap<Object, Tx> inflightTransactions;
    ConcurrentHashMap<TransactionId, Tx> preparedTransactions;
    final PersistenceAdapter persistenceAdapter;
    private boolean doingRecover;
    
    public MemoryTransactionStore(final PersistenceAdapter persistenceAdapter) {
        this.inflightTransactions = new ConcurrentHashMap<Object, Tx>();
        this.preparedTransactions = new ConcurrentHashMap<TransactionId, Tx>();
        this.persistenceAdapter = persistenceAdapter;
    }
    
    public MessageStore proxy(final MessageStore messageStore) {
        return new ProxyMessageStore(messageStore) {
            @Override
            public void addMessage(final ConnectionContext context, final Message send) throws IOException {
                MemoryTransactionStore.this.addMessage(this.getDelegate(), send);
            }
            
            @Override
            public Future<Object> asyncAddQueueMessage(final ConnectionContext context, final Message message) throws IOException {
                MemoryTransactionStore.this.addMessage(this.getDelegate(), message);
                return AbstractMessageStore.FUTURE;
            }
            
            @Override
            public void removeMessage(final ConnectionContext context, final MessageAck ack) throws IOException {
                MemoryTransactionStore.this.removeMessage(this.getDelegate(), ack);
            }
            
            @Override
            public void removeAsyncMessage(final ConnectionContext context, final MessageAck ack) throws IOException {
                MemoryTransactionStore.this.removeMessage(this.getDelegate(), ack);
            }
        };
    }
    
    public TopicMessageStore proxy(final TopicMessageStore messageStore) {
        return new ProxyTopicMessageStore(messageStore) {
            @Override
            public void addMessage(final ConnectionContext context, final Message send) throws IOException {
                MemoryTransactionStore.this.addMessage(this.getDelegate(), send);
            }
            
            @Override
            public Future<Object> asyncAddTopicMessage(final ConnectionContext context, final Message message) throws IOException {
                MemoryTransactionStore.this.addMessage(this.getDelegate(), message);
                return AbstractMessageStore.FUTURE;
            }
            
            @Override
            public void removeMessage(final ConnectionContext context, final MessageAck ack) throws IOException {
                MemoryTransactionStore.this.removeMessage(this.getDelegate(), ack);
            }
            
            @Override
            public void removeAsyncMessage(final ConnectionContext context, final MessageAck ack) throws IOException {
                MemoryTransactionStore.this.removeMessage(this.getDelegate(), ack);
            }
            
            @Override
            public void acknowledge(final ConnectionContext context, final String clientId, final String subscriptionName, final MessageId messageId, final MessageAck ack) throws IOException {
                MemoryTransactionStore.this.acknowledge((TopicMessageStore)this.getDelegate(), clientId, subscriptionName, messageId, ack);
            }
        };
    }
    
    @Override
    public void prepare(final TransactionId txid) {
        final Tx tx = this.inflightTransactions.remove(txid);
        if (tx == null) {
            return;
        }
        this.preparedTransactions.put(txid, tx);
    }
    
    public Tx getTx(final Object txid) {
        Tx tx = this.inflightTransactions.get(txid);
        if (tx == null) {
            tx = new Tx();
            this.inflightTransactions.put(txid, tx);
        }
        return tx;
    }
    
    @Override
    public void commit(final TransactionId txid, final boolean wasPrepared, final Runnable preCommit, final Runnable postCommit) throws IOException {
        if (preCommit != null) {
            preCommit.run();
        }
        Tx tx;
        if (wasPrepared) {
            tx = this.preparedTransactions.remove(txid);
        }
        else {
            tx = this.inflightTransactions.remove(txid);
        }
        if (tx == null) {
            if (postCommit != null) {
                postCommit.run();
            }
            return;
        }
        synchronized (this) {
            tx.commit();
            if (postCommit != null) {
                postCommit.run();
            }
        }
    }
    
    @Override
    public void rollback(final TransactionId txid) {
        this.preparedTransactions.remove(txid);
        this.inflightTransactions.remove(txid);
    }
    
    @Override
    public void start() throws Exception {
    }
    
    @Override
    public void stop() throws Exception {
    }
    
    @Override
    public synchronized void recover(final TransactionRecoveryListener listener) throws IOException {
        this.inflightTransactions.clear();
        this.doingRecover = true;
        try {
            for (final Object txid : this.preparedTransactions.keySet()) {
                final Tx tx = this.preparedTransactions.get(txid);
                listener.recover((XATransactionId)txid, tx.getMessages(), tx.getAcks());
            }
        }
        finally {
            this.doingRecover = false;
        }
    }
    
    void addMessage(final MessageStore destination, final Message message) throws IOException {
        if (this.doingRecover) {
            return;
        }
        if (message.getTransactionId() != null) {
            final Tx tx = this.getTx(message.getTransactionId());
            tx.add(new AddMessageCommand() {
                @Override
                public Message getMessage() {
                    return message;
                }
                
                @Override
                public void run(final ConnectionContext ctx) throws IOException {
                    destination.addMessage(ctx, message);
                }
            });
        }
        else {
            destination.addMessage(null, message);
        }
    }
    
    final void removeMessage(final MessageStore destination, final MessageAck ack) throws IOException {
        if (this.doingRecover) {
            return;
        }
        if (ack.isInTransaction()) {
            final Tx tx = this.getTx(ack.getTransactionId());
            tx.add(new RemoveMessageCommand() {
                @Override
                public MessageAck getMessageAck() {
                    return ack;
                }
                
                @Override
                public void run(final ConnectionContext ctx) throws IOException {
                    destination.removeMessage(ctx, ack);
                }
            });
        }
        else {
            destination.removeMessage(null, ack);
        }
    }
    
    final void acknowledge(final TopicMessageStore destination, final String clientId, final String subscriptionName, final MessageId messageId, final MessageAck ack) throws IOException {
        if (this.doingRecover) {
            return;
        }
        if (ack.isInTransaction()) {
            final Tx tx = this.getTx(ack.getTransactionId());
            tx.add(new RemoveMessageCommand() {
                @Override
                public MessageAck getMessageAck() {
                    return ack;
                }
                
                @Override
                public void run(final ConnectionContext ctx) throws IOException {
                    destination.acknowledge(ctx, clientId, subscriptionName, messageId, ack);
                }
            });
        }
        else {
            destination.acknowledge(null, clientId, subscriptionName, messageId, ack);
        }
    }
    
    public void delete() {
        this.inflightTransactions.clear();
        this.preparedTransactions.clear();
        this.doingRecover = false;
    }
    
    public class Tx
    {
        private final ArrayList<AddMessageCommand> messages;
        private final ArrayList<RemoveMessageCommand> acks;
        
        public Tx() {
            this.messages = new ArrayList<AddMessageCommand>();
            this.acks = new ArrayList<RemoveMessageCommand>();
        }
        
        public void add(final AddMessageCommand msg) {
            this.messages.add(msg);
        }
        
        public void add(final RemoveMessageCommand ack) {
            this.acks.add(ack);
        }
        
        public Message[] getMessages() {
            final Message[] rc = new Message[this.messages.size()];
            int count = 0;
            for (final AddMessageCommand cmd : this.messages) {
                rc[count++] = cmd.getMessage();
            }
            return rc;
        }
        
        public MessageAck[] getAcks() {
            final MessageAck[] rc = new MessageAck[this.acks.size()];
            int count = 0;
            for (final RemoveMessageCommand cmd : this.acks) {
                rc[count++] = cmd.getMessageAck();
            }
            return rc;
        }
        
        public void commit() throws IOException {
            final ConnectionContext ctx = new ConnectionContext();
            MemoryTransactionStore.this.persistenceAdapter.beginTransaction(ctx);
            try {
                for (final AddMessageCommand cmd : this.messages) {
                    cmd.run(ctx);
                }
                for (final RemoveMessageCommand cmd2 : this.acks) {
                    cmd2.run(ctx);
                }
            }
            catch (IOException e) {
                MemoryTransactionStore.this.persistenceAdapter.rollbackTransaction(ctx);
                throw e;
            }
            MemoryTransactionStore.this.persistenceAdapter.commitTransaction(ctx);
        }
    }
    
    public interface RemoveMessageCommand
    {
        MessageAck getMessageAck();
        
        void run(final ConnectionContext p0) throws IOException;
    }
    
    public interface AddMessageCommand
    {
        Message getMessage();
        
        void run(final ConnectionContext p0) throws IOException;
    }
}
