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
import org.apache.xerces.impl.xs.psvi.XSObjectList;
import org.apache.xerces.impl.dv.XSSimpleType;

public class XSConstraints
{
    static final int OCCURRENCE_UNKNOWN = -2;
    static final XSSimpleType STRING_TYPE;
    
    public static boolean checkTypeDerivationOk(final XSTypeDecl derived, XSTypeDecl base, final short block) {
        if (derived == SchemaGrammar.fAnyType) {
            return derived == base;
        }
        if (derived == SchemaGrammar.fAnySimpleType) {
            return base == SchemaGrammar.fAnyType || base == SchemaGrammar.fAnySimpleType;
        }
        if (derived.getTypeCategory() == 14) {
            if (base.getTypeCategory() == 13) {
                if (base != SchemaGrammar.fAnyType) {
                    return false;
                }
                base = SchemaGrammar.fAnySimpleType;
            }
            return checkSimpleDerivation((XSSimpleType)derived, (XSSimpleType)base, block);
        }
        return checkComplexDerivation((XSComplexTypeDecl)derived, base, block);
    }
    
    public static boolean checkSimpleDerivationOk(final XSSimpleType derived, XSTypeDecl base, final short block) {
        if (derived == SchemaGrammar.fAnySimpleType) {
            return base == SchemaGrammar.fAnyType || base == SchemaGrammar.fAnySimpleType;
        }
        if (base.getTypeCategory() == 13) {
            if (base != SchemaGrammar.fAnyType) {
                return false;
            }
            base = SchemaGrammar.fAnySimpleType;
        }
        return checkSimpleDerivation(derived, (XSSimpleType)base, block);
    }
    
    public static boolean checkComplexDerivationOk(final XSComplexTypeDecl derived, final XSTypeDecl base, final short block) {
        if (derived == SchemaGrammar.fAnyType) {
            return derived == base;
        }
        return checkComplexDerivation(derived, base, block);
    }
    
