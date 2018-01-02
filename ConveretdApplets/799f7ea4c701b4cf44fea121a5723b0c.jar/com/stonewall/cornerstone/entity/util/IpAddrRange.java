// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import java.util.ArrayList;
import java.util.List;
import org.xmodel.log.Log;

public class IpAddrRange
{
    private int ip1;
    private int ip2;
    private int mask;
    static final Log log;
    
    static {
        log = Log.getLog(IpAddrRange.class);
    }
    
    public IpAddrRange(final IpAddr ip1, final IpAddr ip2) throws Exception {
        this.ip1 = 0;
        this.ip2 = 0;
        this.mask = 0;
        this.init(ip1, ip2);
    }
    
    public IpAddrRange(final String range) throws Exception {
        this.ip1 = 0;
        this.ip2 = 0;
        this.mask = 0;
        if (!range.contains("-")) {
            throw new Exception("Invalid IP Range");
        }
        final String ip1 = range.split("-")[0];
        final String ip2 = range.split("-")[1];
        this.init(new IpAddr(ip1), new IpAddr(ip2));
    }
    
    private void init(final IpAddr ip1, final IpAddr ip2) throws Exception {
        this.ip1 = ip1.getAddress();
        this.ip2 = ip2.getAddress();
        if (ip1.getNetmask() != ip2.getNetmask()) {
            throw new Exception("Invalid IP Range - Netmask must be the same");
        }
        this.mask = ip1.getNetmask();
    }
    
    public List<IpAddr> getAddresses() {
        final List<IpAddr> addresses = new ArrayList<IpAddr>();
        for (int i = this.ip1; i <= this.ip2; ++i) {
            try {
                addresses.add(new IpAddr(i, this.mask));
            }
            catch (Exception ex) {
                IpAddrRange.log.error(this, ex);
            }
        }
        return addresses;
    }
    
    public int getAddressCount() {
        return this.ip2 - this.ip1 + 1;
    }
}
