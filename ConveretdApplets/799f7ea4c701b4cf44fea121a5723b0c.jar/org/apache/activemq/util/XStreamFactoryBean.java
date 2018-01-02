// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import org.springframework.util.Assert;
import java.util.Iterator;
import org.springframework.beans.propertyeditors.ClassEditor;
import java.util.Map;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.ConverterMatcher;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.FactoryBean;

public class XStreamFactoryBean implements FactoryBean
{
    XStream xstream;
    
    public XStreamFactoryBean() {
        this.xstream = new XStream();
    }
    
    public void setConverters(final ConverterMatcher[] converters) {
        for (int i = 0; i < converters.length; ++i) {
            if (converters[i] instanceof Converter) {
                this.xstream.registerConverter((Converter)converters[i], i);
            }
            else {
                if (!(converters[i] instanceof SingleValueConverter)) {
                    throw new IllegalArgumentException("Invalid ConverterMatcher [" + converters[i] + "]");
                }
                this.xstream.registerConverter((SingleValueConverter)converters[i], i);
            }
        }
    }
    
    public void setAliases(final Map aliases) {
        for (final Map.Entry entry : aliases.entrySet()) {
            Class type;
            if (entry.getValue() instanceof Class) {
                type = entry.getValue();
            }
            else {
                final ClassEditor editor = new ClassEditor();
                editor.setAsText(String.valueOf(entry.getValue()));
                type = (Class)editor.getValue();
            }
            this.xstream.alias((String)entry.getKey(), type);
        }
    }
    
    public void setMode(final int mode) {
        this.xstream.setMode(mode);
    }
    
    public void setAnnotatedClass(final Class<?> annotatedClass) {
        Assert.notNull((Object)annotatedClass, "'annotatedClass' must not be null");
        this.xstream.processAnnotations((Class)annotatedClass);
    }
    
    public void setAnnotatedClasses(final Class<?>[] annotatedClasses) {
        Assert.notEmpty((Object[])annotatedClasses, "'annotatedClasses' must not be empty");
        this.xstream.processAnnotations((Class[])annotatedClasses);
    }
    
    public Object getObject() throws Exception {
        return this.xstream;
    }
    
    public Class getObjectType() {
        return XStream.class;
    }
    
    public boolean isSingleton() {
        return true;
    }
}