    private static boolean checkSimpleDerivation(final XSSimpleType derived, XSSimpleType base, final short block) {
        if (derived == base) {
            return true;
        }
        if ((block & 0x2) != 0x0 || (derived.getBaseType().getFinal() & 0x2) != 0x0) {
            return false;
        }
        final XSSimpleType directBase = (XSSimpleType)derived.getBaseType();
        if (directBase == base) {
            return true;
        }
        if (directBase != SchemaGrammar.fAnySimpleType && checkSimpleDerivation(directBase, base, block)) {
            return true;
        }
        if ((derived.getVariety() == 2 || derived.getVariety() == 3) && base == SchemaGrammar.fAnySimpleType) {
            return true;
        }
        if (base.getVariety() == 3) {
            final XSObjectList subUnionMemberDV = base.getMemberTypes();
            for (int subUnionSize = subUnionMemberDV.getLength(), i = 0; i < subUnionSize; ++i) {
                base = (XSSimpleType)subUnionMemberDV.getItem(i);
                if (checkSimpleDerivation(derived, base, block)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean checkComplexDerivation(final XSComplexTypeDecl derived, XSTypeDecl base, final short block) {
        if (derived == base) {
            return true;
        }
        if ((derived.fDerivedBy & block) != 0x0) {
            return false;
        }
        final XSTypeDecl directBase = derived.fBaseType;
        if (directBase == base) {
            return true;
        }
        if (directBase == SchemaGrammar.fAnyType || directBase == SchemaGrammar.fAnySimpleType) {
            return false;
        }
        if (directBase.getTypeCategory() == 13) {
            return checkComplexDerivation((XSComplexTypeDecl)directBase, base, block);
        }
        if (directBase.getTypeCategory() == 14) {
            if (base.getTypeCategory() == 13) {
                if (base != SchemaGrammar.fAnyType) {
                    return false;
                }
                base = SchemaGrammar.fAnySimpleType;
            }
            return checkSimpleDerivation((XSSimpleType)directBase, (XSSimpleType)base, block);
        }
        return false;
    }
    
    public static Object ElementDefaultValidImmediate(final XSTypeDecl type, final Object value, final ValidationContext context, final ValidatedInfo vinfo) {
        XSSimpleType dv = null;
        if (type.getTypeCategory() == 14) {
            dv = (XSSimpleType)type;
        }
        else {
            final XSComplexTypeDecl ctype = (XSComplexTypeDecl)type;
            if (ctype.fContentType == 1) {
                dv = ctype.fXSSimpleType;
            }
            else {
                if (ctype.fContentType != 3) {
                    return null;
                }
                if (!((XSParticleDecl)ctype.getParticle()).emptiable()) {
                    return null;
                }
            }
        }
        Object actualValue = null;
        if (dv == null) {
            dv = XSConstraints.STRING_TYPE;
        }
        try {
            if (value instanceof String) {
                actualValue = dv.validate((String)value, context, vinfo);
            }
            else {
                final ValidatedInfo info = (ValidatedInfo)value;
                dv.validate(context, info);
                actualValue = info.actualValue;
            }
        }
        catch (InvalidDatatypeValueException ide) {
            return null;
        }
        return actualValue;
    }
    
    static void reportSchemaError(final XMLErrorReporter errorReporter, final SimpleLocator loc, final String key, final Object[] args) {
        if (loc != null) {
            errorReporter.reportError(loc, "http://www.w3.org/TR/xml-schema-1", key, args, (short)1);
        }
        else {
            errorReporter.reportError("http://www.w3.org/TR/xml-schema-1", key, args, (short)1);
        }
    }
    
    public static void fullSchemaChecking(final XSGrammarBucket grammarBucket, final SubstitutionGroupHandler SGHandler, final CMBuilder cmBuilder, final XMLErrorReporter errorReporter) {
        final SchemaGrammar[] grammars = grammarBucket.getGrammars();
        for (int i = grammars.length - 1; i >= 0; --i) {
            SGHandler.addSubstitutionGroup(grammars[i].getSubstitutionGroups());
        }
        final XSParticleDecl fakeDerived = new XSParticleDecl();
        final XSParticleDecl fakeBase = new XSParticleDecl();
        fakeDerived.fType = 3;
        fakeBase.fType = 3;
        for (int g = grammars.length - 1; g >= 0; --g) {
            final XSGroupDecl[] redefinedGroups = grammars[g].getRedefinedGroupDecls();
            final SimpleLocator[] rgLocators = grammars[g].getRGLocators();
            int j = 0;
            while (j < redefinedGroups.length) {
                final XSGroupDecl derivedGrp = redefinedGroups[j++];
                final XSModelGroupImpl derivedMG = derivedGrp.fModelGroup;
                final XSGroupDecl baseGrp = redefinedGroups[j++];
                final XSModelGroupImpl baseMG = baseGrp.fModelGroup;
                if (baseMG == null) {
                    if (derivedMG == null) {
                        continue;
                    }
                    reportSchemaError(errorReporter, rgLocators[j / 2 - 1], "src-redefine.6.2.2", new Object[] { derivedGrp.fName, "rcase-Recurse.2" });
                }
                else {
                    fakeDerived.fValue = derivedMG;
                    fakeBase.fValue = baseMG;
                    try {
                        particleValidRestriction(fakeDerived, SGHandler, fakeBase, SGHandler);
                    }
                    catch (XMLSchemaException e) {
                        final String key = e.getKey();
                        reportSchemaError(errorReporter, rgLocators[j / 2 - 1], key, e.getArgs());
                        reportSchemaError(errorReporter, rgLocators[j / 2 - 1], "src-redefine.6.2.2", new Object[] { derivedGrp.fName, key });
                    }
                }
            }
        }
        final SymbolHash elemTable = new SymbolHash();
        for (int k = grammars.length - 1; k >= 0; --k) {
            int keepType = 0;
            final boolean fullChecked = grammars[k].fFullChecked;
            final XSComplexTypeDecl[] types = grammars[k].getUncheckedComplexTypeDecls();
            final SimpleLocator[] ctLocators = grammars[k].getUncheckedCTLocators();
            for (int l = types.length - 1; l >= 0; --l) {
                if (!fullChecked && types[l].fParticle != null) {
                    elemTable.clear();
                    try {
                        checkElementDeclsConsistent(types[l], types[l].fParticle, elemTable, SGHandler);
                    }
                    catch (XMLSchemaException e2) {
                        reportSchemaError(errorReporter, ctLocators[l], e2.getKey(), e2.getArgs());
                    }
                }
                if (types[l].fBaseType != null && types[l].fBaseType != SchemaGrammar.fAnyType && types[l].fDerivedBy == 2 && types[l].fBaseType instanceof XSComplexTypeDecl) {
                    final XSParticleDecl derivedParticle = types[l].fParticle;
                    final XSParticleDecl baseParticle = ((XSComplexTypeDecl)types[l].fBaseType).fParticle;
                    if (derivedParticle == null && baseParticle != null && !baseParticle.emptiable()) {
                        reportSchemaError(errorReporter, ctLocators[l], "derivation-ok-restriction.5.2", new Object[] { types[l].fName });
                    }
                    else if (derivedParticle != null && baseParticle != null) {
                        try {
                            particleValidRestriction(types[l].fParticle, SGHandler, ((XSComplexTypeDecl)types[l].fBaseType).fParticle, SGHandler);
                        }
                        catch (XMLSchemaException e3) {
                            reportSchemaError(errorReporter, ctLocators[l], e3.getKey(), e3.getArgs());
                            reportSchemaError(errorReporter, ctLocators[l], "derivation-ok-restriction.5.3", new Object[] { types[l].fName });
                        }
                    }
                }
                final XSCMValidator cm = types[l].getContentModel(cmBuilder);
                boolean further = false;
                if (cm != null) {
                    try {
                        further = cm.checkUniqueParticleAttribution(SGHandler);
                    }
                    catch (XMLSchemaException e4) {
                        reportSchemaError(errorReporter, ctLocators[l], e4.getKey(), e4.getArgs());
                    }
                }
                if (!fullChecked && further) {
                    types[keepType++] = types[l];
                }
            }
            if (!fullChecked) {
                grammars[k].setUncheckedTypeNum(keepType);
                grammars[k].fFullChecked = true;
            }
        }
    }
    
    public static void checkElementDeclsConsistent(final XSComplexTypeDecl type, final XSParticleDecl particle, final SymbolHash elemDeclHash, final SubstitutionGroupHandler sgHandler) throws XMLSchemaException {
        final int pType = particle.fType;
        if (pType == 0 || pType == 2) {
            return;
        }
        if (pType == 1) {
            final XSElementDecl elem = (XSElementDecl)particle.fValue;
            findElemInTable(type, elem, elemDeclHash);
            if (elem.fScope == 1) {
                final XSElementDecl[] subGroup = sgHandler.getSubstitutionGroup(elem);
                for (int i = 0; i < subGroup.length; ++i) {
                    findElemInTable(type, subGroup[i], elemDeclHash);
                }
            }
            return;
        }
        final XSModelGroupImpl group = (XSModelGroupImpl)particle.fValue;
        for (int j = 0; j < group.fParticleCount; ++j) {
            checkElementDeclsConsistent(type, group.fParticles[j], elemDeclHash, sgHandler);
        }
    }
    
    public static void findElemInTable(final XSComplexTypeDecl type, final XSElementDecl elem, final SymbolHash elemDeclHash) throws XMLSchemaException {
        final String name = elem.fName + "," + elem.fTargetNamespace;
        XSElementDecl existingElem = null;
        if ((existingElem = (XSElementDecl)elemDeclHash.get(name)) == null) {
            elemDeclHash.put(name, elem);
        }
        else {
            if (elem == existingElem) {
                return;
            }
            if (elem.fType != existingElem.fType) {
                throw new XMLSchemaException("cos-element-consistent", new Object[] { type.fName, name });
            }
        }
    }
    
    private static void particleValidRestriction(final XSParticleDecl dParticle, final SubstitutionGroupHandler dSGHandler, final XSParticleDecl bParticle, final SubstitutionGroupHandler bSGHandler) throws XMLSchemaException {
        particleValidRestriction(dParticle, dSGHandler, bParticle, bSGHandler, true);
    }
    
    private static void particleValidRestriction(XSParticleDecl dParticle, SubstitutionGroupHandler dSGHandler, XSParticleDecl bParticle, SubstitutionGroupHandler bSGHandler, final boolean checkWCOccurrence) throws XMLSchemaException {
        Vector dChildren = null;
        Vector bChildren = null;
        int dMinEffectiveTotalRange = -2;
        int dMaxEffectiveTotalRange = -2;
        if (dParticle.isEmpty() != bParticle.isEmpty()) {
            throw new XMLSchemaException("cos-particle-restrict", (Object[])null);
        }
        short dType = dParticle.fType;
        if (dType == 3) {
            dType = ((XSModelGroupImpl)dParticle.fValue).fCompositor;
            final XSParticleDecl dtmp = getNonUnaryGroup(dParticle);
            if (dtmp != dParticle) {
                dParticle = dtmp;
                dType = dParticle.fType;
                if (dType == 3) {
                    dType = ((XSModelGroupImpl)dParticle.fValue).fCompositor;
                }
            }
            dChildren = removePointlessChildren(dParticle);
        }
        final int dMinOccurs = dParticle.fMinOccurs;
        final int dMaxOccurs = dParticle.fMaxOccurs;
        if (dSGHandler != null && dType == 1) {
            final XSElementDecl dElement = (XSElementDecl)dParticle.fValue;
            if (dElement.fScope == 1) {
                final XSElementDecl[] subGroup = dSGHandler.getSubstitutionGroup(dElement);
                if (subGroup.length > 0) {
                    dType = 101;
                    dMinEffectiveTotalRange = dMinOccurs;
                    dMaxEffectiveTotalRange = dMaxOccurs;
                    dChildren = new Vector(subGroup.length + 1);
                    for (int i = 0; i < subGroup.length; ++i) {
                        addElementToParticleVector(dChildren, subGroup[i]);
                    }
                    addElementToParticleVector(dChildren, dElement);
                    dSGHandler = null;
                }
            }
        }
        short bType = bParticle.fType;
        if (bType == 3) {
            bType = ((XSModelGroupImpl)bParticle.fValue).fCompositor;
            final XSParticleDecl btmp = getNonUnaryGroup(bParticle);
            if (btmp != bParticle) {
                bParticle = btmp;
                bType = bParticle.fType;
                if (bType == 3) {
                    bType = ((XSModelGroupImpl)bParticle.fValue).fCompositor;
                }
            }
            bChildren = removePointlessChildren(bParticle);
        }
        final int bMinOccurs = bParticle.fMinOccurs;
        final int bMaxOccurs = bParticle.fMaxOccurs;
        if (bSGHandler != null && bType == 1) {
            final XSElementDecl bElement = (XSElementDecl)bParticle.fValue;
            if (bElement.fScope == 1) {
                final XSElementDecl[] bsubGroup = bSGHandler.getSubstitutionGroup(bElement);
                if (bsubGroup.length > 0) {
                    bType = 101;
                    bChildren = new Vector(bsubGroup.length + 1);
                    for (int j = 0; j < bsubGroup.length; ++j) {
                        addElementToParticleVector(bChildren, bsubGroup[j]);
                    }
                    addElementToParticleVector(bChildren, bElement);
                    bSGHandler = null;
                }
            }
        }
        switch (dType) {
            case 1: {
                switch (bType) {
                    case 1: {
                        checkNameAndTypeOK((XSElementDecl)dParticle.fValue, dMinOccurs, dMaxOccurs, (XSElementDecl)bParticle.fValue, bMinOccurs, bMaxOccurs);
                        return;
                    }
                    case 2: {
                        checkNSCompat((XSElementDecl)dParticle.fValue, dMinOccurs, dMaxOccurs, (XSWildcardDecl)bParticle.fValue, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                        return;
                    }
                    case 101: {
                        dChildren = new Vector();
                        dChildren.addElement(dParticle);
                        checkRecurseLax(dChildren, 1, 1, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                        return;
                    }
                    case 102:
                    case 103: {
                        dChildren = new Vector();
                        dChildren.addElement(dParticle);
                        checkRecurse(dChildren, 1, 1, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                        return;
                    }
                    default: {
                        throw new XMLSchemaException("Internal-Error", new Object[] { "in particleValidRestriction" });
                    }
                }
                break;
            }
            case 2: {
                switch (bType) {
                    case 2: {
                        checkNSSubset((XSWildcardDecl)dParticle.fValue, dMinOccurs, dMaxOccurs, (XSWildcardDecl)bParticle.fValue, bMinOccurs, bMaxOccurs);
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
                switch (bType) {
                    case 2: {
                        if (dMinEffectiveTotalRange == -2) {
                            dMinEffectiveTotalRange = dParticle.minEffectiveTotalRange();
                        }
                        if (dMaxEffectiveTotalRange == -2) {
                            dMaxEffectiveTotalRange = dParticle.maxEffectiveTotalRange();
                        }
                        checkNSRecurseCheckCardinality(dChildren, dMinEffectiveTotalRange, dMaxEffectiveTotalRange, dSGHandler, bParticle, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                        return;
                    }
                    case 103: {
                        checkRecurse(dChildren, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
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
                switch (bType) {
                    case 2: {
                        if (dMinEffectiveTotalRange == -2) {
                            dMinEffectiveTotalRange = dParticle.minEffectiveTotalRange();
                        }
                        if (dMaxEffectiveTotalRange == -2) {
                            dMaxEffectiveTotalRange = dParticle.maxEffectiveTotalRange();
                        }
                        checkNSRecurseCheckCardinality(dChildren, dMinEffectiveTotalRange, dMaxEffectiveTotalRange, dSGHandler, bParticle, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                        return;
                    }
                    case 101: {
                        checkRecurseLax(dChildren, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
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
                switch (bType) {
                    case 2: {
                        if (dMinEffectiveTotalRange == -2) {
                            dMinEffectiveTotalRange = dParticle.minEffectiveTotalRange();
                        }
                        if (dMaxEffectiveTotalRange == -2) {
                            dMaxEffectiveTotalRange = dParticle.maxEffectiveTotalRange();
                        }
                        checkNSRecurseCheckCardinality(dChildren, dMinEffectiveTotalRange, dMaxEffectiveTotalRange, dSGHandler, bParticle, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                        return;
                    }
                    case 103: {
                        checkRecurseUnordered(dChildren, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                        return;
                    }
                    case 102: {
                        checkRecurse(dChildren, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                        return;
                    }
                    case 101: {
                        final int min1 = dMinOccurs * dChildren.size();
                        final int max1 = (dMaxOccurs == -1) ? dMaxOccurs : (dMaxOccurs * dChildren.size());
                        checkMapAndSum(dChildren, min1, max1, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
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
    
    private static void addElementToParticleVector(final Vector v, final XSElementDecl d) {
        final XSParticleDecl p = new XSParticleDecl();
        p.fValue = d;
        p.fType = 1;
        v.addElement(p);
    }
    
    private static XSParticleDecl getNonUnaryGroup(final XSParticleDecl p) {
        if (p.fType == 1 || p.fType == 2) {
            return p;
        }
        if (p.fMinOccurs == 1 && p.fMaxOccurs == 1 && p.fValue != null && ((XSModelGroupImpl)p.fValue).fParticleCount == 1) {
            return getNonUnaryGroup(((XSModelGroupImpl)p.fValue).fParticles[0]);
        }
        return p;
    }
    
    private static Vector removePointlessChildren(final XSParticleDecl p) {
        if (p.fType == 1 || p.fType == 2 || p.fType == 0) {
            return null;
        }
        final Vector children = new Vector();
        final XSModelGroupImpl group = (XSModelGroupImpl)p.fValue;
        for (int i = 0; i < group.fParticleCount; ++i) {
            gatherChildren(group.fCompositor, group.fParticles[i], children);
        }
        return children;
    }
    
    private static void gatherChildren(final int parentType, final XSParticleDecl p, final Vector children) {
        final int min = p.fMinOccurs;
        final int max = p.fMaxOccurs;
        int type = p.fType;
        if (type == 3) {
            type = ((XSModelGroupImpl)p.fValue).fCompositor;
        }
        if (type == 0) {
            return;
        }
        if (type == 1 || type == 2) {
            children.addElement(p);
            return;
        }
        if (min != 1 || max != 1) {
            children.addElement(p);
        }
        else if (parentType == type) {
            final XSModelGroupImpl group = (XSModelGroupImpl)p.fValue;
            for (int i = 0; i < group.fParticleCount; ++i) {
                gatherChildren(type, group.fParticles[i], children);
            }
        }
        else if (!p.isEmpty()) {
            children.addElement(p);
        }
    }
    
    private static void checkNameAndTypeOK(final XSElementDecl dElement, final int dMin, final int dMax, final XSElementDecl bElement, final int bMin, final int bMax) throws XMLSchemaException {
        if (dElement.fName != bElement.fName || dElement.fTargetNamespace != bElement.fTargetNamespace) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.1", new Object[] { dElement.fName, dElement.fTargetNamespace, bElement.fName, bElement.fTargetNamespace });
        }
        if (!bElement.getIsNillable() && dElement.getIsNillable()) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.2", new Object[] { dElement.fName });
        }
        if (!checkOccurrenceRange(dMin, dMax, bMin, bMax)) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.3", new Object[] { dElement.fName });
        }
        if (bElement.getConstraintType() == 2) {
            if (dElement.getConstraintType() != 2) {
                throw new XMLSchemaException("rcase-NameAndTypeOK.4", new Object[] { dElement.fName });
            }
            boolean isSimple = false;
            if (dElement.fType.getTypeCategory() == 14 || ((XSComplexTypeDecl)dElement.fType).fContentType == 1) {
                isSimple = true;
            }
            if ((!isSimple && !bElement.fDefault.normalizedValue.equals(dElement.fDefault.normalizedValue)) || (isSimple && !bElement.fDefault.actualValue.equals(dElement.fDefault.actualValue))) {
                throw new XMLSchemaException("rcase-NameAndTypeOK.4", new Object[] { dElement.fName });
            }
        }
        checkIDConstraintRestriction(dElement, bElement);
        final int blockSet1 = dElement.fBlock;
        final int blockSet2 = bElement.fBlock;
        if ((blockSet1 & blockSet2) != blockSet2 || (blockSet1 == 0 && blockSet2 != 0)) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.6", new Object[] { dElement.fName });
        }
        if (!checkTypeDerivationOk(dElement.fType, bElement.fType, (short)25)) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.7", new Object[] { dElement.fName });
        }
    }
    
    private static void checkIDConstraintRestriction(final XSElementDecl derivedElemDecl, final XSElementDecl baseElemDecl) throws XMLSchemaException {
    }
    
    private static boolean checkOccurrenceRange(final int min1, final int max1, final int min2, final int max2) {
        return min1 >= min2 && (max2 == -1 || (max1 != -1 && max1 <= max2));
    }
    
    private static void checkNSCompat(final XSElementDecl elem, final int min1, final int max1, final XSWildcardDecl wildcard, final int min2, final int max2, final boolean checkWCOccurrence) throws XMLSchemaException {
        if (checkWCOccurrence && !checkOccurrenceRange(min1, max1, min2, max2)) {
            throw new XMLSchemaException("rcase-NSCompat.2", new Object[] { elem.fName });
        }
        if (!wildcard.allowNamespace(elem.fTargetNamespace)) {
            throw new XMLSchemaException("rcase-NSCompat.1", new Object[] { elem.fName, elem.fTargetNamespace });
        }
    }
    
    private static void checkNSSubset(final XSWildcardDecl dWildcard, final int min1, final int max1, final XSWildcardDecl bWildcard, final int min2, final int max2) throws XMLSchemaException {
        if (!checkOccurrenceRange(min1, max1, min2, max2)) {
            throw new XMLSchemaException("rcase-NSSubset.2", (Object[])null);
        }
        if (!dWildcard.isSubsetOf(bWildcard)) {
            throw new XMLSchemaException("rcase-NSSubset.1", (Object[])null);
        }
    }
    
    private static void checkNSRecurseCheckCardinality(final Vector children, final int min1, final int max1, final SubstitutionGroupHandler dSGHandler, final XSParticleDecl wildcard, final int min2, final int max2, final boolean checkWCOccurrence) throws XMLSchemaException {
        if (checkWCOccurrence && !checkOccurrenceRange(min1, max1, min2, max2)) {
            throw new XMLSchemaException("rcase-NSRecurseCheckCardinality.2", (Object[])null);
        }
        final int count = children.size();
        try {
            for (int i = 0; i < count; ++i) {
                final XSParticleDecl particle1 = children.elementAt(i);
                particleValidRestriction(particle1, dSGHandler, wildcard, null, false);
            }
        }
        catch (XMLSchemaException e) {
            throw new XMLSchemaException("rcase-NSRecurseCheckCardinality.1", (Object[])null);
        }
    }
    
    private static void checkRecurse(final Vector dChildren, final int min1, final int max1, final SubstitutionGroupHandler dSGHandler, final Vector bChildren, final int min2, final int max2, final SubstitutionGroupHandler bSGHandler) throws XMLSchemaException {
        if (!checkOccurrenceRange(min1, max1, min2, max2)) {
            throw new XMLSchemaException("rcase-Recurse.1", (Object[])null);
        }
        final int count1 = dChildren.size();
        final int count2 = bChildren.size();
        int current = 0;
        int i = 0;
    Label_0136:
        while (i < count1) {
            final XSParticleDecl particle1 = dChildren.elementAt(i);
            int j = current;
            while (j < count2) {
                final XSParticleDecl particle2 = bChildren.elementAt(j);
                ++current;
                Label_0133: {
                    try {
                        particleValidRestriction(particle1, dSGHandler, particle2, bSGHandler);
                        break Label_0133;
                    }
                    catch (XMLSchemaException e) {
                        if (!particle2.emptiable()) {
                            throw new XMLSchemaException("rcase-Recurse.2", (Object[])null);
                        }
                        ++j;
                    }
                    continue;
                }
                ++i;
                continue Label_0136;
            }
            throw new XMLSchemaException("rcase-Recurse.2", (Object[])null);
        }
        for (int k = current; k < count2; ++k) {
            final XSParticleDecl particle3 = bChildren.elementAt(k);
            if (!particle3.emptiable()) {
                throw new XMLSchemaException("rcase-Recurse.2", (Object[])null);
            }
        }
    }
    
    private static void checkRecurseUnordered(final Vector dChildren, final int min1, final int max1, final SubstitutionGroupHandler dSGHandler, final Vector bChildren, final int min2, final int max2, final SubstitutionGroupHandler bSGHandler) throws XMLSchemaException {
        if (!checkOccurrenceRange(min1, max1, min2, max2)) {
            throw new XMLSchemaException("rcase-RecurseUnordered.1", (Object[])null);
        }
        final int count1 = dChildren.size();
        final int count2 = bChildren.size();
        final boolean[] foundIt = new boolean[count2];
        int i = 0;
    Label_0141:
        while (i < count1) {
            final XSParticleDecl particle1 = dChildren.elementAt(i);
            int j = 0;
            while (j < count2) {
                final XSParticleDecl particle2 = bChildren.elementAt(j);
                Label_0138: {
                    try {
                        particleValidRestriction(particle1, dSGHandler, particle2, bSGHandler);
                        if (foundIt[j]) {
                            throw new XMLSchemaException("rcase-RecurseUnordered.2", (Object[])null);
                        }
                        foundIt[j] = true;
                        break Label_0138;
                    }
                    catch (XMLSchemaException e) {
                        ++j;
                    }
                    continue;
                }
                ++i;
                continue Label_0141;
            }
            throw new XMLSchemaException("rcase-RecurseUnordered.2", (Object[])null);
        }
        for (int k = 0; k < count2; ++k) {
            final XSParticleDecl particle3 = bChildren.elementAt(k);
            if (!foundIt[k] && !particle3.emptiable()) {
                throw new XMLSchemaException("rcase-RecurseUnordered.2", (Object[])null);
            }
        }
    }
    
    private static void checkRecurseLax(final Vector dChildren, final int min1, final int max1, final SubstitutionGroupHandler dSGHandler, final Vector bChildren, final int min2, final int max2, final SubstitutionGroupHandler bSGHandler) throws XMLSchemaException {
        if (!checkOccurrenceRange(min1, max1, min2, max2)) {
            throw new XMLSchemaException("rcase-RecurseLax.1", (Object[])null);
        }
        final int count1 = dChildren.size();
        final int count2 = bChildren.size();
        int current = 0;
        int i = 0;
    Label_0117:
        while (i < count1) {
            final XSParticleDecl particle1 = dChildren.elementAt(i);
            int j = current;
            while (j < count2) {
                final XSParticleDecl particle2 = bChildren.elementAt(j);
                ++current;
                Label_0114: {
                    try {
                        particleValidRestriction(particle1, dSGHandler, particle2, bSGHandler);
                        break Label_0114;
                    }
                    catch (XMLSchemaException e) {
                        ++j;
                    }
                    continue;
                }
                ++i;
                continue Label_0117;
            }
            throw new XMLSchemaException("rcase-RecurseLax.2", (Object[])null);
        }
    }
    
    private static void checkMapAndSum(final Vector dChildren, final int min1, final int max1, final SubstitutionGroupHandler dSGHandler, final Vector bChildren, final int min2, final int max2, final SubstitutionGroupHandler bSGHandler) throws XMLSchemaException {
        if (!checkOccurrenceRange(min1, max1, min2, max2)) {
            throw new XMLSchemaException("rcase-MapAndSum.2", (Object[])null);
        }
        final int count1 = dChildren.size();
        final int count2 = bChildren.size();
        int i = 0;
    Label_0110:
        while (i < count1) {
            final XSParticleDecl particle1 = dChildren.elementAt(i);
            int j = 0;
            while (j < count2) {
                final XSParticleDecl particle2 = bChildren.elementAt(j);
                Label_0107: {
                    try {
                        particleValidRestriction(particle1, dSGHandler, particle2, bSGHandler);
                        break Label_0107;
                    }
                    catch (XMLSchemaException e) {
                        ++j;
                    }
                    continue;
                }
                ++i;
                continue Label_0110;
            }
            throw new XMLSchemaException("rcase-MapAndSum.1", (Object[])null);
        }
    }
    
    public static boolean overlapUPA(final XSElementDecl element1, final XSElementDecl element2, final SubstitutionGroupHandler sgHandler) {
        if (element1.fName == element2.fName && element1.fTargetNamespace == element2.fTargetNamespace) {
            return true;
        }
        XSElementDecl[] subGroup = sgHandler.getSubstitutionGroup(element1);
        for (int i = subGroup.length - 1; i >= 0; --i) {
            if (subGroup[i].fName == element2.fName && subGroup[i].fTargetNamespace == element2.fTargetNamespace) {
                return true;
            }
        }
        subGroup = sgHandler.getSubstitutionGroup(element2);
        for (int j = subGroup.length - 1; j >= 0; --j) {
            if (subGroup[j].fName == element1.fName && subGroup[j].fTargetNamespace == element1.fTargetNamespace) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean overlapUPA(final XSElementDecl element, final XSWildcardDecl wildcard, final SubstitutionGroupHandler sgHandler) {
        if (wildcard.allowNamespace(element.fTargetNamespace)) {
            return true;
        }
        final XSElementDecl[] subGroup = sgHandler.getSubstitutionGroup(element);
        for (int i = subGroup.length - 1; i >= 0; --i) {
            if (wildcard.allowNamespace(subGroup[i].fTargetNamespace)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean overlapUPA(final XSWildcardDecl wildcard1, final XSWildcardDecl wildcard2) {
        final XSWildcardDecl intersect = wildcard1.performIntersectionWith(wildcard2, wildcard1.fProcessContents);
        return intersect == null || intersect.fType != 3 || intersect.fNamespaceList.length != 0;
    }
    
    public static boolean overlapUPA(final Object decl1, final Object decl2, final SubstitutionGroupHandler sgHandler) {
        if (decl1 instanceof XSElementDecl) {
            if (decl2 instanceof XSElementDecl) {
                return overlapUPA((XSElementDecl)decl1, (XSElementDecl)decl2, sgHandler);
            }
            return overlapUPA((XSElementDecl)decl1, (XSWildcardDecl)decl2, sgHandler);
        }
        else {
            if (decl2 instanceof XSElementDecl) {
                return overlapUPA((XSElementDecl)decl2, (XSWildcardDecl)decl1, sgHandler);
            }
            return overlapUPA((XSWildcardDecl)decl1, (XSWildcardDecl)decl2);
        }
    }
    
    static {
        STRING_TYPE = (XSSimpleType)SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl("string");
    }
}
