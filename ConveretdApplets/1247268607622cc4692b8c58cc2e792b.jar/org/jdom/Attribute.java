// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Attribute implements Serializable, Cloneable
{
    private static final String CVS_ID = "@(#) $RCSfile: Attribute.java,v $ $Revision: 1.56 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
    public static final int UNDECLARED_TYPE = 0;
    public static final int CDATA_TYPE = 1;
    public static final int ID_TYPE = 2;
    public static final int IDREF_TYPE = 3;
    public static final int IDREFS_TYPE = 4;
    public static final int ENTITY_TYPE = 5;
    public static final int ENTITIES_TYPE = 6;
    public static final int NMTOKEN_TYPE = 7;
    public static final int NMTOKENS_TYPE = 8;
    public static final int NOTATION_TYPE = 9;
    public static final int ENUMERATED_TYPE = 10;
    protected String name;
    protected transient Namespace namespace;
    protected String value;
    protected int type;
    protected Element parent;
    
    protected Attribute() {
        this.type = 0;
    }
    
    public Attribute(final String name, final String value, final Namespace namespace) {
        this(name, value, 0, namespace);
    }
    
    public Attribute(final String name, final String value, final int type, final Namespace namespace) {
        this.type = 0;
        this.setName(name);
        this.setValue(value);
        this.setAttributeType(type);
        this.setNamespace(namespace);
    }
    
    public Attribute(final String name, final String value) {
        this(name, value, 0, Namespace.NO_NAMESPACE);
    }
    
    public Attribute(final String name, final String value, final int type) {
        this(name, value, type, Namespace.NO_NAMESPACE);
    }
    
    public Element getParent() {
        return this.parent;
    }
    
    public Document getDocument() {
        final Element parentElement = this.getParent();
        if (parentElement != null) {
            return parentElement.getDocument();
        }
        return null;
    }
    
    protected Attribute setParent(final Element parent) {
        this.parent = parent;
        return this;
    }
    
    public Attribute detach() {
        final Element parentElement = this.getParent();
        if (parentElement != null) {
            parentElement.removeAttribute(this.getName(), this.getNamespace());
        }
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Attribute setName(final String name) {
        final String reason = Verifier.checkAttributeName(name);
        if (reason != null) {
            throw new IllegalNameException(name, "attribute", reason);
        }
        this.name = name;
        return this;
    }
    
    public String getQualifiedName() {
        final String prefix = this.namespace.getPrefix();
        if (prefix == null || "".equals(prefix)) {
            return this.getName();
        }
        return prefix + ':' + this.getName();
    }
    
    public String getNamespacePrefix() {
        return this.namespace.getPrefix();
    }
    
    public String getNamespaceURI() {
        return this.namespace.getURI();
    }
    
    public Namespace getNamespace() {
        return this.namespace;
    }
    
    public Attribute setNamespace(Namespace namespace) {
        if (namespace == null) {
            namespace = Namespace.NO_NAMESPACE;
        }
        if (namespace != Namespace.NO_NAMESPACE && "".equals(namespace.getPrefix())) {
            throw new IllegalNameException("", "attribute namespace", "An attribute namespace without a prefix can only be the NO_NAMESPACE namespace");
        }
        this.namespace = namespace;
        return this;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public Attribute setValue(final String value) {
        final String reason = Verifier.checkCharacterData(value);
        if (reason != null) {
            throw new IllegalDataException(value, "attribute", reason);
        }
        this.value = value;
        return this;
    }
    
    public int getAttributeType() {
        return this.type;
    }
    
    public Attribute setAttributeType(final int type) {
        if (type < 0 || type > 10) {
            throw new IllegalDataException(String.valueOf(type), "attribute", "Illegal attribute type");
        }
        this.type = type;
        return this;
    }
    
    public String toString() {
        return "[Attribute: " + this.getQualifiedName() + "=\"" + this.value + "\"" + "]";
    }
    
    public final boolean equals(final Object ob) {
        return ob == this;
    }
    
    public final int hashCode() {
        return super.hashCode();
    }
    
    public Object clone() {
        Attribute attribute = null;
        try {
            attribute = (Attribute)super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        attribute.parent = null;
        return attribute;
    }
    
    public int getIntValue() throws DataConversionException {
        try {
            return Integer.parseInt(this.value.trim());
        }
        catch (NumberFormatException e) {
            throw new DataConversionException(this.name, "int");
        }
    }
    
    public long getLongValue() throws DataConversionException {
        try {
            return Long.parseLong(this.value.trim());
        }
        catch (NumberFormatException e) {
            throw new DataConversionException(this.name, "long");
        }
    }
    
    public float getFloatValue() throws DataConversionException {
        try {
            return Float.valueOf(this.value.trim());
        }
        catch (NumberFormatException e) {
            throw new DataConversionException(this.name, "float");
        }
    }
    
    public double getDoubleValue() throws DataConversionException {
        try {
            return Double.valueOf(this.value.trim());
        }
        catch (NumberFormatException e) {
            final String v = this.value.trim();
            if ("INF".equals(v)) {
                return Double.POSITIVE_INFINITY;
            }
            if ("-INF".equals(v)) {
                return Double.NEGATIVE_INFINITY;
            }
            throw new DataConversionException(this.name, "double");
        }
    }
    
    public boolean getBooleanValue() throws DataConversionException {
        final String valueTrim = this.value.trim();
        if (valueTrim.equalsIgnoreCase("true") || valueTrim.equalsIgnoreCase("on") || valueTrim.equalsIgnoreCase("1") || valueTrim.equalsIgnoreCase("yes")) {
            return true;
        }
        if (valueTrim.equalsIgnoreCase("false") || valueTrim.equalsIgnoreCase("off") || valueTrim.equalsIgnoreCase("0") || valueTrim.equalsIgnoreCase("no")) {
            return false;
        }
        throw new DataConversionException(this.name, "boolean");
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.namespace.getPrefix());
        out.writeObject(this.namespace.getURI());
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.namespace = Namespace.getNamespace((String)in.readObject(), (String)in.readObject());
    }
}
