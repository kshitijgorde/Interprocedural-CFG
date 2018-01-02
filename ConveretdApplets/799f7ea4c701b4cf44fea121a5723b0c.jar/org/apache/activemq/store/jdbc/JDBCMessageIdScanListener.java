// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc;

import org.apache.activemq.command.MessageId;

public interface JDBCMessageIdScanListener
{
    void messageId(final MessageId p0);
}
