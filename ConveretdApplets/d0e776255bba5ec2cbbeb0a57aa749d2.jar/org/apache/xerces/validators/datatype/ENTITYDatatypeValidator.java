// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Hashtable;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.readers.DefaultEntityHandler;

public class ENTITYDatatypeValidator extends AbstractDatatypeValidator
{
    private DatatypeValidator fBaseValidator;
    private DefaultEntityHandler fEntityHandler;
    private StringPool fStringPool;
    public static final int ENTITY_INITIALIZE = 0;
    
    public ENTITYDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public ENTITYDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fBaseValidator = null;
        this.fEntityHandler = null;
        this.fStringPool = null;
        this.setBasetype(basetype);
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        final StateMessageDatatype stateMessageDatatype = (StateMessageDatatype)o;
        if (stateMessageDatatype != null && stateMessageDatatype.getDatatypeState() == 0) {
            final Object[] array = (Object[])stateMessageDatatype.getDatatypeObject();
            this.fEntityHandler = (DefaultEntityHandler)array[0];
            this.fStringPool = (StringPool)array[1];
        }
        else {
            if (this.fEntityHandler == null) {
                throw new InvalidDatatypeValueException("ERROR: ENTITYDatatype Validator: Failed Initialization DefaultEntityHandler is null");
            }
            if (this.fStringPool == null) {
                throw new InvalidDatatypeValueException("ERROR: ENTITYDatatype Validator: Failed Initialization StrinPool is null");
            }
            if (!this.fEntityHandler.isUnparsedEntity(this.fStringPool.addSymbol(s))) {
                final InvalidDatatypeValueException ex = new InvalidDatatypeValueException("ENTITY '" + s + "' is not valid");
                ex.setMinorCode(79);
                ex.setMajorCode(77);
                throw ex;
            }
        }
        return null;
    }
    
    public int compare(final String s, final String s2) {
        return -1;
    }
    
    public Hashtable getFacets() {
        return null;
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("clone() is not supported in " + this.getClass().getName());
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
}
