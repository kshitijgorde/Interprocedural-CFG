// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import com.stonewall.cornerstone.entity.policy.security.Action;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.policy.security.SecurityRule;
import com.stonewall.cornerstone.entity.policy.PolicyRule;
import java.util.Collection;
import com.stonewall.cornerstone.entity.policy.RuleSet;
import org.xmodel.log.Log;

public class SecurityRuleSetBuilder extends RuleSetBuilder
{
    private int filterIndex;
    static final Log log;
    
    static {
        log = Log.getLog(SecurityRuleSetBuilder.class);
    }
    
    public SecurityRuleSetBuilder(final RuleSet set) {
        this(set, false);
    }
    
    public SecurityRuleSetBuilder(final RuleSet set, final boolean unique) {
        super(set, unique);
        this.filterIndex = 0;
        this.filterIndex = this.calculateFilterIndex(set);
    }
    
    @Override
    public void filter() {
        final Collection<SecurityRule> rules = this.set.getRules();
        this.set.clear();
        this.filterIndex = this.calculateFilterIndex(this.set);
        this.addAll(rules);
    }
    
    @Override
    public void process() {
        for (final RuleSetProcessor processor : this.ruleProcessors) {
            processor.execute(this.set);
        }
        this.filterIndex = this.calculateFilterIndex(this.set);
    }
    
    @Override
    public void add(final PolicyRule rule) {
        final SecurityRule security = (SecurityRule)rule;
        if (this.unique && !this.unique(security)) {
            return;
        }
        if (!this.accept(security)) {
            return;
        }
        switch (security.getActionType()) {
            case filter: {
                this.addFilterRule(security);
                break;
            }
        }
    }
    
    private void addFilterRule(final SecurityRule rule) {
        if (rule.getPriority() == -1) {
            this.set.getRoot().addChild(rule.getRoot().cloneTree());
            return;
        }
        if (this.preserveOrder) {
            this.set.getRoot().addChild(rule.getRoot().cloneTree());
        }
        else {
            this.set.getRoot().addChild(rule.getRoot().cloneTree(), this.filterIndex);
        }
    }
    
    private int calculateFilterIndex(final RuleSet set) {
        int i = 0;
        final Collection<SecurityRule> rules = set.getRules();
        for (final SecurityRule rule : rules) {
            if (rule.getActionType().equals(Action.Type.filter)) {
                break;
            }
            ++i;
        }
        return i;
    }
}
