// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import java.util.ArrayList;
import java.util.Iterator;
import org.xmodel.Element;
import java.util.List;
import java.util.Date;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.UserDbAccess;
import org.xmodel.IModelObject;

public class User extends Entity
{
    public static final String admin = "admin";
    public static final String system = "system";
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.user.getQualifiedName();
    }
    
    public User(final String userid) {
        super(User.tag);
        this.setId(userid);
        this.init();
    }
    
    public User(final IModelObject root) {
        super(root);
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new UserDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new UserDbAccess().insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new UserDbAccess().update(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new UserDbAccess().delete(this);
    }
    
    public void setId() {
    }
    
    public void setPassword(final String s) {
        this.setChild(this.root, "en:password", s);
    }
    
    public String getPassword() {
        return Xlate.get(this.root.getFirstChild("en:password"), (String)null);
    }
    
    public void setCreated(final long value) {
        this.setChild(this.root, "en:created", String.valueOf(value));
    }
    
    public void setCreated() {
        this.setCreated(new Date().getTime());
    }
    
    public long getCreated() {
        return new Long(Xlate.get(this.root.getFirstChild("en:created"), (String)null));
    }
    
    public void setStatus(final Status s) {
        this.setStatus(s.name());
    }
    
    public void setStatus(final String s) {
        final IModelObject status = this.root.getCreateChild("en:status");
        status.setValue(s);
        status.setAttribute("tm", String.valueOf(new Date().getTime()));
    }
    
    public String getStatus() {
        return Xlate.get(this.root.getFirstChild("en:status"), (String)null);
    }
    
    public long getStatusTimestamp() {
        return new Long(Xlate.get(this.root.getFirstChild("en:status"), "tm", (String)null));
    }
    
    public void setStatusTimestamp(final long l) {
        this.root.getCreateChild("en:status").setAttribute("tm", String.valueOf(l));
    }
    
    public void setFirstName(final String s) {
        this.root.getCreateChild("en:name").getCreateChild("en:first").setValue(s);
    }
    
    public String getFirstName() {
        return Xlate.get(this.root.getFirstChild("en:name").getFirstChild("en:first"), (String)null);
    }
    
    public void setLastName(final String s) {
        this.root.getCreateChild("en:name").getCreateChild("en:last").setValue(s);
    }
    
    public String getLastName() {
        return Xlate.get(this.root.getFirstChild("en:name").getFirstChild("en:last"), (String)null);
    }
    
    public void setEmail(final String s) {
        this.root.getCreateChild("en:contact").getCreateChild("en:email").setValue(s);
    }
    
    public String getEmail() {
        return Xlate.get(this.root.getFirstChild("en:contact").getFirstChild("en:email"), (String)null);
    }
    
    public void setPhone(final String s) {
        this.root.getCreateChild("en:contact").getCreateChild("en:phone").setValue(s);
    }
    
    public String getPhone() {
        return Xlate.get(this.root.getFirstChild("en:contact").getFirstChild("en:phone"), (String)null);
    }
    
    public void setRoles(final List<String> list) {
        final IModelObject roles = this.root.getFirstChild("en:roles");
        roles.removeChildren();
        for (final String s : list) {
            final IModelObject o = new Element("en:role");
            o.setValue(s);
            roles.addChild(o);
        }
    }
    
    public List<String> getRoles() {
        final List<String> list = new ArrayList<String>();
        final IModelObject roles = this.root.getFirstChild("en:roles");
        for (final Object o : roles.getChildren()) {
            final IModelObject e = (IModelObject)o;
            list.add(Xlate.get(e, "").trim());
        }
        return list;
    }
    
    public IModelObject getRolesElement() {
        return this.root.getFirstChild("en:roles");
    }
    
    public boolean disabled() {
        final Status status = Status.valueOf(this.getStatus());
        return status == Status.disabled || status == Status.suspended;
    }
    
    public void enable() {
        this.setStatus(Status.enabled);
    }
    
    public void disable() {
        this.setStatus(Status.disabled);
    }
    
    public void suspend() {
        this.setStatus(Status.suspended);
    }
    
    void init() {
        this.root.getCreateChild("en:password").setAttribute("encrypted", "false");
        this.setStatus(Status.enabled);
        this.setCreated();
        this.root.addChild(new Element("en:roles"));
    }
    
    public enum Status
    {
        enabled("enabled", 0), 
        disabled("disabled", 1), 
        suspended("suspended", 2);
        
        private Status(final String s, final int n) {
        }
    }
}
