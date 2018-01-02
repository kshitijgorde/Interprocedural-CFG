// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.Enumeration;
import java.util.Set;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Iterator;
import org.jfree.base.config.ModifiableConfiguration;
import java.util.Properties;

public class DefaultConfiguration extends Properties implements ModifiableConfiguration
{
    public Iterator findPropertyKeys(final String prefix) {
        final TreeSet collector = new TreeSet();
        final Enumeration enum1 = this.keys();
        while (enum1.hasMoreElements()) {
            final String key = enum1.nextElement();
            if (key.startsWith(prefix) && !collector.contains(key)) {
                collector.add(key);
            }
        }
        return Collections.unmodifiableSet((Set<?>)collector).iterator();
    }
    
    public Enumeration getConfigProperties() {
        return this.keys();
    }
    
    public String getConfigProperty(final String key) {
        return this.getProperty(key);
    }
    
    public String getConfigProperty(final String key, final String defaultValue) {
        return this.getProperty(key, defaultValue);
    }
    
    public void setConfigProperty(final String key, final String value) {
        if (value == null) {
            this.remove(key);
        }
        else {
            this.setProperty(key, value);
        }
    }
}
