// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress.serial;

import java.io.DataOutput;
import org.xmodel.compress.CompressorException;
import java.io.IOException;
import java.io.File;
import java.io.DataInput;
import org.xmodel.compress.ISerializer;

public class FileSerializer implements ISerializer
{
    @Override
    public Object readObject(final DataInput dataInput) throws IOException, ClassNotFoundException, CompressorException {
        File file = new File(new String(new byte[dataInput.readInt()]));
        if (!file.isAbsolute()) {
            file = new File(new String(new byte[dataInput.readInt()]), file.getPath());
        }
        return file;
    }
    
    @Override
    public int writeObject(final DataOutput dataOutput, final Object o) throws IOException, CompressorException {
        final File file = (File)o;
        final byte[] bytes = file.getPath().getBytes();
        int length = bytes.length;
        dataOutput.writeInt(bytes.length);
        dataOutput.write(bytes);
        if (!file.isAbsolute()) {
            final byte[] bytes2 = System.getProperty("user.dir").getBytes();
            length += bytes2.length;
            dataOutput.writeInt(bytes2.length);
            dataOutput.write(bytes2);
        }
        return length;
    }
}
