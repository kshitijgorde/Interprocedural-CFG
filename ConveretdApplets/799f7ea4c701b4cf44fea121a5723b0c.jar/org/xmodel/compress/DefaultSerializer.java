// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress;

import java.io.DataOutput;
import java.io.IOException;
import java.io.DataInput;
import org.xmodel.compress.serial.NumberSerializer;
import org.xmodel.compress.serial.BooleanSerializer;
import org.xmodel.compress.serial.StringSerializer;
import java.util.ArrayList;
import java.util.List;

public class DefaultSerializer implements ISerializer
{
    private List<Class<?>> B;
    private List<ISerializer> A;
    
    public DefaultSerializer() {
        this.B = new ArrayList<Class<?>>();
        this.A = new ArrayList<ISerializer>();
        this.register(Object.class, new StringSerializer());
        this.register(Boolean.class, new BooleanSerializer());
        this.register(Number.class, new NumberSerializer());
    }
    
    public void register(final Class<?> clazz, final ISerializer serializer) {
        this.B.add(clazz);
        this.A.add(serializer);
    }
    
    @Override
    public Object readObject(final DataInput dataInput) throws IOException, ClassNotFoundException, CompressorException {
        final int n = dataInput.readShort() & 0xFFFF;
        if (n >= this.A.size()) {
            throw new ClassNotFoundException(String.format("Class identifier out of range, %d.", n));
        }
        return this.A.get(n).readObject(dataInput);
    }
    
    @Override
    public int writeObject(final DataOutput dataOutput, final Object o) throws IOException, CompressorException {
        final int a = this.A(o);
        if (a < 0) {
            throw new CompressorException(String.format("Class not supported, %s.", o.getClass().getName()));
        }
        final int n = 2;
        dataOutput.writeShort(a);
        return n + this.A.get(a).writeObject(dataOutput, o);
    }
    
    private final int A(final Object o) {
        System.out.println(o.getClass().getName());
        final Class<?> class1 = o.getClass();
        for (int i = this.B.size() - 1; i >= 0; --i) {
            if (this.B.get(i).isAssignableFrom(class1)) {
                return i;
            }
        }
        return -1;
    }
}
