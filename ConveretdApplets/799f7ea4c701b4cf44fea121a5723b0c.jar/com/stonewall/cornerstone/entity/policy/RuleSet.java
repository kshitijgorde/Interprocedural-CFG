// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import com.stonewall.cornerstone.entity.EntityFactory;
import java.util.Set;
import com.stonewall.cornerstone.entity.db.DeployablePolicyDbAccess;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.entity.policy.security.Action;
import java.util.ArrayList;
import java.util.HashSet;
import org.xmodel.xpath.expression.IExpression;
import java.util.List;
import org.xmodel.xpath.XPath;
import java.util.Iterator;
import java.util.Collection;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelAlgorithms;
import org.xmodel.Element;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.IModelObject;

public abstract class RuleSet
{
    protected IModelObject root;
    protected EntityReference parent;
    protected static final Log log;
    
    static {
        log = Log.getLog(RuleSet.class);
    }
    
    public RuleSet() {
        this.root = new Element("en:ruleSet");
    }
    
    public RuleSet(final IModelObject root) {
        this.root = root;
    }
    
    public RuleSet(final EntityReference parent, final IModelObject root) {
        this(root);
        this.parent = parent;
    }
    
    public void add(final RuleSet set) {
        ModelAlgorithms.copyChildren(set.getRoot(), this.root, (IModelObjectFactory)null);
    }
    
    public void add(final PolicyRule rule) {
        if (this.parent != null) {
            rule.setParentId(this.parent.getId());
        }
        this.root.addChild(rule.getRoot().cloneTree());
    }
    
    public void add(final IModelObject e) {
        this.add(this.buildRule(e));
    }
    
    public void addAll(final Collection<? extends PolicyRule> rules) {
        for (final PolicyRule rule : rules) {
            this.add(rule);
        }
    }
    
    public void removeRule(final PolicyRule rule) {
        final IExpression path = XPath.createExpression("./*[@id = $id]");
        path.setVariable("id", rule.getId());
        final List<IModelObject> l = path.query(this.root, null);
        for (final IModelObject e : l) {
            e.removeFromParent();
        }
    }
    
    public void replaceRules(final Collection<? extends PolicyRule> rules) {
        this.root.removeChildren();
        this.addAll(rules);
    }
    
    public <T> T createRule(final IModelObject e) {
        return (T)this.buildRule(e);
    }
    
    public <T extends PolicyRule> Collection<T> getRules() {
        return this.getRules(false);
    }
    
    public <T extends PolicyRule> Collection<T> getRules(final boolean uniqued) {
        Collection<T> rules;
        if (uniqued) {
            rules = new HashSet<T>();
        }
        else {
            rules = new ArrayList<T>();
        }
        final List<IModelObject> l = this.root.getChildren();
        for (final IModelObject r : l) {
            rules.add(this.buildRule(r));
        }
        return rules;
    }
    
    public <T extends PolicyRule> List<T> getRules(final int fromIndex) {
        return this.getRules(fromIndex, this.root.getNumberOfChildren() - 1);
    }
    
    public <T> List<T> getRules(final int fromIndex, final int toIndex) {
        final List<T> rules = new ArrayList<T>();
        for (int i = fromIndex; i <= toIndex; ++i) {
            final List<IModelObject> content = this.root.getChildren();
            rules.add(this.buildRule(content.get(i)));
        }
        return rules;
    }
    
    public <T> T getRuleById(final String id) {
        final IExpression path = XPath.createExpression("./*[@id = $id]");
        path.setVariable("id", id);
        return this.selectRule(path);
    }
    
    public <T extends PolicyRule> List<T> getVPNRulesByPaths(final Collection<String> ids) {
        final List<T> rules = new ArrayList<T>();
        final IExpression path = XPath.createExpression("./*[en:action/en:ike/en:paths/en:path/@id = $id or en:action/en:manualKey/en:paths/en:path/@id = $id]");
        for (final String id : ids) {
            path.setVariable("id", id);
            final List<T> list = this.selectRules(path);
            rules.addAll((Collection<? extends T>)list);
        }
        return rules;
    }
    
    public void replaceRulesByType(final Collection<? extends PolicyRule> rules, final Action.Type type) {
        final IExpression path = XPath.createExpression(this.buildActionPath(type));
        final List<IModelObject> l = path.query(this.root, null);
        for (final IModelObject e : l) {
            e.removeFromParent();
        }
        for (final PolicyRule r : rules) {
            this.add(r);
        }
    }
    
    private String buildActionPath(final Action.Type type) {
        final StringBuilder builder = new StringBuilder();
        builder.append("./*[en:action/");
        builder.append(type.getQualifiedName());
        builder.append("]");
        return builder.toString();
    }
    
    public <T> Collection<T> getPendingRules() {
        final List<T> pending = new ArrayList<T>();
        try {
            final Set<String> ids = new HashSet<String>();
            IExpression path = XPath.createExpression("./en:securityRule/@id");
            final List<IModelObject> l = path.query(this.root, null);
            for (final IModelObject a : l) {
                ids.add(Xlate.get(a, (String)null));
            }
            final Collection<String> pendingIds = new DeployablePolicyDbAccess().determinePendingRuleIds(ids);
            path = XPath.createExpression("./en:securityRule[@id = $id]");
            for (final String id : pendingIds) {
                path.setVariable("id", id);
                final T rule = this.createRule(path.queryFirst(this.root));
                pending.add(rule);
            }
        }
        catch (Exception e) {
            RuleSet.log.error(this, e);
        }
        return pending;
    }
    
    public void clear() {
        this.root.removeChildren();
    }
    
    public abstract RuleSet clone();
    
    protected abstract <T> T buildRule(final IModelObject p0);
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    protected <T> T selectRule(final IExpression path) {
        final IModelObject e = path.queryFirst(this.root);
        return this.buildRule(e);
    }
    
    protected <T> List<T> selectRules(final IExpression path) {
        final List<T> rules = new ArrayList<T>();
        final List<IModelObject> l = path.query(this.root, null);
        for (final IModelObject e : l) {
            rules.add(this.buildRule(e));
        }
        return rules;
    }
    
    public Policy getPolicy() {
        return (Policy)EntityFactory.getInstance().createEntity(this.parent.getRoot());
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.getRoot(), IXmlIO.Style.printable);
    }
}
