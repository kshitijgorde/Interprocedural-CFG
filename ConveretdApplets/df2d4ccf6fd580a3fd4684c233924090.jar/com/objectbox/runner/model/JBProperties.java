// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Properties;
import java.io.Serializable;

public abstract class JBProperties implements Serializable
{
    protected Properties properties;
    protected Properties parameters;
    protected Properties jbee_props;
    static final long serialVersionUID = -123456789L;
    
    public JBProperties() {
        this.properties = new Properties();
        this.parameters = new Properties();
        this.jbee_props = new Properties();
        this.init();
    }
    
    public JBProperties(final JBProperties jbProperties) {
        this.properties = new Properties();
        this.parameters = new Properties();
        this.jbee_props = new Properties();
        this.properties = this.copy(jbProperties.properties);
        this.parameters = this.copy(jbProperties.parameters);
        this.jbee_props = this.copy(jbProperties.jbee_props);
    }
    
    public void cleanup() {
        if (this.parameters != null) {
            final Enumeration<Object> keys = ((Hashtable<Object, V>)this.parameters).keys();
            while (keys.hasMoreElements()) {
                this.parameters.remove(keys.nextElement());
            }
            this.parameters.clear();
        }
        if (this.properties != null) {
            final Enumeration<Object> keys2 = ((Hashtable<Object, V>)this.properties).keys();
            while (keys2.hasMoreElements()) {
                this.properties.remove(keys2.nextElement());
            }
            this.properties.clear();
        }
    }
    
    private Properties copy(final Properties properties) {
        final Properties properties2 = new Properties();
        final Enumeration<String> keys = ((Hashtable<String, V>)properties).keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            ((Hashtable<String, String>)properties2).put(new String(s), new String((String)properties.get(s)));
        }
        return properties2;
    }
    
    public Properties getJBeeProps() {
        return this.jbee_props;
    }
    
    public Properties getParameters() {
        return this.parameters;
    }
    
    public abstract JBPropertyEditor getPropertyEditor();
    
    public Properties getProps() {
        return this.properties;
    }
    
    public void init() {
        ((Hashtable<String, String>)this.jbee_props).put("Name", " ");
        ((Hashtable<String, String>)this.jbee_props).put("Window type", "Standard");
        ((Hashtable<String, String>)this.jbee_props).put("Security", "High");
    }
    
    public String toString() {
        return String.valueOf(this.parameters.toString()) + "\n" + this.properties.toString() + this.jbee_props.toString();
    }
}
