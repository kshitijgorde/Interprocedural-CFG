// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Enumeration;
import org.apache.xerces.utils.XMLCharacterProperties;
import java.util.Locale;
import java.util.Hashtable;

public class IDREFDatatypeValidator extends AbstractDatatypeValidator
{
    private DatatypeValidator fBaseValidator;
    private Hashtable fTableOfId;
    private Hashtable fTableIDRefs;
    private Object fNullValue;
    private Locale fLocale;
    private DatatypeMessageProvider fMessageProvider;
    public static final int IDREF_STORE = 0;
    public static final int IDREF_CLEAR = 1;
    public static final int IDREF_VALIDATE = 2;
    
    public IDREFDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public IDREFDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fBaseValidator = null;
        this.fTableOfId = null;
        this.fTableIDRefs = null;
        this.fNullValue = null;
        this.fLocale = null;
        this.fMessageProvider = new DatatypeMessageProvider();
        this.setBasetype(basetype);
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        if (o != null) {
            final StateMessageDatatype stateMessageDatatype = (StateMessageDatatype)o;
            if (stateMessageDatatype.getDatatypeState() == 1) {
                if (this.fTableOfId != null) {
                    this.fTableOfId.clear();
                }
                if (this.fTableIDRefs != null) {
                    this.fTableIDRefs.clear();
                }
                return null;
            }
            if (stateMessageDatatype.getDatatypeState() == 2) {
                this.checkIdRefs();
            }
            else if (stateMessageDatatype.getDatatypeState() == 0) {
                this.fTableOfId = (Hashtable)stateMessageDatatype.getDatatypeObject();
                if (!XMLCharacterProperties.validName(s)) {
                    final InvalidDatatypeValueException ex = new InvalidDatatypeValueException("IDREF is not valid");
                    ex.setMinorCode(77);
                    ex.setMajorCode(2);
                    throw ex;
                }
                this.addIdRef(s, o);
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
    
    private void addIdRef(final String s, final Object o) {
        if (this.fTableOfId != null && this.fTableOfId.containsKey(s)) {
            return;
        }
        if (this.fTableIDRefs == null) {
            this.fTableIDRefs = new Hashtable();
        }
        else if (this.fTableIDRefs.containsKey(s)) {
            return;
        }
        if (this.fNullValue == null) {
            this.fNullValue = new Object();
        }
        try {
            this.fTableIDRefs.put(s, this.fNullValue);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Out of Memory: Hashtable of ID's has " + this.fTableIDRefs.size() + " Elements");
            outOfMemoryError.printStackTrace();
        }
    }
    
    private void checkIdRefs() throws InvalidDatatypeValueException {
        if (this.fTableIDRefs == null) {
            return;
        }
        final Enumeration<String> keys = this.fTableIDRefs.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (this.fTableOfId == null || !this.fTableOfId.containsKey(s)) {
                final InvalidDatatypeValueException ex = new InvalidDatatypeValueException(s);
                ex.setMinorCode(81);
                ex.setMajorCode(2);
                throw ex;
            }
        }
    }
    
    public void setLocale(final Locale fLocale) {
        this.fLocale = fLocale;
    }
    
    private String getErrorString(final int n, final int n2, final Object[] array) {
        try {
            return this.fMessageProvider.createMessage(this.fLocale, n, n2, array);
        }
        catch (Exception ex) {
            return "Illegal Errorcode " + n2;
        }
    }
}
