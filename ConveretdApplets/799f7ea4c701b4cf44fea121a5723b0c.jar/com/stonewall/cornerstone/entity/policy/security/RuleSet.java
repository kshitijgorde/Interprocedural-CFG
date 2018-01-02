// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import java.util.List;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;

public class RuleSet extends com.stonewall.cornerstone.entity.policy.RuleSet
{
    private final IExpression adminRulesExpr;
    private final IExpression filterRulesExpr;
    
    public RuleSet() {
        this.adminRulesExpr = XPath.createExpression("./*[en:action/en:filter and (@type='adminACL' or @type='adminTerminating')]");
        this.filterRulesExpr = XPath.createExpression("./*[en:action/en:filter]");
    }
    
    public RuleSet(final EntityReference ref, final IModelObject root) {
        super(ref, root);
        this.adminRulesExpr = XPath.createExpression("./*[en:action/en:filter and (@type='adminACL' or @type='adminTerminating')]");
        this.filterRulesExpr = XPath.createExpression("./*[en:action/en:filter]");
    }
    
    public RuleSet(final IModelObject root) {
        super(root);
        this.adminRulesExpr = XPath.createExpression("./*[en:action/en:filter and (@type='adminACL' or @type='adminTerminating')]");
        this.filterRulesExpr = XPath.createExpression("./*[en:action/en:filter]");
    }
    
    @Override
    public RuleSet clone() {
        return new RuleSet(this.root.cloneTree());
    }
    
    public static SecurityRule create(final IModelObject e) {
        SecurityRule rule = null;
        switch (Action.getActionType(e)) {
            case filter: {
                rule = new FilterRule(e);
                if (rule.isAdminACL() || rule.isAdminTerminating()) {
                    rule = new AdminRule(e);
                    break;
                }
                break;
            }
        }
        return rule;
    }
    
    @Override
    protected SecurityRule buildRule(final IModelObject e) {
        final SecurityRule rule = create(e);
        rule.setParent(this.parent);
        return rule;
    }
    
    public List<SecurityRule> getAdminRules() {
        return this.selectRules(this.adminRulesExpr);
    }
    
    public List<SecurityRule> getFilterRules() {
        return this.selectRules(this.filterRulesExpr);
    }
}
