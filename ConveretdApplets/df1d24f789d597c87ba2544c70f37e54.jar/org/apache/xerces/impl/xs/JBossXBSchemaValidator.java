// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.QName;

public class JBossXBSchemaValidator extends XMLSchemaValidator
{
    Augmentations handleStartElement(final QName element, final XMLAttributes attributes, final Augmentations augs) {
        final Augmentations modifiedAugs = super.handleStartElement(element, attributes, augs);
        if (modifiedAugs != null) {
            modifiedAugs.putItem("jbossxb.validator", (Object)this);
        }
        return modifiedAugs;
    }
    
    public XSElementDeclaration getCurrentElementDelcaration() {
        return null;
    }
}
