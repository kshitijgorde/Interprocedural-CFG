// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.XSDeclarationPool;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.impl.dv.SchemaDVFactory;

public class SchemaDVFactoryImpl extends SchemaDVFactory
{
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    static SymbolHash fBuiltInTypes;
    protected XSDeclarationPool fDeclPool;
    
    public SchemaDVFactoryImpl() {
        this.fDeclPool = null;
    }
    
    public XSSimpleType getBuiltInType(final String name) {
        return (XSSimpleType)SchemaDVFactoryImpl.fBuiltInTypes.get(name);
    }
    
    public SymbolHash getBuiltInTypes() {
        return SchemaDVFactoryImpl.fBuiltInTypes.makeClone();
    }
    
    public XSSimpleType createTypeRestriction(final String name, final String targetNamespace, final short finalSet, final XSSimpleType base) {
        if (this.fDeclPool != null) {
            final XSSimpleTypeDecl st = this.fDeclPool.getSimpleTypeDecl();
            return st.setRestrictionValues((XSSimpleTypeDecl)base, name, targetNamespace, finalSet);
        }
        return new XSSimpleTypeDecl((XSSimpleTypeDecl)base, name, targetNamespace, finalSet, false);
    }
    
    public XSSimpleType createTypeList(final String name, final String targetNamespace, final short finalSet, final XSSimpleType itemType) {
        if (this.fDeclPool != null) {
            final XSSimpleTypeDecl st = this.fDeclPool.getSimpleTypeDecl();
            return st.setListValues(name, targetNamespace, finalSet, (XSSimpleTypeDecl)itemType);
        }
        return new XSSimpleTypeDecl(name, targetNamespace, finalSet, (XSSimpleTypeDecl)itemType, false);
    }
    
    public XSSimpleType createTypeUnion(final String name, final String targetNamespace, final short finalSet, final XSSimpleType[] memberTypes) {
        final int typeNum = memberTypes.length;
        final XSSimpleTypeDecl[] mtypes = new XSSimpleTypeDecl[typeNum];
        System.arraycopy(memberTypes, 0, mtypes, 0, typeNum);
        if (this.fDeclPool != null) {
            final XSSimpleTypeDecl st = this.fDeclPool.getSimpleTypeDecl();
            return st.setUnionValues(name, targetNamespace, finalSet, mtypes);
        }
        return new XSSimpleTypeDecl(name, targetNamespace, finalSet, mtypes);
    }
    
