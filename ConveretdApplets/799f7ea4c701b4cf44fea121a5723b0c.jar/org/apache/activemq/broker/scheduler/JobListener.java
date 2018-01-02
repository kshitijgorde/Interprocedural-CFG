// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.scheduler;

import org.apache.kahadb.util.ByteSequence;

public interface JobListener
{
    void scheduledJob(final String p0, final ByteSequence p1);
}
