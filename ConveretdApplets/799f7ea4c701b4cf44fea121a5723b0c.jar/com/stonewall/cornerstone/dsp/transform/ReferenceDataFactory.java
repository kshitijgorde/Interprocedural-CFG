// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.transform;

import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityReference;
import java.util.Comparator;
import com.stonewall.cornerstone.entity.policy.ReferenceData;
import com.stonewall.cornerstone.entity.IPInterface;
import com.stonewall.cornerstone.entity.Entity;
import java.util.HashMap;
import com.stonewall.cornerstone.entity.EntityFactory;
import java.util.Map;

public class ReferenceDataFactory
{
    private Map<EntityFactory.EntityType, IRule> register;
    
    public ReferenceDataFactory() {
        this.register = new HashMap<EntityFactory.EntityType, IRule>();
        this.register(EntityFactory.EntityType.network, new ZonePositionalRule());
        this.register(EntityFactory.EntityType.host, new ZonePositionalRule());
        this.register(EntityFactory.EntityType.addressGroup, new ZonePositionalRule());
        this.register(EntityFactory.EntityType.any, new ZonePositionalRule());
    }
    
    public ReferenceData.RefData create(Entity entity, final IPInterface iface) {
        final IRule rule = this.getRule(entity.getEntityType());
        entity = rule.create(entity, iface);
        return ReferenceData.createRef(entity);
    }
    
    public Comparator<ReferenceData.RefData> getIdComparator(final ReferenceData.RefData ref) {
        final EntityReference eref = ref.getEntityReference();
        final IRule rule = this.getRule(eref.getEntityType());
        return rule.getIdComparator();
    }
    
    public Comparator<ReferenceData.RefData> getReferencedComparator(final ReferenceData.RefData ref) {
        final EntityReference eref = ref.getEntityReference();
        final IRule rule = this.getRule(eref.getEntityType());
        return rule.getReferencedComparator();
    }
    
    protected void register(final EntityFactory.EntityType type, final IRule rule) {
        this.register.put(type, rule);
    }
    
    private IRule getRule(final EntityFactory.EntityType type) {
        final IRule rule = this.register.get(type);
        if (rule == null) {
            return new DefaultRule();
        }
        return rule;
    }
    
    public class DefaultRule implements IRule
    {
        protected RefComparator id;
        protected RefComparator referenced;
        
        public DefaultRule() {
            (this.id = new RefComparator()).compareAttribute("id");
            (this.referenced = new RefComparator()).compareAttribute("referenced");
        }
        
        @Override
        public Entity create(final Entity entity, final IPInterface iface) {
            return entity;
        }
        
        @Override
        public Comparator<ReferenceData.RefData> getIdComparator() {
            return this.id;
        }
        
        @Override
        public Comparator<ReferenceData.RefData> getReferencedComparator() {
            return this.referenced;
        }
    }
    
    public class ZonePositionalRule extends DefaultRule
    {
        public ZonePositionalRule() {
            this.id.compareChild("en:zone");
            this.referenced.compareChild("en:zone");
        }
        
        @Override
        public Entity create(final Entity entity, final IPInterface iface) {
            final IModelObject e = entity.getRoot();
            if (iface != null) {
                e.getCreateChild("en:zone").setValue(iface.getZone());
            }
            return entity;
        }
    }
    
    public class InterfacePositionalRule extends DefaultRule
    {
        public InterfacePositionalRule() {
            this.id.compareChild("en:interface");
            this.referenced.compareChild("en:interface");
        }
        
        @Override
        public Entity create(final Entity entity, final IPInterface iface) {
            final IModelObject e = entity.getRoot();
            if (iface != null) {
                e.getCreateChild("en:interface").setValue(iface.getZone());
            }
            return entity;
        }
    }
    
    public interface IRule
    {
        Entity create(final Entity p0, final IPInterface p1);
        
        Comparator<ReferenceData.RefData> getIdComparator();
        
        Comparator<ReferenceData.RefData> getReferencedComparator();
    }
}