    static void createBuiltInTypes() {
        final String ANYSIMPLETYPE = "anySimpleType";
        final String ANYURI = "anyURI";
        final String BASE64BINARY = "base64Binary";
        final String BOOLEAN = "boolean";
        final String BYTE = "byte";
        final String DATE = "date";
        final String DATETIME = "dateTime";
        final String DAY = "gDay";
        final String DECIMAL = "decimal";
        final String DOUBLE = "double";
        final String DURATION = "duration";
        final String ENTITY = "ENTITY";
        final String ENTITIES = "ENTITIES";
        final String FLOAT = "float";
        final String HEXBINARY = "hexBinary";
        final String ID = "ID";
        final String IDREF = "IDREF";
        final String IDREFS = "IDREFS";
        final String INT = "int";
        final String INTEGER = "integer";
        final String LONG = "long";
        final String NAME = "Name";
        final String NEGATIVEINTEGER = "negativeInteger";
        final String MONTH = "gMonth";
        final String MONTHDAY = "gMonthDay";
        final String NCNAME = "NCName";
        final String NMTOKEN = "NMTOKEN";
        final String NMTOKENS = "NMTOKENS";
        final String LANGUAGE = "language";
        final String NONNEGATIVEINTEGER = "nonNegativeInteger";
        final String NONPOSITIVEINTEGER = "nonPositiveInteger";
        final String NORMALIZEDSTRING = "normalizedString";
        final String NOTATION = "NOTATION";
        final String POSITIVEINTEGER = "positiveInteger";
        final String QNAME = "QName";
        final String SHORT = "short";
        final String STRING = "string";
        final String TIME = "time";
        final String TOKEN = "token";
        final String UNSIGNEDBYTE = "unsignedByte";
        final String UNSIGNEDINT = "unsignedInt";
        final String UNSIGNEDLONG = "unsignedLong";
        final String UNSIGNEDSHORT = "unsignedShort";
        final String YEAR = "gYear";
        final String YEARMONTH = "gYearMonth";
        final XSFacets facets = new XSFacets();
        final XSSimpleTypeDecl anySimpleType = XSSimpleTypeDecl.fAnySimpleType;
        SchemaDVFactoryImpl.fBuiltInTypes.put("anySimpleType", anySimpleType);
        final XSSimpleTypeDecl stringDV = new XSSimpleTypeDecl(anySimpleType, "string", (short)1, (short)0, false, false, false, true);
        SchemaDVFactoryImpl.fBuiltInTypes.put("string", stringDV);
        SchemaDVFactoryImpl.fBuiltInTypes.put("boolean", new XSSimpleTypeDecl(anySimpleType, "boolean", (short)2, (short)0, false, true, false, true));
        final XSSimpleTypeDecl decimalDV = new XSSimpleTypeDecl(anySimpleType, "decimal", (short)3, (short)2, false, false, true, true);
        SchemaDVFactoryImpl.fBuiltInTypes.put("decimal", decimalDV);
        SchemaDVFactoryImpl.fBuiltInTypes.put("anyURI", new XSSimpleTypeDecl(anySimpleType, "anyURI", (short)17, (short)0, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("base64Binary", new XSSimpleTypeDecl(anySimpleType, "base64Binary", (short)16, (short)0, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("duration", new XSSimpleTypeDecl(anySimpleType, "duration", (short)6, (short)1, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("dateTime", new XSSimpleTypeDecl(anySimpleType, "dateTime", (short)7, (short)1, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("time", new XSSimpleTypeDecl(anySimpleType, "time", (short)8, (short)1, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("date", new XSSimpleTypeDecl(anySimpleType, "date", (short)9, (short)1, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("gYearMonth", new XSSimpleTypeDecl(anySimpleType, "gYearMonth", (short)10, (short)1, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("gYear", new XSSimpleTypeDecl(anySimpleType, "gYear", (short)11, (short)1, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("gMonthDay", new XSSimpleTypeDecl(anySimpleType, "gMonthDay", (short)12, (short)1, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("gDay", new XSSimpleTypeDecl(anySimpleType, "gDay", (short)13, (short)1, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("gMonth", new XSSimpleTypeDecl(anySimpleType, "gMonth", (short)14, (short)1, false, false, false, true));
        final XSSimpleTypeDecl integerDV = new XSSimpleTypeDecl(decimalDV, "integer", (short)23, (short)2, false, false, true, true);
        SchemaDVFactoryImpl.fBuiltInTypes.put("integer", integerDV);
        facets.maxInclusive = "0";
        final XSSimpleTypeDecl nonPositiveDV = new XSSimpleTypeDecl(integerDV, "nonPositiveInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        nonPositiveDV.applyFacets1(facets, (short)128, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("nonPositiveInteger", nonPositiveDV);
        facets.maxInclusive = "-1";
        final XSSimpleTypeDecl negativeDV = new XSSimpleTypeDecl(integerDV, "negativeInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        negativeDV.applyFacets1(facets, (short)128, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("negativeInteger", negativeDV);
        facets.maxInclusive = "9223372036854775807";
        facets.minInclusive = "-9223372036854775808";
        final XSSimpleTypeDecl longDV = new XSSimpleTypeDecl(integerDV, "long", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        longDV.applyFacets1(facets, (short)1152, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("long", longDV);
        facets.maxInclusive = "2147483647";
        facets.minInclusive = "-2147483648";
        final XSSimpleTypeDecl intDV = new XSSimpleTypeDecl(longDV, "int", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        intDV.applyFacets1(facets, (short)1152, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("int", intDV);
        facets.maxInclusive = "32767";
        facets.minInclusive = "-32768";
        final XSSimpleTypeDecl shortDV = new XSSimpleTypeDecl(intDV, "short", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        shortDV.applyFacets1(facets, (short)1152, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("short", shortDV);
        facets.maxInclusive = "127";
        facets.minInclusive = "-128";
        final XSSimpleTypeDecl byteDV = new XSSimpleTypeDecl(shortDV, "byte", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        byteDV.applyFacets1(facets, (short)1152, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("byte", byteDV);
        facets.minInclusive = "0";
        final XSSimpleTypeDecl nonNegativeDV = new XSSimpleTypeDecl(integerDV, "nonNegativeInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        nonNegativeDV.applyFacets1(facets, (short)1024, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("nonNegativeInteger", nonNegativeDV);
        facets.maxInclusive = "18446744073709551615";
        final XSSimpleTypeDecl unsignedLongDV = new XSSimpleTypeDecl(nonNegativeDV, "unsignedLong", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        unsignedLongDV.applyFacets1(facets, (short)128, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("unsignedLong", unsignedLongDV);
        facets.maxInclusive = "4294967295";
        final XSSimpleTypeDecl unsignedIntDV = new XSSimpleTypeDecl(unsignedLongDV, "unsignedInt", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        unsignedIntDV.applyFacets1(facets, (short)128, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("unsignedInt", unsignedIntDV);
        facets.maxInclusive = "65535";
        final XSSimpleTypeDecl unsignedShortDV = new XSSimpleTypeDecl(unsignedIntDV, "unsignedShort", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        unsignedShortDV.applyFacets1(facets, (short)128, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("unsignedShort", unsignedShortDV);
        facets.maxInclusive = "255";
        final XSSimpleTypeDecl unsignedByteDV = new XSSimpleTypeDecl(unsignedShortDV, "unsignedByte", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        unsignedByteDV.applyFacets1(facets, (short)128, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("unsignedByte", unsignedByteDV);
        facets.minInclusive = "1";
        final XSSimpleTypeDecl positiveIntegerDV = new XSSimpleTypeDecl(nonNegativeDV, "positiveInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        positiveIntegerDV.applyFacets1(facets, (short)1024, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("positiveInteger", positiveIntegerDV);
        SchemaDVFactoryImpl.fBuiltInTypes.put("float", new XSSimpleTypeDecl(anySimpleType, "float", (short)4, (short)2, true, true, true, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("double", new XSSimpleTypeDecl(anySimpleType, "double", (short)5, (short)2, true, true, true, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("hexBinary", new XSSimpleTypeDecl(anySimpleType, "hexBinary", (short)15, (short)0, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("NOTATION", new XSSimpleTypeDecl(anySimpleType, "NOTATION", (short)19, (short)0, false, false, false, true));
        facets.whiteSpace = 1;
        final XSSimpleTypeDecl normalizedDV = new XSSimpleTypeDecl(stringDV, "normalizedString", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        normalizedDV.applyFacets1(facets, (short)64, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("normalizedString", normalizedDV);
        facets.whiteSpace = 2;
        final XSSimpleTypeDecl tokenDV = new XSSimpleTypeDecl(normalizedDV, "token", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        tokenDV.applyFacets1(facets, (short)64, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("token", tokenDV);
        facets.whiteSpace = 2;
        facets.pattern = "([a-zA-Z]{1,8})(-[a-zA-Z0-9]{1,8})*";
        final XSSimpleTypeDecl languageDV = new XSSimpleTypeDecl(tokenDV, "language", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        languageDV.applyFacets1(facets, (short)80, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("language", languageDV);
        facets.whiteSpace = 2;
        final XSSimpleTypeDecl nameDV = new XSSimpleTypeDecl(tokenDV, "Name", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        nameDV.applyFacets1(facets, (short)64, (short)0, (short)2);
        SchemaDVFactoryImpl.fBuiltInTypes.put("Name", nameDV);
        facets.whiteSpace = 2;
        final XSSimpleTypeDecl ncnameDV = new XSSimpleTypeDecl(nameDV, "NCName", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        ncnameDV.applyFacets1(facets, (short)64, (short)0, (short)3);
        SchemaDVFactoryImpl.fBuiltInTypes.put("NCName", ncnameDV);
        SchemaDVFactoryImpl.fBuiltInTypes.put("QName", new XSSimpleTypeDecl(anySimpleType, "QName", (short)18, (short)0, false, false, false, true));
        SchemaDVFactoryImpl.fBuiltInTypes.put("ID", new XSSimpleTypeDecl(ncnameDV, "ID", (short)20, (short)0, false, false, false, true));
        final XSSimpleTypeDecl idrefDV = new XSSimpleTypeDecl(ncnameDV, "IDREF", (short)21, (short)0, false, false, false, true);
        SchemaDVFactoryImpl.fBuiltInTypes.put("IDREF", idrefDV);
        facets.minLength = 1;
        XSSimpleTypeDecl tempDV = new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, idrefDV, true);
        final XSSimpleTypeDecl idrefsDV = new XSSimpleTypeDecl(tempDV, "IDREFS", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        idrefsDV.applyFacets1(facets, (short)4, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("IDREFS", idrefsDV);
        final XSSimpleTypeDecl entityDV = new XSSimpleTypeDecl(ncnameDV, "ENTITY", (short)22, (short)0, false, false, false, true);
        SchemaDVFactoryImpl.fBuiltInTypes.put("ENTITY", entityDV);
        facets.minLength = 1;
        tempDV = new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, entityDV, true);
        final XSSimpleTypeDecl entitiesDV = new XSSimpleTypeDecl(tempDV, "ENTITIES", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        entitiesDV.applyFacets1(facets, (short)4, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("ENTITIES", entitiesDV);
        facets.whiteSpace = 2;
        final XSSimpleTypeDecl nmtokenDV = new XSSimpleTypeDecl(tokenDV, "NMTOKEN", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        nmtokenDV.applyFacets1(facets, (short)64, (short)0, (short)1);
        SchemaDVFactoryImpl.fBuiltInTypes.put("NMTOKEN", nmtokenDV);
        facets.minLength = 1;
        tempDV = new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, nmtokenDV, true);
        final XSSimpleTypeDecl nmtokensDV = new XSSimpleTypeDecl(tempDV, "NMTOKENS", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        nmtokensDV.applyFacets1(facets, (short)4, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("NMTOKENS", nmtokensDV);
    }
    
    public void setDeclPool(final XSDeclarationPool declPool) {
        this.fDeclPool = declPool;
    }
    
    static {
        SchemaDVFactoryImpl.fBuiltInTypes = new SymbolHash();
        createBuiltInTypes();
    }
}
