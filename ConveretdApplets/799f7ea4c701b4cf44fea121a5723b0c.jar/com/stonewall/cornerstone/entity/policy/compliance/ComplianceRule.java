// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.compliance;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.policy.PolicyRule;

public class ComplianceRule extends PolicyRule
{
    public static String tag;
    
    static {
        ComplianceRule.tag = EntityFactory.EntityType.complianceRule.getQualifiedName();
    }
    
    public ComplianceRule() {
        super(ComplianceRule.tag);
    }
    
    public ComplianceRule(final IModelObject root) {
        super(root);
    }
    
    public ComplianceRule(final String id) {
        super(ComplianceRule.tag, id);
    }
    
    public String getClassId() {
        return Xlate.childGet(this.root, "en:classId", (String)null);
    }
    
    public IModelObject getInput() {
        return this.root.getFirstChild("en:input");
    }
    
    @Override
    public ComplianceRule clone() {
        return new ComplianceRule(this.root.cloneTree());
    }
}
