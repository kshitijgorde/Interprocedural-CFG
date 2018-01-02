// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import javax.jms.Message;
import java.io.Serializable;
import java.util.Comparator;

public abstract class MessageComparatorSupport implements Comparator, Serializable
{
    @Override
    public int compare(final Object object1, final Object object2) {
        final Message command1 = (Message)object1;
        final Message command2 = (Message)object2;
        return this.compareMessages(command1, command2);
    }
    
    protected abstract int compareMessages(final Message p0, final Message p1);
    
    protected int compareComparators(final Comparable comparable, final Comparable comparable2) {
        if (comparable == null && comparable2 == null) {
            return 0;
        }
        if (comparable != null) {
            if (comparable2 == null) {
                return 1;
            }
            return comparable.compareTo(comparable2);
        }
        else {
            if (comparable2 == null) {
                return 0;
            }
            if (comparable == null) {
                return -11;
            }
            return comparable2.compareTo(comparable) * -1;
        }
    }
}
