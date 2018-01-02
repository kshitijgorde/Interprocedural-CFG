// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.nat;

import java.util.Iterator;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.entity.EntityReference;
import java.util.ArrayList;
import com.stonewall.cornerstone.entity.policy.PolicyPart;
import com.stonewall.cornerstone.entity.policy.PolicyRule;
import java.util.Collection;
import java.util.List;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.NatDbAccess;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.Label;
import org.xmodel.IModelObject;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.entity.EntityFactory;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;

public class NatPolicy extends DeployablePolicy
{
    private final IExpression partsExpr;
    private final IExpression ruleIdExpr;
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.natPolicy.getQualifiedName();
    }
    
    public NatPolicy() {
        super(NatPolicy.tag, Type.nat);
        this.partsExpr = XPath.createExpression("en:natRule/en:packet/en:source/*| \nen:natRule/en:packet/en:destination/*");
        this.ruleIdExpr = XPath.createExpression("ancestor::en:natRule/@id");
    }
    
    public NatPolicy(final Phase phase) {
        super(NatPolicy.tag, phase, Type.nat);
        this.partsExpr = XPath.createExpression("en:natRule/en:packet/en:source/*| \nen:natRule/en:packet/en:destination/*");
        this.ruleIdExpr = XPath.createExpression("ancestor::en:natRule/@id");
    }
    
    public NatPolicy(final String id, final Phase phase) {
        super(NatPolicy.tag, id, phase, Type.nat);
        this.partsExpr = XPath.createExpression("en:natRule/en:packet/en:source/*| \nen:natRule/en:packet/en:destination/*");
        this.ruleIdExpr = XPath.createExpression("ancestor::en:natRule/@id");
    }
    
    public NatPolicy(final IModelObject o) {
        super(o);
        this.partsExpr = XPath.createExpression("en:natRule/en:packet/en:source/*| \nen:natRule/en:packet/en:destination/*");
        this.ruleIdExpr = XPath.createExpression("ancestor::en:natRule/@id");
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new NatDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public Entity fetchByParent(final String parent, final Label label) throws DbException {
        return new NatDbAccess(label).fetchByDevice(parent, this.getPhase(), false);
    }
    
    @Override
    public Entity fetchRefByParent(final String parent, final Label label) throws DbException {
        return new NatDbAccess(label).fetchByDevice(parent, this.getPhase(), true);
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new NatDbAccess(label).insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new NatDbAccess(label).update(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new NatDbAccess(label).delete(this);
    }
    
    public void setRules(final List<NatRule> rules) {
        this.getRuleSet().replaceRules(rules);
    }
    
    public Collection<NatRule> getRules() {
        final Collection<NatRule> rules = this.getRuleSet().getRules();
        return rules;
    }
    
    public NatRule getRule(final String id) {
        return this.getRuleSet().getRuleById(id);
    }
    
    @Override
    protected void init() {
        super.init();
        this.setPhase(Phase.working);
    }
    
    @Override
    public RuleSet getRuleSet() {
        return new RuleSet(this.getReference(), this.root.getCreateChild("en:ruleSet"));
    }
    
    @Override
    public Policy clone() {
        final IModelObject e = this.getRoot().cloneTree();
        return new NatPolicy(e);
    }
    
    @Override
    protected List<IModelObject> idsToInvalidate(final IModelObject e) {
        final IExpression path = XPath.createExpression("./@id|./en:ruleSet/en:natRule/@id");
        final List<IModelObject> ids = path.query(e, null);
        return ids;
    }
    
    public List<PolicyPart> getParts() {
        final List<PolicyPart> parts = new ArrayList<PolicyPart>();
        final List<IModelObject> l = this.partsExpr.query(this.root, null);
        for (final IModelObject e : l) {
            final IModelObject ruleId = this.ruleIdExpr.queryFirst(e);
            parts.add(new NatRulePart(new EntityReference(e), Xlate.get(ruleId, (String)null), this));
        }
        return parts;
    }
}
