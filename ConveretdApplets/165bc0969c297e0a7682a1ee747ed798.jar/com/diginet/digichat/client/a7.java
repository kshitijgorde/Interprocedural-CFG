// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.network.v;
import com.diginet.digichat.common.j;
import com.diginet.digichat.awt.a8;
import com.diginet.digichat.common.bn;

public interface a7 extends x
{
    void a(final bn p0);
    
    a8 e();
    
    boolean[] getLoc();
    
    void setBuddy(final j p0, final boolean p1, final boolean p2);
    
    void setParams(final v p0);
    
    void setProf(final v p0);
    
    void addMess(final String p0, final int p1, final bd p2, final String[] p3, final long[] p4);
}
