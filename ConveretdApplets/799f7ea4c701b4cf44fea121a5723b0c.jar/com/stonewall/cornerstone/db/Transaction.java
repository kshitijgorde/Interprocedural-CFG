// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import org.xmodel.log.Log;

public class Transaction extends com.stonewall.cornerstone.utility.Transaction
{
    private DbSession session;
    static final Log log;
    
    static {
        log = Log.getLog(Transaction.class);
    }
    
    public Transaction() {
    }
    
    public Transaction(final String id) {
        super(id);
    }
    
    @Override
    public void begin() {
        if (this.session != null) {
            Transaction.log.warn(String.valueOf(this.identity()) + " already started - <begin> ignored.");
            return;
        }
        try {
            this.session = DbSessionFactory.getConnection();
            if (this.session.getTransaction() == null) {
                this.session.setTransaction(this);
                this.session.setAutoCommit(false);
            }
            super.begin();
        }
        catch (DbException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public static Transaction getCurrent() {
        final com.stonewall.cornerstone.utility.Transaction result = com.stonewall.cornerstone.utility.Transaction.getCurrent();
        if (result instanceof Transaction) {
            return (Transaction)result;
        }
        return null;
    }
    
    @Override
    public void commit() {
        try {
            if (this.sessionOwner()) {
                this.session.commit();
                this.session.setTransaction(null);
                this.session.setAutoCommit(true);
                Transaction.log.info("Transaction: " + this.getId() + " - (DB) committed.");
                this.session.close();
                Transaction.log.info("Transaction: " + this.getId() + " - (DB) closed.");
            }
        }
        catch (Exception e) {
            Transaction.log.error(this, e);
            return;
        }
        finally {
            super.commit();
        }
        super.commit();
    }
    
    @Override
    public void end() {
        try {
            if (this.sessionOwner()) {
                this.session.rollback();
                this.session.setTransaction(null);
                this.session.setAutoCommit(true);
                this.session.close();
                Transaction.log.info("Transaction: " + this.getId() + " - (DB) rollback complete.");
            }
        }
        catch (Exception e) {
            Transaction.log.error(this, e);
            return;
        }
        finally {
            super.end();
        }
        super.end();
    }
    
    public void rollback() {
        this.end();
    }
    
    public static DbSession getSession() {
        try {
            final Transaction current = getCurrent();
            if (current != null) {
                return current.session;
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private boolean sessionOwner() {
        return this.session != null && this.session.getTransaction() == this;
    }
}
