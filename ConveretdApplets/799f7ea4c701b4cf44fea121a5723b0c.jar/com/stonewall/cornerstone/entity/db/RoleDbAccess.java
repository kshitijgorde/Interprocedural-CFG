// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.security.FeatureAccess;
import java.util.Collection;
import java.util.Iterator;
import com.stonewall.cornerstone.security.Permission;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.Role;
import java.util.List;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.entity.Entity;
import org.xmodel.log.Log;

public class RoleDbAccess extends DbAccess
{
    static final Log log;
    static final String fetchAll = "select * from role";
    static final String fetchById = "select * from role where id = $id";
    static final String fetchAllStubs = "select id from role";
    static final String insertRole = "insert into role\n(id, description)\nvalues\n($id, $description)";
    static final String deleteRole = "delete from role where id = $id";
    static final String fetchAllPermissions = "select * from permission";
    static final String insertPermission = "insert into permission\n(id, type, target)\nvalues\n($id, $type, $target)";
    static final String insertRolePermission = "insert into role_permission\n(permission, role)\nvalues\n($permission, $role)";
    static final String fetchPermissionsByRoles = "select * from permission where id in \n(select distinct permission from role_permission where role in $role)";
    static final String fetchPermissionsByRole = "select * from permission where id in \n(select distinct permission from role_permission where role = $role)";
    
    static {
        log = Log.getLog(Entity.class);
    }
    
    public RoleDbAccess() {
        super(DbSession.DB.main);
    }
    
    public List<Role> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<Role> fetchAll(final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Role> roles = new ArrayList<Role>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id from role");
            }
            else {
                stmt = db.createStatement("select * from role");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                roles.add(this.buildRole(result, stub));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return roles;
    }
    
    public Role fetchById(final String roleId) throws DbException {
        return this.fetchById(roleId, false);
    }
    
    public Role fetchById(final String roleId, final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            if (stub) {
                final Role r = new Role(roleId);
                r.trim();
                return r;
            }
            final DbStatement stmt = db.createStatement("select * from role where id = $id");
            stmt.setString("id", roleId);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.buildRole(result, stub);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return null;
    }
    
    public void insertRole(final Role role) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into role\n(id, description)\nvalues\n($id, $description)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", role.getId());
            final String description = role.getDescription();
            if (description != null) {
                stmt.setString("description", description);
            }
            stmt.execute();
            insertRolePermissions(db, role);
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private static void insertRolePermissions(final DbSession db, final Role role) throws DbException {
        final DbStatement stmt = db.createStatement("insert into role_permission\n(permission, role)\nvalues\n($permission, $role)");
        for (final Permission p : role.getPermissions()) {
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("role", role.getId());
            stmt.setString("permission", p.getId());
            stmt.execute();
        }
        stmt.close();
    }
    
    public void deleteRole(final Role role) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from role where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", role.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public List<Permission> fetchAllPermissions() throws DbException {
        final DbSession db = this.getDbSession();
        final List<Permission> permissions = new ArrayList<Permission>();
        try {
            final DbStatement stmt = db.createStatement("select * from permission");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                permissions.add(this.buildPermission(result));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return permissions;
    }
    
    public List<Permission> fetchPermissionsByRole(final List<String> roles) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Permission> permissions = new ArrayList<Permission>();
        try {
            final DbStatement stmt = db.createStatement("select * from permission where id in \n(select distinct permission from role_permission where role in $role)");
            stmt.setString("role", roles);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                permissions.add(this.buildPermission(result));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return permissions;
    }
    
    public List<Permission> fetchPermissionsByRole(final String role) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Permission> permissions = new ArrayList<Permission>();
        try {
            final DbStatement stmt = db.createStatement("select * from permission where id in \n(select distinct permission from role_permission where role = $role)");
            stmt.setString("role", role);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                permissions.add(this.buildPermission(result));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return permissions;
    }
    
    public void insertPermission(final Permission permission) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into permission\n(id, type, target)\nvalues\n($id, $type, $target)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", permission.getId());
            stmt.setString("type", permission.getType().name());
            stmt.setString("target", permission.getTargetId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private Role buildRole(final DbResultSet result, final boolean stub) throws DbException {
        final Role role = new Role(result.getString("id"));
        if (stub) {
            role.trim();
            return role;
        }
        role.setDescription(result.getString("description"));
        role.buildPermissionsElement(this.fetchPermissionsByRole(role.getId()));
        return role;
    }
    
    private Permission buildPermission(final DbResultSet result) throws DbException {
        final Permission permission = new Permission(result.getString("id"));
        permission.setType(FeatureAccess.Type.valueOf(result.getString("type")));
        permission.setTargetId(result.getString("target"));
        return permission;
    }
}
