// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.entity.db.TopologyDbAccess;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import com.stonewall.cornerstone.utility.SecureDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import org.xmodel.Xlate;
import java.util.Date;
import com.stonewall.cornerstone.db.DbException;
import org.xmodel.Element;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.utility.IdentityFactory;
import org.xmodel.IModelObject;

public class Entity
{
    protected IModelObject root;
    public static IdentityFactory identityFactory;
    public static final String INVALID_ID = "-1";
    public static final Log log;
    
    static {
        Entity.identityFactory = new IdentityFactory();
        log = Log.getLog(Entity.class);
    }
    
    public Entity(final IModelObject root) {
        this.root = root;
        if (this.getId() == null || this.getId().equals("-1")) {
            this.setId();
        }
        this.ensureUniqueIds(root);
    }
    
    protected Entity(final String tag) {
        this.root = new Element(tag);
        this.setId();
    }
    
    protected Entity(final String tag, final String id) {
        this.root = new Element(tag);
        this.setId(id);
    }
    
    Entity() {
    }
    
    public Entity fetch() throws DbException {
        return this.fetch(null);
    }
    
    public Entity fetch(final Label label) throws DbException {
        throw new UnsupportedOperationException();
    }
    
    public Entity fetchByParent(final String parent) throws DbException {
        return this.fetchByParent(parent, null);
    }
    
    public Entity fetchByParent(final String parent, final Label label) throws DbException {
        throw new UnsupportedOperationException();
    }
    
    public Entity fetchRefByParent(final String parent) throws DbException {
        return this.fetchRefByParent(parent, null);
    }
    
    public Entity fetchRefByParent(final String parent, final Label label) throws DbException {
        throw new UnsupportedOperationException();
    }
    
    public void insert() throws DbException {
        this.setTimestamp(new Date().getTime());
        this.insert(null);
    }
    
    public void insert(final Label label) throws DbException {
        throw new UnsupportedOperationException();
    }
    
    public void update() throws DbException {
        this.setTimestamp(new Date().getTime());
        this.update(null);
    }
    
    public void update(final Label label) throws DbException {
        throw new UnsupportedOperationException();
    }
    
    public void delete() throws DbException {
        this.delete(null);
    }
    
    public void delete(final Label label) throws DbException {
        throw new UnsupportedOperationException();
    }
    
    public String getId() {
        return this.root.getID();
    }
    
    public void setId(final String id) {
        this.root.setID(id);
    }
    
    protected void setId() {
        this.setId(Entity.identityFactory.next());
    }
    
    public String getParentId() {
        return Xlate.get(this.root, "parent", (String)null);
    }
    
    public void setParentId(final String id) {
        if (id == null || id.equals("")) {
            this.root.removeAttribute("parent");
        }
        else {
            this.root.setAttribute("parent", id);
        }
    }
    
    public void setTimestamp(final Long ts) {
        this.root.setAttribute("timestamp", ts.toString());
    }
    
    public long getTimestamp() {
        String s = Xlate.get(this.root, "timestamp", (String)null);
        if (s == null || s.equals("")) {
            s = "0";
        }
        return new Long(s);
    }
    
    public String getViewableTimestamp() {
        final Date date = new Date(this.getTimestamp());
        final SimpleDateFormat sdf = new SimpleDateFormat();
        return sdf.format(date);
    }
    
    public List<Tag> getTags() {
        final List<Tag> tags = new ArrayList<Tag>();
        for (final IModelObject t : this.root.getFirstChild("en:tags").getChildren("en:tag")) {
            tags.add(new Tag(t));
        }
        return tags;
    }
    
    public IModelObject getTagsRoot() {
        return this.root.getFirstChild("en:tags");
    }
    
    public void addTag(final Tag tag) {
        if (this.hasTag(tag)) {
            return;
        }
        this.root.getCreateChild("en:tags").addChild(tag.getRoot());
    }
    
