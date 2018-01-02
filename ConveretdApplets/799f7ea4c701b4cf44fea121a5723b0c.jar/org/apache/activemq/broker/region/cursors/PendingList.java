// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.cursors;

import java.util.Iterator;
import org.apache.activemq.broker.region.MessageReference;

public interface PendingList
{
    boolean isEmpty();
    
    void clear();
    
    PendingNode addMessageFirst(final MessageReference p0);
    
    PendingNode addMessageLast(final MessageReference p0);
    
    void remove(final MessageReference p0);
    
    int size();
    
    Iterator<MessageReference> iterator();
}
