// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import com.stonewall.cornerstone.entity.EntityReference;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.entity.Entity;

public abstract class Policy extends Entity
{
    public static final Log log;
    
    static {
        log = Log.getLog(Policy.class);
    }
    
    public Policy(final String tag, final Type type) {
        super(tag);
        this.init();
        this.setType(type);
    }
    
    public Policy(final String tag, final String id, final Type type) {
        super(tag, id);
        this.init();
        this.setType(type);
    }
    
    public Policy(final IModelObject o) {
        super(o);
    }
    
    protected void init() {
        this.root.getCreateChild("en:ruleSet");
    }
    
    public void setType(final Type type) {
        this.root.setAttribute("type", type.name());
    }
    
    public Type getType() {
        return Type.valueOf(Xlate.get(this.root, "type", (String)null));
    }
    
    public abstract RuleSet getRuleSet();
    
    public List<PolicyRule> getClonedRules() {
        final List<PolicyRule> cloned = new ArrayList<PolicyRule>();
        final Collection<PolicyRule> rules = this.getRuleSet().getRules();
        for (final PolicyRule r : rules) {
            cloned.add((PolicyRule)r.clone());
        }
        return cloned;
    }
    
    public void addRule(final PolicyRule rule) {
        this.root.getFirstChild("en:ruleSet").addChild(rule.getRoot().cloneTree());
    }
    
    public void addRules(final Collection<? extends PolicyRule> rules) {
        for (final PolicyRule rule : rules) {
            this.addRule(rule);
        }
    }
    
    public void replaceRules(final List<? extends PolicyRule> rules) {
        final IModelObject ruleSet = this.root.getFirstChild("en:ruleSet");
        ruleSet.removeChildren();
        this.addRules(rules);
    }
    
    public void replaceRuleSet(final RuleSet set, final boolean resetIds) {
        final RuleSet thisSet = this.getRuleSet();
        thisSet.clear();
        final Collection<PolicyRule> rules = set.getRules();
        for (final PolicyRule rule : rules) {
            final PolicyRule clone = (PolicyRule)rule.clone();
            if (resetIds) {
                clone.resetIds();
            }
            thisSet.add(clone);
        }
    }
    
    public void replaceRuleSet(final RuleSet set) {
        this.replaceRuleSet(set, true);
    }
    
    public void setRulePriority() {
        int i = 1;
        final List<PolicyRule> rules = (List<PolicyRule>)(List)this.getRuleSet().getRules();
        for (final PolicyRule rule : rules) {
            rule.setPriority(i++);
        }
    }
    
    public void setRuleSet(final RuleSet set) {
        IModelObject ruleSet = this.root.getFirstChild("en:ruleSet");
        ruleSet = set.getRoot().cloneTree();
        this.root.addChild(ruleSet);
    }
    
    public void replaceContent(final Policy p, final boolean resetIds) {
        this.replaceRuleSet(p.getRuleSet(), resetIds);
    }
    
    @Override
    public EntityReference getReference() {
        final EntityReference ref = super.getReference();
        ref.setAttribute("type", this.getType().name());
        return ref;
    }
    
    public abstract Policy clone();
    
    public enum Type
    {
        site("site", 0), 
        device("device", 1), 
        nat("nat", 2), 
        compliance("compliance", 3), 
        admin("admin", 4);
        
        private Type(final String s, final int n) {
        }
    }
}
