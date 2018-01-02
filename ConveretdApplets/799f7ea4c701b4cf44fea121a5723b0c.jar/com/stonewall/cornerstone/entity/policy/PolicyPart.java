// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import java.util.Collections;
import com.stonewall.cornerstone.jms.msg.event.DbEvent;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.PolicyPartDbAccess;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.entity.EntityReference;

public class PolicyPart
{
    private EntityReference entity;
    private String id;
    private Policy policy;
    static final Log log;
    
    static {
        log = Log.getLog(PolicyPart.class);
    }
    
    public PolicyPart() {
    }
    
    public PolicyPart(final EntityReference entity) {
        this.entity = entity;
    }
    
    public PolicyPart(final EntityReference entity, final String id, final Policy policy) {
        this.entity = entity;
        this.id = id;
        this.policy = policy;
    }
    
    public boolean inUse() {
        try {
            final PolicyPartDbAccess access = new PolicyPartDbAccess();
            return access.inUse(this.entity.getId());
        }
        catch (DbException dbe) {
            PolicyPart.log.error(this, dbe);
            return false;
        }
    }
    
    public EntityReference getEntity() {
        return this.entity;
    }
    
    public void setEntity(final EntityReference entity) {
        this.entity = entity;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String value) {
        this.id = value;
    }
    
    public Policy getPolicy() {
        return this.policy;
    }
    
    public void setPolicy(final Policy value) {
        this.policy = value;
    }
    
    public Collection<DbEvent> changed() throws DbException {
        return (Collection<DbEvent>)Collections.emptyList();
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PolicyPart:");
        builder.append("\n\tEntity:");
        builder.append(this.entity.getEncoded());
        builder.append("\n\tPolicy Entity:");
        builder.append(this.id);
        builder.append("\n\tPolicy:");
        builder.append(this.policy);
        return builder.toString();
    }
}
