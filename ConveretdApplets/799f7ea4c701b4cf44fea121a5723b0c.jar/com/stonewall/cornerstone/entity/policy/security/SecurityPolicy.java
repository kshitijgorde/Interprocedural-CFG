// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.policy.PolicyPart;
import java.util.ArrayList;
import com.stonewall.cornerstone.entity.policy.ReferenceData;
import java.util.List;
import java.util.Iterator;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.SecurityPolicyDbAccess;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.Label;
import org.xmodel.IModelObject;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.entity.policy.Policy;
import com.stonewall.cornerstone.entity.EntityFactory;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;

public class SecurityPolicy extends DeployablePolicy
{
    private final IExpression rulePartsExpr;
    private final IExpression tunnelPartsExpr;
    private final IExpression ruleIdExpr;
    private final IExpression tunnelIdExpr;
    private final IExpression vpnTunnelsExpr;
    public static final String tag;
    public static final String WORKING_DEFAULT_ID = "WPOLICY0";
    public static final String NET_DEFAULT_ID = "NPOLICY0";
    public static final String ADMIN_DEFAULT_ID = "APOLICY0";
    
    static {
        tag = EntityFactory.EntityType.securityPolicy.getQualifiedName();
    }
    
    public SecurityPolicy(final String id, final Phase phase, final Type type) {
        super(SecurityPolicy.tag, id, phase, type);
        this.rulePartsExpr = XPath.createExpression("en:ruleSet/en:securityRule/en:selector/*/en:source/*| \nen:ruleSet/en:securityRule/en:selector/*/en:destination/*| \nen:ruleSet/en:securityRule/en:selector/*/en:services/*");
        this.tunnelPartsExpr = XPath.createExpression("en:tunnels/en:securityTunnel/en:definition/en:ike/en:phase1/en:p1Proposals/en:p1Proposal| \nen:tunnels/en:securityTunnel/en:definition/en:ike/en:phase2/en:p2Proposals/en:p2Proposal");
        this.ruleIdExpr = XPath.createExpression("ancestor::en:securityRule/@id");
        this.tunnelIdExpr = XPath.createExpression("ancestor::en:securityTunnel/@id");
        this.vpnTunnelsExpr = XPath.createExpression("./en:tunnels/*[en:definition/en:manualKey or en:definition/en:ike]");
    }
    
    public SecurityPolicy(final Phase phase, final Type type) {
        super(SecurityPolicy.tag, phase, type);
        this.rulePartsExpr = XPath.createExpression("en:ruleSet/en:securityRule/en:selector/*/en:source/*| \nen:ruleSet/en:securityRule/en:selector/*/en:destination/*| \nen:ruleSet/en:securityRule/en:selector/*/en:services/*");
        this.tunnelPartsExpr = XPath.createExpression("en:tunnels/en:securityTunnel/en:definition/en:ike/en:phase1/en:p1Proposals/en:p1Proposal| \nen:tunnels/en:securityTunnel/en:definition/en:ike/en:phase2/en:p2Proposals/en:p2Proposal");
        this.ruleIdExpr = XPath.createExpression("ancestor::en:securityRule/@id");
        this.tunnelIdExpr = XPath.createExpression("ancestor::en:securityTunnel/@id");
        this.vpnTunnelsExpr = XPath.createExpression("./en:tunnels/*[en:definition/en:manualKey or en:definition/en:ike]");
    }
    
    public SecurityPolicy(final IModelObject o) {
        super(o);
        this.rulePartsExpr = XPath.createExpression("en:ruleSet/en:securityRule/en:selector/*/en:source/*| \nen:ruleSet/en:securityRule/en:selector/*/en:destination/*| \nen:ruleSet/en:securityRule/en:selector/*/en:services/*");
        this.tunnelPartsExpr = XPath.createExpression("en:tunnels/en:securityTunnel/en:definition/en:ike/en:phase1/en:p1Proposals/en:p1Proposal| \nen:tunnels/en:securityTunnel/en:definition/en:ike/en:phase2/en:p2Proposals/en:p2Proposal");
        this.ruleIdExpr = XPath.createExpression("ancestor::en:securityRule/@id");
        this.tunnelIdExpr = XPath.createExpression("ancestor::en:securityTunnel/@id");
        this.vpnTunnelsExpr = XPath.createExpression("./en:tunnels/*[en:definition/en:manualKey or en:definition/en:ike]");
    }
    