    public void setTags(final List<Tag> tags) {
        for (final Tag tag : tags) {
            this.addTag(tag);
        }
    }
    
    public void setTagsRoot(final IModelObject tags) {
        this.root.removeChildren("en:tags");
        this.root.addChild(tags);
    }
    
    public boolean hasTag(final Tag tag) {
        try {
            final IExpression path = XPath.createExpression("en:tags/en:tag[@id=$id]");
            path.setVariable("id", tag.getId());
            final List<IModelObject> tags = path.query(this.root, null);
            return !tags.isEmpty();
        }
        catch (Exception e) {
            Entity.log.error(this, e);
            return false;
        }
    }
    
    public void removeTags() {
        final IModelObject tags = this.root.getFirstChild("en:tags");
        tags.removeChildren("tag");
    }
    
    public void encrypt() {
        final SecureDocument sd = new SecureDocument(this.root);
        sd.encrypt();
    }
    
    public void decrypt() {
        final SecureDocument sd = new SecureDocument(this.root);
        sd.decrypt();
    }
    
    protected void ensureUniqueIds(final IModelObject e) {
        final IExpression xpath = XPath.createExpression("./descendant-or-self::node()[@id = '-1']");
        final List<IModelObject> ids = xpath.query(e, null);
        for (final IModelObject id : ids) {
            id.setAttribute("id", Entity.identityFactory.next());
        }
    }
    
    protected void invalidateIds(final IModelObject e) {
        final List<IModelObject> ids = this.idsToInvalidate(e);
        for (final IModelObject o : ids) {
            o.setValue("-1");
        }
    }
    
    protected List<IModelObject> idsToInvalidate(final IModelObject e) {
        final IExpression path = XPath.createExpression(".//@id");
        return path.query(e, null);
    }
    
    public void resetIds() {
        this.invalidateIds(this.getRoot());
        this.ensureUniqueIds(this.getRoot());
    }
    
    public EntityFactory.EntityType getEntityType() {
        return EntityFactory.EntityType.valueOf(this.root);
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public void setRoot(final IModelObject e) {
        this.root = e;
    }
    
    @Override
    public String toString() {
        return this.toString(true);
    }
    
    public String toString(final boolean encrypted) {
        IModelObject root = this.getRoot().cloneTree();
        final ModelBuilder builder = new ModelBuilder();
        if (encrypted) {
            final SecureDocument sd = new SecureDocument(root);
            sd.encrypt();
            root = sd.getRoot();
        }
        return builder.writeModel(root, IXmlIO.Style.printable);
    }
    
    public EntityReference getReference() {
        final EntityReference ref = new EntityReference(this.getEntityType(), this.getId());
        if (this.getParentId() != null && !this.getParentId().equals("")) {
            ref.setAttribute("parent", this.getParentId());
        }
        ref.setAttribute("timestamp", String.valueOf(this.getTimestamp()));
        return ref;
    }
    
    public boolean isAssociated() {
        try {
            return new TopologyDbAccess().isAssociated(this);
        }
        catch (DbException e) {
            return false;
        }
    }
    
    public String getName() {
        return "";
    }
    
    public boolean compareTo(final EntityReference ref) {
        return ref.getEntityType().equals(this.getEntityType()) && ref.getId().equals(this.getId());
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Entity) {
            final Entity entity = (Entity)o;
            if (this.getId().equals(entity.getId())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public boolean exists() {
        return false;
    }
    
    public void trim() {
        this.root.removeChildren();
    }
    
    protected void setChild(final IModelObject parent, final String tag, final String value) {
        if (value == null || value.equals("null")) {
            return;
        }
        IModelObject child = parent.getFirstChild(tag);
        if (child == null) {
            child = new Element(tag);
            parent.addChild(child);
        }
        child.setValue(value);
    }
    
    public String getDisplayName() {
        return this.getName();
    }
}
