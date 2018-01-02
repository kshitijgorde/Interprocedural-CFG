// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.IBoundChangeRecord;
import org.xmodel.ChangeSet;
import org.xmodel.IChangeSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class GroupTransaction implements ITransaction
{
    private List<ITransaction> A;
    private List<ITransaction> B;
    private List<ITransaction> C;
    
    public GroupTransaction() {
        this.A = new ArrayList<ITransaction>();
        this.B = new ArrayList<ITransaction>();
        this.C = new ArrayList<ITransaction>();
    }
    
    public void addTransaction(final ITransaction transaction) {
        this.A.add(transaction);
    }
    
    public void removeTransaction(final ITransaction transaction) {
        this.A.remove(transaction);
    }
    
    @Override
    public boolean lock(int n) {
        for (final ITransaction transaction : this.A) {
            final long nanoTime = System.nanoTime();
            if (!transaction.lock(n)) {
                break;
            }
            this.B.add(transaction);
            n -= (int)(System.nanoTime() - nanoTime);
            if (n <= 0) {
                break;
            }
        }
        if (this.B.size() != this.A.size()) {
            this.unlock();
            return false;
        }
        this.A.clear();
        return true;
    }
    
    @Override
    public void unlock() {
        for (final ITransaction transaction : this.B) {
            transaction.unlock();
            this.A.add(transaction);
        }
    }
    
    @Override
    public boolean commit() {
        if (this.B.size() == 0 || this.C.size() > 0) {
            throw new IllegalStateException();
        }
        int n = 0;
        for (final ITransaction transaction : this.B) {
            try {
                if (!transaction.commit()) {
                    break;
                }
                ++n;
            }
            finally {
                this.C.add(transaction);
            }
            this.C.add(transaction);
        }
        if (n != this.B.size()) {
            for (int i = 0; i < n; ++i) {
                final ITransaction transaction2 = this.B.get(i);
                try {
                    if (!transaction2.rollback()) {}
                }
                finally {
                    this.C.add(transaction2);
                }
                this.C.add(transaction2);
            }
        }
        return this.C.size() == 0;
    }
    
    @Override
    public boolean rollback() {
        if (this.B.size() == 0 || this.C.size() > 0) {
            throw new IllegalStateException();
        }
        this.C = new ArrayList<ITransaction>();
        for (final ITransaction transaction : this.B) {
            try {
                if (!transaction.rollback()) {}
            }
            finally {
                this.C.add(transaction);
            }
            this.C.add(transaction);
        }
        return this.C.size() == 0;
    }
    
    @Override
    public IChangeSet getChanges() {
        final ChangeSet set = new ChangeSet();
        final Iterator<ITransaction> iterator = this.A.iterator();
        while (iterator.hasNext()) {
            final Iterator<IBoundChangeRecord> iterator2 = iterator.next().getChanges().getRecords().iterator();
            while (iterator2.hasNext()) {
                set.addRecord(iterator2.next());
            }
        }
        return set;
    }
}
