// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.util.SymbolHash;

public class FullDVFactory extends BaseDVFactory
{
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    static SymbolHash fFullTypes;
    
    public XSSimpleType getBuiltInType(final String name) {
        return (XSSimpleType)FullDVFactory.fFullTypes.get(name);
    }
    
    public SymbolHash getBuiltInTypes() {
        return FullDVFactory.fFullTypes.makeClone();
    }
    
    static void createBuiltInTypes(final SymbolHash types) {
        BaseDVFactory.createBuiltInTypes(types);
        final String DOUBLE = "double";
        final String DURATION = "duration";
        final String ENTITY = "ENTITY";
        final String ENTITIES = "ENTITIES";
        final String FLOAT = "float";
        final String HEXBINARY = "hexBinary";
        final String ID = "ID";
        final String IDREF = "IDREF";
        final String IDREFS = "IDREFS";
        final String NAME = "Name";
        final String NCNAME = "NCName";
        final String NMTOKEN = "NMTOKEN";
        final String NMTOKENS = "NMTOKENS";
        final String LANGUAGE = "language";
        final String NORMALIZEDSTRING = "normalizedString";
        final String NOTATION = "NOTATION";
        final String QNAME = "QName";
        final String STRING = "string";
        final String TOKEN = "token";
        final XSFacets facets = new XSFacets();
        final XSSimpleTypeDecl anySimpleType = XSSimpleTypeDecl.fAnySimpleType;
        final XSSimpleTypeDecl stringDV = (XSSimpleTypeDecl)types.get("string");
        types.put("float", new XSSimpleTypeDecl(anySimpleType, "float", (short)4, (short)2, true, true, true, true));
        types.put("double", new XSSimpleTypeDecl(anySimpleType, "double", (short)5, (short)2, true, true, true, true));
        types.put("duration", new XSSimpleTypeDecl(anySimpleType, "duration", (short)6, (short)1, false, false, false, true));
        types.put("hexBinary", new XSSimpleTypeDecl(anySimpleType, "hexBinary", (short)15, (short)0, false, false, false, true));
        types.put("QName", new XSSimpleTypeDecl(anySimpleType, "QName", (short)18, (short)0, false, false, false, true));
        types.put("NOTATION", new XSSimpleTypeDecl(anySimpleType, "NOTATION", (short)19, (short)0, false, false, false, true));
        facets.whiteSpace = 1;
        final XSSimpleTypeDecl normalizedDV = new XSSimpleTypeDecl(stringDV, "normalizedString", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        normalizedDV.applyFacets1(facets, (short)64, (short)0);
        types.put("normalizedString", normalizedDV);
        facets.whiteSpace = 2;
        final XSSimpleTypeDecl tokenDV = new XSSimpleTypeDecl(normalizedDV, "token", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        tokenDV.applyFacets1(facets, (short)64, (short)0);
        types.put("token", tokenDV);
        facets.whiteSpace = 2;
        facets.pattern = "([a-zA-Z]{1,8})(-[a-zA-Z0-9]{1,8})*";
        final XSSimpleTypeDecl languageDV = new XSSimpleTypeDecl(tokenDV, "language", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        languageDV.applyFacets1(facets, (short)80, (short)0);
        types.put("language", languageDV);
        facets.whiteSpace = 2;
        final XSSimpleTypeDecl nameDV = new XSSimpleTypeDecl(tokenDV, "Name", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        nameDV.applyFacets1(facets, (short)64, (short)0, (short)2);
        types.put("Name", nameDV);
        facets.whiteSpace = 2;
        final XSSimpleTypeDecl ncnameDV = new XSSimpleTypeDecl(nameDV, "NCName", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        ncnameDV.applyFacets1(facets, (short)64, (short)0, (short)3);
        types.put("NCName", ncnameDV);
        types.put("ID", new XSSimpleTypeDecl(ncnameDV, "ID", (short)20, (short)0, false, false, false, true));
        final XSSimpleTypeDecl idrefDV = new XSSimpleTypeDecl(ncnameDV, "IDREF", (short)21, (short)0, false, false, false, true);
        types.put("IDREF", idrefDV);
        facets.minLength = 1;
        XSSimpleTypeDecl tempDV = new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, idrefDV, true);
        final XSSimpleTypeDecl idrefsDV = new XSSimpleTypeDecl(tempDV, "IDREFS", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        idrefsDV.applyFacets1(facets, (short)4, (short)0);
        types.put("IDREFS", idrefsDV);
        final XSSimpleTypeDecl entityDV = new XSSimpleTypeDecl(ncnameDV, "ENTITY", (short)22, (short)0, false, false, false, true);
        types.put("ENTITY", entityDV);
        facets.minLength = 1;
        tempDV = new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, entityDV, true);
        final XSSimpleTypeDecl entitiesDV = new XSSimpleTypeDecl(tempDV, "ENTITIES", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        entitiesDV.applyFacets1(facets, (short)4, (short)0);
        types.put("ENTITIES", entitiesDV);
        facets.whiteSpace = 2;
        final XSSimpleTypeDecl nmtokenDV = new XSSimpleTypeDecl(tokenDV, "NMTOKEN", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        nmtokenDV.applyFacets1(facets, (short)64, (short)0, (short)1);
        types.put("NMTOKEN", nmtokenDV);
        facets.minLength = 1;
        tempDV = new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short)0, nmtokenDV, true);
        final XSSimpleTypeDecl nmtokensDV = new XSSimpleTypeDecl(tempDV, "NMTOKENS", "http://www.w3.org/2001/XMLSchema", (short)0, false);
        nmtokensDV.applyFacets1(facets, (short)4, (short)0);
        types.put("NMTOKENS", nmtokensDV);
    }
    
    static {
        createBuiltInTypes(FullDVFactory.fFullTypes = new SymbolHash(89));
    }
}
