// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import java.util.Iterator;
import org.xmodel.Element;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.RoleDbAccess;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import com.stonewall.cornerstone.security.Permission;
import java.util.List;

public class Role extends Entity
{
    private List<Permission> permissions;
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.role.getQualifiedName();
    }
    
    public Role() {
        super(Role.tag);
        this.permissions = new ArrayList<Permission>();
        this.init();
    }
    
    public Role(final Name name) {
        super(Role.tag);
        this.permissions = new ArrayList<Permission>();
        this.setId(name.name());
        this.init();
    }
    
    public Role(final String id) {
        super(Role.tag, id);
        this.permissions = new ArrayList<Permission>();
    }
    
    public Role(final IModelObject root) {
        super(root);
        this.permissions = new ArrayList<Permission>();
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new RoleDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new RoleDbAccess().insertRole(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new RoleDbAccess().deleteRole(this);
    }
    
    public void setId() {
    }
    
    public void setDescription(final String s) {
        this.setChild(this.root, "en:description", s);
    }
    
    public String getDescription() {
        return Xlate.get(this.root.getFirstChild("en:description"), (String)null);
    }
    
    void init() {
        this.root.addChild(new Element("en:description"));
        this.root.addChild(new Element("en:permissions"));
    }
    
    public void addPermissions(final List<Permission> permissions) {
        for (final Permission p : permissions) {
            this.addPermission(p);
        }
    }
    
    public void addPermission(final Permission access) {
        this.permissions.add(access);
    }
    
    public List<Permission> getPermissions() {
        return this.permissions;
    }
    
    public void buildPermissionsElement(final List<Permission> permissions) {
        final IModelObject pe = this.root.getCreateChild("en:permissions");
        for (final Permission p : permissions) {
            IModelObject target = null;
            final IExpression path = XPath.createExpression(p.getTargetPath());
            if (p.hasVariable()) {
                path.setVariable(p.getVariableName(), p.getTargetId());
            }
            target = path.queryFirst(pe);
            IModelObject e = null;
            if (target != null) {
                e = p.getActionAsObject();
            }
            else {
                target = pe;
                e = p.getAccessAsObject();
            }
            target.addChild(e);
        }
    }
    
    public enum Name
    {
        admin("admin", 0), 
        operator("operator", 1);
        
        private Name(final String s, final int n) {
        }
    }
}
