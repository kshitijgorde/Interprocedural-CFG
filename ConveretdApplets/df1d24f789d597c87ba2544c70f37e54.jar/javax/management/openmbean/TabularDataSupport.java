// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Map;

public class TabularDataSupport implements Cloneable, Map, Serializable, TabularData
{
    private static final long serialVersionUID = 5720150593236309827L;
    private static final ObjectStreamField[] serialPersistentFields;
    private Map dataMap;
    private TabularType tabularType;
    private transient String[] indexNames;
    
    public TabularDataSupport(final TabularType tabularType) {
        this(tabularType, 101, 0.75f);
    }
    
    public TabularDataSupport(final TabularType tabularType, final int initialCapacity, final float loadFactor) {
        this.init(new HashMap(initialCapacity, loadFactor), tabularType);
    }
    
    public TabularType getTabularType() {
        return this.tabularType;
    }
    
    public Object[] calculateIndex(final CompositeData value) {
        this.validateCompositeData(value);
        return value.getAll(this.indexNames);
    }
    
    public void clear() {
        this.dataMap.clear();
    }
    
    public boolean containsKey(final Object[] key) {
        return key != null && this.dataMap.containsKey(Arrays.asList(key));
    }
    
    public boolean containsValue(final CompositeData value) {
        return this.dataMap.containsValue(value);
    }
    
    public CompositeData get(final Object[] key) {
        this.validateKey(key);
        return this.dataMap.get(Arrays.asList(key));
    }
    
    public boolean isEmpty() {
        return this.dataMap.isEmpty();
    }
    
    public Set keySet() {
        return this.dataMap.keySet();
    }
    
    public void put(final CompositeData value) {
        final List index = Arrays.asList(this.calculateIndex(value));
        if (this.dataMap.containsKey(index)) {
            throw new KeyAlreadyExistsException("The index is already used " + index);
        }
        this.dataMap.put(index, value);
    }
    
    public void putAll(final CompositeData[] values) {
        if (values == null) {
            return;
        }
        final HashSet keys = new HashSet();
        for (int i = 0; i < values.length; ++i) {
            final List index = Arrays.asList(this.calculateIndex(values[i]));
            if (keys.contains(index)) {
                throw new KeyAlreadyExistsException("Duplicate index in values " + index + " for value " + values[i]);
            }
            keys.add(index);
            if (this.dataMap.containsKey(index)) {
                throw new KeyAlreadyExistsException("Index already used " + index + " for value " + values[i]);
            }
        }
        for (int i = 0; i < values.length; ++i) {
            this.put(values[i]);
        }
    }
    
    public CompositeData remove(final Object[] key) {
        this.validateKey(key);
        return this.dataMap.remove(Arrays.asList(key));
    }
    
    public int size() {
        return this.dataMap.size();
    }
    
    public Collection values() {
        return this.dataMap.values();
    }
    
    public boolean containsKey(final Object key) {
        return key != null && key instanceof Object[] && this.containsKey((Object[])key);
    }
    
    public boolean containsValue(final Object value) {
        return this.dataMap.containsValue(value);
    }
    
    public Set entrySet() {
        return this.dataMap.entrySet();
    }
    
    public Object get(final Object key) {
        return this.get((Object[])key);
    }
    
    public Object put(final Object key, final Object value) {
        this.put((CompositeData)value);
        return value;
    }
    
    public void putAll(final Map t) {
        if (t == null) {
            return;
        }
        final CompositeData[] data = new CompositeData[t.size()];
        int count = 0;
        final Iterator i = t.values().iterator();
        while (i.hasNext()) {
            data[count++] = i.next();
        }
        this.putAll(data);
    }
    
    public Object remove(final Object key) {
        return this.remove((Object[])key);
    }
    
    public Object clone() {
        try {
            final TabularDataSupport result = (TabularDataSupport)super.clone();
            result.dataMap = (Map)((HashMap)this.dataMap).clone();
            return result;
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException("Unexpected clone not supported exception.");
        }
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        final ObjectInputStream.GetField getField = in.readFields();
        final Map dataMap = (Map)getField.get("dataMap", null);
        final TabularType tabularType = (TabularType)getField.get("tabularType", null);
        try {
            this.init(dataMap, tabularType);
        }
        catch (Exception e) {
            throw new StreamCorruptedException(e.toString());
        }
    }
    
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof TabularData)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final TabularData other = (TabularData)obj;
        if (!this.tabularType.equals(other.getTabularType())) {
            return false;
        }
        if (this.size() != other.size()) {
            return false;
        }
        for (final Entry entry : this.dataMap.entrySet()) {
            final Object[] indexes = entry.getKey().toArray();
            final Object thisValue = entry.getValue();
            final Object otherValue = other.get(indexes);
            if ((thisValue != null || otherValue != null) && (thisValue == null || !thisValue.equals(otherValue))) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        int hash = this.tabularType.hashCode();
        final Iterator i = this.dataMap.values().iterator();
        while (i.hasNext()) {
            hash += i.next().hashCode();
        }
        return hash;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(this.getClass().getName());
        buffer.append(": tabularType=[");
        buffer.append(this.getTabularType());
        buffer.append("] mappings=[");
        final Iterator entries = this.dataMap.entrySet().iterator();
        while (entries.hasNext()) {
            final Entry entry = entries.next();
            buffer.append(entry.getKey());
            buffer.append("=");
            buffer.append(entry.getValue());
            if (entries.hasNext()) {
                buffer.append(",");
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
    
    private void init(final Map dataMap, final TabularType tabularType) {
        if (dataMap == null) {
            throw new IllegalArgumentException("null dataMap");
        }
        if (tabularType == null) {
            throw new IllegalArgumentException("null tabularType");
        }
        this.dataMap = dataMap;
        this.tabularType = tabularType;
        final List indexNameList = tabularType.getIndexNames();
        this.indexNames = indexNameList.toArray(new String[indexNameList.size()]);
    }
    
    private void validateCompositeData(final CompositeData value) {
        if (value == null) {
            throw new NullPointerException("null value");
        }
        if (!value.getCompositeType().equals(this.tabularType.getRowType())) {
            throw new InvalidOpenTypeException("value has composite type " + value.getCompositeType() + " expected row type " + this.tabularType.getRowType());
        }
    }
    
    private void validateKey(final Object[] key) {
        if (key == null || key.length == 0) {
            throw new NullPointerException("null key");
        }
        if (key.length != this.indexNames.length) {
            throw new InvalidKeyException("key has " + key.length + " elements, " + "should be " + this.indexNames.length);
        }
        for (int i = 0; i < key.length; ++i) {
            final OpenType openType = this.tabularType.getRowType().getType(this.indexNames[i]);
            if (key[i] != null && !openType.isValue(key[i])) {
                throw new InvalidKeyException("key element " + i + " " + key + " is not a value for " + openType);
            }
        }
    }
    
    static {
        serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("dataMap", Map.class), new ObjectStreamField("tabularType", TabularType.class) };
    }
}
