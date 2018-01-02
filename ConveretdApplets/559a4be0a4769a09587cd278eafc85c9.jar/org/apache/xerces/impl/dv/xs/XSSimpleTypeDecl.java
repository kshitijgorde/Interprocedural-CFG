// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.xs.XSTypeDecl;
import org.apache.xerces.impl.xs.psvi.XSAnnotation;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.impl.xs.psvi.StringList;
import java.util.StringTokenizer;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.xpath.regex.RegularExpression;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.impl.xs.psvi.XSObject;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.impl.xs.psvi.XSObjectList;
import org.apache.xerces.impl.xs.psvi.XSSimpleTypeDefinition;
import org.apache.xerces.impl.dv.DatatypeException;
import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;
import java.util.Vector;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.dv.XSSimpleType;

public class XSSimpleTypeDecl implements XSSimpleType
{
    static final short DV_STRING = 1;
    static final short DV_BOOLEAN = 2;
    static final short DV_DECIMAL = 3;
    static final short DV_FLOAT = 4;
    static final short DV_DOUBLE = 5;
    static final short DV_DURATION = 6;
    static final short DV_DATETIME = 7;
    static final short DV_TIME = 8;
    static final short DV_DATE = 9;
    static final short DV_GYEARMONTH = 10;
    static final short DV_GYEAR = 11;
    static final short DV_GMONTHDAY = 12;
    static final short DV_GDAY = 13;
    static final short DV_GMONTH = 14;
    static final short DV_HEXBINARY = 15;
    static final short DV_BASE64BINARY = 16;
    static final short DV_ANYURI = 17;
    static final short DV_QNAME = 18;
    static final short DV_NOTATION = 19;
    static final short DV_ANYSIMPLETYPE = 0;
    static final short DV_ID = 20;
    static final short DV_IDREF = 21;
    static final short DV_ENTITY = 22;
    static final short DV_INTEGER = 23;
    static final short DV_LIST = 24;
    static final short DV_UNION = 25;
    static final TypeValidator[] fDVs;
    static final short NORMALIZE_NONE = 0;
    static final short NORMALIZE_TRIM = 1;
    static final short NORMALIZE_FULL = 2;
    static final short[] fDVNormalizeType;
    static final short SPECIAL_PATTERN_NONE = 0;
    static final short SPECIAL_PATTERN_NMTOKEN = 1;
    static final short SPECIAL_PATTERN_NAME = 2;
    static final short SPECIAL_PATTERN_NCNAME = 3;
    static final String[] SPECIAL_PATTERN_STRING;
    static final String[] WS_FACET_STRING;
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    static final String ANY_TYPE = "anyType";
    static final ValidationContext fEmptyContext;
    private boolean fIsImmutable;
    private XSSimpleTypeDecl fItemType;
    private XSSimpleTypeDecl[] fMemberTypes;
    private String fTypeName;
    private String fTargetNamespace;
    private short fFinalSet;
    private XSSimpleTypeDecl fBase;
    private short fVariety;
    private short fValidationDV;
    private short fFacetsDefined;
    private short fFixedFacet;
    private short fWhiteSpace;
    private int fLength;
    private int fMinLength;
    private int fMaxLength;
    private int fTotalDigits;
    private int fFractionDigits;
    private Vector fPattern;
    private Vector fPatternStr;
    private Vector fEnumeration;
    private Object fMaxInclusive;
    private Object fMaxExclusive;
    private Object fMinExclusive;
    private Object fMinInclusive;
    private short fPatternType;
    private short fOrdered;
    private boolean fFinite;
    private boolean fBounded;
    private boolean fNumeric;
    static final XSSimpleTypeDecl fAnySimpleType;
    static final ValidationContext fDummyContext;
    
    public XSSimpleTypeDecl() {
        this.fIsImmutable = false;
        this.fFinalSet = 0;
        this.fVariety = -1;
        this.fValidationDV = -1;
        this.fFacetsDefined = 0;
        this.fFixedFacet = 0;
        this.fWhiteSpace = 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fPatternType = 0;
    }
    
    protected XSSimpleTypeDecl(final XSSimpleTypeDecl base, final String name, final short validateDV, final short ordered, final boolean bounded, final boolean finite, final boolean numeric, final boolean isImmutable) {
        this.fIsImmutable = false;
        this.fFinalSet = 0;
        this.fVariety = -1;
        this.fValidationDV = -1;
        this.fFacetsDefined = 0;
        this.fFixedFacet = 0;
        this.fWhiteSpace = 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fPatternType = 0;
        this.fIsImmutable = isImmutable;
        this.fBase = base;
        this.fTypeName = name;
        this.fTargetNamespace = "http://www.w3.org/2001/XMLSchema";
        this.fVariety = 1;
        this.fValidationDV = validateDV;
        this.fFacetsDefined = 64;
        if (validateDV == 1) {
            this.fWhiteSpace = 0;
        }
        else {
            this.fWhiteSpace = 2;
            this.fFixedFacet = 64;
        }
        this.fOrdered = ordered;
        this.fBounded = bounded;
        this.fFinite = finite;
        this.fNumeric = numeric;
    }
    
    protected XSSimpleTypeDecl(final XSSimpleTypeDecl base, final String name, final String uri, final short finalSet, final boolean isImmutable) {
        this.fIsImmutable = false;
        this.fFinalSet = 0;
        this.fVariety = -1;
        this.fValidationDV = -1;
        this.fFacetsDefined = 0;
        this.fFixedFacet = 0;
        this.fWhiteSpace = 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fPatternType = 0;
        this.fBase = base;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fVariety = this.fBase.fVariety;
        this.fValidationDV = this.fBase.fValidationDV;
        switch (this.fVariety) {
            case 2: {
                this.fItemType = this.fBase.fItemType;
                break;
            }
            case 3: {
                this.fMemberTypes = this.fBase.fMemberTypes;
                break;
            }
        }
        this.fLength = this.fBase.fLength;
        this.fMinLength = this.fBase.fMinLength;
        this.fMaxLength = this.fBase.fMaxLength;
        this.fPattern = this.fBase.fPattern;
        this.fPatternStr = this.fBase.fPatternStr;
        this.fEnumeration = this.fBase.fEnumeration;
        this.fWhiteSpace = this.fBase.fWhiteSpace;
        this.fMaxExclusive = this.fBase.fMaxExclusive;
        this.fMaxInclusive = this.fBase.fMaxInclusive;
        this.fMinExclusive = this.fBase.fMinExclusive;
        this.fMinInclusive = this.fBase.fMinInclusive;
        this.fTotalDigits = this.fBase.fTotalDigits;
        this.fFractionDigits = this.fBase.fFractionDigits;
        this.fPatternType = this.fBase.fPatternType;
        this.fFixedFacet = this.fBase.fFixedFacet;
        this.fFacetsDefined = this.fBase.fFacetsDefined;
        this.caclFundamentalFacets();
        this.fIsImmutable = isImmutable;
    }
    
    protected XSSimpleTypeDecl(final String name, final String uri, final short finalSet, final XSSimpleTypeDecl itemType, final boolean isImmutable) {
        this.fIsImmutable = false;
        this.fFinalSet = 0;
        this.fVariety = -1;
        this.fValidationDV = -1;
        this.fFacetsDefined = 0;
        this.fFixedFacet = 0;
        this.fWhiteSpace = 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fPatternType = 0;
        this.fBase = XSSimpleTypeDecl.fAnySimpleType;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fVariety = 2;
        this.fItemType = itemType;
        this.fValidationDV = 24;
        this.fFacetsDefined = 64;
        this.fFixedFacet = 64;
        this.fWhiteSpace = 2;
        this.caclFundamentalFacets();
        this.fIsImmutable = isImmutable;
    }
    
