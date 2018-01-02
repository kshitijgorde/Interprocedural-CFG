// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.IChangeSet;
import org.xmodel.diff.IXmlMatcher;
import com.stonewall.cornerstone.diff.EntityContentMatcher;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.P1ProposalDbAccess;
import com.stonewall.cornerstone.entity.Label;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.Entity;

public class P1Proposal extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.p1Proposal.getQualifiedName();
    }
    
    public P1Proposal(final String id) {
        super(P1Proposal.tag, id);
    }
    
    public P1Proposal() {
        super(P1Proposal.tag);
        this.root.getCreateChild("en:name");
        this.root.getCreateChild("en:group");
        this.root.getCreateChild("en:cipher");
        this.root.getCreateChild("en:hash");
        this.root.getCreateChild("en:authMethod");
        this.root.getCreateChild("en:lifetime");
    }
    
    public P1Proposal(final IModelObject root) {
        super(root);
    }
    
    public P1Proposal(final String group, final String cipher, final String hash, final String authMethod, final int lifetime, final String unit) {
        this();
        this.setGroup(group);
        this.setCipher(cipher);
        this.setHash(hash);
        this.setAuthMethod(authMethod);
        this.setLifetime(lifetime, unit);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new P1ProposalDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new P1ProposalDbAccess(label).insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new P1ProposalDbAccess(label).delete(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new P1ProposalDbAccess(label).update(this);
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
    
    public void setGroup(final String value) {
        this.root.getCreateChild("en:group").setValue(value);
    }
    
    public String getGroup() {
        return Xlate.get(this.root.getFirstChild("en:group"), (String)null);
    }
    
    public void setCipher(final String value) {
        this.root.getCreateChild("en:cipher").setValue(value);
    }
    
    public String getCipher() {
        return Xlate.get(this.root.getFirstChild("en:cipher"), (String)null);
    }
    
    public void setHash(final String value) {
        this.root.getCreateChild("en:hash").setValue(value);
    }
    
    public String getHash() {
        return Xlate.get(this.root.getFirstChild("en:hash"), (String)null);
    }
    
    public void setAuthMethod(final String value) {
        final IModelObject e = this.root.getCreateChild("en:authMethod");
        e.setValue(value);
    }
    
    public String getAuthMethod() {
        return Xlate.get(this.root.getFirstChild("en:authMethod"), (String)null);
    }
    
    public void setLifetime(final int value, final String unit) {
        final IModelObject e = this.root.getCreateChild("en:lifetime");
        e.setValue(String.valueOf(value));
        e.setAttribute("unit", unit);
    }
    
    public int getLifetime() {
        return new Integer(Xlate.get(this.root.getFirstChild("en:lifetime"), (String)null));
    }
    
    public String getUnit() {
        return Xlate.get(this.root.getFirstChild("en:lifetime"), "unit", (String)null);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof P1Proposal) {
            final P1Proposal p1 = (P1Proposal)o;
            final XmlDiffer differ = new XmlDiffer();
            differ.setMatcher(new EntityContentMatcher());
            return !differ.diff(this.getRoot(), p1.getRoot(), null);
        }
        return false;
    }
}
