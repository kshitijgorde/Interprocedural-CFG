// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.darwin;

import com.kenai.constantine.Constant;

public enum IPProto implements Constant
{
    IPPROTO_IP(0), 
    IPPROTO_HOPOPTS(0), 
    IPPROTO_ICMP(1), 
    IPPROTO_IGMP(2), 
    IPPROTO_IPIP(4), 
    IPPROTO_TCP(6), 
    IPPROTO_EGP(8), 
    IPPROTO_PUP(12), 
    IPPROTO_UDP(17), 
    IPPROTO_IDP(22), 
    IPPROTO_TP(29), 
    IPPROTO_IPV6(41), 
    IPPROTO_ROUTING(43), 
    IPPROTO_FRAGMENT(44), 
    IPPROTO_RSVP(46), 
    IPPROTO_GRE(47), 
    IPPROTO_ESP(50), 
    IPPROTO_AH(51), 
    IPPROTO_ICMPV6(58), 
    IPPROTO_NONE(59), 
    IPPROTO_DSTOPTS(60), 
    IPPROTO_MTP(92), 
    IPPROTO_ENCAP(98), 
    IPPROTO_PIM(103), 
    IPPROTO_RAW(255), 
    IPPROTO_MAX(256);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 256L;
    
    private IPProto(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
