// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import java.util.Vector;
import org.apache.xerces.impl.xs.models.XSCMValidator;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.impl.xs.models.CMBuilder;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.impl.xs.util.SimpleLocator;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.impl.dv.XSSimpleType;

public class XSConstraints
{
    static final int OCCURRENCE_UNKNOWN = -2;
    static final XSSimpleType STRING_TYPE;
    
    public static boolean checkTypeDerivationOk(final XSTypeDefinition xsTypeDefinition, XSTypeDefinition fAnySimpleType, final short n) {
        if (xsTypeDefinition == SchemaGrammar.fAnyType) {
            return xsTypeDefinition == fAnySimpleType;
        }
        if (xsTypeDefinition == SchemaGrammar.fAnySimpleType) {
            return fAnySimpleType == SchemaGrammar.fAnyType || fAnySimpleType == SchemaGrammar.fAnySimpleType;
        }
        if (xsTypeDefinition.getTypeCategory() == 16) {
            if (fAnySimpleType.getTypeCategory() == 15) {
                if (fAnySimpleType != SchemaGrammar.fAnyType) {
                    return false;
                }
                fAnySimpleType = SchemaGrammar.fAnySimpleType;
            }
            return checkSimpleDerivation((XSSimpleType)xsTypeDefinition, (XSSimpleType)fAnySimpleType, n);
        }
        return checkComplexDerivation((XSComplexTypeDecl)xsTypeDefinition, fAnySimpleType, n);
    }
    
    public static boolean checkSimpleDerivationOk(final XSSimpleType xsSimpleType, XSTypeDefinition fAnySimpleType, final short n) {
        if (xsSimpleType == SchemaGrammar.fAnySimpleType) {
            return fAnySimpleType == SchemaGrammar.fAnyType || fAnySimpleType == SchemaGrammar.fAnySimpleType;
        }
        if (fAnySimpleType.getTypeCategory() == 15) {
            if (fAnySimpleType != SchemaGrammar.fAnyType) {
                return false;
            }
            fAnySimpleType = SchemaGrammar.fAnySimpleType;
        }
        return checkSimpleDerivation(xsSimpleType, (XSSimpleType)fAnySimpleType, n);
    }
    
    public static boolean checkComplexDerivationOk(final XSComplexTypeDecl xsComplexTypeDecl, final XSTypeDefinition xsTypeDefinition, final short n) {
        if (xsComplexTypeDecl == SchemaGrammar.fAnyType) {
            return xsComplexTypeDecl == xsTypeDefinition;
        }
        return checkComplexDerivation(xsComplexTypeDecl, xsTypeDefinition, n);
    }
    
