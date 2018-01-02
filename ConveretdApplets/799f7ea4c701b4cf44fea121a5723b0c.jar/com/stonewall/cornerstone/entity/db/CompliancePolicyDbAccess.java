// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.policy.compliance.CompliancePolicy;
import java.util.List;
import com.stonewall.cornerstone.db.DbSession;

public class CompliancePolicyDbAccess extends DbAccess
{
    static final String fetchAll = "select * from compliance_policy";
    static final String fetchAllStubs = "select id, timestamp from compliance_policy";
    static final String fetchById = "select * from compliance_policy where id = $id";
    static final String fetchStubById = "select id, timestamp from compliance_policy where id = $id";
    static final String fetchByIds = "select * from compliance_policy where id in $ids";
    static final String fetchAllIdAndName = "select id, name from compliance_policy";
    static final String fetchIdAndNameById = "select id, name from compliance_policy where id in $ids";
    static final String insert = "insert into compliance_policy\n(id, timestamp, name, content)\nvalues\n($id, $timestamp, $name, $content)";
    static final String update = "update compliance_policy set\ntimestamp = $timestamp, \nname = $name, \ncontent = $content \nwhere id = $id ";
    static final String delete = "delete from compliance_policy where id = $id";
    
    public CompliancePolicyDbAccess() {
        super(DbSession.DB.main);
    }
    
    public List<CompliancePolicy> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<CompliancePolicy> fetchAll(final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        final List<CompliancePolicy> list = new ArrayList<CompliancePolicy>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id, timestamp from compliance_policy");
            }
            else {
                stmt = db.createStatement("select * from compliance_policy");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(this.build(result, stub));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public CompliancePolicy fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public CompliancePolicy fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, timestamp from compliance_policy where id = $id");
            }
            else {
                stmt = session.createStatement("select * from compliance_policy where id = $id");
            }
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.build(result, stub);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<CompliancePolicy> fetchById(final List<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        final List<CompliancePolicy> policies = new ArrayList<CompliancePolicy>();
        try {
            final DbStatement stmt = session.createStatement("select * from compliance_policy where id in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                policies.add(this.build(result, false));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return policies;
    }
    
    public List<CompliancePolicy> fetchIdAndName() throws DbException {
        final DbSession db = this.getDbSession();
        final List<CompliancePolicy> list = new ArrayList<CompliancePolicy>();
        try {
            final DbStatement stmt = db.createStatement("select id, name from compliance_policy");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final CompliancePolicy p = new CompliancePolicy(result.getString("id"));
                p.setName(result.getString("name"));
                list.add(p);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public List<CompliancePolicy> fetchIdAndName(final List<String> ids) throws DbException {
        final DbSession db = this.getDbSession();
        final List<CompliancePolicy> policies = new ArrayList<CompliancePolicy>();
        try {
            final DbStatement stmt = db.createStatement("select id, name from compliance_policy where id in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final CompliancePolicy p = new CompliancePolicy(result.getString("id"));
                p.setName(result.getString("name"));
                policies.add(p);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return policies;
    }
    
    public void insert(final CompliancePolicy policy) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into compliance_policy\n(id, timestamp, name, content)\nvalues\n($id, $timestamp, $name, $content)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", policy.getId());
            stmt.setString("content", policy.getRoot());
            stmt.set("timestamp", policy.getTimestamp());
            stmt.setString("name", policy.getName());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final CompliancePolicy policy) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update compliance_policy set\ntimestamp = $timestamp, \nname = $name, \ncontent = $content \nwhere id = $id ");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", policy.getId());
            stmt.setString("content", policy.getRoot());
            stmt.set("timestamp", policy.getTimestamp());
            stmt.setString("name", policy.getName());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final CompliancePolicy policy) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from compliance_policy where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", policy.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    protected CompliancePolicy build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final CompliancePolicy p = new CompliancePolicy(result.getString("id"));
            p.setTimestamp(result.getLong("timestamp"));
            p.trim();
            return p;
        }
        final CompliancePolicy p = new CompliancePolicy(result.getElement("content"));
        return p;
    }
}
