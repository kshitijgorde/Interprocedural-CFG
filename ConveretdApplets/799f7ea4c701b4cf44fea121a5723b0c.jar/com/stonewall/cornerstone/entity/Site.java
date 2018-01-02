// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import com.stonewall.cornerstone.jms.msg.event.DbEvent;
import java.util.Collection;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.SiteDbAccess;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class Site extends Entity
{
    public static final String tag;
    public static final String ROOT_ID = "SITE0";
    
    static {
        tag = EntityFactory.EntityType.site.getQualifiedName();
    }
    
    public Site() {
        super(Site.tag);
        this.init();
    }
    
    public Site(final IModelObject root) {
        super(root);
    }
    
    public Site(final String id) {
        this();
        this.setId(id);
    }
    
    private void init() {
        this.root.addChild(new Element("en:name"));
        this.root.setAttribute("pending", Boolean.toString(false));
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new SiteDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new SiteDbAccess(label).insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new SiteDbAccess(label).update(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new SiteDbAccess(label).delete(this);
    }
    
    public EntityReference getParentReference() {
        final String parentId = this.getParentId();
        if (parentId != null) {
            return new EntityReference(EntityFactory.EntityType.site, parentId);
        }
        return null;
    }
    
    public void setName(final String value) {
        final IModelObject name = this.root.getFirstChild("en:name");
        name.setValue(value);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setDescription(final String value) {
        this.setChild(this.root, "en:description", value);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    public void setPending(final boolean value) {
        this.root.setAttribute("pending", String.valueOf(value));
    }
    
    public boolean isPending() {
        return Xlate.get(this.root, "pending", false);
    }
    
    public void updatePending(final boolean pending) {
        try {
            final SiteTree tree = new SiteTree(this.getId());
            new SiteDbAccess().updatePending(tree.getAllSiteIds(), pending);
            for (final Site child : tree.getAllSites()) {
                DbEvent.sendUpdate(child.getReference());
            }
        }
        catch (Exception ex) {
            Site.log.error(this, ex);
        }
    }
    
    public Site getParent() {
        if (this.root.getParent() != null) {
            return new Site(this.root.getParent());
        }
        if (this.getId().equals("SITE0")) {
            return null;
        }
        final EntityReference ref = this.getParentReference();
        if (ref != null) {
            return (Site)ref.getReferent();
        }
        return null;
    }
    
    public List<Site> getChildren() {
        final List<Site> sites = new ArrayList<Site>();
        final List<IModelObject> l = this.root.getChildren("en:site");
        for (final IModelObject e : l) {
            sites.add(new Site(e));
        }
        return sites;
    }
    
    public void addChild(final Site child) {
        this.root.addChild(child.getRoot());
    }
    
    public void updateSite(final Site site) {
        this.setName(site.getName());
    }
    
    public void prune() {
        this.root.removeChildren("en:site");
    }
}
