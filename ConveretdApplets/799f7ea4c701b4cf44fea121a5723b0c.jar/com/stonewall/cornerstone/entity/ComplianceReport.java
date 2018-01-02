// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.Xlate;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.policy.compliance.CompliancePolicy;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.ComplianceReportDbAccess;
import org.xmodel.IModelObject;

public class ComplianceReport extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.complianceReport.getQualifiedName();
    }
    
    public ComplianceReport() {
        super(ComplianceReport.tag);
        this.init();
    }
    
    public ComplianceReport(final IModelObject root) {
        super(root);
    }
    
    public ComplianceReport(final String id) {
        super(ComplianceReport.tag, id);
        this.init();
    }
    
    protected void init() {
        this.root.getCreateChild("en:records");
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new ComplianceReportDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new ComplianceReportDbAccess().insert(this);
    }
    
    @Override
    public void update(final Label label) throws DbException {
        new ComplianceReportDbAccess().update(this);
    }
    
    public List<ComplianceRecord> getRecords() {
        final List<ComplianceRecord> records = new ArrayList<ComplianceRecord>();
        final List<IModelObject> children = this.root.getFirstChild("en:records").getChildren("en:complianceRecord");
        for (final IModelObject e : children) {
            records.add(new ComplianceRecord(e));
        }
        return records;
    }
    
    public void add(final ComplianceRecord record) {
        record.setParentId(this.getId());
        final IModelObject e = this.root.getCreateChild("en:records");
        e.addChild(record.getRoot());
    }
    
    public void addAll(final List<ComplianceRecord> records) {
        for (final ComplianceRecord record : records) {
            this.add(record);
        }
    }
    
    public void setRecords(final List<ComplianceRecord> records) {
        final IModelObject e = this.root.getCreateChild("en:records");
        e.removeChildren();
        for (final ComplianceRecord r : records) {
            e.addChild(r.getRoot());
        }
    }
    
    public void setPolicy(final CompliancePolicy policy) {
        this.root.addChild(policy.getReference().getRoot());
    }
    
    public EntityReference getPolicy() {
        return new EntityReference(this.root.getFirstChild("en:compliancePolicy"));
    }
    
    public void setUser(final String id) {
        final IModelObject user = new Element("en:user", id);
        this.root.addChild(user);
    }
    
    public String getUser() {
        return Xlate.get(this.root.getFirstChild("en:user"), "id", (String)null);
    }
}
