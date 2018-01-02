// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.ObjectOutputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectInputStream;
import java.awt.Stroke;
import java.io.Serializable;

public class StrokeTable extends ObjectTable implements Serializable
{
    public Stroke getStroke(final int n, final int n2) {
        return (Stroke)this.getObject(n, n2);
    }
    
    public void setStroke(final int n, final int n2, final Stroke stroke) {
        this.setObject(n, n2, stroke);
    }
    
    public boolean equals(final Object o) {
        return o instanceof StrokeTable && super.equals(o);
    }
    
    public int hashCode() {
        return super.hashCode();
    }
    
    protected Object readSerializedData(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        return SerialUtilities.readStroke(objectInputStream);
    }
    
    protected void writeSerializedData(final ObjectOutputStream objectOutputStream, final Object o) throws IOException {
        SerialUtilities.writeStroke((Stroke)o, objectOutputStream);
    }
}
