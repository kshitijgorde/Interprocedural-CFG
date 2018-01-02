// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import java.util.Set;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.utility.IdentityFactory;

public class Permission extends Access
{
    private String id;
    public static IdentityFactory identityFactory;
    
    static {
        Permission.identityFactory = new IdentityFactory();
    }
    
    public Permission(final IModelObject root) {
        super(root);
        this.setId(root.getID());
    }
    
    public Permission(final String id) {
        this.setId(id);
    }
    
    public Permission(final FeatureAccess fa, final String targetId) {
        super(fa, targetId);
        this.id = Permission.identityFactory.next();
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public boolean hasPermission(final Set<String> userPermissions) {
        return this.selectPermission(userPermissions, "*") || this.selectPermission(userPermissions, this.targetId);
    }
    
    protected boolean selectPermission(final Set<String> userPermissions, final String id) {
        String access = this.fa.getType().getValue();
        if (this.fa.hasVariable()) {
            access = access.replace("$" + this.fa.getVariableName(), "'" + id + "'");
        }
        return userPermissions.contains(access);
    }
    
    @Override
    public IModelObject getRoot() {
        return this.fa.getAccessAsObject(this.targetId);
    }
}
