// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.psvi.XSAnnotation;
import org.apache.xerces.impl.xs.psvi.XSNotationDeclaration;

public class XSNotationDecl implements XSNotationDeclaration
{
    public String fName;
    public String fTargetNamespace;
    public String fPublicId;
    public String fSystemId;
    
    public XSNotationDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fPublicId = null;
        this.fSystemId = null;
    }
    
    public short getType() {
        return 11;
    }
    
    public String getName() {
        return this.fName;
    }
    
    public String getNamespace() {
        return this.fTargetNamespace;
    }
    
    public String getSystemId() {
        return this.fSystemId;
    }
    
    public String getPublicId() {
        return this.fPublicId;
    }
    
    public XSAnnotation getAnnotation() {
        return null;
    }
}
