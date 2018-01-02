// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import java.util.Iterator;
import com.stonewall.cornerstone.entity.policy.PolicyRule;
import java.util.Collection;
import com.stonewall.cornerstone.entity.policy.RuleSet;
import org.xmodel.log.Log;

public class NatRuleSetBuilder extends RuleSetBuilder
{
    static final Log log;
    
    static {
        log = Log.getLog(NatRuleSetBuilder.class);
    }
    
    public NatRuleSetBuilder(final RuleSet set) {
        this(set, false);
    }
    
    public NatRuleSetBuilder(final RuleSet set, final boolean unique) {
        super(set, unique);
    }
    
    @Override
    public RuleSet getRuleSet() {
        return this.set;
    }
    
    @Override
    public void filter() {
        final Collection<PolicyRule> rules = this.set.getRules();
        this.set.clear();
        this.addAll(rules);
    }
    
    @Override
    public void process() {
        for (final RuleSetProcessor processor : this.ruleProcessors) {
            processor.execute(this.set);
        }
    }
    
    @Override
    public void add(final PolicyRule rule) {
        if (this.unique && !this.unique(rule)) {
            return;
        }
        if (!this.accept(rule)) {
            return;
        }
        this.set.getRoot().addChild(rule.getRoot().cloneTree());
    }
}
