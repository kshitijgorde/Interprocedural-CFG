// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

import org.apache.xerces.xni.parser.XMLComponentManager;

public class XML11DTDValidator extends XMLDTDValidator
{
    protected static final String DTD_VALIDATOR_PROPERTY = "http://apache.org/xml/properties/internal/validator/dtd";
    
    public void reset(final XMLComponentManager xmlComponentManager) {
        final XMLDTDValidator xmldtdValidator;
        if ((xmldtdValidator = (XMLDTDValidator)xmlComponentManager.getProperty("http://apache.org/xml/properties/internal/validator/dtd")) != null && xmldtdValidator != this) {
            super.fGrammarBucket = xmldtdValidator.getGrammarBucket();
        }
        super.reset(xmlComponentManager);
    }
    
    protected void init() {
        if (super.fValidation || super.fDynamicValidation) {
            super.init();
            try {
                super.fValID = super.fDatatypeValidatorFactory.getBuiltInDV("XML11ID");
                super.fValIDRef = super.fDatatypeValidatorFactory.getBuiltInDV("XML11IDREF");
                super.fValIDRefs = super.fDatatypeValidatorFactory.getBuiltInDV("XML11IDREFS");
                super.fValNMTOKEN = super.fDatatypeValidatorFactory.getBuiltInDV("XML11NMTOKEN");
                super.fValNMTOKENS = super.fDatatypeValidatorFactory.getBuiltInDV("XML11NMTOKENS");
            }
            catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }
    }
}
