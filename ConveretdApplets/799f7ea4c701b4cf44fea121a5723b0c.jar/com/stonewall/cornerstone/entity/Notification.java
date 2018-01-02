// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.NotificationDbAccess;
import org.xmodel.IModelObject;

public class Notification extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.notification.getQualifiedName();
    }
    
    public Notification() {
        super(Notification.tag);
    }
    
    public Notification(final String id) {
        super(Notification.tag, id);
    }
    
    public Notification(final IModelObject root) {
        super(root);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new NotificationDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new NotificationDbAccess().insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new NotificationDbAccess().update(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new NotificationDbAccess().delete(this);
    }
    
    public void setUser(final String id) {
        this.root.getCreateChild("en:user").setValue(id);
    }
    
    public String getUser() {
        return Xlate.childGet(this.root, "en:user", (String)null);
    }
    
    public void setSelector(final IModelObject imo) {
        this.root.getCreateChild("en:selector").addChild(imo);
    }
    
    public IModelObject getSelector() {
        return this.root.getFirstChild("en:selector").getChild(0);
    }
    
    public IModelObject getHandler() {
        return this.root.getFirstChild("en:handler").getChild(0);
    }
    
    public void setHandler(final IModelObject imo) {
        this.root.getCreateChild("en:handler").addChild(imo);
    }
}
