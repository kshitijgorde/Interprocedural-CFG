// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc.adapter;

import org.apache.activemq.store.jdbc.Statements;

public class TransactJDBCAdapter extends ImageBasedJDBCAdaptor
{
    @Override
    public void setStatements(final Statements statements) {
        String lockCreateStatement = "SELECT * FROM " + statements.getFullLockTableName();
        if (statements.isUseLockCreateWhereClause()) {
            lockCreateStatement += " WHERE ID = 1";
        }
        lockCreateStatement += " WITH (UPDLOCK, ROWLOCK)";
        statements.setLockCreateStatement(lockCreateStatement);
        super.setStatements(statements);
    }
}
