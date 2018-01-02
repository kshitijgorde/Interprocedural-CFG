// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress.serial;

import java.io.DataOutput;
import org.xmodel.compress.CompressorException;
import java.io.IOException;
import java.io.DataInput;
import org.xmodel.compress.ISerializer;

public class StringSerializer implements ISerializer
{
    @Override
    public Object readObject(final DataInput dataInput) throws IOException, ClassNotFoundException, CompressorException {
        final byte[] array = new byte[dataInput.readInt()];
        dataInput.readFully(array);
        return new String(array);
    }
    
    @Override
    public int writeObject(final DataOutput dataOutput, final Object o) throws IOException, CompressorException {
        final byte[] bytes = o.toString().getBytes();
        dataOutput.writeInt(bytes.length);
        dataOutput.write(bytes);
        return bytes.length;
    }
}
