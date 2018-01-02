// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

public abstract class PolicyIssueRepairer
{
    protected PolicyIssue issue;
    
    public PolicyIssueRepairer(final PolicyIssue issue) {
        this.issue = issue;
    }
    
    public abstract boolean repair(final Policy p0);
}
