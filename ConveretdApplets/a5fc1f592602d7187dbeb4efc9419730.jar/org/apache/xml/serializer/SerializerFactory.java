// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xml.res.XMLMessages;
import org.xml.sax.ContentHandler;
import java.util.Properties;
import java.util.Hashtable;

public abstract class SerializerFactory
{
    private static Hashtable m_formats;
    
    public static Serializer getSerializer(final Properties format) {
        Serializer ser;
        try {
            final String method = format.getProperty("method");
            if (method == null) {
                throw new IllegalArgumentException("The output format has a null method name");
            }
            String className = format.getProperty("{http://xml.apache.org/xalan}content-handler");
            if (null == className) {
                final Properties methodDefaults = OutputPropertiesFactory.getDefaultMethodProperties(method);
                className = methodDefaults.getProperty("{http://xml.apache.org/xalan}content-handler");
                if (null == className) {
                    throw new IllegalArgumentException("The output format must have a '{http://xml.apache.org/xalan}content-handler' property!");
                }
            }
            final ClassLoader loader = ObjectFactory.findClassLoader();
            Class cls = ObjectFactory.findProviderClass(className, loader, true);
            final Object obj = cls.newInstance();
            if (obj instanceof SerializationHandler) {
                ser = cls.newInstance();
                ser.setOutputFormat(format);
            }
            else {
                if (!(obj instanceof ContentHandler)) {
                    throw new Exception(XMLMessages.createXMLMessage("ER_SERIALIZER_NOT_CONTENTHANDLER", new Object[] { className }));
                }
                className = "org.apache.xml.serializer.ToXMLSAXHandler";
                cls = ObjectFactory.findProviderClass(className, loader, true);
                final SerializationHandler sh = cls.newInstance();
                sh.setContentHandler((ContentHandler)obj);
                sh.setOutputFormat(format);
                ser = sh;
            }
        }
        catch (Exception e) {
            throw new WrappedRuntimeException(e);
        }
        return ser;
    }
    
    static {
        SerializerFactory.m_formats = new Hashtable();
    }
}
