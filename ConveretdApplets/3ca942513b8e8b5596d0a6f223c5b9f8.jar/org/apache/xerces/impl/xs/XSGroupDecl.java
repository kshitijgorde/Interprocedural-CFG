// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.xs.XSModelGroup;
import org.apache.xerces.xs.XSModelGroupDefinition;

public class XSGroupDecl implements XSModelGroupDefinition
{
    public String fName;
    public String fTargetNamespace;
    public XSModelGroupImpl fModelGroup;
    public XSAnnotationImpl fAnnotation;
    
    public XSGroupDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fModelGroup = null;
        this.fAnnotation = null;
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
        return this.fAnnotation;
    }
    
    public XSNamespaceItem getNamespaceItem() {
        return null;
    }
}
