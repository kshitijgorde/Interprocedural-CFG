// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.diff;

import com.stonewall.cornerstone.entity.ServiceSet;
import java.util.List;
import org.xmodel.IChangeSet;
import org.xmodel.diff.IXmlMatcher;
import com.stonewall.cornerstone.diff.EntityContentMatcher;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.dsp.loader.LoaderException;
import com.stonewall.cornerstone.entity.EntityFactory;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.dsp.loader.Loader;
import org.xmodel.log.Log;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IPath;
import org.xmodel.diff.RuleBasedMatcher;

public class DevicePolicyMatcher extends RuleBasedMatcher
{
    final IPath actionTypePath;
    final IPath defTypePath;
    final IPath ipHeaderPath;
    final IExpression srcEntityExpr;
    final IExpression dstEntityExpr;
    final IExpression ifaceEntityExpr;
    protected XmlDiffer differ;
    static final Log log;
    
    static {
        log = Log.getLog(DevicePolicyMatcher.class);
    }
    
    public DevicePolicyMatcher(final Loader loader) {
        this.actionTypePath = XPath.createPath("en:action/*");
        this.defTypePath = XPath.createPath("en:definition/*");
        this.ipHeaderPath = XPath.createPath("en:selector/en:ipHeader");
        this.srcEntityExpr = XPath.createExpression("en:source/*");
        this.dstEntityExpr = XPath.createExpression("en:destination/*");
        this.ifaceEntityExpr = XPath.createExpression("en:interface/en:ipInterface");
        this.differ = new XmlDiffer();
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
            expr = XPath.createExpression("self::en:p1Proposals/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.p1Proposal));
            expr = XPath.createExpression("self::en:p2Proposals/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.p2Proposal));
            expr = XPath.createExpression("self::en:anys/parent::en:references");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.any));
            expr = XPath.createExpression("self::en:securityRule/parent::en:ruleSet");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.securityRule));
            expr = XPath.createExpression("self::en:securityTunnel/parent::en:tunnels");
            this.addRule(expr, factory.getMatcher(EntityFactory.EntityType.securityTunnel));
        }
        catch (LoaderException le) {
            DevicePolicyMatcher.log.error(this, le);
        }
    }
    
    @Override
    public boolean shouldDiff(final IModelObject object, final String attrName, final boolean lhs) {
        return super.shouldDiff(object, attrName, lhs) && (attrName == null || (!attrName.equals("id") && !attrName.equals("parent") && !attrName.equals("pending") && !attrName.equals("timestamp") && !attrName.equals("referenced") && !attrName.equals("phase")));
    }
    
    @Override
    public boolean shouldDiff(final IModelObject object, final boolean lhs) {
        return super.shouldDiff(object, lhs) && (!object.isType("en:name") && !object.isType("en:priority"));
    }
    
    @Override
    public boolean isList(final IModelObject parent) {
        return super.isList(parent) || parent.isType("en:ruleSet") || parent.isType("en:tunnels") || parent.isType("en:selector") || (parent.isType("en:p1Proposals") && !parent.getParent().isType("en:references")) || (parent.isType("en:p2Proposals") && !parent.getParent().isType("en:references"));
    }
    
    @Override
    public boolean isMatch(final IModelObject localChild, final IModelObject foreignChild) {
        if (localChild.isType("en:securityRule") && foreignChild.isType("en:securityRule")) {
            final IModelObject lhsAction = this.actionTypePath.queryFirst(localChild);
            final IModelObject rhsAction = this.actionTypePath.queryFirst(foreignChild);
            if (!lhsAction.isType(rhsAction.getType())) {
                return false;
            }
            if (this.compareSelectors(localChild, foreignChild)) {
                return true;
            }
            if (lhsAction.isType("en:filter")) {
                return false;
            }
            this.differ.setMatcher(new EntityContentMatcher());
            return this.differ.diff(lhsAction, rhsAction, null);
        }
        else {
            if (localChild.isType("en:securityTunnel") && foreignChild.isType("en:securityTunnel")) {
                final IModelObject lhsDef = this.defTypePath.queryFirst(localChild);
                final IModelObject rhsDef = this.defTypePath.queryFirst(foreignChild);
                if (lhsDef != null && rhsDef != null) {
                    if (!lhsDef.isType(rhsDef.getType())) {
                        return false;
                    }
                    final List<IModelObject> lifaces = this.ifaceEntityExpr.query(localChild, null);
                    final List<IModelObject> rifaces = this.ifaceEntityExpr.query(foreignChild, null);
                    final IModelObject liface1 = lifaces.get(0);
                    final IModelObject liface2 = lifaces.get(1);
                    final IModelObject riface1 = rifaces.get(0);
                    final IModelObject riface2 = rifaces.get(1);
                    if (!liface1.getID().equals(riface1.getID()) && !liface2.getID().equals(riface2.getID()) && !liface1.getID().equals(riface2.getID()) && !liface2.getID().equals(riface1.getID())) {
                        return false;
                    }
                    this.differ.setMatcher(new EntityContentMatcher());
                    return this.differ.diff(lhsDef, rhsDef, null);
                }
            }
            if (localChild.isType("en:ipHeader") && foreignChild.isType("en:ipHeader")) {
                return localChild.isType(foreignChild.getType());
            }
            return super.isMatch(localChild, foreignChild);
        }
    }
    
    protected boolean compareSelectors(final IModelObject lhsRule, final IModelObject rhsRule) {
        final List<IModelObject> lhsSelectors = this.ipHeaderPath.query(lhsRule, null);
        final List<IModelObject> rhsSelectors = this.ipHeaderPath.query(rhsRule, null);
        boolean singleSelector = false;
        if (lhsSelectors.size() == rhsSelectors.size()) {
            singleSelector = false;
        }
        else {
            if (lhsSelectors.size() <= 1 || rhsSelectors.size() != 1) {
                return false;
            }
            singleSelector = true;
        }
        for (int i = 0; i < lhsSelectors.size(); ++i) {
            final IModelObject lhsSelector = lhsSelectors.get(i);
            IModelObject rhsSelector = null;
            if (singleSelector) {
                rhsSelector = rhsSelectors.get(0);
            }
            else {
                rhsSelector = rhsSelectors.get(i);
            }
            if (singleSelector) {
                if (this.compareSelector(lhsSelector, rhsSelector)) {
                    return true;
                }
            }
            else if (!this.compareSelector(lhsSelector, rhsSelector)) {
                return false;
            }
        }
        return true;
    }
    
    protected boolean compareSelector(final IModelObject lhsSelector, final IModelObject rhsSelector) {
        try {
            final IModelObject lsrc = this.srcEntityExpr.queryFirst(lhsSelector);
            final IModelObject ldst = this.dstEntityExpr.queryFirst(lhsSelector);
            final IModelObject rsrc = this.srcEntityExpr.queryFirst(rhsSelector);
            final IModelObject rdst = this.dstEntityExpr.queryFirst(rhsSelector);
            if (!lsrc.getID().equals(rsrc.getID())) {
                return false;
            }
            if (!ldst.getID().equals(rdst.getID())) {
                return false;
            }
            final ServiceSet lset = new ServiceSet(lhsSelector.getFirstChild("en:services"));
            final ServiceSet rset = new ServiceSet(rhsSelector.getFirstChild("en:services"));
            return lset.equals(rset);
        }
        catch (Exception e) {
            DevicePolicyMatcher.log.error(this, e);
            return false;
        }
    }
}
