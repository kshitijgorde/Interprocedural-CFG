// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.nat;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import java.util.List;
import com.stonewall.cornerstone.entity.policy.Endpoint;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.policy.security.IPHeader;
import com.stonewall.cornerstone.entity.IPInterface;
import com.stonewall.cornerstone.entity.policy.Selector;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.policy.PolicyRule;

public class NatRule extends PolicyRule
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.natRule.getQualifiedName();
    }
    
    public NatRule() {
        super(NatRule.tag);
        this.init();
    }
    
    public NatRule(final String id) {
        super(NatRule.tag, id);
    }
    
    public NatRule(final IModelObject root) {
        super(root);
    }
    
    @Override
    public void setName(final String value) {
        this.root.getCreateChild("en:name").setValue(value);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setSelector(final Selector s) {
        final IModelObject selector = this.root.getCreateChild("en:selector");
        selector.addChild(s.getRoot().cloneTree());
    }
    
    public void setIngress(final IPInterface value) {
        final IPHeader h = new IPHeader(this.ipHeader());
        h.setIngress(value.getReference());
    }
    
    public EntityReference getIngress() {
        return new IPHeader(this.ipHeader()).getIngress();
    }
    
    public void setEgress(final IPInterface value) {
        final IPHeader h = new IPHeader(this.ipHeader());
        h.setEgress(value.getReference());
    }
    
    public EntityReference getEgress() {
        return new IPHeader(this.ipHeader()).getEgress();
    }
    
    public void setSource(final Endpoint value) {
        final IModelObject e = this.ipHeader().getFirstChild("en:source");
        e.removeChildren();
        e.addChild(value.getRoot().cloneTree());
    }
    
    public Endpoint getSource() {
        final List<IModelObject> children = this.ipHeader().getFirstChild("en:source").getChildren();
        final IModelObject ref = children.get(0);
        return Endpoint.createEndpoint(ref);
    }
    
    public void setDestination(final Endpoint value) {
        final IModelObject e = this.ipHeader().getFirstChild("en:destination");
        e.removeChildren();
        e.addChild(value.getRoot().cloneTree());
    }
    
    public Endpoint getDestination() {
        final List<IModelObject> children = this.ipHeader().getFirstChild("en:destination").getChildren();
        final IModelObject ref = children.get(0);
        return Endpoint.createEndpoint(ref);
    }
    
    public Action getAction(final SD sd) {
        final IModelObject o = this.action(sd);
        return this.createAction(o);
    }
    
    public void setAction(final SD sd, final Action action) {
        final IModelObject o = this.action(sd);
        final IModelObject parent = o.getParent();
        o.removeFromParent();
        parent.addChild(action.getRoot().cloneTree());
    }
    
    protected Action createAction(final IModelObject e) {
        Action action = null;
        switch (Action.Type.valueOf(e)) {
            case direct: {
                action = new DirectAction(e);
                break;
            }
            case dynamic: {
                action = new DynamicAction(e);
                break;
            }
            case egress: {
                action = new EgressAction(e);
                break;
            }
            case none: {
                action = new NoneAction(e);
                break;
            }
        }
        return action;
    }
    
    private IModelObject ipHeader() {
        return this.root.getCreateChild("en:selector").getCreateChild("en:ipHeader");
    }
    
    protected IModelObject action(final SD sd) {
        final IModelObject action = this.root.getFirstChild("en:action");
        final IModelObject trans = action.getFirstChild("en:" + sd.name());
        return trans.getChild(0);
    }
    
    private void init() {
        this.ipHeader();
    }
    
    @Override
    public PolicyRule clone() {
        final IModelObject clone = this.root.cloneTree();
        final NatRule rule = new NatRule(clone);
        return rule;
    }
    
    @Override
    protected List<IModelObject> idsToInvalidate(final IModelObject e) {
        final IExpression path = XPath.createExpression("./@id");
        final List<IModelObject> ids = path.query(e, null);
        return ids;
    }
    
    public enum SD
    {
        src("src", 0), 
        dst("dst", 1);
        
        private SD(final String s, final int n) {
        }
    }
}
