// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc.adapter;

import org.apache.activemq.store.jdbc.Statements;

public class HsqldbJDBCAdapter extends BytesJDBCAdapter
{
    @Override
    public void setStatements(final Statements statements) {
        statements.setBinaryDataType("OTHER");
        super.setStatements(statements);
    }
}
