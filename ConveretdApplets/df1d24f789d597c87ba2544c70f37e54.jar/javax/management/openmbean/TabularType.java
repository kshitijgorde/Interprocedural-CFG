// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class TabularType extends OpenType implements Serializable
{
    private CompositeType rowType;
    private List indexNames;
    private transient int cachedHashCode;
    private transient String cachedToString;
    private static final long serialVersionUID = 6554071860220659261L;
    
    public TabularType(final String typeName, final String description, final CompositeType rowType, final String[] indexNames) throws OpenDataException {
        super(TabularData.class.getName(), typeName, description);
        this.cachedHashCode = 0;
        this.cachedToString = null;
        if (rowType == null) {
            throw new IllegalArgumentException("null rowType");
        }
        if (indexNames == null || indexNames.length == 0) {
            throw new IllegalArgumentException("null or empty indexNames");
        }
        this.rowType = rowType;
        this.indexNames = new ArrayList();
        for (int i = 0; i < indexNames.length; ++i) {
            if (indexNames[i] == null) {
                throw new IllegalArgumentException("null index name " + i);
            }
            final String indexName = indexNames[i].trim();
            if (indexName.length() == 0) {
                throw new IllegalArgumentException("empty index name " + i);
            }
            if (!rowType.containsKey(indexName)) {
                throw new OpenDataException("no item name " + indexName);
            }
            this.indexNames.add(indexName);
        }
    }
    
    public CompositeType getRowType() {
        return this.rowType;
    }
    
    public List getIndexNames() {
        return Collections.unmodifiableList((List<?>)this.indexNames);
    }
    
    public boolean isValue(final Object obj) {
        if (obj == null || !(obj instanceof TabularData)) {
            return false;
        }
        final TabularType other = ((TabularData)obj).getTabularType();
        return this.equals(other);
    }
    
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof TabularType)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final TabularType other = (TabularType)obj;
        if (!this.getTypeName().equals(other.getTypeName())) {
            return false;
        }
        if (!this.getRowType().equals(other.getRowType())) {
            return false;
        }
        final Iterator thisNames = this.getIndexNames().iterator();
        final Iterator otherNames = other.getIndexNames().iterator();
        while (thisNames.hasNext() && otherNames.hasNext()) {
            final String thisName = thisNames.next();
            final String otherName = otherNames.next();
            if (!thisName.equals(otherName)) {
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
        this.cachedHashCode += this.getRowType().hashCode();
        final Iterator i = this.indexNames.iterator();
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
        buffer.append(": typeName=[");
        buffer.append(this.getTypeName());
        buffer.append("] rowType=[");
        buffer.append(this.getRowType());
        buffer.append("] indexNames=[");
        final Iterator thisNames = this.getIndexNames().iterator();
        while (thisNames.hasNext()) {
            buffer.append(thisNames.next());
            if (thisNames.hasNext()) {
                buffer.append(", ");
            }
        }
        buffer.append("]");
        return this.cachedToString = buffer.toString();
    }
}