    private static boolean checkSimpleDerivation(final XSSimpleType xsSimpleType, XSSimpleType xsSimpleType2, final short n) {
        if (xsSimpleType == xsSimpleType2) {
            return true;
        }
        if ((n & 0x2) != 0x0 || (xsSimpleType.getBaseType().getFinal() & 0x2) != 0x0) {
            return false;
        }
        final XSSimpleType xsSimpleType3 = (XSSimpleType)xsSimpleType.getBaseType();
        if (xsSimpleType3 == xsSimpleType2) {
            return true;
        }
        if (xsSimpleType3 != SchemaGrammar.fAnySimpleType && checkSimpleDerivation(xsSimpleType3, xsSimpleType2, n)) {
            return true;
        }
        if ((xsSimpleType.getVariety() == 2 || xsSimpleType.getVariety() == 3) && xsSimpleType2 == SchemaGrammar.fAnySimpleType) {
            return true;
        }
        if (xsSimpleType2.getVariety() == 3) {
            final XSObjectList memberTypes = xsSimpleType2.getMemberTypes();
            for (int length = memberTypes.getLength(), i = 0; i < length; ++i) {
                xsSimpleType2 = (XSSimpleType)memberTypes.item(i);
                if (checkSimpleDerivation(xsSimpleType, xsSimpleType2, n)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean checkComplexDerivation(final XSComplexTypeDecl xsComplexTypeDecl, XSTypeDefinition fAnySimpleType, final short n) {
        if (xsComplexTypeDecl == fAnySimpleType) {
            return true;
        }
        if ((xsComplexTypeDecl.fDerivedBy & n) != 0x0) {
            return false;
        }
        final XSTypeDefinition fBaseType = xsComplexTypeDecl.fBaseType;
        if (fBaseType == fAnySimpleType) {
            return true;
        }
        if (fBaseType == SchemaGrammar.fAnyType || fBaseType == SchemaGrammar.fAnySimpleType) {
            return false;
        }
        if (fBaseType.getTypeCategory() == 15) {
            return checkComplexDerivation((XSComplexTypeDecl)fBaseType, fAnySimpleType, n);
        }
        if (fBaseType.getTypeCategory() == 16) {
            if (fAnySimpleType.getTypeCategory() == 15) {
                if (fAnySimpleType != SchemaGrammar.fAnyType) {
                    return false;
                }
                fAnySimpleType = SchemaGrammar.fAnySimpleType;
            }
            return checkSimpleDerivation((XSSimpleType)fBaseType, (XSSimpleType)fAnySimpleType, n);
        }
        return false;
    }
    
    public static Object ElementDefaultValidImmediate(final XSTypeDefinition xsTypeDefinition, final String s, final ValidationContext validationContext, final ValidatedInfo validatedInfo) {
        XSSimpleType xsSimpleType = null;
        if (xsTypeDefinition.getTypeCategory() == 16) {
            xsSimpleType = (XSSimpleType)xsTypeDefinition;
        }
        else {
            final XSComplexTypeDecl xsComplexTypeDecl = (XSComplexTypeDecl)xsTypeDefinition;
            if (xsComplexTypeDecl.fContentType == 1) {
                xsSimpleType = xsComplexTypeDecl.fXSSimpleType;
            }
            else {
                if (xsComplexTypeDecl.fContentType != 3) {
                    return null;
                }
                if (!((XSParticleDecl)xsComplexTypeDecl.getParticle()).emptiable()) {
                    return null;
                }
            }
        }
        if (xsSimpleType == null) {
            xsSimpleType = XSConstraints.STRING_TYPE;
        }
        Object o;
        try {
            o = xsSimpleType.validate(s, validationContext, validatedInfo);
            if (validatedInfo != null) {
                o = xsSimpleType.validate(validatedInfo.stringValue(), validationContext, validatedInfo);
            }
        }
        catch (InvalidDatatypeValueException ex) {
            return null;
        }
        return o;
    }
    
    static void reportSchemaError(final XMLErrorReporter xmlErrorReporter, final SimpleLocator simpleLocator, final String s, final Object[] array) {
        if (simpleLocator != null) {
            xmlErrorReporter.reportError(simpleLocator, "http://www.w3.org/TR/xml-schema-1", s, array, (short)1);
        }
        else {
            xmlErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", s, array, (short)1);
        }
    }
    
    public static void fullSchemaChecking(final XSGrammarBucket xsGrammarBucket, final SubstitutionGroupHandler substitutionGroupHandler, final CMBuilder cmBuilder, final XMLErrorReporter xmlErrorReporter) {
        final SchemaGrammar[] grammars = xsGrammarBucket.getGrammars();
        for (int i = grammars.length - 1; i >= 0; --i) {
            substitutionGroupHandler.addSubstitutionGroup(grammars[i].getSubstitutionGroups());
        }
        final XSParticleDecl xsParticleDecl = new XSParticleDecl();
        final XSParticleDecl xsParticleDecl2 = new XSParticleDecl();
        xsParticleDecl.fType = 3;
        xsParticleDecl2.fType = 3;
        for (int j = grammars.length - 1; j >= 0; --j) {
            final XSGroupDecl[] redefinedGroupDecls = grammars[j].getRedefinedGroupDecls();
            final SimpleLocator[] rgLocators = grammars[j].getRGLocators();
            int k = 0;
            while (k < redefinedGroupDecls.length) {
                final XSGroupDecl xsGroupDecl = redefinedGroupDecls[k++];
                final XSModelGroupImpl fModelGroup = xsGroupDecl.fModelGroup;
                final XSModelGroupImpl fModelGroup2 = redefinedGroupDecls[k++].fModelGroup;
                if (fModelGroup2 == null) {
                    if (fModelGroup == null) {
                        continue;
                    }
                    reportSchemaError(xmlErrorReporter, rgLocators[k / 2 - 1], "src-redefine.6.2.2", new Object[] { xsGroupDecl.fName, "rcase-Recurse.2" });
                }
                else {
                    xsParticleDecl.fValue = fModelGroup;
                    xsParticleDecl2.fValue = fModelGroup2;
                    try {
                        particleValidRestriction(xsParticleDecl, substitutionGroupHandler, xsParticleDecl2, substitutionGroupHandler);
                    }
                    catch (XMLSchemaException ex) {
                        final String key = ex.getKey();
                        reportSchemaError(xmlErrorReporter, rgLocators[k / 2 - 1], key, ex.getArgs());
                        reportSchemaError(xmlErrorReporter, rgLocators[k / 2 - 1], "src-redefine.6.2.2", new Object[] { xsGroupDecl.fName, key });
                    }
                }
            }
        }
        final SymbolHash symbolHash = new SymbolHash();
        for (int l = grammars.length - 1; l >= 0; --l) {
            int uncheckedTypeNum = 0;
            final boolean fFullChecked = grammars[l].fFullChecked;
            final XSComplexTypeDecl[] uncheckedComplexTypeDecls = grammars[l].getUncheckedComplexTypeDecls();
            final SimpleLocator[] uncheckedCTLocators = grammars[l].getUncheckedCTLocators();
            for (int n = 0; n < uncheckedComplexTypeDecls.length; ++n) {
                if (!fFullChecked && uncheckedComplexTypeDecls[n].fParticle != null) {
                    symbolHash.clear();
                    try {
                        checkElementDeclsConsistent(uncheckedComplexTypeDecls[n], uncheckedComplexTypeDecls[n].fParticle, symbolHash, substitutionGroupHandler);
                    }
                    catch (XMLSchemaException ex2) {
                        reportSchemaError(xmlErrorReporter, uncheckedCTLocators[n], ex2.getKey(), ex2.getArgs());
                    }
                }
                if (uncheckedComplexTypeDecls[n].fBaseType != null && uncheckedComplexTypeDecls[n].fBaseType != SchemaGrammar.fAnyType && uncheckedComplexTypeDecls[n].fDerivedBy == 2 && uncheckedComplexTypeDecls[n].fBaseType instanceof XSComplexTypeDecl) {
                    final XSParticleDecl fParticle = uncheckedComplexTypeDecls[n].fParticle;
                    final XSParticleDecl fParticle2 = ((XSComplexTypeDecl)uncheckedComplexTypeDecls[n].fBaseType).fParticle;
                    if (fParticle == null) {
                        if (fParticle2 != null && !fParticle2.emptiable()) {
                            reportSchemaError(xmlErrorReporter, uncheckedCTLocators[n], "derivation-ok-restriction.5.3.2", new Object[] { uncheckedComplexTypeDecls[n].fName, uncheckedComplexTypeDecls[n].fBaseType.getName() });
                        }
                    }
                    else if (fParticle2 != null) {
                        try {
                            particleValidRestriction(uncheckedComplexTypeDecls[n].fParticle, substitutionGroupHandler, ((XSComplexTypeDecl)uncheckedComplexTypeDecls[n].fBaseType).fParticle, substitutionGroupHandler);
                        }
                        catch (XMLSchemaException ex3) {
                            reportSchemaError(xmlErrorReporter, uncheckedCTLocators[n], ex3.getKey(), ex3.getArgs());
                            reportSchemaError(xmlErrorReporter, uncheckedCTLocators[n], "derivation-ok-restriction.5.4.2", new Object[] { uncheckedComplexTypeDecls[n].fName });
                        }
                    }
                    else {
                        reportSchemaError(xmlErrorReporter, uncheckedCTLocators[n], "derivation-ok-restriction.5.4.2", new Object[] { uncheckedComplexTypeDecls[n].fName });
                    }
                }
                final XSCMValidator contentModel = uncheckedComplexTypeDecls[n].getContentModel(cmBuilder);
                boolean checkUniqueParticleAttribution = false;
                if (contentModel != null) {
                    try {
                        checkUniqueParticleAttribution = contentModel.checkUniqueParticleAttribution(substitutionGroupHandler);
                    }
                    catch (XMLSchemaException ex4) {
                        reportSchemaError(xmlErrorReporter, uncheckedCTLocators[n], ex4.getKey(), ex4.getArgs());
                    }
                }
                if (!fFullChecked && checkUniqueParticleAttribution) {
                    uncheckedComplexTypeDecls[uncheckedTypeNum++] = uncheckedComplexTypeDecls[n];
                }
            }
            if (!fFullChecked) {
                grammars[l].setUncheckedTypeNum(uncheckedTypeNum);
                grammars[l].fFullChecked = true;
            }
        }
    }
    
    public static void checkElementDeclsConsistent(final XSComplexTypeDecl xsComplexTypeDecl, final XSParticleDecl xsParticleDecl, final SymbolHash symbolHash, final SubstitutionGroupHandler substitutionGroupHandler) throws XMLSchemaException {
        final short fType = xsParticleDecl.fType;
        if (fType == 2) {
            return;
        }
        if (fType == 1) {
            final XSElementDecl xsElementDecl = (XSElementDecl)xsParticleDecl.fValue;
            findElemInTable(xsComplexTypeDecl, xsElementDecl, symbolHash);
            if (xsElementDecl.fScope == 1) {
                final XSElementDecl[] substitutionGroup = substitutionGroupHandler.getSubstitutionGroup(xsElementDecl);
                for (int i = 0; i < substitutionGroup.length; ++i) {
                    findElemInTable(xsComplexTypeDecl, substitutionGroup[i], symbolHash);
                }
            }
            return;
        }
        final XSModelGroupImpl xsModelGroupImpl = (XSModelGroupImpl)xsParticleDecl.fValue;
        for (int j = 0; j < xsModelGroupImpl.fParticleCount; ++j) {
            checkElementDeclsConsistent(xsComplexTypeDecl, xsModelGroupImpl.fParticles[j], symbolHash, substitutionGroupHandler);
        }
    }
    
    public static void findElemInTable(final XSComplexTypeDecl xsComplexTypeDecl, final XSElementDecl xsElementDecl, final SymbolHash symbolHash) throws XMLSchemaException {
        final String string = xsElementDecl.fName + "," + xsElementDecl.fTargetNamespace;
        final XSElementDecl xsElementDecl2;
        if ((xsElementDecl2 = (XSElementDecl)symbolHash.get(string)) == null) {
            symbolHash.put(string, xsElementDecl);
        }
        else {
            if (xsElementDecl == xsElementDecl2) {
                return;
            }
            if (xsElementDecl.fType != xsElementDecl2.fType) {
                throw new XMLSchemaException("cos-element-consistent", new Object[] { xsComplexTypeDecl.fName, xsElementDecl.fName });
            }
        }
    }
    
    private static void particleValidRestriction(final XSParticleDecl xsParticleDecl, final SubstitutionGroupHandler substitutionGroupHandler, final XSParticleDecl xsParticleDecl2, final SubstitutionGroupHandler substitutionGroupHandler2) throws XMLSchemaException {
        particleValidRestriction(xsParticleDecl, substitutionGroupHandler, xsParticleDecl2, substitutionGroupHandler2, true);
    }
    
    private static void particleValidRestriction(XSParticleDecl xsParticleDecl, SubstitutionGroupHandler substitutionGroupHandler, XSParticleDecl xsParticleDecl2, SubstitutionGroupHandler substitutionGroupHandler2, final boolean b) throws XMLSchemaException {
        Vector removePointlessChildren = null;
        Vector removePointlessChildren2 = null;
        int n = -2;
        int n2 = -2;
        if (xsParticleDecl.isEmpty() && !xsParticleDecl2.emptiable()) {
            throw new XMLSchemaException("cos-particle-restrict.a", (Object[])null);
        }
        if (!xsParticleDecl.isEmpty() && xsParticleDecl2.isEmpty()) {
            throw new XMLSchemaException("cos-particle-restrict.b", (Object[])null);
        }
        int n3 = xsParticleDecl.fType;
        if (n3 == 3) {
            n3 = ((XSModelGroupImpl)xsParticleDecl.fValue).fCompositor;
            final XSParticleDecl nonUnaryGroup = getNonUnaryGroup(xsParticleDecl);
            if (nonUnaryGroup != xsParticleDecl) {
                xsParticleDecl = nonUnaryGroup;
                n3 = xsParticleDecl.fType;
                if (n3 == 3) {
                    n3 = ((XSModelGroupImpl)xsParticleDecl.fValue).fCompositor;
                }
            }
            removePointlessChildren = removePointlessChildren(xsParticleDecl);
        }
        final int fMinOccurs = xsParticleDecl.fMinOccurs;
        final int fMaxOccurs = xsParticleDecl.fMaxOccurs;
        if (substitutionGroupHandler != null && n3 == 1) {
            final XSElementDecl xsElementDecl = (XSElementDecl)xsParticleDecl.fValue;
            if (xsElementDecl.fScope == 1) {
                final XSElementDecl[] substitutionGroup = substitutionGroupHandler.getSubstitutionGroup(xsElementDecl);
                if (substitutionGroup.length > 0) {
                    n3 = 101;
                    n = fMinOccurs;
                    n2 = fMaxOccurs;
                    removePointlessChildren = new Vector(substitutionGroup.length + 1);
                    for (int i = 0; i < substitutionGroup.length; ++i) {
                        addElementToParticleVector(removePointlessChildren, substitutionGroup[i]);
                    }
                    addElementToParticleVector(removePointlessChildren, xsElementDecl);
                    substitutionGroupHandler = null;
                }
            }
        }
        int n4 = xsParticleDecl2.fType;
        if (n4 == 3) {
            n4 = ((XSModelGroupImpl)xsParticleDecl2.fValue).fCompositor;
            final XSParticleDecl nonUnaryGroup2 = getNonUnaryGroup(xsParticleDecl2);
            if (nonUnaryGroup2 != xsParticleDecl2) {
                xsParticleDecl2 = nonUnaryGroup2;
                n4 = xsParticleDecl2.fType;
                if (n4 == 3) {
                    n4 = ((XSModelGroupImpl)xsParticleDecl2.fValue).fCompositor;
                }
            }
            removePointlessChildren2 = removePointlessChildren(xsParticleDecl2);
        }
        final int fMinOccurs2 = xsParticleDecl2.fMinOccurs;
        final int fMaxOccurs2 = xsParticleDecl2.fMaxOccurs;
        if (substitutionGroupHandler2 != null && n4 == 1) {
            final XSElementDecl xsElementDecl2 = (XSElementDecl)xsParticleDecl2.fValue;
            if (xsElementDecl2.fScope == 1) {
                final XSElementDecl[] substitutionGroup2 = substitutionGroupHandler2.getSubstitutionGroup(xsElementDecl2);
                if (substitutionGroup2.length > 0) {
                    n4 = 101;
                    removePointlessChildren2 = new Vector(substitutionGroup2.length + 1);
                    for (int j = 0; j < substitutionGroup2.length; ++j) {
                        addElementToParticleVector(removePointlessChildren2, substitutionGroup2[j]);
                    }
                    addElementToParticleVector(removePointlessChildren2, xsElementDecl2);
                    substitutionGroupHandler2 = null;
                }
            }
        }
        switch (n3) {
            case 1: {
                switch (n4) {
                    case 1: {
                        checkNameAndTypeOK((XSElementDecl)xsParticleDecl.fValue, fMinOccurs, fMaxOccurs, (XSElementDecl)xsParticleDecl2.fValue, fMinOccurs2, fMaxOccurs2);
                        return;
                    }
                    case 2: {
                        checkNSCompat((XSElementDecl)xsParticleDecl.fValue, fMinOccurs, fMaxOccurs, (XSWildcardDecl)xsParticleDecl2.fValue, fMinOccurs2, fMaxOccurs2, b);
                        return;
                    }
                    case 101: {
                        final Vector<XSParticleDecl> vector = new Vector<XSParticleDecl>();
                        vector.addElement(xsParticleDecl);
                        checkRecurseLax(vector, 1, 1, substitutionGroupHandler, removePointlessChildren2, fMinOccurs2, fMaxOccurs2, substitutionGroupHandler2);
                        return;
                    }
                    case 102:
                    case 103: {
                        final Vector<XSParticleDecl> vector2 = new Vector<XSParticleDecl>();
                        vector2.addElement(xsParticleDecl);
                        checkRecurse(vector2, 1, 1, substitutionGroupHandler, removePointlessChildren2, fMinOccurs2, fMaxOccurs2, substitutionGroupHandler2);
                        return;
                    }
                    default: {
                        throw new XMLSchemaException("Internal-Error", new Object[] { "in particleValidRestriction" });
                    }
                }
                break;
            }
            case 2: {
                switch (n4) {
                    case 2: {
                        checkNSSubset((XSWildcardDecl)xsParticleDecl.fValue, fMinOccurs, fMaxOccurs, (XSWildcardDecl)xsParticleDecl2.fValue, fMinOccurs2, fMaxOccurs2);
                        return;
                    }
                    case 1:
                    case 101:
                    case 102:
                    case 103: {
                        throw new XMLSchemaException("cos-particle-restrict.2", new Object[] { "any:choice,sequence,all,elt" });
                    }
                    default: {
                        throw new XMLSchemaException("Internal-Error", new Object[] { "in particleValidRestriction" });
                    }
                }
                break;
            }
            case 103: {
                switch (n4) {
                    case 2: {
                        if (n == -2) {
                            n = xsParticleDecl.minEffectiveTotalRange();
                        }
                        if (n2 == -2) {
                            n2 = xsParticleDecl.maxEffectiveTotalRange();
                        }
                        checkNSRecurseCheckCardinality(removePointlessChildren, n, n2, substitutionGroupHandler, xsParticleDecl2, fMinOccurs2, fMaxOccurs2, b);
                        return;
                    }
                    case 103: {
                        checkRecurse(removePointlessChildren, fMinOccurs, fMaxOccurs, substitutionGroupHandler, removePointlessChildren2, fMinOccurs2, fMaxOccurs2, substitutionGroupHandler2);
                        return;
                    }
                    case 1:
                    case 101:
                    case 102: {
                        throw new XMLSchemaException("cos-particle-restrict.2", new Object[] { "all:choice,sequence,elt" });
                    }
                    default: {
                        throw new XMLSchemaException("Internal-Error", new Object[] { "in particleValidRestriction" });
                    }
                }
                break;
            }
            case 101: {
                switch (n4) {
                    case 2: {
                        if (n == -2) {
                            n = xsParticleDecl.minEffectiveTotalRange();
                        }
                        if (n2 == -2) {
                            n2 = xsParticleDecl.maxEffectiveTotalRange();
                        }
                        checkNSRecurseCheckCardinality(removePointlessChildren, n, n2, substitutionGroupHandler, xsParticleDecl2, fMinOccurs2, fMaxOccurs2, b);
                        return;
                    }
                    case 101: {
                        checkRecurseLax(removePointlessChildren, fMinOccurs, fMaxOccurs, substitutionGroupHandler, removePointlessChildren2, fMinOccurs2, fMaxOccurs2, substitutionGroupHandler2);
                        return;
                    }
                    case 1:
                    case 102:
                    case 103: {
                        throw new XMLSchemaException("cos-particle-restrict.2", new Object[] { "choice:all,sequence,elt" });
                    }
                    default: {
                        throw new XMLSchemaException("Internal-Error", new Object[] { "in particleValidRestriction" });
                    }
                }
                break;
            }
            case 102: {
                switch (n4) {
                    case 2: {
                        if (n == -2) {
                            n = xsParticleDecl.minEffectiveTotalRange();
                        }
                        if (n2 == -2) {
                            n2 = xsParticleDecl.maxEffectiveTotalRange();
                        }
                        checkNSRecurseCheckCardinality(removePointlessChildren, n, n2, substitutionGroupHandler, xsParticleDecl2, fMinOccurs2, fMaxOccurs2, b);
                        return;
                    }
                    case 103: {
                        checkRecurseUnordered(removePointlessChildren, fMinOccurs, fMaxOccurs, substitutionGroupHandler, removePointlessChildren2, fMinOccurs2, fMaxOccurs2, substitutionGroupHandler2);
                        return;
                    }
                    case 102: {
                        checkRecurse(removePointlessChildren, fMinOccurs, fMaxOccurs, substitutionGroupHandler, removePointlessChildren2, fMinOccurs2, fMaxOccurs2, substitutionGroupHandler2);
                        return;
                    }
                    case 101: {
                        checkMapAndSum(removePointlessChildren, fMinOccurs * removePointlessChildren.size(), (fMaxOccurs == -1) ? fMaxOccurs : (fMaxOccurs * removePointlessChildren.size()), substitutionGroupHandler, removePointlessChildren2, fMinOccurs2, fMaxOccurs2, substitutionGroupHandler2);
                        return;
                    }
                    case 1: {
                        throw new XMLSchemaException("cos-particle-restrict.2", new Object[] { "seq:elt" });
                    }
                    default: {
                        throw new XMLSchemaException("Internal-Error", new Object[] { "in particleValidRestriction" });
                    }
                }
                break;
            }
            default: {}
        }
    }
    
    private static void addElementToParticleVector(final Vector vector, final XSElementDecl fValue) {
        final XSParticleDecl xsParticleDecl = new XSParticleDecl();
        xsParticleDecl.fValue = fValue;
        xsParticleDecl.fType = 1;
        vector.addElement(xsParticleDecl);
    }
    
    private static XSParticleDecl getNonUnaryGroup(final XSParticleDecl xsParticleDecl) {
        if (xsParticleDecl.fType == 1 || xsParticleDecl.fType == 2) {
            return xsParticleDecl;
        }
        if (xsParticleDecl.fMinOccurs == 1 && xsParticleDecl.fMaxOccurs == 1 && xsParticleDecl.fValue != null && ((XSModelGroupImpl)xsParticleDecl.fValue).fParticleCount == 1) {
            return getNonUnaryGroup(((XSModelGroupImpl)xsParticleDecl.fValue).fParticles[0]);
        }
        return xsParticleDecl;
    }
    
    private static Vector removePointlessChildren(final XSParticleDecl xsParticleDecl) {
        if (xsParticleDecl.fType == 1 || xsParticleDecl.fType == 2) {
            return null;
        }
        final Vector vector = new Vector();
        final XSModelGroupImpl xsModelGroupImpl = (XSModelGroupImpl)xsParticleDecl.fValue;
        for (int i = 0; i < xsModelGroupImpl.fParticleCount; ++i) {
            gatherChildren(xsModelGroupImpl.fCompositor, xsModelGroupImpl.fParticles[i], vector);
        }
        return vector;
    }
    
    private static void gatherChildren(final int n, final XSParticleDecl xsParticleDecl, final Vector vector) {
        final int fMinOccurs = xsParticleDecl.fMinOccurs;
        final int fMaxOccurs = xsParticleDecl.fMaxOccurs;
        short n2 = xsParticleDecl.fType;
        if (n2 == 3) {
            n2 = ((XSModelGroupImpl)xsParticleDecl.fValue).fCompositor;
        }
        if (n2 == 1 || n2 == 2) {
            vector.addElement(xsParticleDecl);
            return;
        }
        if (fMinOccurs != 1 || fMaxOccurs != 1) {
            vector.addElement(xsParticleDecl);
        }
        else if (n == n2) {
            final XSModelGroupImpl xsModelGroupImpl = (XSModelGroupImpl)xsParticleDecl.fValue;
            for (int i = 0; i < xsModelGroupImpl.fParticleCount; ++i) {
                gatherChildren(n2, xsModelGroupImpl.fParticles[i], vector);
            }
        }
        else if (!xsParticleDecl.isEmpty()) {
            vector.addElement(xsParticleDecl);
        }
    }
    
    private static void checkNameAndTypeOK(final XSElementDecl xsElementDecl, final int n, final int n2, final XSElementDecl xsElementDecl2, final int n3, final int n4) throws XMLSchemaException {
        if (xsElementDecl.fName != xsElementDecl2.fName || xsElementDecl.fTargetNamespace != xsElementDecl2.fTargetNamespace) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.1", new Object[] { xsElementDecl.fName, xsElementDecl.fTargetNamespace, xsElementDecl2.fName, xsElementDecl2.fTargetNamespace });
        }
        if (!xsElementDecl2.getNillable() && xsElementDecl.getNillable()) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.2", new Object[] { xsElementDecl.fName });
        }
        if (!checkOccurrenceRange(n, n2, n3, n4)) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.3", new Object[] { xsElementDecl.fName, Integer.toString(n), (n2 == -1) ? "unbounded" : Integer.toString(n2), Integer.toString(n3), (n4 == -1) ? "unbounded" : Integer.toString(n4) });
        }
        if (xsElementDecl2.getConstraintType() == 2) {
            if (xsElementDecl.getConstraintType() != 2) {
                throw new XMLSchemaException("rcase-NameAndTypeOK.4.a", new Object[] { xsElementDecl.fName, xsElementDecl2.fDefault.stringValue() });
            }
            boolean b = false;
            if (xsElementDecl.fType.getTypeCategory() == 16 || ((XSComplexTypeDecl)xsElementDecl.fType).fContentType == 1) {
                b = true;
            }
            if ((!b && !xsElementDecl2.fDefault.normalizedValue.equals(xsElementDecl.fDefault.normalizedValue)) || (b && !xsElementDecl2.fDefault.actualValue.equals(xsElementDecl.fDefault.actualValue))) {
                throw new XMLSchemaException("rcase-NameAndTypeOK.4.b", new Object[] { xsElementDecl.fName, xsElementDecl.fDefault.stringValue(), xsElementDecl2.fDefault.stringValue() });
            }
        }
        checkIDConstraintRestriction(xsElementDecl, xsElementDecl2);
        final short fBlock = xsElementDecl.fBlock;
        final short fBlock2 = xsElementDecl2.fBlock;
        if ((fBlock & fBlock2) != fBlock2 || (fBlock == 0 && fBlock2 != 0)) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.6", new Object[] { xsElementDecl.fName });
        }
        if (!checkTypeDerivationOk(xsElementDecl.fType, xsElementDecl2.fType, (short)25)) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.7", new Object[] { xsElementDecl.fName, xsElementDecl.fType.getName(), xsElementDecl2.fType.getName() });
        }
    }
    
    private static void checkIDConstraintRestriction(final XSElementDecl xsElementDecl, final XSElementDecl xsElementDecl2) throws XMLSchemaException {
    }
    
    private static boolean checkOccurrenceRange(final int n, final int n2, final int n3, final int n4) {
        return n >= n3 && (n4 == -1 || (n2 != -1 && n2 <= n4));
    }
    
    private static void checkNSCompat(final XSElementDecl xsElementDecl, final int n, final int n2, final XSWildcardDecl xsWildcardDecl, final int n3, final int n4, final boolean b) throws XMLSchemaException {
        if (b && !checkOccurrenceRange(n, n2, n3, n4)) {
            throw new XMLSchemaException("rcase-NSCompat.2", new Object[] { xsElementDecl.fName, Integer.toString(n), (n2 == -1) ? "unbounded" : Integer.toString(n2), Integer.toString(n3), (n4 == -1) ? "unbounded" : Integer.toString(n4) });
        }
        if (!xsWildcardDecl.allowNamespace(xsElementDecl.fTargetNamespace)) {
            throw new XMLSchemaException("rcase-NSCompat.1", new Object[] { xsElementDecl.fName, xsElementDecl.fTargetNamespace });
        }
    }
    
    private static void checkNSSubset(final XSWildcardDecl xsWildcardDecl, final int n, final int n2, final XSWildcardDecl xsWildcardDecl2, final int n3, final int n4) throws XMLSchemaException {
        if (!checkOccurrenceRange(n, n2, n3, n4)) {
            throw new XMLSchemaException("rcase-NSSubset.2", new Object[] { Integer.toString(n), (n2 == -1) ? "unbounded" : Integer.toString(n2), Integer.toString(n3), (n4 == -1) ? "unbounded" : Integer.toString(n4) });
        }
        if (!xsWildcardDecl.isSubsetOf(xsWildcardDecl2)) {
            throw new XMLSchemaException("rcase-NSSubset.1", (Object[])null);
        }
        if (xsWildcardDecl.weakerProcessContents(xsWildcardDecl2)) {
            throw new XMLSchemaException("rcase-NSSubset.3", new Object[] { xsWildcardDecl.getProcessContentsAsString(), xsWildcardDecl2.getProcessContentsAsString() });
        }
    }
    
    private static void checkNSRecurseCheckCardinality(final Vector vector, final int n, final int n2, final SubstitutionGroupHandler substitutionGroupHandler, final XSParticleDecl xsParticleDecl, final int n3, final int n4, final boolean b) throws XMLSchemaException {
        if (b && !checkOccurrenceRange(n, n2, n3, n4)) {
            throw new XMLSchemaException("rcase-NSRecurseCheckCardinality.2", new Object[] { Integer.toString(n), (n2 == -1) ? "unbounded" : Integer.toString(n2), Integer.toString(n3), (n4 == -1) ? "unbounded" : Integer.toString(n4) });
        }
        final int size = vector.size();
        try {
            for (int i = 0; i < size; ++i) {
                particleValidRestriction(vector.elementAt(i), substitutionGroupHandler, xsParticleDecl, null, false);
            }
        }
        catch (XMLSchemaException ex) {
            throw new XMLSchemaException("rcase-NSRecurseCheckCardinality.1", (Object[])null);
        }
    }
    
    private static void checkRecurse(final Vector vector, final int n, final int n2, final SubstitutionGroupHandler substitutionGroupHandler, final Vector vector2, final int n3, final int n4, final SubstitutionGroupHandler substitutionGroupHandler2) throws XMLSchemaException {
        if (!checkOccurrenceRange(n, n2, n3, n4)) {
            throw new XMLSchemaException("rcase-Recurse.1", new Object[] { Integer.toString(n), (n2 == -1) ? "unbounded" : Integer.toString(n2), Integer.toString(n3), (n4 == -1) ? "unbounded" : Integer.toString(n4) });
        }
        final int size = vector.size();
        final int size2 = vector2.size();
        int n5 = 0;
        int i = 0;
    Label_0190:
        while (i < size) {
            final XSParticleDecl xsParticleDecl = vector.elementAt(i);
            int j = n5;
            while (j < size2) {
                final XSParticleDecl xsParticleDecl2 = vector2.elementAt(j);
                ++n5;
                Label_0187: {
                    try {
                        particleValidRestriction(xsParticleDecl, substitutionGroupHandler, xsParticleDecl2, substitutionGroupHandler2);
                        break Label_0187;
                    }
                    catch (XMLSchemaException ex) {
                        if (!xsParticleDecl2.emptiable()) {
                            throw new XMLSchemaException("rcase-Recurse.2", (Object[])null);
                        }
                        ++j;
                    }
                    continue;
                }
                ++i;
                continue Label_0190;
            }
            throw new XMLSchemaException("rcase-Recurse.2", (Object[])null);
        }
        for (int k = n5; k < size2; ++k) {
            if (!vector2.elementAt(k).emptiable()) {
                throw new XMLSchemaException("rcase-Recurse.2", (Object[])null);
            }
        }
    }
    
    private static void checkRecurseUnordered(final Vector vector, final int n, final int n2, final SubstitutionGroupHandler substitutionGroupHandler, final Vector vector2, final int n3, final int n4, final SubstitutionGroupHandler substitutionGroupHandler2) throws XMLSchemaException {
        if (!checkOccurrenceRange(n, n2, n3, n4)) {
            throw new XMLSchemaException("rcase-RecurseUnordered.1", new Object[] { Integer.toString(n), (n2 == -1) ? "unbounded" : Integer.toString(n2), Integer.toString(n3), (n4 == -1) ? "unbounded" : Integer.toString(n4) });
        }
        final int size = vector.size();
        final int size2 = vector2.size();
        final boolean[] array = new boolean[size2];
        int i = 0;
    Label_0195:
        while (i < size) {
            final XSParticleDecl xsParticleDecl = vector.elementAt(i);
            int j = 0;
            while (j < size2) {
                final XSParticleDecl xsParticleDecl2 = vector2.elementAt(j);
                Label_0192: {
                    try {
                        particleValidRestriction(xsParticleDecl, substitutionGroupHandler, xsParticleDecl2, substitutionGroupHandler2);
                        if (array[j]) {
                            throw new XMLSchemaException("rcase-RecurseUnordered.2", (Object[])null);
                        }
                        array[j] = true;
                        break Label_0192;
                    }
                    catch (XMLSchemaException ex) {
                        ++j;
                    }
                    continue;
                }
                ++i;
                continue Label_0195;
            }
            throw new XMLSchemaException("rcase-RecurseUnordered.2", (Object[])null);
        }
        for (int k = 0; k < size2; ++k) {
            final XSParticleDecl xsParticleDecl3 = vector2.elementAt(k);
            if (!array[k] && !xsParticleDecl3.emptiable()) {
                throw new XMLSchemaException("rcase-RecurseUnordered.2", (Object[])null);
            }
        }
    }
    
    private static void checkRecurseLax(final Vector vector, final int n, final int n2, final SubstitutionGroupHandler substitutionGroupHandler, final Vector vector2, final int n3, final int n4, final SubstitutionGroupHandler substitutionGroupHandler2) throws XMLSchemaException {
        if (!checkOccurrenceRange(n, n2, n3, n4)) {
            throw new XMLSchemaException("rcase-RecurseLax.1", new Object[] { Integer.toString(n), (n2 == -1) ? "unbounded" : Integer.toString(n2), Integer.toString(n3), (n4 == -1) ? "unbounded" : Integer.toString(n4) });
        }
        final int size = vector.size();
        final int size2 = vector2.size();
        int n5 = 0;
        int i = 0;
    Label_0171:
        while (i < size) {
            final XSParticleDecl xsParticleDecl = vector.elementAt(i);
            int j = n5;
            while (j < size2) {
                final XSParticleDecl xsParticleDecl2 = vector2.elementAt(j);
                ++n5;
                Label_0168: {
                    try {
                        particleValidRestriction(xsParticleDecl, substitutionGroupHandler, xsParticleDecl2, substitutionGroupHandler2);
                        break Label_0168;
                    }
                    catch (XMLSchemaException ex) {
                        ++j;
                    }
                    continue;
                }
                ++i;
                continue Label_0171;
            }
            throw new XMLSchemaException("rcase-RecurseLax.2", (Object[])null);
        }
    }
    
    private static void checkMapAndSum(final Vector vector, final int n, final int n2, final SubstitutionGroupHandler substitutionGroupHandler, final Vector vector2, final int n3, final int n4, final SubstitutionGroupHandler substitutionGroupHandler2) throws XMLSchemaException {
        if (!checkOccurrenceRange(n, n2, n3, n4)) {
            throw new XMLSchemaException("rcase-MapAndSum.2", new Object[] { Integer.toString(n), (n2 == -1) ? "unbounded" : Integer.toString(n2), Integer.toString(n3), (n4 == -1) ? "unbounded" : Integer.toString(n4) });
        }
        final int size = vector.size();
        final int size2 = vector2.size();
        int i = 0;
    Label_0164:
        while (i < size) {
            final XSParticleDecl xsParticleDecl = vector.elementAt(i);
            int j = 0;
            while (j < size2) {
                final XSParticleDecl xsParticleDecl2 = vector2.elementAt(j);
                Label_0161: {
                    try {
                        particleValidRestriction(xsParticleDecl, substitutionGroupHandler, xsParticleDecl2, substitutionGroupHandler2);
                        break Label_0161;
                    }
                    catch (XMLSchemaException ex) {
                        ++j;
                    }
                    continue;
                }
                ++i;
                continue Label_0164;
            }
            throw new XMLSchemaException("rcase-MapAndSum.1", (Object[])null);
        }
    }
    
    public static boolean overlapUPA(final XSElementDecl xsElementDecl, final XSElementDecl xsElementDecl2, final SubstitutionGroupHandler substitutionGroupHandler) {
        if (xsElementDecl.fName == xsElementDecl2.fName && xsElementDecl.fTargetNamespace == xsElementDecl2.fTargetNamespace) {
            return true;
        }
        final XSElementDecl[] substitutionGroup = substitutionGroupHandler.getSubstitutionGroup(xsElementDecl);
        for (int i = substitutionGroup.length - 1; i >= 0; --i) {
            if (substitutionGroup[i].fName == xsElementDecl2.fName && substitutionGroup[i].fTargetNamespace == xsElementDecl2.fTargetNamespace) {
                return true;
            }
        }
        final XSElementDecl[] substitutionGroup2 = substitutionGroupHandler.getSubstitutionGroup(xsElementDecl2);
        for (int j = substitutionGroup2.length - 1; j >= 0; --j) {
            if (substitutionGroup2[j].fName == xsElementDecl.fName && substitutionGroup2[j].fTargetNamespace == xsElementDecl.fTargetNamespace) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean overlapUPA(final XSElementDecl xsElementDecl, final XSWildcardDecl xsWildcardDecl, final SubstitutionGroupHandler substitutionGroupHandler) {
        if (xsWildcardDecl.allowNamespace(xsElementDecl.fTargetNamespace)) {
            return true;
        }
        final XSElementDecl[] substitutionGroup = substitutionGroupHandler.getSubstitutionGroup(xsElementDecl);
        for (int i = substitutionGroup.length - 1; i >= 0; --i) {
            if (xsWildcardDecl.allowNamespace(substitutionGroup[i].fTargetNamespace)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean overlapUPA(final XSWildcardDecl xsWildcardDecl, final XSWildcardDecl xsWildcardDecl2) {
        final XSWildcardDecl performIntersectionWith = xsWildcardDecl.performIntersectionWith(xsWildcardDecl2, xsWildcardDecl.fProcessContents);
        return performIntersectionWith == null || performIntersectionWith.fType != 3 || performIntersectionWith.fNamespaceList.length != 0;
    }
    
    public static boolean overlapUPA(final Object o, final Object o2, final SubstitutionGroupHandler substitutionGroupHandler) {
        if (o instanceof XSElementDecl) {
            if (o2 instanceof XSElementDecl) {
                return overlapUPA((XSElementDecl)o, (XSElementDecl)o2, substitutionGroupHandler);
            }
            return overlapUPA((XSElementDecl)o, (XSWildcardDecl)o2, substitutionGroupHandler);
        }
        else {
            if (o2 instanceof XSElementDecl) {
                return overlapUPA((XSElementDecl)o2, (XSWildcardDecl)o, substitutionGroupHandler);
            }
            return overlapUPA((XSWildcardDecl)o, (XSWildcardDecl)o2);
        }
    }
    
    static {
        STRING_TYPE = (XSSimpleType)SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl("string");
    }
}
