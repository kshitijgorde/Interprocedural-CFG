// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.psvi.XSAnnotation;
import org.apache.xerces.impl.xs.psvi.XSModelGroup;
import org.apache.xerces.impl.xs.psvi.XSModelGroupDefinition;

public class XSGroupDecl implements XSModelGroupDefinition
{
    public String fName;
    public String fTargetNamespace;
    public XSModelGroupImpl fModelGroup;
    
    public XSGroupDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fModelGroup = null;
    }
    
    public short getType() {
        return 6;
    }
    
    public String getName() {
        return this.fName;
    }
    
    public String getNamespace() {
        return this.fTargetNamespace;
    }
    
    public XSModelGroup getModelGroup() {
        return this.fModelGroup;
    }
    
    public XSAnnotation getAnnotation() {
        return null;
    }
}
