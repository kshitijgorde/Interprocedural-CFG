// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.ArrayList;
import java.util.logging.Logger;

public class DNSState implements Comparable
{
    private static Logger logger;
    private final String name;
    private static int nextOrdinal;
    private final int ordinal;
    private static final ArrayList sequence;
    public static final DNSState PROBING_1;
    public static final DNSState PROBING_2;
    public static final DNSState PROBING_3;
    public static final DNSState ANNOUNCING_1;
    public static final DNSState ANNOUNCING_2;
    public static final DNSState ANNOUNCED;
    public static final DNSState CANCELED;
    
    private DNSState(final String name) {
        this.ordinal = DNSState.nextOrdinal++;
        this.name = name;
        DNSState.sequence.add(this);
    }
    
    @Override
    public final String toString() {
        return this.name;
    }
    
    public final DNSState advance() {
        return (this.isProbing() || this.isAnnouncing()) ? DNSState.sequence.get(this.ordinal + 1) : this;
    }
    
    public final DNSState revert() {
        return (this == DNSState.CANCELED) ? this : DNSState.PROBING_1;
    }
    
    public boolean isProbing() {
        return this.compareTo(DNSState.PROBING_1) >= 0 && this.compareTo(DNSState.PROBING_3) <= 0;
    }
    
    public boolean isAnnouncing() {
        return this.compareTo(DNSState.ANNOUNCING_1) >= 0 && this.compareTo(DNSState.ANNOUNCING_2) <= 0;
    }
    
    public boolean isAnnounced() {
        return this.compareTo(DNSState.ANNOUNCED) == 0;
    }
    
    @Override
    public int compareTo(final Object o) {
        return this.ordinal - ((DNSState)o).ordinal;
    }
    
    static {
        DNSState.logger = Logger.getLogger(DNSState.class.toString());
        DNSState.nextOrdinal = 0;
        sequence = new ArrayList();
        PROBING_1 = new DNSState("probing 1");
        PROBING_2 = new DNSState("probing 2");
        PROBING_3 = new DNSState("probing 3");
        ANNOUNCING_1 = new DNSState("announcing 1");
        ANNOUNCING_2 = new DNSState("announcing 2");
        ANNOUNCED = new DNSState("announced");
        CANCELED = new DNSState("canceled");
    }
}
