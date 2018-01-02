// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import com.stonewall.cornerstone.entity.policy.security.Action;
import java.util.Iterator;
import java.util.Collection;
import com.stonewall.cornerstone.entity.policy.PolicyRule;
import java.util.ArrayList;
import org.xmodel.log.Log;
import java.util.List;
import com.stonewall.cornerstone.entity.policy.RuleSet;

public abstract class RuleSetBuilder
{
    protected RuleSet set;
    protected boolean unique;
    protected boolean preserveOrder;
    private List<RuleSetFilter> ruleFilters;
    protected List<RuleSetProcessor> ruleProcessors;
    static final Log log;
    
    static {
        log = Log.getLog(RuleSetBuilder.class);
    }
    
    public RuleSetBuilder(final RuleSet set) {
        this(set, false);
    }
    
    public RuleSetBuilder(final RuleSet set, final boolean unique) {
        this.preserveOrder = false;
        this.ruleFilters = new ArrayList<RuleSetFilter>();
        this.ruleProcessors = new ArrayList<RuleSetProcessor>();
        this.set = set;
        this.unique = unique;
    }
    
    public RuleSet getRuleSet() {
        return this.set;
    }
    
    public void addFilter(final RuleSetFilter filter) {
        this.ruleFilters.add(filter);
    }
    
    public abstract void filter();
    
    public void addProcessor(final RuleSetProcessor processor) {
        this.ruleProcessors.add(processor);
    }
    
    public abstract void process();
    
    public abstract void add(final PolicyRule p0);
    
    public void addAll(final Collection<? extends PolicyRule> list) {
        for (final PolicyRule rule : list) {
            this.add(rule);
        }
    }
    
    public void addAll(final RuleSet newSet) {
        final Collection<PolicyRule> rules = newSet.getRules();
        this.addAll(rules);
    }
    
    public void addAfter(final PolicyRule context, final PolicyRule rule) {
        final int index = this.getRuleIndex(context);
        this.set.getRoot().addChild(rule.getRoot().cloneTree(), index + 1);
    }
    
    public void replaceRulesByType(final Collection<PolicyRule> rules, final Action.Type type) {
        this.set.replaceRulesByType(rules, type);
    }
    
    public void append(final List<PolicyRule> rules) {
        for (final PolicyRule rule : rules) {
            this.set.getRoot().addChild(rule.getRoot().cloneTree());
        }
    }
    
    public void preserveOrder(final boolean flag) {
        this.preserveOrder = flag;
    }
    
    @Override
    public String toString() {
        return this.set.toString();
    }
    
    protected boolean accept(final PolicyRule rule) {
        for (final RuleSetFilter filter : this.ruleFilters) {
            if (!filter.accept(rule, this.set)) {
                return false;
            }
        }
        return true;
    }
    
    protected boolean unique(final PolicyRule rule) {
        final Collection<PolicyRule> rules = this.set.getRules();
        for (final PolicyRule r : rules) {
            if (r.equals(rule)) {
                return false;
            }
        }
        return true;
    }
    
    private int getRuleIndex(final PolicyRule rule) {
        int i = this.set.getRoot().getChildren().indexOf(rule.getRoot());
        if (i >= 0) {
            return i;
        }
        i = 0;
        final Collection<PolicyRule> rules = this.set.getRules();
        for (final PolicyRule r : rules) {
            if (rule.getId().equals(r.getId())) {
                break;
            }
            ++i;
        }
        return i;
    }
}
