// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import java.util.Hashtable;
import org.xml.sax.ContentHandler;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.xml.serializer.Serializer;
import java.util.Properties;
import java.io.IOException;
import javax.xml.transform.TransformerException;
import org.apache.xml.serializer.SerializerFactory;
import org.apache.xalan.templates.OutputProperties;

public class SerializerSwitcher
{
    public static void switchSerializerIfHTML(final TransformerImpl transformer, final String ns, final String localName) throws TransformerException {
        if (null == transformer) {
            return;
        }
        if ((null == ns || ns.length() == 0) && localName.equalsIgnoreCase("html")) {
            if (null != transformer.getOutputPropertyNoDefault("method")) {
                return;
            }
            final Properties prevProperties = transformer.getOutputFormat().getProperties();
            final OutputProperties htmlOutputProperties = new OutputProperties("html");
            htmlOutputProperties.copyFrom(prevProperties, true);
            final Properties htmlProperties = htmlOutputProperties.getProperties();
            try {
                final Serializer oldSerializer = null;
                if (null != oldSerializer) {
                    final Serializer serializer = SerializerFactory.getSerializer(htmlProperties);
                    final Writer writer = oldSerializer.getWriter();
                    if (null != writer) {
                        serializer.setWriter(writer);
                    }
                    else {
                        final OutputStream os = oldSerializer.getOutputStream();
                        if (null != os) {
                            serializer.setOutputStream(os);
                        }
                    }
                    final ContentHandler ch = serializer.asContentHandler();
                    transformer.setContentHandler(ch);
                }
            }
            catch (IOException e) {
                throw new TransformerException(e);
            }
        }
    }
    
    private static String getOutputPropertyNoDefault(final String qnameString, final Properties props) throws IllegalArgumentException {
        final String value = ((Hashtable<K, String>)props).get(qnameString);
        return value;
    }
    
    public static Serializer switchSerializerIfHTML(final String ns, final String localName, final Properties props, final Serializer oldSerializer) throws TransformerException {
        Serializer newSerializer = oldSerializer;
        if ((null == ns || ns.length() == 0) && localName.equalsIgnoreCase("html")) {
            if (null != getOutputPropertyNoDefault("method", props)) {
                return newSerializer;
            }
            final OutputProperties htmlOutputProperties = new OutputProperties("html");
            htmlOutputProperties.copyFrom(props, true);
            final Properties htmlProperties = htmlOutputProperties.getProperties();
            if (null != oldSerializer) {
                final Serializer serializer = SerializerFactory.getSerializer(htmlProperties);
                final Writer writer = oldSerializer.getWriter();
                if (null != writer) {
                    serializer.setWriter(writer);
                }
                else {
                    final OutputStream os = serializer.getOutputStream();
                    if (null != os) {
                        serializer.setOutputStream(os);
                    }
                }
                newSerializer = serializer;
            }
        }
        return newSerializer;
    }
}
