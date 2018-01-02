// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress.serial;

import java.io.DataOutput;
import org.xmodel.compress.CompressorException;
import java.io.IOException;
import java.io.DataInput;
import org.xmodel.compress.ISerializer;

public class BooleanSerializer implements ISerializer
{
    @Override
    public Object readObject(final DataInput dataInput) throws IOException, ClassNotFoundException, CompressorException {
        return dataInput.readBoolean();
    }
    
    @Override
    public int writeObject(final DataOutput dataOutput, final Object o) throws IOException, CompressorException {
        dataOutput.writeBoolean((boolean)o);
        return 1;
    }
}
