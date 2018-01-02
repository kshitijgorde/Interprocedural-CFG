// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema;

import org.apache.xerces.validators.dtd.InsertableElementsInfo;
import org.apache.xerces.validators.datatype.DatatypeValidator;
import org.apache.xerces.validators.datatype.InvalidDatatypeValueException;
import org.apache.xerces.framework.XMLContentSpecNode;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.validators.dtd.ElementDeclPool;
import org.apache.xerces.validators.dtd.XMLContentModel;

public class DatatypeContentModel implements XMLContentModel
{
    XSchemaValidator.DatatypeValidatorRegistry fDatatypeRegistry;
    ElementDeclPool fElementDeclPool;
    StringPool fStringPool;
    int fChild;
    
    public DatatypeContentModel(final XSchemaValidator.DatatypeValidatorRegistry fDatatypeRegistry, final ElementDeclPool fElementDeclPool, final StringPool fStringPool, final int fChild) {
        this.fChild = -1;
        this.fDatatypeRegistry = fDatatypeRegistry;
        this.fElementDeclPool = fElementDeclPool;
        this.fStringPool = fStringPool;
        this.fChild = fChild;
    }
    
    public int validateContent(final int n, final int[] array) throws Exception {
        try {
            final int contentSpec = this.fElementDeclPool.getContentSpec(this.fChild);
            final XMLContentSpecNode xmlContentSpecNode = new XMLContentSpecNode();
            this.fElementDeclPool.getContentSpecNode(contentSpec, xmlContentSpecNode);
            final String string = this.fStringPool.toString(xmlContentSpecNode.value);
            final DatatypeValidator validator = this.fDatatypeRegistry.getValidatorFor(string);
            if (validator != null) {
                validator.validate(this.fStringPool.toString(array[0]));
            }
            else {
                System.out.println("No validator for datatype " + string);
            }
        }
        catch (InvalidDatatypeValueException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            System.out.println("Internal error in datatype validation");
        }
        return -1;
    }
    
    public int whatCanGoHere(final boolean b, final InsertableElementsInfo insertableElementsInfo) throws Exception {
        return -1;
    }
}
