// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.log.Log;

public abstract class Access implements Comparable
{
    protected FeatureAccess fa;
    protected String targetId;
    static final Log log;
    
    static {
        log = Log.getLog(Access.class);
    }
    
    public Access() {
    }
    
    public Access(final IModelObject root) {
        this.fa = FeatureAccess.Type.valueOf(Xlate.get(root, (String)null)).getFeatureAccess();
        this.targetId = Xlate.get(root, this.fa.getVariableName(), (String)null);
    }
    
    public Access(final FeatureAccess fa, final String targetId) {
        this.fa = fa;
        this.targetId = targetId;
    }
    
    public String getTargetId() {
        return this.targetId;
    }
    
    public void setTargetId(final String targetId) {
        this.targetId = targetId;
    }
    
    public FeatureAccess.Type getType() {
        return this.fa.getType();
    }
    
    public void setType(final FeatureAccess.Type type) {
        this.fa = type.getFeatureAccess();
    }
    
    @Override
    public String toString() {
        if (this.fa.hasVariable()) {
            return this.fa.toString().replace("$" + this.fa.getVariableName(), "'" + this.targetId + "'");
        }
        return this.fa.toString();
    }
    
    @Override
    public int compareTo(final Object o) {
        final Access a = (Access)o;
        return this.fa.getType().name().compareTo(a.fa.getType().name());
    }
    
    public abstract IModelObject getRoot();
    
    public String getTargetPath() {
        return this.fa.getTargetPath();
    }
    
    public String getVariableName() {
        return this.fa.getVariableName();
    }
    
    public boolean hasVariable() {
        return this.fa.hasVariable();
    }
    
    public String getMutexPath() {
        return this.fa.getMutexPath();
    }
    
    public String getPermissionPath() {
        return this.fa.getPermissionPath();
    }
    
    public String getPath() {
        return this.fa.getPath();
    }
    
    public String getAsString() {
        return this.fa.getAccessAsString(this.targetId);
    }
    
    public String getActionPath() {
        return this.fa.getActionPath();
    }
    
    public String getActionAsString() {
        return this.fa.getActionAsString();
    }
    
    public IModelObject getActionAsObject() {
        return this.fa.getActionAsObject();
    }
    
    public IModelObject getAccessAsObject() {
        return this.fa.getAccessAsObject(this.targetId);
    }
}
