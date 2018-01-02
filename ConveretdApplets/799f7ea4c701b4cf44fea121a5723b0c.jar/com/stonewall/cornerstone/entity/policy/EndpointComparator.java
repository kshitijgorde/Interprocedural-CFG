// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import com.stonewall.cornerstone.entity.EntityFactory;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.entity.IAddress;

public class EndpointComparator
{
    public boolean contains(final Endpoint ep1, final Endpoint ep2) {
        final Entity e1 = ep1.getReferent();
        final Entity e2 = ep2.getReferent();
        final IpAddr addr1 = ((IAddress)e1).getIpAddress();
        switch (ep1.getType()) {
            case network: {
                if (ep2.isAny()) {
                    return false;
                }
                if (ep1.isInternet() && ep2.isInternet()) {
                    return true;
                }
                for (final IpAddr addr2 : ((IAddress)e2).getIpAddresses()) {
                    if (!addr1.contains(addr2)) {
                        return false;
                    }
                }
                return true;
            }
            case host: {
                if (!(ep2 instanceof HostEndpoint)) {
                    return false;
                }
                final IpAddr addr2 = ((IAddress)e2).getIpAddress();
                return addr1.contains(addr2);
            }
            case ipInterface: {
                final IpAddr addr2 = ((IAddress)e2).getIpAddress();
                return addr1.contains(addr2);
            }
            case addressGroup: {
                final Iterator<IpAddr> iterator2 = ((IAddress)e1).getIpAddresses().iterator();
                if (iterator2.hasNext()) {
                    final IpAddr a1 = iterator2.next();
                    for (final IpAddr a2 : ((IAddress)e2).getIpAddresses()) {
                        if (a1.contains(a2)) {
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }
            case any: {
                return true;
            }
            default: {
                return true;
            }
        }
    }
}
