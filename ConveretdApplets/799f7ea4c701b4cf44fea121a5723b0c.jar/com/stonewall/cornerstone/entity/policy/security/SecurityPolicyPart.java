// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.db.DbException;
import java.util.ArrayList;
import java.util.Collections;
import com.stonewall.cornerstone.entity.db.SecurityPolicyDbAccess;
import com.stonewall.cornerstone.jms.msg.event.DbEvent;
import java.util.Collection;
import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.policy.PolicyPart;

public class SecurityPolicyPart extends PolicyPart
{
    public SecurityPolicyPart() {
    }
    
    public SecurityPolicyPart(final EntityReference entity) {
        super(entity);
    }
    
    public SecurityPolicyPart(final EntityReference entity, final String rule, final Policy policy) {
        super(entity, rule, policy);
    }
    
    @Override
    public Collection<DbEvent> changed() throws DbException {
        final SecurityPolicyDbAccess policyAccess = new SecurityPolicyDbAccess();
        policyAccess.updatePending(this.getPolicy().getId(), true);
        policyAccess.markPartsPending((Collection<PolicyPart>)Collections.singletonList(this));
        final Collection<DbEvent> events = new ArrayList<DbEvent>();
        events.add(new DbEvent(DbEvent.Action.update, this.getPolicy().getReference()));
        return events;
    }
}
