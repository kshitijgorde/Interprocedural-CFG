// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.InputStream;
import org.jboss.mx.server.ObjectInputStreamWithClassLoader;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SerializationHelper
{
    public static Object deserialize(final byte[] byteArray) throws IOException, ClassNotFoundException {
        return deserialize(byteArray, Thread.currentThread().getContextClassLoader());
    }
    
    public static Object deserialize(final byte[] byteArray, ClassLoader cl) throws IOException, ClassNotFoundException {
        if (byteArray == null) {
            return null;
        }
        if (byteArray.length == 0) {
            return null;
        }
        try {
            if (cl == null) {
                cl = SerializationHelper.class.getClassLoader();
            }
            final ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(byteArray);
            final ObjectInputStream objectinputstream = (ObjectInputStream)new ObjectInputStreamWithClassLoader((InputStream)bytearrayinputstream, cl);
            final Object obj = objectinputstream.readObject();
            return obj;
        }
        catch (OptionalDataException optionaldataexception) {
            throw new IOException(optionaldataexception.getMessage());
        }
    }
    
    public static byte[] serialize(final Object obj) throws IOException {
        final ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        final ObjectOutputStream objectoutputstream = new ObjectOutputStream(bytearrayoutputstream);
        objectoutputstream.writeObject(obj);
        return bytearrayoutputstream.toByteArray();
    }
}
