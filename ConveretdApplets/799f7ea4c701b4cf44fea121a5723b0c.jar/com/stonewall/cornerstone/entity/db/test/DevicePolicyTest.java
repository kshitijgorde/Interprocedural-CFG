// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db.test;

import java.util.List;
import com.stonewall.cornerstone.entity.policy.nat.NatPolicy;
import com.stonewall.cornerstone.entity.db.NatDbAccess;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class DevicePolicyTest extends TestCase
{
    public void testFetchForParent() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            NatPolicy policy = new NatDbAccess().fetchWorkingPolicy("COVKZ027X");
            assertTrue(policy != null);
            System.out.println(policy);
            policy = new NatDbAccess().fetchWorkingReference("COVKZ027X");
            assertTrue(policy != null);
            System.out.println(policy);
            List<NatPolicy> ps = new NatDbAccess().fetchAll();
            System.out.println(ps);
            ps = new NatDbAccess().fetchAll(true);
            System.out.println(ps);
            policy = new NatDbAccess().fetchById(policy.getId());
            assertTrue(policy != null);
            System.out.println(policy);
            policy = new NatDbAccess().fetchById(policy.getId(), true);
            assertTrue(policy != null);
            System.out.println(policy);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
