// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.policy.security.Transform;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.policy.security.P2Proposal;
import com.stonewall.cornerstone.entity.Label;

public class P2ProposalDbAccess extends DbAccess
{
    static final String fetchAll = "select * from p2_proposal ";
    static final String fetchAllStubs = "select id from p2_proposal ";
    static final String fetchById = "select * from p2_proposal where id = $id";
    static final String insert = "insert into p2_proposal\n(id, name, grp, lifesize, lifetime, unit, transform, prop_grp, description)\nvalues\n($id, $name, $grp, $lifesize, $lifetime, $unit, $transform, $prop_grp, $description)";
    static final String update = "update p2_proposal set\nname = $name, \ngrp = $grp, \nlifesize = $lifesize, \nlifetime = $lifetime, \nunit = $unit, \ntransform = $transform, \nprop_grp = $prop_grp, \ndescription = $description\nwhere id = $id";
    static final String delete = "delete from p2_proposal where id = $id \n";
    
    public P2ProposalDbAccess() {
    }
    
    public P2ProposalDbAccess(final Label label) {
        super(label);
    }
    
    public P2ProposalDbAccess(final String label) {
        super(label);
    }
    
    public P2Proposal fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public P2Proposal fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final P2Proposal p2 = new P2Proposal(id);
                p2.trim();
                return p2;
            }
            final DbStatement stmt = session.createStatement("select * from p2_proposal where id = $id");
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
    
    public List<P2Proposal> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<P2Proposal> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<P2Proposal> proposals = new ArrayList<P2Proposal>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from p2_proposal ");
            }
            else {
                stmt = session.createStatement("select * from p2_proposal ");
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
    
    public void insert(final P2Proposal p) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into p2_proposal\n(id, name, grp, lifesize, lifetime, unit, transform, prop_grp, description)\nvalues\n($id, $name, $grp, $lifesize, $lifetime, $unit, $transform, $prop_grp, $description)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", p.getId());
            stmt.setString("name", p.getName());
            stmt.setString("grp", p.getGroup());
            stmt.set("lifesize", p.getLifesize());
            stmt.set("lifetime", p.getLifetime());
            stmt.setString("unit", p.getUnit());
            stmt.setString("transform", p.getTransform().getRoot());
            stmt.setString("prop_grp", p.getProposalGroup());
            stmt.setString("description", p.getDescription());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final P2Proposal p) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update p2_proposal set\nname = $name, \ngrp = $grp, \nlifesize = $lifesize, \nlifetime = $lifetime, \nunit = $unit, \ntransform = $transform, \nprop_grp = $prop_grp, \ndescription = $description\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", p.getId());
            stmt.setString("name", p.getName());
            stmt.setString("grp", p.getGroup());
            stmt.set("lifesize", p.getLifesize());
            stmt.set("lifetime", p.getLifetime());
            stmt.setString("unit", p.getUnit());
            stmt.setString("transform", p.getTransform().getRoot());
            stmt.setString("prop_grp", p.getProposalGroup());
            stmt.setString("description", p.getDescription());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final P2Proposal p) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from p2_proposal where id = $id \n");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", p.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    private static P2Proposal build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final P2Proposal p = new P2Proposal();
            p.setId(result.getString("id"));
            p.trim();
            return p;
        }
        final P2Proposal p = new P2Proposal();
        p.setId(result.getString("id"));
        p.setName(result.getString("name"));
        p.setGroup(result.getString("grp"));
        p.setLifesize(result.getInteger("lifesize"));
        p.setLifetime(result.getInteger("lifetime"), result.getString("unit"));
        p.setTransform(Transform.createTransform(result.getElement("transform")));
        p.setProposalGroup(result.getString("prop_grp"));
        p.setDescription(result.getString("description"));
        return p;
    }
}