    protected XSSimpleTypeDecl(final String name, final String uri, final short finalSet, final XSSimpleTypeDecl[] memberTypes) {
        this.fIsImmutable = false;
        this.fFinalSet = 0;
        this.fVariety = -1;
        this.fValidationDV = -1;
        this.fFacetsDefined = 0;
        this.fFixedFacet = 0;
        this.fWhiteSpace = 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fPatternType = 0;
        this.fBase = XSSimpleTypeDecl.fAnySimpleType;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fVariety = 3;
        this.fMemberTypes = memberTypes;
        this.fValidationDV = 25;
        this.fFacetsDefined = 64;
        this.fWhiteSpace = 2;
        this.caclFundamentalFacets();
        this.fIsImmutable = false;
    }
    
    protected XSSimpleTypeDecl setRestrictionValues(final XSSimpleTypeDecl base, final String name, final String uri, final short finalSet) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = base;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fVariety = this.fBase.fVariety;
        this.fValidationDV = this.fBase.fValidationDV;
        switch (this.fVariety) {
            case 2: {
                this.fItemType = this.fBase.fItemType;
                break;
            }
            case 3: {
                this.fMemberTypes = this.fBase.fMemberTypes;
                break;
            }
        }
        this.fLength = this.fBase.fLength;
        this.fMinLength = this.fBase.fMinLength;
        this.fMaxLength = this.fBase.fMaxLength;
        this.fPattern = this.fBase.fPattern;
        this.fPatternStr = this.fBase.fPatternStr;
        this.fEnumeration = this.fBase.fEnumeration;
        this.fWhiteSpace = this.fBase.fWhiteSpace;
        this.fMaxExclusive = this.fBase.fMaxExclusive;
        this.fMaxInclusive = this.fBase.fMaxInclusive;
        this.fMinExclusive = this.fBase.fMinExclusive;
        this.fMinInclusive = this.fBase.fMinInclusive;
        this.fTotalDigits = this.fBase.fTotalDigits;
        this.fFractionDigits = this.fBase.fFractionDigits;
        this.fPatternType = this.fBase.fPatternType;
        this.fFixedFacet = this.fBase.fFixedFacet;
        this.fFacetsDefined = this.fBase.fFacetsDefined;
        this.caclFundamentalFacets();
        return this;
    }
    
    protected XSSimpleTypeDecl setListValues(final String name, final String uri, final short finalSet, final XSSimpleTypeDecl itemType) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = XSSimpleTypeDecl.fAnySimpleType;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fVariety = 2;
        this.fItemType = itemType;
        this.fValidationDV = 24;
        this.fFacetsDefined = 64;
        this.fFixedFacet = 64;
        this.fWhiteSpace = 2;
        this.caclFundamentalFacets();
        return this;
    }
    
    protected XSSimpleTypeDecl setUnionValues(final String name, final String uri, final short finalSet, final XSSimpleTypeDecl[] memberTypes) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = XSSimpleTypeDecl.fAnySimpleType;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fVariety = 3;
        this.fMemberTypes = memberTypes;
        this.fValidationDV = 25;
        this.fFacetsDefined = 64;
        this.fWhiteSpace = 2;
        this.caclFundamentalFacets();
        return this;
    }
    
    public short getType() {
        return 3;
    }
    
    public short getTypeCategory() {
        return 14;
    }
    
    public String getName() {
        return this.fTypeName;
    }
    
    public String getNamespace() {
        return this.fTargetNamespace;
    }
    
    public short getFinal() {
        return this.fFinalSet;
    }
    
    public boolean getIsFinal(final short derivation) {
        return (this.fFinalSet & derivation) != 0x0;
    }
    
    public XSTypeDefinition getBaseType() {
        return this.fBase;
    }
    
    public boolean getIsAnonymous() {
        return this.fTypeName == null;
    }
    
    public short getVariety() {
        return (short)((this.fValidationDV == 0) ? 0 : this.fVariety);
    }
    
    public boolean isIDType() {
        switch (this.fVariety) {
            case 1: {
                return this.fValidationDV == 20;
            }
            case 2: {
                return this.fItemType.isIDType();
            }
            case 3: {
                for (int i = 0; i < this.fMemberTypes.length; ++i) {
                    if (this.fMemberTypes[i].isIDType()) {
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }
    
    public short getWhitespace() throws DatatypeException {
        if (this.fVariety == 3) {
            throw new DatatypeException("dt-whitespace", new Object[] { this.fTypeName });
        }
        return this.fWhiteSpace;
    }
    
    public short getPrimitiveKind() {
        if (this.fVariety != 1 || this.fValidationDV == 0) {
            return 0;
        }
        if (this.fValidationDV == 20 || this.fValidationDV == 21 || this.fValidationDV == 22) {
            return 1;
        }
        if (this.fValidationDV == 23) {
            return 3;
        }
        return this.fValidationDV;
    }
    
    public XSSimpleTypeDefinition getPrimitiveType() {
        if (this.fVariety == 1 && this.fValidationDV != 0) {
            XSSimpleTypeDecl pri;
            for (pri = this; pri.fBase != XSSimpleTypeDecl.fAnySimpleType; pri = pri.fBase) {}
            return pri;
        }
        return null;
    }
    
    public XSSimpleTypeDefinition getItemType() {
        if (this.fVariety == 2) {
            return this.fItemType;
        }
        return null;
    }
    
    public XSObjectList getMemberTypes() {
        if (this.fVariety == 3) {
            return new XSObjectListImpl(this.fMemberTypes, this.fMemberTypes.length);
        }
        return null;
    }
    
    public void applyFacets(final XSFacets facets, final short presentFacet, final short fixedFacet, final ValidationContext context) throws InvalidDatatypeFacetException {
        this.applyFacets(facets, presentFacet, fixedFacet, (short)0, context);
    }
    
    void applyFacets1(final XSFacets facets, final short presentFacet, final short fixedFacet) {
        try {
            this.applyFacets(facets, presentFacet, fixedFacet, (short)0, XSSimpleTypeDecl.fDummyContext);
        }
        catch (InvalidDatatypeFacetException e) {
            throw new RuntimeException("internal error");
        }
        this.fIsImmutable = true;
    }
    
    void applyFacets1(final XSFacets facets, final short presentFacet, final short fixedFacet, final short patternType) {
        try {
            this.applyFacets(facets, presentFacet, fixedFacet, patternType, XSSimpleTypeDecl.fDummyContext);
        }
        catch (InvalidDatatypeFacetException e) {
            throw new RuntimeException("internal error");
        }
        this.fIsImmutable = true;
    }
    
    void applyFacets(final XSFacets facets, final short presentFacet, final short fixedFacet, final short patternType, final ValidationContext context) throws InvalidDatatypeFacetException {
        if (this.fIsImmutable) {
            return;
        }
        final ValidatedInfo tempInfo = new ValidatedInfo();
        this.fFacetsDefined = 0;
        this.fFixedFacet = 0;
        int result = 0;
        final short allowedFacet = XSSimpleTypeDecl.fDVs[this.fValidationDV].getAllowedFacets();
        if ((presentFacet & 0x2) != 0x0) {
            if ((allowedFacet & 0x2) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "length" });
            }
            else {
                this.fLength = facets.length;
                this.fFacetsDefined |= 0x2;
                if ((fixedFacet & 0x2) != 0x0) {
                    this.fFixedFacet |= 0x2;
                }
            }
        }
        if ((presentFacet & 0x4) != 0x0) {
            if ((allowedFacet & 0x4) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "minLength" });
            }
            else {
                this.fMinLength = facets.minLength;
                this.fFacetsDefined |= 0x4;
                if ((fixedFacet & 0x4) != 0x0) {
                    this.fFixedFacet |= 0x4;
                }
            }
        }
        if ((presentFacet & 0x8) != 0x0) {
            if ((allowedFacet & 0x8) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "maxLength" });
            }
            else {
                this.fMaxLength = facets.maxLength;
                this.fFacetsDefined |= 0x8;
                if ((fixedFacet & 0x8) != 0x0) {
                    this.fFixedFacet |= 0x8;
                }
            }
        }
        if ((presentFacet & 0x10) != 0x0) {
            if ((allowedFacet & 0x10) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "pattern" });
            }
            else {
                RegularExpression regex = null;
                try {
                    regex = new RegularExpression(facets.pattern, "X");
                }
                catch (Exception e) {
                    this.reportError("InvalidRegex", new Object[] { facets.pattern, e.getLocalizedMessage() });
                }
                if (regex != null) {
                    (this.fPattern = new Vector()).addElement(regex);
                    (this.fPatternStr = new Vector()).addElement(facets.pattern);
                    this.fFacetsDefined |= 0x10;
                    if ((fixedFacet & 0x10) != 0x0) {
                        this.fFixedFacet |= 0x10;
                    }
                }
            }
        }
        if ((presentFacet & 0x20) != 0x0) {
            if ((allowedFacet & 0x20) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "enumeration" });
            }
            else {
                this.fEnumeration = new Vector();
                final Vector enumVals = facets.enumeration;
                final Vector enumNSDecls = facets.enumNSDecls;
                final ValidationContextImpl ctx = new ValidationContextImpl(context);
                for (int i = 0; i < enumVals.size(); ++i) {
                    if (enumNSDecls != null) {
                        ctx.setNSContext(enumNSDecls.elementAt(i));
                    }
                    try {
                        this.fEnumeration.addElement(this.fBase.validate(enumVals.elementAt(i), ctx, tempInfo));
                    }
                    catch (InvalidDatatypeValueException ide) {
                        this.reportError("enumeration-valid-restriction", new Object[] { enumVals.elementAt(i) });
                    }
                }
                this.fFacetsDefined |= 0x20;
                if ((fixedFacet & 0x20) != 0x0) {
                    this.fFixedFacet |= 0x20;
                }
            }
        }
        if ((presentFacet & 0x40) != 0x0) {
            if ((allowedFacet & 0x40) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "whiteSpace" });
            }
            else {
                this.fWhiteSpace = facets.whiteSpace;
                this.fFacetsDefined |= 0x40;
                if ((fixedFacet & 0x40) != 0x0) {
                    this.fFixedFacet |= 0x40;
                }
            }
        }
        boolean needCheckBase = true;
        if ((presentFacet & 0x80) != 0x0) {
            if ((allowedFacet & 0x80) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "maxInclusive" });
            }
            else {
                try {
                    this.fMaxInclusive = this.getActualValue(facets.maxInclusive, context, tempInfo, true);
                    this.fFacetsDefined |= 0x80;
                    if ((fixedFacet & 0x80) != 0x0) {
                        this.fFixedFacet |= 0x80;
                    }
                }
                catch (InvalidDatatypeValueException ide2) {
                    this.reportError("FacetValueFromBase", new Object[] { facets.maxInclusive, "maxInclusive" });
                }
                if ((this.fBase.fFacetsDefined & 0x80) != 0x0) {
                    result = XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(this.fMaxInclusive, this.fBase.fMaxInclusive);
                    if ((this.fBase.fFixedFacet & 0x80) != 0x0 && result != 0) {
                        this.reportError("FixedFacetValue", new Object[] { "maxInclusive", this.fMaxInclusive, this.fBase.fMaxInclusive });
                    }
                    if (result == 0) {
                        needCheckBase = false;
                    }
                }
                if (needCheckBase) {
                    try {
                        this.fBase.validate(context, tempInfo);
                    }
                    catch (InvalidDatatypeValueException ide2) {
                        this.reportError("FacetValueFromBase", new Object[] { facets.maxInclusive, "maxInclusive" });
                    }
                }
            }
        }
        needCheckBase = true;
        if ((presentFacet & 0x100) != 0x0) {
            if ((allowedFacet & 0x100) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "maxExclusive" });
            }
            else {
                try {
                    this.fMaxExclusive = this.getActualValue(facets.maxExclusive, context, tempInfo, true);
                    this.fFacetsDefined |= 0x100;
                    if ((fixedFacet & 0x100) != 0x0) {
                        this.fFixedFacet |= 0x100;
                    }
                }
                catch (InvalidDatatypeValueException ide2) {
                    this.reportError("FacetValueFromBase", new Object[] { facets.maxExclusive, "maxExclusive" });
                }
                if ((this.fBase.fFacetsDefined & 0x100) != 0x0) {
                    result = XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(this.fMaxExclusive, this.fBase.fMaxExclusive);
                    if ((this.fBase.fFixedFacet & 0x100) != 0x0 && result != 0) {
                        this.reportError("FixedFacetValue", new Object[] { "maxExclusive", facets.maxExclusive, this.fBase.fMaxExclusive });
                    }
                    if (result == 0) {
                        needCheckBase = false;
                    }
                }
                if (needCheckBase) {
                    try {
                        this.fBase.validate(context, tempInfo);
                    }
                    catch (InvalidDatatypeValueException ide2) {
                        this.reportError("FacetValueFromBase", new Object[] { facets.maxExclusive, "maxExclusive" });
                    }
                }
            }
        }
        needCheckBase = true;
        if ((presentFacet & 0x200) != 0x0) {
            if ((allowedFacet & 0x200) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "minExclusive" });
            }
            else {
                try {
                    this.fMinExclusive = this.getActualValue(facets.minExclusive, context, tempInfo, true);
                    this.fFacetsDefined |= 0x200;
                    if ((fixedFacet & 0x200) != 0x0) {
                        this.fFixedFacet |= 0x200;
                    }
                }
                catch (InvalidDatatypeValueException ide2) {
                    this.reportError("FacetValueFromBase", new Object[] { facets.minExclusive, "minExclusive" });
                }
                if ((this.fBase.fFacetsDefined & 0x200) != 0x0) {
                    result = XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fBase.fMinExclusive);
                    if ((this.fBase.fFixedFacet & 0x200) != 0x0 && result != 0) {
                        this.reportError("FixedFacetValue", new Object[] { "minExclusive", facets.minExclusive, this.fBase.fMinExclusive });
                    }
                    if (result == 0) {
                        needCheckBase = false;
                    }
                }
                if (needCheckBase) {
                    try {
                        this.fBase.validate(context, tempInfo);
                    }
                    catch (InvalidDatatypeValueException ide2) {
                        this.reportError("FacetValueFromBase", new Object[] { facets.minExclusive, "minExclusive" });
                    }
                }
            }
        }
        needCheckBase = true;
        if ((presentFacet & 0x400) != 0x0) {
            if ((allowedFacet & 0x400) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "minInclusive" });
            }
            else {
                try {
                    this.fMinInclusive = this.getActualValue(facets.minInclusive, context, tempInfo, true);
                    this.fFacetsDefined |= 0x400;
                    if ((fixedFacet & 0x400) != 0x0) {
                        this.fFixedFacet |= 0x400;
                    }
                }
                catch (InvalidDatatypeValueException ide2) {
                    this.reportError("FacetValueFromBase", new Object[] { facets.minInclusive, "minInclusive" });
                }
                if ((this.fBase.fFacetsDefined & 0x400) != 0x0) {
                    result = XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fBase.fMinInclusive);
                    if ((this.fBase.fFixedFacet & 0x400) != 0x0 && result != 0) {
                        this.reportError("FixedFacetValue", new Object[] { "minInclusive", facets.minInclusive, this.fBase.fMinInclusive });
                    }
                    if (result == 0) {
                        needCheckBase = false;
                    }
                }
                if (needCheckBase) {
                    try {
                        this.fBase.validate(context, tempInfo);
                    }
                    catch (InvalidDatatypeValueException ide2) {
                        this.reportError("FacetValueFromBase", new Object[] { facets.minInclusive, "minInclusive" });
                    }
                }
            }
        }
        if ((presentFacet & 0x800) != 0x0) {
            if ((allowedFacet & 0x800) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "totalDigits" });
            }
            else {
                this.fTotalDigits = facets.totalDigits;
                this.fFacetsDefined |= 0x800;
                if ((fixedFacet & 0x800) != 0x0) {
                    this.fFixedFacet |= 0x800;
                }
            }
        }
        if ((presentFacet & 0x1000) != 0x0) {
            if ((allowedFacet & 0x1000) == 0x0) {
                this.reportError("cos-applicable-facets", new Object[] { "fractionDigits" });
            }
            else {
                this.fFractionDigits = facets.fractionDigits;
                this.fFacetsDefined |= 0x1000;
                if ((fixedFacet & 0x1000) != 0x0) {
                    this.fFixedFacet |= 0x1000;
                }
            }
        }
        if (patternType != 0) {
            this.fPatternType = patternType;
        }
        if (this.fFacetsDefined != 0) {
            if ((this.fFacetsDefined & 0x2) != 0x0 && ((this.fFacetsDefined & 0x4) != 0x0 || (this.fFacetsDefined & 0x8) != 0x0)) {
                this.reportError("length-minLength-maxLength", null);
            }
            if ((this.fFacetsDefined & 0x4) != 0x0 && (this.fFacetsDefined & 0x8) != 0x0 && this.fMinLength > this.fMaxLength) {
                this.reportError("minLength-less-than-equal-to-maxLength", new Object[] { Integer.toString(this.fMinLength), Integer.toString(this.fMaxLength) });
            }
            if ((this.fFacetsDefined & 0x100) != 0x0 && (this.fFacetsDefined & 0x80) != 0x0) {
                this.reportError("maxInclusive-maxExclusive", null);
            }
            if ((this.fFacetsDefined & 0x200) != 0x0 && (this.fFacetsDefined & 0x400) != 0x0) {
                this.reportError("minInclusive-minExclusive", null);
            }
            if ((this.fFacetsDefined & 0x80) != 0x0 && (this.fFacetsDefined & 0x400) != 0x0) {
                result = XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fMaxInclusive);
                if (result != -1 && result != 0) {
                    this.reportError("minInclusive-less-than-equal-to-maxInclusive", new Object[] { this.fMinInclusive, this.fMaxInclusive });
                }
            }
            if ((this.fFacetsDefined & 0x100) != 0x0 && (this.fFacetsDefined & 0x200) != 0x0) {
                result = XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fMaxExclusive);
                if (result != -1 && result != 0) {
                    this.reportError("minExclusive-less-than-equal-to-maxExclusive", new Object[] { this.fMinExclusive, this.fMaxExclusive });
                }
            }
            if ((this.fFacetsDefined & 0x80) != 0x0 && (this.fFacetsDefined & 0x200) != 0x0 && XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fMaxInclusive) != -1) {
                this.reportError("minExclusive-less-than-maxInclusive", new Object[] { this.fMinExclusive, this.fMaxInclusive });
            }
            if ((this.fFacetsDefined & 0x100) != 0x0 && (this.fFacetsDefined & 0x400) != 0x0 && XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fMaxExclusive) != -1) {
                this.reportError("minInclusive-less-than-maxExclusive", new Object[] { this.fMinInclusive, this.fMaxExclusive });
            }
            if ((this.fFacetsDefined & 0x1000) != 0x0 && (this.fFacetsDefined & 0x800) != 0x0 && this.fFractionDigits > this.fTotalDigits) {
                this.reportError("fractionDigits-totalDigits", new Object[] { Integer.toString(this.fFractionDigits), Integer.toString(this.fTotalDigits) });
            }
            if ((this.fFacetsDefined & 0x2) != 0x0) {
                if ((this.fBase.fFacetsDefined & 0x8) != 0x0 || (this.fBase.fFacetsDefined & 0x4) != 0x0) {
                    this.reportError("length-minLength-maxLength", null);
                }
                else if ((this.fBase.fFacetsDefined & 0x2) != 0x0 && this.fLength != this.fBase.fLength) {
                    this.reportError("length-valid-restriction", new Object[] { Integer.toString(this.fLength), Integer.toString(this.fBase.fLength) });
                }
            }
            if ((this.fBase.fFacetsDefined & 0x2) != 0x0 && ((this.fFacetsDefined & 0x8) != 0x0 || (this.fFacetsDefined & 0x4) != 0x0)) {
                this.reportError("length-minLength-maxLength", null);
            }
            if ((this.fFacetsDefined & 0x4) != 0x0) {
                if ((this.fBase.fFacetsDefined & 0x8) != 0x0) {
                    if (this.fMinLength > this.fBase.fMaxLength) {
                        this.reportError("minLength-less-than-equal-to-maxLength", new Object[] { Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMaxLength) });
                    }
                }
                else if ((this.fBase.fFacetsDefined & 0x4) != 0x0) {
                    if ((this.fBase.fFixedFacet & 0x4) != 0x0 && this.fMinLength != this.fBase.fMinLength) {
                        this.reportError("FixedFacetValue", new Object[] { "minLength", Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMinLength) });
                    }
                    if (this.fMinLength < this.fBase.fMinLength) {
                        this.reportError("minLength-valid-restriction", new Object[] { Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMinLength) });
                    }
                }
            }
            if ((this.fFacetsDefined & 0x8) != 0x0 && (this.fBase.fFacetsDefined & 0x4) != 0x0 && this.fMaxLength < this.fBase.fMinLength) {
                this.reportError("minLength-less-than-equal-to-maxLength", new Object[] { Integer.toString(this.fBase.fMinLength), Integer.toString(this.fMaxLength) });
            }
            if ((this.fFacetsDefined & 0x8) != 0x0 && (this.fBase.fFacetsDefined & 0x8) != 0x0) {
                if ((this.fBase.fFixedFacet & 0x8) != 0x0 && this.fMaxLength != this.fBase.fMaxLength) {
                    this.reportError("FixedFacetValue", new Object[] { "maxLength", Integer.toString(this.fMaxLength), Integer.toString(this.fBase.fMaxLength) });
                }
                if (this.fMaxLength > this.fBase.fMaxLength) {
                    this.reportError("maxLength-valid-restriction", new Object[] { Integer.toString(this.fMaxLength), Integer.toString(this.fBase.fMaxLength) });
                }
            }
            if ((this.fFacetsDefined & 0x800) != 0x0 && (this.fBase.fFacetsDefined & 0x800) != 0x0) {
                if ((this.fBase.fFixedFacet & 0x800) != 0x0 && this.fTotalDigits != this.fBase.fTotalDigits) {
                    this.reportError("FixedFacetValue", new Object[] { "totalDigits", Integer.toString(this.fTotalDigits), Integer.toString(this.fBase.fTotalDigits) });
                }
                if (this.fTotalDigits > this.fBase.fTotalDigits) {
                    this.reportError("totalDigits-valid-restriction", new Object[] { Integer.toString(this.fTotalDigits), Integer.toString(this.fBase.fTotalDigits) });
                }
            }
            if ((this.fFacetsDefined & 0x1000) != 0x0 && (this.fBase.fFacetsDefined & 0x1000) != 0x0 && (this.fBase.fFixedFacet & 0x1000) != 0x0 && this.fFractionDigits != this.fBase.fFractionDigits) {
                this.reportError("FixedFacetValue", new Object[] { "fractionDigits", Integer.toString(this.fFractionDigits), Integer.toString(this.fBase.fFractionDigits) });
            }
            if ((this.fFacetsDefined & 0x40) != 0x0 && (this.fBase.fFacetsDefined & 0x40) != 0x0) {
                if ((this.fBase.fFixedFacet & 0x40) != 0x0 && this.fWhiteSpace != this.fBase.fWhiteSpace) {
                    this.reportError("FixedFacetValue", new Object[] { "whiteSpace", this.whiteSpaceValue(this.fWhiteSpace), this.whiteSpaceValue(this.fBase.fWhiteSpace) });
                }
                if ((this.fWhiteSpace == 0 || this.fWhiteSpace == 1) && this.fBase.fWhiteSpace == 2) {
                    this.reportError("whiteSpace-valid-restriction.1", null);
                }
                if (this.fWhiteSpace == 0 && this.fBase.fWhiteSpace == 1) {
                    this.reportError("whiteSpace-valid-restriction.2", null);
                }
            }
        }
        if ((this.fFacetsDefined & 0x2) == 0x0 && (this.fBase.fFacetsDefined & 0x2) != 0x0) {
            this.fFacetsDefined |= 0x2;
            this.fLength = this.fBase.fLength;
        }
        if ((this.fFacetsDefined & 0x4) == 0x0 && (this.fBase.fFacetsDefined & 0x4) != 0x0) {
            this.fFacetsDefined |= 0x4;
            this.fMinLength = this.fBase.fMinLength;
        }
        if ((this.fFacetsDefined & 0x8) == 0x0 && (this.fBase.fFacetsDefined & 0x8) != 0x0) {
            this.fFacetsDefined |= 0x8;
            this.fMaxLength = this.fBase.fMaxLength;
        }
        if ((this.fBase.fFacetsDefined & 0x10) != 0x0) {
            if ((this.fFacetsDefined & 0x10) == 0x0) {
                this.fPattern = this.fBase.fPattern;
                this.fPatternStr = this.fBase.fPatternStr;
                this.fFacetsDefined |= 0x10;
            }
            else {
                for (int j = this.fBase.fPattern.size() - 1; j >= 0; --j) {
                    this.fPattern.addElement(this.fBase.fPattern.elementAt(j));
                    this.fPatternStr.addElement(this.fBase.fPatternStr.elementAt(j));
                }
            }
        }
        if ((this.fFacetsDefined & 0x40) == 0x0 && (this.fBase.fFacetsDefined & 0x40) != 0x0) {
            this.fFacetsDefined |= 0x40;
            this.fWhiteSpace = this.fBase.fWhiteSpace;
        }
        if ((this.fFacetsDefined & 0x20) == 0x0 && (this.fBase.fFacetsDefined & 0x20) != 0x0) {
            this.fFacetsDefined |= 0x20;
            this.fEnumeration = this.fBase.fEnumeration;
        }
        if ((this.fBase.fFacetsDefined & 0x100) != 0x0 && (this.fFacetsDefined & 0x100) == 0x0 && (this.fFacetsDefined & 0x80) == 0x0) {
            this.fFacetsDefined |= 0x100;
            this.fMaxExclusive = this.fBase.fMaxExclusive;
        }
        if ((this.fBase.fFacetsDefined & 0x80) != 0x0 && (this.fFacetsDefined & 0x100) == 0x0 && (this.fFacetsDefined & 0x80) == 0x0) {
            this.fFacetsDefined |= 0x80;
            this.fMaxInclusive = this.fBase.fMaxInclusive;
        }
        if ((this.fBase.fFacetsDefined & 0x200) != 0x0 && (this.fFacetsDefined & 0x200) == 0x0 && (this.fFacetsDefined & 0x400) == 0x0) {
            this.fFacetsDefined |= 0x200;
            this.fMinExclusive = this.fBase.fMinExclusive;
        }
        if ((this.fBase.fFacetsDefined & 0x400) != 0x0 && (this.fFacetsDefined & 0x200) == 0x0 && (this.fFacetsDefined & 0x400) == 0x0) {
            this.fFacetsDefined |= 0x400;
            this.fMinInclusive = this.fBase.fMinInclusive;
        }
        if ((this.fBase.fFacetsDefined & 0x800) != 0x0 && (this.fFacetsDefined & 0x800) == 0x0) {
            this.fFacetsDefined |= 0x800;
            this.fTotalDigits = this.fBase.fTotalDigits;
        }
        if ((this.fBase.fFacetsDefined & 0x1000) != 0x0 && (this.fFacetsDefined & 0x1000) == 0x0) {
            this.fFacetsDefined |= 0x1000;
            this.fFractionDigits = this.fBase.fFractionDigits;
        }
        if (this.fPatternType == 0 && this.fBase.fPatternType != 0) {
            this.fPatternType = this.fBase.fPatternType;
        }
        this.fFixedFacet |= this.fBase.fFixedFacet;
        this.caclFundamentalFacets();
    }
    
    public Object validate(final String content, ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = XSSimpleTypeDecl.fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        }
        final boolean needNormalize = context == null || context.needToNormalize();
        final Object ob = this.getActualValue(content, context, validatedInfo, needNormalize);
        this.validate(context, validatedInfo);
        return ob;
    }
    
    public Object validate(final Object content, ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = XSSimpleTypeDecl.fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        }
        final boolean needNormalize = context == null || context.needToNormalize();
        final Object ob = this.getActualValue(content, context, validatedInfo, needNormalize);
        this.validate(context, validatedInfo);
        return ob;
    }
    
    public void validate(ValidationContext context, final ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = XSSimpleTypeDecl.fEmptyContext;
        }
        if (context.needFacetChecking() && this.fFacetsDefined != 0 && this.fFacetsDefined != 64) {
            this.checkFacets(validatedInfo);
        }
        if (context.needExtraChecking()) {
            this.checkExtraRules(context, validatedInfo);
        }
    }
    
    private void checkFacets(final ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        final Object ob = validatedInfo.actualValue;
        final String content = validatedInfo.normalizedValue;
        final int length = XSSimpleTypeDecl.fDVs[this.fValidationDV].getDataLength(ob);
        if ((this.fFacetsDefined & 0x8) != 0x0 && length > this.fMaxLength) {
            throw new InvalidDatatypeValueException("cvc-maxLength-valid", new Object[] { content, Integer.toString(length), Integer.toString(this.fMaxLength) });
        }
        if ((this.fFacetsDefined & 0x4) != 0x0 && length < this.fMinLength) {
            throw new InvalidDatatypeValueException("cvc-minLength-valid", new Object[] { content, Integer.toString(length), Integer.toString(this.fMinLength) });
        }
        if ((this.fFacetsDefined & 0x2) != 0x0 && length != this.fLength) {
            throw new InvalidDatatypeValueException("cvc-length-valid", new Object[] { content, Integer.toString(length), Integer.toString(this.fLength) });
        }
        if ((this.fFacetsDefined & 0x20) != 0x0) {
            boolean present = false;
            for (int i = 0; i < this.fEnumeration.size(); ++i) {
                if (this.fEnumeration.elementAt(i).equals(ob)) {
                    present = true;
                    break;
                }
            }
            if (!present) {
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[] { content, this.fEnumeration.toString() });
            }
        }
        if ((this.fFacetsDefined & 0x1000) != 0x0) {
            final int scale = XSSimpleTypeDecl.fDVs[this.fValidationDV].getFractionDigits(ob);
            if (scale > this.fFractionDigits) {
                throw new InvalidDatatypeValueException("cvc-fractionDigits-valid", new Object[] { content, Integer.toString(scale), Integer.toString(this.fFractionDigits) });
            }
        }
        if ((this.fFacetsDefined & 0x800) != 0x0) {
            final int totalDigits = XSSimpleTypeDecl.fDVs[this.fValidationDV].getTotalDigits(ob);
            if (totalDigits > this.fTotalDigits) {
                throw new InvalidDatatypeValueException("cvc-totalDigits-valid", new Object[] { content, Integer.toString(totalDigits), Integer.toString(this.fTotalDigits) });
            }
        }
        if ((this.fFacetsDefined & 0x80) != 0x0) {
            final int compare = XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(ob, this.fMaxInclusive);
            if (compare != -1 && compare != 0) {
                throw new InvalidDatatypeValueException("cvc-maxInclusive-valid", new Object[] { content, this.fMaxInclusive });
            }
        }
        if ((this.fFacetsDefined & 0x100) != 0x0) {
            final int compare = XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(ob, this.fMaxExclusive);
            if (compare != -1) {
                throw new InvalidDatatypeValueException("cvc-maxExclusive-valid", new Object[] { content, this.fMaxExclusive });
            }
        }
        if ((this.fFacetsDefined & 0x400) != 0x0) {
            final int compare = XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(ob, this.fMinInclusive);
            if (compare != 1 && compare != 0) {
                throw new InvalidDatatypeValueException("cvc-minInclusive-valid", new Object[] { content, this.fMinInclusive });
            }
        }
        if ((this.fFacetsDefined & 0x200) != 0x0) {
            final int compare = XSSimpleTypeDecl.fDVs[this.fValidationDV].compare(ob, this.fMinExclusive);
            if (compare != 1) {
                throw new InvalidDatatypeValueException("cvc-minExclusive-valid", new Object[] { content, this.fMinExclusive });
            }
        }
    }
    
    private void checkExtraRules(final ValidationContext context, final ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        final Object ob = validatedInfo.actualValue;
        if (this.fVariety == 1) {
            XSSimpleTypeDecl.fDVs[this.fValidationDV].checkExtraRules(ob, context);
        }
        else if (this.fVariety == 2) {
            final ListData values = (ListData)ob;
            final int len = values.length();
            if (this.fItemType.fVariety == 3) {
                final XSSimpleTypeDecl[] memberTypes = (XSSimpleTypeDecl[])validatedInfo.memberTypes;
                final XSSimpleType memberType = validatedInfo.memberType;
                for (int i = len - 1; i >= 0; --i) {
                    validatedInfo.actualValue = values.item(i);
                    validatedInfo.memberType = memberTypes[i];
                    this.fItemType.checkExtraRules(context, validatedInfo);
                }
                validatedInfo.memberType = memberType;
            }
            else {
                for (int j = len - 1; j >= 0; --j) {
                    validatedInfo.actualValue = values.item(j);
                    this.fItemType.checkExtraRules(context, validatedInfo);
                }
            }
            validatedInfo.actualValue = values;
        }
        else {
            ((XSSimpleTypeDecl)validatedInfo.memberType).checkExtraRules(context, validatedInfo);
        }
    }
    
    private Object getActualValue(final Object content, final ValidationContext context, final ValidatedInfo validatedInfo, final boolean needNormalize) throws InvalidDatatypeValueException {
        String nvalue;
        if (needNormalize) {
            nvalue = this.normalize(content, this.fWhiteSpace);
        }
        else {
            nvalue = content.toString();
        }
        if ((this.fFacetsDefined & 0x10) != 0x0) {
            for (int idx = this.fPattern.size() - 1; idx >= 0; --idx) {
                final RegularExpression regex = this.fPattern.elementAt(idx);
                if (!regex.matches(nvalue)) {
                    throw new InvalidDatatypeValueException("cvc-pattern-valid", new Object[] { content, this.fPatternStr.elementAt(idx) });
                }
            }
        }
        if (this.fVariety == 1) {
            if (this.fPatternType != 0) {
                boolean seenErr = false;
                if (this.fPatternType == 1) {
                    seenErr = !XMLChar.isValidNmtoken(nvalue);
                }
                else if (this.fPatternType == 2) {
                    seenErr = !XMLChar.isValidName(nvalue);
                }
                else if (this.fPatternType == 3) {
                    seenErr = !XMLChar.isValidNCName(nvalue);
                }
                if (seenErr) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { nvalue, XSSimpleTypeDecl.SPECIAL_PATTERN_STRING[this.fPatternType] });
                }
            }
            validatedInfo.normalizedValue = nvalue;
            final Object avalue = XSSimpleTypeDecl.fDVs[this.fValidationDV].getActualValue(nvalue, context);
            return validatedInfo.actualValue = avalue;
        }
        if (this.fVariety == 2) {
            final StringTokenizer parsedList = new StringTokenizer(nvalue, " ");
            final int countOfTokens = parsedList.countTokens();
            final Object[] avalue2 = new Object[countOfTokens];
            final XSSimpleTypeDecl[] memberTypes = new XSSimpleTypeDecl[countOfTokens];
            for (int i = 0; i < countOfTokens; ++i) {
                avalue2[i] = this.fItemType.getActualValue(parsedList.nextToken(), context, validatedInfo, false);
                if (context.needFacetChecking() && this.fItemType.fFacetsDefined != 0 && this.fItemType.fFacetsDefined != 64) {
                    this.fItemType.checkFacets(validatedInfo);
                }
                memberTypes[i] = (XSSimpleTypeDecl)validatedInfo.memberType;
            }
            final ListData v = new ListData(avalue2);
            validatedInfo.actualValue = v;
            validatedInfo.memberType = null;
            validatedInfo.memberTypes = memberTypes;
            validatedInfo.normalizedValue = nvalue;
            return v;
        }
        int j = 0;
        while (j < this.fMemberTypes.length) {
            try {
                final Object actualValue = this.fMemberTypes[j].getActualValue(content, context, validatedInfo, true);
                if (context.needFacetChecking() && this.fMemberTypes[j].fFacetsDefined != 0 && this.fMemberTypes[j].fFacetsDefined != 64) {
                    this.fMemberTypes[j].checkFacets(validatedInfo);
                }
                validatedInfo.memberType = this.fMemberTypes[j];
                return actualValue;
            }
            catch (InvalidDatatypeValueException invalidValue) {
                ++j;
            }
        }
        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.2", new Object[] { content, this.fTypeName });
    }
    
    public boolean isEqual(final Object value1, final Object value2) {
        return value1 != null && value1.equals(value2);
    }
    
    public static String normalize(final String content, final short ws) {
        final int len = (content == null) ? 0 : content.length();
        if (len == 0 || ws == 0) {
            return content;
        }
        final StringBuffer sb = new StringBuffer();
        if (ws == 1) {
            for (int i = 0; i < len; ++i) {
                final char ch = content.charAt(i);
                if (ch != '\t' && ch != '\n' && ch != '\r') {
                    sb.append(ch);
                }
                else {
                    sb.append(' ');
                }
            }
        }
        else {
            boolean isLeading = true;
            for (int i = 0; i < len; ++i) {
                char ch = content.charAt(i);
                if (ch != '\t' && ch != '\n' && ch != '\r' && ch != ' ') {
                    sb.append(ch);
                    isLeading = false;
                }
                else {
                    while (i < len - 1) {
                        ch = content.charAt(i + 1);
                        if (ch != '\t' && ch != '\n' && ch != '\r' && ch != ' ') {
                            break;
                        }
                        ++i;
                    }
                    if (i < len - 1 && !isLeading) {
                        sb.append(' ');
                    }
                }
            }
        }
        return sb.toString();
    }
    
    protected String normalize(final Object content, final short ws) {
        if (content == null) {
            return null;
        }
        if ((this.fFacetsDefined & 0x10) == 0x0) {
            final short norm_type = XSSimpleTypeDecl.fDVNormalizeType[this.fValidationDV];
            if (norm_type == 0) {
                return content.toString();
            }
            if (norm_type == 1) {
                return content.toString().trim();
            }
        }
        if (!(content instanceof StringBuffer)) {
            final String strContent = content.toString();
            return normalize(strContent, ws);
        }
        final StringBuffer sb = (StringBuffer)content;
        final int len = sb.length();
        if (len == 0) {
            return "";
        }
        if (ws == 0) {
            return sb.toString();
        }
        if (ws == 1) {
            for (int i = 0; i < len; ++i) {
                final char ch = sb.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == '\r') {
                    sb.setCharAt(i, ' ');
                }
            }
        }
        else {
            int j = 0;
            boolean isLeading = true;
            for (int i = 0; i < len; ++i) {
                char ch = sb.charAt(i);
                if (ch != '\t' && ch != '\n' && ch != '\r' && ch != ' ') {
                    sb.setCharAt(j++, ch);
                    isLeading = false;
                }
                else {
                    while (i < len - 1) {
                        ch = sb.charAt(i + 1);
                        if (ch != '\t' && ch != '\n' && ch != '\r' && ch != ' ') {
                            break;
                        }
                        ++i;
                    }
                    if (i < len - 1 && !isLeading) {
                        sb.setCharAt(j++, ' ');
                    }
                }
            }
            sb.setLength(j);
        }
        return sb.toString();
    }
    
    void reportError(final String key, final Object[] args) throws InvalidDatatypeFacetException {
        throw new InvalidDatatypeFacetException(key, args);
    }
    
    private String whiteSpaceValue(final short ws) {
        return XSSimpleTypeDecl.WS_FACET_STRING[ws];
    }
    
    public short getOrdered() {
        return this.fOrdered;
    }
    
    public boolean getIsBounded() {
        return this.fBounded;
    }
    
    public boolean getIsFinite() {
        return this.fFinite;
    }
    
    public boolean getIsNumeric() {
        return this.fNumeric;
    }
    
    public boolean getIsDefinedFacet(final short facetName) {
        return (this.fFacetsDefined & facetName) != 0x0;
    }
    
    public short getDefinedFacets() {
        return this.fFacetsDefined;
    }
    
    public boolean getIsFixedFacet(final short facetName) {
        return (this.fFixedFacet & facetName) != 0x0;
    }
    
    public short getFixedFacets() {
        return this.fFixedFacet;
    }
    
    public String getLexicalFacetValue(final short facetName) {
        switch (facetName) {
            case 2: {
                return Integer.toString(this.fLength);
            }
            case 4: {
                return Integer.toString(this.fMinLength);
            }
            case 8: {
                return Integer.toString(this.fMaxLength);
            }
            case 64: {
                return XSSimpleTypeDecl.WS_FACET_STRING[this.fWhiteSpace];
            }
            case 128: {
                return this.fMaxInclusive.toString();
            }
            case 256: {
                return this.fMaxExclusive.toString();
            }
            case 512: {
                return this.fMinExclusive.toString();
            }
            case 1024: {
                return this.fMinInclusive.toString();
            }
            case 2048: {
                return Integer.toString(this.fTotalDigits);
            }
            case 4096: {
                return Integer.toString(this.fFractionDigits);
            }
            default: {
                return null;
            }
        }
    }
    
    public StringList getLexicalEnumerations() {
        if (this.fEnumeration == null) {
            return null;
        }
        final int size = this.fEnumeration.size();
        final String[] strs = new String[size];
        for (int i = 0; i < size; ++i) {
            strs[i] = this.fEnumeration.elementAt(i).toString();
        }
        return new StringListImpl(strs, size);
    }
    
    public StringList getLexicalPatterns() {
        if (this.fPatternStr == null) {
            return null;
        }
        final int size = this.fPatternStr.size();
        final String[] strs = new String[size];
        for (int i = 0; i < size; ++i) {
            strs[i] = this.fPatternStr.elementAt(i);
        }
        return new StringListImpl(strs, size);
    }
    
    public XSAnnotation getAnnotation() {
        return null;
    }
    
    private void caclFundamentalFacets() {
        this.setOrdered();
        this.setNumeric();
        this.setBounded();
        this.setCardinality();
    }
    
    private void setOrdered() {
        if (this.fVariety == 1) {
            this.fOrdered = this.fBase.fOrdered;
        }
        else if (this.fVariety == 2) {
            this.fOrdered = 0;
        }
        else if (this.fVariety == 3) {
            final int length = this.fMemberTypes.length;
            if (length == 0) {
                this.fOrdered = 1;
                return;
            }
            final short ancestorId = this.getPrimitiveDV(this.fMemberTypes[0].fValidationDV);
            boolean commonAnc = ancestorId != 0;
            boolean allFalse = this.fMemberTypes[0].fOrdered == 0;
            for (int i = 1; i < this.fMemberTypes.length && (commonAnc || allFalse); ++i) {
                if (commonAnc) {
                    commonAnc = (ancestorId == this.getPrimitiveDV(this.fMemberTypes[i].fValidationDV));
                }
                if (allFalse) {
                    allFalse = (this.fMemberTypes[i].fOrdered == 0);
                }
            }
            if (commonAnc) {
                this.fOrdered = this.fMemberTypes[0].fOrdered;
            }
            else if (allFalse) {
                this.fOrdered = 0;
            }
            else {
                this.fOrdered = 1;
            }
        }
    }
    
    private void setNumeric() {
        if (this.fVariety == 1) {
            this.fNumeric = this.fBase.fNumeric;
        }
        else if (this.fVariety == 2) {
            this.fNumeric = false;
        }
        else if (this.fVariety == 3) {
            final XSSimpleType[] memberTypes = this.fMemberTypes;
            for (int i = 0; i < memberTypes.length; ++i) {
                if (!memberTypes[i].getIsNumeric()) {
                    this.fNumeric = false;
                    return;
                }
            }
            this.fNumeric = true;
        }
    }
    
    private void setBounded() {
        if (this.fVariety == 1) {
            if (((this.fFacetsDefined & 0x400) != 0x0 || (this.fFacetsDefined & 0x200) != 0x0) && ((this.fFacetsDefined & 0x80) != 0x0 || (this.fFacetsDefined & 0x100) != 0x0)) {
                this.fBounded = true;
            }
            else {
                this.fBounded = false;
            }
        }
        else if (this.fVariety == 2) {
            if ((this.fFacetsDefined & 0x2) != 0x0 || ((this.fFacetsDefined & 0x4) != 0x0 && (this.fFacetsDefined & 0x8) != 0x0)) {
                this.fBounded = true;
            }
            else {
                this.fBounded = false;
            }
        }
        else if (this.fVariety == 3) {
            final XSSimpleTypeDecl[] memberTypes = this.fMemberTypes;
            short ancestorId = 0;
            if (memberTypes.length > 0) {
                ancestorId = this.getPrimitiveDV(memberTypes[0].fValidationDV);
            }
            for (int i = 0; i < memberTypes.length; ++i) {
                if (!memberTypes[i].getIsBounded() || ancestorId != this.getPrimitiveDV(memberTypes[i].fValidationDV)) {
                    this.fBounded = false;
                    return;
                }
            }
            this.fBounded = true;
        }
    }
    
    private boolean specialCardinalityCheck() {
        return this.fBase.fValidationDV == 9 || this.fBase.fValidationDV == 10 || this.fBase.fValidationDV == 11 || this.fBase.fValidationDV == 12 || this.fBase.fValidationDV == 13 || this.fBase.fValidationDV == 14;
    }
    
    private void setCardinality() {
        if (this.fVariety == 1) {
            if (this.fBase.fFinite) {
                this.fFinite = true;
            }
            else if ((this.fFacetsDefined & 0x2) != 0x0 || (this.fFacetsDefined & 0x8) != 0x0 || (this.fFacetsDefined & 0x800) != 0x0) {
                this.fFinite = true;
            }
            else if (((this.fFacetsDefined & 0x400) != 0x0 || (this.fFacetsDefined & 0x200) != 0x0) && ((this.fFacetsDefined & 0x80) != 0x0 || (this.fFacetsDefined & 0x100) != 0x0)) {
                if ((this.fFacetsDefined & 0x1000) != 0x0 || this.specialCardinalityCheck()) {
                    this.fFinite = true;
                }
                else {
                    this.fFinite = false;
                }
            }
            else {
                this.fFinite = false;
            }
        }
        else if (this.fVariety == 2) {
            if ((this.fFacetsDefined & 0x2) != 0x0 || ((this.fFacetsDefined & 0x4) != 0x0 && (this.fFacetsDefined & 0x8) != 0x0)) {
                this.fFinite = true;
            }
            else {
                this.fFinite = false;
            }
        }
        else if (this.fVariety == 3) {
            final XSSimpleType[] memberTypes = this.fMemberTypes;
            for (int i = 0; i < memberTypes.length; ++i) {
                if (!memberTypes[i].getIsFinite()) {
                    this.fFinite = false;
                    return;
                }
            }
            this.fFinite = true;
        }
    }
    
    private short getPrimitiveDV(final short validationDV) {
        if (validationDV == 20 || validationDV == 21 || validationDV == 22) {
            return 1;
        }
        if (validationDV == 23) {
            return 3;
        }
        return validationDV;
    }
    
    public boolean derivedFrom(final XSTypeDefinition ancestor) {
        if (ancestor == null) {
            return false;
        }
        if (ancestor.getBaseType() == ancestor) {
            return true;
        }
        XSTypeDefinition type;
        for (type = this; type != ancestor && type != XSSimpleTypeDecl.fAnySimpleType; type = type.getBaseType()) {}
        return type == ancestor;
    }
    
    public boolean derivedFrom(final String ancestorNS, final String ancestorName) {
        if (ancestorName == null) {
            return false;
        }
        if ("http://www.w3.org/2001/XMLSchema".equals(ancestorNS) && "anyType".equals(ancestorName)) {
            return true;
        }
        XSTypeDecl type;
        for (type = this; (!ancestorName.equals(type.getName()) || ((ancestorNS != null || type.getNamespace() != null) && (ancestorNS == null || !ancestorNS.equals(type.getNamespace())))) && type != XSSimpleTypeDecl.fAnySimpleType; type = (XSTypeDecl)type.getBaseType()) {}
        return type != XSSimpleTypeDecl.fAnySimpleType;
    }
    
    public void reset() {
        if (this.fIsImmutable) {
            return;
        }
        this.fItemType = null;
        this.fMemberTypes = null;
        this.fTypeName = null;
        this.fTargetNamespace = null;
        this.fFinalSet = 0;
        this.fBase = null;
        this.fVariety = -1;
        this.fValidationDV = -1;
        this.fFacetsDefined = 0;
        this.fFixedFacet = 0;
        this.fWhiteSpace = 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fPattern = null;
        this.fPatternStr = null;
        this.fEnumeration = null;
        this.fMaxInclusive = null;
        this.fMaxExclusive = null;
        this.fMinExclusive = null;
        this.fMinInclusive = null;
        this.fPatternType = 0;
    }
    
    static {
        fDVs = new TypeValidator[] { new AnySimpleDV(), new StringDV(), new BooleanDV(), new DecimalDV(), new FloatDV(), new DoubleDV(), new DurationDV(), new DateTimeDV(), new TimeDV(), new DateDV(), new YearMonthDV(), new YearDV(), new MonthDayDV(), new DayDV(), new MonthDV(), new HexBinaryDV(), new Base64BinaryDV(), new AnyURIDV(), new QNameDV(), new QNameDV(), new IDDV(), new IDREFDV(), new EntityDV(), new IntegerDV(), new ListDV(), new UnionDV() };
        fDVNormalizeType = new short[] { 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 2, 0 };
        SPECIAL_PATTERN_STRING = new String[] { "NONE", "NMTOKEN", "Name", "NCName", "integer" };
        WS_FACET_STRING = new String[] { "preserve", "collapse", "replace" };
        fEmptyContext = new ValidationContext() {
            public boolean needFacetChecking() {
                return true;
            }
            
            public boolean needExtraChecking() {
                return false;
            }
            
            public boolean needToNormalize() {
                return true;
            }
            
            public boolean isEntityDeclared(final String name) {
                return false;
            }
            
            public boolean isEntityUnparsed(final String name) {
                return false;
            }
            
            public boolean isIdDeclared(final String name) {
                return false;
            }
            
            public void addId(final String name) {
            }
            
            public void addIdRef(final String name) {
            }
            
            public String getSymbol(final String symbol) {
                return null;
            }
            
            public String getURI(final String prefix) {
                return null;
            }
        };
        fAnySimpleType = new XSSimpleTypeDecl(null, "anySimpleType", (short)0, (short)0, false, true, false, true);
        fDummyContext = new ValidationContext() {
            public boolean needFacetChecking() {
                return true;
            }
            
            public boolean needExtraChecking() {
                return false;
            }
            
            public boolean needToNormalize() {
                return false;
            }
            
            public boolean isEntityDeclared(final String name) {
                return false;
            }
            
            public boolean isEntityUnparsed(final String name) {
                return false;
            }
            
            public boolean isIdDeclared(final String name) {
                return false;
            }
            
            public void addId(final String name) {
            }
            
            public void addIdRef(final String name) {
            }
            
            public String getSymbol(final String symbol) {
                return null;
            }
            
            public String getURI(final String prefix) {
                return null;
            }
        };
    }
    
    class ValidationContextImpl implements ValidationContext
    {
        ValidationContext fExternal;
        NamespaceContext fNSContext;
        
        ValidationContextImpl(final ValidationContext external) {
            this.fExternal = external;
        }
        
        void setNSContext(final NamespaceContext nsContext) {
            this.fNSContext = nsContext;
        }
        
        public boolean needFacetChecking() {
            return this.fExternal.needFacetChecking();
        }
        
        public boolean needExtraChecking() {
            return this.fExternal.needExtraChecking();
        }
        
        public boolean needToNormalize() {
            return this.fExternal.needToNormalize();
        }
        
        public boolean isEntityDeclared(final String name) {
            return this.fExternal.isEntityDeclared(name);
        }
        
        public boolean isEntityUnparsed(final String name) {
            return this.fExternal.isEntityUnparsed(name);
        }
        
        public boolean isIdDeclared(final String name) {
            return this.fExternal.isIdDeclared(name);
        }
        
        public void addId(final String name) {
            this.fExternal.addId(name);
        }
        
        public void addIdRef(final String name) {
            this.fExternal.addIdRef(name);
        }
        
        public String getSymbol(final String symbol) {
            return this.fExternal.getSymbol(symbol);
        }
        
        public String getURI(final String prefix) {
            if (this.fNSContext == null) {
                return this.fExternal.getURI(prefix);
            }
            return this.fNSContext.getURI(prefix);
        }
    }
}
