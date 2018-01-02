// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import java.util.Iterator;
import java.util.List;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public abstract class DeployablePolicy extends Policy
{
    static final String referenceExpr = "./en:ruleSet//en:any|./en:ruleSet//en:network|./en:ruleSet//en:host|./en:ruleSet//en:addressGroup|./en:ruleSet//en:addressRange|./en:ruleSet//en:ipInterface|./en:ruleSet//en:ipService|./en:ruleSet//en:ipServiceGroup|./en:ruleSet//en:p1Proposal|./en:ruleSet//en:p2Proposal";
    
    public DeployablePolicy(final String tag, final Type type) {
        super(tag, type);
        this.init();
    }
    
    public DeployablePolicy(final String tag, final String id, final Phase phase, final Type type) {
        super(tag, id, type);
        this.init();
        this.setPhase(phase);
    }
    
    public DeployablePolicy(final String tag, final Phase phase, final Type type) {
        super(tag, type);
        this.init();
        this.setPhase(phase);
    }
    
    public DeployablePolicy(final IModelObject o) {
        super(o);
    }
    
    @Override
    protected void init() {
        super.init();
        this.replaceReferenceData(new ReferenceData());
        this.setPending(true);
    }
    
    public void setPhase(final Phase value) {
        this.root.setAttribute("phase", value.name());
    }
    
    public Phase getPhase() {
        final String phase = Xlate.get(this.root, "phase", (String)null);
        if (phase != null && !phase.equals("")) {
            return Phase.valueOf(phase);
        }
        return null;
    }
    
    public boolean isPending() {
        return Boolean.parseBoolean(Xlate.get(this.root, "pending", (String)null));
    }
    
    public void setPending(final boolean flag) {
        this.root.setAttribute("pending", String.valueOf(flag));
    }
    
    @Override
    public EntityReference getReference() {
        final EntityReference ref = super.getReference();
        ref.setAttribute("phase", this.getPhase().name());
        ref.setAttribute("pending", Boolean.toString(this.isPending()));
        return ref;
    }
    
    public void replaceReferenceData(final ReferenceData data) {
        this.removeReferenceData();
        this.setReferenceData(data);
    }
    
    public void removeReferenceData() {
        this.root.removeChildren("en:references");
    }
    
    public void setReferenceData(final ReferenceData data) {
        this.removeReferenceData();
        this.root.addChild(data.getRoot().cloneTree());
    }
    
    public ReferenceData getReferenceData() {
        return new ReferenceData(this.root.getCreateChild("en:references"));
    }
    
    public void replaceContent(final DeployablePolicy p) {
        super.replaceContent(p, true);
        this.replaceReferenceData(p.getReferenceData());
    }
    
    public void expand(final ReferenceData data) {
        final IExpression path = XPath.createExpression("./en:ruleSet//en:any|./en:ruleSet//en:network|./en:ruleSet//en:host|./en:ruleSet//en:addressGroup|./en:ruleSet//en:addressRange|./en:ruleSet//en:ipInterface|./en:ruleSet//en:ipService|./en:ruleSet//en:ipServiceGroup|./en:ruleSet//en:p1Proposal|./en:ruleSet//en:p2Proposal");
        final List<IModelObject> refs = path.evaluateNodes(new Context(this.root));
        for (final IModelObject ref : refs) {
            this.expand(ref, data);
        }
    }
    
    private void expand(final IModelObject e, final ReferenceData data) {
        final IModelObject parent = e.getParent();
        final EntityReference eref = new EntityReference(e);
        if (data.isReference(eref)) {
            parent.removeChild(e);
            final ReferenceData.RefData ref = data.getData(eref.getId());
            parent.addChild(ref.getRoot().cloneTree());
        }
        final List<IModelObject> children = e.getChildren();
        for (final IModelObject child : children) {
            this.expand(child, data);
        }
    }
    
    public void prune() {
        final ReferenceData data = this.getReferenceData();
        final IExpression path = XPath.createExpression("./en:ruleSet//en:any|./en:ruleSet//en:network|./en:ruleSet//en:host|./en:ruleSet//en:addressGroup|./en:ruleSet//en:addressRange|./en:ruleSet//en:ipInterface|./en:ruleSet//en:ipService|./en:ruleSet//en:ipServiceGroup|./en:ruleSet//en:p1Proposal|./en:ruleSet//en:p2Proposal");
        final List<IModelObject> refs = path.evaluateNodes(new Context(this.root));
        for (final IModelObject ref : refs) {
            this.prune(ref, data);
        }
    }
    
    private void prune(final IModelObject e, final ReferenceData data) {
        if (data.isReference(new EntityReference(e))) {
            e.removeChildren();
        }
        final List<IModelObject> children = e.getChildren();
        for (final IModelObject child : children) {
            this.prune(child, data);
        }
    }
    
    public enum Phase
    {
        working("working", 0), 
        net("net", 1), 
        deployed("deployed", 2), 
        baseline("baseline", 3);
        
        private Phase(final String s, final int n) {
        }
    }
}
