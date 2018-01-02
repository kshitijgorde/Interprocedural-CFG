// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.util.Iterator;
import org.jfree.util.Log;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.TreeSet;

public class BeanObjectDescription extends AbstractObjectDescription
{
    private TreeSet ignoredParameters;
    
    public BeanObjectDescription(final Class clazz) {
        this(clazz, true);
    }
    
    public BeanObjectDescription(final Class clazz, final boolean b) {
        super(clazz);
        this.ignoredParameters = new TreeSet();
        if (!b) {
            return;
        }
        final Method[] methods = this.getObjectClass().getMethods();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            if (Modifier.isPublic(method.getModifiers())) {
                if (!Modifier.isStatic(method.getModifiers())) {
                    if (method.getParameterTypes().length == 1) {
                        if (method.getName().startsWith("set")) {
                            try {
                                final String propertyName = this.getPropertyName(method.getName());
                                this.findGetMethod(propertyName, method.getParameterTypes()[0]);
                                this.setParameterDefinition(propertyName, method.getParameterTypes()[0]);
                            }
                            catch (NoSuchMethodException ex) {}
                        }
                    }
                }
            }
        }
    }
    
    public Object createObject() {
        try {
            final Object instance = this.getObjectClass().newInstance();
            final Iterator parameterNames = this.getParameterNames();
            while (parameterNames.hasNext()) {
                final String s = parameterNames.next();
                if (this.isParameterIgnored(s)) {
                    continue;
                }
                final Method setMethod = this.findSetMethod(s);
                final Object parameter = this.getParameter(s);
                if (parameter == null) {
                    continue;
                }
                setMethod.invoke(instance, parameter);
            }
            return instance;
        }
        catch (Exception ex) {
            Log.error("Unable to invoke bean method", ex);
            return null;
        }
    }
    
    private Method findSetMethod(final String s) throws NoSuchMethodException {
        return this.getObjectClass().getMethod(this.getSetterName(s), this.getParameterDefinition(s));
    }
    
    private Method findGetMethod(final String s, final Class clazz) throws NoSuchMethodException {
        return this.getObjectClass().getMethod(this.getGetterName(s, clazz), (Class[])new Class[0]);
    }
    
    private String getSetterName(final String s) {
        if (s.length() == 0) {
            return "set";
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("set");
        sb.append(Character.toUpperCase(s.charAt(0)));
        if (s.length() > 1) {
            sb.append(s.substring(1));
        }
        return sb.toString();
    }
    
    private String getGetterName(final String s, final Class clazz) {
        String s2 = "get";
        if (Boolean.TYPE.equals(clazz)) {
            s2 = "is";
        }
        if (s.length() == 0) {
            return s2;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(s2);
        sb.append(Character.toUpperCase(s.charAt(0)));
        if (s.length() > 1) {
            sb.append(s.substring(1));
        }
        return sb.toString();
    }
    
    private String getPropertyName(final String s) {
        if (s.length() < 3) {
            throw new IllegalArgumentException();
        }
        if (s.length() == 3) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(Character.toLowerCase(s.charAt(3)));
        if (s.length() > 4) {
            sb.append(s.substring(4));
        }
        return sb.toString();
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (o == null) {
            throw new NullPointerException("Given object is null");
        }
        final Class objectClass = this.getObjectClass();
        if (!objectClass.isInstance(o)) {
            throw new ObjectFactoryException("Object is no instance of " + objectClass + "(is " + o.getClass() + ")");
        }
        final Iterator parameterNames = this.getParameterNames();
        while (parameterNames.hasNext()) {
            final String s = parameterNames.next();
            if (this.isParameterIgnored(s)) {
                continue;
            }
            try {
                final Object invoke = this.findGetMethod(s, this.getParameterDefinition(s)).invoke(o, new Object[0]);
                if (invoke == null) {
                    continue;
                }
                this.setParameter(s, invoke);
            }
            catch (Exception ex) {
                Log.info("Exception on method invokation.", ex);
            }
        }
    }
    
    protected void ignoreParameter(final String s) {
        this.ignoredParameters.add(s);
    }
    
    protected boolean isParameterIgnored(final String s) {
        return this.ignoredParameters.contains(s);
    }
}
