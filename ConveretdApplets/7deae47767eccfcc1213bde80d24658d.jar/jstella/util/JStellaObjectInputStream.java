// 
// Decompiled by Procyon v0.5.30
// 

package jstella.util;

import java.util.HashMap;
import java.io.ObjectStreamClass;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.io.ObjectInputStream;

public class JStellaObjectInputStream extends ObjectInputStream
{
    private static Map<Long, Class<?>> myClassRegistry;
    
    public JStellaObjectInputStream(final InputStream in) throws IOException {
        super(in);
    }
    
    protected Class<?> resolveClass(final ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        final long zSerialVersionUID = desc.getSerialVersionUID();
        if (JStellaObjectInputStream.myClassRegistry.containsKey(zSerialVersionUID)) {
            return JStellaObjectInputStream.myClassRegistry.get(zSerialVersionUID);
        }
        return super.resolveClass(desc);
    }
    
    public static void registerClass(final Class<?> aClass, final long aSerialVersionUID) {
        JStellaObjectInputStream.myClassRegistry.put(aSerialVersionUID, aClass);
    }
    
    static {
        JStellaObjectInputStream.myClassRegistry = new HashMap<Long, Class<?>>();
    }
}
