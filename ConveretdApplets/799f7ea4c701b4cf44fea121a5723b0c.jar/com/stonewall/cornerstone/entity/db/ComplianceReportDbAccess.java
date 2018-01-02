// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.db.DbResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.entity.ComplianceReport;
import com.stonewall.cornerstone.db.DbSession;

public class ComplianceReportDbAccess extends DbAccess
{
    static final String fetchAll = "select * from compliance_report order by timestamp";
    static final String fetchAllStubs = "select id from compliance_report order by timestamp";
    static final String fetchById = "select * from compliance_report where id = $id";
    static final String fetchStubById = "select id from compliance_report where id = $id";
    static final String fetchReportByPolicy = "select * from compliance_report where policy = $policy";
    static final String insert = "insert into compliance_report\n(id, policy, userid, timestamp, content)\nvalues\n($id, $policy, $userid, $timestamp, $content)";
    static final String update = "update compliance_report set\ntimestamp = $timestamp \ncontent = $content\n where id = $id ";
    
    public ComplianceReportDbAccess() {
        super(DbSession.DB.main);
    }
    
    public void insert(final ComplianceReport report) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into compliance_report\n(id, policy, userid, timestamp, content)\nvalues\n($id, $policy, $userid, $timestamp, $content)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", report.getId());
            stmt.setString("policy", report.getPolicy().getId());
            stmt.setString("userid", report.getUser());
            stmt.set("timestamp", report.getTimestamp());
            stmt.setString("content", report.getRoot().cloneTree());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final ComplianceReport report) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update compliance_report set\ntimestamp = $timestamp \ncontent = $content\n where id = $id ");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", report.getId());
            stmt.set("timestamp", report.getTimestamp());
            stmt.setString("content", report.getRoot().cloneTree());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public Collection<ComplianceReport> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<ComplianceReport> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<ComplianceReport> reports = new ArrayList<ComplianceReport>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from compliance_report order by timestamp");
            }
            else {
                stmt = session.createStatement("select * from compliance_report order by timestamp");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final ComplianceReport report = this.build(result, stub);
                reports.add(report);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return reports;
    }
    
    public ComplianceReport fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public ComplianceReport fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final ComplianceReport report = new ComplianceReport(id);
                report.trim();
                return report;
            }
            final DbStatement stmt = session.createStatement("select * from compliance_report where id = $id");
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
    
    public ComplianceReport fetchReportByPolicy(final String id) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from compliance_report where policy = $policy");
            stmt.setString("policy", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.build(result, false);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public ComplianceReport build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final ComplianceReport report = new ComplianceReport(result.getString("id"));
            report.trim();
            return report;
        }
        final ComplianceReport report = new ComplianceReport(result.getElement("content"));
        return report;
    }
}
