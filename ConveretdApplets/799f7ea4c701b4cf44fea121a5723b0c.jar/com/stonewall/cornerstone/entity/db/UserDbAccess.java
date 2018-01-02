// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Iterator;
import com.stonewall.cornerstone.db.Transaction;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.User;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.entity.Entity;
import org.xmodel.log.Log;

public class UserDbAccess extends DbAccess
{
    static final Log log;
    static final String fetchById = "select * from user where id = $id";
    static final String fetchAll = "select * from user order by id";
    static final String fetchAllStubs = "select id from user order by id";
    static final String fetchRolesByUser = "select role from user_role where userid = $userid";
    static final String fetchRoleMatrix = "select * from user_role";
    static final String insert = "insert into user\n(id, created, status, status_ts, first_name, last_name, email, phone, password)\nvalues\n($id, $created, $status, $status_ts, $first_name, $last_name, $email, $phone, $password)";
    static final String insertUserRole = "insert into user_role (userid, role) values ($userid, $role)";
    static final String deleteRolesByUser = "delete from user_role where userid = $userid";
    static final String delete = "delete from user where id = $id";
    static final String update = "update user\nset status = $status,\nstatus_ts = $status_ts,\nfirst_name = $first_name,\nlast_name = $last_name,\nemail = $email,\nphone = $phone,\npassword = $password\nwhere id = $id";
    
    static {
        log = Log.getLog(Entity.class);
    }
    
    public UserDbAccess() {
        super(DbSession.DB.main);
    }
    
    public User fetchById(final String userid) throws DbException {
        return this.fetchById(userid, false);
    }
    
    public User fetchById(final String userid, final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            if (stub) {
                final User u = new User(userid);
                u.trim();
                return u;
            }
            final DbStatement stmt = db.createStatement("select * from user where id = $id");
            stmt.setString("id", userid);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final User user = this.build(result, stub);
                if (!stub) {
                    user.setRoles(this.fetchRolesByUser(user.getId()));
                }
                return user;
            }
        }
        finally {
            db.close();
        }
        db.close();
        return null;
    }
    
    public List<User> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<User> fetchAll(final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        final List<User> users = new ArrayList<User>();
        Map<String, List<String>> roleMatrix = this.fetchRoleMatrix();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id from user order by id");
            }
            else {
                stmt = db.createStatement("select * from user order by id");
                roleMatrix = this.fetchRoleMatrix();
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final User user = this.build(result, stub);
                if (!stub) {
                    final List<String> roles = roleMatrix.get(user.getId());
                    if (roles != null) {
                        user.setRoles(roles);
                    }
                }
                users.add(user);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return users;
    }
    
    public List<String> fetchRolesByUser(final String userid) throws DbException {
        final DbSession db = this.getDbSession();
        final List<String> roles = new ArrayList<String>();
        try {
            final DbStatement stmt = db.createStatement("select role from user_role where userid = $userid");
            stmt.setString("userid", userid);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                roles.add(result.getString());
            }
        }
        finally {
            db.close();
        }
        db.close();
        return roles;
    }
    
    private Map<String, List<String>> fetchRoleMatrix() throws DbException {
        final DbSession db = this.getDbSession();
        final Map<String, List<String>> matrix = new HashMap<String, List<String>>();
        try {
            final DbStatement stmt = db.createStatement("select * from user_role");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final String userid = result.getString("userid");
                List<String> list = matrix.get(userid);
                if (list == null) {
                    list = new ArrayList<String>();
                    matrix.put(userid, list);
                }
                list.add(result.getString("role"));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return matrix;
    }
    
    public void insert(final User user) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into user\n(id, created, status, status_ts, first_name, last_name, email, phone, password)\nvalues\n($id, $created, $status, $status_ts, $first_name, $last_name, $email, $phone, $password)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", user.getId());
            stmt.set("created", user.getCreated());
            stmt.setString("status", user.getStatus());
            stmt.set("status_ts", user.getStatusTimestamp());
            stmt.setString("first_name", user.getFirstName());
            stmt.setString("last_name", user.getLastName());
            stmt.setString("email", user.getEmail());
            stmt.setString("phone", user.getPhone());
            stmt.setString("password", user.getPassword(), true);
            stmt.execute();
            this.insertRoles(user);
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void insertUserRole(final String userid, final String role) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into user_role (userid, role) values ($userid, $role)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("userid", userid);
            stmt.setString("role", role);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void deleteRolesByUser(final String userid) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from user_role where userid = $userid");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("userid", userid);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final User user) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from user where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", user.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final User user) throws DbException {
        final Transaction tr = new Transaction();
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update user\nset status = $status,\nstatus_ts = $status_ts,\nfirst_name = $first_name,\nlast_name = $last_name,\nemail = $email,\nphone = $phone,\npassword = $password\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", user.getId());
            stmt.setString("status", user.getStatus());
            stmt.set("status_ts", user.getStatusTimestamp());
            stmt.setString("first_name", user.getFirstName());
            stmt.setString("last_name", user.getLastName());
            stmt.setString("email", user.getEmail());
            stmt.setString("phone", user.getPhone());
            stmt.setString("password", user.getPassword(), true);
            stmt.execute();
            tr.begin();
            this.deleteRolesByUser(user.getId());
            this.insertRoles(user);
            tr.commit();
        }
        finally {
            db.close();
            tr.rollback();
        }
        db.close();
        tr.rollback();
    }
    
    public User build(final DbResultSet result, final boolean stub) throws DbException {
        final User u = new User(result.getString("id"));
        if (stub) {
            u.trim();
            return u;
        }
        u.setCreated(result.getLong("created"));
        u.setStatus(result.getString("status"));
        u.setStatusTimestamp(result.getLong("status_ts"));
        u.setFirstName(result.getString("first_name"));
        u.setLastName(result.getString("last_name"));
        u.setEmail(result.getString("email"));
        u.setPhone(result.getString("phone"));
        u.setPassword(result.getString("password", true));
        return u;
    }
    
    public void insertRoles(final User user) throws DbException {
        for (final String role : user.getRoles()) {
            this.insertUserRole(user.getId(), role);
        }
    }
}
