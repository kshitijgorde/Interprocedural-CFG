// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.command;

import java.util.Arrays;
import org.apache.activemq.util.HexSupport;
import javax.transaction.xa.Xid;

public class XATransactionId extends TransactionId implements Xid, Comparable
{
    public static final byte DATA_STRUCTURE_TYPE = 112;
    private int formatId;
    private byte[] branchQualifier;
    private byte[] globalTransactionId;
    private transient int hash;
    private transient String transactionKey;
    
    public XATransactionId() {
    }
    
    public XATransactionId(final Xid xid) {
        this.formatId = xid.getFormatId();
        this.globalTransactionId = xid.getGlobalTransactionId();
        this.branchQualifier = xid.getBranchQualifier();
    }
    
    @Override
    public byte getDataStructureType() {
        return 112;
    }
    
    @Override
    public synchronized String getTransactionKey() {
        if (this.transactionKey == null) {
            this.transactionKey = "XID:" + this.formatId + ":" + HexSupport.toHexFromBytes(this.globalTransactionId) + ":" + HexSupport.toHexFromBytes(this.branchQualifier);
        }
        return this.transactionKey;
    }
    
    @Override
    public String toString() {
        return this.getTransactionKey();
    }
    
    @Override
    public boolean isXATransaction() {
        return true;
    }
    
    @Override
    public boolean isLocalTransaction() {
        return false;
    }
    
    @Override
    public int getFormatId() {
        return this.formatId;
    }
    
    @Override
    public byte[] getGlobalTransactionId() {
        return this.globalTransactionId;
    }
    
    @Override
    public byte[] getBranchQualifier() {
        return this.branchQualifier;
    }
    
    public void setBranchQualifier(final byte[] branchQualifier) {
        this.branchQualifier = branchQualifier;
        this.hash = 0;
    }
    
    public void setFormatId(final int formatId) {
        this.formatId = formatId;
        this.hash = 0;
    }
    
    public void setGlobalTransactionId(final byte[] globalTransactionId) {
        this.globalTransactionId = globalTransactionId;
        this.hash = 0;
    }
    
    @Override
    public int hashCode() {
        if (this.hash == 0) {
            this.hash = this.formatId;
            this.hash = hash(this.globalTransactionId, this.hash);
            this.hash = hash(this.branchQualifier, this.hash);
            if (this.hash == 0) {
                this.hash = 11332302;
            }
        }
        return this.hash;
    }
    
    private static int hash(final byte[] bytes, int hash) {
        for (int size = bytes.length, i = 0; i < size; ++i) {
            hash ^= bytes[i] << i % 4 * 8;
        }
        return hash;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null || o.getClass() != XATransactionId.class) {
            return false;
        }
        final XATransactionId xid = (XATransactionId)o;
        return xid.formatId == this.formatId && Arrays.equals(xid.globalTransactionId, this.globalTransactionId) && Arrays.equals(xid.branchQualifier, this.branchQualifier);
    }
    
    @Override
    public int compareTo(final Object o) {
        if (o == null || o.getClass() != XATransactionId.class) {
            return -1;
        }
        final XATransactionId xid = (XATransactionId)o;
        return this.getTransactionKey().compareTo(xid.getTransactionKey());
    }
}
