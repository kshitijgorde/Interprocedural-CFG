// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamIterator implements OutputIterator
{
    ObjectOutputStream stream;
    
    public ObjectOutputStreamIterator(final ObjectOutputStream stream) {
        this.stream = stream;
    }
    
    public ObjectOutputStreamIterator(final ObjectOutputStreamIterator objectOutputStreamIterator) {
        this.stream = objectOutputStreamIterator.stream;
    }
    
    public void put(final Object o) {
        try {
            this.stream.writeObject(o);
        }
        catch (IOException ex) {}
    }
    
    public void advance() {
    }
    
    public void advance(final int n) {
    }
    
    public Object clone() {
        return new ObjectOutputStreamIterator(this);
    }
}
