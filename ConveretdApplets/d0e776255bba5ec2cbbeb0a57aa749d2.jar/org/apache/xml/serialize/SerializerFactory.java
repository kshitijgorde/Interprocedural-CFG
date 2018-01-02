// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import java.util.StringTokenizer;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Hashtable;

public abstract class SerializerFactory
{
    public static final String FactoriesProperty = "org.apache.xml.serialize.factories";
    private static Hashtable _factories;
    
    public static void registerSerializerFactory(final SerializerFactory serializerFactory) {
        synchronized (SerializerFactory._factories) {
            SerializerFactory._factories.put(serializerFactory.getSupportedMethod(), serializerFactory);
        }
    }
    
    public static SerializerFactory getSerializerFactory(final String s) {
        return SerializerFactory._factories.get(s);
    }
    
    protected abstract String getSupportedMethod();
    
    public abstract Serializer makeSerializer(final OutputFormat p0);
    
    public abstract Serializer makeSerializer(final Writer p0, final OutputFormat p1);
    
    public abstract Serializer makeSerializer(final OutputStream p0, final OutputFormat p1) throws UnsupportedEncodingException;
    
    static {
        SerializerFactory._factories = new Hashtable();
        registerSerializerFactory(new SerializerFactoryImpl("xml"));
        registerSerializerFactory(new SerializerFactoryImpl("html"));
        registerSerializerFactory(new SerializerFactoryImpl("xhtml"));
        registerSerializerFactory(new SerializerFactoryImpl("text"));
        final String property = System.getProperty("org.apache.xml.serialize.factories");
        if (property != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(property, " ;,:");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                try {
                    final SerializerFactory serializerFactory = (SerializerFactory)Class.forName(nextToken).newInstance();
                    if (!SerializerFactory._factories.containsKey(serializerFactory.getSupportedMethod())) {
                        continue;
                    }
                    SerializerFactory._factories.put(serializerFactory.getSupportedMethod(), serializerFactory);
                }
                catch (Exception ex) {}
            }
        }
    }
}
