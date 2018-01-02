// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLAttributes;

public class XMLAttributesImpl implements XMLAttributes
{
    protected boolean fNamespaces;
    protected int fLength;
    protected Attribute[] fAttributes;
    protected Augmentations[] fAugmentations;
    
    public XMLAttributesImpl() {
        this.fNamespaces = true;
        this.fAttributes = new Attribute[4];
        this.fAugmentations = new AugmentationsImpl[4];
        for (int i = 0; i < this.fAttributes.length; ++i) {
            this.fAttributes[i] = new Attribute();
            this.fAugmentations[i] = new AugmentationsImpl();
        }
    }
    
    public void setNamespaces(final boolean namespaces) {
        this.fNamespaces = namespaces;
    }
    
    public int addAttribute(final QName name, final String type, final String value) {
        int index = (name.uri != null && !name.uri.equals("")) ? this.getIndex(name.uri, name.localpart) : this.getIndex(name.rawname);
        if (index == -1) {
            index = this.fLength;
            if (this.fLength++ == this.fAttributes.length) {
                final Attribute[] attributes = new Attribute[this.fAttributes.length + 4];
                final Augmentations[] augs = new AugmentationsImpl[this.fAttributes.length + 4];
                System.arraycopy(this.fAttributes, 0, attributes, 0, this.fAttributes.length);
                System.arraycopy(this.fAugmentations, 0, augs, 0, this.fAttributes.length);
                for (int i = this.fAttributes.length; i < attributes.length; ++i) {
                    attributes[i] = new Attribute();
                    augs[i] = new AugmentationsImpl();
                }
                this.fAttributes = attributes;
                this.fAugmentations = augs;
            }
        }
        this.fAugmentations[index].clear();
        final Attribute attribute = this.fAttributes[index];
        attribute.name.setValues(name);
        attribute.type = type;
        attribute.value = value;
        attribute.nonNormalizedValue = value;
        attribute.specified = false;
        return index;
    }
    
    public void removeAllAttributes() {
        this.fLength = 0;
    }
    
    public void removeAttributeAt(final int attrIndex) {
        if (attrIndex < this.fLength - 1) {
            final Attribute removedAttr = this.fAttributes[attrIndex];
            final Augmentations removedAug = this.fAugmentations[attrIndex];
            System.arraycopy(this.fAttributes, attrIndex + 1, this.fAttributes, attrIndex, this.fLength - attrIndex - 1);
            System.arraycopy(this.fAugmentations, attrIndex + 1, this.fAugmentations, attrIndex, this.fLength - attrIndex - 1);
            this.fAttributes[this.fLength - 1] = removedAttr;
            this.fAugmentations[this.fLength - 1] = removedAug;
        }
        --this.fLength;
    }
    
    public void setName(final int attrIndex, final QName attrName) {
        this.fAttributes[attrIndex].name.setValues(attrName);
    }
    
    public void getName(final int attrIndex, final QName attrName) {
        attrName.setValues(this.fAttributes[attrIndex].name);
    }
    
    public void setType(final int attrIndex, final String attrType) {
        this.fAttributes[attrIndex].type = attrType;
    }
    
    public void setValue(final int attrIndex, final String attrValue) {
        final Attribute attribute = this.fAttributes[attrIndex];
        attribute.value = attrValue;
        attribute.nonNormalizedValue = attrValue;
    }
    
    public void setNonNormalizedValue(final int attrIndex, String attrValue) {
        if (attrValue == null) {
            attrValue = this.fAttributes[attrIndex].value;
        }
        this.fAttributes[attrIndex].nonNormalizedValue = attrValue;
    }
    
    public String getNonNormalizedValue(final int attrIndex) {
        final String value = this.fAttributes[attrIndex].nonNormalizedValue;
        return value;
    }
    
    public void setSpecified(final int attrIndex, final boolean specified) {
        this.fAttributes[attrIndex].specified = specified;
    }
    
    public boolean isSpecified(final int attrIndex) {
        return this.fAttributes[attrIndex].specified;
    }
    
