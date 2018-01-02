// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import java.util.Collections;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.entity.EntityFactory;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import com.stonewall.cornerstone.entity.Label;
import com.stonewall.cornerstone.utility.Transaction;
import java.util.Iterator;
import java.util.Collection;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.log.Log;

public class DbEvent extends Event
{
    public static final String Tag;
    static final Log log;
    
    static {
        Tag = Type.db.getQualifiedName();
        log = Log.getLog(DbEvent.class);
    }
    
    public static DbEvent create(final EntityReference entity, final EntityReference parent) {
        return new DbEvent(Action.create, entity, parent);
    }
    
    public static DbEvent update(final EntityReference entity) {
        return new DbEvent(Action.update, entity);
    }
    
    public static DbEvent delete(final EntityReference entity) {
        return new DbEvent(Action.delete, entity, null);
    }
    
    public static void sendCreate(final EntityReference entity, final EntityReference parent) {
        new DbEvent(Action.create, entity, parent).send();
    }
    
    public static void sendUpdate(final EntityReference entity) {
        new DbEvent(Action.update, entity).send();
    }
    
    public static void sendCreate(final Entity entity, final EntityReference parent) {
        new DbEvent(Action.create, entity, parent).send();
    }
    
    public static void sendUpdate(final Entity entity) {
        new DbEvent(Action.update, entity).send();
    }
    
    public static void sendDelete(final EntityReference entity) {
        new DbEvent(Action.delete, entity, null).send();
    }
    
    public static void send(final Collection<DbEvent> events) {
        for (final DbEvent event : events) {
            event.send();
        }
    }
    
    @Override
    public void send() {
        final Transaction tr = Transaction.getCurrent();
        if (tr != null) {
            tr.add(this);
        }
        else {
            super.send();
        }
    }
    
    public DbEvent(final Action action, final EntityReference entity, final EntityReference parent) {
        this(action, entity);
        if (parent != null) {
            this.root.getCreateChild("evt:parent").addChild(parent.cloneContent());
        }
    }
    
    public DbEvent(final Action action, final EntityReference entity) {
        super(DbEvent.Tag, DbEvent.log);
        this.root.getCreateChild("evt:action").setValue(action.name());
        this.root.getCreateChild("evt:label").setValue(Label.latest.getId());
        this.root.getCreateChild("evt:entityRef").addChild(entity.cloneContent());
    }
    
    public DbEvent(final Action action, final Entity entity, final EntityReference parent) {
        this(action, entity.getReference(), parent);
        this.root.getCreateChild("evt:entity").addChild(entity.getRoot().cloneTree());
    }
    
    private DbEvent(final Action action, final Entity entity) {
        this(action, entity.getReference());
        this.root.getCreateChild("evt:entity").addChild(entity.getRoot().cloneTree());
    }
    
    public DbEvent(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public DbEvent(final IModelObject e) {
        super(e, DbEvent.log);
    }
    
    public EntityReference getEntityReference() {
        EntityReference result = null;
        final IModelObject entityRef = this.root.getFirstChild("evt:entityRef");
        if (entityRef != null) {
            final IModelObject e = entityRef.getChild(0);
            result = new EntityReference(e);
        }
        else {
            final Entity entity = this.getEntity();
            if (entity != null) {
                result = entity.getReference();
            }
        }
        return result;
    }
    
    public Entity getEntity() {
        final IModelObject entity = this.root.getFirstChild("evt:entity");
        if (entity == null) {
            return null;
        }
        return EntityFactory.getInstance().createEntity(entity.getChild(0).cloneTree());
    }
    
    public EntityReference getParent() {
        final IModelObject parent = this.root.getFirstChild("evt:parent");
        if (parent != null) {
            final IModelObject e = parent.getChild(0);
            return new EntityReference(e);
        }
        return null;
    }
    
    @Override
    public Action getAction() {
        return Action.valueOf(Xlate.get(this.root.getFirstChild("evt:action"), (String)null));
    }
    
    public Collection<EntityReference> getEntityReferences() {
        if (this.root.getFirstChild("evt:entityRef") == null) {
            return null;
        }
        return Collections.singletonList(this.getEntityReference());
    }
    
    public boolean isComplete() {
        return this.root.getFirstChild("evt:entity") != null;
    }
    
    public void setLabel(final Label label) {
        final IModelObject e = this.root.getFirstChild("evt:label");
        if (label != null) {
            e.setValue(label.getId());
        }
        else {
            this.root.removeChild(e);
        }
    }
    
    public String getLabel() {
        final IModelObject label = this.root.getFirstChild("evt:label");
        if (label != null) {
            return Xlate.get(label, (String)null);
        }
        return null;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof DbEvent) {
            final DbEvent dbo = (DbEvent)o;
            if (this.getEntityReference().equals(dbo.getEntityReference()) && this.getAction().equals(dbo.getAction())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.getEntityReference().hashCode();
    }
    
    public enum Action
    {
        create("create", 0), 
        update("update", 1), 
        delete("delete", 2);
        
        private Action(final String s, final int n) {
        }
    }
}
