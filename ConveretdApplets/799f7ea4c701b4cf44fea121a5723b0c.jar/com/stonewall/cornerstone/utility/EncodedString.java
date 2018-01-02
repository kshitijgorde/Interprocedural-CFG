// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.StringReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Date;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

class EncodedString
{
    private String content;
    private boolean decoded;
    private final Random random;
    private Map<String, String> properties;
    
    EncodedString(final String content) {
        this.decoded = false;
        this.random = new Random();
        this.properties = Collections.emptyMap();
        this.content = content;
    }
    
    void setProperties(final Map<String, String> properties) {
        (this.properties = properties).put("random", Long.toHexString(this.random.nextLong()));
        this.properties.put("date", new Date().toString());
    }
    
    void replace() {
        this.content = this.content.replaceAll("\\\\n", "\r\n");
    }
    
    private void replaceProperties() {
        final Properties p = System.getProperties();
        final Enumeration en = p.propertyNames();
        while (en.hasMoreElements()) {
            final String key = en.nextElement().toString();
            final String value = p.getProperty(key).replace('\\', '/');
            this.content = this.content.replaceAll("\\$\\{" + key + "\\}", value);
        }
        final Iterator<String> iterator = this.properties.keySet().iterator();
        while (iterator.hasNext()) {
            final String key = iterator.next();
            final String v = this.properties.get(key);
            this.content = this.content.replaceAll("\\$\\{" + key + "\\}", v);
        }
    }
    
    @Override
    public String toString() {
        if (!this.decoded) {
            this.replaceProperties();
            this.replace();
            this.decoded = true;
        }
        return this.content;
    }
    
    public Reader reader() {
        return new StringReader(this.toString());
    }
}
