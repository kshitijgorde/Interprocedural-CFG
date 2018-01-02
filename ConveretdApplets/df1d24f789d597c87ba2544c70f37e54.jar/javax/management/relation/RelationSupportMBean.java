// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

public interface RelationSupportMBean extends Relation
{
    Boolean isInRelationService();
    
    void setRelationServiceManagementFlag(final Boolean p0) throws IllegalArgumentException;
}
