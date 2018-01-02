// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import java.util.Hashtable;
import org.xml.sax.ContentHandler;
import java.io.IOException;
import javax.xml.transform.TransformerException;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.xalan.serialize.SerializerFactory;
import org.apache.xalan.templates.OutputProperties;
import org.apache.xalan.serialize.Serializer;
import java.util.Properties;

public class SerializerSwitcher
{
    private static String getOutputPropertyNoDefault(final String qnameString, final Properties props) throws IllegalArgumentException {
        final String value = ((Hashtable<K, String>)props).get(qnameString);
        return value;
    }
    
    public static Serializer switchSerializerIfHTML(final String ns, final String localName, final Properties props, final Serializer oldSerializer) throws TransformerException {
        Serializer newSerializer = oldSerializer;
        if ((ns == null || ns.length() == 0) && localName.equalsIgnoreCase("html")) {
            if (getOutputPropertyNoDefault("method", props) != null) {
                return newSerializer;
            }
            final OutputProperties htmlOutputProperties = new OutputProperties("html");
            htmlOutputProperties.copyFrom(props, true);
            final Properties htmlProperties = htmlOutputProperties.getProperties();
            if (oldSerializer != null) {
                final Serializer serializer = SerializerFactory.getSerializer(htmlProperties);
                final Writer writer = oldSerializer.getWriter();
                if (writer != null) {
                    serializer.setWriter(writer);
                }
                else {
                    final OutputStream os = serializer.getOutputStream();
                    if (os != null) {
                        serializer.setOutputStream(os);
                    }
                }
                newSerializer = serializer;
            }
        }
        return newSerializer;
    }
    
    public static void switchSerializerIfHTML(final TransformerImpl transformer, final String ns, final String localName) throws TransformerException {
        if (transformer == null) {
            return;
        }
        if ((ns == null || ns.length() == 0) && localName.equalsIgnoreCase("html")) {
            if (transformer.getOutputPropertyNoDefault("method") != null) {
                return;
            }
            final Properties prevProperties = transformer.getOutputFormat().getProperties();
            final OutputProperties htmlOutputProperties = new OutputProperties("html");
            htmlOutputProperties.copyFrom(prevProperties, true);
            final Properties htmlProperties = htmlOutputProperties.getProperties();
            try {
                final Serializer oldSerializer = transformer.getSerializer();
                if (oldSerializer != null) {
                    final Serializer serializer = SerializerFactory.getSerializer(htmlProperties);
                    final Writer writer = oldSerializer.getWriter();
                    if (writer != null) {
                        serializer.setWriter(writer);
                    }
                    else {
                        final OutputStream os = serializer.getOutputStream();
                        if (os != null) {
                            serializer.setOutputStream(os);
                        }
                    }
                    transformer.setSerializer(serializer);
                    final ContentHandler ch = serializer.asContentHandler();
                    transformer.setContentHandler(ch);
                }
            }
            catch (IOException e) {
                throw new TransformerException(e);
            }
        }
    }
}
