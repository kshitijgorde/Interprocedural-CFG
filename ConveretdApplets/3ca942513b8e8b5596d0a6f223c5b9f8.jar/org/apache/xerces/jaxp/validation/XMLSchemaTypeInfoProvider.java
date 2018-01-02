// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.xs.XSTypeDefinition;
import java.util.Locale;
import org.apache.xerces.xs.ElementPSVI;
import org.apache.xerces.xs.AttributePSVI;
import org.w3c.dom.TypeInfo;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.Augmentations;
import javax.xml.validation.TypeInfoProvider;

final class XMLSchemaTypeInfoProvider extends TypeInfoProvider
{
    private final ValidStateTypeInfoProvider fValidStateTypeInfoProvider;
    private final IllegalStateTypeInfoProvider fIllegalStateTypeInfoProvider;
    private TypeInfoProvider fCurrentTypeInfoProvider;
    private Augmentations fElementAugs;
    private XMLAttributes fAttributes;
    
    public XMLSchemaTypeInfoProvider() {
        this.fValidStateTypeInfoProvider = new ValidStateTypeInfoProvider();
        this.fIllegalStateTypeInfoProvider = IllegalStateTypeInfoProvider.getInstance();
        this.fCurrentTypeInfoProvider = this.fIllegalStateTypeInfoProvider;
    }
    
    void beginStartElement(final Augmentations fElementAugs, final XMLAttributes fAttributes) {
        this.fCurrentTypeInfoProvider = this.fValidStateTypeInfoProvider;
        this.fElementAugs = fElementAugs;
        this.fAttributes = fAttributes;
    }
    
    void finishStartElement() {
        this.fCurrentTypeInfoProvider = this.fIllegalStateTypeInfoProvider;
        this.fElementAugs = null;
        this.fAttributes = null;
        this.fValidStateTypeInfoProvider.fCachedElementTypeInfo = null;
        this.fValidStateTypeInfoProvider.fCachedAttributeIndex = -1;
        this.fValidStateTypeInfoProvider.fCachedAttributePSVI = null;
    }
    
    void beginEndElement(final Augmentations fElementAugs) {
        this.fElementAugs = fElementAugs;
    }
    
    void finishEndElement() {
        this.fElementAugs = null;
    }
    
    public TypeInfo getElementTypeInfo() {
        return this.fCurrentTypeInfoProvider.getElementTypeInfo();
    }
    
    public TypeInfo getAttributeTypeInfo(final int n) {
        return this.fCurrentTypeInfoProvider.getAttributeTypeInfo(n);
    }
    
    public boolean isIdAttribute(final int n) {
        return this.fCurrentTypeInfoProvider.isIdAttribute(n);
    }
    
    public boolean isSpecified(final int n) {
        return this.fCurrentTypeInfoProvider.isSpecified(n);
    }
    
    ElementPSVI getElementPSVI() {
        return (this.fElementAugs != null) ? ((ElementPSVI)this.fElementAugs.getItem("ELEMENT_PSVI")) : null;
    }
    
    AttributePSVI getAttributePSVI(final int n) {
        if (this.fAttributes != null) {
            final Augmentations augmentations = this.fAttributes.getAugmentations(n);
            if (augmentations != null) {
                return (AttributePSVI)augmentations.getItem("ATTRIBUTE_PSVI");
            }
        }
        return null;
    }
    
    AttributePSVI getAttributePSVIByName(final String s, final String s2) {
        if (this.fAttributes != null) {
            final Augmentations augmentations = this.fAttributes.getAugmentations(s, s2);
            if (augmentations != null) {
                return (AttributePSVI)augmentations.getItem("ATTRIBUTE_PSVI");
            }
        }
        return null;
    }
    
    static final class IllegalStateTypeInfoProvider extends TypeInfoProvider
    {
        private static IllegalStateTypeInfoProvider ILLEGAL_STATE_TYPE_INFO_PROVIDER_INSTANCE;
        
        public static IllegalStateTypeInfoProvider getInstance() {
            return IllegalStateTypeInfoProvider.ILLEGAL_STATE_TYPE_INFO_PROVIDER_INSTANCE;
        }
        
