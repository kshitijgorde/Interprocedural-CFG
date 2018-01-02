// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import org.apache.xerces.utils.XMLCharacterProperties;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Locale;
import java.util.Hashtable;

public class IDDatatypeValidator extends AbstractDatatypeValidator
{
    private DatatypeValidator fBaseValidator;
    private Object fNullValue;
    private DatatypeMessageProvider fMessageProvider;
    private Hashtable fTableOfId;
    private Locale fLocale;
    private int fFacetsDefined;
    private int fLength;
    private Vector fEnumeration;
    public static final int IDREF_STORE = 0;
    public static final int ID_CLEAR = 1;
    
    public IDDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public IDDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fBaseValidator = null;
        this.fNullValue = null;
        this.fMessageProvider = new DatatypeMessageProvider();
        this.fLocale = null;
        this.fFacetsDefined = 0;
        this.fLength = 0;
        this.fEnumeration = new Vector();
        this.setBasetype(basetype);
        if (hashtable != null) {
            final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (s.equals("enumeration")) {
                    this.fFacetsDefined += 16;
                    this.fEnumeration = (Vector)hashtable.get(s);
                }
                else {
                    if (!s.equals("length")) {
                        continue;
                    }
                    ++this.fFacetsDefined;
                    final String s2 = hashtable.get(s);
                    try {
                        this.fLength = Integer.parseInt(s2);
                    }
                    catch (NumberFormatException ex) {
                        throw new InvalidDatatypeFacetException("Length value '" + s2 + "' is invalid.");
                    }
                    if (this.fLength < 0) {
                        throw new InvalidDatatypeFacetException("Length value '" + s2 + "'  must be a nonNegativeInteger.");
                    }
                    continue;
                }
            }
        }
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        if (this.fBaseValidator != null) {
            if ((this.fFacetsDefined & 0x1) != 0x0 && s.length() != this.fLength) {
                throw new InvalidDatatypeValueException("Value '" + s + "' with length '" + s.length() + "' is not equal to length facet '" + this.fLength + "'.");
            }
            if ((this.fFacetsDefined & 0x10) != 0x0 && !this.fEnumeration.contains(s)) {
                throw new InvalidDatatypeValueException("Value '" + s + "' must be one of " + this.fEnumeration);
            }
        }
        if (o != null && ((StateMessageDatatype)o).getDatatypeState() == 1) {
            if (this.fTableOfId != null) {
                this.fTableOfId.clear();
                this.fTableOfId = null;
            }
            return null;
        }
        if (!XMLCharacterProperties.validName(s)) {
            final InvalidDatatypeValueException ex = new InvalidDatatypeValueException("ID is not valid: " + s);
            ex.setMinorCode(75);
            ex.setMajorCode(76);
            throw ex;
        }
        if (!this.addId(s, o)) {
            final InvalidDatatypeValueException ex2 = new InvalidDatatypeValueException("ID '" + s + "'  has to be unique");
            ex2.setMinorCode(76);
            ex2.setMajorCode(76);
            throw ex2;
        }
        return this.fTableOfId;
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("clone() is not supported in " + this.getClass().getName());
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
    
    private boolean addId(final String s, final Object o) {
        if (this.fTableOfId == null) {
            this.fTableOfId = new Hashtable();
        }
        else if (this.fTableOfId.containsKey(s)) {
            return false;
        }
        if (this.fNullValue == null) {
            this.fNullValue = new Object();
        }
        try {
            this.fTableOfId.put(s, this.fNullValue);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
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
