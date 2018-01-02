// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.TagDbAccess;
import org.xmodel.IModelObject;

public class Tag extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.tag.getQualifiedName();
    }
    
    public Tag() {
        super(Tag.tag);
        this.init();
    }
    
    public Tag(final IModelObject root) {
        super(root);
    }
    
    public Tag(final String id) {
        super(Tag.tag, id);
        this.init();
    }
    
    private void init() {
        this.root.setAttribute("mutable", "true");
        this.root.getCreateChild("en:name");
        this.root.getCreateChild("en:category");
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new TagDbAccess().delete(this);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new TagDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new TagDbAccess().insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new TagDbAccess().update(this);
    }
    
    public void setName(final String value) {
        this.setChild(this.root, "en:name", value);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setCategory(final Category value) {
        this.setChild(this.root, "en:category", value.name());
    }
    
    public Category getCategory() {
        return Category.valueOf(Xlate.get(this.root.getFirstChild("en:category"), (String)null));
    }
    
    public void setIcon(final String value) {
        this.root.getCreateChild("en:icon").setValue(value);
    }
    
    public String getIcon() {
        return Xlate.get(this.root.getFirstChild("en:icon"), "");
    }
    
    public void setMutable(final boolean value) {
        this.root.setAttribute("mutable", String.valueOf(value));
    }
    
    public boolean isMutable() {
        final String s = Xlate.get(this.root, "mutable", (String)null);
        return s == null || Boolean.parseBoolean(s);
    }
    
    public enum Category
    {
        deviceType("deviceType", 0), 
        pci("pci", 1);
        
        private Category(final String s, final int n) {
        }
    }
}
