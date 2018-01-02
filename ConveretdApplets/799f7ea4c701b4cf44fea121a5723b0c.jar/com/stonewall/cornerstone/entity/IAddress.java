// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import java.util.List;
import com.stonewall.cornerstone.entity.util.IpAddr;

public interface IAddress
{
    IpAddr getIpAddress();
    
    List<IpAddr> getIpAddresses();
}
