// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum IPProto implements Constant
{
    IPPROTO_IP(1), 
    IPPROTO_HOPOPTS(2), 
    IPPROTO_ICMP(3), 
    IPPROTO_IGMP(4), 
    IPPROTO_IPIP(5), 
    IPPROTO_TCP(6), 
    IPPROTO_EGP(7), 
    IPPROTO_PUP(8), 
    IPPROTO_UDP(9), 
    IPPROTO_IDP(10), 
    IPPROTO_TP(11), 
    IPPROTO_IPV6(12), 
    IPPROTO_ROUTING(13), 
    IPPROTO_FRAGMENT(14), 
    IPPROTO_RSVP(15), 
    IPPROTO_GRE(16), 
    IPPROTO_ESP(17), 
    IPPROTO_AH(18), 
    IPPROTO_ICMPV6(19), 
    IPPROTO_NONE(20), 
    IPPROTO_DSTOPTS(21), 
    IPPROTO_MTP(22), 
    IPPROTO_ENCAP(23), 
    IPPROTO_PIM(24), 
    IPPROTO_COMP(25), 
    IPPROTO_SCTP(26), 
    IPPROTO_RAW(27), 
    IPPROTO_MAX(28);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 28L;
    
    private IPProto(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
