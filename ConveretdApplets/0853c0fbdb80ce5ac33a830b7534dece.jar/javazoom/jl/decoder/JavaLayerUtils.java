// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.InputStream;

public class JavaLayerUtils
{
    private static JavaLayerHook hook;
    static /* synthetic */ Class class$javazoom$jl$decoder$JavaLayerUtils;
    
    public static Object deserialize(final InputStream inputStream, final Class clazz) throws IOException {
        if (clazz == null) {
            throw new NullPointerException("cls");
        }
        final Object deserialize = deserialize(inputStream, clazz);
        if (!clazz.isInstance(deserialize)) {
            throw new InvalidObjectException("type of deserialized instance not of required class.");
        }
        return deserialize;
    }
    
    public static Object deserialize(final InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new NullPointerException("in");
        }
        final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object object;
        try {
            object = objectInputStream.readObject();
        }
        catch (ClassNotFoundException ex) {
            throw new InvalidClassException(ex.toString());
        }
        return object;
    }
    
    public static Object deserializeArray(final InputStream inputStream, final Class clazz, final int n) throws IOException {
        if (clazz == null) {
            throw new NullPointerException("elemType");
        }
        if (n < -1) {
            throw new IllegalArgumentException("length");
        }
        final Object deserialize = deserialize(inputStream);
        final Class<?> class1 = deserialize.getClass();
        if (!class1.isArray()) {
            throw new InvalidObjectException("object is not an array");
        }
        if (class1.getComponentType() != clazz) {
            throw new InvalidObjectException("unexpected array component type");
        }
        if (n != -1 && Array.getLength(deserialize) != n) {
            throw new InvalidObjectException("array length mismatch");
        }
        return deserialize;
    }
    
    public static Object deserializeArrayResource(final String s, final Class clazz, final int n) throws IOException {
        final InputStream resourceAsStream = getResourceAsStream(s);
        if (resourceAsStream == null) {
            throw new IOException("unable to load resource '" + s + "'");
        }
        return deserializeArray(resourceAsStream, clazz, n);
    }
    
    public static void serialize(final OutputStream outputStream, final Object o) throws IOException {
        if (outputStream == null) {
            throw new NullPointerException("out");
        }
        if (o == null) {
            throw new NullPointerException("obj");
        }
        new ObjectOutputStream(outputStream).writeObject(o);
    }
    
    public static synchronized void setHook(final JavaLayerHook hook) {
        JavaLayerUtils.hook = hook;
    }
    
    public static synchronized JavaLayerHook getHook() {
        return JavaLayerUtils.hook;
    }
    
    public static synchronized InputStream getResourceAsStream(final String s) {
        InputStream inputStream;
        if (JavaLayerUtils.hook != null) {
            inputStream = JavaLayerUtils.hook.getResourceAsStream(s);
        }
        else {
            inputStream = ((JavaLayerUtils.class$javazoom$jl$decoder$JavaLayerUtils == null) ? (JavaLayerUtils.class$javazoom$jl$decoder$JavaLayerUtils = class$("javazoom.jl.decoder.JavaLayerUtils")) : JavaLayerUtils.class$javazoom$jl$decoder$JavaLayerUtils).getResourceAsStream(s);
        }
        return inputStream;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        JavaLayerUtils.hook = null;
    }
}
