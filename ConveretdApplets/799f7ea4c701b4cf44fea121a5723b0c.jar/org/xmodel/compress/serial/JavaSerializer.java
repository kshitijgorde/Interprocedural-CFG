// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress.serial;

import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import org.xmodel.compress.CompressorException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import org.xmodel.compress.ISerializer;

public class JavaSerializer implements ISerializer
{
    @Override
    public Object readObject(final DataInput dataInput) throws IOException, ClassNotFoundException, CompressorException {
        final byte[] array = new byte[dataInput.readInt()];
        dataInput.readFully(array);
        final ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(array));
        final Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
    
    @Override
    public int writeObject(final DataOutput dataOutput, final Object o) throws IOException, CompressorException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        dataOutput.writeInt(byteArray.length);
        dataOutput.write(byteArray);
        return byteArray.length;
    }
}
