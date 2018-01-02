// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xalan.templates.OutputProperties;
import java.util.Properties;
import java.util.Hashtable;

public abstract class SerializerFactory
{
    private static Hashtable _formats;
    
    static {
        SerializerFactory._formats = new Hashtable();
    }
    
    public static Serializer getSerializer(final Properties format) {
        Serializer ser = null;
        try {
            final String method = format.getProperty("method");
            if (method == null) {
                throw new IllegalArgumentException("The output format has not method name");
            }
            Class cls = null;
            if (cls == null) {
                final String className = format.getProperty(OutputProperties.S_KEY_CONTENT_HANDLER);
                if (className == null) {
                    throw new IllegalArgumentException("The output format must have a '" + OutputProperties.S_KEY_CONTENT_HANDLER + "' property!");
                }
                cls = Class.forName(className);
            }
            ser = cls.newInstance();
            ser.setOutputFormat(format);
        }
        catch (Exception e) {
            throw new WrappedRuntimeException(e);
        }
        return ser;
    }
}
