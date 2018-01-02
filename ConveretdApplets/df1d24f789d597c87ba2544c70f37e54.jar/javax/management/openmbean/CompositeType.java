// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.util.Iterator;
import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;
import java.io.Serializable;

public class CompositeType extends OpenType implements Serializable
{
    private TreeMap nameToDescription;
    private TreeMap nameToType;
    private transient int cachedHashCode;
    private transient String cachedToString;
    private static final long serialVersionUID = -5366242454346948798L;
    
    public CompositeType(final String typeName, final String description, final String[] itemNames, final String[] itemDescriptions, final OpenType[] itemTypes) throws OpenDataException {
        super(CompositeData.class.getName(), typeName, description);
        this.cachedHashCode = 0;
        this.cachedToString = null;
        if (itemNames == null || itemNames.length == 0) {
            throw new IllegalArgumentException("null or empty itemNames");
        }
        if (itemDescriptions == null || itemDescriptions.length == 0) {
            throw new IllegalArgumentException("null or empty itemDescriptions");
        }
        if (itemTypes == null || itemTypes.length == 0) {
            throw new IllegalArgumentException("null or empty itemTypes");
        }
        if (itemNames.length != itemDescriptions.length) {
            throw new IllegalArgumentException("wrong number of itemDescriptions");
        }
        if (itemNames.length != itemTypes.length) {
            throw new IllegalArgumentException("wrong number of itemTypes");
        }
        this.nameToDescription = new TreeMap();
        this.nameToType = new TreeMap();
        for (int i = 0; i < itemNames.length; ++i) {
            if (itemNames[i] == null) {
                throw new IllegalArgumentException("null item name " + i);
            }
            final String itemName = itemNames[i].trim();
            if (itemName.length() == 0) {
                throw new IllegalArgumentException("empty item name " + i);
            }
            if (this.nameToDescription.containsKey(itemName)) {
                throw new OpenDataException("duplicate item name " + itemName);
            }
            if (itemDescriptions[i] == null) {
                throw new IllegalArgumentException("null item description " + i);
            }
            final String itemDescription = itemDescriptions[i].trim();
            if (itemDescription.length() == 0) {
                throw new IllegalArgumentException("empty item description " + i);
            }
            if (itemTypes[i] == null) {
                throw new IllegalArgumentException("null item type " + i);
            }
            this.nameToDescription.put(itemName, itemDescription);
            this.nameToType.put(itemName, itemTypes[i]);
        }
    }
    
    public boolean containsKey(final String itemName) {
        return itemName != null && this.nameToDescription.containsKey(itemName);
    }
    
    public String getDescription(final String itemName) {
        if (itemName == null) {
            return null;
        }
        return this.nameToDescription.get(itemName);
    }
    
    public OpenType getType(final String itemName) {
        if (itemName == null) {
            return null;
        }
        return this.nameToType.get(itemName);
    }
    
    public Set keySet() {
        return Collections.unmodifiableSet(this.nameToDescription.keySet());
    }
    
    public boolean isValue(final Object obj) {
        return obj != null && obj instanceof CompositeData && this.equals(((CompositeData)obj).getCompositeType());
    }
    
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof CompositeType)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final CompositeType other = (CompositeType)obj;
        if (!this.getTypeName().equals(other.getTypeName())) {
            return false;
        }
        final Iterator thisNames = this.keySet().iterator();
        final Iterator otherNames = other.keySet().iterator();
        while (thisNames.hasNext() && otherNames.hasNext()) {
            final String thisName = thisNames.next();
            final String otherName = otherNames.next();
            if (!thisName.equals(otherName)) {
                return false;
            }
            if (!this.getType(thisName).equals(other.getType(otherName))) {
                return false;
            }
        }
        return !thisNames.hasNext() && !otherNames.hasNext();
    }
    
    public int hashCode() {
        if (this.cachedHashCode != 0) {
            return this.cachedHashCode;
        }
        this.cachedHashCode = this.getTypeName().hashCode();
        Iterator i = this.nameToType.values().iterator();
        while (i.hasNext()) {
            this.cachedHashCode += i.next().hashCode();
        }
        i = this.nameToDescription.keySet().iterator();
        while (i.hasNext()) {
            this.cachedHashCode += i.next().hashCode();
        }
        return this.cachedHashCode;
    }
    
    public String toString() {
        if (this.cachedToString != null) {
            return this.cachedToString;
        }
        final StringBuffer buffer = new StringBuffer(this.getClass().getName());
        buffer.append("\n");
        final Iterator thisNames = this.keySet().iterator();
        while (thisNames.hasNext()) {
            final String thisName = thisNames.next();
            buffer.append("name=");
            buffer.append(thisName);
            buffer.append(" type=");
            buffer.append(this.getType(thisName));
            if (thisNames.hasNext()) {
                buffer.append("\n");
            }
        }
        return this.cachedToString = buffer.toString();
    }
}
