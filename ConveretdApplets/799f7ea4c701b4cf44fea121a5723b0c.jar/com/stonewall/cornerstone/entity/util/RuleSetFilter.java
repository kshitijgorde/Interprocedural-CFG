// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import com.stonewall.cornerstone.entity.policy.RuleSet;
import com.stonewall.cornerstone.entity.policy.PolicyRule;

public interface RuleSetFilter
{
    boolean accept(final PolicyRule p0, final RuleSet p1);
}
