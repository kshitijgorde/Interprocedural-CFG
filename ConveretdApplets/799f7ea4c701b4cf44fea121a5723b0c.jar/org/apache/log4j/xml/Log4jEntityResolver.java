// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.xml;

import java.io.InputStream;
import org.apache.log4j.helpers.LogLog;
import org.xml.sax.InputSource;
import org.xml.sax.EntityResolver;

public class Log4jEntityResolver implements EntityResolver
{
    public InputSource resolveEntity(final String publicId, final String systemId) {
        if (!systemId.endsWith("log4j.dtd")) {
            return null;
        }
        final Class clazz = this.getClass();
        final InputStream in = clazz.getResourceAsStream("/org/apache/log4j/xml/log4j.dtd");
        if (in == null) {
            LogLog.error("Could not find [log4j.dtd]. Used [" + clazz.getClassLoader() + "] class loader in the search.");
            return null;
        }
        return new InputSource(in);
    }
}
