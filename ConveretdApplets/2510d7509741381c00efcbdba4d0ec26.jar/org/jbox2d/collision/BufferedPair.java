// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

public class BufferedPair implements Comparable<BufferedPair>
{
    int proxyId1;
    int proxyId2;
    
    private boolean equals(final BufferedPair other) {
        return this.proxyId1 == other.proxyId1 && this.proxyId2 == other.proxyId2;
    }
    
    private boolean minor(final BufferedPair other) {
        return this.proxyId1 < other.proxyId1 || (this.proxyId1 == other.proxyId1 && this.proxyId2 < other.proxyId2);
    }
    
    public int compareTo(final BufferedPair p) {
        if (this.minor(p)) {
            return -1;
        }
        if (this.equals(p)) {
            return 0;
        }
        return 1;
    }
}
