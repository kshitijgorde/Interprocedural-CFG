// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;
import java.io.DataInputStream;

public class n
{
    public Object a(final DataInputStream dataInputStream) {
        return ca.a(dataInputStream);
    }
    
    public byte[] a(final Vector vector) {
        final Enumeration<Object> elements = vector.elements();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(10240);
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        while (elements.hasMoreElements()) {
            ca.a(elements.nextElement(), dataOutputStream);
        }
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    public Object a() {
        return new Byte((byte)2);
    }
}
