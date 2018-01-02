// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.entity.policy.compliance.ComplianceRule;
import java.util.Date;
import org.xmodel.IModelObject;

public class ComplianceRecord extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.complianceRecord.getQualifiedName();
    }
    
    public ComplianceRecord(final String id) {
        super(ComplianceRecord.tag, id);
    }
    
    public ComplianceRecord(final IModelObject e) {
        super(e);
    }
    
    public ComplianceRecord() {
        super(ComplianceRecord.tag);
        this.setTimestamp(new Date().getTime());
    }
    
    public void setRule(final ComplianceRule rule) {
        this.root.addChild(rule.getReference().getRoot());
    }
    
    public String getRuleId() {
        return this.root.getFirstChild("en:complianceRule").getID();
    }
    
    public void setViolation(final String violation) {
        this.root.getCreateChild("en:violation").setValue(violation);
    }
    
    public String getViolation() {
        return Xlate.get(this.root.getCreateChild("en:violation"), (String)null);
    }
}
