// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.apache.xerces.util.ObjectFactory;
import java.util.StringTokenizer;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Hashtable;

public abstract class SerializerFactory
{
    public static final String FactoriesProperty = "org.apache.xml.serialize.factories";
    private static Hashtable _factories;
    
    public static void registerSerializerFactory(final SerializerFactory factory) {
        synchronized (SerializerFactory._factories) {
            final String method = factory.getSupportedMethod();
            SerializerFactory._factories.put(method, factory);
        }
    }
    
    public static SerializerFactory getSerializerFactory(final String method) {
        return SerializerFactory._factories.get(method);
    }
    
    protected abstract String getSupportedMethod();
    
    public abstract Serializer makeSerializer(final OutputFormat p0);
    
    public abstract Serializer makeSerializer(final Writer p0, final OutputFormat p1);
    
    public abstract Serializer makeSerializer(final OutputStream p0, final OutputFormat p1) throws UnsupportedEncodingException;
    
    static {
        SerializerFactory._factories = new Hashtable();
        SerializerFactory factory = new SerializerFactoryImpl("xml");
        registerSerializerFactory(factory);
        factory = new SerializerFactoryImpl("html");
        registerSerializerFactory(factory);
        factory = new SerializerFactoryImpl("xhtml");
        registerSerializerFactory(factory);
        factory = new SerializerFactoryImpl("text");
        registerSerializerFactory(factory);
        final String list = System.getProperty("org.apache.xml.serialize.factories");
        if (list != null) {
            final StringTokenizer token = new StringTokenizer(list, " ;,:");
            while (token.hasMoreTokens()) {
                final String className = token.nextToken();
                try {
                    factory = (SerializerFactory)ObjectFactory.newInstance(className, ObjectFactory.findClassLoader(), true);
                    if (!SerializerFactory._factories.containsKey(factory.getSupportedMethod())) {
                        continue;
                    }
                    SerializerFactory._factories.put(factory.getSupportedMethod(), factory);
                }
                catch (Exception ex) {}
            }
        }
    }
}
