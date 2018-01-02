// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import org.jboss.util.UnreachableStatementException;
import org.jboss.util.propertyeditor.PropertyEditors;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.io.InputStream;
import org.jboss.mx.server.ObjectInputStreamWithClassLoader;
import java.beans.PropertyEditor;
import java.util.Map;
import java.beans.PropertyEditorManager;
import javax.management.MalformedObjectNameException;
import javax.management.InstanceNotFoundException;
import javax.management.ReflectionException;
import javax.management.MBeanException;
import javax.management.ObjectInstance;
import org.jboss.mx.loading.MBeanElement;
import javax.management.ObjectName;
import javax.management.MBeanServer;
import org.jboss.logging.Logger;

public class MBeanInstaller
{
    public static final String VERSIONS = "versions";
    public static final String DATE = "date";
    private static final Logger log;
    private MBeanServer server;
    private ClassLoader ctxClassLoader;
    private ObjectName loaderName;
    private ObjectName registryName;
    
    public MBeanInstaller(final MBeanServer server, final ClassLoader ctxClassLoader, final ObjectName loaderName) throws Exception {
        this.server = server;
        this.ctxClassLoader = ctxClassLoader;
        this.loaderName = loaderName;
        this.registryName = new ObjectName("JMImplementation:type=MBeanRegistry");
    }
    
    public ObjectInstance installMBean(final MBeanElement element) throws MBeanException, ReflectionException, InstanceNotFoundException, MalformedObjectNameException {
        MBeanInstaller.log.debug("Installing MBean: " + element);
        ObjectInstance instance = null;
        final ObjectName elementName = this.getElementName(element);
        if (element.getVersions().isEmpty() || !this.server.isRegistered(elementName)) {
            if (element.getCode() != null) {
                instance = this.createMBean(element);
            }
            else {
                if (element.getObject() == null) {
                    throw new MBeanException(new IllegalArgumentException("No code or object tag"));
                }
                instance = this.deserialize(element);
            }
        }
        else {
            instance = this.updateMBean(element);
        }
        return instance;
    }
    
    public ObjectInstance createMBean(final MBeanElement element) throws MBeanException, ReflectionException, InstanceNotFoundException, MalformedObjectNameException {
        MBeanInstaller.log.debug("Creating MBean.. ");
        final ObjectName elementName = this.getElementName(element);
        final Map valueMap = this.createValueMap(element);
        final String[] classes = element.getConstructorTypes();
        final String[] paramStrings = element.getConstructorValues();
        final Object[] params = new Object[paramStrings.length];
        for (int i = 0; i < paramStrings.length; ++i) {
            try {
                final Class typeClass = this.server.getClassLoaderRepository().loadClass(classes[i]);
                final PropertyEditor editor = PropertyEditorManager.findEditor(typeClass);
                if (editor == null) {
                    throw new IllegalArgumentException("No property editor for type=" + typeClass);
                }
                editor.setAsText(paramStrings[i]);
                params[i] = editor.getValue();
            }
            catch (Exception e) {
                throw new MBeanException(e);
            }
        }
        final Object instance = this.server.instantiate(element.getCode(), this.loaderName, params, classes);
        return this.registerMBean(instance, elementName, valueMap);
    }
    
    public ObjectInstance deserialize(final MBeanElement element) throws MBeanException, ReflectionException, InstanceNotFoundException, MalformedObjectNameException {
        InputStream is = null;
        Object instance = null;
        try {
            is = this.ctxClassLoader.getResourceAsStream(element.getObject());
            if (is == null) {
                throw new IllegalArgumentException("Object not found " + element.getObject());
            }
            final ObjectInputStreamWithClassLoader ois = new ObjectInputStreamWithClassLoader(is, this.ctxClassLoader);
            instance = ois.readObject();
        }
        catch (Exception e) {
            throw new MBeanException(e);
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (Exception ex) {}
            }
        }
        final ObjectName elementName = this.getElementName(element);
        final Map valueMap = this.createValueMap(element);
        return this.registerMBean(instance, elementName, valueMap);
    }
    
    public ObjectInstance updateMBean(final MBeanElement element) throws MBeanException, ReflectionException, InstanceNotFoundException, MalformedObjectNameException {
        MBeanInstaller.log.debug("updating MBean... ");
        final ObjectName elementName = this.getElementName(element);
        final MLetVersion preVersion = new MLetVersion(this.getVersions(elementName));
        final MLetVersion newVersion = new MLetVersion(element.getVersions());
        MBeanInstaller.log.debug("Installed version : " + preVersion);
        MBeanInstaller.log.debug("Loaded version    : " + newVersion);
        if (!preVersion.isNull() && !newVersion.isNull() && preVersion.compareTo(newVersion) < 0) {
            if (this.server.isRegistered(elementName)) {
                this.unregisterMBean(elementName);
                MBeanInstaller.log.debug("Unregistering previous version " + preVersion);
            }
            MBeanInstaller.log.debug("Installing newer version " + newVersion);
            return this.createMBean(element);
        }
        return this.server.getObjectInstance(elementName);
    }
    
    private ObjectName getElementName(final MBeanElement element) throws MalformedObjectNameException {
        return (element.getName() != null) ? new ObjectName(element.getName()) : null;
    }
    
    private Map createValueMap(final MBeanElement element) {
        final HashMap valueMap = new HashMap();
        if (element.getVersions() != null && !element.getVersions().isEmpty()) {
            valueMap.put("versions", element.getVersions());
        }
        valueMap.put("date", new Date(System.currentTimeMillis()));
        valueMap.put("org.jboss.mx.classloader", this.ctxClassLoader);
        return valueMap;
    }
    
    private List getVersions(final ObjectName name) throws MBeanException, ReflectionException, InstanceNotFoundException {
        if (!this.server.isRegistered(name)) {
            return null;
        }
        return (List)this.getValue(name, "versions");
    }
    
    private Object getValue(final ObjectName name, final String key) throws MBeanException, ReflectionException, InstanceNotFoundException {
        final Object value = this.server.invoke(this.registryName, "getValue", new Object[] { name, key }, new String[] { ObjectName.class.getName(), String.class.getName() });
        return value;
    }
    
    private ObjectInstance registerMBean(final Object object, final ObjectName name, final Map valueMap) throws MBeanException, ReflectionException, InstanceNotFoundException {
        if (object == null) {
            throw new ReflectionException(new IllegalArgumentException("Attempting to register a null object"));
        }
        return (ObjectInstance)this.server.invoke(this.registryName, "registerMBean", new Object[] { object, name, valueMap }, new String[] { Object.class.getName(), ObjectName.class.getName(), Map.class.getName() });
    }
    
    private void unregisterMBean(final ObjectName name) throws MBeanException, ReflectionException, InstanceNotFoundException {
        this.server.invoke(this.registryName, "unregisterMBean", new Object[] { name }, new String[] { ObjectName.class.getName() });
    }
    
    static {
        log = Logger.getLogger(MBeanInstaller.class);
        final Class c = PropertyEditors.class;
        if (c == null) {
            throw new UnreachableStatementException();
        }
    }
}
