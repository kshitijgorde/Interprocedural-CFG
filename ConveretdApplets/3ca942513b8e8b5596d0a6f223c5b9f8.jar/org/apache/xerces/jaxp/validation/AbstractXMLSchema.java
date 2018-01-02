// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.XMLGrammarPool;
import javax.xml.validation.ValidatorHandler;
import javax.xml.validation.Validator;
import javax.xml.validation.Schema;

abstract class AbstractXMLSchema extends Schema implements XSGrammarPoolContainer
{
    public Validator newValidator() {
        return new XMLSchemaValidator(this);
    }
    
    public ValidatorHandler newValidatorHandler() {
        return new XMLSchemaValidatorHandler(this);
    }
    
    public abstract boolean isFullyComposed();
    
    public abstract XMLGrammarPool getGrammarPool();
}
