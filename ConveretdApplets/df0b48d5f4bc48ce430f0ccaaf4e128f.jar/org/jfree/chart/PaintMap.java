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
import org.jfree.util.PaintUtilities;
import java.awt.Paint;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class PaintMap implements Cloneable, Serializable
{
    private transient Map store;
    
    public PaintMap() {
        this.store = new HashMap();
    }
    
    public Paint getPaint(final Comparable key) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        return this.store.get(key);
    }
    
    public boolean containsKey(final Comparable key) {
        return this.store.containsKey(key);
    }
    
    public void put(final Comparable key, final Paint paint) {
        if (key == null) {
            throw new IllegalArgumentException("Null 'key' argument.");
        }
        this.store.put(key, paint);
    }
    
    public void clear() {
        this.store.clear();
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PaintMap)) {
            return false;
        }
        final PaintMap that = (PaintMap)obj;
        if (this.store.size() != that.store.size()) {
            return false;
        }
        final Set keys = this.store.keySet();
        for (final Comparable key : keys) {
            final Paint p1 = this.getPaint(key);
            final Paint p2 = that.getPaint(key);
            if (!PaintUtilities.equal(p1, p2)) {
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
            final Paint paint = this.getPaint(key);
            SerialUtilities.writePaint(paint, stream);
        }
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.store = new HashMap();
        for (int keyCount = stream.readInt(), i = 0; i < keyCount; ++i) {
            final Comparable key = (Comparable)stream.readObject();
            final Paint paint = SerialUtilities.readPaint(stream);
            this.store.put(key, paint);
        }
    }
}
