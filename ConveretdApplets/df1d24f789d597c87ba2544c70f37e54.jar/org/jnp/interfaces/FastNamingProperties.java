// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.interfaces;

import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Enumeration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class FastNamingProperties extends Properties
{
    private static final long serialVersionUID = 190486940953472275L;
    
    public Object setProperty(final String s1, final String s2) {
        throw new UnsupportedOperationException();
    }
    
    public void load(final InputStream is) throws IOException {
        throw new UnsupportedOperationException();
    }
    
    public String getProperty(final String s) {
        if (s.equals("jndi.syntax.direction")) {
            return "left_to_right";
        }
        if (s.equals("jndi.syntax.ignorecase")) {
            return "false";
        }
        if (s.equals("jndi.syntax.separator")) {
            return "/";
        }
        return null;
    }
    
    public String getProperty(final String name, final String defaultValue) {
        String ret = this.getProperty(name);
        if (ret == null) {
            ret = defaultValue;
        }
        return ret;
    }
    
    public Enumeration propertyNames() {
        throw new UnsupportedOperationException();
    }
    
    public void list(final PrintStream ps) {
        throw new UnsupportedOperationException();
    }
    
    public void list(final PrintWriter ps) {
        throw new UnsupportedOperationException();
    }
    
    public int size() {
        throw new UnsupportedOperationException();
    }
    
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }
    
    public Enumeration keys() {
        throw new UnsupportedOperationException();
    }
    
    public Enumeration elements() {
        throw new UnsupportedOperationException();
    }
    
    public boolean contains(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public boolean containsValue(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public boolean containsKey(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public Object get(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public Object put(final Object o1, final Object o2) {
        throw new UnsupportedOperationException();
    }
    
    public Object remove(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public void putAll(final Map m) {
        throw new UnsupportedOperationException();
    }
    
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    public Object clone() {
        throw new UnsupportedOperationException();
    }
    
    public String toString() {
        throw new UnsupportedOperationException();
    }
    
    public Set keySet() {
        throw new UnsupportedOperationException();
    }
    
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }
    
    public Collection values() {
        throw new UnsupportedOperationException();
    }
    
    public boolean equals(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
}
