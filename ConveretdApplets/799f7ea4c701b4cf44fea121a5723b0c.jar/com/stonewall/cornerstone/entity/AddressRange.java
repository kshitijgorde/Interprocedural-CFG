// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import java.util.ArrayList;
import java.util.List;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.AddressRangeDbAccess;
import org.xmodel.IModelObject;

public class AddressRange extends Entity implements IComposite
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.addressRange.getQualifiedName();
    }
    
    public AddressRange() {
        super(AddressRange.tag);
    }
    
    public AddressRange(final String id) {
        super(AddressRange.tag, id);
    }
    
    public AddressRange(final IModelObject e) {
        super(e);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new AddressRangeDbAccess(label).fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new AddressRangeDbAccess(label).insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new AddressRangeDbAccess(label).delete(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new AddressRangeDbAccess(label).update(this);
    }
    
    public void setName(final String value) {
        this.root.getCreateChild("en:name").setValue(value);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setDescription(final String value) {
        this.root.getCreateChild("en:description").setValue(value);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    public void setStart(final Host host) {
        this.setStart(host.getReference());
    }
    
    public void setStart(final EntityReference ref) {
        final IModelObject start = this.root.getCreateChild("en:start");
        start.removeChildren();
        start.addChild(ref.getRoot().cloneObject());
    }
    
    public EntityReference getStart() {
        return new EntityReference(this.root.getFirstChild("en:start").getChild(0));
    }
    
    public void setEnd(final Host host) {
        this.setEnd(host.getReference());
    }
    
    public void setEnd(final EntityReference ref) {
        final IModelObject end = this.root.getCreateChild("en:end");
        end.removeChildren();
        end.addChild(ref.getRoot().cloneObject());
    }
    
    public EntityReference getEnd() {
        return new EntityReference(this.root.getFirstChild("en:end").getChild(0));
    }
    
    @Override
    public List<EntityReference> getParts() {
        final List<EntityReference> parts = new ArrayList<EntityReference>();
        parts.add(this.getStart());
        parts.add(this.getEnd());
        return parts;
    }
    
    @Override
    public void addPart(final EntityReference part) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean exists() {
        try {
            return new AddressRangeDbAccess().exists(this.getId());
        }
        catch (DbException e) {
            return false;
        }
    }
}
