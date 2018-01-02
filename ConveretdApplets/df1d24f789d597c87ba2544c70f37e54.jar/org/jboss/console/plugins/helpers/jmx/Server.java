// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.jmx;

import javax.management.IntrospectionException;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;
import javax.management.Attribute;
import javax.management.AttributeList;
import java.util.HashMap;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import java.util.Set;
import javax.management.QueryExp;
import javax.management.ObjectName;
import java.util.TreeMap;
import java.util.Iterator;
import javax.management.JMException;
import org.jboss.mx.util.MBeanServerLocator;
import javax.management.MBeanServer;
import org.jboss.logging.Logger;

public class Server
{
    static Logger log;
    
    public static MBeanServer getMBeanServer() throws JMException {
        return MBeanServerLocator.locateJBoss();
    }
    
    public static Iterator getDomainData(final String filter) throws JMException {
        final MBeanServer server = getMBeanServer();
        final TreeMap domainData = new TreeMap();
        if (server != null) {
            ObjectName filterName = null;
            if (filter != null) {
                filterName = new ObjectName(filter);
            }
            final Set objectNames = server.queryNames(filterName, null);
            for (final ObjectName name : objectNames) {
                final MBeanInfo info = server.getMBeanInfo(name);
                final String domainName = name.getDomain();
                final MBeanData mbeanData = new MBeanData(name, info);
                DomainData data = domainData.get(domainName);
                if (data == null) {
                    data = new DomainData(domainName);
                    domainData.put(domainName, data);
                }
                data.addData(mbeanData);
            }
        }
        final Iterator domainDataIter = domainData.values().iterator();
        return domainDataIter;
    }
    
    public static MBeanData getMBeanData(final String name) throws JMException {
        final MBeanServer server = getMBeanServer();
        final ObjectName objName = new ObjectName(name);
        final MBeanInfo info = server.getMBeanInfo(objName);
        final MBeanData mbeanData = new MBeanData(objName, info);
        return mbeanData;
    }
    
    public static Object getMBeanAttributeObject(final String name, final String attrName) throws JMException {
        final MBeanServer server = getMBeanServer();
        final ObjectName objName = new ObjectName(name);
        final Object value = server.getAttribute(objName, attrName);
        return value;
    }
    
    public static String getMBeanAttribute(final String name, final String attrName) throws JMException {
        final MBeanServer server = getMBeanServer();
        final ObjectName objName = new ObjectName(name);
        String value = null;
        try {
            final Object attr = server.getAttribute(objName, attrName);
            if (attr != null) {
                value = attr.toString();
            }
        }
        catch (JMException e) {
            value = e.getMessage();
        }
        return value;
    }
    
    public static AttrResultInfo getMBeanAttributeResultInfo(final String name, final MBeanAttributeInfo attrInfo) throws JMException {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final MBeanServer server = getMBeanServer();
        final ObjectName objName = new ObjectName(name);
        final String attrName = attrInfo.getName();
        final String attrType = attrInfo.getType();
        Object value = null;
        Throwable throwable = null;
        if (attrInfo.isReadable()) {
            try {
                value = server.getAttribute(objName, attrName);
            }
            catch (Throwable t) {
                throwable = t;
            }
        }
        Class typeClass = null;
        try {
            typeClass = getPrimativeClass(attrType);
            if (typeClass == null) {
                typeClass = loader.loadClass(attrType);
            }
        }
        catch (ClassNotFoundException ex) {}
        PropertyEditor editor = null;
        if (typeClass != null) {
            editor = PropertyEditorManager.findEditor(typeClass);
        }
        return new AttrResultInfo(attrName, editor, value, throwable);
    }
    
    public static AttributeList setAttributes(final String name, final HashMap attributes) throws JMException {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final MBeanServer server = getMBeanServer();
        final ObjectName objName = new ObjectName(name);
        final MBeanInfo info = server.getMBeanInfo(objName);
        final MBeanAttributeInfo[] attributesInfo = info.getAttributes();
        final AttributeList newAttributes = new AttributeList();
        for (int a = 0; a < attributesInfo.length; ++a) {
            final MBeanAttributeInfo attrInfo = attributesInfo[a];
            final String attrName = attrInfo.getName();
            if (attributes.containsKey(attrName)) {
                final String value = attributes.get(attrName);
                final String attrType = attrInfo.getType();
                Attribute attr = null;
                try {
                    Class argType = getPrimativeClass(attrType);
                    if (argType == null) {
                        argType = loader.loadClass(attrType);
                    }
                    final PropertyEditor editor = PropertyEditorManager.findEditor(argType);
                    if (editor == null) {
                        Server.log.trace("Failed to find PropertyEditor for type: " + attrType);
                        continue;
                    }
                    editor.setAsText(value);
                    attr = new Attribute(attrName, editor.getValue());
                }
                catch (ClassNotFoundException e) {
                    Server.log.trace("Failed to load class for attribute: " + ((attr == null) ? "null" : attr.getName()), e);
                    throw new ReflectionException(e, "Failed to load class for attribute: " + ((attr == null) ? "null" : attr.getName()));
                }
                server.setAttribute(objName, attr);
                newAttributes.add(attr);
            }
        }
        return newAttributes;
    }
    
    public static OpResultInfo invokeOp(final String name, final int index, final String[] args) throws JMException {
        final MBeanServer server = getMBeanServer();
        final ObjectName objName = new ObjectName(name);
        final MBeanInfo info = server.getMBeanInfo(objName);
        final MBeanOperationInfo[] opInfo = info.getOperations();
        final MBeanOperationInfo op = opInfo[index];
        final MBeanParameterInfo[] paramInfo = op.getSignature();
        final String[] argTypes = new String[paramInfo.length];
        for (int p = 0; p < paramInfo.length; ++p) {
            argTypes[p] = paramInfo[p].getType();
        }
        return invokeOpByName(name, op.getName(), argTypes, args);
    }
    
    public static OpResultInfo invokeOpByName(final String name, final String opName, final String[] argTypes, final String[] args) throws JMException {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final MBeanServer server = getMBeanServer();
        final ObjectName objName = new ObjectName(name);
        final int length = (argTypes != null) ? argTypes.length : 0;
        final Object[] typedArgs = new Object[length];
        for (int p = 0; p < typedArgs.length; ++p) {
            final String arg = args[p];
            try {
                Class argType = getPrimativeClass(argTypes[p]);
                if (argType == null) {
                    argType = loader.loadClass(argTypes[p]);
                }
                final PropertyEditor editor = PropertyEditorManager.findEditor(argType);
                if (editor == null) {
                    throw new IntrospectionException("Failed to find PropertyEditor for type: " + argTypes[p]);
                }
                editor.setAsText(arg);
                typedArgs[p] = editor.getValue();
            }
            catch (ClassNotFoundException e) {
                Server.log.trace("Failed to load class for arg" + p, e);
                throw new ReflectionException(e, "Failed to load class for arg" + p);
            }
        }
        final Object opReturn = server.invoke(objName, opName, typedArgs, argTypes);
        return new OpResultInfo(opName, argTypes, opReturn);
    }
    
    static Class getPrimativeClass(final String type) {
        final String[] primatives = { "boolean", "byte", "char", "int", "short", "float", "double", "long" };
        final Class[] primativeTypes = { Boolean.TYPE, Byte.TYPE, Character.TYPE, Integer.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Long.TYPE };
        Class c = null;
        for (int p = 0; p < primatives.length; ++p) {
            if (type.equals(primatives[p])) {
                c = primativeTypes[p];
            }
        }
        return c;
    }
    
    static {
        Server.log = Logger.getLogger(Server.class);
    }
}
