// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db.test;

import com.stonewall.cornerstone.entity.policy.security.SecurityPolicy;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;
import java.util.List;
import com.stonewall.cornerstone.entity.policy.security.SitePolicy;
import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.entity.db.SecurityPolicyDbAccess;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class SitePolicyTest extends TestCase
{
    public void testFetchForParent() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            SitePolicy policy = new SecurityPolicyDbAccess().fetchWorkingSitePolicy("SITE0");
            assertTrue(policy != null);
            System.out.println(policy);
            policy = new SecurityPolicyDbAccess().fetchWorkingSiteReference("SITE0");
            assertTrue(policy != null);
            System.out.println(policy);
            List<DeployablePolicy> ps = new SecurityPolicyDbAccess().fetchAll(Policy.Type.site);
            System.out.println(ps);
            ps = new SecurityPolicyDbAccess().fetchAll(Policy.Type.site, true);
            System.out.println(ps);
            policy = new SecurityPolicyDbAccess().fetchById("WPOLICY0");
            assertTrue(policy != null);
            System.out.println(policy);
            policy = new SecurityPolicyDbAccess().fetchById("WPOLICY0", true);
            assertTrue(policy != null);
            System.out.println(policy);
            final List<SecurityPolicy> sps = new SecurityPolicyDbAccess().fetchAll(true);
            System.out.println(sps);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
