// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.IPServiceGroupDbAccess;
import org.xmodel.IModelObject;

public class IPServiceGroup extends Entity implements Service, IComposite
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.ipServiceGroup.getQualifiedName();
    }
    
    public IPServiceGroup() {
        super(IPServiceGroup.tag);
        this.init();
    }
    
    public IPServiceGroup(final IModelObject e) {
        super(e);
    }
    
    public IPServiceGroup(final String id) {
        super(IPServiceGroup.tag, id);
    }
    
    private void init() {
        this.root.setAttribute("mutable", "true");
        this.root.getCreateChild("en:name");
        this.root.getCreateChild("en:entries");
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new IPServiceGroupDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new IPServiceGroupDbAccess(label).insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new IPServiceGroupDbAccess(label).delete(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new IPServiceGroupDbAccess(label).update(this);
    }
    
    public boolean isMutable() {
        return Boolean.parseBoolean(Xlate.get(this.root, "mutable", (String)null));
    }
    
    public void setMutable(final boolean flag) {
        this.root.setAttribute("mutable", String.valueOf(flag));
    }
    
    public String getGroup() {
        return Xlate.get(this.root.getFirstChild("en:group"), (String)null);
    }
    
    public void setGroup(final String value) {
        this.setChild(this.root, "en:group", value);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), "");
    }
    
    public void setName(final String value) {
        this.setChild(this.root, "en:name", value);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    public void setDescription(final String value) {
        this.setChild(this.root, "en:description", value);
    }
    
    public void reverse() {
        for (final EntityReference entry : this.getParts()) {
            IPService service = (IPService)entry.getReferent();
            service = service.clone();
            service.reverse();
        }
    }
    
    public boolean isInverse(final IPServiceGroup s) {
        final IPServiceGroup r = s.clone();
        r.reverse();
        return this.equals(r);
    }
    
    @Override
    public boolean contains(final Service ips) {
        if (!(ips instanceof IPServiceGroup)) {
            return false;
        }
        final List<EntityReference> l1 = ((IPServiceGroup)ips).getParts();
        final List<EntityReference> l2 = this.getParts();
        for (final EntityReference e1 : l1) {
            final IPService svc1 = (IPService)e1.getReferent();
            boolean found = false;
            for (final EntityReference e2 : l2) {
                final IPService svc2 = (IPService)e2.getReferent();
                if (svc2.contains(svc1)) {
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
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof IPServiceGroup)) {
            return false;
        }
        if (super.equals(o)) {
            return true;
        }
        final IPServiceGroup ips = (IPServiceGroup)o;
        final List<EntityReference> l1 = ips.getParts();
        final List<EntityReference> l2 = this.getParts();
        if (l1.size() != l2.size()) {
            return false;
        }
        for (final EntityReference e1 : l1) {
            final IPService svc1 = (IPService)e1.getReferent();
            boolean found = false;
            for (final EntityReference e2 : l2) {
                final IPService svc2 = (IPService)e2.getReferent();
                if (svc2.equals(svc1)) {
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
    
    public IPServiceGroup clone() {
        final IModelObject e = this.getRoot().cloneTree();
        return new IPServiceGroup(e);
    }
    
    public boolean containsReferences() {
        return true;
    }
    
    @Override
    public List<EntityReference> getParts() {
        final List<EntityReference> entries = new ArrayList<EntityReference>();
        final List<IModelObject> l = this.root.getFirstChild("en:entries").getChildren("en:ipService");
        if (!l.isEmpty()) {
            for (final IModelObject e : l) {
                entries.add(new EntityReference(e));
            }
        }
        return entries;
    }
    
    @Override
    public void addPart(final EntityReference part) {
        final IModelObject entries = this.root.getFirstChild("en:entries");
        entries.addChild(part.cloneContent());
    }
    
    @Override
    public boolean isAny() {
        return false;
    }
}
