// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy;

import com.stonewall.cornerstone.utility.IdentityFactory;
import com.stonewall.cornerstone.entity.EntityFactory;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.IChangeSet;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.Entity;
import org.xmodel.diff.IXmlMatcher;
import java.util.Iterator;
import java.util.Comparator;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import org.xmodel.Element;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class ReferenceData
{
    private IModelObject root;
    protected static final Log log;
    static final String allRefPath = "./*/*";
    static final String refByIdPath = "./*/*[@id=$id]";
    static final String refByReferencedPath = "./*/*[@referenced=$referenced]";
    static final String refsByParentPath = "./*[name()=$parent]/*";
    
    static {
        log = Policy.log;
    }
    
    public ReferenceData() {
        this.root = new Element("en:references");
        this.init();
    }
    
    public ReferenceData(final IModelObject e) {
        if (e.getType().equals("en:references")) {
            this.root = e;
            return;
        }
        final IExpression path = XPath.createExpression("./ancestor::*/en:references");
        this.root = path.queryFirst(e);
        if (this.root == null) {
            this.root = new Element("en:references");
            this.init();
        }
    }
    
    private void init() {
        this.root.addChild(new Element("en:anys"));
        this.root.addChild(new Element("en:networks"));
        this.root.addChild(new Element("en:hosts"));
        this.root.addChild(new Element("en:addressGroups"));
        this.root.addChild(new Element("en:addressRanges"));
        this.root.addChild(new Element("en:ipInterfaces"));
        this.root.addChild(new Element("en:p1Proposals"));
        this.root.addChild(new Element("en:p2Proposals"));
        this.root.addChild(new Element("en:ipServices"));
        this.root.addChild(new Element("en:ipServiceGroups"));
    }
    
    public void reset() {
        this.root.removeChildren();
        this.init();
    }
    
    public void add(final RefData ref) {
        final IModelObject parent = this.getParent(ref);
        parent.addChild(ref.getRoot().cloneTree());
    }
    
    public void remove(final String id) {
        final RefData ref = this.getRefById(id);
        if (ref != null) {
            final IModelObject e = ref.getRoot();
            e.removeFromParent();
        }
    }
    
    public boolean hasReference(final String refId) {
        return this.getRefById(refId) != null;
    }
    
    public boolean hasReference(final RefData ref, final Comparator<RefData> comparator) {
        final IModelObject parent = this.getParent(ref);
        for (final IModelObject e : parent.getChildren()) {
            final RefData child = this.createData(e);
            if (comparator.compare(ref, child) == 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasReference(final RefData ref, final IXmlMatcher matcher) {
        return this.findData(ref, matcher) != null;
    }
    
    public boolean hasReference(final Entity entity, final Comparator<RefData> comparator) {
        final RefData ref = this.createData(entity.getRoot());
        return this.hasReference(ref, comparator);
    }
    
    public boolean isReference(final EntityReference eref) {
        switch (eref.getEntityType()) {
            case ipInterface:
            case p1Proposal:
            case p2Proposal:
            case addressGroup:
            case addressRange:
            case ipService:
            case ipServiceGroup:
            case network:
            case host:
            case any: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isReference(final IModelObject o) {
        return Xlate.get(o, "referenced", (String)null) != null;
    }
    
    public RefData findData(final RefData ref, final IXmlMatcher matcher) {
        final XmlDiffer differ = new XmlDiffer();
        differ.setMatcher(matcher);
        final IModelObject rparent = this.getParent(ref);
        final List<IModelObject> children = rparent.getChildren();
        for (final IModelObject child : children) {
            if (differ.diff(ref.getRoot(), child, null)) {
                return this.createData(child);
            }
        }
        return null;
    }
    
    public RefData getData(final String id) {
        final RefData ref = this.getRefById(id);
        if (ref != null) {
            return ref;
        }
        return this.getRefByReferencedId(id);
    }
    
    public RefData getData(final RefData ref, final Comparator<RefData> comparator) {
        final IModelObject parent = this.getParent(ref);
        for (final IModelObject e : parent.getChildren()) {
            final RefData child = this.createData(e);
            if (comparator.compare(ref, child) == 0) {
                return child;
            }
        }
        return null;
    }
    
    public RefData getData(final Entity entity, final Comparator<RefData> comparator) {
        final RefData ref = this.createData(entity.getRoot());
        return this.getData(ref, comparator);
    }
    
    private RefData getRefById(final String id) {
        final IExpression path = XPath.createExpression("./*/*[@id=$id]");
        if (id != null) {
            path.setVariable("id", id);
        }
        final IModelObject ref = path.queryFirst(this.root);
        if (ref != null) {
            try {
                return this.createData(ref);
            }
            catch (Exception e) {
                ReferenceData.log.error(this, e);
            }
        }
        return null;
    }
    
    private RefData getRefByReferencedId(final String id) {
        final IExpression path = XPath.createExpression("./*/*[@referenced=$referenced]");
        path.setVariable("referenced", id);
        final IModelObject ref = path.queryFirst(this.root);
        if (ref != null) {
            try {
                return this.createData(ref);
            }
            catch (Exception e) {
                ReferenceData.log.error(this, e);
            }
        }
        return null;
    }
    
    public List<RefData> getAllData() {
        final List<RefData> refs = new ArrayList<RefData>();
        final IExpression path = XPath.createExpression("./*/*");
        final List<IModelObject> l = path.evaluateNodes(new Context(this.root));
        for (final IModelObject e : l) {
            try {
                refs.add(this.createData(e));
            }
            catch (Exception ex) {
                ReferenceData.log.error(this, ex);
            }
        }
        return refs;
    }
    
    private IModelObject getParent(final RefData ref) {
        final String parentName = String.valueOf(ref.getType().getQualifiedName()) + "s";
        return this.root.getFirstChild(parentName);
    }
    
    public static RefData createRef(final Entity entity) {
        final ReferenceData data = new ReferenceData();
        return data.new RefData(entity);
    }
    
    private RefData createData(final IModelObject e) {
        return new RefData(e);
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.getRoot(), IXmlIO.Style.printable);
    }
    
    public ReferenceData clone() {
        return new ReferenceData(this.root.cloneTree());
    }
    
    public class RefData
    {
        private IModelObject root;
        
        RefData(final Entity entity) {
            (this.root = entity.getRoot().cloneTree()).setAttribute("id", new IdentityFactory().next());
            this.root.setAttribute("referenced", entity.getId());
        }
        
        RefData(final IModelObject e) {
            this.root = e;
        }
        
        public EntityReference getEntityReference() {
            return new EntityReference(this.getType(), this.getId());
        }
        
        public Entity getReferencedEntity() {
            final IModelObject clone = this.root.cloneTree();
            clone.setAttribute("id", this.getReferenced());
            clone.removeAttribute("referenced");
            return EntityFactory.getInstance().createEntity(clone);
        }
        
        public Entity getEntity() {
            return EntityFactory.getInstance().createEntity(this.root);
        }
        
        public EntityFactory.EntityType getType() {
            return EntityFactory.EntityType.valueOf(this.root);
        }
        
        protected void trim(final IModelObject e) {
        }
        
        public String getId() {
            return this.root.getID();
        }
        
        public String getReferenced() {
            return Xlate.get(this.root, "referenced", (String)null);
        }
        
        public void setReferenced(final String value) {
            this.root.setAttribute("referenced", value);
        }
        
        public boolean isSticky() {
            final String sticky = Xlate.get(this.root, "sticky", (String)null);
            return sticky != null && Boolean.parseBoolean(sticky);
        }
        
        public IModelObject getRoot() {
            return this.root;
        }
        
        public Endpoint createEndpoint() {
            return Endpoint.createEndpoint(this.getEntityReference().getRoot());
        }
        
        @Override
        public String toString() {
            final ModelBuilder builder = new ModelBuilder();
            return builder.writeModel(this.getRoot(), IXmlIO.Style.printable);
        }
    }
}
