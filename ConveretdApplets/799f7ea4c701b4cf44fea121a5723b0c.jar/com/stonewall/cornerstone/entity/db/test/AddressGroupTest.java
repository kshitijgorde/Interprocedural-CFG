// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db.test;

import java.util.List;
import com.stonewall.cornerstone.entity.db.AddressGroupDbAccess;
import com.stonewall.cornerstone.entity.Label;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.entity.Network;
import com.stonewall.cornerstone.entity.AddressGroup;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class AddressGroupTest extends TestCase
{
    public void testFetch() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            AddressGroup group = new AddressGroup();
            group.setName("group");
            final Network network = new Network();
            network.setIpAddress(new IpAddr("172.1.100.0/24"));
            group.addPart(network.getReference());
            final AddressGroupDbAccess access = new AddressGroupDbAccess(Label.latest);
            access.insert(group);
            group = access.fetchById(group.getId());
            System.out.println(group);
            group = access.fetchById(group.getId(), true);
            System.out.println(group);
            List<AddressGroup> groups = access.fetchAll();
            System.out.println(groups);
            groups = access.fetchAll(true);
            System.out.println(groups);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
