// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import java.awt.Paint;
import java.io.Serializable;

public class PaintList extends AbstractObjectList implements Cloneable, Serializable
{
    public Paint getPaint(final int n) {
        return (Paint)this.get(n);
    }
    
    public void setPaint(final int n, final Paint paint) {
        this.set(n, paint);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean equals(final Object o) {
        return o != null && (o == this || (o instanceof PaintList && super.equals(o)));
    }
    
    public int hashCode() {
        return super.hashCode();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        final int size = this.size();
        objectOutputStream.writeInt(size);
        for (int i = 0; i < size; ++i) {
            final Paint paint = this.getPaint(i);
            if (paint != null) {
                objectOutputStream.writeInt(i);
                SerialUtilities.writePaint(paint, objectOutputStream);
            }
            else {
                objectOutputStream.writeInt(-1);
            }
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        for (int int1 = objectInputStream.readInt(), i = 0; i < int1; ++i) {
            final int int2 = objectInputStream.readInt();
            if (int2 != -1) {
                this.setPaint(int2, SerialUtilities.readPaint(objectInputStream));
            }
        }
    }
}
