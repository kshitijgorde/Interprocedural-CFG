// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import org.apache.activemq.command.JournalTopicAck;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.command.Message;
import java.util.Iterator;
import org.apache.activemq.command.XATransactionId;
import org.apache.activemq.store.TransactionRecoveryListener;
import org.apache.activemq.kaha.impl.async.Location;
import java.io.IOException;
import org.apache.activemq.command.DataStructure;
import org.apache.activemq.command.JournalTransaction;
import java.util.LinkedHashMap;
import org.apache.activemq.command.TransactionId;
import java.util.Map;
import org.apache.activemq.store.TransactionStore;

public class AMQTransactionStore implements TransactionStore
{
    protected Map<TransactionId, AMQTx> inflightTransactions;
    Map<TransactionId, AMQTx> preparedTransactions;
    private final AMQPersistenceAdapter peristenceAdapter;
    private boolean doingRecover;
    
    public AMQTransactionStore(final AMQPersistenceAdapter adapter) {
        this.inflightTransactions = new LinkedHashMap<TransactionId, AMQTx>();
        this.preparedTransactions = new LinkedHashMap<TransactionId, AMQTx>();
        this.peristenceAdapter = adapter;
    }
    
    @Override
    public void prepare(final TransactionId txid) throws IOException {
        AMQTx tx = null;
        synchronized (this.inflightTransactions) {
            tx = this.inflightTransactions.remove(txid);
        }
        if (tx == null) {
            return;
        }
        this.peristenceAdapter.writeCommand(new JournalTransaction((byte)1, txid, false), true);
        synchronized (this.preparedTransactions) {
            this.preparedTransactions.put(txid, tx);
        }
    }
    
    public void replayPrepare(final TransactionId txid) throws IOException {
        AMQTx tx = null;
        synchronized (this.inflightTransactions) {
            tx = this.inflightTransactions.remove(txid);
        }
        if (tx == null) {
            return;
        }
        synchronized (this.preparedTransactions) {
            this.preparedTransactions.put(txid, tx);
        }
    }
    
    public AMQTx getTx(final TransactionId txid, final Location location) {
        AMQTx tx = null;
        synchronized (this.inflightTransactions) {
            tx = this.inflightTransactions.get(txid);
            if (tx == null) {
                tx = new AMQTx(location);
                this.inflightTransactions.put(txid, tx);
            }
        }
        return tx;
    }
    
    @Override
    public void commit(final TransactionId txid, final boolean wasPrepared, final Runnable preCommit, final Runnable postCommit) throws IOException {
        if (preCommit != null) {
            preCommit.run();
        }
        AMQTx tx;
        if (wasPrepared) {
            synchronized (this.preparedTransactions) {
                tx = this.preparedTransactions.remove(txid);
            }
        }
        else {
            synchronized (this.inflightTransactions) {
                tx = this.inflightTransactions.remove(txid);
            }
        }
        if (tx == null) {
            if (postCommit != null) {
                postCommit.run();
            }
            return;
        }
        if (txid.isXATransaction()) {
            this.peristenceAdapter.writeCommand(new JournalTransaction((byte)2, txid, wasPrepared), true, true);
        }
        else {
            this.peristenceAdapter.writeCommand(new JournalTransaction((byte)4, txid, wasPrepared), true, true);
        }
        if (postCommit != null) {
            postCommit.run();
        }
    }
    
    public AMQTx replayCommit(final TransactionId txid, final boolean wasPrepared) throws IOException {
        if (wasPrepared) {
            synchronized (this.preparedTransactions) {
                return this.preparedTransactions.remove(txid);
            }
        }
        synchronized (this.inflightTransactions) {
            return this.inflightTransactions.remove(txid);
        }
    }
    
    @Override
    public void rollback(final TransactionId txid) throws IOException {
        AMQTx tx = null;
        synchronized (this.inflightTransactions) {
            tx = this.inflightTransactions.remove(txid);
        }
        if (tx != null) {
            synchronized (this.preparedTransactions) {
                tx = this.preparedTransactions.remove(txid);
            }
        }
        if (tx != null) {
            if (txid.isXATransaction()) {
                this.peristenceAdapter.writeCommand(new JournalTransaction((byte)3, txid, false), true, true);
            }
            else {
                this.peristenceAdapter.writeCommand(new JournalTransaction((byte)5, txid, false), true, true);
            }
        }
    }
    
    public void replayRollback(final TransactionId txid) throws IOException {
        boolean inflight = false;
        synchronized (this.inflightTransactions) {
            inflight = (this.inflightTransactions.remove(txid) != null);
        }
        if (inflight) {
            synchronized (this.preparedTransactions) {
                this.preparedTransactions.remove(txid);
            }
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
        synchronized (this.inflightTransactions) {
            this.inflightTransactions.clear();
        }
        this.doingRecover = true;
        try {
            Map<TransactionId, AMQTx> txs = null;
            synchronized (this.preparedTransactions) {
                txs = new LinkedHashMap<TransactionId, AMQTx>(this.preparedTransactions);
            }
            for (final Object txid : txs.keySet()) {
                final AMQTx tx = txs.get(txid);
                listener.recover((XATransactionId)txid, tx.getMessages(), tx.getAcks());
            }
        }
        finally {
            this.doingRecover = false;
        }
    }
    
    void addMessage(final AMQMessageStore store, final Message message, final Location location) throws IOException {
        final AMQTx tx = this.getTx(message.getTransactionId(), location);
        tx.add(store, message, location);
    }
    
    public void removeMessage(final AMQMessageStore store, final MessageAck ack, final Location location) throws IOException {
        final AMQTx tx = this.getTx(ack.getTransactionId(), location);
        tx.add(store, ack);
    }
    
    public void acknowledge(final AMQTopicMessageStore store, final JournalTopicAck ack, final Location location) {
        final AMQTx tx = this.getTx(ack.getTransactionId(), location);
        tx.add(store, ack);
    }
    
    public Location checkpoint() throws IOException {
        Location minimumLocationInUse = null;
        synchronized (this.inflightTransactions) {
            for (final AMQTx tx : this.inflightTransactions.values()) {
                final Location location = tx.getLocation();
                if (minimumLocationInUse == null || location.compareTo(minimumLocationInUse) < 0) {
                    minimumLocationInUse = location;
                }
            }
        }
        synchronized (this.preparedTransactions) {
            for (final AMQTx tx : this.preparedTransactions.values()) {
                final Location location = tx.getLocation();
                if (minimumLocationInUse == null || location.compareTo(minimumLocationInUse) < 0) {
                    minimumLocationInUse = location;
                }
            }
            return minimumLocationInUse;
        }
    }
    
    public boolean isDoingRecover() {
        return this.doingRecover;
    }
    
    public Map<TransactionId, AMQTx> getPreparedTransactions() {
        return this.preparedTransactions;
    }
    
    public void setPreparedTransactions(final Map<TransactionId, AMQTx> preparedTransactions) {
        if (preparedTransactions != null) {
            this.preparedTransactions.clear();
            this.preparedTransactions.putAll(preparedTransactions);
        }
    }
}
