// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.filter;

import org.apache.activemq.command.ActiveMQDestination;

public class PrefixDestinationFilter extends DestinationFilter
{
    private String[] prefixes;
    private byte destinationType;
    
    public PrefixDestinationFilter(final String[] prefixes, final byte destinationType) {
        this.prefixes = prefixes;
        this.destinationType = destinationType;
    }
    
    @Override
    public boolean matches(final ActiveMQDestination destination) {
        if (destination.getDestinationType() != this.destinationType) {
            return false;
        }
        final String[] path = DestinationPath.getDestinationPaths(destination.getPhysicalName());
        final int length = this.prefixes.length;
        if (path.length >= length) {
            for (int size = length - 1, i = 0; i < size; ++i) {
                if (!path[i].equals("*") && !this.prefixes[i].equals("*") && !this.prefixes[i].equals(path[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public String getText() {
        return DestinationPath.toString(this.prefixes);
    }
    
    @Override
    public String toString() {
        return super.toString() + "[destination: " + this.getText() + "]";
    }
    
    public boolean isWildcard() {
        return true;
    }
}