    public int getLength() {
        return this.fLength;
    }
    
    public String getType(final int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        final String type = this.fAttributes[index].type;
        if (type.indexOf(40) == 0 && type.lastIndexOf(41) == type.length() - 1) {
            return "NMTOKEN";
        }
        return type;
    }
    
    public String getType(final String qname) {
        final int index = this.getIndex(qname);
        return (index != -1) ? this.fAttributes[index].type : null;
    }
    
    public String getValue(final int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fAttributes[index].value;
    }
    
    public String getValue(final String qname) {
        final int index = this.getIndex(qname);
        return (index != -1) ? this.fAttributes[index].value : null;
    }
    
    public String getName(final int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fAttributes[index].name.rawname;
    }
    
    public int getIndex(final String qName) {
        for (int i = 0; i < this.fLength; ++i) {
            final Attribute attribute = this.fAttributes[i];
            if (attribute.name.rawname != null && attribute.name.rawname.equals(qName)) {
                return i;
            }
        }
        return -1;
    }
    
    public int getIndex(final String uri, final String localPart) {
        for (int i = 0; i < this.fLength; ++i) {
            final Attribute attribute = this.fAttributes[i];
            if (attribute.name.localpart != null && attribute.name.localpart.equals(localPart) && (uri == attribute.name.uri || (uri != null && attribute.name.uri != null && attribute.name.uri.equals(uri)))) {
                return i;
            }
        }
        return -1;
    }
    
    public String getLocalName(final int index) {
        if (!this.fNamespaces) {
            return "";
        }
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fAttributes[index].name.localpart;
    }
    
    public String getQName(final int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        final String rawname = this.fAttributes[index].name.rawname;
        return (rawname != null) ? rawname : "";
    }
    
    public String getType(final String uri, final String localName) {
        if (!this.fNamespaces) {
            return null;
        }
        final int index = this.getIndex(uri, localName);
        return (index != -1) ? this.getType(index) : null;
    }
    
    public String getPrefix(final int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        final String prefix = this.fAttributes[index].name.prefix;
        return (prefix != null) ? prefix : "";
    }
    
    public String getURI(final int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        final String uri = this.fAttributes[index].name.uri;
        return uri;
    }
    
    public String getValue(final String uri, final String localName) {
        final int index = this.getIndex(uri, localName);
        return (index != -1) ? this.getValue(index) : null;
    }
    
    public Augmentations getAugmentations(final String uri, final String localName) {
        final int index = this.getIndex(uri, localName);
        return (index != -1) ? this.fAugmentations[index] : null;
    }
    
    public Augmentations getAugmentations(final String qName) {
        final int index = this.getIndex(qName);
        return (index != -1) ? this.fAugmentations[index] : null;
    }
    
    public Augmentations getAugmentations(final int attributeIndex) {
        if (attributeIndex < 0 || attributeIndex >= this.fLength) {
            return null;
        }
        return this.fAugmentations[attributeIndex];
    }
    
    public void setURI(final int attrIndex, final String uri) {
        this.fAttributes[attrIndex].name.uri = uri;
    }
    
    public void setSchemaId(final int attrIndex, final boolean schemaId) {
        this.fAttributes[attrIndex].schemaId = schemaId;
    }
    
    public boolean getSchemaId(final int index) {
        return index >= 0 && index < this.fLength && this.fAttributes[index].schemaId;
    }
    
    public boolean getSchemaId(final String qname) {
        final int index = this.getIndex(qname);
        return index != -1 && this.fAttributes[index].schemaId;
    }
    
    public boolean getSchemaId(final String uri, final String localName) {
        if (!this.fNamespaces) {
            return false;
        }
        final int index = this.getIndex(uri, localName);
        return index != -1 && this.fAttributes[index].schemaId;
    }
    
    static class Attribute
    {
        public QName name;
        public String type;
        public String value;
        public String nonNormalizedValue;
        public boolean specified;
        public boolean schemaId;
        
        Attribute() {
            this.name = new QName();
        }
    }
}
