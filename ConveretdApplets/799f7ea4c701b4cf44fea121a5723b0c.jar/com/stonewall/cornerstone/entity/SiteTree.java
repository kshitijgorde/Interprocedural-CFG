// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.Xlate;
import java.util.Collections;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.entity.policy.security.SecurityPolicy;
import java.util.Iterator;
import java.util.Collection;
import com.stonewall.cornerstone.entity.db.SecurityPolicyDbAccess;
import java.util.ArrayList;
import com.stonewall.cornerstone.entity.policy.security.SitePolicy;
import java.util.List;
import com.stonewall.cornerstone.entity.db.SiteDbAccess;
import com.stonewall.cornerstone.db.DbException;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class SiteTree
{
    private IModelObject rootNode;
    static final Log log;
    
    static {
        log = Log.getLog(SiteTree.class);
    }
    
    public SiteTree(final Site site) {
        this(site.getRoot());
    }
    
    public SiteTree(IModelObject node) {
        IModelObject parent = node.getParent();
        node = node.cloneTree();
        if (parent != null) {
            parent = parent.cloneTree();
            parent.removeChildren();
            parent.addChild(node);
        }
        this.rootNode = node;
    }
    
    public SiteTree() throws DbException {
        this("SITE0");
    }
    
    public SiteTree(final String id) throws DbException {
        final long start = System.currentTimeMillis();
        this.rootNode = new SiteDbAccess().fetchSiteTree(id).getRoot();
        final long end = System.currentTimeMillis();
        final long duration = end - start;
        SiteTree.log.debug("siteTree@" + duration);
    }
    
    public List<SitePolicy> getNetPolicies() throws DbException {
        return this._getNetPolicies(this.rootNode);
    }
    
    private List<SitePolicy> _getNetPolicies(final IModelObject node) throws DbException {
        final List<SitePolicy> nets = new ArrayList<SitePolicy>();
        final SitePolicy latest = new SecurityPolicyDbAccess().fetchNetPolicy(node.getID());
        if (latest != null) {
            nets.add(latest);
        }
        final List<IModelObject> children = node.getChildren("en:site");
        for (final IModelObject n : children) {
            nets.addAll(this._getNetPolicies(n));
        }
        return nets;
    }
    
    public SiteTree createTreeForPolicy(final SecurityPolicy policy) {
        final Site site = new Site(policy.getParentId());
        final IModelObject node = this.locateNode(site.getReference());
        return new SiteTree(node);
    }
    
    public SiteTree createTreeForSite(final String siteId) {
        final IModelObject node = this.locateNode(new EntityReference(EntityFactory.EntityType.site, siteId));
        if (node == null) {
            return null;
        }
        return new SiteTree(node);
    }
    
    public List<Site> getChildren(final Site site) {
        final List<Site> sites = new ArrayList<Site>();
        final IModelObject node = this.locateNode(site.getReference());
        final List<IModelObject> l = this.getDescendants(node);
        for (final IModelObject e : l) {
            sites.add(new Site(e));
        }
        return sites;
    }
    
    public List<Site> getAllSites() throws Exception {
        final List<Site> sites = new ArrayList<Site>();
        sites.add(new Site(this.rootNode));
        final IExpression xpath = XPath.createExpression(".//en:site");
        final List<IModelObject> elements = xpath.evaluateNodes(new Context(this.rootNode));
        for (final IModelObject e : elements) {
            sites.add(new Site(e));
        }
        return sites;
    }
    
    public List<Site> getBranchNodes() {
        final List<Site> branch = new ArrayList<Site>();
        IModelObject parent = this.rootNode.getParent();
        final List<IModelObject> ancestors = new ArrayList<IModelObject>();
        while (parent != null) {
            ancestors.add(parent);
            parent = parent.getParent();
        }
        Collections.reverse(ancestors);
        for (final IModelObject e : ancestors) {
            branch.add(new Site(e));
        }
        final Site rootSite = new Site(this.rootNode);
        branch.add(rootSite);
        branch.addAll(this.getChildren(rootSite));
        return branch;
    }
    
    public List<Site> getAncestors() {
        final List<Site> sites = new ArrayList<Site>();
        final List<IModelObject> l = this.getAncestors(this.rootNode);
        for (final IModelObject e : l) {
            sites.add(new Site(e));
        }
        return sites;
    }
    
    public List<String> getAncestorIds() {
        final List<String> ids = new ArrayList<String>();
        final List<IModelObject> l = this.getAncestors(this.rootNode);
        for (final IModelObject e : l) {
            ids.add(e.getID());
        }
        return ids;
    }
    
    public List<String> getAncestorIds(final EntityReference site) {
        final List<String> ids = new ArrayList<String>();
        final IModelObject node = this.locateNode(site);
        final List<IModelObject> l = this.getAncestors(node);
        for (final IModelObject e : l) {
            ids.add(e.getID());
        }
        return ids;
    }
    
    private List<IModelObject> getAncestors(final IModelObject node) {
        final IExpression xpath = XPath.createExpression("ancestor-or-self::en:site");
        return xpath.evaluateNodes(new Context(node));
    }
    
    private List<IModelObject> getDescendants(final IModelObject node) {
        final IExpression xpath = XPath.createExpression("descendant::en:site");
        return xpath.evaluateNodes(new Context(node));
    }
    
    public List<String> getAllSiteIds() {
        final List<String> ids = new ArrayList<String>();
        try {
            final IExpression xpath = XPath.createExpression(".//en:site/@id");
            final List<IModelObject> attributes = xpath.evaluateNodes(new Context(this.rootNode));
            for (final IModelObject o : attributes) {
                ids.add(Xlate.get(o, (String)null));
            }
            if (!ids.contains(this.rootNode.getID())) {
                ids.add(this.rootNode.getID());
            }
        }
        catch (Exception e) {
            SiteTree.log.error(this, e);
        }
        return ids;
    }
    
    public int getLevel(Site site) {
        int level;
        for (level = 0; !this.isRoot(site); site = site.getParent(), ++level) {}
        return level;
    }
    
    public int getHighestLevel() {
        return this.rootNode.getChildren().size();
    }
    
    private boolean isRoot(final Site site) {
        return this.rootNode.getID().equals(site.getId());
    }
    
    public Site getRootNode() {
        return new Site(this.rootNode);
    }
    
    public Site locateSite(final EntityReference ref) {
        return new Site(this.locateNode(ref));
    }
    
    private IModelObject locateNode(final EntityReference ref) {
        if (this.rootNode.getID().equals(ref.getId())) {
            return this.rootNode;
        }
        final IExpression path = XPath.createExpression(".//en:site[@id ='" + ref.getId() + "']");
        return path.queryFirst(this.rootNode);
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.rootNode, IXmlIO.Style.printable);
    }
}
