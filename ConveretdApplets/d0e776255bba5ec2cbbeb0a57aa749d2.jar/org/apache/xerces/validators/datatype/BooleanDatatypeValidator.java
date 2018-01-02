// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.xerces.utils.regex.RegularExpression;
import java.util.Locale;

public class BooleanDatatypeValidator extends AbstractDatatypeValidator
{
    private Locale fLocale;
    private DatatypeValidator fBaseValidator;
    private String fPattern;
    private int fFacetsDefined;
    private DatatypeMessageProvider fMessageProvider;
    private static final String[] fValueSpace;
    private RegularExpression fRegex;
    
    public BooleanDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public BooleanDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fLocale = null;
        this.fBaseValidator = null;
        this.fPattern = null;
        this.fFacetsDefined = 0;
        this.fMessageProvider = new DatatypeMessageProvider();
        this.fRegex = null;
        this.setBasetype(basetype);
        if (hashtable != null && !b) {
            final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (!s.equals("pattern")) {
                    throw new InvalidDatatypeFacetException("Only constraining facet in boolean datatype is PATTERN");
                }
                this.fFacetsDefined += 8;
                this.fPattern = hashtable.get(s);
                if (this.fPattern == null) {
                    continue;
                }
                this.fRegex = new RegularExpression(this.fPattern, "X");
            }
        }
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        this.checkContent(s);
        return null;
    }
    
    public int compare(final String s, final String s2) {
        return 0;
    }
    
    public Hashtable getFacets() {
        return null;
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
    
    private String getErrorString(final int n, final int n2, final Object[] array) {
        try {
            return this.fMessageProvider.createMessage(this.fLocale, n, n2, array);
        }
        catch (Exception ex) {
            return "Illegal Errorcode " + n2;
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("clone() is not supported in " + this.getClass().getName());
    }
    
    private void checkContent(final String s) throws InvalidDatatypeValueException {
        boolean b = false;
        for (int i = 0; i < BooleanDatatypeValidator.fValueSpace.length; ++i) {
            if (s.equals(BooleanDatatypeValidator.fValueSpace[i])) {
                b = true;
            }
        }
        if (!b) {
            throw new InvalidDatatypeValueException(this.getErrorString(2, 0, new Object[] { s }));
        }
        if ((this.fFacetsDefined & 0x8) != 0x0 && (this.fRegex == null || !this.fRegex.matches(s))) {
            throw new InvalidDatatypeValueException("Value'" + s + "does not match regular expression facet" + this.fPattern);
        }
    }
    
    static {
        fValueSpace = new String[] { "false", "true", "0", "1" };
    }
}