    @Override
    protected void init() {
        super.init();
        this.root.getCreateChild("en:ruleGroups");
        this.root.getCreateChild("en:tunnels");
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new SecurityPolicyDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public Entity fetchByParent(final String parent, final Label label) throws DbException {
        return new SecurityPolicyDbAccess(label).fetchByParent(parent, this.getType(), this.getPhase(), false);
    }
    
    @Override
    public Entity fetchRefByParent(final String parent, final Label label) throws DbException {
        return new SecurityPolicyDbAccess(label).fetchByParent(parent, this.getType(), this.getPhase(), true);
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new SecurityPolicyDbAccess(label).insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new SecurityPolicyDbAccess(label).delete(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new SecurityPolicyDbAccess(label).update(this);
    }
    
    @Override
    public RuleSet getRuleSet() {
        return new RuleSet(this.getReference(), this.root.getCreateChild("en:ruleSet"));
    }
    
    public void setRuleGroups(final IModelObject e) {
        final IModelObject ruleGroups = this.root.getCreateChild("en:ruleGroups");
        ruleGroups.removeChildren();
        for (final IModelObject c : e.getChildren()) {
            ruleGroups.addChild(c.cloneTree());
        }
    }
    
    public IModelObject getRuleGroups() {
        return this.getRoot().getFirstChild("en:ruleGroups");
    }
    
    @Override
    protected List<IModelObject> idsToInvalidate(final IModelObject e) {
        final IExpression path = XPath.createExpression("./@id|./en:ruleSet/en:securityRule/@id|./en:ruleSet/en:securityRule/en:selector/*/@id|./en:tunnels/en:securityTunnel/@id");
        final List<IModelObject> ids = path.query(e, null);
        return ids;
    }
    
    protected void build(final ReferenceData data) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Policy clone() {
        final IModelObject e = this.getRoot().cloneTree();
        return new SecurityPolicy(e);
    }
    
    public List<SecurityTunnel> getTunnels() {
        final List<SecurityTunnel> tunnels = new ArrayList<SecurityTunnel>();
        final List<IModelObject> l = this.vpnTunnelsExpr.query(this.root, null);
        for (final IModelObject e : l) {
            SecurityTunnel tunnel = null;
            switch (TunnelDef.getTunnelType(e)) {
                case ike: {
                    tunnel = new IKETunnel(e);
                    break;
                }
                case manualKey: {
                    tunnel = new ManualKeyTunnel(e);
                    break;
                }
            }
            tunnels.add(tunnel);
        }
        return tunnels;
    }
    
    public void addTunnel(final SecurityTunnel tunnel) {
        this.getRoot().getCreateChild("en:tunnels").addChild(tunnel.getRoot());
    }
    
    public void addTunnels(final List<SecurityTunnel> tunnels) {
        for (final SecurityTunnel tunnel : tunnels) {
            this.addTunnel(tunnel);
        }
    }
    
    public void replaceTunnels(final List<SecurityTunnel> tunnels, final boolean resetIds) {
        final IModelObject l = this.root.getCreateChild("en:tunnels");
        l.removeChildren();
        for (final SecurityTunnel tunnel : tunnels) {
            final SecurityTunnel clone = tunnel.clone();
            if (resetIds) {
                clone.resetIds();
            }
            this.addTunnel(clone);
        }
    }
    
    public void removeTunnels() {
        this.getRoot().getCreateChild("en:tunnels").removeChildren();
    }
    
    @Override
    public void replaceContent(final Policy p, final boolean resetIds) {
        final SecurityPolicy secP = (SecurityPolicy)p;
        this.replaceRuleSet(secP.getRuleSet(), resetIds);
        this.replaceTunnels(secP.getTunnels(), false);
    }
    
    public List<PolicyPart> getParts() {
        final List<PolicyPart> parts = new ArrayList<PolicyPart>();
        List<IModelObject> l = this.rulePartsExpr.query(this.root, null);
        for (final IModelObject e : l) {
            final IModelObject ruleId = this.ruleIdExpr.queryFirst(e);
            parts.add(new SecurityPolicyPart(new EntityReference(e), Xlate.get(ruleId, (String)null), this));
        }
        l = this.tunnelPartsExpr.query(this.root, null);
        for (final IModelObject e : l) {
            final IModelObject id = this.tunnelIdExpr.queryFirst(e);
            parts.add(new SecurityPolicyPart(new EntityReference(e), Xlate.get(id, (String)null), this));
        }
        return parts;
    }
}
