// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base.config;

import java.util.Set;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Properties;

public class HierarchicalConfiguration implements ModifiableConfiguration
{
    private Properties configuration;
    private transient ModifiableConfiguration parentConfiguration;
    
    public HierarchicalConfiguration() {
        this.configuration = new Properties();
    }
    
    public HierarchicalConfiguration(final ModifiableConfiguration parentConfiguration) {
        this();
        this.parentConfiguration = parentConfiguration;
    }
    
    public String getConfigProperty(final String s) {
        return this.getConfigProperty(s, null);
    }
    
    public String getConfigProperty(final String s, final String s2) {
        String s3 = this.configuration.getProperty(s);
        if (s3 == null) {
            if (this.isRootConfig()) {
                s3 = s2;
            }
            else {
                s3 = this.parentConfiguration.getConfigProperty(s, s2);
            }
        }
        return s3;
    }
    
    public void setConfigProperty(final String s, final String s2) {
        if (s == null) {
            throw new NullPointerException();
        }
        if (s2 == null) {
            this.configuration.remove(s);
        }
        else {
            this.configuration.setProperty(s, s2);
        }
    }
    
    private boolean isRootConfig() {
        return this.parentConfiguration == null;
    }
    
    public boolean isLocallyDefined(final String s) {
        return this.configuration.containsKey(s);
    }
    
    protected Properties getConfiguration() {
        return this.configuration;
    }
    
    public void insertConfiguration(final HierarchicalConfiguration parentConfig) {
        parentConfig.setParentConfig(this.getParentConfig());
        this.setParentConfig(parentConfig);
    }
    
    protected void setParentConfig(final ModifiableConfiguration parentConfiguration) {
        if (this.parentConfiguration == this) {
            throw new IllegalArgumentException("Cannot add myself as parent configuration.");
        }
        this.parentConfiguration = parentConfiguration;
    }
    
    protected ModifiableConfiguration getParentConfig() {
        return this.parentConfiguration;
    }
    
    public Enumeration getConfigProperties() {
        return this.configuration.keys();
    }
    
    public Iterator findPropertyKeys(final String s) {
        final TreeSet<Object> set = new TreeSet<Object>();
        this.collectPropertyKeys(s, this, set);
        return Collections.unmodifiableSet((Set<?>)set).iterator();
    }
    
    private void collectPropertyKeys(final String s, final ModifiableConfiguration modifiableConfiguration, final TreeSet set) {
        final Enumeration configProperties = modifiableConfiguration.getConfigProperties();
        while (configProperties.hasMoreElements()) {
            final String s2 = configProperties.nextElement();
            if (s2.startsWith(s) && !set.contains(s2)) {
                set.add(s2);
            }
        }
        if (modifiableConfiguration instanceof HierarchicalConfiguration) {
            final HierarchicalConfiguration hierarchicalConfiguration = (HierarchicalConfiguration)modifiableConfiguration;
            if (hierarchicalConfiguration.parentConfiguration != null) {
                this.collectPropertyKeys(s, hierarchicalConfiguration.parentConfiguration, set);
            }
        }
    }
}
