// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import com.stonewall.cornerstone.entity.Service;
import com.stonewall.cornerstone.entity.ServiceSet;
import com.stonewall.cornerstone.entity.util.SelectorComparator;
import com.stonewall.cornerstone.entity.query.DeviceQuery;
import java.util.Collection;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import com.stonewall.cornerstone.entity.policy.PolicyRule;
import org.xmodel.IModelObject;

public class FilterRule extends SecurityRule
{
    public FilterRule() {
    }
    
    public FilterRule(final String name, final int priority, final FilterAction.Access access) {
        super(name, priority, new FilterAction(access));
    }
    
    public FilterRule(final String name, final String description, final int priority, final FilterAction.Access access) {
        super(name, priority, new FilterAction(access));
        this.setDescription(description);
    }
    
    public FilterRule(final String name, final String description, final FilterAction.Access access, final boolean secureFlag) {
        super(name, 0, new FilterAction(access));
        this.setDescription(description);
        this.setSecure(secureFlag);
    }
    
    public FilterRule(final IModelObject e) {
        super(e);
    }
    
    public void setSecure(final boolean secure) {
        final FilterAction action = (FilterAction)this.getAction();
        action.setSecure(secure);
    }
    
    public boolean shouldTerminate() {
        return false;
    }
    
    public boolean isSecure() {
        final FilterAction action = (FilterAction)this.getAction();
        return action.isSecure();
    }
    
    public boolean canRoute() {
        return !this.isSecure() || (this.isSecure() && !this.getTunnelIds().isEmpty());
    }
    
    public Action createAction(final IModelObject e) {
        return new FilterAction(e);
    }
    
    @Override
    public PolicyRule clone() {
        final IModelObject clone = this.root.cloneTree();
        final FilterRule rule = new FilterRule(clone);
        return rule;
    }
    
    public Endpoint getSource() {
        return this.getFirstSelector().getSource();
    }
    
    public Endpoint getDestination() {
        return this.getFirstSelector().getDestination();
    }
    
    public IPHeader getSelector() {
        final IModelObject selector = this.getSelectorElements().get(0);
        return new IPHeader(selector);
    }
    
    @Override
    public Collection<String> affectedDevices() {
        final DeviceQuery query = new DeviceQuery();
        final Collection<String> ids = query.affectedDevices(this.getSelectors(), this.isSecure());
        return ids;
    }
    
    @Override
    public SelectorComparator getSelectorComparator() {
        return new FilterSelectorComparator();
    }
    
    public boolean isDenyAll() {
        final Endpoint src = this.getSource();
        final Endpoint dst = this.getDestination();
        if (src == null || dst == null) {
            return false;
        }
        final IPHeader header = this.getSelector();
        final ServiceSet set = header.getServiceSet();
        final Service svc = set.getFirstService();
        return src.isAny() && dst.isAny() && svc.isAny();
    }
}
