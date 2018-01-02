// 
// Decompiled by Procyon v0.5.30
// 

package org.yaml.snakeyaml;

import java.io.Reader;
import java.io.InputStream;

public class JavaBeanParser
{
    public static <T> T load(final String yaml, final Class<T> javabean) {
        final JavaBeanLoader<T> loader = new JavaBeanLoader<T>((Class<S>)javabean);
        return loader.load(yaml);
    }
    
    public static <T> T load(final InputStream io, final Class<T> javabean) {
        final JavaBeanLoader<T> loader = new JavaBeanLoader<T>((Class<S>)javabean);
        return loader.load(io);
    }
    
    public static <T> T load(final Reader io, final Class<T> javabean) {
        final JavaBeanLoader<T> loader = new JavaBeanLoader<T>((Class<S>)javabean);
        return loader.load(io);
    }
}
