// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Iterator;
import com.stonewall.cornerstone.entity.IPInterface;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.entity.PolicyServer;
import com.stonewall.cornerstone.db.DbSession;

public class PolicyServerDbAccess extends DbAccess
{
    static final String insert = "insert into policy_server\n(id, name)\nvalues\n($id, $name)";
    static final String fetchIds = "select id from policy_server";
    static final String delete = "delete from policy_server where id = $id";
    static final String fetchById = "select * from policy_server where id = $id";
    static final String fetchAll = "select * from policy_server";
    static final String fetchAllStubs = "select id from policy_server";
    
    public PolicyServerDbAccess() {
        super(DbSession.DB.main);
    }
    
    public void insert(final PolicyServer server) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into policy_server\n(id, name)\nvalues\n($id, $name)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", server.getId());
            stmt.setString("name", server.getName());
            stmt.execute();
            new IPInterfaceDbAccess(this.getDb()).insert(server.getInterfaces());
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final PolicyServer server) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            new IPInterfaceDbAccess(this.getDb()).deleteByParent(server.getId());
            final DbStatement stmt = db.createStatement("delete from policy_server where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.set("id", server.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public PolicyServer fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public PolicyServer fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final PolicyServer p = new PolicyServer(id);
                p.trim();
                return p;
            }
            final DbStatement stmt = session.createStatement("select * from policy_server where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final PolicyServer p2 = this.build(result, stub);
                return p2;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<PolicyServer> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<PolicyServer> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<PolicyServer> list = new ArrayList<PolicyServer>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from policy_server");
            }
            else {
                stmt = session.createStatement("select * from policy_server");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final PolicyServer p = this.build(result, stub);
                list.add(p);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public List<String> fetchIds() throws DbException {
        final DbSession session = this.getDbSession();
        final List<String> list = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("select id from policy_server");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(result.getString("id"));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    private PolicyServer build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final PolicyServer p = new PolicyServer(result.getString("id"));
            p.trim();
            return p;
        }
        final PolicyServer p = new PolicyServer(result.getString("id"));
        p.setName(result.getString("name"));
        for (final IPInterface intf : new IPInterfaceDbAccess(this.getDb()).fetchByParent(p.getId())) {
            p.addInterface(intf);
        }
        return p;
    }
}
