// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.xs.XSObjectList;
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
    
    public XSSimpleType getBuiltInType(final String s) {
        return (XSSimpleType)SchemaDVFactoryImpl.fBuiltInTypes.get(s);
    }
    
    public SymbolHash getBuiltInTypes() {
        return SchemaDVFactoryImpl.fBuiltInTypes.makeClone();
    }
    
    public XSSimpleType createTypeRestriction(final String s, final String s2, final short n, final XSSimpleType xsSimpleType, final XSObjectList list) {
        if (this.fDeclPool != null) {
            return this.fDeclPool.getSimpleTypeDecl().setRestrictionValues((XSSimpleTypeDecl)xsSimpleType, s, s2, n, list);
        }
        return new XSSimpleTypeDecl((XSSimpleTypeDecl)xsSimpleType, s, s2, n, false, list);
    }
    
    public XSSimpleType createTypeList(final String s, final String s2, final short n, final XSSimpleType xsSimpleType, final XSObjectList list) {
        if (this.fDeclPool != null) {
            return this.fDeclPool.getSimpleTypeDecl().setListValues(s, s2, n, (XSSimpleTypeDecl)xsSimpleType, list);
        }
        return new XSSimpleTypeDecl(s, s2, n, (XSSimpleTypeDecl)xsSimpleType, false, list);
    }
    
    public XSSimpleType createTypeUnion(final String s, final String s2, final short n, final XSSimpleType[] array, final XSObjectList list) {
        final int length = array.length;
        final XSSimpleTypeDecl[] array2 = new XSSimpleTypeDecl[length];
        System.arraycopy(array, 0, array2, 0, length);
        if (this.fDeclPool != null) {
            return this.fDeclPool.getSimpleTypeDecl().setUnionValues(s, s2, n, array2, list);
        }
        return new XSSimpleTypeDecl(s, s2, n, array2, list);
    }
    
    static void createBuiltInTypes() {
        final XSFacets xsFacets = new XSFacets();
        final XSSimpleTypeDecl fAnySimpleType = XSSimpleTypeDecl.fAnySimpleType;
        final XSSimpleTypeDecl fAnyAtomicType = XSSimpleTypeDecl.fAnyAtomicType;
        final XSSimpleTypeDecl xsSimpleTypeDecl = fAnySimpleType;
        SchemaDVFactoryImpl.fBuiltInTypes.put("anySimpleType", fAnySimpleType);
        final XSSimpleTypeDecl xsSimpleTypeDecl2 = new XSSimpleTypeDecl(xsSimpleTypeDecl, "string", (short)1, (short)0, false, false, false, true, (short)2);
        SchemaDVFactoryImpl.fBuiltInTypes.put("string", xsSimpleTypeDecl2);
        SchemaDVFactoryImpl.fBuiltInTypes.put("boolean", new XSSimpleTypeDecl(xsSimpleTypeDecl, "boolean", (short)2, (short)0, false, true, false, true, (short)3));
        final XSSimpleTypeDecl xsSimpleTypeDecl3 = new XSSimpleTypeDecl(xsSimpleTypeDecl, "decimal", (short)3, (short)2, false, false, true, true, (short)4);
        SchemaDVFactoryImpl.fBuiltInTypes.put("decimal", xsSimpleTypeDecl3);
        SchemaDVFactoryImpl.fBuiltInTypes.put("anyURI", new XSSimpleTypeDecl(xsSimpleTypeDecl, "anyURI", (short)17, (short)0, false, false, false, true, (short)18));
        SchemaDVFactoryImpl.fBuiltInTypes.put("base64Binary", new XSSimpleTypeDecl(xsSimpleTypeDecl, "base64Binary", (short)16, (short)0, false, false, false, true, (short)17));
        SchemaDVFactoryImpl.fBuiltInTypes.put("duration", new XSSimpleTypeDecl(xsSimpleTypeDecl, "duration", (short)6, (short)1, false, false, false, true, (short)7));
        SchemaDVFactoryImpl.fBuiltInTypes.put("dateTime", new XSSimpleTypeDecl(xsSimpleTypeDecl, "dateTime", (short)7, (short)1, false, false, false, true, (short)8));
        SchemaDVFactoryImpl.fBuiltInTypes.put("time", new XSSimpleTypeDecl(xsSimpleTypeDecl, "time", (short)8, (short)1, false, false, false, true, (short)9));
        SchemaDVFactoryImpl.fBuiltInTypes.put("date", new XSSimpleTypeDecl(xsSimpleTypeDecl, "date", (short)9, (short)1, false, false, false, true, (short)10));
        SchemaDVFactoryImpl.fBuiltInTypes.put("gYearMonth", new XSSimpleTypeDecl(xsSimpleTypeDecl, "gYearMonth", (short)10, (short)1, false, false, false, true, (short)11));
        SchemaDVFactoryImpl.fBuiltInTypes.put("gYear", new XSSimpleTypeDecl(xsSimpleTypeDecl, "gYear", (short)11, (short)1, false, false, false, true, (short)12));
        SchemaDVFactoryImpl.fBuiltInTypes.put("gMonthDay", new XSSimpleTypeDecl(xsSimpleTypeDecl, "gMonthDay", (short)12, (short)1, false, false, false, true, (short)13));
        SchemaDVFactoryImpl.fBuiltInTypes.put("gDay", new XSSimpleTypeDecl(xsSimpleTypeDecl, "gDay", (short)13, (short)1, false, false, false, true, (short)14));
        SchemaDVFactoryImpl.fBuiltInTypes.put("gMonth", new XSSimpleTypeDecl(xsSimpleTypeDecl, "gMonth", (short)14, (short)1, false, false, false, true, (short)15));
        final XSSimpleTypeDecl xsSimpleTypeDecl4 = new XSSimpleTypeDecl(xsSimpleTypeDecl3, "integer", (short)24, (short)2, false, false, true, true, (short)30);
        SchemaDVFactoryImpl.fBuiltInTypes.put("integer", xsSimpleTypeDecl4);
        xsFacets.maxInclusive = "0";
        final XSSimpleTypeDecl xsSimpleTypeDecl5 = new XSSimpleTypeDecl(xsSimpleTypeDecl4, "nonPositiveInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)31);
        xsSimpleTypeDecl5.applyFacets1(xsFacets, (short)32, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("nonPositiveInteger", xsSimpleTypeDecl5);
        xsFacets.maxInclusive = "-1";
        final XSSimpleTypeDecl xsSimpleTypeDecl6 = new XSSimpleTypeDecl(xsSimpleTypeDecl4, "negativeInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)32);
        xsSimpleTypeDecl6.applyFacets1(xsFacets, (short)32, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("negativeInteger", xsSimpleTypeDecl6);
        xsFacets.maxInclusive = "9223372036854775807";
        xsFacets.minInclusive = "-9223372036854775808";
        final XSSimpleTypeDecl xsSimpleTypeDecl7 = new XSSimpleTypeDecl(xsSimpleTypeDecl4, "long", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)33);
        xsSimpleTypeDecl7.applyFacets1(xsFacets, (short)288, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("long", xsSimpleTypeDecl7);
        xsFacets.maxInclusive = "2147483647";
        xsFacets.minInclusive = "-2147483648";
        final XSSimpleTypeDecl xsSimpleTypeDecl8 = new XSSimpleTypeDecl(xsSimpleTypeDecl7, "int", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)34);
        xsSimpleTypeDecl8.applyFacets1(xsFacets, (short)288, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("int", xsSimpleTypeDecl8);
        xsFacets.maxInclusive = "32767";
        xsFacets.minInclusive = "-32768";
        final XSSimpleTypeDecl xsSimpleTypeDecl9 = new XSSimpleTypeDecl(xsSimpleTypeDecl8, "short", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)35);
        xsSimpleTypeDecl9.applyFacets1(xsFacets, (short)288, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("short", xsSimpleTypeDecl9);
        xsFacets.maxInclusive = "127";
        xsFacets.minInclusive = "-128";
        final XSSimpleTypeDecl xsSimpleTypeDecl10 = new XSSimpleTypeDecl(xsSimpleTypeDecl9, "byte", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)36);
        xsSimpleTypeDecl10.applyFacets1(xsFacets, (short)288, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("byte", xsSimpleTypeDecl10);
        xsFacets.minInclusive = "0";
        final XSSimpleTypeDecl xsSimpleTypeDecl11 = new XSSimpleTypeDecl(xsSimpleTypeDecl4, "nonNegativeInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)37);
        xsSimpleTypeDecl11.applyFacets1(xsFacets, (short)256, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("nonNegativeInteger", xsSimpleTypeDecl11);
        xsFacets.maxInclusive = "18446744073709551615";
        final XSSimpleTypeDecl xsSimpleTypeDecl12 = new XSSimpleTypeDecl(xsSimpleTypeDecl11, "unsignedLong", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)38);
        xsSimpleTypeDecl12.applyFacets1(xsFacets, (short)32, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("unsignedLong", xsSimpleTypeDecl12);
        xsFacets.maxInclusive = "4294967295";
        final XSSimpleTypeDecl xsSimpleTypeDecl13 = new XSSimpleTypeDecl(xsSimpleTypeDecl12, "unsignedInt", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)39);
        xsSimpleTypeDecl13.applyFacets1(xsFacets, (short)32, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("unsignedInt", xsSimpleTypeDecl13);
        xsFacets.maxInclusive = "65535";
        final XSSimpleTypeDecl xsSimpleTypeDecl14 = new XSSimpleTypeDecl(xsSimpleTypeDecl13, "unsignedShort", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)40);
        xsSimpleTypeDecl14.applyFacets1(xsFacets, (short)32, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("unsignedShort", xsSimpleTypeDecl14);
        xsFacets.maxInclusive = "255";
        final XSSimpleTypeDecl xsSimpleTypeDecl15 = new XSSimpleTypeDecl(xsSimpleTypeDecl14, "unsignedByte", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)41);
        xsSimpleTypeDecl15.applyFacets1(xsFacets, (short)32, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("unsignedByte", xsSimpleTypeDecl15);
        xsFacets.minInclusive = "1";
        final XSSimpleTypeDecl xsSimpleTypeDecl16 = new XSSimpleTypeDecl(xsSimpleTypeDecl11, "positiveInteger", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)42);
        xsSimpleTypeDecl16.applyFacets1(xsFacets, (short)256, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("positiveInteger", xsSimpleTypeDecl16);
        SchemaDVFactoryImpl.fBuiltInTypes.put("float", new XSSimpleTypeDecl(xsSimpleTypeDecl, "float", (short)4, (short)1, true, true, true, true, (short)5));
        SchemaDVFactoryImpl.fBuiltInTypes.put("double", new XSSimpleTypeDecl(xsSimpleTypeDecl, "double", (short)5, (short)1, true, true, true, true, (short)6));
        SchemaDVFactoryImpl.fBuiltInTypes.put("hexBinary", new XSSimpleTypeDecl(xsSimpleTypeDecl, "hexBinary", (short)15, (short)0, false, false, false, true, (short)16));
        SchemaDVFactoryImpl.fBuiltInTypes.put("NOTATION", new XSSimpleTypeDecl(xsSimpleTypeDecl, "NOTATION", (short)20, (short)0, false, false, false, true, (short)20));
        xsFacets.whiteSpace = 1;
        final XSSimpleTypeDecl xsSimpleTypeDecl17 = new XSSimpleTypeDecl(xsSimpleTypeDecl2, "normalizedString", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)21);
        xsSimpleTypeDecl17.applyFacets1(xsFacets, (short)16, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("normalizedString", xsSimpleTypeDecl17);
        xsFacets.whiteSpace = 2;
        final XSSimpleTypeDecl xsSimpleTypeDecl18 = new XSSimpleTypeDecl(xsSimpleTypeDecl17, "token", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)22);
        xsSimpleTypeDecl18.applyFacets1(xsFacets, (short)16, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("token", xsSimpleTypeDecl18);
        xsFacets.whiteSpace = 2;
        xsFacets.pattern = "([a-zA-Z]{1,8})(-[a-zA-Z0-9]{1,8})*";
        final XSSimpleTypeDecl xsSimpleTypeDecl19 = new XSSimpleTypeDecl(xsSimpleTypeDecl18, "language", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)23);
        xsSimpleTypeDecl19.applyFacets1(xsFacets, (short)24, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("language", xsSimpleTypeDecl19);
        xsFacets.whiteSpace = 2;
        final XSSimpleTypeDecl xsSimpleTypeDecl20 = new XSSimpleTypeDecl(xsSimpleTypeDecl18, "Name", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)25);
        xsSimpleTypeDecl20.applyFacets1(xsFacets, (short)16, (short)0, (short)2);
        SchemaDVFactoryImpl.fBuiltInTypes.put("Name", xsSimpleTypeDecl20);
        xsFacets.whiteSpace = 2;
        final XSSimpleTypeDecl xsSimpleTypeDecl21 = new XSSimpleTypeDecl(xsSimpleTypeDecl20, "NCName", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)26);
        xsSimpleTypeDecl21.applyFacets1(xsFacets, (short)16, (short)0, (short)3);
        SchemaDVFactoryImpl.fBuiltInTypes.put("NCName", xsSimpleTypeDecl21);
        SchemaDVFactoryImpl.fBuiltInTypes.put("QName", new XSSimpleTypeDecl(xsSimpleTypeDecl, "QName", (short)18, (short)0, false, false, false, true, (short)19));
        SchemaDVFactoryImpl.fBuiltInTypes.put("ID", new XSSimpleTypeDecl(xsSimpleTypeDecl21, "ID", (short)21, (short)0, false, false, false, true, (short)27));
        final XSSimpleTypeDecl xsSimpleTypeDecl22 = new XSSimpleTypeDecl(xsSimpleTypeDecl21, "IDREF", (short)22, (short)0, false, false, false, true, (short)28);
        SchemaDVFactoryImpl.fBuiltInTypes.put("IDREF", xsSimpleTypeDecl22);
        xsFacets.minLength = 1;
        final XSSimpleTypeDecl xsSimpleTypeDecl23 = new XSSimpleTypeDecl(new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, xsSimpleTypeDecl22, true, null), "IDREFS", "http://www.w3.org/2001/XMLSchema", (short)0, false, null);
        xsSimpleTypeDecl23.applyFacets1(xsFacets, (short)2, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("IDREFS", xsSimpleTypeDecl23);
        final XSSimpleTypeDecl xsSimpleTypeDecl24 = new XSSimpleTypeDecl(xsSimpleTypeDecl21, "ENTITY", (short)23, (short)0, false, false, false, true, (short)29);
        SchemaDVFactoryImpl.fBuiltInTypes.put("ENTITY", xsSimpleTypeDecl24);
        xsFacets.minLength = 1;
        final XSSimpleTypeDecl xsSimpleTypeDecl25 = new XSSimpleTypeDecl(new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, xsSimpleTypeDecl24, true, null), "ENTITIES", "http://www.w3.org/2001/XMLSchema", (short)0, false, null);
        xsSimpleTypeDecl25.applyFacets1(xsFacets, (short)2, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("ENTITIES", xsSimpleTypeDecl25);
        xsFacets.whiteSpace = 2;
        final XSSimpleTypeDecl xsSimpleTypeDecl26 = new XSSimpleTypeDecl(xsSimpleTypeDecl18, "NMTOKEN", "http://www.w3.org/2001/XMLSchema", (short)0, false, null, (short)24);
        xsSimpleTypeDecl26.applyFacets1(xsFacets, (short)16, (short)0, (short)1);
        SchemaDVFactoryImpl.fBuiltInTypes.put("NMTOKEN", xsSimpleTypeDecl26);
        xsFacets.minLength = 1;
        final XSSimpleTypeDecl xsSimpleTypeDecl27 = new XSSimpleTypeDecl(new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, xsSimpleTypeDecl26, true, null), "NMTOKENS", "http://www.w3.org/2001/XMLSchema", (short)0, false, null);
        xsSimpleTypeDecl27.applyFacets1(xsFacets, (short)2, (short)0);
        SchemaDVFactoryImpl.fBuiltInTypes.put("NMTOKENS", xsSimpleTypeDecl27);
    }
    
    public void setDeclPool(final XSDeclarationPool fDeclPool) {
        this.fDeclPool = fDeclPool;
    }
    
    static {
        SchemaDVFactoryImpl.fBuiltInTypes = new SymbolHash();
        createBuiltInTypes();
    }
}
