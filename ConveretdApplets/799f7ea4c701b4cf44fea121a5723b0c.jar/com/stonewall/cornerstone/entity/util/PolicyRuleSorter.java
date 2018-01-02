// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import com.stonewall.cornerstone.entity.policy.security.Action;
import com.stonewall.cornerstone.entity.policy.PolicyRule;
import java.util.Comparator;
import java.util.Collection;
import com.stonewall.cornerstone.entity.policy.security.SecurityRule;
import com.stonewall.cornerstone.dsort.SortedCollection;
import com.stonewall.cornerstone.entity.policy.RuleSet;

public class PolicyRuleSorter
{
    public void sort(final RuleSet set) {
        final SortedCollection<SecurityRule> list = new SortedCollection<SecurityRule>();
        final Collection<SecurityRule> rules = set.getRules();
        list.addAll((Collection<?>)rules);
        list.setComparator(new RuleTypeComparator());
        list.sort();
        set.replaceRules(list);
    }
    
    class RuleTypeComparator implements Comparator<SecurityRule>
    {
        @Override
        public int compare(final SecurityRule a, final SecurityRule b) {
            if (!a.getActionType().equals(Action.Type.filter) && !b.getActionType().equals(Action.Type.filter)) {
                return 0;
            }
            if (a.getActionType().equals(Action.Type.filter) && b.getActionType().equals(Action.Type.filter)) {
                return 0;
            }
            if (a.getActionType().equals(Action.Type.filter) && !b.getActionType().equals(Action.Type.filter)) {
                return 1;
            }
            return -1;
        }
    }
}