        public TypeInfo getElementTypeInfo() {
            throw new IllegalStateException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "TypeInfoProviderIllegalState", null));
        }
        
        public TypeInfo getAttributeTypeInfo(final int n) {
            throw new IllegalStateException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "TypeInfoProviderIllegalState", null));
        }
        
        public boolean isIdAttribute(final int n) {
            throw new IllegalStateException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "TypeInfoProviderIllegalState", null));
        }
        
        public boolean isSpecified(final int n) {
            throw new IllegalStateException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "TypeInfoProviderIllegalState", null));
        }
        
        static {
            IllegalStateTypeInfoProvider.ILLEGAL_STATE_TYPE_INFO_PROVIDER_INSTANCE = new IllegalStateTypeInfoProvider();
        }
    }
    
    final class ValidStateTypeInfoProvider extends TypeInfoProvider
    {
        private TypeInfo fCachedElementTypeInfo;
        private int fCachedAttributeIndex;
        private AttributePSVI fCachedAttributePSVI;
        
        public ValidStateTypeInfoProvider() {
            this.fCachedAttributeIndex = -1;
        }
        
        public TypeInfo getElementTypeInfo() {
            if (this.fCachedElementTypeInfo != null) {
                return this.fCachedElementTypeInfo;
            }
            XSTypeDefinition xsTypeDefinition = null;
            if (XMLSchemaTypeInfoProvider.this.fElementAugs != null) {
                final ElementPSVI elementPSVI = (ElementPSVI)XMLSchemaTypeInfoProvider.this.fElementAugs.getItem("ELEMENT_PSVI");
                if (elementPSVI != null) {
                    xsTypeDefinition = elementPSVI.getMemberTypeDefinition();
                    if (xsTypeDefinition == null) {
                        xsTypeDefinition = elementPSVI.getTypeDefinition();
                    }
                }
            }
            return this.fCachedElementTypeInfo = new XMLSchemaTypeInfo(xsTypeDefinition);
        }
        
        public TypeInfo getAttributeTypeInfo(final int n) {
            final AttributePSVI attributePSVI = this.getAttributePSVI(n);
            XSTypeDefinition xsTypeDefinition = null;
            if (attributePSVI != null) {
                xsTypeDefinition = attributePSVI.getMemberTypeDefinition();
                if (xsTypeDefinition == null) {
                    xsTypeDefinition = attributePSVI.getTypeDefinition();
                }
            }
            return new XMLSchemaTypeInfo(xsTypeDefinition);
        }
        
        public boolean isIdAttribute(final int n) {
            final AttributePSVI attributePSVI = this.getAttributePSVI(n);
            if (attributePSVI != null) {
                final XSSimpleTypeDefinition memberTypeDefinition = attributePSVI.getMemberTypeDefinition();
                if (memberTypeDefinition != null) {
                    return ((XSSimpleType)memberTypeDefinition).isIDType();
                }
                final XSTypeDefinition typeDefinition = attributePSVI.getTypeDefinition();
                if (typeDefinition != null) {
                    return ((XSSimpleType)typeDefinition).isIDType();
                }
            }
            return false;
        }
        
        public boolean isSpecified(final int n) {
            final AttributePSVI attributePSVI = this.getAttributePSVI(n);
            return attributePSVI == null || !attributePSVI.getIsSchemaSpecified();
        }
        
        private AttributePSVI getAttributePSVI(final int fCachedAttributeIndex) {
            if (fCachedAttributeIndex < 0 || fCachedAttributeIndex >= XMLSchemaTypeInfoProvider.this.fAttributes.getLength()) {
                throw new ArrayIndexOutOfBoundsException(fCachedAttributeIndex);
            }
            if (this.fCachedAttributeIndex != fCachedAttributeIndex) {
                this.fCachedAttributeIndex = fCachedAttributeIndex;
                this.fCachedAttributePSVI = (AttributePSVI)XMLSchemaTypeInfoProvider.this.fAttributes.getAugmentations(fCachedAttributeIndex).getItem("ATTRIBUTE_PSVI");
            }
            return this.fCachedAttributePSVI;
        }
    }
}
