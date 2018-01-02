// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import java.util.List;
import java.io.Serializable;

public interface RelationType extends Serializable
{
    String getRelationTypeName();
    
    List getRoleInfos();
    
    RoleInfo getRoleInfo(final String p0) throws IllegalArgumentException, RoleInfoNotFoundException;
}
