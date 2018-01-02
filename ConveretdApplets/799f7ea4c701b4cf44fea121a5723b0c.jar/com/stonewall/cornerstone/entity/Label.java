// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbConfiguration;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.LabelDbAccess;
import java.util.Date;
import com.stonewall.cornerstone.security.User;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.db.DbSession;
import org.xmodel.log.Log;

public class Label extends Entity
{
    public static final String tag;
    public static final Label latest;
    protected static final Log log;
    
    static {
        tag = EntityFactory.EntityType.label.getQualifiedName();
        latest = new Label(DbSession.DB.latest.name(), false);
        log = Log.getLog(Label.class);
    }
    
    public Label() {
    }
    
    public Label(final String id) {
        super(Label.tag, id);
        this.init();
    }
    
    public Label(final String id, final boolean visible) {
        super(Label.tag, id);
        this.init();
        this.setVisible(visible);
    }
    
    public Label(final IModelObject e) {
        super(e);
    }
    
    public Label(final String name, final String description) {
        super(Label.tag);
        this.init();
        this.setName(name);
        this.setDescription(description);
    }
    
    private void init() {
        this.root.getCreateChild("en:name");
        this.root.getCreateChild("en:userId").setValue(User.defaultUserid.getId());
        this.setTimestamp(new Date().getTime());
        this.root.getCreateChild("en:scope").setValue(Scope.global.name());
        this.root.getCreateChild("en:visible").setValue("true");
        this.root.getCreateChild("en:description");
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new LabelDbAccess().fetchById(this.getId());
    }
    
    public void rollback() throws DbException {
        try {
            DbConfiguration.reloadLatest(this.getDatabase());
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public void insert() throws DbException {
        try {
            DbConfiguration.makeSnapshot(DbSession.DB.latest.name(), this.getDatabase());
            this.setTimestamp(new Date().getTime());
            new LabelDbAccess().insert(this);
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    @Override
    public void delete() throws DbException {
        new LabelDbAccess().delete(this);
        try {
            DbConfiguration.dropDatabase(this.getDatabase());
        }
        catch (Exception e) {
            throw new DbException(e);
        }
    }
    
    public void setName(final String name) {
        this.setChild(this.root, "en:name", name);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), this.getId());
    }
    
    public void setUser(final String userId) {
        this.setChild(this.root, "en:userId", userId);
    }
    
    public String getUser() {
        return Xlate.get(this.root.getFirstChild("en:userId"), (String)null);
    }
    
    public void setType(final Type type) {
        this.root.setAttribute("type", type.name());
    }
    
    public Type getType() {
        return Type.valueOf(Xlate.get(this.root, "type", (String)null));
    }
    
    public void setScope(final Scope value) {
        this.setChild(this.root, "en:scope", value.name());
    }
    
    public Scope getScope() {
        return Scope.valueOf(Xlate.get(this.root.getFirstChild("en:scope"), (String)null));
    }
    
    public void setVisible(final boolean value) {
        this.setChild(this.root, "en:visible", String.valueOf(value));
    }
    
    public boolean getVisible() {
        return Boolean.parseBoolean(Xlate.get(this.root.getFirstChild("en:visible"), (String)null));
    }
    
    public void setDescription(final String s) {
        this.setChild(this.root, "en:description", s);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    public String getDatabase() {
        final String id = this.getId();
        DbSession.DB[] values;
        for (int length = (values = DbSession.DB.values()).length, i = 0; i < length; ++i) {
            final DbSession.DB d = values[i];
            if (id.equals(d.name())) {
                return id;
            }
        }
        return "db" + id;
    }
    
    @Override
    public EntityReference getReference() {
        final EntityReference ref = super.getReference();
        ref.setAttribute("type", this.getType().name());
        return ref;
    }
    
    public enum Scope
    {
        user("user", 0), 
        global("global", 1);
        
        private Scope(final String s, final int n) {
        }
    }
    
    public enum Type
    {
        deploy("deploy", 0), 
        audit("audit", 1), 
        user("user", 2);
        
        private Type(final String s, final int n) {
        }
    }
}
