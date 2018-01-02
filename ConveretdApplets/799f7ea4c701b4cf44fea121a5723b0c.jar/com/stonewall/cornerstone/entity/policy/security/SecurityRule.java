// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import java.util.Collections;
import org.xmodel.IChangeSet;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.entity.policy.EndpointComparator;
import com.stonewall.cornerstone.entity.util.SelectorComparator;
import org.xmodel.Xlate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import com.stonewall.cornerstone.entity.policy.Selector;
import java.util.List;
import org.xmodel.Element;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.policy.PolicyRule;

public abstract class SecurityRule extends PolicyRule
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.securityRule.getQualifiedName();
    }
    
    public SecurityRule() {
        super(SecurityRule.tag);
    }
    
    public SecurityRule(final String id) {
        super(SecurityRule.tag, id);
        this.init();
    }
    
    public SecurityRule(final IModelObject root) {
        super(root);
    }
    
    public SecurityRule(final String name, final int priority, final Action action) {
        super(SecurityRule.tag, name, priority);
        this.init();
        this.setAction(action);
    }
    
    protected void init() {
        this.root.addChild(new Element("en:selector"));
        this.root.addChild(new Element("en:action"));
    }
    
    protected List<IModelObject> getSelectorElements() {
        return this.root.getCreateChild("en:selector").getChildren();
    }
    
    public void addSelector(final Selector s) {
        final IModelObject selector = this.root.getCreateChild("en:selector");
        selector.addChild(s.getRoot().cloneTree());
    }
    
    public void addSelectors(final Collection<Selector> l) {
        for (final Selector s : l) {
            this.addSelector(s);
        }
    }
    
    public List<Selector> getSelectors() {
        final List<Selector> l = new ArrayList<Selector>();
        final List<IModelObject> elements = this.root.getFirstChild("en:selector").getChildren();
        for (final IModelObject e : elements) {
            l.add(Selector.createSelector(e));
        }
        return l;
    }
    
    public Selector getFirstSelector() {
        Selector result = null;
        final List<IModelObject> list = this.root.getFirstChild("en:selector").getChildren();
        if (list.size() > 0) {
            result = Selector.createSelector(list.get(0));
        }
        return result;
    }
    
    public void setAction(final Action value) {
        final IModelObject action = this.root.getCreateChild("en:action");
        action.addChild(value.getRoot().cloneTree());
    }
    
    public Action getAction() {
        return this.createAction(this.root.getFirstChild("en:action").getChildren().get(0));
    }
    
    public void setRuleGroup(final String value) {
        this.root.getCreateChild("en:ruleGroup").setValue(value);
    }
    
    public String getRuleGroup() {
        return Xlate.get(this.root.getFirstChild("en:ruleGroup"), (String)null);
    }
    
    public void setGateway(final LocalGateway local, final RemoteGateway remote) {
        final Action action = this.getAction();
        action.setGateway(local, remote);
    }
    
    public Gateway getLocalGateway() {
        final Action action = this.getAction();
        return action.getLocalGateway();
    }
    
    public Gateway getRemoteGateway() {
        final Action action = this.getAction();
        return action.getRemoteGateway();
    }
    
    protected Action createAction(final IModelObject e) {
        Action action = null;
        switch (Action.Type.valueOf(e)) {
            case filter: {
                action = new FilterAction(e);
                break;
            }
        }
        return action;
    }
    
    public abstract SelectorComparator getSelectorComparator();
    
    public boolean containsSelector(final Selector selector, final EndpointComparator epCom) {
        final SelectorComparator comparator = this.getSelectorComparator();
        comparator.setEndpointComparator(epCom);
        for (final Selector s : this.getSelectors()) {
            if (comparator.contains(s, selector)) {
                return true;
            }
        }
        return false;
    }
    
    public void removeSelector(final Selector selector) {
        final IExpression path = XPath.createExpression("./en:selector/*[@id = $id]");
        path.setVariable("id", selector.getId());
        final IModelObject e = path.queryFirst(this.root);
        e.removeFromParent();
    }
    
    public void removeSelectors(final Collection<Selector> selectors) {
        for (final Selector s : selectors) {
            this.removeSelector(s);
        }
    }
    
    public void removeSelectors() {
        this.root.getCreateChild("en:selector").removeChildren();
    }
    
    public void replaceSelectors(final Collection<Selector> selectors) {
        this.removeSelectors();
        this.addSelectors(selectors);
    }
    
    public Action.Type getActionType() {
        return this.getAction().getType();
    }
    
    @Override
    public void completeOptimization() {
        if (this.namesToCombine != null && !this.namesToCombine.isEmpty()) {
            final StringBuffer buf = new StringBuffer();
            buf.append("This rule is a combination of the following rules: " + this.getName());
            for (final String s : this.namesToCombine) {
                buf.append("+");
                buf.append(s);
            }
            this.setDescription(buf.toString());
            this.setName("Optimized");
        }
    }
    
    public boolean equals(final SecurityRule rule) {
        final XmlDiffer differ = new XmlDiffer();
        if (!differ.diff(this.getAction().getRoot(), rule.getAction().getRoot(), null)) {
            return false;
        }
        if (this.getSelectors().size() != rule.getSelectors().size()) {
            return false;
        }
        final SelectorComparator sc = this.getSelectorComparator();
        for (final Selector s : this.getSelectors()) {
            boolean found = false;
            for (final Selector s2 : rule.getSelectors()) {
                if (sc.equals(s, s2)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
    
    public void setTerminating() {
    }
    
    public boolean isAdminTerminating() {
        return this.getRuleType().equals(Type.adminTerminating);
    }
    
    public boolean isAdminACL() {
        return this.getRuleType().equals(Type.adminACL);
    }
    
    public boolean isAutoACL() {
        return this.getRuleType().equals(Type.autoACL);
    }
    
    public boolean isAutoDenyAll() {
        return this.getRuleType().equals(Type.autoDenyAll);
    }
    
    public void setTunnels(final Collection<SecurityTunnel> tunnels) {
        this.removeTunnels();
        final IModelObject action = this.getAction().getRoot();
        final IModelObject parent = action.getCreateChild("en:tunnels");
        for (final SecurityTunnel t : tunnels) {
            final IModelObject o = new Element("en:securityTunnel");
            o.setAttribute("id", t.getId());
            parent.addChild(o);
        }
    }
    
    public void setTunnelIds(final Collection<String> tunnelIds) {
        this.removeTunnels();
        final IModelObject action = this.getAction().getRoot();
        final IModelObject parent = action.getCreateChild("en:tunnels");
        for (final String t : tunnelIds) {
            final IModelObject o = new Element("en:securityTunnel");
            o.setAttribute("id", t);
            parent.addChild(o);
        }
    }
    
    public boolean hasTunnels() {
        final IExpression path = XPath.createExpression("./en:action/*/en:tunnels/en:securityTunnel");
        final List<IModelObject> l = path.query(this.root, null);
        return !l.isEmpty();
    }
    
    public void removeTunnels() {
        final IExpression path = XPath.createExpression("./en:action/*/en:tunnels/en:securityTunnel");
        final List<IModelObject> l = path.query(this.root, null);
        for (final IModelObject e : l) {
            e.removeFromParent();
        }
    }
    
    public void removeTunnel(final String tunnelId) {
        final IExpression path = XPath.createExpression("./en:action/*/en:tunnels/en:securityTunnel[@id = $id]");
        path.setVariable("id", tunnelId);
        final IModelObject e = path.queryFirst(this.root);
        e.removeFromParent();
    }
    
    public List<String> getTunnelIds() {
        final List<String> tunnelIds = new ArrayList<String>();
        final IExpression path = XPath.createExpression("./en:action/*/en:tunnels/en:securityTunnel/@id");
        final List<IModelObject> l = path.query(this.root, null);
        for (final IModelObject a : l) {
            tunnelIds.add(Xlate.get(a, (String)null));
        }
        return tunnelIds;
    }
    
    @Override
    protected List<IModelObject> idsToInvalidate(final IModelObject e) {
        final IExpression path = XPath.createExpression("./@id|./en:selector/*/@id");
        final List<IModelObject> ids = path.query(e, null);
        return ids;
    }
    
    public Collection<String> affectedDevices() {
        return (Collection<String>)Collections.EMPTY_LIST;
    }
}
