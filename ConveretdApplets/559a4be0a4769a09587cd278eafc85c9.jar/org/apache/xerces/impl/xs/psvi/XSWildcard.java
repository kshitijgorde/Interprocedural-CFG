// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSWildcard extends XSTerm
{
    public static final short PC_STRICT = 1;
    public static final short PC_SKIP = 2;
    public static final short PC_LAX = 3;
    public static final short NSCONSTRAINT_ANY = 1;
    public static final short NSCONSTRAINT_NOT = 2;
    public static final short NSCONSTRAINT_LIST = 3;
    
    short getConstraintType();
    
    StringList getNSConstraintList();
    
    short getProcessContents();
    
    XSAnnotation getAnnotation();
}
