// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.journal;

import org.apache.kahadb.util.ByteSequence;

public interface ReplicationTarget
{
    void replicate(final Location p0, final ByteSequence p1, final boolean p2);
}
