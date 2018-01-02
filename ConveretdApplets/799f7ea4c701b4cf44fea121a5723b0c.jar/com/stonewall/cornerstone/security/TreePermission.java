// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import java.util.Iterator;
import com.stonewall.cornerstone.entity.EntityReference;
import java.util.Set;
import com.stonewall.cornerstone.db.DbException;
import java.util.Map;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.SiteTree;
import com.stonewall.cornerstone.entity.EntityFactory;

public class TreePermission extends Permission
{
    private EntityFactory.EntityType entityType;
    private SiteTree siteTree;
    
    public TreePermission(final IModelObject root) {
        super(root);
        this.siteTree = null;
    }
    
    public TreePermission(final FeatureAccess fa, final EntityFactory.EntityType entityType, final String targetId, final Map<String, Object> data) {
        super(fa, targetId);
        this.siteTree = null;
        this.entityType = entityType;
        try {
            this.siteTree = data.get("siteTree");
            if (this.siteTree == null) {
                data.put("siteTree", this.siteTree = new SiteTree());
            }
        }
        catch (DbException dbe) {
            TreePermission.log.error(this, dbe);
        }
    }
    
    @Override
    public boolean hasPermission(final Set<String> userPermissions) {
        if (super.hasPermission(userPermissions)) {
            return true;
        }
        for (final String parent : this.siteTree.getAncestorIds(new EntityReference(this.entityType, this.targetId))) {
            if (this.selectPermission(userPermissions, parent)) {
                return true;
            }
        }
        return false;
    }
}
