// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.nat;

import com.stonewall.cornerstone.db.DbException;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.db.SiteDbAccess;
import java.util.Collections;
import com.stonewall.cornerstone.entity.db.NatDbAccess;
import java.util.ArrayList;
import com.stonewall.cornerstone.jms.msg.event.DbEvent;
import java.util.Collection;
import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.policy.PolicyPart;

public class NatRulePart extends PolicyPart
{
    public NatRulePart() {
    }
    
    public NatRulePart(final EntityReference entity) {
        super(entity);
    }
    
    public NatRulePart(final EntityReference entity, final String rule, final Policy policy) {
        super(entity, rule, policy);
    }
    
    @Override
    public Collection<DbEvent> changed() throws DbException {
        final Collection<DbEvent> events = new ArrayList<DbEvent>();
        final NatDbAccess policyAccess = new NatDbAccess();
        policyAccess.updatePending(this.getPolicy().getId(), true);
        final Collection<String> siteIds = policyAccess.fetchSiteIds(Collections.singletonList(this.getPolicy().getId()));
        new SiteDbAccess().updatePending(siteIds, true);
        for (final String id : siteIds) {
            events.add(new DbEvent(DbEvent.Action.update, new EntityReference(EntityFactory.EntityType.site, id)));
        }
        events.add(new DbEvent(DbEvent.Action.update, this.getPolicy().getReference()));
        return events;
    }
}
