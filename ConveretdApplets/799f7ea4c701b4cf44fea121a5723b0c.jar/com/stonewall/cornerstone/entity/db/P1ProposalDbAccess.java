// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.policy.security.P1Proposal;
import com.stonewall.cornerstone.entity.Label;

public class P1ProposalDbAccess extends DbAccess
{
    static final String fetchAll = "select * from p1_proposal ";
    static final String fetchAllStubs = "select id from p1_proposal ";
    static final String fetchById = "select * from p1_proposal where id = $id";
    static final String insert = "insert into p1_proposal\n(id, name, grp, cipher, hash, auth, lifetime, unit, prop_grp, description)\nvalues\n($id, $name, $grp, $cipher, $hash, $auth, $lifetime, $unit, $prop_grp, $description)";
    static final String update = "update p1_proposal set\nname = $name, \ngrp = $grp, \ncipher = $cipher, \nhash = $hash, \nauth = $auth, \nlifetime = $lifetime, \nunit = $unit, \nprop_grp = $prop_grp, \ndescription = $description\nwhere id = $id";
    static final String delete = "delete from p1_proposal where id = $id \n";
    
    public P1ProposalDbAccess() {
    }
    
    public P1ProposalDbAccess(final Label label) {
        super(label);
    }
    
    public P1ProposalDbAccess(final String label) {
        super(label);
    }
    
    public P1Proposal fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public P1Proposal fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final P1Proposal p1 = new P1Proposal(id);
                p1.trim();
                return p1;
            }
            final DbStatement stmt = session.createStatement("select * from p1_proposal where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return build(result, stub);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<P1Proposal> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<P1Proposal> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<P1Proposal> proposals = new ArrayList<P1Proposal>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from p1_proposal ");
            }
            else {
                stmt = session.createStatement("select * from p1_proposal ");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                proposals.add(build(result, stub));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return proposals;
    }
    
    public void insert(final P1Proposal p) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into p1_proposal\n(id, name, grp, cipher, hash, auth, lifetime, unit, prop_grp, description)\nvalues\n($id, $name, $grp, $cipher, $hash, $auth, $lifetime, $unit, $prop_grp, $description)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", p.getId());
            stmt.setString("name", p.getName());
            stmt.setString("grp", p.getGroup());
            stmt.setString("cipher", p.getCipher());
            stmt.setString("hash", p.getHash());
            stmt.setString("auth", p.getAuthMethod());
            stmt.set("lifetime", p.getLifetime());
            stmt.setString("unit", p.getUnit());
            stmt.setString("prop_grp", p.getProposalGroup());
            stmt.setString("description", p.getDescription());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final P1Proposal p) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update p1_proposal set\nname = $name, \ngrp = $grp, \ncipher = $cipher, \nhash = $hash, \nauth = $auth, \nlifetime = $lifetime, \nunit = $unit, \nprop_grp = $prop_grp, \ndescription = $description\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", p.getId());
            stmt.setString("name", p.getName());
            stmt.setString("grp", p.getGroup());
            stmt.setString("cipher", p.getCipher());
            stmt.setString("hash", p.getHash());
            stmt.setString("auth", p.getAuthMethod());
            stmt.set("lifetime", p.getLifetime());
            stmt.setString("unit", p.getUnit());
            stmt.setString("prop_grp", p.getProposalGroup());
            stmt.setString("description", p.getDescription());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final P1Proposal p) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from p1_proposal where id = $id \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", p.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private static P1Proposal build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final P1Proposal p = new P1Proposal();
            p.setId(result.getString("id"));
            p.trim();
            return p;
        }
        final P1Proposal p = new P1Proposal();
        p.setId(result.getString("id"));
        p.setName(result.getString("name"));
        p.setCipher(result.getString("cipher"));
        p.setGroup(result.getString("grp"));
        p.setHash(result.getString("hash"));
        p.setAuthMethod(result.getString("auth"));
        p.setLifetime(result.getInteger("lifetime"), result.getString("unit"));
        p.setProposalGroup(result.getString("prop_grp"));
        p.setDescription(result.getString("description"));
        return p;
    }
}
