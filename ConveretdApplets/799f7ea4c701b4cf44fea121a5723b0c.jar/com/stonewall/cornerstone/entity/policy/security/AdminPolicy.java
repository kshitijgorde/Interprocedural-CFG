// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.AdminPolicyDbAccess;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.Label;
import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;

public class AdminPolicy extends SecurityPolicy
{
    public AdminPolicy() {
        super(Phase.working, Type.admin);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new AdminPolicyDbAccess().fetch();
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new AdminPolicyDbAccess().insert(this);
    }
}
