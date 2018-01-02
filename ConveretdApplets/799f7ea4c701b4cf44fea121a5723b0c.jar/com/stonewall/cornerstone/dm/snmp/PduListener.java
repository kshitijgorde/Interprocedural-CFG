// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.snmp;

public interface PduListener
{
    void pduResponse(final SmartPdu p0);
    
    void vbLimitAdjusted(final SmartPdu p0);
}
