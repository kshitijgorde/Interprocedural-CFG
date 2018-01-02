// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.util.Iterator;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.SortedMap;
import java.io.ObjectStreamField;
import java.io.Serializable;

public class CompositeDataSupport implements CompositeData, Serializable
{
    private static final long serialVersionUID = 8003518976613702244L;
    private static final ObjectStreamField[] serialPersistentFields;
    private SortedMap contents;
    private CompositeType compositeType;
    private int hashCode;
    
    public CompositeDataSupport(final CompositeType compositeType, final String[] itemNames, final Object[] itemValues) throws OpenDataException {
        if (compositeType == null) {
            throw new IllegalArgumentException("null compositeType");
        }
        if (itemNames == null) {
            throw new IllegalArgumentException("null itemNames");
        }
        if (itemValues == null) {
            throw new IllegalArgumentException("null itemValues");
        }
        if (itemNames.length == 0) {
            throw new IllegalArgumentException("empty itemNames");
        }
        if (itemValues.length == 0) {
            throw new IllegalArgumentException("empty itemValues");
        }
        if (itemNames.length != itemValues.length) {
            throw new IllegalArgumentException("itemNames has size " + itemNames.length + " but itemValues has size " + itemValues.length);
        }
        final int compositeNameSize = compositeType.keySet().size();
        if (itemNames.length != compositeNameSize) {
            throw new OpenDataException("itemNames has size " + itemNames.length + " but composite type has size " + compositeNameSize);
        }
        this.compositeType = compositeType;
        this.contents = new TreeMap();
        for (int i = 0; i < itemNames.length; ++i) {
            if (itemNames[i] == null || itemNames[i].length() == 0) {
                throw new IllegalArgumentException("Item name " + i + " is null or empty");
            }
            if (this.contents.get(itemNames[i]) != null) {
                throw new OpenDataException("duplicate item name " + itemNames[i]);
            }
            final OpenType openType = compositeType.getType(itemNames[i]);
            if (openType == null) {
                throw new OpenDataException("item name not in composite type " + itemNames[i]);
            }
            if (itemValues[i] != null && !openType.isValue(itemValues[i])) {
                throw new OpenDataException("item value " + itemValues[i] + " for item name " + itemNames[i] + " is not a " + openType);
            }
            this.contents.put(itemNames[i], itemValues[i]);
        }
    }
    
    public CompositeDataSupport(final CompositeType compositeType, final Map items) throws OpenDataException {
        this.init(compositeType, items);
    }
    
    public CompositeType getCompositeType() {
        return this.compositeType;
    }
    
    public Object get(final String key) {
        this.validateKey(key);
        return this.contents.get(key);
    }
    
    public Object[] getAll(final String[] keys) {
        if (keys == null) {
            throw new IllegalArgumentException("Null keys");
        }
        final Object[] result = new Object[keys.length];
        for (int i = 0; i < keys.length; ++i) {
            this.validateKey(keys[i]);
            result[i] = this.contents.get(keys[i]);
        }
        return result;
    }
    
    public boolean containsKey(final String key) {
        return key != null && key.length() != 0 && this.contents.containsKey(key);
    }
    
    public boolean containsValue(final Object value) {
        return this.contents.containsValue(value);
    }
    
    public Collection values() {
        return Collections.unmodifiableCollection(this.contents.values());
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        final ObjectInputStream.GetField getField = in.readFields();
        final SortedMap contents = (SortedMap)getField.get("contents", null);
        final CompositeType compositeType = (CompositeType)getField.get("compositeType", null);
        try {
            this.init(compositeType, contents);
        }
        catch (Exception e) {
            throw new StreamCorruptedException(e.toString());
        }
    }
    
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof CompositeData)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        final CompositeData other = (CompositeData)obj;
        if (!this.compositeType.equals(other.getCompositeType())) {
            return false;
        }
        if (this.values().size() != other.values().size()) {
            return false;
        }
        for (final String key : this.contents.keySet()) {
            final Object thisValue = this.get(key);
            final Object otherValue = other.get(key);
            if ((thisValue != null || otherValue != null) && (thisValue == null || !thisValue.equals(otherValue))) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        if (this.hashCode != 0) {
            return this.hashCode;
        }
        this.hashCode = this.compositeType.hashCode();
        for (final Object value : this.contents.values()) {
            if (value != null) {
                this.hashCode += value.hashCode();
            }
        }
        return this.hashCode;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(this.getClass().getName());
        buffer.append(": compositeType=[");
        buffer.append(this.getCompositeType());
        buffer.append("] mappings=[");
        final Iterator keys = this.compositeType.keySet().iterator();
        while (keys.hasNext()) {
            final Object key = keys.next();
            buffer.append(key + "=" + this.contents.get(key));
            if (keys.hasNext()) {
                buffer.append(",");
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
    
    private void init(final CompositeType compositeType, final Map items) throws OpenDataException {
        if (compositeType == null) {
            throw new IllegalArgumentException("null compositeType");
        }
        if (items == null) {
            throw new IllegalArgumentException("null items");
        }
        if (items.size() == 0) {
            throw new IllegalArgumentException("empty items");
        }
        final int compositeNameSize = compositeType.keySet().size();
        if (items.size() != compositeNameSize) {
            throw new OpenDataException("items has size " + items.size() + " but composite type has size " + compositeNameSize);
        }
        this.compositeType = compositeType;
        this.contents = new TreeMap();
        for (final Object next : items.keySet()) {
            if (next != null && !(next instanceof String)) {
                throw new ArrayStoreException("key is not a String " + next);
            }
            final String key = (String)next;
            if (key == null || key.length() == 0) {
                throw new IllegalArgumentException("Key is null or empty");
            }
            final OpenType openType = compositeType.getType(key);
            if (openType == null) {
                throw new OpenDataException("item name not in composite type " + key);
            }
            final Object value = items.get(key);
            if (value != null && !openType.isValue(value)) {
                throw new OpenDataException("item value " + value + " for item name " + key + " is not a " + openType);
            }
            this.contents.put(key, value);
        }
    }
    
    private void validateKey(final String key) throws InvalidKeyException {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("null or empty key");
        }
        if (!this.compositeType.containsKey(key)) {
            throw new InvalidKeyException("no such item name " + key + " for composite type " + this.compositeType);
        }
    }
    
    static {
        serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("contents", SortedMap.class), new ObjectStreamField("compositeType", CompositeType.class) };
    }
}
