// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import java.awt.Shape;
import java.io.Serializable;

public class ShapeTable extends ObjectTable implements Serializable
{
    public Shape getShape(final int n, final int n2) {
        return (Shape)this.getObject(n, n2);
    }
    
    public void setShape(final int n, final int n2, final Shape shape) {
        this.setObject(n, n2, shape);
    }
    
    public boolean equals(final Object o) {
        return o instanceof ShapeTable && super.equals(o);
    }
    
    public int hashCode() {
        return super.hashCode();
    }
    
    protected void writeSerializedData(final ObjectOutputStream objectOutputStream, final Object o) throws IOException {
        SerialUtilities.writeShape((Shape)o, objectOutputStream);
    }
    
    protected Object readSerializedData(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        return SerialUtilities.readShape(objectInputStream);
    }
}
