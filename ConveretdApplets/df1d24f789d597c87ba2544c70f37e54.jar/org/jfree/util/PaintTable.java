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

public class PaintTable extends ObjectTable implements Serializable
{
    public Paint getPaint(final int n, final int n2) {
        return (Paint)this.getObject(n, n2);
    }
    
    public void setPaint(final int n, final int n2, final Paint paint) {
        this.setObject(n, n2, paint);
    }
    
    public boolean equals(final Object o) {
        return o instanceof PaintTable && super.equals(o);
    }
    
    public int hashCode() {
        return super.hashCode();
    }
    
    protected void writeSerializedData(final ObjectOutputStream objectOutputStream, final Object o) throws IOException {
        SerialUtilities.writePaint((Paint)o, objectOutputStream);
    }
    
    protected Object readSerializedData(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        return SerialUtilities.readPaint(objectInputStream);
    }
}
