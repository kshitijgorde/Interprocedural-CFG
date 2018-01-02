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
    private static final String CVS_ID = "@(#) $RCSfile: Attribute.java,v $ $Revision: 1.40 $ $Date: 2002/03/19 04:25:37 $ $Name: jdom_1_0_b8 $";
    public static final int UNDECLARED_ATTRIBUTE = 0;
    public static final int CDATA_ATTRIBUTE = 1;
    public static final int ID_ATTRIBUTE = 2;
    public static final int IDREF_ATTRIBUTE = 3;
    public static final int IDREFS_ATTRIBUTE = 4;
    public static final int ENTITY_ATTRIBUTE = 5;
    public static final int ENTITIES_ATTRIBUTE = 6;
    public static final int NMTOKEN_ATTRIBUTE = 7;
    public static final int NMTOKENS_ATTRIBUTE = 8;
    public static final int NOTATION_ATTRIBUTE = 9;
    public static final int ENUMERATED_ATTRIBUTE = 10;
    protected String name;
    protected transient Namespace namespace;
    protected String value;
    protected int type;
    protected Object parent;
    
    protected Attribute() {
        this.type = 0;
    }
    
    public Attribute(final String name, final String value, final Namespace namespace) {
        this.type = 0;
        this.setName(name);
        this.setValue(value);
        this.setNamespace(namespace);
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
        return (Element)this.parent;
    }
    
    public Document getDocument() {
        if (this.parent != null) {
            return ((Element)this.parent).getDocument();
        }
        return null;
    }
    
    protected Attribute setParent(final Element parent) {
        this.parent = parent;
        return this;
    }
    
    public Attribute detach() {
        final Element p = this.getParent();
        if (p != null) {
            p.removeAttribute(this.getName(), this.getNamespace());
        }
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Attribute setName(final String name) {
        final String reason;
        if ((reason = Verifier.checkAttributeName(name)) != null) {
            throw new IllegalNameException(name, "attribute", reason);
        }
        this.name = name;
        return this;
    }
    
    public String getQualifiedName() {
        final String prefix = this.namespace.getPrefix();
        if (prefix != null && !prefix.equals("")) {
            return prefix + ':' + this.getName();
        }
        return this.getName();
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
        if (namespace != Namespace.NO_NAMESPACE && namespace.getPrefix().equals("")) {
            throw new IllegalNameException("", "attribute namespace", "An attribute namespace without a prefix can only be the NO_NAMESPACE namespace");
        }
        this.namespace = namespace;
        return this;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public Attribute setValue(final String value) {
        String reason = null;
        if ((reason = Verifier.checkCharacterData(value)) != null) {
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
            return Integer.parseInt(this.value);
        }
        catch (NumberFormatException e) {
            throw new DataConversionException(this.name, "int");
        }
    }
    
    public long getLongValue() throws DataConversionException {
        try {
            return Long.parseLong(this.value);
        }
        catch (NumberFormatException e) {
            throw new DataConversionException(this.name, "long");
        }
    }
    
    public float getFloatValue() throws DataConversionException {
        try {
            return Float.valueOf(this.value);
        }
        catch (NumberFormatException e) {
            throw new DataConversionException(this.name, "float");
        }
    }
    
    public double getDoubleValue() throws DataConversionException {
        try {
            return Double.valueOf(this.value);
        }
        catch (NumberFormatException e) {
            throw new DataConversionException(this.name, "double");
        }
    }
    
    public boolean getBooleanValue() throws DataConversionException {
        if (this.value.equalsIgnoreCase("true") || this.value.equalsIgnoreCase("on") || this.value.equalsIgnoreCase("yes")) {
            return true;
        }
        if (this.value.equalsIgnoreCase("false") || this.value.equalsIgnoreCase("off") || this.value.equalsIgnoreCase("no")) {
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
