// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import com.stonewall.cornerstone.utility.Namespaces;
import org.xmodel.Element;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.entity.policy.ReferenceData;
import org.xmodel.IModelObject;

public class EntityReference
{
    private IModelObject root;
    private ReferenceData data;
    private Label label;
    private boolean expanded;
    static final Log log;
    
    static {
        log = Log.getLog(EntityReference.class);
    }
    
    public static String getEncoded(final Collection<EntityReference> refs) {
        boolean first = true;
        final StringBuilder builder = new StringBuilder();
        for (final EntityReference ref : refs) {
            if (!first) {
                builder.append(",");
            }
            first = false;
            builder.append(ref.getEncoded());
        }
        return builder.toString();
    }
    
    public static Collection<EntityReference> getReferences(final String s) {
        final List<EntityReference> refs = new ArrayList<EntityReference>();
        final StringTokenizer st = new StringTokenizer(s, ",");
        while (st.hasMoreTokens()) {
            refs.add(new EntityReference(st.nextToken()));
        }
        return refs;
    }
    
    public EntityReference() {
        this.expanded = false;
    }
    
    public EntityReference(final IModelObject e) {
        this.expanded = false;
        this.root = e;
    }
    
    public EntityReference(final EntityFactory.EntityType type, final String id) {
        this.expanded = false;
        (this.root = new Element(type.getQualifiedName())).setID(id);
    }
    
    public EntityReference(final String encoded) {
        this.expanded = false;
        final String[] field = encoded.split("\\.");
        final EntityFactory.EntityType type = EntityFactory.EntityType.valueOf(field[0]);
        (this.root = new Element(type.getQualifiedName())).setAttribute("id", field[1]);
    }
    
    public EntityFactory.EntityType getEntityType() {
        return EntityFactory.EntityType.valueOf(this.root);
    }
    
    public String getEntityName() {
        return Namespaces.enns.getUnqualifiedName(this.root.getType());
    }
    
    public String getId() {
        return this.root.getID();
    }
    
    public void setId(final String id) {
        this.root.setID(id);
    }
    
    public void setAttribute(final String name, final String value) {
        this.root.setAttribute(name, value);
    }
    
    public Entity getReferent() {
        if (this.expanded) {
            final Entity entity = this.createEntity();
            entity.setRoot(this.root);
            return entity;
        }
        if (this.data == null) {
            this.data = new ReferenceData(this.root);
        }
        final ReferenceData.RefData ref = this.data.getData(this.getId());
        if (ref != null) {
            return ref.getEntity();
        }
        try {
            final Entity entity2 = this.createEntity();
            return entity2.fetch(this.label);
        }
        catch (Exception dbe) {
            EntityReference.log.warn("cannot fetch entity: " + this.getEntityType() + ":" + this.getId(), dbe);
            return null;
        }
    }
    
    public Entity createEntity() {
        try {
            return EntityFactory.getInstance().createEntity(this.getEntityType(), this.getId());
        }
        catch (Exception e) {
            EntityReference.log.error(this, e);
            return null;
        }
    }
    
    public String getEncoded() {
        return String.valueOf(this.getEntityType().name()) + "." + this.getId();
    }
    
    public boolean canResolve() {
        if (this.expanded) {
            return true;
        }
        final Entity entity = this.createEntity();
        return entity.exists();
    }
    
    public EntityReference clone() {
        return new EntityReference(this.cloneContent());
    }
    
    public IModelObject cloneContent() {
        return this.root.cloneTree();
    }
    
    @Override
    public String toString() {
        final IModelObject root = this.getRoot().cloneTree();
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(root, IXmlIO.Style.printable);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof EntityReference) {
            final EntityReference ro = (EntityReference)o;
            if (this.getId().equals(ro.getId()) && this.getEntityType().equals(ro.getEntityType())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public void setExpanded(final boolean flag) {
        this.expanded = flag;
    }
    
    public void setLabel(final Label label) {
        this.label = label;
    }
    
    public void setData(final ReferenceData data) {
        this.data = data;
    }
}
