// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress.serial;

import java.io.DataOutput;
import org.xmodel.compress.CompressorException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.DataInput;
import org.xmodel.compress.ISerializer;

public class NumberSerializer implements ISerializer
{
    @Override
    public Object readObject(final DataInput dataInput) throws IOException, ClassNotFoundException, CompressorException {
        final byte byte1 = dataInput.readByte();
        if (byte1 == 19) {
            return dataInput.readFloat();
        }
        if (byte1 == 20) {
            return dataInput.readDouble();
        }
        if (byte1 == 21) {
            return new BigDecimal(new BigInteger(new byte[dataInput.readByte() & 0xFF]), dataInput.readInt());
        }
        if (byte1 == 1) {
            return dataInput.readByte();
        }
        if (byte1 == 2) {
            return dataInput.readShort();
        }
        if (byte1 == 3) {
            return dataInput.readInt();
        }
        if (byte1 == 4) {
            return dataInput.readLong();
        }
        if (byte1 == 5) {
            return new BigInteger(new byte[dataInput.readByte() & 0xFF]);
        }
        throw new IOException("Illegal number type specifier.");
    }
    
    @Override
    public int writeObject(final DataOutput dataOutput, final Object o) throws IOException, CompressorException {
        if (o instanceof Float) {
            dataOutput.writeByte(19);
            dataOutput.writeFloat((float)o);
        }
        else if (o instanceof Double) {
            dataOutput.writeByte(20);
            dataOutput.writeDouble((double)o);
        }
        else if (o instanceof BigDecimal) {
            dataOutput.writeByte(21);
            final BigDecimal bigDecimal = (BigDecimal)o;
            final byte[] byteArray = bigDecimal.unscaledValue().toByteArray();
            dataOutput.writeByte((byte)byteArray.length);
            dataOutput.write(byteArray);
            dataOutput.writeInt(bigDecimal.scale());
        }
        else if (o instanceof Byte) {
            dataOutput.writeByte(1);
            dataOutput.writeByte((byte)o);
        }
        else if (o instanceof Short) {
            dataOutput.writeByte(2);
            dataOutput.writeShort((short)o);
        }
        else if (o instanceof Integer) {
            dataOutput.writeByte(3);
            dataOutput.writeInt((int)o);
        }
        else if (o instanceof Long) {
            dataOutput.writeByte(4);
            dataOutput.writeLong((long)o);
        }
        else if (o instanceof BigInteger) {
            dataOutput.writeByte(5);
            final byte[] byteArray2 = ((BigInteger)o).toByteArray();
            dataOutput.writeByte((byte)byteArray2.length);
            dataOutput.write(byteArray2);
        }
        throw new IllegalArgumentException();
    }
}
