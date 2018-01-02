// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import java.util.Collections;
import java.util.List;

public class InternetAddress extends IpAddr
{
    private static InternetAddress instance;
    private NetworkContainer netContainer;
    
    public static InternetAddress getInstance() {
        if (InternetAddress.instance == null) {
            InternetAddress.instance = new InternetAddress();
        }
        return InternetAddress.instance;
    }
    
    private InternetAddress() {
        this.netContainer = null;
    }
    
    @Override
    public boolean contains(final IpAddr ip) {
        return this.equals((Object)ip) || (ip.isPublic() && this.notContainedByAnyNetwork(ip));
    }
    
    private boolean notContainedByAnyNetwork(final IpAddr ip) {
        boolean result = true;
        if (this.netContainer != null) {
            result = this.netContainer.notContainedByAnyNetwork(ip);
        }
        return result;
    }
    
    @Override
    public boolean bidirectionallyContains(final IpAddr ip) {
        return this.contains(ip);
    }
    
    @Override
    public boolean equalsIgnoreMask(final IpAddr ip) {
        return this.equals((Object)ip);
    }
    
    @Override
    public int compare(final IpAddr ip) {
        return -1;
    }
    
    @Override
    public int compareIgnoreMask(final IpAddr ip) {
        return -1;
    }
    
    @Override
    public int getAddress() {
        return 0;
    }
    
    @Override
    public int[] getAddressOctets() {
        return new int[] { 256, 256, 256, 256 };
    }
    
    @Override
    public List<IpAddr> getAllSubnets() {
        return Collections.emptyList();
    }
    
    @Override
    public int getNetmask() {
        return 0;
    }
    
    @Override
    public int getNetmaskLength() {
        return 33;
    }
    
    @Override
    public IpAddr getSubnet() {
        return IpAddr.ipAny();
    }
    
    @Override
    public boolean isAny() {
        return false;
    }
    
    @Override
    public boolean isBroadcast() {
        return false;
    }
    
    @Override
    public boolean isHost() {
        return false;
    }
    
    @Override
    public boolean isLocal() {
        return false;
    }
    
    @Override
    public boolean isLoopback() {
        return false;
    }
    
    @Override
    public boolean isMulticast() {
        return false;
    }
    
    @Override
    public boolean isNetwork() {
        return false;
    }
    
    @Override
    public boolean isPrivate() {
        return false;
    }
    
    @Override
    public boolean isReserved() {
        return false;
    }
    
    public void setContainer(final NetworkContainer c) {
        this.netContainer = c;
    }
    
    @Override
    public boolean isInternet() {
        return true;
    }
    
    @Override
    public String getAddressString() {
        return "";
    }
    
    @Override
    public String getNetmaskString() {
        return "";
    }
    
    @Override
    public String toString() {
        return "Internet";
    }
}
