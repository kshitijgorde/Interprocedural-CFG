// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.state;

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
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import sun.misc.Unsafe;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.SessionInfo;
import java.util.Collections;
import java.util.ArrayList;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.command.ConsumerId;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.command.DestinationInfo;
import java.util.List;
import org.apache.activemq.command.SessionId;
import org.apache.activemq.command.TransactionId;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.command.ConnectionInfo;

public class ConnectionState
{
    ConnectionInfo info;
    private final ConcurrentHashMap<TransactionId, TransactionState> transactions;
    private final ConcurrentHashMap<SessionId, SessionState> sessions;
    private final List<DestinationInfo> tempDestinations;
    private final AtomicBoolean shutdown;
    private boolean connectionInterruptProcessingComplete;
    private HashMap<ConsumerId, ConsumerInfo> recoveringPullConsumers;
    
    public ConnectionState(final ConnectionInfo info) {
        this.transactions = new ConcurrentHashMap<TransactionId, TransactionState>();
        this.sessions = new ConcurrentHashMap<SessionId, SessionState>();
        this.tempDestinations = Collections.synchronizedList(new ArrayList<DestinationInfo>());
        this.shutdown = new AtomicBoolean(false);
        this.connectionInterruptProcessingComplete = true;
        this.info = info;
        this.addSession(new SessionInfo(info, -1L));
    }
    
    @Override
    public String toString() {
        return this.info.toString();
    }
    
    public void reset(final ConnectionInfo info) {
        this.info = info;
        this.transactions.clear();
        this.sessions.clear();
        this.tempDestinations.clear();
        this.shutdown.set(false);
        this.addSession(new SessionInfo(info, -1L));
    }
    
    public void addTempDestination(final DestinationInfo info) {
        this.checkShutdown();
        this.tempDestinations.add(info);
    }
    
    public void removeTempDestination(final ActiveMQDestination destination) {
        final Iterator<DestinationInfo> iter = this.tempDestinations.iterator();
        while (iter.hasNext()) {
            final DestinationInfo di = iter.next();
            if (di.getDestination().equals(destination)) {
                iter.remove();
            }
        }
    }
    
    public void addTransactionState(final TransactionId id) {
        this.checkShutdown();
        this.transactions.put(id, new TransactionState(id));
    }
    
    public TransactionState getTransactionState(final TransactionId id) {
        return this.transactions.get(id);
    }
    
    public Collection<TransactionState> getTransactionStates() {
        return this.transactions.values();
    }
    
    public TransactionState removeTransactionState(final TransactionId id) {
        return this.transactions.remove(id);
    }
    
    public void addSession(final SessionInfo info) {
        this.checkShutdown();
        this.sessions.put(info.getSessionId(), new SessionState(info));
    }
    
    public SessionState removeSession(final SessionId id) {
        return this.sessions.remove(id);
    }
    
    public SessionState getSessionState(final SessionId id) {
        return this.sessions.get(id);
    }
    
    public ConnectionInfo getInfo() {
        return this.info;
    }
    
    public Set<SessionId> getSessionIds() {
        return (Set<SessionId>)this.sessions.keySet();
    }
    
    public List<DestinationInfo> getTempDestinations() {
        return this.tempDestinations;
    }
    
    public Collection<SessionState> getSessionStates() {
        return this.sessions.values();
    }
    
    private void checkShutdown() {
        if (this.shutdown.get()) {
            throw new IllegalStateException("Disposed");
        }
    }
    
    public void shutdown() {
        if (this.shutdown.compareAndSet(false, true)) {
            for (final SessionState ss : this.sessions.values()) {
                ss.shutdown();
            }
        }
    }
    
    public Map<ConsumerId, ConsumerInfo> getRecoveringPullConsumers() {
        if (this.recoveringPullConsumers == null) {
            this.recoveringPullConsumers = new HashMap<ConsumerId, ConsumerInfo>();
        }
        return this.recoveringPullConsumers;
    }
    
    public void setConnectionInterruptProcessingComplete(final boolean connectionInterruptProcessingComplete) {
        this.connectionInterruptProcessingComplete = connectionInterruptProcessingComplete;
    }
    
    public boolean isConnectionInterruptProcessingComplete() {
        return this.connectionInterruptProcessingComplete;
    }
}
