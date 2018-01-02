// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Set;
import org.jfree.util.ObjectUtilities;
import java.awt.Stroke;
import java.util.TreeMap;
import java.util.Map;
import java.io.Serializable;

public class StrokeMap implements Cloneable, Serializable
{
    private transient Map store;
    
    public StrokeMap() {
        this.store = new TreeMap();
    }
    
    public Stroke getStroke(final Comparable key) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        return this.store.get(key);
    }
    
    public boolean containsKey(final Comparable key) {
        return this.store.containsKey(key);
    }
    
    public void put(final Comparable key, final Stroke stroke) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        this.store.put(key, stroke);
    }
    
    public void clear() {
        this.store.clear();
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StrokeMap)) {
            return false;
        }
        final StrokeMap that = (StrokeMap)obj;
        if (this.store.size() != that.store.size()) {
            return false;
        }
        final Set keys = this.store.keySet();
        for (final Comparable key : keys) {
            final Stroke s1 = this.getStroke(key);
            final Stroke s2 = that.getStroke(key);
            if (!ObjectUtilities.equal(s1, s2)) {
                return false;
            }
        }
        return true;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.store.size());
        final Set keys = this.store.keySet();
        for (final Comparable key : keys) {
            stream.writeObject(key);
            final Stroke stroke = this.getStroke(key);
            SerialUtilities.writeStroke(stroke, stream);
        }
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.store = new TreeMap();
        for (int keyCount = stream.readInt(), i = 0; i < keyCount; ++i) {
            final Comparable key = (Comparable)stream.readObject();
            final Stroke stroke = SerialUtilities.readStroke(stream);
            this.store.put(key, stroke);
        }
    }
}
