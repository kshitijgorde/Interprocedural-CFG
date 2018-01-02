// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import java.util.Iterator;
import java.util.List;
import com.stonewall.cornerstone.entity.policy.Selector;
import org.xmodel.xpath.expression.Context;
import com.stonewall.cornerstone.entity.policy.security.IPHeader;
import com.stonewall.cornerstone.entity.policy.EndpointComparator;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.utility.ILog;
import org.xmodel.xaction.GuardedAction;

public class LocateNatRuleAction extends GuardedAction implements ILog
{
    private String assign;
    private IExpression selectorExpr;
    private IExpression policyExpr;
    private IExpression childrenExpr;
    private IExpression ruleExpr;
    
    public LocateNatRuleAction() {
        this.childrenExpr = XPath.createExpression("en:ruleSet/en:natRule/en:selector/en:ipHeader");
        this.ruleExpr = XPath.createExpression("ancestor::en:natRule");
    }
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        final IModelObject root = document.getRoot();
        this.assign = Xlate.get(root, "assign", (String)null);
        this.selectorExpr = document.getExpression("selector", false);
        this.policyExpr = document.getExpression("policy", false);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        IModelObject result = null;
        final DeployablePolicy p = (DeployablePolicy)EntityFactory.getInstance().createEntity(this.policyExpr.queryFirst(context));
        final IModelObject iselector = this.selectorExpr.queryFirst(context);
        if (iselector == null) {
            throw new IllegalArgumentException("Cannot locate the selector: " + this);
        }
        final EndpointComparator comparator = new EndpointComparator();
        final Selector selector1 = new IPHeader(iselector);
        final List<IModelObject> natSelectors = this.childrenExpr.evaluateNodes(new Context(p.getRoot()));
        for (final IModelObject o : natSelectors) {
            final Selector natSelector = new IPHeader(o);
            if (comparator.contains(natSelector.getSource(), selector1.getSource()) && comparator.contains(natSelector.getDestination(), selector1.getDestination())) {
                result = this.ruleExpr.queryFirst(o);
                break;
            }
        }
        final IVariableScope scope = context.getScope();
        if (scope == null) {
            throw new IllegalArgumentException("Context does not allow variable assignment: " + this);
        }
        scope.set(this.assign, result);
        return null;
    }
}
