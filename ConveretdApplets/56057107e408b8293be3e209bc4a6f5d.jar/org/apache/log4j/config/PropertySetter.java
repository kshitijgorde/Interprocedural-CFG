// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.config;

import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.Level;
import java.lang.reflect.Method;
import java.util.Enumeration;
import org.apache.log4j.Appender;
import org.apache.log4j.helpers.OptionConverter;
import java.util.Properties;
import java.beans.IntrospectionException;
import org.apache.log4j.helpers.LogLog;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class PropertySetter
{
    protected Object obj;
    protected PropertyDescriptor[] props;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$log4j$Priority;
    
    public PropertySetter(final Object obj) {
        this.obj = obj;
    }
    
    protected void introspect() {
        try {
            this.props = Introspector.getBeanInfo(this.obj.getClass()).getPropertyDescriptors();
        }
        catch (IntrospectionException ex) {
            LogLog.error("Failed to introspect " + this.obj + ": " + ex.getMessage());
            this.props = new PropertyDescriptor[0];
        }
    }
    
    public static void setProperties(final Object o, final Properties properties, final String s) {
        new PropertySetter(o).setProperties(properties, s);
    }
    
    public void setProperties(final Properties properties, final String s) {
        final int length = s.length();
        final Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final String s2 = (String)propertyNames.nextElement();
            if (s2.startsWith(s)) {
                if (s2.indexOf(46, length + 1) > 0) {
                    continue;
                }
                final String andSubst = OptionConverter.findAndSubst(s2, properties);
                final String substring = s2.substring(length);
                if ("layout".equals(substring) && this.obj instanceof Appender) {
                    continue;
                }
                this.setProperty(substring, andSubst);
            }
        }
        this.activate();
    }
    
    public void setProperty(String decapitalize, final String s) {
        if (s == null) {
            return;
        }
        decapitalize = Introspector.decapitalize(decapitalize);
        final PropertyDescriptor propertyDescriptor = this.getPropertyDescriptor(decapitalize);
        if (propertyDescriptor == null) {
            LogLog.warn("No such property [" + decapitalize + "] in " + this.obj.getClass().getName() + ".");
        }
        else {
            try {
                this.setProperty(propertyDescriptor, decapitalize, s);
            }
            catch (PropertySetterException ex) {
                LogLog.warn("Failed to set property [" + decapitalize + "] to value \"" + s + "\". ", ex.rootCause);
            }
        }
    }
    
    public void setProperty(final PropertyDescriptor propertyDescriptor, final String s, final String s2) throws PropertySetterException {
        final Method writeMethod = propertyDescriptor.getWriteMethod();
        if (writeMethod == null) {
            throw new PropertySetterException("No setter for property [" + s + "].");
        }
        final Class<?>[] parameterTypes = writeMethod.getParameterTypes();
        if (parameterTypes.length != 1) {
            throw new PropertySetterException("#params for setter != 1");
        }
        Object convertArg;
        try {
            convertArg = this.convertArg(s2, parameterTypes[0]);
        }
        catch (Throwable t) {
            throw new PropertySetterException("Conversion to type [" + parameterTypes[0] + "] failed. Reason: " + t);
        }
        if (convertArg == null) {
            throw new PropertySetterException("Conversion to type [" + parameterTypes[0] + "] failed.");
        }
        LogLog.debug("Setting property [" + s + "] to [" + convertArg + "].");
        try {
            writeMethod.invoke(this.obj, convertArg);
        }
        catch (Exception ex) {
            throw new PropertySetterException(ex);
        }
    }
    
    protected Object convertArg(final String s, final Class clazz) {
        if (s == null) {
            return null;
        }
        final String trim = s.trim();
        if (((PropertySetter.class$java$lang$String == null) ? (PropertySetter.class$java$lang$String = class$("java.lang.String")) : PropertySetter.class$java$lang$String).isAssignableFrom(clazz)) {
            return s;
        }
        if (Integer.TYPE.isAssignableFrom(clazz)) {
            return new Integer(trim);
        }
        if (Long.TYPE.isAssignableFrom(clazz)) {
            return new Long(trim);
        }
        if (Boolean.TYPE.isAssignableFrom(clazz)) {
            if ("true".equalsIgnoreCase(trim)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(trim)) {
                return Boolean.FALSE;
            }
        }
        else if (((PropertySetter.class$org$apache$log4j$Priority == null) ? (PropertySetter.class$org$apache$log4j$Priority = class$("org.apache.log4j.Priority")) : PropertySetter.class$org$apache$log4j$Priority).isAssignableFrom(clazz)) {
            return OptionConverter.toLevel(trim, Level.DEBUG);
        }
        return null;
    }
    
    protected PropertyDescriptor getPropertyDescriptor(final String s) {
        if (this.props == null) {
            this.introspect();
        }
        for (int i = 0; i < this.props.length; ++i) {
            if (s.equals(this.props[i].getName())) {
                return this.props[i];
            }
        }
        return null;
    }
    
    public void activate() {
        if (this.obj instanceof OptionHandler) {
            ((OptionHandler)this.obj).activateOptions();
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
