// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.IChangeSet;
import org.xmodel.diff.IXmlMatcher;
import com.stonewall.cornerstone.diff.EntityContentMatcher;
import org.xmodel.diff.XmlDiffer;
import java.util.List;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.P2ProposalDbAccess;
import com.stonewall.cornerstone.entity.Label;
import org.xmodel.IModelObject;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.Entity;

public class P2Proposal extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.p2Proposal.getQualifiedName();
    }
    
    public P2Proposal(final String id) {
        super(P2Proposal.tag, id);
    }
    
    public P2Proposal() {
        super(P2Proposal.tag);
        this.root.addChild(new Element("en:transform"));
        this.root.addChild(new Element("en:group"));
        this.root.addChild(new Element("en:lifetime"));
    }
    
    public P2Proposal(final IModelObject root) {
        super(root);
    }
    
    public P2Proposal(final Transform transform, final String group, final int lifetime, final String unit) {
        this();
        this.setTransform(transform);
        this.setGroup(group);
        this.setLifetime(lifetime, unit);
    }
    
    public P2Proposal(final Transform transform, final String group, final int lifetime, final String unit, final int lifesize) {
        this();
        this.setTransform(transform);
        this.setGroup(group);
        this.setLifetime(lifetime, unit);
        this.setLifesize(lifesize);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new P2ProposalDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new P2ProposalDbAccess(label).insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new P2ProposalDbAccess(label).delete(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new P2ProposalDbAccess(label).update(this);
    }
    
    @Override
    public IModelObject getRoot() {
        return this.root;
    }
    
    public void setProposalGroup(final String value) {
        if (value != null) {
            this.root.setAttribute("proposalGroup", value);
        }
    }
    
    public String getProposalGroup() {
        return Xlate.get(this.root, "proposalGroup", (String)null);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setName(final String name) {
        this.root.getCreateChild("en:name").setValue(name);
    }
    
    public void setDescription(final String value) {
        this.root.getCreateChild("en:description").setValue(value);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    public void setTransform(final Transform value) {
        final IModelObject transform = this.root.getFirstChild("en:transform");
        transform.addChild(value.getRoot().cloneTree());
    }
    
    public Transform getTransform() {
        final List<IModelObject> content = this.root.getFirstChild("en:transform").getChildren();
        return Transform.createTransform(content.get(0));
    }
    
    public void setGroup(final String value) {
        if (value != null) {
            this.root.getFirstChild("en:group").setValue(value);
        }
    }
    
    public String getGroup() {
        return Xlate.get(this.root.getFirstChild("en:group"), (String)null);
    }
    
    public void setLifetime(final int value, final String unit) {
        final IModelObject e = this.root.getFirstChild("en:lifetime");
        e.setValue(String.valueOf(value));
        e.setAttribute("unit", unit);
    }
    
    public int getLifetime() {
        return Xlate.get(this.root.getFirstChild("en:lifetime"), 0);
    }
    
    public String getUnit() {
        return Xlate.get(this.root.getFirstChild("en:lifetime"), "unit", (String)null);
    }
    
    public void setLifesize(final int value) {
        this.root.getCreateChild("en:lifesize").setValue(String.valueOf(value));
    }
    
    public int getLifesize() {
        final IModelObject e = this.root.getFirstChild("en:lifesize");
        if (e != null && !Xlate.get(e, "").equals("")) {
            return Xlate.get(e, 0);
        }
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof P2Proposal) {
            final P2Proposal p2 = (P2Proposal)o;
            final XmlDiffer differ = new XmlDiffer();
            differ.setMatcher(new EntityContentMatcher());
            return !differ.diff(this.getRoot(), p2.getRoot(), null);
        }
        return false;
    }
}
