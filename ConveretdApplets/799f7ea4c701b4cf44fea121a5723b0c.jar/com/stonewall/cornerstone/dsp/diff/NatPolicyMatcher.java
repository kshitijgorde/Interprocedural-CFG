// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.diff;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import com.stonewall.cornerstone.entity.ServiceSet;
import java.util.List;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.dsp.loader.LoaderException;
import com.stonewall.cornerstone.entity.EntityFactory;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.dsp.loader.Loader;
import org.xmodel.log.Log;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IPath;
import org.xmodel.diff.RuleBasedMatcher;

public class NatPolicyMatcher extends RuleBasedMatcher
{
    final IPath ipHeaderPath;
    final IExpression srcEntityExpr;
    final IExpression dstEntityExpr;
    private IExpression interfaceMatchExpression;
    static final Log log;
    
    static {
        log = Log.getLog(NatPolicyMatcher.class);
    }
    
    public NatPolicyMatcher(final Loader loader) {
        this.ipHeaderPath = XPath.createPath("en:selector/en:ipHeader");
        this.srcEntityExpr = XPath.createExpression("en:source/*");
        this.dstEntityExpr = XPath.createExpression("en:destination/*");
        this.interfaceMatchExpression = XPath.createExpression("($lhs/en:ingress/en:ipInterface/@id = $rhs/en:ingress/en:ipInterface/@id) and ($lhs/en:egress/en:ipInterface/@id = $rhs/en:egress/en:ipInterface/@id)");
        try {
            final MatcherFactory factory = (MatcherFactory)loader.newInstance("diff.MatcherFactory", new Object[0]);
            IExpression expr = XPath.createExpression("self::en:networks/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.network));
            expr = XPath.createExpression("self::en:hosts/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.host));
            expr = XPath.createExpression("self::en:addressGroups/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.addressGroup));
            expr = XPath.createExpression("self::en:ipServices/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.ipService));
            expr = XPath.createExpression("self::en:ipServiceGroups/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.ipServiceGroup));
            expr = XPath.createExpression("self::en:ipInterfaces/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.ipInterface));
            expr = XPath.createExpression("self::en:anys/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.any));
        }
        catch (LoaderException le) {
            NatPolicyMatcher.log.error(this, le);
        }
    }
    
    @Override
    public boolean shouldDiff(final IModelObject object, final String attrName, final boolean lhs) {
        return attrName == null || (!attrName.equals("id") && !attrName.equals("parent") && !attrName.equals("pending") && !attrName.equals("type") && !attrName.equals("timestamp") && !attrName.equals("referenced") && !attrName.equals("phase"));
    }
    
    @Override
    public boolean isList(final IModelObject parent) {
        return parent.isType("en:ruleSet");
    }
    
    @Override
    public boolean isMatch(final IModelObject localChild, final IModelObject foreignChild) {
        if (localChild.isType("en:natRule") && foreignChild.isType("en:natRule")) {
            return this.compareRule(localChild, foreignChild);
        }
        return localChild.isType(foreignChild.getType());
    }
    
    protected boolean compareRule(final IModelObject lhs, final IModelObject rhs) {
        final IModelObject lhsSelector = this.ipHeaderPath.query(lhs, null).get(0);
        final IModelObject rhsSelector = this.ipHeaderPath.query(rhs, null).get(0);
        return this.compareSelectors(lhsSelector, rhsSelector) && this.compareInterfaces(lhsSelector, rhsSelector);
    }
    
    protected boolean compareSelectors(final IModelObject lhs, final IModelObject rhs) {
        try {
            final IModelObject lsrc = this.srcEntityExpr.queryFirst(lhs);
            final IModelObject ldst = this.dstEntityExpr.queryFirst(lhs);
            final IModelObject rsrc = this.srcEntityExpr.queryFirst(rhs);
            final IModelObject rdst = this.dstEntityExpr.queryFirst(rhs);
            if (!lsrc.getID().equals(rsrc.getID())) {
                return false;
            }
            if (!ldst.getID().equals(rdst.getID())) {
                return false;
            }
            final ServiceSet lset = new ServiceSet(lhs.getFirstChild("en:services"));
            final ServiceSet rset = new ServiceSet(rhs.getFirstChild("en:services"));
            return lset.equals(rset);
        }
        catch (Exception e) {
            NatPolicyMatcher.log.error(this, e);
            return false;
        }
    }
    
    protected boolean compareInterfaces(final IModelObject lhs, final IModelObject rhs) {
        this.interfaceMatchExpression.setVariable("lhs", lhs);
        this.interfaceMatchExpression.setVariable("rhs", rhs);
        return this.interfaceMatchExpression.evaluateBoolean(new Context(lhs), false);
    }
}
