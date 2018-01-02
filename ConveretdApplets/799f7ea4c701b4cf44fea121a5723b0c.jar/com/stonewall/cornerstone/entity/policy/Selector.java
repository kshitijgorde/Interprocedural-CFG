// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.ServiceSet;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.entity.policy.security.IPHeader;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.Entity;

public abstract class Selector extends Entity
{
    public static Selector createSelector(final IModelObject e) {
        if (e.getType().equals("en:ipHeader")) {
            return new IPHeader(e);
        }
        return null;
    }
    
    public Selector(final String tag) {
        super(tag);
    }
    
    public Selector(final IModelObject o) {
        super(o);
    }
    
    @Override
    public void setId(final String id) {
        super.setId(id);
    }
    
    public void setOptimized(final boolean value) {
        this.root.setAttribute("optimized", String.valueOf(value));
    }
    
    public List<Endpoint> getEndpoints() {
        final List<Endpoint> eps = new ArrayList<Endpoint>();
        eps.add(this.getSource());
        eps.add(this.getDestination());
        return eps;
    }
    
    public ServiceSet getServiceSet() {
        return new ServiceSet(this.root.getFirstChild("en:services"));
    }
    
    public void replaceServiceSet(final ServiceSet set) {
        this.root.removeChildren("en:services");
        this.root.addChild(set.getRoot().cloneTree());
    }
    
    public abstract void reverse();
    
    public abstract boolean isInverse(final Selector p0);
    
    public abstract Endpoint getSource();
    
    public abstract void setSource(final Endpoint p0);
    
    public abstract Endpoint getDestination();
    
    public abstract void setDestination(final Endpoint p0);
    
    public abstract void setDirection(final Direction p0);
    
    public abstract void setIngress(final EntityReference p0);
    
    public abstract EntityReference getIngress();
    
    public abstract void setEgress(final EntityReference p0);
    
    public abstract EntityReference getEgress();
    
    public abstract Selector clone();
    
    public String toErrorString() {
        return String.valueOf(this.getSource().toErrorString()) + "/" + this.getDestination().toErrorString();
    }
    
    public enum Direction
    {
        outbound("outbound", 0), 
        inbound("inbound", 1);
        
        private Direction(final String s, final int n) {
        }
    }
}
